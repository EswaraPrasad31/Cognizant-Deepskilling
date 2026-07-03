public class DecoratorPatternTest {

    public static void main(String[] args) {

        System.out.println("Email Notification:");
        Notifier emailNotifier = new EmailNotifier();
        emailNotifier.send("Meeting at 10 AM");

        System.out.println("\nEmail + SMS Notification:");
        Notifier smsNotifier =
                new SMSNotifierDecorator(new EmailNotifier());
        smsNotifier.send("Project Deadline Tomorrow");

        System.out.println("\nEmail + SMS + Slack Notification:");
        Notifier fullNotifier =
                new SlackNotifierDecorator(
                        new SMSNotifierDecorator(
                                new EmailNotifier()));

        fullNotifier.send("System Maintenance Tonight");
    }
}