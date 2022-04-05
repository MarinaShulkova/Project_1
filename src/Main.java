import javax.naming.PartialResultException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[][] array = new String[15][2];
        int volume = 0;
        int menu_item = 0;

        while ((menu_item = menu()) != 0) {
// работа меню
            switch (menu_item) {
                case 1:
                    volume = add_expenses(volume, array);
                    break;
                case 2:
                    array = delete_expenses(array);
                    print_array(array);
                    break;
                case 3:
                    System.out.println("Your balance: ");
                    System.out.println(Balance(array));
                    break;
                case 4:
                    print_array(array);
                    break;
                case 5:
                    category(array);
                    break;
                default:
                    System.out.println("Enter number from 0 to 5");
                    break;
            }
        }
        System.out.println("Goodbye");
        System.exit(404);

    }

    //Вывод меню
    public static int menu () {
        System.out.println("1 Добавить трату/пополнение \n" +
                "2 Удалить трату/пополнение \n" +
                "3 Узнать текущий баланс \n" +
                "4 Вывести все траты/пополнения \n" +
                "5 Вывести траты по определенной категории \n" +
                "0 Выход");
        System.out.println("Choose menu item: ");
        Scanner scr = new Scanner(System.in);
        int menu_item = scr.nextInt();
        return (menu_item);
    }

    //Процедура добавления траты/пополнения
    public static int add_expenses ( int volume, String[][] array){
        System.out.println("Enter expenses: ");
        Scanner scr = new Scanner(System.in);
        String expenses = scr.nextLine();
        array[volume] = expenses.split(" ");
        if ((Integer.parseInt(array[volume][1]) + Balance(array)) < 0) {
            System.out.println("Not enough money");
            return volume;
        }return (volume + 1);
    }
    //Процедура удаления
    public static String[][] delete_expenses (String[][]array){
        Scanner scr = new Scanner(System.in);
        System.out.println("Enter number of string to delete: ");
        int item = scr.nextInt();
        for (int i = item; i < array.length; i++) {
            array[i - 1][0] = array[i][0];
            array[i - 1][1] = array[i][1];

            if (array[i][0] == null) break;
        }
        return (array);
    }
    // Печать массива
    public static void print_array (String[][]array){
        System.out.println("Your expenses: ");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == null) break;
                System.out.print(array[i][j] + " ");
            }
            if (array[i][0] == null) break;
            else System.out.println();
        }
    }
    // Вывод суммы затрат по категории
    public static void category (String[][]array){
        Scanner scr = new Scanner(System.in);
        System.out.print("Enter category of expenses: ");
        String type = scr.next();
        Boolean flag = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i][0] == null) break;
            if (array[i][0].equals(type)) {
                System.out.print(array[i][0] + " ");
                System.out.println(array[i][1]);
                flag = true;
            }
        }
        if (!flag) System.out.println("No such category of expenses was found");
    }
    // Подсчет баланса
    public static int Balance (String[][]array){
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i][1] == null) break;
            sum += Integer.parseInt(array[i][1]);
        }
        return sum;
    }

}
