package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmailSender;
import model.User;
import persist.DatabaseProvider;
import persist.DerbyDatabase;
import persist.IDatabase;

public class AdminEmailServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
	Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);	
	HttpSession session = null;
	IDatabase db = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Admin Email Page Servlet: doGet");
		session = req.getSession();
		
		/*If session DB reference is null.
		 * create new DB (fake for now)
		 * set session DB reference to new db
		 */
		
		User currentUser = (User) session.getAttribute("currentUser");
		if(session.getAttribute("currentUser") == null) {
			resp.sendRedirect(req.getContextPath() + "/home");
		}else if(currentUser.getAccessID() != 2){
			resp.sendRedirect(req.getContextPath() + "/home");
		}
		
		
		if(DatabaseProvider.getInstance() == null) {
			DatabaseProvider.setInstance(new DerbyDatabase());//------------------------------------------DATABASE MARKER
			db = DatabaseProvider.getInstance();
		}else {
			db = DatabaseProvider.getInstance();
		}
		
		req.getRequestDispatcher("/_view/adminEmail.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Admin Email Page Servlet: doPost");
		
		
		
		if(req.getParameter("allUsersButton") == null && req.getParameter("specificUsersButton") == null) {
			req.setAttribute("error", "Please check one of the boxes below");
			req.setAttribute("message", req.getParameter("message"));
			req.setAttribute("subject", req.getParameter("subject"));
			req.getRequestDispatcher("/_view/adminEmail.jsp").forward(req, resp);
		}
		
		if(req.getParameter("subject").isEmpty() || req.getParameter("message").isEmpty()) {
			req.setAttribute("error", "Please fill out the subject/message fields");
			req.getRequestDispatcher("/_view/adminEmail.jsp").forward(req, resp);
		}
		
		
		/*If email is being sent to all users in database,
		 * call database query, build emailSender object,
		 * and call emailSender method
		 */
		if(req.getParameter("allUsersButton") != null) {
			List<User> allUsers_list = db.getAllUsers();
			ArrayList<User> allUsers_arrayList = new ArrayList<User>();
			allUsers_arrayList.addAll(allUsers_list);
			
			//remove admin@ycp.edu as it is an actual email
			allUsers_arrayList.remove(2);
			
			EmailSender emailSender = new EmailSender();
			emailSender.loadEmailsFromUserList(allUsers_arrayList);
			emailSender.sendMassEmail(req.getParameter("subject"), req.getParameter("message"));
			
		/*If being sent to specific users,
		 * obtain users from jsp,
		 * build email list from comma seperated string,
		 * build emailSender object,
		 * call emailSender method
		 */
		}else if(req.getParameter("specificUsersButton") != null){
			
			if(req.getParameter("emails").isEmpty()) {
				req.setAttribute("error", "Please provide emails to send to");
				req.getRequestDispatcher("/_view/adminEmail.jsp").forward(req, resp);
			}
			
			String rawEmails = req.getParameter("emails");
			ArrayList<String> emails_final = new ArrayList<String>();
			
			//builds an email string char by char until it hits a ','
			//then adds the email string to the arraylist, and resets the email string
			String email = "";
			for(int i = 0; i < rawEmails.length(); i++) {
				char c = rawEmails.charAt(i);
				if((c != ',') && (i != rawEmails.length() - 1)) {
					email = email + c;
				}else if(c == ','){
					emails_final.add(email);
					email = "";
				}else if(i == rawEmails.length() - 1) {
					email = email + c;
					emails_final.add(email);
					email = "";
				}
			}
		
			//finally validates emails for correct email form using regex
			for(String email_validate : emails_final) {
				System.out.println(email_validate);
				if(!validate(email_validate)) {
					req.setAttribute("error", email_validate + " is of invalid email form");
					req.setAttribute("message", req.getParameter("message"));
					req.setAttribute("subject", req.getParameter("subject"));
					req.getRequestDispatcher("/_view/adminEmail.jsp").forward(req, resp);
				}
			}
			
			EmailSender emailSender = new EmailSender();
			emailSender.loadEmailsFromEmailList(emails_final);
			emailSender.sendMassEmail(req.getParameter("subject"), req.getParameter("message"));
		
		}
		
		
		req.setAttribute("success", "Email sent successfully!");
		req.getRequestDispatcher("/_view/adminEmail.jsp").forward(req, resp);
	}
	
	//method to validate email with the Pattern at top of file
	public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
	}

}
