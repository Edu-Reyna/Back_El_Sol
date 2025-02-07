package com.ferreteria.config;

import com.ferreteria.controller.dto.*;
import com.ferreteria.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        // Mapeo de OrderEntity a OrderDTO
        modelMapper.createTypeMap(OrderEntity.class, OrderDTO.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getUser().getId(), OrderDTO::setUserId);
                });

        // Mapeo de OrderDetailsEntity a OrderDetailsDTO
        modelMapper.createTypeMap(OrderDetailsEntity.class, OrderDetailsDTO.class)
                .addMappings(mapper -> {
                    mapper.map(OrderDetailsEntity::getProduct, OrderDetailsDTO::setProductId);
                    mapper.map(OrderDetailsEntity::getQuantity, OrderDetailsDTO::setQuantity);
                });

        // Mapeo de ProductEntity a ProductDTO
        modelMapper.createTypeMap(ProductEntity.class, ProductDTO.class)
                .addMappings(mapper -> {
                    mapper.map(ProductEntity::getName, ProductDTO::setName);
                    mapper.map(ProductEntity::getDescription, ProductDTO::setDescription);
                    mapper.map(ProductEntity::getPrice, ProductDTO::setPrice);
                    mapper.map(ProductEntity::getStock, ProductDTO::setStock);
                    mapper.map(ProductEntity::isStatus, ProductDTO::setStatus);
                    mapper.map(ProductEntity::getCategory, ProductDTO::setCategoryId);
                });

        // Mapeo de CategoryEntity a CategoryDTO
        modelMapper.createTypeMap(CategoryEntity.class, CategoryDTO.class)
                .addMappings(mapper -> {
                    mapper.map(CategoryEntity::getId, CategoryDTO::setId);
                    mapper.map(CategoryEntity::getName, CategoryDTO::setName);
                    mapper.map(CategoryEntity::getDescription, CategoryDTO::setDescription);
                });

        return modelMapper;
    }
}
