package br.bmcopias.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.border.Border;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.bmcopias.bean.ProdutoBean;
import br.bmcopias.configuration.ConfiguracaoPrincipal;

import com.altec.bsbr.app.jpme.exception.SenhaInvalidaException;
import com.altec.bsbr.fw.ErrorMessage;

public class Util {
	
	private static final Logger log = LoggerFactory.getLogger(Util.class);

	public static final int NIVEL_OPERADOR = 0;
	static DateFormat datfmt1 = null;
	static DateFormat datfmt2 = null;
	static DateFormat datfmt3 = null;
	
    private static ConfiguracaoPrincipal con = ConfiguracaoPrincipal.getInstance();

	public static String getTime() {
		String data = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat();
			Date hoje = new Date();
			data = sdf.format(hoje);
		} catch (Exception e) {
			logarException(e);
		}
		return data;
	}

	public static void logarException(Exception e) {
		log.error("Lançada a Exception :: " + e.getMessage());

	}

	public static void logarException(Exception e, String msg) {
		log.error("Lançada a Exception :: " + e.getMessage() + " " + msg);

	}

	public static void logar(Object o) {
		log.info("Util.logar :: " + o);
	}
	
	public static void info(Object o) {
		log.info("Util.logar :: " + o);
	}
	
	public static void error(Object o) {
		log.error("Util.logar :: " + o);
	}
	
	public static void debug(Object o) {
		log.debug("Util.logar :: " + o);
	}	

	public static void logar(String s, Object o) {
		log.info("Util.logar :: "+ s + " " + o);
	}

	public static void sairDoSistema() {
		System.exit(1);
	}

	public static Border getBordaPadrao() {
		Border borda = BorderFactory.createLineBorder(Color.BLUE, 2);
		return borda;
	}

	public static Icon getIconeClientes() {
		Icon i = new ImageIcon(Util.class.getResource("coordenador.gif"));
		return i;
	}

	public static Icon getIcone(IconeEnum iconeE) {
		Icon i = new ImageIcon(Util.class.getResource(iconeE.getNomeIcone()
				+ iconeE.getExtensaoIcone()));
		return i;
	}

	public static BufferedImage getBufferedImage(String nomeArquivo, Component c) {

//		Image image = c.getToolkit().getImage(con.getString("bmc.pastaImages")+nomeArquivo);
		
		Image image = null;
		
		if(con.getBoolean("bmc.buscaImageFromBundle")){
			image = c.getToolkit().getImage(con.getString("bmc.pastaImages")+nomeArquivo);
		}else{
			c.getToolkit().getImage(Util.class.getResource(nomeArquivo));
		}

		waitForImage(image, c);
		BufferedImage bufferedImage = null;

		try {

			System.out.println("Imagem : " + nomeArquivo);
			bufferedImage = new BufferedImage(image.getWidth(c), image
					.getHeight(c), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = bufferedImage.createGraphics();
			g2d.drawImage(image, 0, 0, c);

		} catch (Exception e) {
			Util.logarException(e);
		}

		return (bufferedImage);
	}

	/**
	 * Recupera uma imagem associada a um arquivo, e espera até que ela esteja
	 * totalmente carregada.
	 */
	public static boolean waitForImage(Image image, Component c) {
		MediaTracker tracker = new MediaTracker(c);
		tracker.addImage(image, 0);
		try {
			tracker.waitForAll();
		} catch (InterruptedException ie) {
		}
		return (!tracker.isErrorAny());
	}

	public static Font getDefaultFont() {
		 Font f = new Font("Verdana", 0, 10);
		 return f;
	}
	
	/**
	 * "Serif", 
	 * "SansSerif", 
	 * "Monospaced", 
	 * "Dialog",
      "DialogInput"
	 * @param tipoFonte
	 * @return
	 */
	public static Font getFont(String tipoFonte, int i, int tamanho){
		Font f = new Font(tipoFonte, i, tamanho);
		 return f;
	}

	public static Border getTitledBorder(String string) {
		return BorderFactory.createTitledBorder(BorderFactory
				.createBevelBorder(2), string);
	}

	public static Object getStringComAspas(String str) {
		if (str == null) {
			return null;
		}

		str = "'" + str + "'";

		str = str.toUpperCase();

		return str;
	}

	public static Icon getIcone(String string) {
		
		Icon i = null;
		
		if(con.getBoolean("bmc.buscaImageFromBundle")){
			i = new ImageIcon(con.getString("bmc.pastaImages") + string);
		}else{
			i = new ImageIcon(Util.class.getResource(string));
		}
		
//		Icon i = new ImageIcon(con.getString("bmc.pastaImages") + string);
		return i;
	}
	
	public static ImageIcon getImageIcon(String nomeArquivo) {
		ImageIcon i = new ImageIcon(con.getString("bmc.pastaImages") + nomeArquivo);
		return i;
	}	

	public static final boolean isDate(Object o) {
		if (o instanceof GregorianCalendar)
			return true;

		if (o instanceof java.util.Date)
			return true;

		if (o == null)
			return false;

		String so = o.toString();

		if (datfmt1 == null) {
			datfmt1 = new SimpleDateFormat("dd/MM/yy");
			// Flavio - 20/07/2005 datfmt1 =
			// DateFormat.getDateInstance(DateFormat.SHORT);
			datfmt2 = DateFormat.getDateTimeInstance(DateFormat.SHORT,
					DateFormat.MEDIUM);
			datfmt3 = DateFormat.getDateTimeInstance(DateFormat.SHORT,
					DateFormat.SHORT);
			datfmt1.setLenient(false);
			datfmt2.setLenient(false);
			datfmt3.setLenient(false);
		}

		if (so.indexOf(":") == -1) {
			if (so.length() <= 5)
				so += "/" + new GregorianCalendar().get(Calendar.YEAR);

			try {
				datfmt1.parse(so);
			} catch (Exception e) {
				return false;
			}
		} else {
			try {
				datfmt2.parse(so);
			} catch (Exception e) {
			}

			try {
				datfmt3.parse(so);
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}

	public static final GregorianCalendar toDate(Object o) {
		if (o == null)
			return null;

		if (o instanceof GregorianCalendar)
			return (GregorianCalendar) o;

		if (o instanceof java.util.Date) {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime((java.util.Date) o);
			return gc;
		}

		if (datfmt1 == null) {
			datfmt1 = new SimpleDateFormat("dd/MM/yy");
			// Flavio - 20/07/2005 datfmt1 =
			// DateFormat.getDateInstance(DateFormat.SHORT);
			datfmt2 = DateFormat.getDateTimeInstance(DateFormat.SHORT,
					DateFormat.MEDIUM);
			datfmt3 = DateFormat.getDateTimeInstance(DateFormat.SHORT,
					DateFormat.SHORT);
			datfmt1.setLenient(false);
			datfmt2.setLenient(false);
			datfmt3.setLenient(false);
		}

		GregorianCalendar gc = new GregorianCalendar();
		java.util.Date dt;

		String so = o.toString();

		if (so.indexOf(":") == -1) {
			if (so.length() <= 5)
				so += "/" + new GregorianCalendar().get(Calendar.YEAR);

			try {
				dt = datfmt1.parse(so);
			} catch (Exception e) {
				// e.printStackTrace();
				return null;
			}
		} else {
			try {
				dt = datfmt2.parse(so);
			} catch (Exception e) {
				// e.printStackTrace();
				return null;
			}

			try {
				dt = datfmt3.parse(so);
			} catch (Exception e) {
				// e.printStackTrace();
				return null;
			}
		}

		gc.setTime(dt);
		return gc;
	}

	public static JPanel panelBox(Component[] labels, Component[] fields) {
		return panelBox(labels, fields, true);
	}

	public static JPanel panelBox(Component[] labels, Component[] fields,
			boolean wrapped) {
		JPanel pnlLabel = new JPanel(new GridLayout(0, 1, 0, 0));
		pnlLabel.setOpaque(false);
		pnlLabel.setFont(getDefaultFont());

		JPanel pnlField = new JPanel(new GridLayout(0, 1, 0, 0));
		pnlField.setOpaque(false);

		JPanel pnlTop = new JPanel();
		pnlTop.setOpaque(false);
		pnlTop.setLayout(new BorderLayout(0, 0));

		for (int i = 0; i < labels.length; ++i) {
			if (labels[i] != null){
				labels[i].setFont(getDefaultFont());
				pnlLabel.add(labels[i]);
			}else {
				JLabel lblBlank = new JLabel(" ");
				lblBlank.setOpaque(false);
				pnlLabel.add(lblBlank);
			}

			if (fields[i] != null)
				if (wrapped)
					pnlField.add(wrapPanel(fields[i]));
				else
					pnlField.add(fields[i]);
			else {
				JLabel lblBlank = new JLabel(" ");
				lblBlank.setOpaque(false);
				pnlField.add(lblBlank);
			}
		}

		pnlTop.add(BorderLayout.WEST, pnlLabel);
		pnlTop.add(BorderLayout.CENTER, pnlField);

		return pnlTop;
	}
	
	public static JPanel wrapPanel(Component p) {
        return wrapPanel(p, FlowLayout.LEFT);
    }

    public static JPanel wrapPanel(Component p, int alignment) {
        JPanel pnl = new JPanel(new FlowLayout(alignment, 0, 0));
        pnl.setOpaque(false);
        pnl.add(p);

        return pnl;
    }

	public static boolean isBlankOrNull(String descr) {
		return descr == null || descr.equals("");
	}

	public static Object[][] preencheDadosByLista(List<ProdutoBean> al) {
		Object[][] data = null;
		
        if (al != null && al.size() != 0) {

        	data = new Object[al.size()][4];

            for (int i = 0; i < al.size(); i++) {
                ProdutoBean ra = (ProdutoBean) al.get(i);
                data[i][0] = String.valueOf(ra.getProdutoId());
                data[i][1] = ra.getDescricao();
                data[i][2] = getAsString(ra.getVlrEstimadoCusto());
                data[i][3] = dateToString(ra.getDataCriacao());
            }

        }
        
        return data;
	}

	public static String dateToString(java.sql.Date dataCriacao) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String retorno = sdf.format(dataCriacao);
		return retorno;
	}
	
	public static String getAsString(double vlr) {
        Locale.setDefault(new Locale("pt", "BR"));
        DecimalFormat df = new DecimalFormat("#,##0.00");

        try{
            return df.format(vlr);
        } catch (Exception e) {
            return null;
        }
    }
	
	public static Font getFonteGrande() {
		return new Font("Arial", 20, 20);
	}

	public static Font getFonteMedia() {
		return new Font("Arial", 15, 15);
	}
	
	public static Font getFonteMediaVerdana() {
		return new Font("Verdana", 15, 15);
	}	

	public static String getValorString(String vlr) {
		if(vlr == null){ return "" ;}
		
		return vlr;
	}

	public static String getTituloMensagemOptionPane() {
		return "Informação";
	}
	
	public static JFormattedTextField getTextFormatoValor(){
		DecimalFormat moneyFormat = (DecimalFormat)NumberFormat.getNumberInstance();
		moneyFormat.setMaximumFractionDigits(2);
		moneyFormat.setMinimumFractionDigits(2);
		moneyFormat.setNegativePrefix("(");
		moneyFormat.setNegativeSuffix(")");		
		
		JFormattedTextField retorno = new JFormattedTextField(moneyFormat);
		retorno.setValue(new Double(0));
		retorno.setColumns(8);
		retorno.setFocusLostBehavior(
	            JFormattedTextField.COMMIT_OR_REVERT);
		
		return retorno;
	}
	
	public static JFormattedTextField getTextFormatoQuantidade(){
		DecimalFormat moneyFormat = (DecimalFormat)NumberFormat.getNumberInstance();
		moneyFormat.setMaximumFractionDigits(0);
		moneyFormat.setMaximumFractionDigits(0);
		moneyFormat.setNegativePrefix("(");
		moneyFormat.setNegativeSuffix(")");	
		JFormattedTextField retorno = new JFormattedTextField(moneyFormat);
		retorno.setValue(new Integer(0));
		retorno.setColumns(8);
		retorno.setFocusLostBehavior(
	            JFormattedTextField.COMMIT_OR_REVERT);
		
		return retorno;
	}
	
	public static JFormattedTextField getTextFormatoQuantidade(int numeroColunas, Integer defaultValue){
		DecimalFormat moneyFormat = (DecimalFormat)NumberFormat.getNumberInstance();
		moneyFormat.setMaximumFractionDigits(0);
		moneyFormat.setMaximumFractionDigits(0);
		moneyFormat.setNegativePrefix("(");
		moneyFormat.setNegativeSuffix(")");	
		JFormattedTextField retorno = new JFormattedTextField(moneyFormat);
		retorno.setValue(defaultValue);
		retorno.setColumns(numeroColunas);
		retorno.setFocusLostBehavior(
	            JFormattedTextField.COMMIT_OR_REVERT);
		
		return retorno;
	}	

	public static double getAsDouble(String text) {
		text = text.replace(",", ".");
		Double d = new Double(text);
		return d;
	}

	public static String getTituloFrames() {
		return con.getString("bmc.frameTitle");
	}
	
	public static boolean verifyCGC(String cgc) //string com 12 posições
	{
		if (cgc == null)
			return false;

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cgc.length(); ++i)
			if (cgc.charAt(i) >= '0' && cgc.charAt(i) <= '9')
				sb.append(cgc.charAt(i));

		if (sb.toString().length() != 14 || Double.parseDouble(sb.toString())==0)
			return false;

		cgc = nedit(sb.toString(), 14);

		int digito1 = 99;
		int digito2  ;

		digito1=calculaDigitoCGC(cgc, 1, digito1);
		digito2=calculaDigitoCGC(cgc, 2, digito1);
		String controle = String.valueOf(digito1)+String.valueOf(digito2);

		if (!controle.equals(cgc.substring(cgc.length()-2,cgc.length())))
				return false;
		else
				return true;
	}
	
	private static int calculaDigitoCGC(String cgc, int calculaDigito, int digito1)
	{
			int fim = 11;
			int digito;
			int soma;
			int fator = 0;

	 		int multiplo = 5;

			if (calculaDigito == 2)
				  multiplo = 6;

			soma = 0;
			for (int i = 0; i <= fim; ++i)
			{
				soma += Integer.parseInt(cgc.substring(i, i+1)) * multiplo;
				if (--multiplo < 2)
					 multiplo = 9;
			}

			if (calculaDigito == 2)
					soma += digito1 * 2;

		 	fator = (soma/11)*11;
			int resto = soma - fator;

			if (resto == 0 || resto ==1)
				digito = 0;
			else
				digito = 11 - resto;

			return digito;
	}
	
	public static final String nedit(String txt, int len) {
        StringBuffer s;

        if (txt == null)
            s = new StringBuffer();
        else
            s = new StringBuffer(txt);

        for (int i = s.length(); i < len; ++i)
            s.insert(0, '0');

        return s.toString().substring(s.length() - len, s.length());
    }

    public static final String redit(String txt, int len) {
        StringBuffer s;

        if (txt == null)
            s = new StringBuffer();
        else
            s = new StringBuffer(txt);

        for (int i = s.length(); i < len; ++i)
            s.insert(0, ' ');

        return s.toString().substring(s.length() - len, s.length());
    }

    public static final String redit(String txt, int len, char ch) {
        StringBuffer s;

        if (txt == null)
            s = new StringBuffer();
        else
            s = new StringBuffer(txt);

        for (int i = s.length(); i < len; ++i)
            s.insert(0, ch);

        return s.toString().substring(s.length() - len, s.length());
    }

	public static AbstractFormatterFactory getFormatterTextField(String string) {
		MaskFormatter mf = null;
		try {
			mf = new MaskFormatter(string);
		} catch (ParseException e1) {
			logar("Erro ao tentar criar o formatter do text field", e1.getMessage());
		}
		
		DefaultFormatterFactory formatt = new DefaultFormatterFactory(mf);
		return formatt;
	}

	public static String getDataHojeDDMMYYYY() {
		return getDataDDMMYYYY(new Date());
	}
	
	public static String getHojeSomaMesDDMMYYYY(int qtdMeses) {
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, qtdMeses);
		return getDataDDMMYYYY(cal.getTime());
	}	
	
	public static String getDataDDMMYYYY(Date data){
		String retorno = "";
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			retorno = sdf.format(data);
		}catch(Exception e){}
		
		return retorno;
	}

	public static Icon getIconeEmpresa() {
//		Icon i = new ImageIcon(con.getString("bmc.pastaImages") + con.getString("bmc.logo.telaInicial"));
		Icon i = null;//new ImageIcon(Util.class.getResource(con.getString("bmc.logo.telaInicial")));
		if(con.getBoolean("bmc.buscaImageFromBundle")){
			i = new ImageIcon(con.getString("bmc.pastaImages") + con.getString("bmc.logo.telaInicial"));
		}else{
			i = new ImageIcon(Util.class.getResource(con.getString("bmc.logo.telaInicial")));
		}		
		
		return i;
	}
	
	public static String convertDoubleToString(double d)throws Exception{
		String conv         = null;
		NumberFormat format = DecimalFormat.getInstance();
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);
		NumberFormat format2 = new DecimalFormat("####.00");
		conv                = format.format(d);
		return format2.parse(conv).toString();
	}
	
    private static final String EMPTY_STRING = "";
    private static final DecimalFormat INTEGER_FORMAT = new DecimalFormat("#");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat DATE_HOUR_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public static final String INDICADOR_INATIVO = "0";
	public static final String INDICADOR_ATIVO   = "1";

	public static final String USUARIO_DEFAULT = "SISTEMAGCF";
    
    @SuppressWarnings("unchecked")
	public static boolean temProximo(List list, int quantidadePagina){
    	
        boolean temProximo = false;
        
        if(list.size() > quantidadePagina){
        	temProximo = true;
        	list.remove(list.size() - 1);
        }    
        
        return temProximo;
    	
    }
    
    public static Long getLong(String str){
    	if(str == null){
    		return new Long(0);
    	}
    	return new Long(str);	
    }
    
    public static Date stringToDate(String data) {
        DateTime dt = null;
        try {
            DateTimeFormatter fmt = DateTimeFormat.forPattern("ddMMyyyy");
            dt  = fmt.parseDateTime(data);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dt.toDate();
    }
    
    public static java.sql.Date stringToSQLDate(String data) {
        DateTime dt = null;
        try {
            DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
            dt  = fmt.parseDateTime(data);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return new java.sql.Date(dt.getMillis());
    }    
    
    public static Date createDate(String data) {
        Date dt = null;
        try {
            SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            dt  = dtf.parse(data);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }
    
    public static String dateHourToString(Date data){
        String dt = null;
        try {
            SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dt = dtf.format(data);
        }
        catch (Exception e) {
            /*caso não esteja no formato correto devolve null
             * por isso não está sendo lançada nem tratada a exception
            */
        }
        return dt;    	
    }
    
    public static String dateToString(Date data) {
        String dt = null;
        try {
            dt = DateFormat.getDateInstance(DateFormat.MEDIUM).format(data);
        }
        catch (Exception e) {
            /*caso não esteja no formato correto devolve null
             * por isso não está sendo lançada nem tratada a exception
            */
        }
        return dt;
    }
    
    public static String dateToStringDDMMYYYmmss(Date data) {
        String dt = null;
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//            dt = DateFormat.getDateInstance(DateFormat.MEDIUM).format(data);
        	dt = sdf.format(data);
        }
        catch (Exception e) {
            /*caso não esteja no formato correto devolve null
             * por isso não está sendo lançada nem tratada a exception
            */
        }
        return dt;
    }    
    
    public static String getStringData(Object ob){
        String ret = null;
        if (ob instanceof BigDecimal){
            BigDecimal b = (BigDecimal)ob;
            ret = b.toString();
        } else if (ob instanceof String){
            String t = (String)ob;
            ret = t.trim();
        } else if (ob instanceof Boolean){
            Boolean bo = (Boolean) ob;
            ret = bo.toString().trim();
        }
        
        if(ret == null){
            return "";
        }
        
        return ret;
    }
    
    @SuppressWarnings("unchecked")
    public static boolean isEmpty(Object o){
        if(o == null){
            return true;
        }
        
        if(o instanceof String && ((String)o).trim().equals(EMPTY_STRING)){
            return true;
        }        
        // QA - Call to equals() with null argument
        if(o instanceof BigDecimal && ((BigDecimal)o).equals(new BigDecimal(0))){
            return true;
        }        
        
        if(o instanceof Collection && ((Collection)o).isEmpty()){
            return true;
        }
        
        return false;
    }
    
    public static boolean isNull(Object o){
        if(o == null){
            return true;
        }
        
        return false;
    }
    
    public static boolean notEmpty(Object o){
        return !isEmpty(o);
    }
    
    public static Date addOneDay(Date dt){
        Calendar cal = new GregorianCalendar();
        cal.setTime(dt);
        cal.add(Calendar.DAY_OF_MONTH, 1); 
        return cal.getTime();
    }
    
    public static Date addDay(Date dt, int qtde){
        Calendar cal = new GregorianCalendar();
        cal.setTime(dt);
        cal.add(Calendar.DAY_OF_YEAR, qtde); 
        return cal.getTime();
    }
    
    public static BigDecimal getBigDecimal(String decimal) {
        if (decimal == null) {
            return new BigDecimal(0);
        }
        else {
            return new BigDecimal(decimal);
        }
    }       
    
    public static BigDecimal getBigDecimal(BigDecimal decimal) {
        if (decimal == null) {
            return new BigDecimal(0);
        }
        else {
            return decimal;
        }
    }   
    
    public static String getString(String string) {
        if (string == null) {
            return "";
        }
        else {
            return string;
        }
    }   
    
    public static Date getDate(Date date) {
        if (date == null) {
            return new Date(0);
        }
        else {
            return date;
        }
    }      

    public static String getString(BigDecimal decimal) {
        if (decimal == null) {
            return "";
        }
        else {
            return decimal.toString();
        }
    }
    
    
    public static String getStringForNumber(BigDecimal decimal) {
        if (decimal == null) {
            return null;
        }
        else {
            return decimal.toString();
        }
    }
    
    public static String getStringFromInteger(BigDecimal decimal) {
        if (decimal == null) {
            return null;
        }
        else {
            return INTEGER_FORMAT.format(decimal.intValue());
        }
    }
    
    public static String getStringFromDouble(BigDecimal decimal) {
        if (decimal == null) {
            return null;
        }
        else {
            return String.valueOf(decimal.doubleValue());
        }
    }
    
    public static String trim(Object ob){
        String ret = null;
        if (ob instanceof String){
            String t = (String) ob;
            ret = t.trim();
        }
        return ret;
    }
    
    public static String desFormatCNPJ(String cnpj){
        if (cnpj != null){
            return cnpj.replace(".", "").replace("/", "").replace("-", "");
        }
        return null;
    }
    
    public static Date setHourToDate(Date dt){
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static String createStringFromMap(Map<String, String> situacoes, String separador) {
        String retorno = "";
        if (situacoes != null) {
            for (Entry<String, String> entry : situacoes.entrySet()) {
                retorno += entry.getValue()+separador;
            }
            if(retorno.length() >= 1){
                retorno = retorno.substring(0, retorno.length() - 1);
            }    
        }
        return retorno;  
    }
    
    public static boolean compareRange(Date dataInicial, Date dataFinal, int diasRange){
        Calendar gcInicial = GregorianCalendar.getInstance();
        gcInicial.setTime(dataInicial);
        gcInicial.add(Calendar.DAY_OF_YEAR, diasRange);
        
        Calendar gcFinal = GregorianCalendar.getInstance();
        gcFinal.setTime(dataFinal);
        
        if(gcInicial.before(gcFinal)){
            return false;
        }
        
        return true;
    }
    
    public static boolean isToday(Date someDate){
    	DateTime dateTime = new DateTime(someDate).withMillisOfDay(0);
    	DateTime today = new DateTime().withMillisOfDay(0);
    	
    	Days days = Days.daysBetween(dateTime, today);
    	
    	if(days.getDays()==0) return true;
    	
    	return false;
    }

	/**
	 * Calcula e retorna a diferença em dias entre 2 datas. Se a dataInicial for posterior a dataFinal
	 * o resultado será negativo.
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 */
	public static int diffDays(Date dataInicial, Date dataFinal) {
    	DateTime dtInicial = new DateTime(dataInicial).withMillisOfDay(0);
    	DateTime dtFinal = new DateTime(dataFinal).withMillisOfDay(0);
    	
    	Days days = Days.daysBetween(dtInicial, dtFinal);		
		return days.getDays();
	}
	
	public static String addZeroEsq(BigDecimal val){
		String ret = "0";
		if (val.intValue() >= 1 && val.intValue() <= 9){
			ret = "0".concat(val.toString());
		} else {
			ret = val.toString();
		}
		return ret;
	}
	
	public static String addZeroQtdEsq(BigDecimal val, int qtd){
		String ret = "";
		for (int i = 0; i < qtd; i++) {
			ret = ret.concat("0");
		}
		return ret.concat(val.toString());
	}
	
	/**
	 * Valida se a data é feriado Sabado = 0, Domingo = 1, Não feriado = -1.
	 * @param date
	 * @return
	 */
	public static int validarFeriado(Date date){
		int valid = -1;
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dia = c.get(Calendar.DAY_OF_WEEK);
        if (dia == Calendar.SATURDAY){
        	valid = 0;
        } else if (dia == Calendar.SUNDAY){
        	valid = 1;
        }
		return valid;
	}
	
	/**
	 * Valida se a Operação é D-1.
	 * @param date
	 * @return
	 */
	public static boolean validaDataD_1(Date date){
		boolean valid = false;
		Date dtMov = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dtMov);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        dtMov = c.getTime();
        Date td = new Timestamp(dtMov.getTime());
    	if (date.before(td)){
    		valid = true;
    	}
		return valid;
	}
	
    public static BigDecimal dividir(BigDecimal divisor, BigDecimal dividendo) {
        return divisor.divide(dividendo, RoundingMode.DOWN).setScale(2, RoundingMode.DOWN);
    }

    public static BigDecimal multiplicar(BigDecimal fator1, BigDecimal fator2) {
        return fator1.multiply(fator2).setScale(2, RoundingMode.DOWN);
    }

	public static String convertLongToString(long idVenda) {
		if(idVenda == 0){
			return "";
		}
		
		return String.valueOf(idVenda);
	}

    public static Image createImage(String path, String description) {
        URL imageURL = Util.class.getResource(path);
        
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

	public static java.sql.Date hoje() {
		return new java.sql.Date(new Date().getTime());
	}
	
    public static Timestamp hojeDataHoraTimesTamp() {
        return new Timestamp(new Date().getTime());
    }
	
	public static String hojeString(){
		return dateToString(hoje());
	}
    
    public static final String zn(String p) {
        if (p == null)
            return EMPTY_STRING;
        else
            return p;
    }
    

    public static boolean isNotBlankOrNull(Object o) {
        return !isBlankOrNull(o);
    }

    public static boolean isBlankOrNull(Object o) {
        if (o == null) {
            return true;
        }

        if (EMPTY_STRING.equals(o.toString())) {
            return true;
        }

        return false;
    }

    public static boolean isBlankZeroOrNull(Object o) {
        if (o == null) {
            return true;
        }

        if (o instanceof Number) {

            if ((((Number) o)).intValue() == 0) {
                return true;
            }

        }

        if (EMPTY_STRING.equals(o.toString())) {
            return true;
        }

        return false;
    }
    
    
    public static String generateNewPassword() {
        Random r = new Random();
        String s = String.valueOf(r.nextLong());

        while (s.length() < 7) {
            s = s + "a";
        }

        s = s.replace("-", "Z");

        return s.substring(0, 7);
    }

	public static String getJndiNameContext() {
		return "bjbraz/context";
	}
	
	/**
     * 
     * @param criptoString
     * @return
	 * @throws IOException 
     * @throws IOException
     */
    public static String descriptoBase64(String criptoString)  {
        String decript = null;
		try {
			decript = new String(new sun.misc.BASE64Decoder().decodeBuffer(criptoString));
		} catch (IOException e) {
			logar(e.getMessage());
		}
        return decript;
    }

    public static String encriptBase64(String criptoString) {
        String encript = new String(new sun.misc.BASE64Encoder().encode(criptoString.getBytes()));
        return encript;
    }
	

}