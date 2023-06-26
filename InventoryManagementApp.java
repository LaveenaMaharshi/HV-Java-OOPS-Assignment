import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private String name;
    private String specification;
    private double cost;
    private int count;

    public Product(String name, String specification, double cost, int count) {
        this.name = name;
        this.specification = specification;
        this.cost = cost;
        this.count = count;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

class InventoryManagementSystem {
    private List<Product> products;

    public InventoryManagementSystem() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }

    public void displayProductList() {
        System.out.println("Product List:");
        for (Product product : products) {
            System.out.println("- " + product.getName());
        }
    }

    public int getProductCount(String productName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product.getCount();
            }
        }
        return 0;
    }

    public void displayProductDetails(String productName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                System.out.println("Product Specification: " + product.getSpecification());
                System.out.println("Product Cost: " + product.getCost());
                System.out.println("Product Count Available: " + product.getCount());
                return;
            }
        }
        System.out.println("Product not found!");
    }

    public void updateProductDetails(String productName, String specification, double cost) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                product.setSpecification(specification);
                product.setCost(cost);
                return;
            }
        }
        System.out.println("Product not found!");
    }

    public void updateInventory(String productName, int quantity) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                int updatedCount = product.getCount() + quantity;
                if (updatedCount >= 0) {
                    product.setCount(updatedCount);
                    System.out.println("Inventory updated successfully!");
                } else {
                    System.out.println("Insufficient quantity available!");
                }
                return;
            }
        }
        System.out.println("Product not found!");
    }
}

public class InventoryManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManagementSystem inventorySystem = new InventoryManagementSystem();

        // Sample data for demonstration
        Product product1 = new Product("Laptop", "16GB RAM, 512GB SSD", 999.99, 5);
        Product product2 = new Product("Smartphone", "6.5-inch Display, 128GB Storage", 699.99, 10);
        inventorySystem.addProduct(product1);
        inventorySystem.addProduct(product2);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n----- Inventory Management System -----");
            System.out.println("1. Product List");
            System.out.println("2. Product Count");
            System.out.println("3. View Product Details");
            System.out.println("4. Edit Product");
            System.out.println("5. Update Inventory");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    inventorySystem.displayProductList();
                    break;
                case 2:
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    int count = inventorySystem.getProductCount(productName);
                    System.out.println("Quantity available: " + count);
                    break;
                case 3:
                    System.out.print("Enter product name: ");
                    productName = scanner.nextLine();
                    inventorySystem.displayProductDetails(productName);
                    break;
                case 4:
                    System.out.print("Enter product name: ");
                    productName = scanner.nextLine();
                    System.out.print("Enter new specification: ");
                    String specification = scanner.nextLine();
                    System.out.print("Enter new cost: ");
                    double cost = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    inventorySystem.updateProductDetails(productName, specification, cost);
                    break;
                case 5:
                    System.out.print("Enter product name: ");
                    productName = scanner.nextLine();
                    System.out.print("Enter quantity to add/delete: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    inventorySystem.updateInventory(productName, quantity);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }
}
