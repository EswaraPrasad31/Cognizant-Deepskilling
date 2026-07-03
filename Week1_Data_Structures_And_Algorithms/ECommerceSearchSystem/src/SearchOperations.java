public class SearchOperations {

    public static SearchProduct linearSearch(SearchProduct[] products, int productId) {

        for (SearchProduct product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }

        return null;
    }

    public static SearchProduct binarySearch(SearchProduct[] products, int productId) {

        int leftIndex = 0;
        int rightIndex = products.length - 1;

        while (leftIndex <= rightIndex) {

            int middleIndex = (leftIndex + rightIndex) / 2;

            if (products[middleIndex].getProductId() == productId) {
                return products[middleIndex];
            }

            if (products[middleIndex].getProductId() < productId) {
                leftIndex = middleIndex + 1;
            } else {
                rightIndex = middleIndex - 1;
            }
        }

        return null;
    }
}