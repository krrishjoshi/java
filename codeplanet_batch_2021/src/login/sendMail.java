package login;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class sendMail {

	public void send( String to, String subject, String body) {
		
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host","smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.auth", "true");
		String from = "krrishjoshijoshi@gmail.com";
		Session session = Session.getInstance(prop, new SimpleAuthenticator(from,"vanukrrish"));
		session.setDebug(true);
		System.out.println(session.getDebugOut());
		try {
			MimeMessage message = new MimeMessage(session);
			message.setSubject(subject);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setText(body);
			Transport.send(message);
			System.out.println("krrishjohsi");
			
		}catch (Exception e) {
			
		}
	}
}
class SimpleAuthenticator  extends Authenticator{
	String username = null;
	String password = null;
	public SimpleAuthenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username,password);
	}
}