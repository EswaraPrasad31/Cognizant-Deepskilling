public class Employee {

    private int employeeId;
    private String employeeName;
    private String position;
    private double salary;

    public Employee(int employeeId,
                    String employeeName,
                    String position,
                    double salary) {

        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.position = position;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {

        return "Employee ID: " + employeeId +
               ", Name: " + employeeName +
               ", Position: " + position +
               ", Salary: ₹" + salary;
    }
}