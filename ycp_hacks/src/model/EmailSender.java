package model;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
	private ArrayList<User> accountsReceiving = new ArrayList<User>();	
	private ArrayList<String> emailsReceiving = new ArrayList<String>();
	private String from;
	private String passwordFrom;
	private Properties prop;
	Session session;
	
	/*
	 * Builds accountsReceiving/emailsReceiving lists
	 * 		-does this in case emailSender object needs to send to multiple recips
	 * Builds account that the email is being sent from
	 * and builds the host, which is gmail
	 */
	public EmailSender(ArrayList<User> accountsReceiving) {
		for(User user : accountsReceiving) {
			this.accountsReceiving.add(user);
			this.emailsReceiving.add(user.getEmail());
		}
		from = "walrussuit@gmail.com";
		//ask tim if you don't know the password
		passwordFrom = "11041998";
		prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
	}
	
	/*
	 * Only sends to one account
	 */
	public void sendRegEmail() {
		session = Session.getInstance(prop,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, passwordFrom);
                }
            });
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailsReceiving.get(0)));
			message.setSubject(accountsReceiving.get(0).getFirstName() + ", welcome to YCP Hacks!");
			message.setText("Your account under: " + emailsReceiving.get(0) + ", is registered for YCP Hacks!");
			Transport.send(message);
		}catch(MessagingException e) {
			System.out.println(e);
		}	
	}
	
	
	//TODO: add notification email methods, that send out to multiple emails,
	//		could potentially send emails to whole database
	//		if this is case use the lists that are built in the constructor
	
}
