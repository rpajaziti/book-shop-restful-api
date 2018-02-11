package com.rpajaziti.bookshop.service;

import com.rpajaziti.bookshop.dao.CategoryDAO;
import com.rpajaziti.bookshop.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    @Transactional
    public List<Category> getCategories() {
        return categoryDAO.getCategories();
    }

    @Override
    @Transactional
    public Category saveOrUpdateCategory(Category category) {
        return category;
    }

    @Override
    @Transactional
    public Category getCategory(String id) {
        return categoryDAO.getCategory(id);
    }
}