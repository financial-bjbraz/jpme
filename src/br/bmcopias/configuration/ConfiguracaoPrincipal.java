package br.bmcopias.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class ConfiguracaoPrincipal {

	// Singleton
	private Properties props 				   = null;
	private static final String nomeProperties = "bm.properties";
	InputStream is 							   = null;
	static ConfiguracaoPrincipal c 			   = new ConfiguracaoPrincipal();

	private ConfiguracaoPrincipal() {

		this.is = this.getClass().getResourceAsStream(nomeProperties);

		props = new Properties();
		if (is == null)
			return;
		try {
			props.load(is);
		} catch (IOException ex) {
			props = null;
		}
	}

	public static ConfiguracaoPrincipal getInstance() {
		return c;
	}

	public String getString(String name) {
		if (props == null)
			return null;

		return props.getProperty(name);
	}

	public int getInt(String name) {
		if (props == null)
			return 0;

		String str = props.getProperty(name);
		if (str == null)
			return 0;

		int val = 0;
		try {
			val = Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			System.out.println("Invalid number parameter" + ex.toString());
			return 0;
		}
		return val;
	}

	public void listaProperties() {

		if (props == null) {
			return;
		}
		String item = null;

		Enumeration en = props.keys();
		while (en.hasMoreElements()) {
			System.out.println();
			item = (String) en.nextElement();
			System.out.print(item + " = ");
			System.out.println(props.getProperty(item));
		}

	}

	public Vector listaProps() {
		Vector<String[]> lista = new Vector<String[]>();
		if (props == null) {
			return null;
		}
		String[] items = null;
		Enumeration en = props.keys();
		String item = null;

		while (en.hasMoreElements()) {
			item = (String) en.nextElement();
			items = new String[2];
			items[0] = item;
			items[1] = props.getProperty(item);
			lista.add(items);
		}

		return lista;
	}

	public double getDouble(String name) {
		if (props == null)
			return 0;

		String str = props.getProperty(name);
		if (str == null)
			return 0;

		double val = 0;
		try {
			val = Double.parseDouble(str);
		} catch (NumberFormatException ex) {
			System.out.println("Invalid number parameter " + ex);
			return 0;
		}
		return val;
	}

	public boolean getBoolean(String name) {
		boolean retorno = false;

		try {
			String str = props.getProperty(name, "false");
			if (str.equalsIgnoreCase("true")) {
				retorno = true;
			}
		} catch (Exception ex) {
		}
		return retorno;
	}

	public void seta(String nomeProps, String valProps) throws Exception {
		try {
			props.setProperty(nomeProps, valProps);
			props.put(nomeProps, valProps);
			System.out.println("Setando novas propriedades : " + nomeProps + ". Valor : " + valProps);
			this.saveProperties();
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void saveProperties() throws Exception {
//		FileOutputStream fo = null;
//		String path = props.getProperty("jcoletor.root.path");
//		if (this.props != null) {
//			try {
//				File f = new File(path);
//				f = new File(f, nomeProperties);
//				fo = new FileOutputStream(f);
//				System.out.println("Tentando Salvar Arquivo.");
//				this.props.store(fo, "conf.properties");
//
//			} catch (IOException ex) {
//				System.out.println("Erro. ");
//				ex.printStackTrace();
//				//log.severe("Unable to save configuration file");
//			} finally {
//				try {
//					if (fo != null)
//						fo.close();
//				} catch (IOException ex1) {
//					ex1.printStackTrace();
//				}
//			}
//		}
	} // Fim do Methodo

}

