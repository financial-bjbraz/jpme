package br.com.bjbraz.screen;

import java.text.MessageFormat;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.PropertyOverrideConfigurer;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import br.bmcopias.layout.frame.Splash;

import com.altec.bsbr.fw.batch.Handler;
import com.altec.bsbr.fw.config.RunningMode;
import com.altec.bsbr.fw.config.Version;
import com.altec.bsbr.fw.logging.LoggingConfigurator;

public class JpmeApplicationInitializer {

	private static Logger logger = LoggerFactory.getLogger(BatchRunner.class);
	public static final int BATCH_OK = 0;
	public static final int BATCH_WARN = 100;
	public static final int BATCH_ERROR = 1;
	private boolean sharedOutput;
	private Handler startHandler;
	private Long maxTasksRunning;
	private Long sleepPeriodInMilis;
	private static String batchConfig = "classpath:/batch.xml";
	private static String batchProperties = "classpath:/META-INF/config/batch.properties";
	private static String batchPropertiesRM = "classpath:/META-INF/config/{runningMode}/batch.properties";
	private static int returnCode = 0;

	public JpmeApplicationInitializer() {
		sleepPeriodInMilis = Long.valueOf(100L);
	}

	public static void setReturnCode(int AreturnCode) {
		returnCode = AreturnCode;
	}

	public static int getReturnCode() {
		return returnCode;
	}

	public void setMaxTasksRunning(Long maxTasksRunning) {
		if (maxTasksRunning.longValue() == 0L
				|| maxTasksRunning.longValue() == -1L)
			this.maxTasksRunning = null;
		this.maxTasksRunning = maxTasksRunning;
	}

	public void setSleepPeriodInMilis(Long sleepPeriodInMilis) {
		this.sleepPeriodInMilis = sleepPeriodInMilis;
	}

	public boolean isSharedOutput() {
		return sharedOutput;
	}

	public void setSharedOutput(boolean sharedOutput) {
		this.sharedOutput = sharedOutput;
	}

	public static int main() {
		logger.info("Starting BatchRunner");
		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		Splash splashScreen = new Splash();
		splashScreen.showSplash();
		splashScreen.setProgressMaxMin(0, 1600);
		splashScreen.pack();
		splashScreen.setDisplayString(100, "Validando instalação...");
		
		GenericApplicationContext ctx = new GenericApplicationContext();
		int retCode;
		try {
			RunningMode.configure("batch");
			LoggingConfigurator loggingConfigurator = new LoggingConfigurator();
			loggingConfigurator.configure();
			Logger startup = LoggerFactory.getLogger("STARTUP");
			startup
					.info("================================================================");
			startup.info("  Starting system configuration...");
			startup.info((new StringBuilder())
					.append("  Application Version [").append(
							Version.getApplicationVersion()).append("]")
					.toString());
			startup.info((new StringBuilder())
					.append("  Framework Version   [").append(
							Version.getFrameworkVersion()).append("]")
					.toString());
			startup.info((new StringBuilder()).append("  Running mode        ")
					.append(RunningMode.get()).toString());
			XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
			xmlReader.loadBeanDefinitions(new ClassPathResource(
					"/META-INF/root-context.xml"));
			xmlReader.loadBeanDefinitions(new ClassPathResource(
					"/META-INF/batch-context.xml"));
			xmlReader.loadBeanDefinitions(batchConfig);
			splashScreen.setDisplayString(600, "Validando configurações...");
			PropertiesBeanDefinitionReader propReader = new PropertiesBeanDefinitionReader(
					ctx);
			String currentBatchPropertiesRM = batchPropertiesRM.replace(
					"{runningMode}",
					System.getProperty("runningMode") == null ? "[NONE]"
							: ((CharSequence) (System
									.getProperty("runningMode"))));
			Resource batchPropertiesRMResource = propReader.getResourceLoader()
					.getResource(currentBatchPropertiesRM);
			if (batchPropertiesRMResource == null
					|| !batchPropertiesRMResource.exists()) {
				Resource batchPropertiesResource = propReader
						.getResourceLoader().getResource(batchProperties);
				if (batchPropertiesResource != null
						&& batchPropertiesResource.exists()) {
					PropertyOverrideConfigurer cfg = new PropertyOverrideConfigurer();
					cfg.setLocation(batchPropertiesResource);
					cfg.postProcessBeanFactory(ctx.getBeanFactory());
				} else {
					throw new IllegalStateException(
							MessageFormat
									.format(
											"None of the following ({0}, {1}) config files could be found",
											new Object[] {
													currentBatchPropertiesRM,
													batchProperties }));
				}
			}
			splashScreen.setDisplayString(700, "Conectando ao banco de dados...");
			ctx.refresh();
			splashScreen.setDisplayString(800, "Carregando interface...");
			startup.info("  Application context configured");
			startup.info("================================================================");
			br.com.bjbraz.screen.BatchRunner batchRunner = (BatchRunner) ctx.getBean("batchRunner");
			splashScreen.setDisplayString(1000, "Carregando interface...");
			retCode = batchRunner.run(splashScreen, ctx);
			
		} catch (BeanDefinitionStoreException beanDefinitionStoreException) {
			retCode = 1;
			logger.error("Erro configuracao", beanDefinitionStoreException);
			logger.error("Please check your configurations and ensure that 'runningMode' system property was set properly.");
		} catch (Throwable t) {
			retCode = 1;
			logger.error("Error running batch", t);
		} finally {
			if (ctx != null)
				ctx.stop();
		}
		logger.info((new StringBuilder()).append("Finishing BatchRunner -> ")
				.append(message(retCode)).toString());
		return retCode;
	}
	
	private static String message(int code) {
		if (code == 0)
			return (new StringBuilder()).append("OK:[").append(code)
					.append("]").toString();
		if (code >= 100 && code <= 199)
			return (new StringBuilder()).append("WARNING:[").append(code)
					.append("]").toString();
		if (code >= 1 && code <= 100)
			return (new StringBuilder()).append("ERROR:[").append(code).append(
					"]").toString();
		else
			return (new StringBuilder()).append("BUSINESS CODE:[").append(code)
					.append("]").toString();
	}

	public static void main(String args[]) {
		if (System.getProperty("batchConfig") != null)
			batchConfig = System.getProperty("batchConfig");
		if (System.getProperty("batchProperties") != null)
			batchProperties = System.getProperty("batchProperties");
		
		int retorno = main();
		
		if(retorno > 0){
			System.exit(retorno);
		}
	}
}
