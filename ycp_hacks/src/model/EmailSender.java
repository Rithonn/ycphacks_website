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
	 * Builds account that the email is being sent from
	 * and builds the host, which is gmail
	 */
	public EmailSender() {
		from = "walrussuit@gmail.com";
		//ask tim if you don't know the password
		passwordFrom = "11041998";
		prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
	}
	
	/**Returns accountsReceiving arrayList.
	 * For testing use
	 *@return Returns ArrayList of User objects
	 */
	public ArrayList<User> getAccountsReceiving() {
		return accountsReceiving;
	}
	
	/**Returns emailsReceiving arryList.
	 * For testing use
	 * @return Returns ArrayList of String Emails
	 */
	public ArrayList<String> getEmailsReceiving(){
		return emailsReceiving;
	}
	
	/**Builds email list from an arraylist of user type
	 * 
	 * 
	 * @param accountsReceiving An ArrayList of User objects
	 */
	public void loadEmailsFromUserList(ArrayList<User> accountsReceiving) {
		this.accountsReceiving = accountsReceiving;
		for(User user : accountsReceiving) {
			this.emailsReceiving.add(user.getEmail());
		}
	}
	
	/**Builds email list from an arraylist of string type.
	 * Used if you just have the emails, and not a collections
	 * of Users.
	 * 
	 * @param emailsReceiving An ArrayList of emails
	 */
	public void loadEmailsFromEmailList(ArrayList<String> emailsReceiving) {
		this.emailsReceiving = emailsReceiving;
	}
	
	/**
	 * Used for sending a confirmation email to a
	 * single user that just made an account. Will only access element 0 
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
			message_accMade.setText("Your account under: " + emailsReceiving.get(0) + ", has been made! Expect an email from us when your account is registered for the event.");
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
	
	/**Method to send an email to a single user with their new password
	 * 
	 * @param newpw New password that will be included in the email
	 */
	public void sendForgotPasswordEmail(String newpw) {
		session = Session.getInstance(prop,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(from, passwordFrom);
	                }
	            });
			
			try {
				MimeMessage message_forgotPW = new MimeMessage(session);
				message_forgotPW.setFrom(new InternetAddress(from));
				message_forgotPW.addRecipient(Message.RecipientType.TO, new InternetAddress(emailsReceiving.get(0)));
				message_forgotPW.setSubject("***Password Reset!***");
				message_forgotPW.setText("Your account under: " + emailsReceiving.get(0) + " changed it's password to: " + newpw
						+ ", log in with this new password and change your password to whatever you would like.");
				Transport.send(message_forgotPW);
			}catch(MessagingException e) {
				System.out.println(e);
			}	
	}
	
	/**
	 * Used for sending a confirmation email to a
	 * single user that their account was registered. Will only access element 0 
	 * of the arrayList of users provided in the constructor.
	 */
	public void sendRegisteredEmail() {
		session = Session.getInstance(prop,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(from, passwordFrom);
	                }
	            });
			
			try {
				MimeMessage message_accRegistered = new MimeMessage(session);
				message_accRegistered.setFrom(new InternetAddress(from));
				message_accRegistered.addRecipient(Message.RecipientType.TO, new InternetAddress(emailsReceiving.get(0)));
				message_accRegistered.setSubject(accountsReceiving.get(0).getFirstName() + ", your account is registered!");
				message_accRegistered.setText("Your account under: " + emailsReceiving.get(0) + ", is registered! We look forward to seeing you at the event.");
				Transport.send(message_accRegistered);
			}catch(MessagingException e) {
				System.out.println(e);
			}	
	}
}
