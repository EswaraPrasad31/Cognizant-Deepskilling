public class EmployeeManagementDemo {

    public static void main(String[] args) {

        EmployeeManager employeeManager =
                new EmployeeManager(10);

        employeeManager.addEmployee(
                new Employee(101,
                             "Rahul",
                             "Developer",
                             65000));

        employeeManager.addEmployee(
                new Employee(102,
                             "Kiran",
                             "Tester",
                             50000));

        employeeManager.addEmployee(
                new Employee(103,
                             "Priya",
                             "Manager",
                             85000));

        System.out.println("\nEmployee Records");
        employeeManager.displayEmployees();

        Employee employee =
                employeeManager.searchEmployee(102);

        if (employee != null) {
            System.out.println("\nEmployee Found");
            System.out.println(employee);
        }

        employeeManager.deleteEmployee(102);

        System.out.println("\nAfter Deletion");
        employeeManager.displayEmployees();
    }
}