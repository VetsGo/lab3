package org.example;
import java.util.*;

public class Order {
    private static int nextOrderId = 1;
    private int orderId;
    private List<Product> products;
    private String status;

    public Order(List<Product> products) {
        this.orderId = nextOrderId++;
        this.products = new ArrayList<>(products);
        this.status = "Processing";
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}