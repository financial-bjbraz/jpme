package br.bmcopias.mail;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 
 * @author Alex Simas Braz
 *
 */
public class SendMail {
	
	public void sendSimpleMail(MailBean mb) throws AddressException, MessagingException {
        Properties mailProps = new Properties();
        mailProps.put("mail.transport.protocol", "smtp");
        mailProps.put("mail.smtp.starttls.enable", "true");
        mailProps.put("mail.smtp.host", "smtp.gmail.com");
        mailProps.put("mail.smtp.auth", "true");
        mailProps.put("mail.smtp.user", "alexjavabraz@gmail.com");
        mailProps.put("mail.smtp.password", "deltasp5k");
        mailProps.put("mail.debug", "true");
        mailProps.put("mail.smtp.port", "465");
        mailProps.put("mail.smtp.debug", "true");
        mailProps.put("mail.mime.charset", "ISO-8859-1");
        mailProps.put("mail.smtp.socketFactory.port", "465");
        mailProps.put("mail.smtp.socketFactory.fallback", "false");
        mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
  
        Authenticator auth = new Autenticacao();
        Session session = Session.getDefaultInstance(mailProps, auth);
  
        Message message = new MimeMessage(session);
  
        message.setFrom(new InternetAddress(mb.getDe()));
//        message.setHeader("Disposition-Notification-To",mb.getDe());
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mb.getDestinatarios()[0]));
  
        message.setSubject(mb.getAssunto());
        Multipart part = new  MimeMultipart();
        
        try{
	        File arquivo = new File("C:\\projeto\\horas.txt");
	        FileOutputStream out = new FileOutputStream(arquivo);
	        part.writeTo(out);
	        message.setContent(part);
	        message.setContent("aaa", "aaaa");
	        message.setFileName("teste.txt");
        }catch(Exception e){}
        
        message.setContent(mb.getConteudo().toString(), "text/html");
  
        Transport.send(message);
    }
  
    private class Autenticacao extends javax.mail.Authenticator {
  
        public PasswordAuthentication getPasswordAuthentication() {
            String user = "alexjavabraz@gmail.com";
            String pwd = "deltasp5k";
            return new PasswordAuthentication(user, pwd);
        }
    }

	public static void main(String[] args) throws Exception {
		MailBean bean = new MailBean();
		bean.setAssunto("teste");
		bean.setConteudo("conteudo");
		bean.setDestinatarios(new String[] { "asimas@f9c.com.br" });
		bean.setDe("alexjavabraz@gmail.com");
		SendMail email = new SendMail();
		email.sendSimpleMail(bean);
	}

}