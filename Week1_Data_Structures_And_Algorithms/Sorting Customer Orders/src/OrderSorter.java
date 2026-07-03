public class OrderSorter {

    public static void bubbleSort(Order[] orders) {

        int size = orders.length;

        for (int i = 0; i < size - 1; i++) {

            for (int j = 0; j < size - i - 1; j++) {

                if (orders[j].getTotalPrice() >
                        orders[j + 1].getTotalPrice()) {

                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    public static void quickSort(Order[] orders,
                                 int lowIndex,
                                 int highIndex) {

        if (lowIndex < highIndex) {

            int pivotIndex =
                    partition(orders, lowIndex, highIndex);

            quickSort(orders, lowIndex, pivotIndex - 1);
            quickSort(orders, pivotIndex + 1, highIndex);
        }
    }

    private static int partition(Order[] orders,
                                 int lowIndex,
                                 int highIndex) {

        double pivot =
                orders[highIndex].getTotalPrice();

        int smallerElementIndex = lowIndex - 1;

        for (int currentIndex = lowIndex;
             currentIndex < highIndex;
             currentIndex++) {

            if (orders[currentIndex].getTotalPrice()
                    < pivot) {

                smallerElementIndex++;

                Order temp =
                        orders[smallerElementIndex];

                orders[smallerElementIndex] =
                        orders[currentIndex];

                orders[currentIndex] = temp;
            }
        }

        Order temp =
                orders[smallerElementIndex + 1];

        orders[smallerElementIndex + 1] =
                orders[highIndex];

        orders[highIndex] = temp;

        return smallerElementIndex + 1;
    }

    public static void displayOrders(Order[] orders) {

        for (Order order : orders) {
            System.out.println(order);
        }
    }
}