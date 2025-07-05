package product;

import core.Product;
import shipping.Shippable;

public class TV extends Product implements Shippable {
    private double weight;

    public TV(String name, double price, int quantityAvailable, double weight) {
        super(name, price, quantityAvailable);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}