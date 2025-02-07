package com.ferreteria.services.interfaces;


import com.ferreteria.controller.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getProductById(Long id);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id);

    String deleteProduct(Long id);
}
