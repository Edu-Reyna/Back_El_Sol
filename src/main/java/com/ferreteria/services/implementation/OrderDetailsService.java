package com.ferreteria.services.implementation;

import com.ferreteria.controller.dto.OrderDetailsDTO;
import com.ferreteria.entities.OrderDetailsEntity;
import com.ferreteria.repositories.IOrderDetailsRepository;
import com.ferreteria.services.interfaces.IOrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailsService implements IOrderDetailsService {

    private final ModelMapper modelMapper;
    private final IOrderDetailsRepository orderDetailsRepository;

    @Override
    public OrderDetailsDTO createOrderDetails(OrderDetailsDTO orderDetailsDTO) {

        OrderDetailsEntity orderDetails = this.modelMapper.map(orderDetailsDTO, OrderDetailsEntity.class);

        OrderDetailsEntity orderDetailsSaved = this.orderDetailsRepository.save(orderDetails);

        return this.modelMapper.map(orderDetailsSaved, OrderDetailsDTO.class);
    }
}
