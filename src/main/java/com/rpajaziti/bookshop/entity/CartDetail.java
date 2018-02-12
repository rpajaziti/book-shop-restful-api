package com.rpajaziti.bookshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "cart_detail")
public class CartDetail extends BaseEntity {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST
            , CascadeType.REFRESH})
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @Column(name = "cart_id", insertable = false, updatable = false)
    private String cartId;

    @Column(name = "book_id")
    private String bookId;

    @Column(name = "total")
    private Double total;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private int quantity;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        return "CartDetail{" +
                "id=" + this.getId() +
                ", cart=" + cart +
                ", bookId=" + bookId +
                ", total=" + total +
                '}';
    }
}
