package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Environment env;

    @Value("${alkemy.email.sender}")
    private String emailSender;
    @Value("${alkemy.email.enabled}")
    private boolean enabled;

    public void sendWelcomeEmailTo(String email) {
        if(!enabled) {
            return;
        }
        //String apiKey = env.getProperty("EMAIL_API_KEY");
        String apiKey = "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";
        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(email);
        Content content = new Content("text/plain","Welcome " + email + " to the Disney Alkemy application! Enjoy it!");
        String subject = "Welcome to Disney Alkemy";

        Mail mail= new Mail(fromEmail, subject, toEmail, content);
        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            new IOException(ex.getMessage());
        }
    }
}


