package com.rpajaziti.bookshop.service;

import com.rpajaziti.bookshop.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getCategories();

    Category saveOrUpdateCategory(Category category);

    Category getCategory(String id);
}
