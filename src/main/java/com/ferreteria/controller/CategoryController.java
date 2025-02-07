package com.ferreteria.controller;

import com.ferreteria.controller.dto.CategoryDTO;
import com.ferreteria.services.interfaces.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    //Crear categoría
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {

        return new ResponseEntity<>(this.categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    //Obtener todas las categorías
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {

        return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatus.OK);
    }

    //Obtener categoría por id
    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {

        return new ResponseEntity<>(this.categoryService.getProductById(id), HttpStatus.OK);
    }

    //Actualizar categoría
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {

        return new ResponseEntity<>(this.categoryService.updateCategory(categoryDTO, id), HttpStatus.CREATED);
    }

    //Eliminar categoría
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {

        return new ResponseEntity<>(this.categoryService.deleteProduct(id), HttpStatus.OK);
    }

}
