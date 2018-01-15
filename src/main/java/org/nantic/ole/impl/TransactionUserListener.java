package org.nantic.ole.impl;

import org.nantic.entites.Transaction;
import org.nantic.entites.User;
import org.nantic.ole.ITransactionListener;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;

public class TransactionUserListener implements ITransactionListener {

	@Override
	public void performedTransactionEvent(ArrayList<Transaction> transactions) {
		User user = transactions.get(0).getUser();
		String message = String.format("Thanks for shopping with us " + user.getUsername() + "! Your reciept: \n");
		int count = 1;
		int total = 0;
		for (Transaction transaction : transactions) {
			String temp ="" + count + ". Product: " + transaction.getProductBean().getName() + " Price: " +
			transaction.getProductBean().getPrice() + " Quantity: " +transaction.getQuantity() +  " Total: " + transaction.getTotal() + "\n";
			message += temp;
			count++;
			total += transaction.getTotal();
		}
		
		message += "Total reciept amount: " + total;
		String to = user.getEmail();
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
