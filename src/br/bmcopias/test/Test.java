package br.bmcopias.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Test {
	
	public static void main(String[] args) throws Exception{
		try {
			String s = "\\bsbr\\teste\\";
			BufferedWriter out = new BufferedWriter(new FileWriter("c:\\temp\\test.txt"));
			out.write("aString\nthis is a\nttest");
			out.close();
			} 
			catch (IOException e) 
			{ 
			System.out.println("Exception ");

			}

	}

}
