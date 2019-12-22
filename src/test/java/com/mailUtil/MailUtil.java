/**
 * MailUtil.java
 * This class reads/writes the emails
 * both on local win machine as well as on new CI Server
 * */
package com.mailUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.customReporting.ReportingHistoryHTML;
import com.seleniumExceptionHandling.CustomExceptionHandler;
import com.xlUtil.DataTable;

public class MailUtil {

	// Name of Test Data Sheet used for storing the email credentials, to and
	// from emails
	private static final String EMAIL_CRED_SHEET = "emailCred";
	// Constants for reading values from Excel File
	private static final int CRED_ROW_NUM = 1;
	private static final String USERNAME_COL = "username";
	private static final String PASSWORD_COL = "password";
	private static final String EMAIL_TO_COL = "emailTo";

	private static final String READ_EMAIL_FOLDER = "Spam";
	public static final String SUBJECT = "subject";
	public static final String FROM = "from";
	public static final String BODY = "body";

	/**
	 * Returns the platform specific host name, for windows it is
	 * smtp.office365.com. and for linux it is 10.184.40.100 as per SR110623520
	 * 
	 * @return host name string
	 * @author shailendra.rajawat 03-May-2019
	 */
	private static String getHost() {
		String host = "imap.gmail.com";
		String platform = Util.getOSName();
		if (!platform.toLowerCase().contains("win")) {
			host = "imap.gmail.com";
		}
		return host;
	}

	/**
	 * Returns the platform specific port number string used for Sending Mail
	 * Notifications, for windows it is 587 and for linux it is 25 as per
	 * SR110623520
	 * 
	 * @return port number string
	 * @author shailendra.rajawat 03-May-2019
	 */
	private static String getPort_Send() {
		String host = "587";
		String platform = Util.getOSName();
		if (!platform.toLowerCase().contains("win")) {
			host = "587";
		}
		return host;
	}

	/**
	 * Returns the platform specific port number string used for Reading Mails, for
	 * windows it is 995 and for linux it is NOT_SET
	 * 
	 * @return port number string
	 * @author shailendra.rajawat 03-May-2019
	 */
	/*
	 * private static String getPort_Read(){ String host = "995"; String platform =
	 * Util.getOSName(); if(!platform.toLowerCase().contains("win")){ host = "995";
	 * } return host; }
	 */

	/**
	 * This method checks the passed desc and returns appropriate row num based on
	 * this row number the to address of sending mail will be decided for example,
	 * why to send Mails related to Qlik application to IOTRON Team and IOTRON
	 * related mails to Qlik team?
	 * 
	 * @author shailendra.rajawat 03-May-2019
	 */
	private static int getAppropriateRow(String desc) {
		int rowNum = 1;
		if (desc.toLowerCase().contains("qlik")) {
			rowNum = 2;
		}
		return rowNum;
	}

	public static void main(String[] args) {
		// sendNotificationMail("TEST Suite[Qlik Data Download]
		// Test[Qlik_OR_ALL_APPS] Env[PROD] Mon May 20 12:58:43 IST 2019");
		/*
		 * Map<String, String> abcdMap = readMail_SpamFolder("Auto1569913827060");
		 * 
		 * String body = abcdMap.get(BODY);
		 */

		String body = "<table style=\"max-width:40%; min-width:280px; margin: 0px auto; border: 1px solid #cdcdcd; border-collapse:collapse; font-family:sans-serif;\" cellpadding=\"0\" cellspaceing=\"0\">"
				+

				"<tr><td style=\"padding:15px 30px 0px; font-size:20px; font-weight:600; color:#443d3d;\">Hi Automation_Admin Last,</td></tr>"
				+ "<tr>"
				+ "<tr><td style=\"padding:15px 30px 0px; font-size:20px; font-weight:600; color:#443d3d;\">Welcome to HCM System </td></tr>"
				+ "<tr>"
				+ "<tr><td style=\"padding:15px 30px 0px; font-size:20px; font-weight:600; color:#443d3d;\">Username: Auto1569913827060</td></tr>"
				+ "<tr>"
				+ "<tr><td style=\"padding:15px 30px 0px; font-size:20px; font-weight:600; color:#007dbc !important\"><a href=\"https://admin.dev.healthcaremgr.net/GeneratePassword/NC8zRFBHRzZFWVZTNTk3VEo0eUVBZz09/YVEydlUyR1kwc2VqTnlSVkEwa3AyUT09/TDVpNGVRaVRZTEU3amtGZmJlekpYZz09\" style=\"color:#007dbc !important\">Click here to reset Password</a></td></tr>"
				+ "<tr>" +

				"<tr>" + "<td width=\"100%\" style=\"margin-bottom: 30px; float: left; width: 100%;\">" + "</td>"
				+ "</tr>" + "</table>" +

				"<table style=\"width: 41%; margin: 0px auto; padding: 20px;\">"
				+ "<tr style=\"margin-bottom: 20px; font-size: 12px; float: left; width: 100%;\">"
				+ "<td style=\"line-height: 20px; width: 100%; text-align: center; display: block; color: #909090;\">&#169;2018 All Rights Reserved <b>HCM</b></td>";

		// substring method
		// indexOf

		System.out.println(body);

		int startIndex = body.indexOf("https://");
		int endIndex = body.indexOf("\"", startIndex);
		System.out.println(startIndex);
		System.out.println(endIndex);

		String url = body.substring(startIndex, endIndex);
		System.out.println(url);

	}

	/**
	 * This method sends a notification mail with attached html reports It also set
	 * proper subject as per passed description
	 * 
	 * @param desc The description about Suite, Test and Env added to the mail
	 *             subject+body
	 * @return port number string
	 * @author shailendra.rajawat 03-May-2019
	 */
	public static void sendNotificationMail(String desc) {

		System.out.println("===============================================================================");
		System.out.println("Mail STARTED " + new Date());
		if (!Constant.enableMailNotification) {
			System.out.println("Mail feature is STOPPED " + new Date());
			System.out.println("===============================================================================");
			return;
		}
		DataTable data = new DataTable(Constant.getTestDataFilePath(), EMAIL_CRED_SHEET);

		final String username = data.getValue(CRED_ROW_NUM, USERNAME_COL);
		final String password = data.getValue(CRED_ROW_NUM, PASSWORD_COL);

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", getHost()); // 10.184.40.100, port: 25;
		props.put("mail.smtp.port", getPort_Send()); // smtp.office365.com 587

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(data.getValue(getAppropriateRow(desc), EMAIL_TO_COL)));
			message.setSubject("Notification of Automated test execution on " + Util.getOSName() + " : " + desc);

			// Attaching the html file
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(getMailBody(desc), "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// In case the files size is very large then we will just send the
			// notification mail w/o attaching the reports
			try {

				// In case the HTML report file is not found
				try {
					messageBodyPart = new MimeBodyPart();
					String filename = Constant.getResultHtmlFilePath();
					DataSource source = new FileDataSource(filename);
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(Constant.reportRedesignTemplateName);
					multipart.addBodyPart(messageBodyPart);
				} catch (Exception ee) {
					ee.printStackTrace();
				}

				// In case the HTML report file is not found
				try {
					BodyPart messageBodyPart1 = new MimeBodyPart();
					String filename1 = Constant.getResultextenthtmlfilePath();
					DataSource source1 = new FileDataSource(filename1);
					messageBodyPart1.setDataHandler(new DataHandler(source1));
					messageBodyPart1.setFileName(Constant.resultExtentHTMLFileName);
					multipart.addBodyPart(messageBodyPart1);
				} catch (Exception ee) {
					ee.printStackTrace();
				}

				// Send the complete message parts
				message.setContent(multipart);
				Transport.send(message);
				System.out.println("Mail Sent, With Attachments");
			} catch (Exception ee) {
				Message messageWoAttchment = new MimeMessage(session);
				messageWoAttchment.setFrom(new InternetAddress(username));

				messageWoAttchment.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(data.getValue(getAppropriateRow(desc), EMAIL_TO_COL)));
				messageWoAttchment
						.setSubject("Notification of Automated test execution on " + Util.getOSName() + " : " + desc);

				// Attaching the html file
				BodyPart messageBodyPartWoAttachment = new MimeBodyPart();
				messageBodyPartWoAttachment.setContent(getMailBody(desc), "text/html");

				Multipart multipartWoAttachment = new MimeMultipart();
				multipartWoAttachment.addBodyPart(messageBodyPartWoAttachment);

				// Send the complete message parts
				messageWoAttchment.setContent(multipartWoAttachment);
				Transport.send(messageWoAttchment);
				System.out.println("Mail Sent, W/O Attachments");
			}

		} catch (MessagingException e) {
			if (e.toString().contains("AuthenticationFailed")) {
				System.err.println(
						"FAILED TO SEND MAIL : Authentication Failed for the username and password mentioned in Sheet ["
								+ EMAIL_CRED_SHEET + "] " + Constant.getTestDataFilePath());
			} else {
				e.printStackTrace();
			}

		} finally {
			System.out.println("Mail ENDED " + new Date());
			System.out.println("===============================================================================");
		}

	}

	private static Object getMailBody(String desc) {
		return "Hi All," + "<br/><br/>" + "This is the System generated mail for Automation Testing." + "<h3>" + desc
				+ "</h3>" + "<h4>Quick Reference: </h4>"
				+ ReportingHistoryHTML.getDashboardContent("", Constant.getResultHtmlFilePath()) + "<br/> "
				+ "<h4>Scenarios: </h4>"
				+ ReportingHistoryHTML.getQuickViewContent("", Constant.getResultHtmlFilePath())

				+ "<h4>Please find the attached HTML report</h4>"
				+ "<b>Note</b> : <i>If attachments are not found, then the files might be too large to attach in this mail, please contact Automation team for detailed report or you can follow below steps. </i>"
				+ "<h4>Steps to find HTML reports on CI server(Linux only):</h4>" + "<ol>" + "<li>"
				+ "Press Win + R on your windows machine" + "</li>" + "<li>" + "Type mstsc & Click Ok" + "</li>"
				+ "<li>" + "Provide ip of new RD Web(10.182.37.216), & click Connect" + "</li>" + "<li>"
				+ "Enter your office365 credentials, & click Ok" + "</li>" + "<li>"
				+ "Open Chrome on the newly opened Remote desktop" + "</li>" + "<li>"
				+ "Fire Jenkins(http://10.184.40.101:8080) url" + "</li>" + "<li>"
				+ "Login with your Jenkins credentials(created by Automation Team)" + "</li>" + "<li>"
				+ "Click on the Job Name link(ex: Qlik_Data_Verification)" + "</li>" + "<li>"
				+ "Click on Workspace link" + "</li>" + "<li>" + "Click on IOTRON-AutomationFrameworkApex5 link"
				+ "</li>" + "<li>" + "Click on ReportingHistory link" + "</li>" + "<li>" + "Click on HTML link"
				+ "</li>" + "<li>" + "Click on page1 link <b><i>Bookmark this page for future references</i></b>"
				+ "</li>" + "</ol>" + "<br/>" + "Thanks" + "<h3>" + "-Shailendra" + "</h3>";
	}

	/**
	 * Reading the mail
	 * 
	 * @author Shailendra Oct 1, 2019
	 */
	public static Map<String, String> readMail_SpamFolder(String... mailBodyContains) {
		return readMail_SpamFolder(Constant.wait, mailBodyContains);
	}

	/**
	 * Reading the mail
	 * 
	 * @author Adarsh 28-Nov-2019
	 */
	public static Map<String, String> readMail_SpamFolder(long timeOutInSec, String... mailBodyContains) {
		Map<String, String> foundMessage = null;
		try {

			DataTable data = new DataTable(Constant.getTestDataFilePath(), EMAIL_CRED_SHEET);

			String username = data.getValue(CRED_ROW_NUM, USERNAME_COL);
			String password = data.getValue(CRED_ROW_NUM, PASSWORD_COL);

			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect(getHost(), username, password);

			Folder folder = null;
			Folder[] f = store.getDefaultFolder().list("*");
			for (Folder fd : f) {
				// System.out.println(">> "+fd.getName());
				if (fd.getFullName().contains(READ_EMAIL_FOLDER)) {
					folder = fd;
					break;
				}
			}

			folder.open(Folder.READ_ONLY);

			// Adding a code to loop until either mail is found or timeout

			long timeOutTimeCounter = timeOutInSec;
			boolean timeOut = true;
			boolean mailFound = true;
			do {

				// retrieve the messages from the folder in an array and print it
				Message[] messages = folder.getMessages();
				// System.out.println("messages.length---" + messages1.length);
				// int counter = 1;
				for (int i = messages.length - 1; i >= (messages.length - 10); i--) {
					Message message = messages[i];
					// System.out.println("---------------------------------");
					// System.out.println("Email Number " + (counter++));
					// System.out.println("Subject: " + message.getSubject());
					// System.out.println("From: " + message.getFrom()[0]);

					String body = null;

					Object content = message.getContent();
					if (content instanceof Multipart) {
						Multipart mp = (Multipart) content;
						for (int j = 0; j < mp.getCount(); j++) {
							BodyPart bp = mp.getBodyPart(j);
							if (Pattern.compile(Pattern.quote("text/html"), Pattern.CASE_INSENSITIVE)
									.matcher(bp.getContentType()).find()) {
								// found html part
								body = (String) bp.getContent();
							} else {
								// some other bodypart...
							}
						}
					}

					// System.out.println("Text: " + bp.getContent());

					boolean flag[] = new boolean[mailBodyContains.length];
					boolean combinedFlags = true;

					for (int j = 0; j < mailBodyContains.length; j++) {
						if (body.toLowerCase().contains(mailBodyContains[j].toLowerCase())) {
							flag[j] = true;
						} else {
							flag[j] = false;
						}
					}

					for (boolean b : flag) {
						if (!b) {
							combinedFlags = false;
							break;
						}
					}

					if (combinedFlags) {
						mailFound = false;
						foundMessage = new HashMap<>();
						foundMessage.put(SUBJECT, message.getSubject());
						foundMessage.put(FROM, message.getFrom()[0].toString());
						foundMessage.put(BODY, body);
						break;
					}

				}

				timeOutTimeCounter--;
				if (timeOutTimeCounter > 0) {
					Thread.sleep(1000);
				} else {
					timeOut = false;
					CustomReporter.report(STATUS.FAIL, "TIMEOUT MAIL [" + mailBodyContains
							+ "] NOT FOUND AFTER WAITING FOR '" + timeOutInSec + "' SECONDS");
				}
				
			} while (mailFound && timeOut);

			store.close();

		} catch (NoSuchProviderException e) {
			new CustomExceptionHandler(e);
		} catch (MessagingException e) {
			new CustomExceptionHandler(e);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}

		if (foundMessage != null) {
			CustomReporter.report(STATUS.PASS, "Mail " + foundMessage + " is received");
		} else {
			CustomReporter.report(STATUS.FAIL, "Mail " + mailBodyContains + " is NOT received");
		}

		return foundMessage;
	}

}
