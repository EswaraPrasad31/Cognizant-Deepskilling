public class SearchDemo {

    public static void main(String[] args) {

        SearchProduct[] sortedProducts = {
            new SearchProduct(101, "Laptop", "Electronics"),
            new SearchProduct(102, "Mobile", "Electronics"),
            new SearchProduct(103, "Headphones", "Accessories"),
            new SearchProduct(104, "Keyboard", "Accessories")
        };

        SearchProduct linearSearchResult =
                SearchOperations.linearSearch(sortedProducts, 103);

        if (linearSearchResult != null) {
            System.out.println("Linear Search Found: "
                    + linearSearchResult.getProductName());
        } else {
            System.out.println("Product not found using Linear Search");
        }

        SearchProduct binarySearchResult =
                SearchOperations.binarySearch(sortedProducts, 103);

        if (binarySearchResult != null) {
            System.out.println("Binary Search Found: "
                    + binarySearchResult.getProductName());
        } else {
            System.out.println("Product not found using Binary Search");
        }
    }
}