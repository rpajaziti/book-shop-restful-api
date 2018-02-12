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

    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") String id) {
        return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.saveOrUpdateCategory(category), HttpStatus.OK);
    }

    @PutMapping(value = "{id}", consumes = "application/json")
    public ResponseEntity<ResponseMessage> updateCategory(@PathVariable("id") String id,
                                                          @RequestBody Category category) {
        if (category.getId() == null) {
            category.setId(id);
        }
        categoryService.saveOrUpdateCategory(category);

        return new ResponseEntity<>(new ResponseMessage().setMessage("Updated Successfully"), HttpStatus.OK);
    }
}
