public class StrategyPatternTest {

    public static void main(String[] args) {

        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment());
        context.makePayment(5000);

        context.setPaymentStrategy(new PayPalPayment());
        context.makePayment(2500);
    }
}