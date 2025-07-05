package shipping;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ShippingService {
    public void ship(List<ShippableItem> items) {
        Map<String, Integer> quantityMap = new HashMap<>();
        Map<String, Double> totalWeightMap = new HashMap<>();

        for (ShippableItem item : items) {
            String name = item.getName();
            double weight = item.getWeight();
            quantityMap.put(name, quantityMap.getOrDefault(name, 0) + 1);
            totalWeightMap.put(name, totalWeightMap.getOrDefault(name, 0.0) + weight);
        }

        System.out.println("** Shipment notice **");
        double totalPackageWeight = 0;
        for (String name : quantityMap.keySet()) {
            int quantity = quantityMap.get(name);
            double totalWeight = totalWeightMap.get(name);
            System.out.println(quantity + "x " + name + " " + totalWeight + "g");
            totalPackageWeight += totalWeight;
        }
        System.out.println("Total package weight " + (totalPackageWeight / 1000) + "kg");
    }
}
