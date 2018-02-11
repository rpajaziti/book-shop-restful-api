package com.rpajaziti.bookshop.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid")
    @GenericGenerator(name = "uuid",
            strategy = "com.rpajaziti.bookshop.custom.IdGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
