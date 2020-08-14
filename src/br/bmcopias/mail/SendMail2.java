package br.bmcopias.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author Alex Simas Braz
 *
 */
public class SendMail2 {
	
	public static void send(String smtpServer, String to, String from, String subject, String body)   
	  {   
	    try  
	    {   
	      Properties props = System.getProperties();   
	      props.put("mail.host", "smtp.mail.yahoo.com.br");
	      props.put("mail.smtp.host", "smtp.mail.yahoo.com.br");
	      props.put("mail.transport.protocol", "smtp");
//	      props.put("mail.smtp.port", "587");
	      
	      Session session = Session.getDefaultInstance(props, null);   
	  
	       
	      Message msg = new MimeMessage(session);   
	  
	        
	      msg.setFrom(new InternetAddress(from));   
	      msg.setRecipients(Message.RecipientType.TO,   
	      InternetAddress.parse(to, false));   
	  
	    
	      msg.setSubject(subject);   
	      msg.setText(body);   
	  
	     
	      msg.setHeader("Alex", "Alex");   
	      msg.setSentDate(new Date());   
	  
	    
	      Transport.send(msg);   
	  
	      System.out.println("Message sent OK.");   
	    }   
	    catch (Exception ex)   
	    {   
	      ex.printStackTrace();   
	    }   
	  }   
	  
	  
	    
	   public static void main(String args[]){   
	      try  
	    {   
	      String smtpServer="smtp.yahoo.com.br";   
	      String to="alex_simas@yahoo.com.br";   
	      String from="alex_simas@yahoo.com.br";   
	      String subject="Primeiro envio de email";   
	      String body="Esta é uma mensagem de teste.";   
	  
	      send(smtpServer, to, from, subject, body);   
	    }   
	    catch (Exception ex)   
	    {   
	      System.out.println(" Hello ");   
	    }   
	  
	    System.exit(0);   
	  
	               
	          
	   }    


}