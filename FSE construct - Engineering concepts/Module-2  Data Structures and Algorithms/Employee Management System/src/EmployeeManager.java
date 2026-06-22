public class EmployeeManager {

    private Employee[] employees;
    private int employeeCount;

    public EmployeeManager(int capacity) {
        employees = new Employee[capacity];
        employeeCount = 0;
    }

    public void addEmployee(Employee employee) {

        if (employeeCount < employees.length) {
            employees[employeeCount] = employee;
            employeeCount++;

            System.out.println("Employee Added Successfully");
        } else {
            System.out.println("Employee Array is Full");
        }
    }

    public Employee searchEmployee(int employeeId) {

        for (int index = 0; index < employeeCount; index++) {

            if (employees[index].getEmployeeId() == employeeId) {
                return employees[index];
            }
        }

        return null;
    }

    public void deleteEmployee(int employeeId) {

        int deleteIndex = -1;

        for (int index = 0; index < employeeCount; index++) {

            if (employees[index].getEmployeeId() == employeeId) {
                deleteIndex = index;
                break;
            }
        }

        if (deleteIndex == -1) {
            System.out.println("Employee Not Found");
            return;
        }

        for (int index = deleteIndex;
             index < employeeCount - 1;
             index++) {

            employees[index] = employees[index + 1];
        }

        employees[employeeCount - 1] = null;
        employeeCount--;

        System.out.println("Employee Deleted Successfully");
    }

    public void displayEmployees() {

        for (int index = 0; index < employeeCount; index++) {
            System.out.println(employees[index]);
        }
    }
}