package product;

import core.Product;
import shipping.Shippable;
import util.Expirable;

import java.time.LocalDate;


public class Cheese extends Product implements Expirable, Shippable {
    private LocalDate expirationDate;
    private double weight;

    public Cheese(String name, double price, int quantityAvailable, LocalDate expirationDate, double weight) {
        super(name, price, quantityAvailable);
        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}