package barcode;

import com.lowagie.text.pdf.Barcode;
import com.lowagie.text.pdf.Barcode128;

public class BarCode {
	
	public static void main(String[] args) {
		
		Barcode bc = new Barcode128();
		bc.setCode("BM COPIAS");
		//https://svn.java.net/svn/myerp~source-code-repository 
	}

}
