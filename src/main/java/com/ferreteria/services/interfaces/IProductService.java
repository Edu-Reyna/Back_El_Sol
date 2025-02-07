package com.ferreteria.services.interfaces;

import com.ferreteria.controller.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    ProductDTO createProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long id);

    ProductDTO updateProduct(ProductDTO productDTO, Long id);

    String deleteProduct(Long id);

    ProductDTO resupplyProduct(ProductDTO productDTO);

    List<ProductDTO> getAllByProducts();
}
