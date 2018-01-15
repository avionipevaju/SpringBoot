package org.nantic.ole.impl;

import org.nantic.entites.User;
import org.nantic.ole.IUserListener;
import org.nantic.ole.UserEvent;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUserListener implements IUserListener {

	@Override
	public void performedUserEvent(UserEvent userEvent) {
		User user = userEvent.getUsers();
		
		String to = user.getEmail();
		String message = String.format("Thanks for registering at our web shop " + user.getUsername() + "!");
		final String userr = "nantic14@raf.rs";
		final String pass = "Kingcrimson1995@";
	
		System.out.println("Send Mail To: " + to);
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		//props.put("mail.debug", "true");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userr, pass);
		}
	});

	try {
		// Create a default MimeMessage object.
		MimeMessage mm = new MimeMessage(session);

		// Set From: header field of the header.
		mm.setFrom(new InternetAddress(userr));

		// Set To: header field of the header.
		mm.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		// Set Subject: header field
		mm.setSubject("WebShop");

		// Now set the actual message
		mm.setText(message);

		// Send message
		Transport.send(mm);
		System.out.println("Sent message successfully....");
	} catch (MessagingException mex) {
		System.out.println(mex);
	}
		
		
	}

}
