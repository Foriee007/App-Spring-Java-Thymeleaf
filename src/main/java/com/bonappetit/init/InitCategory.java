package com.bonappetit.init;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.CategoryEnum;
import com.bonappetit.repo.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InitCategory implements CommandLineRunner {
    private Map<CategoryEnum, String> categoriesDescription = Map.of(
            CategoryEnum.MAIN_DISH, "Heart of the meal, substantial and satisfying; main dish delights taste buds.",
            CategoryEnum.DESSERT, "Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy.",
            CategoryEnum.COCKTAIL, "Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass."
            );
    private CategoryRepository categoryRepository;

    public InitCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = categoryRepository.count();
        if (count==0){
            for (CategoryEnum categoryEnum : categoriesDescription.keySet()){
                Category category = new Category(categoryEnum, categoriesDescription.get(categoryEnum));

                categoryRepository.save(category);
            }

        }


    }
}
