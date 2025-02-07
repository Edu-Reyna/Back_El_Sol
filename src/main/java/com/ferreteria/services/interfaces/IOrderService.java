package com.ferreteria.services.interfaces;

import com.ferreteria.controller.dto.OrderDTO;

import java.util.List;

public interface IOrderService {

    OrderDTO createOrder(OrderDTO orderDTO);

    List<OrderDTO> getAllOrders();

    OrderDTO getOrderById(Long id);

    List<OrderDTO> getOrdersByEmployee(Long id);

    List<OrderDTO> getOrdersByDay();

    List<OrderDTO> getOrdersByMonth();
}
