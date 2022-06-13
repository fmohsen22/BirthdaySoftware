package com.mastertheboss.application;

import com.mastertheboss.domain.Employee;
import com.mastertheboss.framework.Configuration;
import com.mastertheboss.util.email.GmailTextMsgSendWithSSL;
import emailcontent.TextReader;

import java.util.List;

public class EmailSender {
    public static void main(String[] args) {
        List< Employee > employeeBirthdayList = new QueryExecutor().employeesWithBirthday();

        for (Employee em: employeeBirthdayList){

            String EmailSubject = Configuration.of().getPath("subject");
            String EmailBody = TextReader.textReader(em.getGender(),em.getName(),Configuration.of().getPath("sender"));
            String ToAddress = em.getEmail();

            GmailTextMsgSendWithSSL gmailTextMsgSend = new GmailTextMsgSendWithSSL();
            gmailTextMsgSend.sendGmail(EmailSubject, EmailBody, ToAddress);
        }


    }
}
