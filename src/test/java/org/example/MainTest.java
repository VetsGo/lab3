package org.example;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class MainTest {

    private Cart cart;
    private List<Product> availableProducts;

    @Before
    public void setUp() {
        cart = new Cart();
        availableProducts = new ArrayList<>();
        availableProducts.add(new Product(1, "Телефон Xiaomi", 8000));
        availableProducts.add(new Product(2, "Ноутбук Lenovo", 20000));
        availableProducts.add(new Product(3, "Планшет Notepad", 10000));
        availableProducts.add(new Product(4, "Комп'ютер Samsung", 700));
        availableProducts.add(new Product(5, "Консоль PlayStation", 15000));
        availableProducts.add(new Product(6, "Телевізор LG", 20000));
    }

    @Test
    public void testAddProductToCart() {
        Product productToAdd = availableProducts.get(0);
        cart.addProduct(productToAdd);
        Map<Integer, Integer> productsInCart = cart.getProductsInCart();
        assertEquals(1, productsInCart.size());
    }

    @Test
    public void testRemoveProductFromCart() {
        Product productToAdd = availableProducts.get(0);
        cart.addProduct(productToAdd);
        cart.removeProduct(productToAdd);
        Map<Integer, Integer> productsInCart = cart.getProductsInCart();
        assertEquals(0, productsInCart.size());
    }

    @Test
    public void testPlaceOrder() {
        Product productToAdd = availableProducts.get(0);
        cart.addProduct(productToAdd);
        List<Product> productsInCart = new ArrayList<>();
        productsInCart.add(productToAdd);
        Order order = new Order(productsInCart);
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        assertEquals(1, orders.size());
        cart = new Cart();
    }

    @Test
    public void testGetOrderStatus() {
        Product productToAdd = availableProducts.get(0);
        cart.addProduct(productToAdd);
        List<Product> productsInCart = new ArrayList<>();
        productsInCart.add(productToAdd);
        Order order = new Order(productsInCart);
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        Order retrievedOrder = orders.get(0);
        retrievedOrder.setStatus("Processing");
        assertEquals("Processing", retrievedOrder.getStatus());
    }
}