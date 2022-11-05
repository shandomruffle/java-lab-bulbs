import java.util.*;

public class CollectionProcessor {
    public static <T extends LightingBulb>
    List<T> sortByCost(Collection<T> list) {
        return list.stream().sorted(Comparator.comparing(T::getCost)).toList();
    }

    public static <T extends LightingBulb>
    List<T> sortByCostOverPowerDescending(Collection<T> list) {
        return list.stream()
                .sorted(Comparator.comparing(
                        (T b) -> -(double)b.getCost() / b.getPower()))
                .toList();
    }

    public static <T extends LightingBulb>
    List<String> distinctProducersStartingWithC(Collection<T> list) {
        return list.stream()
                .map(T::getProducer)
                .filter(p -> p.startsWith("C"))
                .distinct()
                .toList();
    }

    public static <T extends LightingBulb>
    double averageCostByProducer(Collection<T> list, String producer) {
        var optional = list.stream()
                .filter(b -> b.getProducer().equals(producer))
                .mapToDouble(T::getCost)
                .average();
        if (optional.isEmpty()) {
            throw new RuntimeException("Not found producer: " + producer);
        }
        return optional.getAsDouble();
    }
}
