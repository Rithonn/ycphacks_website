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
	
	/**
	 * Builds accountsReceiving/emailsReceiving lists
	 * 		-does this in case emailSender object needs to send to multiple recips
	 * Builds account that the email is being sent from
	 * and builds the host, which is gmail
	 */
	public EmailSender(ArrayList<User> accountsReceiving) {
		this.accountsReceiving = accountsReceiving;
		for(User user : accountsReceiving) {
			this.emailsReceiving.add(user.getEmail());
		}
		from = "walrussuit@gmail.com";
		//ask tim if you don't know the password
		passwordFrom = "";
		prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
	}
	
	/**
	 * Used for sending a confirmation email to a
	 * single user that just registered. Will only access element 0 
	 * of the arrayList of users provided in the constructor
	 */
	public void sendAccCreationEmail() {
		session = Session.getInstance(prop,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, passwordFrom);
                }
            });
		
		try {
			MimeMessage message_accMade = new MimeMessage(session);
			message_accMade.setFrom(new InternetAddress(from));
			message_accMade.addRecipient(Message.RecipientType.TO, new InternetAddress(emailsReceiving.get(0)));
			message_accMade.setSubject(accountsReceiving.get(0).getFirstName() + ", welcome to YCP Hacks!");
			message_accMade.setText("Your account under: " + emailsReceiving.get(0) + ", is registered for YCP Hacks!");
			Transport.send(message_accMade);
		}catch(MessagingException e) {
			System.out.println(e);
		}	
	}
	
	/**Method to send a mass email to multiple users.
	 * Constructor handles who it gets sent to, as the constructor
	 * gets passed an arrayList of users
	 * 
	 * @param subject String to display in the subject field of the email
	 * @param message String to display as the message of the email
	 */
	public void sendMassEmail(String subject, String message) {
		session = Session.getInstance(prop,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(from, passwordFrom);
	                }
	            });
			
			try {
				MimeMessage message_massEmail = new MimeMessage(session);
				message_massEmail.setFrom(new InternetAddress(from));
				for(String email : emailsReceiving) {
					message_massEmail.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
				}
				message_massEmail.setSubject(subject);
				message_massEmail.setText(message);
				Transport.send(message_massEmail);
			}catch(MessagingException e) {
				System.out.println(e);
			}	
	}
}
