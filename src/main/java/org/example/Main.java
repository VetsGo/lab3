package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.setProperty("console.encoding", "UTF-8");
        Scanner scanner = new Scanner(System.in);
        List<Product> availableProducts = new ArrayList<>();
        Cart cart = new Cart();
        List<Order> orders = new ArrayList<>();

        availableProducts.add(new Product(1, "Телефон Xiaomi", 8000));
        availableProducts.add(new Product(2, "Ноутбук Lenovo", 20000));
        availableProducts.add(new Product(3, "Планшет Notepad", 10000));
        availableProducts.add(new Product(4, "Комп'ютер Samsung", 700));
        availableProducts.add(new Product(5, "Консоль PlayStation", 15000));
        availableProducts.add(new Product(6, "Телевізор LG", 20000));

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Доступні продукти:");
            for (Product product : availableProducts) {
                System.out.println(product.getId() + ". " + product.getName() + " - " + product.getPrice() + " грн");
            }

            System.out.println("Виберіть дію:");
            System.out.println("1. Додати продукт до кошика");
            System.out.println("2. Видалити продукт з кошика");
            System.out.println("3. Зробити замовлення");
            System.out.println("4. Перевірити статус замовлення");
            System.out.println("5. Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введіть ID продукту, який ви хочете додати до кошика: ");
                    int productIdToAdd = scanner.nextInt();
                    Product productToAdd = availableProducts.stream()
                            .filter(product -> product.getId() == productIdToAdd)
                            .findFirst()
                            .orElse(null);
                    if (productToAdd != null) {
                        cart.addProduct(productToAdd);
                        System.out.println("Продукт додано до кошика");
                    } else {
                        System.out.println("Продукт не знайдено");
                    }
                    break;
                case 2:
                    System.out.print("Введіть ID продукту, який ви хочете видалити з кошика: ");
                    int productIdToRemove = scanner.nextInt();
                    Product productToRemove = availableProducts.stream()
                            .filter(product -> product.getId() == productIdToRemove)
                            .findFirst()
                            .orElse(null);
                    if (productToRemove != null) {
                        cart.removeProduct(productToRemove);
                        System.out.println("Продукт видалено з кошика");
                    } else {
                        System.out.println("Продукт не знайдено");
                    }
                    break;
                case 3:
                    List<Product> productsInCart = new ArrayList<>();
                    for (Map.Entry<Integer, Integer> entry : cart.getProductsInCart().entrySet()) {
                        int productId = entry.getKey();
                        int quantity = entry.getValue();
                        for (int i = 0; i < quantity; i++) {
                            Product productInCart = availableProducts.stream()
                                    .filter(product -> product.getId() == productId)
                                    .findFirst()
                                    .orElse(null);
                            if (productInCart != null) {
                                productsInCart.add(productInCart);
                            }
                        }
                    }
                    if (!productsInCart.isEmpty()) {
                        Order order = new Order(productsInCart);
                        orders.add(order);
                        System.out.println("Замовлення створено. ID замовлення: " + order.getOrderId());
                        System.out.println("Ваше замовлення: " + order.getProducts());
                        cart = new Cart();
                    } else {
                        System.out.println("Кошик пустий");
                    }
                    break;
                case 4:
                    System.out.print("Введіть ID замовлення: ");
                    int orderIdToCheck = scanner.nextInt();
                    Order orderToCheck = orders.stream()
                            .filter(order -> order.getOrderId() == orderIdToCheck)
                            .findFirst()
                            .orElse(null);
                    if (orderToCheck != null) {
                        System.out.println("Статус замовлення " + orderToCheck.getOrderId() + ": " + orderToCheck.getStatus());
                    } else {
                        System.out.println("Замовлення не знайдено");
                    }
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Недійсна дія");
            }
        }
        scanner.close();
    }
}