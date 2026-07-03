public class TaskManagementDemo {

    public static void main(String[] args) {

        TaskLinkedList taskManager =
                new TaskLinkedList();

        taskManager.addTask(
                new Task(101,
                         "Design Database",
                         "Pending"));

        taskManager.addTask(
                new Task(102,
                         "Develop API",
                         "In Progress"));

        taskManager.addTask(
                new Task(103,
                         "Testing",
                         "Pending"));

        System.out.println("All Tasks");

        taskManager.displayTasks();

        Task task =
                taskManager.searchTask(102);

        if (task != null) {

            System.out.println("\nTask Found");

            System.out.println(task);
        }

        System.out.println();

        taskManager.deleteTask(102);

        System.out.println("\nRemaining Tasks");

        taskManager.displayTasks();
    }
}