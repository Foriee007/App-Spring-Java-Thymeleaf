package com.bonappetit.repo;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long> {
    //Optional<Category> findCategoryByName(CategoryEnum categoryEnum);

    //Optional<Category> findCategory(CategoryEnum category);

    Optional<Category> findByName(CategoryEnum category);
}
