package com.rpajaziti.bookshop.controller;

import com.rpajaziti.bookshop.custom.ResponseMessage;
import com.rpajaziti.bookshop.entity.Category;
import com.rpajaziti.bookshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") String id) {
        return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.saveOrUpdateCategory(category), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<ResponseMessage> updateCategory(@RequestBody Category category) {

        categoryService.saveOrUpdateCategory(category);

        return new ResponseEntity<>(new ResponseMessage().setMessage("Updated Successfully"), HttpStatus.OK);
    }
}