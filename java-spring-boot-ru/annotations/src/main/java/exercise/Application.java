package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        Class<Address> addressClass = Address.class;

        // Получаем все методы класса Address
        Method[] methods = addressClass.getDeclaredMethods();

        // Проходим по всем методам и проверяем наличие аннотации Inspect
        for (Method method : methods) {
            if (method.isAnnotationPresent(Inspect.class)) {
                // Получаем имя метода
                String methodName = method.getName();
                // Получаем тип возвращаемого значения метода
                String returnType = method.getReturnType().getSimpleName();
                // Выводим информацию о методе
                System.out.println("Method " + methodName + " returns a value of type " + returnType);
            }
        }

        // Для демонстрации работы можно создать экземпляр класса Address и вызвать его методы
        System.out.println("City: " + address.getCity());
        System.out.println("Postal Code: " + address.getPostalCode());
        // END
    }
}
