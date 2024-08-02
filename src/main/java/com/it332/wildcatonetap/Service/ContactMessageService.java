package com.it332.wildcatonetap.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.it332.wildcatonetap.Repository.ContactMessageRepository;
import com.it332.wildcatonetap.Entity.ContactMessageEntity;

@Service
public class ContactMessageService {
    @Autowired
    private ContactMessageRepository contactMessageRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void saveAndSendMessage(ContactMessageEntity message) {
        contactMessageRepository.save(message);
        sendEmail(message);
    }

    private void sendEmail(ContactMessageEntity message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo("wildcatonetap@gmail.com");
        mailMessage.setSubject("New Contact Form Submission");
        mailMessage.setText("Name: " + message.getName() + "\n"
                + "Email: " + message.getEmail() + "\n"
                + "Phone: " + message.getPhoneNumber() + "\n"
                + "Message: " + message.getMessage());
        mailMessage.setReplyTo(message.getEmail());  // Set the Reply-To header
        mailSender.send(mailMessage);
    }
}
