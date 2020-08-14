package barcode.examples;

import java.awt.print.PrinterJob;

import javax.swing.JFrame;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;

/**
 * 
 * Print a barcode using Java's print API
 * 
 * @author Sean C. Sullivan
 * 
 */
public class PrintingExample {

	public static void main(String[] args) {
		try {
			Barcode b = BarcodeFactory.createCode128("BM COPIAS");
			PrinterJob job = PrinterJob.getPrinterJob();
			
			JFrame frame = new JFrame();
			frame.add(b);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
			 job.setPrintable(b);
			 if (job.printDialog())
			 {
			 job.print();
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
