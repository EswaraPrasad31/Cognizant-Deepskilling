public class LibrarySearch {

    public static Book linearSearch(Book[] books,
                                    String title) {

        for (Book book : books) {

            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }

        return null;
    }

    public static Book binarySearch(Book[] books,
                                    String title) {

        int leftIndex = 0;
        int rightIndex = books.length - 1;

        while (leftIndex <= rightIndex) {

            int middleIndex =
                    (leftIndex + rightIndex) / 2;

            int comparison =
                    books[middleIndex]
                    .getTitle()
                    .compareToIgnoreCase(title);

            if (comparison == 0) {
                return books[middleIndex];
            }

            if (comparison < 0) {
                leftIndex = middleIndex + 1;
            } else {
                rightIndex = middleIndex - 1;
            }
        }

        return null;
    }
}