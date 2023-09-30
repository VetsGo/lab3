package org.example;
import java.util.*;

public class Cart {
    private Map<Integer, Integer> productsInCart;

    public Cart() {
        productsInCart = new HashMap<>();
    }

    public void addProduct(Product product) {
        int productId = product.getId();
        productsInCart.put(productId, productsInCart.getOrDefault(productId, 0) + 1);
    }

    public void removeProduct(Product product) {
        int productId = product.getId();
        if (productsInCart.containsKey(productId)) {
            int quantity = productsInCart.get(productId);
            if (quantity > 1) {
                productsInCart.put(productId, quantity - 1);
            } else {
                productsInCart.remove(productId);
            }
        }
    }

    public Map<Integer, Integer> getProductsInCart() {
        return productsInCart;
    }
}