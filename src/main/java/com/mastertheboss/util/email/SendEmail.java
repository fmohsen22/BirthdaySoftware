package com.mastertheboss.util.email;

import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SendEmail {

    public static void send(String user, String pass, String toEmail, String subj, String body) throws InterruptedException {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.office365.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        final String username = user;//
        final String password = pass;
        try {
            Session session = Session.getDefaultInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            // -- Create a new message --
            Message msg = new MimeMessage(session);


            try {
                msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

//            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            // -- Set the FROM and TO fields --
//            msg.setFrom(new InternetAddress("no_reply@example.com",false));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail, false));
            msg.setSubject(subj);
            msg.setText(body);
            msg.setSentDate(new Date());
            Transport.send(msg);
//            MyLogger.InfoLog(SendNoReplyEmail.class, "Email has been successfuly sent.");
            System.out.println("Email has been successfuly sent.");
        } catch (MessagingException e) {
//            MyLogger.ErrorLog(SendNoReplyEmail.class, e.getMessage());
            System.out.println(e.getMessage());
            TimeUnit.MINUTES.sleep(5);
        }
    }

    @Test
    public void test() throws InterruptedException {

        send("m.fakhari@qcentris.com","MOHf66965691!","fmohsen22@gmail.com","test","test");
//

//        // Recipient's email ID needs to be mentioned.
//        String to = "mohsen.fakhari@iaeste.com";
//
//        // Sender's email ID needs to be mentioned
//        String from = "m.fakhari@qcentris.com";
//
//        // Assuming you are sending email from through gmails smtp
//        String host = "smtp.office365.com";
//
//        // Get system properties
//        Properties properties = System.getProperties();
//
//        // Setup mail server
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.port", "465");
//        properties.put("mail.smtp.ssl.enable", "true");
//        properties.put("mail.smtp.auth", "true");
//
//        // Get the Session object.// and pass
//        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//
//            protected PasswordAuthentication getPasswordAuthentication() {
//
//                return new PasswordAuthentication("fm.fakhari@qcentris.com", "MOHf66965691!");
//
//            }
//
//        });
//        //session.setDebug(true);
//        try {
//            // Create a default MimeMessage object.
//            MimeMessage message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: header field of the header.
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//            // Set Subject: header field
//            message.setSubject("This is the Subject Line!");
//
//            Multipart multipart = new MimeMultipart();
//
//            MimeBodyPart attachmentPart = new MimeBodyPart();
//
//            MimeBodyPart textPart = new MimeBodyPart();
//
//            try {
//
//                File f =new File("C:\\Users\\MohsenFakhari\\OneDrive - QCENTRIS\\Bilder\\t1.jpeg");
//
//                attachmentPart.attachFile(f);
//                textPart.setText("This is text");
//                multipart.addBodyPart(textPart);
//                multipart.addBodyPart(attachmentPart);
//
//            } catch (IOException e) {
//
//                e.printStackTrace();
//
//            }
//
//            message.setContent(multipart);
//
//            System.out.println("sending...");
//            // Send message
//            Transport.send(message);
//            System.out.println("Sent message successfully....");
//        } catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
//
//
    }

    @Test
    public void test2() throws MessagingException {
        System.out.println("SimpleEmail Start");
        String smtpHostServer = "smtp.gmail.com";
        String toEmail = "fmohsen22@gmail.com";
        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtpHostServer);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.port", "25");
        Session session = Session.getDefaultInstance(props);
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress("n91eply@gmail.com", "NoReply-JD"));
            msg.setReplyTo(InternetAddress.parse("n91eply@gmail.com", false));
            msg.setSubject("SimpleEmail Testing Subject", "UTF-8");
            msg.setText("SimpleEmail Testing Body", "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);
            System.out.println("EMail Sent Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

