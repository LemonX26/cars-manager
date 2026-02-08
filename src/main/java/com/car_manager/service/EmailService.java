package com.car_manager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.car_manager.model.CarSaleAnnouncement;
import jakarta.mail.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String fromAddress = "snajpermati26@gmail.com";
    private final String toAddress = "snajpermati26@gmail.com";

    public void sendJsonAnnouncement(CarSaleAnnouncement announcement) {
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(announcement);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("snajpermati26@gmail.com");
            message.setSubject("Nowe ogłoszenie samochodu");
            message.setText("Treść ogłoszenia:\n\n" + json);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendAnnouncementEmail(CarSaleAnnouncement carSaleAnnouncement){
        //6digits code
        int generatedId = (int) (Math.random() * 1000000);
        //int generatedId = UUID.randomUUID().hashCode();
        String htmlContent = generateHtmlEmail(carSaleAnnouncement, generatedId);

        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            mimeMessage.setRecipients(Message.RecipientType.TO, toAddress);
            mimeMessage.setFrom(fromAddress);
            mimeMessage.setSubject("🚗 Nowe ogłoszenie sprzedaży samochodu");

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setText(htmlContent, true); // true = HTML
        };
        mailSender.send(mimeMessagePreparator);
    }
    private String generateHtmlEmail(CarSaleAnnouncement a, int code) {
        try {
            ClassPathResource resource = new ClassPathResource("templates/emailTemplate.html");
            String template = Files.readString(resource.getFile().toPath(), StandardCharsets.UTF_8);

            return String.format(template,
                    code,
                    a.getCar().getName(),
                    a.getCar().getModel(),
                    a.getCar().getCategory(),
                    a.getCar().getProductionYear(),
                    a.getCar().getMileage(),
                    a.getCar().getOriginCountry(),
                    a.getCar().getStatus(),
                    a.getPrice(),
                    a.getDescription(),
                    a.getLocation(),
                    a.getSellerName(),
                    a.getContactInfo(),
                    a.getImageUrl()
            );

        } catch (IOException e) {
            e.printStackTrace();
            return "<p>Błąd generowania wiadomości</p>";
        }
    }
}