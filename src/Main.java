
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static int existMoney = 8_000_000;

    public static ArrayList<Card> list;

    public static Card card;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String number;
        while (true) {
        System.out.print("Input card number (xxxx-xxxx-xxxx-xxxx):");




            number = in.nextLine();

            list = new ArrayList<>();

            ValidCard valid = new ValidCard(number);
            if (!valid.valid()) {
                System.out.println("Invalid card number");
            }else break;
        }

        System.out.print("Input password: ");
        String password = in.nextLine();


        //блокировка после 3 ввода
        if (password.length() != 4) {
            System.out.println("Error password");
            return;
        }

        list = FileCRUD.getCards();

        for (Card c : list) {
            if (c.getNumberCard().equals(number) && c.getPassword().equals(password)) card = c;
        }

        if (card == null) {
            System.out.println("Не удалось найти карту");
            return;
        }

        System.out.println("Авторизация прошла успешно");

        outMenu();

    }


    public static void outMenu() {
        System.out.println("\n1. Проверить баланс карты;");
        System.out.println("2. Снять деньги со счета;");
        System.out.println("3. Пополнить баланс;");
        System.out.println("4. Завершить сеанс.");
        inputMenu();
    }

    public static void inputMenu() {
        Scanner in = new Scanner(System.in);
        System.out.print("\nВведите пункт меню: ");
        int cod = in.nextInt();
        switch (cod) {
            case 1: {
                System.out.println("Баланс карты: " + card.getMoney());
                break;
            }
            case 2: {
                System.out.print("Введите сумму: ");
                try {
                    int m = in.nextInt();

                    if (card.removeMoney(m)) {
                        System.out.println("Вывод произошел успешно");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Введите корректную сумму");
                }

                break;
            }
            case 3: {
                System.out.print("Введите сумму: ");
                try {
                    int m = in.nextInt();
                    if (card.addMoney(m)) System.out.println("Баланс успешно пополнен");
                } catch (NumberFormatException e) {
                    System.out.println("Введите корректную сумму");
                }
                break;
            }
            case 4: {
                FileCRUD.updateFile(list);
                System.exit(0);
                break;
            }
            default: {
                System.out.println("Введите корректный пункт меню.");
                inputMenu();
                break;
            }
        }

        checkExit();
    }

    public static void checkExit() {
        System.out.print("\nЖелаете продолжить? (Да/Нет): ");
        Scanner in = new Scanner(System.in);
        String l = in.nextLine();
        if (l.equalsIgnoreCase("Да")) outMenu();
        if (l.equalsIgnoreCase("Нет")){
            FileCRUD.updateFile(list);
            System.exit(0);
        }


        System.out.println("Введите корректный ответ");
        checkExit();

    }
}
