package h11;

import org.jetbrains.annotations.Nullable;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * A {@link Warehouse} manages a list of {@link Product}s and provides methods to query information about them.
 * It also contains a maximum capacity, which it can hold and a current capacity, which specifies how many {@link Product}s
 * are in the {@link Warehouse} currently
 */
public class Warehouse {

    @DoNotTouch
    public List<Product> products = new ArrayList<>();
    @DoNotTouch
    private int maxCapacity = 100;
    @DoNotTouch
    private int currentCapacity = 0;

    @DoNotTouch
    public Warehouse(List<Product> products) {
        this.products = products;
    }

    /**
     * Gets the price of the product
     * @param prod      product whose price should read
     * @return          returns the price
     */
    @StudentImplementationRequired
    public double getPrice(@Nullable Product prod) {
        // TODO H2.1
        Optional<Double> productOptional = Optional.ofNullable(prod.price());
        return productOptional.orElse(0.0);
    }

    @DoNotTouch
    public List<Product> getProducts() {
        return this.products;
    }

    /**
     * Gets a List of filtered products
     * @param predicate     condition for filter
     * @return              List of Product
     */
    @StudentImplementationRequired
    public List<Product> getProducts(Predicate<? super Product> predicate) {
        // TODO H2.2
        return products.stream()
            .filter(predicate)
            .toList();
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }


    @DoNotTouch
    public int getCurrentCapacity() {
        return currentCapacity;
    }

    @DoNotTouch
    public int getMaxCapacity() {
        return maxCapacity;
    }

    @DoNotTouch
    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    /**
     * Gets the total quantity of a specific product
     * @param product   product that is to be searched
     * @return      total quantity
     */
    @StudentImplementationRequired
    public long getTotalQuantityOfProduct(Product product) {
        // TODO H2.2
        return products.stream()
            .filter(product1 -> product1.equals(product))
            .count();
    }

    /**
     * Gets the price of all products
     * @return total price
     */
    @StudentImplementationRequired
    public double getTotalPrice() {
        // TODO H2.3
        return (double) products.stream()
            .map(Product::price)
            .count();
    }

    /**
     * Adds a list of products to a given list
     * @param product   type of product
     * @param numberOfProducts  number of products that should be added
     */
    @StudentImplementationRequired
    public void addProducts(Product product, int numberOfProducts) {
        // TODO H2.5
        products.addAll(generateProducts(product.type(), product.price(), product.name())
            .limit(numberOfProducts)
            .toList());
    }

    /**
     * generates a new product
     * @param typ type of product
     * @param price price of product
     * @param name  name of the product
     * @return  infinite stream of generated product
     */
    @StudentImplementationRequired
    public Stream<Product> generateProducts(ProductType typ, double price, String name) {
        // TODO H2.4
        return Stream.generate(() -> new Product(typ, price, 1, name));
    }
}
