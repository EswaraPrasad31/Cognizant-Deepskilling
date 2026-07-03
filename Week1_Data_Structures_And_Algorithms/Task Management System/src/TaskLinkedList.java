public class TaskLinkedList {

    private Task head;

    public void addTask(Task task) {

        if (head == null) {
            head = task;
            return;
        }

        Task currentTask = head;

        while (currentTask.next != null) {
            currentTask = currentTask.next;
        }

        currentTask.next = task;
    }

    public Task searchTask(int taskId) {

        Task currentTask = head;

        while (currentTask != null) {

            if (currentTask.getTaskId() == taskId) {
                return currentTask;
            }

            currentTask = currentTask.next;
        }

        return null;
    }

    public void deleteTask(int taskId) {

        if (head == null) {
            return;
        }

        if (head.getTaskId() == taskId) {
            head = head.next;
            System.out.println("Task Deleted Successfully");
            return;
        }

        Task currentTask = head;

        while (currentTask.next != null &&
               currentTask.next.getTaskId() != taskId) {

            currentTask = currentTask.next;
        }

        if (currentTask.next != null) {

            currentTask.next = currentTask.next.next;

            System.out.println("Task Deleted Successfully");
        } else {

            System.out.println("Task Not Found");
        }
    }

    public void displayTasks() {

        Task currentTask = head;

        while (currentTask != null) {

            System.out.println(currentTask);

            currentTask = currentTask.next;
        }
    }
}