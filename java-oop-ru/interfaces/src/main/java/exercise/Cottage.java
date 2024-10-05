package exercise;

// BEGIN
public class Cottage implements Home {
    private double area;      // Общая площадь коттеджа
    private int floorCount;   // Количество этажей

    // Конструктор класса
    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    // Метод для получения общей площади коттеджа
    @Override
    public double getArea() {
        return area; // Площадь коттеджа
    }

    // Метод для сравнения двух объектов недвижимости
    @Override
    public int compareTo(Home another) {
        double currentArea = this.getArea();
        double anotherArea = another.getArea();

        // Сравнение площади
        if (currentArea > anotherArea) {
            return 1;   // Текущий коттедж больше
        } else if (currentArea < anotherArea) {
            return -1;  // Текущий коттедж меньше
        } else {
            return 0;   // Площади равны
        }
    }

    // Метод для строкового представления коттеджа
    @Override
    public String toString() {
        return floorCount + " этажный коттедж площадью " + area + " метров";
    }
}
// END
