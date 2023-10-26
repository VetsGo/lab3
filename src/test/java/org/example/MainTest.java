package org.example;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MainTest {

    @InjectMocks
    private Cart cart;

    @Mock
    private List<Product> availableProducts;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddProductToCart() {
        Product productToAdd = new Product(1, "Телефон Xiaomi", 8000);
        Mockito.when(availableProducts.get(0)).thenReturn(productToAdd);
        cart.addProduct(productToAdd);
        Map<Integer, Integer> productsInCart = cart.getProductsInCart();
        assertEquals(1, productsInCart.size());
    }

    @Test
    public void testRemoveProductFromCart() {
        Product productToAdd = new Product(2, "Ноутбук Lenovo", 20000);
        Mockito.when(availableProducts.get(0)).thenReturn(productToAdd);
        cart.addProduct(productToAdd);
        cart.removeProduct(productToAdd);
        Map<Integer, Integer> productsInCart = cart.getProductsInCart();
        assertEquals(0, productsInCart.size());
    }

    @Test
    public void testPlaceOrder() {
        Product productToAdd = new Product(3, "Планшет Notepad", 10000);
        Mockito.when(availableProducts.get(0)).thenReturn(productToAdd);
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
        Product productToAdd = new Product(4, "Комп'ютер Samsung", 700);
        Mockito.when(availableProducts.get(0)).thenReturn(productToAdd);
        cart.addProduct(productToAdd);
        List<Product> productsInCart = new ArrayList<>();
        productsInCart.add(productToAdd);
        Order order = new Order(productsInCart);
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        Order retrievedOrder = orders.get(0);
        Order spyOrder = Mockito.spy(retrievedOrder);
        spyOrder.setStatus("Processing");

        assertEquals("Processing", spyOrder.getStatus());
    }
}