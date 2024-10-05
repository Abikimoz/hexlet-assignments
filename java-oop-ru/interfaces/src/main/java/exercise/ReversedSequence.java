package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private final String original;

    // Конструктор принимает исходную строку
    public ReversedSequence(String original) {
        this.original = original;
    }

    // Метод возвращает длину строки
    @Override
    public int length() {
        return original.length();
    }

    // Метод возвращает символ по индексу с конца
    @Override
    public char charAt(int index) {
        return original.charAt(length() - 1 - index);
    }

    // Метод возвращает подстроку в перевернутом виде
    @Override
    public CharSequence subSequence(int start, int end) {
        return new ReversedSequence(original.substring(length() - end, length() - start));
    }

    // Метод возвращает строковое представление в перевернутом виде
    @Override
    public String toString() {
        return new StringBuilder(original).reverse().toString();
    }
}
// END
