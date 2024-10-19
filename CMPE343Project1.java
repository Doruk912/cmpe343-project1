import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;

public class CMPE343Project1 {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(
                """
                        *******************************************************************
                                                                                        \s
                                    CMPE343-Object Oriented Programming Languages
                                                     PROJECT 1                   \s
                                                                                        \s
                        *******************************************************************
                                                                                           \s
                                    __        __   _                                            \s
                                    \\ \\      / /__| | ___ ___  _ __ ___   ___                 \s
                                     \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\           \s
                                      \\ V  V /  __/ | (_| (_) | | | | | |  __/                 \s
                                       \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|            \s
                                                                                           \s
                        *******************************************************************
                                                Group Members:                          \s
                                                                                        \s
                                - Doruk Ege Aksu                                        \s
                                - İlhan Uzunoğlu                                        \s
                                - İmran Durmuş                                          \s
                                - Kerem Biçen                                           \s
                                - Tağmaç Zeki Bilen                                     \s
                                                                                        \s
                        *******************************************************************

                        """
        );

        String input;
        while(true){
            System.out.println("""
                    Please select an option:
                    [A] Statistical Information about an array,
                    [B] Matrix Operations,
                    [C] Text Encryption/Decryption,
                    [D] Tic-tac-toe HotSeat,
                    [E] Terminate.\s
                    """);

            input = scanner.nextLine();
            switch (input.toUpperCase()){
                case "A":
                    A();
                    break;
                case "B":
                    System.out.println("Matrix Operations\n");
                    break;
                case "C":
                    textEncryptionDecryptionMenu();
                    break;
                case "D":
                    D();
                    break;
                case "E":
                    System.out.println("Terminating the program");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    public static void textEncryptionDecryptionMenu(){
        clearConsole();
        String input;
        while(true){
            System.out.println("""
                    Text Encryption/Decryption
                    Please select an option:
                    [A] Text Encryption,
                    [B] Text Decryption,
                    [C] Return To Main Menu.\s
                    """);
            String textInput = scanner.nextLine();
            switch (textInput){
                case "A":
                    textEncryption();
                    break;
                case "B":
                    textDecryption();
                    break;
                case "C":
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    public static void textEncryption(){
        System.out.println("Enter Key: ");
        int key = Integer.valueOf(scanner.nextLine());
        while(key <= -27 || key >= 27){
            System.out.println("Invalid key. Please enter a key between -26 and 26.");
            key = Integer.valueOf(scanner.nextLine());
        }
        System.out.println("Enter Message: ");
        String message = scanner.nextLine();

        StringBuilder encryptedMessage = new StringBuilder();
        for(char c : message.toCharArray()){
            if(Character.isUpperCase(c)){
                encryptedMessage.append((char) ((c + key - 65) % 26 + 65));
            } else if(Character.isLowerCase(c)){
                encryptedMessage.append((char) ((c + key - 97) % 26 + 97));
            } else {
                encryptedMessage.append(c);
            }
        }
        System.out.println("Encrypted Message: " + encryptedMessage  + "\n");
    }

    public static void textDecryption(){
        System.out.println("Enter Key: ");
        int key = Integer.valueOf(scanner.nextLine());
        while(key <= -27 || key >= 27){
            System.out.println("Invalid key. Please enter a key between -26 and 26.");
            key = Integer.valueOf(scanner.nextLine());
        }
        System.out.println("Enter Encrypted Message: ");
        String message = scanner.nextLine();

        StringBuilder decryptedMessage = new StringBuilder();
        for(char c : message.toCharArray()){
            if(Character.isUpperCase(c)){
                decryptedMessage.append((char) ((c - key - 65) % 26 + 65));
            } else if(Character.isLowerCase(c)){
                decryptedMessage.append((char) ((c - key - 97) % 26 + 97));
            } else {
                decryptedMessage.append(c);
            }
        }
        System.out.println("Decrypted Message: " + decryptedMessage + "\n");
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void ClearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error while clearing screen");
        }
    }

    public static void ReturnToMenu() {
        System.out.println("\nPress ENTER to return to main menu");
        try {
            System.in.read();
        } catch (IOException ex) {
            System.out.println("Error");
        }
        ClearScreen();
    }

    public static int SafeIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return new Scanner(System.in).nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Wrong data type, try again");
            }
        }
    }

    public static double SafeDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return new Scanner(System.in).nextDouble();
            } catch (InputMismatchException ex) {
                System.out.println("Wrong data type, try again");
            }
        }
    }

    public static void A() {
        ClearScreen();
        System.out.println("Selected option: A\n");
        int size = -1;

        while (size < 1) {
            size = SafeIntInput("Size of the array: ");

            if (size < 1)
                System.out.println("Size cannot be smaller than 1");
        }

        double arithmetic = 0;
        double geometric = 1;

        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = SafeDoubleInput(i + ": ");
            arithmetic += arr[i];
            geometric *= arr[i];
        }

        Arrays.sort(arr);

        double median = (size % 2 == 0) ? (arr[size / 2] + arr[size / 2 - 1]) / 2.0 : arr[size / 2];
        arithmetic /= size;
        geometric = Math.pow(geometric, 1.0 / size);

        System.out.println("Median: " + median);
        System.out.println("Arithmetic Mean: " + arithmetic);
        System.out.println("Geometric Mean: " + geometric);

        ReturnToMenu();
    }

    public static void PrintTable(char[][] table) {
        System.out.println();
        System.out.println("  1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 3; j++)
                System.out.print(table[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    public static char CheckWinner(char[][] table) {
        for (int i = 0; i < 3; i++) {
            if (table[i][0] == 'X' && table[i][1] == 'X' && table[i][2] == 'X')
                return 'X';
            else if (table[i][0] == 'O' && table[i][1] == 'O' && table[i][2] == 'O')
                return 'O';
            else if (table[0][i] == 'X' && table[1][i] == 'X' && table[2][i] == 'X')
                return 'X';
            else if (table[0][i] == 'O' && table[1][i] == 'O' && table[2][i] == 'O')
                return 'O';
        }

        if (table[0][0] == 'X' && table[1][1] == 'X' && table[2][2] == 'X')
            return 'X';
        else if (table[0][0] == 'O' && table[1][1] == 'O' && table[2][2] == 'O')
            return 'O';
        else if (table[0][2] == 'X' && table[1][1] == 'X' && table[2][0] == 'X')
            return 'X';
        else if (table[0][2] == 'O' && table[1][1] == 'O' && table[2][0] == 'O')
            return 'O';

        return '-';
    }

    public static void MakeMove(char[][] table, boolean turn) {
        while (true) {
            System.out.print((turn ? 'X' : 'O') + "'s turn: ");
            Scanner sc = new Scanner(System.in);
            try {
                String move = sc.nextLine();
                int x = move.charAt(0) - '0';
                int y = move.charAt(1) - '0';

                if (x > 3 || x < 1 || y > 3 || y < 1) {
                    System.out.println("Out of bounds");
                    continue;
                }

                x -= 1;
                y -= 1;

                if (table[y][x] != '-') {
                    System.out.println("Not empty");
                    continue;
                }

                table[y][x] = turn ? 'X' : 'O';
                break;
            } catch (StringIndexOutOfBoundsException ex) {
                System.out.println("Unknown move");
            }
        }
    }

    public static void D() {
        char[][] table = new char[3][3];
        boolean turn = true;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                table[i][j] = '-';

        for (int i = 0; i < 9; i++) {
            ClearScreen();
            PrintTable(table);
            MakeMove(table, turn);
            turn = !turn;

            if (CheckWinner(table) == 'X') {
                ClearScreen();
                PrintTable(table);
                System.out.println("X wins");
                ReturnToMenu();
                return;
            } else if (CheckWinner(table) == 'O') {
                ClearScreen();
                PrintTable(table);
                System.out.println("O wins");
                ReturnToMenu();
                return;
            }
        }

        ClearScreen();
        PrintTable(table);
        System.out.println("draw");
        ReturnToMenu();
    }
}
