import java.util.Scanner;

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
            switch (input){
                case "A":
                    System.out.println("Statistical Information about an array\n");
                    break;
                case "B":
                    System.out.println("Matrix Operations\n");
                    break;
                case "C":
                    textEncryptionDecryptionMenu();
                    break;
                case "D":
                    System.out.println("Tic-tac-toe HotSeat\n");
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

}
