package com.ferreteria.services.implementation;

import com.ferreteria.controller.dto.ProductDTO;
import com.ferreteria.entities.CategoryEntity;
import com.ferreteria.entities.ProductEntity;
import com.ferreteria.repositories.ICategoryRepository;
import com.ferreteria.repositories.IProductRepository;
import com.ferreteria.services.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ModelMapper modelMapper;
    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        ProductEntity productEntity = this.modelMapper.map(productDTO, ProductEntity.class);

        ProductEntity productSave = this.productRepository.save(productEntity);

        return this.modelMapper.map(productSave, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return this.productRepository.findAll().stream()
                .map(product -> this.modelMapper.map(product, ProductDTO.class)).toList();
    }

    @Override
    public List<ProductDTO> getAllByProducts() {
        return this.productRepository.findAllByStatus(true).stream()
                .map(product -> this.modelMapper.map(product, ProductDTO.class)).toList();
    }

    @Override
    public ProductDTO getProductById(Long id) {
         ProductEntity product = this.productRepository.findById(id).orElseGet(ProductEntity::new);

         return this.modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long id) {
        ProductEntity product = this.productRepository.findById(id).orElseThrow(() -> new RuntimeException("El producto no existe"));
        CategoryEntity category = this.categoryRepository.findById(productDTO.getCategoryId().getId()).orElseThrow(() -> new RuntimeException("La categoría no existe"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setStatus(productDTO.isStatus());
        product.setCategory(category);

        ProductEntity productSaved = this.productRepository.save(product);
        return this.modelMapper.map(productSaved, ProductDTO.class);
    }

    @Override
    public String deleteProduct(Long id) {

        ProductEntity product = this.productRepository.findById(id).orElseThrow(() -> new RuntimeException("El producto no existe"));

        this.productRepository.delete(product);

        return "Producto Eliminado";
    }

    @Override
    public ProductDTO resupplyProduct(ProductDTO productDTO) {
        ProductEntity product = this.productRepository.findById(productDTO.getId()).orElseThrow(() -> new RuntimeException("El producto no existe"));

        product.setStock( product.getStock() + productDTO.getStock() );

        if (product.getStock() > 0) {
            product.setStatus(true);
        }

        ProductEntity productSaved = this.productRepository.save(product);
        return this.modelMapper.map(productSaved, ProductDTO.class);
    }



}
