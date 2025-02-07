package com.ferreteria.services.implementation;

import com.ferreteria.entities.OrderEntity;
import com.ferreteria.entities.ProductEntity;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public void sendEmailWithTemplate(String to, List<OrderEntity> orders, List<ProductEntity> products) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();
            context.setVariable("orders", orders);
            context.setVariable("products", products);
            String html = templateEngine.process("email-template", context);

            helper.setTo(to);
            helper.setSubject("Resumen diario de órdenes y productos que necesitan compra");
            helper.setText(html, true);

            mailSender.send(message);
        }catch (Exception e){
            throw new RuntimeException("Error al enviar correo", e);
        }

    }
}
