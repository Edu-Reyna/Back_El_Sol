package com.ferreteria.controller;

import com.ferreteria.controller.dto.ProductDTO;
import com.ferreteria.services.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    //Crear producto
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(this.productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    //Obtener todos los productos dependiendo del estado
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<List<ProductDTO>> getAllProductsByStatus() {
        return new ResponseEntity<>(this.productService.getAllByProducts(), HttpStatus.OK);
    }

    //Obtener todos lo productos sin importar estado
    @GetMapping("/all/products")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);
    }

    //Obtener producto por id
    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(this.productService.getProductById(id), HttpStatus.OK);
    }

    //Actualizar producto
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long id) {
        return new ResponseEntity<>(this.productService.updateProduct(productDTO, id), HttpStatus.CREATED);
    }

    //Eliminar producto
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return new ResponseEntity<>(this.productService.deleteProduct(id), HttpStatus.OK);
    }

    //Reabastecer producto
    @PutMapping("/reestock")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<ProductDTO> resupplyProduct(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(this.productService.resupplyProduct(productDTO), HttpStatus.CREATED);
    }
}
