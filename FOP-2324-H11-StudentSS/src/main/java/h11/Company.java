package h11;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * A {@link Company} manages a list of {@link Department}s and a list of {@link Warehouse}s and provides
 * methods to query information about them.
 *
 * @param departments The departments of this company.
 * @param warehouses  The warehouses of this company.
 */
public record Company(@DoNotTouch List<Department> departments, @DoNotTouch List<Warehouse> warehouses) {

    /**
     * Creates a new {@link Company} with the given departments and warehouses.
     *
     * @param departments the departments
     * @param warehouses  the warehouses
     */
    @DoNotTouch
    public Company {
    }

    /**
     * gets a list of all employees of all departments
     * @return  list of all employees
     */
    @StudentImplementationRequired
    public List<Employee> getListOfAllEmployee() {
        // TODO H3.1
        return departments.stream()
            .flatMap(department -> department.employees().stream())
            .toList();
    }

    /**
     * gets total quantity of a specific product in every warehouse
     * @param product
     * @return number of the quantity
     */
    @StudentImplementationRequired
    public long getQuantityOfProduct(Product product) {
        // TODO H3.2
        return warehouses.stream()
            .flatMap(warehouse -> warehouse.products.stream())
            .filter(product1 -> product1.equals(product))
            .mapToInt(Product::quantity)
            .count();
    }

    /**
     * gets name of products that were filtered
     * @param predicates    condition for filter
     * @return  list of string with every product name
     */
    @StudentImplementationRequired
    public List<String> getFilteredProductNames(List<Predicate<Product>> predicates) {
        // TODO H3.3
        return warehouses.stream()
            .flatMap(warehouse -> warehouse.products.stream())
            .filter(predicates.stream().reduce(product -> true, Predicate::and))
            .map(Product::name)
            .toList();
    }

    /**
     * gets a list of products in a specific price range
     * @param low   lower limit of price range
     * @param high  higher limit of price range
     * @return list of products within price range
     */
    @StudentImplementationRequired
    public List<Product> priceRange(double low, double high) {
        // TODO H3.4
        return warehouses.stream()
            .flatMap(warehouse -> warehouse.products.stream())
            .filter(product -> product.price() <= high && product.price() >= low)
            .sorted(Comparator.comparing(Product::price))
            .toList();
    }

    /**
     * gets a list of all employees sorted after their last name
     * @return list of strings with names of every employee
     */
    @StudentImplementationRequired
    public List<String> getEmployeesSortedByName() {
        // TODO H3.5
        return departments.stream()
            .flatMap(department -> department.employees().stream())
            .map(Employee::getName)
            .map(name -> name.split(" "))
            .map(names -> names[1] + ", " + names[0])
            .sorted(Comparator.naturalOrder())
            .toList();
    }

    /**
     * filters list of products and returns a list limited to a specific number of einträge
     * @param type type of product that is searched for
     * @param numberOfProducts  number of products that should be listed
     * @return  list of string with product name and price
     */
    @StudentImplementationRequired
    public List<String> getAllProductsByType(ProductType type, int numberOfProducts) {
        // TODO H3.6
        return warehouses.stream()
            .flatMap(warehouse -> warehouse.products.stream())
            .filter(product -> product.type().equals(type))
            .sorted(Comparator.comparingDouble(Product::price).reversed())
            .limit(numberOfProducts)
            .map(product -> product.name() + ": " + product.price())
            .toList();
    }

    @Override
    public List<Department> departments() {
        return departments;
    }

    @Override
    public List<Warehouse> warehouses() {
        return warehouses;
    }
}
