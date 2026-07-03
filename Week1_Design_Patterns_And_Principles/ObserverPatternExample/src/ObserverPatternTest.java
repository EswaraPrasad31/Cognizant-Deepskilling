public class ObserverPatternTest {

    public static void main(String[] args) {

        StockMarket stockMarket = new StockMarket();

        Observer mobileUser = new MobileApp("Rahul");
        Observer webUser = new WebApp("Priya");

        stockMarket.registerObserver(mobileUser);
        stockMarket.registerObserver(webUser);

        stockMarket.setStockData("TCS", 3850.50);

        stockMarket.setStockData("Infosys", 1620.75);

        stockMarket.deregisterObserver(webUser);

        stockMarket.setStockData("Wipro", 520.25);
    }
}