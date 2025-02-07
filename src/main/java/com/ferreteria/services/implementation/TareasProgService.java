package com.ferreteria.services.implementation;

import com.ferreteria.entities.OrderEntity;
import com.ferreteria.entities.ProductEntity;
import com.ferreteria.repositories.IOrderRepository;
import com.ferreteria.repositories.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TareasProgService {

    private final EmailService emailService;
    private final IOrderRepository orderRepository;
    private final IProductRepository productRepository;

    @Scheduled(cron = "0 */3 * * * *", zone = "America/Panama")
    public void sendEmailWithTemplate() {
        ZoneId panamaZone = ZoneId.of("America/Panama");
        LocalDate today = LocalDate.now(panamaZone);
        LocalDateTime startDate = today.atStartOfDay(panamaZone).toLocalDateTime();
        LocalDateTime endDate = today.plusDays(1).atStartOfDay(panamaZone).toLocalDateTime();

        List<OrderEntity> orders = orderRepository.findByDateBetween(startDate, endDate);
        List<ProductEntity> products = productRepository.findByStockLessThanEqual(10);

        if (!orders.isEmpty()) {
            emailService.sendEmailWithTemplate("eduardoe.reyna@itse.ac.pa", orders, products);
        }
    }
}
