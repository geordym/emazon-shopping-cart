package com.emazon.shopping_cart.domain.model;

public class CartItem {

    private final Long id;
    private final ShoppingCart shoppingCart;
    private final Long idArticle;
    private final Integer quantity;
    private final Double price;

    private CartItem(Builder builder) {
        this.id = builder.id;
        this.shoppingCart = builder.shoppingCart;
        this.idArticle = builder.idArticle;
        this.quantity = builder.quantity;
        this.price = builder.price;
    }

    public Long getId() {
        return id;
    }



    public Long getIdArticle() {
        return idArticle;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public static class Builder {
        private Long id;
        private ShoppingCart shoppingCart;
        private Long idArticle;
        private Integer quantity;
        private Double price;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withShoppingCart(ShoppingCart shoppingCart) {
            this.shoppingCart = shoppingCart;
            return this;
        }

        public Builder withIdArticle(Long idArticle) {
            this.idArticle = idArticle;
            return this;
        }

        public Builder withQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public CartItem build() {
            return new CartItem(this);
        }

    }
}
