public class ProxyPatternTest {

    public static void main(String[] args) {

        Image image = new ProxyImage("Nature.jpg");

        System.out.println("Image object created.");

        System.out.println("\nFirst Display:");
        image.display();

        System.out.println("\nSecond Display:");
        image.display();
    }
}