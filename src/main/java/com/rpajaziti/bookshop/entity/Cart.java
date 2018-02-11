package com.rpajaziti.bookshop.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid")
    @GenericGenerator(name = "uuid",
            strategy = "com.rpajaziti.bookshop.custom.IdGenerator")
    @Column(name = "id")
    private String id;

    @NotNull(message = "customerId column is required")
    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private long createdAt;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private List<CartDetail> cartDetails;

    public Cart() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double total) {
        this.totalPrice = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public List<CartDetail> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", total=" + totalPrice +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
