import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            processUserInput();
        } catch (WrongParsingDataException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void processUserInput() throws WrongParsingDataException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите следующие данные в формате:");
        System.out.println("Фамилия Имя Отчество датарождения(dd.mm.yyyy) 88005553535 пол(m или f)");
        System.out.println("Пример: Фамилия Имя Отчество 31.12.2023 88005553535 m");

        String input = scanner.nextLine();

        UserData userData;
        try {
            userData = UserInputParser.parse(input);
        } finally {
            scanner.close();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userData.getLastName() + ".txt", true))) {
            writer.write(userData.toString());
            writer.newLine();
            writer.flush();
            System.out.println("Данные успешно записаны в файл: " + userData.getLastName() + ".txt");
        } catch (IOException e) {
            throw new WrongParsingDataException("Ошибка записи в файл: " + e.getMessage(), e);
        }
    }
}