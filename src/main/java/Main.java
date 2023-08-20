import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("На сколько человек необходимо разделить счёт?");
            String countStr = scanner.next();

            if (!countStr.matches("[1-9]+")) {
                System.out.println("Не корректный ввод");
            } else {
                count = Integer.parseInt(countStr);

                if (count == 1) {
                    System.out.println("Подсчет для одного человека");
                    addProductList(1);

                } else if (count < 1) {
                    System.out.println("Некорректное значение для подсчёта");

                } else {
                    System.out.println("Подсчет для " + count + " человек");
                    addProductList(count);
                }
            }
        }

        while (count < 2);
    }

    private static void addProductList(int count) {


        ArrayList<Product> productList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String nameFormat;
        boolean active = true;

        while (active) {
            System.out.println("Введите название товара");
            String name = scanner.next();
            nameFormat = name.trim().toUpperCase().toLowerCase();
            if (nameFormat.contains("завершить"))
                break;
            if (!nameFormat.chars().allMatch(Character::isLetter)) {
                System.out.println("Не корректное название товара");
            } else {
                do {
                    System.out.println("Введите цену товара в формате рубли.копейки");
                    String str = scanner.next();
                    if (str.trim().toUpperCase().toLowerCase().contains("завершить")) {
                        active = false;
                        break;
                    }
                    if (!str.matches("[0-9]+\\.[0-9]+")) {
                        System.out.println("Не корректная цена товара");
                    } else {
                        double price = Double.parseDouble(str);

                        Product product = new Product(nameFormat, price);

                        productList.add(product);
                        System.out.println("Товар " + product.name + " с ценой " + product.price + " успешно добавлен в корзину");
                        System.out.println("Хотите добавить еще?");
                        break;
                    }
                } while (true);
            }
        }
        System.out.println("===========================");
        sum(count, productList);
    }

    private static void sum(int count, ArrayList<Product> productList) {
        double sum = productList.stream().mapToDouble(product1 -> product1.price).sum();
        System.out.println("Список продуктов: ");
        productList.forEach((s) -> System.out.println(s.name + " : " + s.price));
        System.out.println("Итоговая сумма: " + parseRubCase(sum));
        System.out.println("Итоговая сумма на каждого человека: " + parseRubCase(sum / count));
        System.out.println("===========================");
    }

    public static String parseRubCase(double price) {
        int inInt = (int) price;
        String cased;
        if (inInt % 100 / 10 == 1 || inInt % 10 >= 5 || inInt % 10 == 0)
            cased = "рублей";
        else if (inInt % 10 == 1)
            cased = "рубль";
        else
            cased = "рубля";
        return String.format("%.2f", price) + " " + cased;
    }
}