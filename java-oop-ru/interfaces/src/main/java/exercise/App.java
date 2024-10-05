package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class App {

    public static List<String> buildApartmentsList(List<Home> properties, int n) {
        // Сортировка объектов по площади
        Collections.sort(properties, Comparator.comparingDouble(Home::getArea));

        // Берем первые n элементов и преобразуем их в строковое представление
        return properties.stream()
            .limit(n)
            .map(Home::toString)
            .collect(Collectors.toList());
    }
}
// END
