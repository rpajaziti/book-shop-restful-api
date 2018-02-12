package com.rpajaziti.bookshop.controller;

import com.rpajaziti.bookshop.custom.ResponseMessage;
import com.rpajaziti.bookshop.entity.Cart;
import com.rpajaziti.bookshop.entity.CartDetail;
import com.rpajaziti.bookshop.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart-details")
public class CartDetailController {

    private final CartDetailService cartDetailService;

    @Autowired
    public CartDetailController(CartDetailService cartDetailService) {
        this.cartDetailService = cartDetailService;
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<CartDetail> getCartDetailById(@PathVariable("id") String id) {
        return new ResponseEntity<>(cartDetailService.getCartDetail(id), HttpStatus.OK);

    }

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<?> saveCartDetail(@RequestBody CartDetail cartDetail) {
        cartDetail.setCart(new Cart());
        cartDetail.getCart().setId(cartDetail.getCartId());
        cartDetail = cartDetailService.saveOrUpdateCartDetail(cartDetail);
        if (cartDetail == null) {
            return new ResponseEntity<>(new ResponseMessage().setMessage("Bad Request."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cartDetail, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", consumes = "application/json")
    public ResponseEntity<ResponseMessage> updateCartDetail(@PathVariable("id") String id,
                                                            @RequestBody CartDetail cartDetail) {
        if (cartDetail.getId() == null) {
            cartDetail.setId(id);
        }
        cartDetail.setCart(new Cart());
        cartDetail.getCart().setId(cartDetail.getCartId());

        cartDetail = cartDetailService.saveOrUpdateCartDetail(cartDetail);
        if (cartDetail == null) {
            return new ResponseEntity<>(new ResponseMessage().setMessage("Bad Request."), HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(new ResponseMessage().setMessage("Updated Successfully."), HttpStatus.OK);
    }
}
