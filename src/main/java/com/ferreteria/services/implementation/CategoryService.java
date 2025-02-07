package com.ferreteria.services.implementation;

import com.ferreteria.controller.dto.CategoryDTO;
import com.ferreteria.entities.CategoryEntity;
import com.ferreteria.repositories.ICategoryRepository;
import com.ferreteria.services.interfaces.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final ModelMapper modelMapper;
    private final ICategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = this.modelMapper.map(categoryDTO, CategoryEntity.class);

        CategoryEntity categorySave = this.categoryRepository.save(categoryEntity);

        return this.modelMapper.map(categorySave, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return this.categoryRepository.findAll().stream().map(category -> this.modelMapper.map(category, CategoryDTO.class)).toList();
    }

    @Override
    public CategoryDTO getProductById(Long id) {
        CategoryEntity category = this.categoryRepository.findById(id).orElseGet(CategoryEntity::new);

        return this.modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id) {
        CategoryEntity category = this.categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("La categoría no existe"));

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        CategoryEntity categorySave = this.categoryRepository.save(category);
        return this.modelMapper.map(categorySave, CategoryDTO.class);
    }

    @Override
    public String deleteProduct(Long id) {

        CategoryEntity category = this.categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("La categoría no existe"));

        this.categoryRepository.delete(category);

        return "Categoría Eliminada";
    }


}
