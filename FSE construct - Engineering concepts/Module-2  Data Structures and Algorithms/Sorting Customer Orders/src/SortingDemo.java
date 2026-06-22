public class SortingDemo {

    public static void main(String[] args) {

        Order[] orders = {

                new Order(101, "Rahul", 5500),
                new Order(102, "Kiran", 2500),
                new Order(103, "Anil", 8500),
                new Order(104, "Priya", 4500)
        };

        System.out.println("Original Orders");
        OrderSorter.displayOrders(orders);

        OrderSorter.quickSort(
                orders,
                0,
                orders.length - 1);

        System.out.println("\nOrders After Quick Sort");
        OrderSorter.displayOrders(orders);
    }
}