package shipping;

public class ProductShipmentItem implements ShippableItem {
    private String name;
    private double weight;

    public ProductShipmentItem(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}