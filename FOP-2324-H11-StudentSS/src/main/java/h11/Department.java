package h11;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;
import static org.tudalgo.algoutils.student.Student.crash;
import java.util.List;

/**
 * A {@link Department} manages a list of {@link Employee}s and provides methods to query information about them.
 *
 * @param employees The employees of this department.
 */
public record Department(@DoNotTouch List<Employee> employees) {

    /**
     * Returns the employees of this department.
     *
     * @return the employees
     */
    @Override
    public List<Employee> employees() {
        return this.employees;
    }

    /**
     * Gets a list of all positions of every employee in the department
     * @return list of positions
     */
    @StudentImplementationRequired
    public List<Position> getListOfPositionsInDepartment() {
        // TODO H1.1
        return employees.stream()
            .map(Employee::getPosition)
            .distinct()
            .toList();
    }

    /**
     * gets a list of employees in a specific position
     * @param position  position that is to be searched for
     * @return list of employees
     */
    @StudentImplementationRequired
    public List<Employee> filterEmployeeByPosition(Position position) {
        // TODO H1.2
        return employees.stream()
            .filter(employee -> employee.getPosition() == position)
            .toList();
    }

    /**
     * gets a number of employees filtered after a chosen salary
     * @param salary    salary after which should filtered
     * @return  number employees filtered after salary
     */
    @StudentImplementationRequired
    public long getNumberOfEmployeesBySalary(double salary) {
        // TODO H1.3
        return employees.stream()
            .filter(employee -> employee.getSalary() >= salary)
            .count();
    }

    /**
     * adjusts salary of all employees
     * @param amount    amount of money that should be added or remmoved
     * @param increase  increases salary if true, decreases salary if false
     */
    @StudentImplementationRequired
    public void adjustSalary(double amount, boolean increase) {
        // TODO H1.4
        if (increase)
            employees.stream()
                .forEach(employee -> employee.setSalary(employee.getSalary() + amount));
        else
            employees.stream()
                .forEach(employee -> employee.setSalary(employee.getSalary() - amount));
    }
}
