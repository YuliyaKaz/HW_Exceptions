import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Organizer organizer = new Organizer();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Для выхода введите q");
            System.out.println("Введите Фамилию, Имя, Отчество, Дату рождения, номер телефона, пол");

            try {
                isRunning = organizer.parse(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Недопустимые символы в номере телефона");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}