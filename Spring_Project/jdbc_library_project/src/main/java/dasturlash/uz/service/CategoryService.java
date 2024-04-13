package dasturlash.uz.service;

import dasturlash.uz.Container.ComponentContainer;
import dasturlash.uz.dto.Category;
import dasturlash.uz.repository.CategoryRepository;

import java.util.List;

public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService() {
        categoryRepository=new CategoryRepository();
    }

    public void CategoryList(){
        List<Category>categoryList=categoryRepository.categoryList();
        for(Category category:categoryList){
            System.out.println(category.toString());
        }
    }

    public void delete(Integer id) {
        int effectedRows = ComponentContainer.categoryRepository.deleteById(id);
        if (effectedRows != 0){
            System.out.println("Category delete");
        }else {
            System.out.println("Category not delete");
        }
    }

    public void addCategory(Category category) {
        ComponentContainer.categoryRepository.addCategory(category);
    }
}
