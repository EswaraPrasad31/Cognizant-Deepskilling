public class LibraryManagementDemo {

    public static void main(String[] args) {

        Book[] books = {

            new Book(101,
                     "Atomic Habits",
                     "James Clear"),

            new Book(102,
                     "Clean Code",
                     "Robert Martin"),

            new Book(103,
                     "Java Complete Reference",
                     "Herbert Schildt"),

            new Book(104,
                     "Think And Grow Rich",
                     "Napoleon Hill")
        };

        Book linearSearchResult =
                LibrarySearch.linearSearch(
                        books,
                        "Clean Code");

        if (linearSearchResult != null) {

            System.out.println(
                    "Linear Search Found:");

            System.out.println(
                    linearSearchResult);
        }

        Book binarySearchResult =
                LibrarySearch.binarySearch(
                        books,
                        "Java Complete Reference");

        if (binarySearchResult != null) {

            System.out.println(
                    "\nBinary Search Found:");

            System.out.println(
                    binarySearchResult);
        }
    }
}