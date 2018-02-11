package com.rpajaziti.bookshop.dao;

import com.rpajaziti.bookshop.entity.Category;

import java.util.List;

public interface CategoryDAO {

	List<Category> getCategories();

	void saveOrUpdateCategory(Category cart);

	Category getCategory(String id);
}
