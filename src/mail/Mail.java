package mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mail {
    static String FROM = "freeuser123@nate.com";
    static String SENDERNAME = "고급객체9조";

    static String SMTP_USERNAME = "freeuser123"; 
    static String SMTP_PASSWORD = "ksh02121()"; 
    
    static String HOST = "smtp.mail.nate.com";
    static int PORT = 465;
    
    static String TITLE = "날씨정보";
    static String TO = "";
    static String CONTENT = "";
   
    public Mail(String mailaddr, String testStr) throws Exception {
    	TO = mailaddr;
    	CONTENT = testStr;
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT); 
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", HOST);

        Session session = Session.getDefaultInstance(props);
 
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, SENDERNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(TITLE);
        msg.setContent(CONTENT, "text/html;charset=euc-kr");
        
        Transport transport = session.getTransport();
 
        try { 
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients()); 
            System.out.println("전송완료");
        } catch (Exception ex) {
            ex.printStackTrace();
 
        } finally {
            transport.close();
        }
    }


}
