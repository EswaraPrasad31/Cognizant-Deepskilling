public class InventoryManagementDemo {

    public static void main(String[] args) {

        InventoryManager inventoryManager =
                new InventoryManager();

        Product laptop =
                new Product(101,
                            "Laptop",
                            10,
                            55000);

        Product smartphone =
                new Product(102,
                            "Smartphone",
                            20,
                            25000);

        inventoryManager.addProduct(laptop);
        inventoryManager.addProduct(smartphone);

        inventoryManager.displayInventory();

        inventoryManager.updateProduct(
                101,
                15,
                53000);

        inventoryManager.deleteProduct(102);

        System.out.println("\nUpdated Inventory");

        inventoryManager.displayInventory();
    }
}