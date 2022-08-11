package com.mastertheboss.util.email;

import org.apache.poi.poifs.nio.DataSource;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class GmailTextMsgSendWithSSL {

    Properties properties;
    Session session;
    MimeMessage mimeMessage;

    String USERNAME = "mohsen.fakhari@iaeste.at";
    String PASSWORD = "srouvkjvgdgybilq";
    String HOSTNAME = "smtp.gmail.com";
    String SSL_PORT = "465";
    boolean AUTH = true;
    String SERVERTYPE = "smtp";

    public static void main(String args[]) throws MessagingException {
        String EmailSubject = "Subject:Text Subject";
        String EmailBody = "Text Message Body: Hello World test";
        String ToAddress = "m.fakhari@qcentris.com";

        GmailTextMsgSendWithSSL gmailTextMsgSend = new GmailTextMsgSendWithSSL();
        gmailTextMsgSend.sendGmail(EmailSubject, EmailBody, ToAddress);
    }

    public void sendGmail(String EmailSubject, String EmailBody, String ToAddress) {
        try {
            properties = new Properties();
            properties.put("mail.smtp.host", HOSTNAME);

            // SSL_PORT Enabled
            properties.put("mail.smtp.port", SSL_PORT);

            // AUTH Enabled
            properties.put("mail.smtp.auth", AUTH);

            // SSL Enabled
            properties.put("mail.smtp.socketFactory.port", SSL_PORT);
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            // Authenticating
            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USERNAME, PASSWORD);
                }
            };

            // creating session
            session = Session.getDefaultInstance(properties, auth);

            // create mimemessage
            mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(ToAddress));
            mimeMessage.setFrom(new InternetAddress("no-reply@abc.com", "WebMaster"));
            mimeMessage.setSubject(EmailSubject);

            //image part
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart textPart = new MimeBodyPart();
            String htmlText ="<img src=\"cid:image\"> " +
                    "<html><head><style>h1 {background-color: #FFF100;padding: 15px; text-indent: 40px;} " ;
            textPart.setContent(htmlText, "text/html");

            multipart.addBodyPart(textPart);
            BodyPart imagePart = new MimeBodyPart();
            FileDataSource fds = new FileDataSource
                    ("C:\\Users\\MohsenFakhari\\OneDrive - QCENTRIS\\Bilder\\birthday.jpg");
            mimeMessage.setDataHandler(new DataHandler(fds));
            mimeMessage.setHeader("Content-ID","<image>");
            mimeMessage.setDisposition(MimeBodyPart.INLINE);

            multipart.addBodyPart(imagePart);
            mimeMessage.setContent(multipart);

            // setting text message body
            mimeMessage.setText(EmailBody);

            // sending mail
            Transport.send(mimeMessage);
            System.out.println("Mail Send Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}