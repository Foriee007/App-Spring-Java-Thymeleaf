package com.bonappetit.service.impl;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.CategoryEnum;
import com.bonappetit.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public Category findCategory(CategoryEnum typeRecipe) {
        return null;
    }
}
