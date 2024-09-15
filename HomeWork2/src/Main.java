import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:mysql:///mobiles";
    private static final String username = "root";
    private static final String password = "4544120";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n***ДОБРО ПОЖАЛОВАТЬ***");
                System.out.println("1. Отображение всего содержимого таблицы из базы данных");
                System.out.println("2. Показать всех производителей автомобилей");
                System.out.println("3. Показать автомобили указанного года выпуска");
                System.out.println("4. Перечислить автомобили конкретного производителя");
                System.out.println("5. Показать автомобили указанного цвета");
                System.out.println("6. Отобразить автомобили указанного объема двигателя");
                System.out.println("7. Отобразить автомобили указанного типа автомобиля");
                System.out.println("0. Выход");

                System.out.print("\nВаш выбор: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 0:
                        System.out.println("Выход из системы");
                        connection.close();
                        scanner.close();
                        return;
                    case 1:
                        printTable(connection);
                        break;
                    case 2:
                        listProducers(connection);
                        break;
                    case 3:
                        listAutomobilesByYear(connection, scanner);
                        break;
                    case 4:
                        listAutomobilesByProducer(connection, scanner);
                        break;
                    case 5:
                        listAutomobilesByColour(connection, scanner);
                        break;
                    case 6:
                        listAutomobilesByEngineVolume(connection, scanner);
                        break;
                    case 7:
                        listAutomobilesByType(connection, scanner);
                        break;
                    default:
                        System.out.println("Неверный ввод...\n");
                        break;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printTable(Connection connection) {
        String query = "select * from cars";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String producer = resultSet.getString("producer");
                String name = resultSet.getString("name");
                double engineVolume = resultSet.getDouble("engine_volume");
                int releaseYear = resultSet.getInt("realise_year");
                String colour = resultSet.getString("color");
                String carType = resultSet.getString("car_type");
                System.out.printf("| %d | %s | %s | %f | %d | %s | %s |\n", id, producer, name, engineVolume, releaseYear, colour, carType);
            }
        } catch (SQLException e) {
            System.out.println("Не удалось вывести содержимое таблицы");
            e.printStackTrace();
        }
    }

    public static void listProducers(Connection connection) {
        String query = "select producer from cars group by producer";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String producer = resultSet.getString("producer");
                System.out.println(producer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listAutomobilesByYear(Connection connection, Scanner scanner) {
        String query = "select * from cars where realise_year = ?";
        System.out.print("Введите год выпуска: ");
        int releaseYear = scanner.nextInt();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, releaseYear);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String producer = resultSet.getString("producer");
                String name = resultSet.getString("name");
                double engineVolume = resultSet.getDouble("engine_volume");
                String colour = resultSet.getString("color");
                String carType = resultSet.getString("car_type");
                System.out.printf("| %d | %s | %s | %f | %d | %s | %s |\n", id, producer, name, engineVolume, releaseYear, colour, carType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listAutomobilesByProducer(Connection connection, Scanner scanner) {
        scanner.nextLine();
        String query = "select * from cars where producer = ?";
        System.out.print("Введите производителя: ");
        String producer = scanner.nextLine();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, producer);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double engineVolume = resultSet.getDouble("engine_volume");
                int releaseYear = resultSet.getInt("realise_year");
                String colour = resultSet.getString("color");
                String carType = resultSet.getString("car_type");
                System.out.printf("| %d |  %s | %f | %d | %s | %s |\n", id, name, engineVolume, releaseYear, colour, carType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listAutomobilesByColour(Connection connection, Scanner scanner) {
        scanner.nextLine();
        String query = "select * from cars where color = ?";
        System.out.print("Введите цвет: ");
        String colour = scanner.nextLine();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, colour);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String producer = resultSet.getString("producer");
                String name = resultSet.getString("name");
                double engineVolume = resultSet.getDouble("engine_volume");
                int releaseYear = resultSet.getInt("realise_year");
                String carType = resultSet.getString("car_type");
                System.out.printf("| %d | %s | %s | %f | %d | %s | %s |\n", id, producer, name, engineVolume, releaseYear, colour, carType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listAutomobilesByEngineVolume(Connection connection, Scanner scanner) {
        String query = "select * from cars where engine_volume = ?";
        System.out.print("Введите объем двигателя: ");
        double engineVolume = scanner.nextDouble();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, engineVolume);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String producer = resultSet.getString("producer");
                String name = resultSet.getString("name");
                int releaseYear = resultSet.getInt("realise_year");
                String colour = resultSet.getString("color");
                String carType = resultSet.getString("car_type");
                System.out.printf("| %d | %s | %s | %f | %d | %s | %s |\n", id, producer, name, engineVolume, releaseYear, colour, carType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listAutomobilesByType(Connection connection, Scanner scanner) {
        scanner.nextLine();
        String query = "select * from cars where car_type = ?";
        System.out.print("Введите тип автомобиля: ");
        String carType = scanner.nextLine();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, carType);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String producer = resultSet.getString("producer");
                String name = resultSet.getString("name");
                double engineVolume = resultSet.getDouble("engine_volume");
                int releaseYear = resultSet.getInt("realise_year");
                String colour = resultSet.getString("color");
                System.out.printf("| %d | %s | %s | %f | %d | %s | %s |\n", id, producer, name, engineVolume, releaseYear, colour, carType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}