package br.bmcopias.layout.frame;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import br.bmcopias.util.Util;

public class Principal {


	
    protected static Image createImage(String path, String description) {
        URL imageURL = Util.class.getResource(path);
        
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }	

}
