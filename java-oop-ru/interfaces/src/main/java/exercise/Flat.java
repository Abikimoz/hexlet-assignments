package exercise;

// BEGIN
public class Flat implements Home {
    private double area;        // Жилая площадь квартиры
    private double balconyArea; // Площадь балкона
    private int floor;         // Этаж

    // Конструктор класса
    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    // Метод для получения общей площади квартиры
    @Override
    public double getArea() {
        return area + balconyArea; // Общая площадь = жилая площадь + площадь балкона
    }

    // Метод для сравнения двух объектов недвижимости
    @Override
    public int compareTo(Home another) {
        double currentArea = this.getArea();
        double anotherArea = another.getArea();

        // Сравнение площади
        if (currentArea > anotherArea) {
            return 1;   // Текущая квартира больше
        } else if (currentArea < anotherArea) {
            return -1;  // Текущая квартира меньше
        } else {
            return 0;   // Площади равны
        }
    }

    // Метод для строкового представления квартиры
    @Override
    public String toString() {
        return "Квартира площадью " + getArea() + " метров на " + floor + " этаже";
    }
}
// END
