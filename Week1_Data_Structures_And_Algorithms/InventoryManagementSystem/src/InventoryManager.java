import java.util.HashMap;
import java.util.Map;

public class InventoryManager {

    private Map<Integer, Product> inventoryMap =
            new HashMap<>();

    public void addProduct(Product product) {
        inventoryMap.put(product.getProductId(), product);
        System.out.println("Product Added Successfully");
    }

    public void updateProduct(int productId,
                              int quantity,
                              double price) {

        Product existingProduct =
                inventoryMap.get(productId);

        if (existingProduct != null) {
            existingProduct.setQuantity(quantity);
            existingProduct.setPrice(price);

            System.out.println("Product Updated Successfully");
        } else {
            System.out.println("Product Not Found");
        }
    }

    public void deleteProduct(int productId) {

        if (inventoryMap.remove(productId) != null) {
            System.out.println("Product Deleted Successfully");
        } else {
            System.out.println("Product Not Found");
        }
    }

    public void displayInventory() {

        for (Product product : inventoryMap.values()) {
            System.out.println(product);
        }
    }
}