import java.util.*;

interface PaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("Processing Credit Card payment...");
        return true;
    }
}

class PayPalPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment...");
        return false;
    }
}

class BankTransferPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("Processing Bank Transfer...");
        return true;
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }
}

class Order {
    private String orderId;
    private List<OrderItem> items = new ArrayList<>();
    private String status = "Pending";

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public void pay(PaymentMethod paymentMethod) {

        if (isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        System.out.println(
                "Payment initiated for Order " + orderId);

        boolean success =
                paymentMethod.processPayment(getTotal());

        if (success) {
            status = "Paid";
            System.out.println("Payment successful.");
        } else {
            System.out.println("Payment failed.");
        }

        System.out.println("Order status: " + status);
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class PaymentProcessingShoppingSystem {
    public static void main(String[] args) {

        Customer customerX = new Customer("Customer X");

        Order orderX = new Order("X");

        Product productA = new Product("Product A", 500);
        Product productB = new Product("Product B", 300);

        orderX.addItem(productA, 2);
        orderX.addItem(productB, 1);

        System.out.println("Order created for " + customerX.getName() + ".");

        orderX.pay(new CreditCardPayment());

        Customer customerY = new Customer("Customer Y");
        Order orderY = new Order("Y");

        System.out.println("\nOrder created for " + customerY.getName() + ".");
        orderY.pay(new PayPalPayment());

        Customer customerZ = new Customer("Customer Z");
        Order orderZ = new Order("Z");

        Product productC = new Product("Product C", 400);
        orderZ.addItem(productC, 1);

        System.out.println("\nOrder created for " + customerZ.getName() + ".");
        orderZ.pay(new PayPalPayment());
    }
}