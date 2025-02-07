package com.ferreteria.services.implementation;

import com.ferreteria.controller.dto.OrderDTO;
import com.ferreteria.entities.OrderDetailsEntity;
import com.ferreteria.entities.OrderEntity;
import com.ferreteria.entities.ProductEntity;
import com.ferreteria.repositories.IOrderRepository;
import com.ferreteria.repositories.IProductRepository;
import com.ferreteria.services.interfaces.IOrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final ModelMapper modelMapper;
    private final IOrderRepository orderRepository;
    private final IProductRepository productRepository;


    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        OrderEntity order = this.modelMapper.map(orderDTO, OrderEntity.class);

        for (OrderDetailsEntity  orderDetails : order.getOrderDetails()) {

            ProductEntity product = this.productRepository.findById(orderDetails.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("El producto no existe"));

            if (product.getStock() < orderDetails.getQuantity()) {
                throw new RuntimeException("No hay stock suficiente para el producto " + product.getId()+" "+ product.getName());
            }

            product.setStock(product.getStock() - orderDetails.getQuantity());
            if (product.getStock() == 0) {
                product.setStatus(false);
            }
            productRepository.save(product);

            orderDetails.setProduct(product);
            orderDetails.setPrice(product.getPrice());
            BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(orderDetails.getQuantity()));

            orderDetails.setSubtotal(subtotal);
            orderDetails.setOrder(order);

        }
        BigDecimal total = order.getOrderDetails()
                .stream()
                .map(OrderDetailsEntity::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotal(total);

        order.setDate(LocalDateTime.now());

        OrderEntity orderSaved = this.orderRepository.save(order);

        return this.modelMapper.map(orderSaved, OrderDTO.class);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return this.orderRepository.findAll().stream().map(order -> this.modelMapper.map(order, OrderDTO.class)).toList();
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        OrderEntity order = this.orderRepository.findById(id).orElseGet(OrderEntity::new);

        return this.modelMapper.map(order, OrderDTO.class);
    }

    @Override
    public List<OrderDTO> getOrdersByEmployee(Long id) {
        return this.orderRepository.findByUserId(id)
                .stream().map(order -> this.modelMapper.map(order, OrderDTO.class)).toList();
    }

    @Override
    public List<OrderDTO> getOrdersByDay() {
        ZoneId panamaZone = ZoneId.of("America/Panama");
        LocalDate today = LocalDate.now(panamaZone);
        LocalDateTime startDate = today.atStartOfDay(panamaZone).toLocalDateTime();
        LocalDateTime endDate = today.plusDays(1).atStartOfDay(panamaZone).toLocalDateTime();

        return this.orderRepository.findByDateBetween(startDate, endDate)
                .stream().map(order -> this.modelMapper.map(order, OrderDTO.class)).toList();
    }

    @Override
    public List<OrderDTO> getOrdersByMonth() {
        ZoneId panamaZone = ZoneId.of("America/Panama");
        LocalDate today = LocalDate.now(panamaZone);
        YearMonth yearMonth = YearMonth.from(today);

        LocalDate startMonth = yearMonth.atDay(1);
        LocalDate endMonth = yearMonth.atEndOfMonth();

        LocalDateTime startDate = startMonth.atStartOfDay(panamaZone).toLocalDateTime();
        LocalDateTime endDate = endMonth.atStartOfDay(panamaZone).toLocalDateTime();

        return this.orderRepository.findByDateBetween(startDate, endDate)
                .stream().map(order -> this.modelMapper.map(order, OrderDTO.class)).toList();
    }


}
