import java.io.*;
import java.util.Scanner;

public class FileHandler {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== File Handling Menu ===");
            System.out.println("1. Read File");
            System.out.println("2. Write to File");
            System.out.println("3. Modify File");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    readFile();
                    break;
                case 2:
                    writeFile();
                    break;
                case 3:
                    modifyFile();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void readFile() {
        System.out.print("Enter file name to read: ");
        String fileName = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("=== File Content ===");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void writeFile() {
        System.out.print("Enter file name to write: ");
        String fileName = scanner.nextLine();

        System.out.println("Enter text (type 'exit' to finish):");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            String line;
            while (!(line = scanner.nextLine()).equalsIgnoreCase("exit")) {
                bw.write(line);
                bw.newLine();
            }
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void modifyFile() {
        System.out.print("Enter file name to modify: ");
        String fileName = scanner.nextLine();
        System.out.print("Word to replace: ");
        String oldWord = scanner.nextLine();
        System.out.print("New word: ");
        String newWord = scanner.nextLine();

        try {
            File file = new File(fileName);
            String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
            content = content.replaceAll(oldWord, newWord);
            java.nio.file.Files.write(file.toPath(), content.getBytes());
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }
}
