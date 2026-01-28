package com.sorokaandriy.notification_service.service;

import com.sorokaandriy.notification_service.dto.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.sorokaandriy.notification_service.model.EmailTemplates.ORDER_CONFIRMATION;
import static com.sorokaandriy.notification_service.model.EmailTemplates.PAYMENT_CONFIRMATION;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper =
                new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
        mimeMessageHelper.setFrom("sorokaandriy1124@gmail.com");
        final String templateName = PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>(); // fields message
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variables); // set messages
        mimeMessageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject()); // set title

        try {
            String htmlTemplate = templateEngine.process(templateName,context); // unites template with messages
            mimeMessageHelper.setText(htmlTemplate, true); // its html

            mimeMessageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("INFO - Email successfully sent to {} with template {}",destinationEmail,templateName);
        } catch (MessagingException e){
            log.warn("WARNING - Cannot send email to {}", destinationEmail);
        }



    }


    @Async
    public void sendOrderSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal totalAmount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper =
                new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
        mimeMessageHelper.setFrom("sorokaandriy1124@gmail.com");
        final String templateName = ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>(); // fields message
        variables.put("customerName", customerName);
        variables.put("totalAmount", totalAmount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);

        Context context = new Context();
        context.setVariables(variables); // set messages
        mimeMessageHelper.setSubject(ORDER_CONFIRMATION.getSubject()); // set title

        try {
            String htmlTemplate = templateEngine.process(templateName,context); // unites template with messages
            mimeMessageHelper.setText(htmlTemplate, true); // set true to html

            mimeMessageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("INFO - Email successfully sent to {} with template {}",destinationEmail,templateName);
        } catch (MessagingException e){
            log.warn("WARNING - Cannot send email to {}", destinationEmail);
        }



    }



}
