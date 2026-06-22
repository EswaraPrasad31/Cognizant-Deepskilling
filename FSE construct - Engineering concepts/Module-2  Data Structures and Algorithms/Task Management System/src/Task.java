public class Task {

    private int taskId;
    private String taskName;
    private String status;

    Task next;

    public Task(int taskId,
                String taskName,
                String status) {

        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {

        return "Task ID: " + taskId +
               ", Task Name: " + taskName +
               ", Status: " + status;
    }
}