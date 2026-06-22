public class FinancialForecastDemo {

    public static void main(String[] args) {

        double currentValue = 10000.0;
        double annualGrowthRate = 0.10;
        int forecastYears = 5;

        double futureValue =
                FinancialForecast.calculateFutureValue(
                        currentValue,
                        annualGrowthRate,
                        forecastYears);

        System.out.println("Current Value: ₹" +
                currentValue);

        System.out.println("Growth Rate: " +
                (annualGrowthRate * 100) + "%");

        System.out.println("Forecast Years: " +
                forecastYears);

        System.out.println("Future Value: ₹" +
                String.format("%.2f", futureValue));
    }
}