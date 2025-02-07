package com.ferreteria.services.interfaces;

import com.ferreteria.controller.dto.OrderDetailsDTO;

public interface IOrderDetailsService {

    OrderDetailsDTO createOrderDetails(OrderDetailsDTO orderDetailsDTO);
}
