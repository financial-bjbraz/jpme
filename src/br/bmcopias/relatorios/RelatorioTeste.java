package br.bmcopias.relatorios;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import br.bmcopias.dao.Apoio;

public class RelatorioTeste extends Apoio{
	
	public static void main(String[] args)  {
		Map parametros = new HashMap();
		parametros.put("USUARIO", "ALEX");
		parametros.put("DATA", "010101");
		
		
		RelatorioTeste rt = new RelatorioTeste();
		String urlReport = RelatorioTeste.class.getResource("relatorio_vendas.jasper").getPath();
		
		JasperPrint jasperPrint = null;
		
		try {
			jasperPrint = JasperFillManager.fillReport( urlReport, parametros, rt.getCon());
			JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorio_vendas.pdf");
			JasperViewer viewer = new JasperViewer(jasperPrint);
			viewer.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*

			 OutputStream ouputStream=new FileOutputStream(new File(FILE_PATH + parameter.getNomeArquivo()+ ".xls"));
			 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			
			 JRXlsExporter exporterXLS = new JRXlsExporter();
			 exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
			 exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);   
			 exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
			 exporterXLS.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, Boolean.TRUE);
			 exporterXLS.exportReport();
			 ouputStream.write(byteArrayOutputStream.toByteArray()); 
			 ouputStream.flush();
			 ouputStream.close();
			
			 response.setContentType("application/x-download");   
			 response.setHeader("Content-Disposition", "attachment;  filename=" + parameter.getNomeArquivo()+".xls");   
			
			JRXlsExporter xls=new JRXlsExporter();   
			
			OutputStream output = response.getOutputStream(  );   
			
			xls.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);   
			xls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,output);   
			xls.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);   
			xls.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);   
			xls.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, Boolean.TRUE);
			xls.exportReport();   
			output.flush();   
			output.close();  
			
			
			con.close();
			con = null;
	 * 
	 * */

}
