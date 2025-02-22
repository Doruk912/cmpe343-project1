import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Main class for Project 1
 */
public class CMPE343Project1 {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Main function which prints the ASCII art and waits for an input from the user
     * @param args Command line arguments
     */
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
                    matrixOperationsMenu();
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

    /**
     * Displays the menu for text encryption and decryption
     */
    public static void textEncryptionDecryptionMenu(){
        ClearScreen();
        while(true){
            System.out.println("""
                    Selected option: [C] Text Encryption/Decryption
                    
                    Please select an option:
                    [A] Text Encryption,
                    [B] Text Decryption,
                    [C] Return To Main Menu.\s
                    """);
            String textInput = scanner.nextLine();
            switch (textInput.toUpperCase()){
                case "A":
                    textEncryption();
                    break;
                case "B":
                    textDecryption();
                    break;
                case "C":
                    ClearScreen();
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    /**
     * Encrypts text
     */
    public static void textEncryption(){
        int key = SafeIntInput("Enter Key: ");
        while(key <= -27 || key >= 27){
            key = SafeIntInput("Invalid key. Please enter a key between -26 and 26.\n");
        }
        System.out.println("Enter Message: ");
        String message = scanner.nextLine();

        StringBuilder encryptedMessage = new StringBuilder();
        for(char c : message.toCharArray()){
            if(Character.isUpperCase(c)){
                encryptedMessage.append((char) ((c + key - 65 + 26) % 26 + 65));
            } else if(Character.isLowerCase(c)){
                encryptedMessage.append((char) ((c + key - 97 + 26) % 26 + 97));
            } else {
                encryptedMessage.append(c);
            }
        }
        System.out.println("Encrypted Message: " + encryptedMessage);
        ReturnToMenu();
    }

    /**
     * Decrypts text
     */
    public static void textDecryption(){
        int key = SafeIntInput("Enter Key: ");
        while(key <= -27 || key >= 27){
            key = SafeIntInput("Invalid key. Please enter a key between -26 and 26.\n");
        }
        System.out.println("Enter Encrypted Message: ");
        String message = scanner.nextLine();

        StringBuilder decryptedMessage = new StringBuilder();
        for(char c : message.toCharArray()){
            if(Character.isUpperCase(c)){
                decryptedMessage.append((char) ((c - key - 65 + 26) % 26 + 65));
            } else if(Character.isLowerCase(c)){
                decryptedMessage.append((char) ((c - key - 97 + 26) % 26 + 97));
            } else {
                decryptedMessage.append(c);
            }
        }
        System.out.println("Decrypted Message: " + decryptedMessage);
        ReturnToMenu();
    }

    /**
     * Clears console with printing new line
     */
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * Clears the console by running the relevant command for the detected operating system
     */
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

    /**
     * Waits ENTER key from the user and clears the screen which prepares fresh start for the main loop
     */
    public static void ReturnToMenu() {
        System.out.println("\nPress ENTER to return to menu");
        scanner.nextLine();
        ClearScreen();
    }

    /**
     * Prompts integer input until no errors are encountered
     * @param prompt Prompt which is going to be printed on the screen
     * @return Integer value from the user
     */
    public static int SafeIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = scanner.nextLine();
            if (response.isBlank()) {
                System.out.println("Empty input. Please try again.");
                continue;
            }
            try {
                return Integer.parseInt(response);
            } catch (NumberFormatException ex) {
                System.out.println("Wrong data type, try again");
            }
        }
    }

    /**
     * Prompts positive integer input until no errors are encountered
     * @param prompt Prompt which is going to be printed on the screen
     * @return Positive integer value from the user
     */
    public static int safePositiveIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = scanner.nextLine();
            if (response.isBlank()) {
                System.out.println("Empty input. Please try again.");
                continue;
            }
            try {
                int num = Integer.parseInt(response);
                if(num <= 0){
                    System.out.println("Number should be greater than 0. Please try again.");
                    continue;
                }
                return Integer.parseInt(response);
            } catch (NumberFormatException ex) {
                System.out.println("Wrong data type, try again");
            }
        }
    }

    /**
     * Prompts double input until no errors are encountered
     * @param prompt Prompt which is going to be printed on the screen
     * @return Double value from the user
     */
    public static double SafeDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = scanner.nextLine();
            if (response.isBlank()) {
                System.out.println("Empty input. Please try again.");
                continue;
            }
            try {
                return Double.parseDouble(response);
            } catch (NumberFormatException ex) {
                System.out.println("Wrong data type, try again");
            }
        }
    }

    /**
     * Recursive calculation of harmonic mean
     * @param size The size of the array. Is used to monitor the remaining elements of the array to process. It is used as the condition to stop the recursion.
     * @param arr The array itself that we are calculating the harmonic mean of
     * @param reciprocalSum To calculate the harmonic mean we need to divide the total count of the number by the reciprocals of the numbers themselves summed
     * @return Returns the results of the method to the method itself (recursion) until the size returned is equal to 0 which then returns the harmonic mean itself.
     */
    public static double harmonicMeanRecursive (int size,double[] arr, double reciprocalSum){
        if (size == 0) {
            return arr.length / reciprocalSum;
        }
        return harmonicMeanRecursive(size-1,arr,reciprocalSum+1/ arr[arr.length-size]);
    }
    /**
     * Statistical information of an array
     */
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
        double reciprocalSum = 0;

        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = SafeDoubleInput(i + 1 + ": ");
            arithmetic += arr[i];
            geometric *= arr[i];
        }

        Arrays.sort(arr);

        double median = (size % 2 == 0) ? (arr[size / 2] + arr[size / 2 - 1]) / 2.0 : arr[size / 2];
        arithmetic /= size;
        geometric = Math.pow(geometric, 1.0 / size);
        double harmonic = harmonicMeanRecursive(size,arr, reciprocalSum);
        System.out.println("\nMedian: " + median);
        System.out.println("Arithmetic Mean: " + arithmetic);
        System.out.println("Geometric Mean: " + geometric);
        System.out.println("Harmonic Mean: " + harmonic);

        ReturnToMenu();
    }

    /**
     * Print tic-tac-toe game board
     * @param table Tic-tac-toe game board
     */
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

    /**
     * Check tic-tac-toe table for winner and return if there is a winner
     * @param table Tic-tac-toe game board
     * @return 'X' if X wins, 'O' if O wins, '-' if there is no winner
     */
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

    /**
     * Prompt user to make move on the tic-tac-toe table
     * @param table Tic-tac-toe game board
     * @param turn Tells the function which player should make the move
     */
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

    /**
     * Tic-tac-toe game
     */
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

    /**
     * Display the menu for matrix operations
     */
    public static void matrixOperationsMenu(){
        ClearScreen();
        while(true){
            System.out.println("""
                    Selected option: [B] Matrix Operations
                    
                    Please select an option:
                    [A] Transpose of a Matrix,
                    [B] Inverse of a Matrix,
                    [C] Matrix Multiplication,
                    [D] Element-wise Multiplication,
                    [E] Return To Main Menu.\s
                    """);
            String input = scanner.nextLine();
            switch (input.toUpperCase()){
                case "A":
                    transpose();
                    break;
                case "B":
                    inverse();
                    break;
                case "C":
                    multiplication();
                    break;
                case "D":
                    elementWiseMultiplication();
                    break;
                case "E":
                    ClearScreen();
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    /**
     * Transpose of a matrix
     */
    public static void transpose(){
        System.out.println("Enter the number of rows and columns of the matrix: ");
        int rows = safePositiveIntInput("Rows: ");
        int columns = safePositiveIntInput("Columns: ");

        int[][] matrix = new int[rows][columns];
        System.out.println("Enter the elements of the matrix: ");
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                matrix[i][j] = SafeIntInput("Element at [" + (i + 1) + "][" + (j + 1) + "]: ");
            }
        }

        System.out.println("\nOriginal Matrix: ");
        printMatrix(matrix);

        int[][] transposedMatrix = new int[columns][rows];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                transposedMatrix[j][i] = matrix[i][j];
            }
        }

        System.out.println("\nTransposed Matrix: ");
        printMatrix(transposedMatrix);

        ReturnToMenu();
    }

    /**
     * Inverse of a matrix
     */
    public static void inverse() {
    System.out.println("Enter the size of the square matrix (n x n): ");
    int size = safePositiveIntInput("Size: ");

    if (size <= 1) {
        System.out.println("Matrix should be at least 2x2.");
        ReturnToMenu();
        return;
    }

    double[][] matrix = new double[size][size];
    System.out.println("Enter the elements of the matrix: ");
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            matrix[i][j] = SafeDoubleInput("Element at [" + (i + 1) + "][" + (j + 1) + "]: ");
        }
    }

    System.out.println("\nOriginal Matrix: ");
    printDoubleMatrix(matrix);

    if (!isInvertible(matrix, size)) {
        System.out.println("\nThe matrix is non-invertible (determinant is 0).");
        ReturnToMenu();
        return;
    }

    double[][] inverseMatrix = gaussianEliminationInverse(matrix, size);

    System.out.println("\nInverse Matrix: ");
    printDoubleMatrix(inverseMatrix);

    ReturnToMenu();
}


/**
 * Gauss elimination inverse on matrix
 * @param matrix Matrix to invert
 * @param size Row or column length of the matrix
 * @return Resulting matrix
 */
public static double[][] gaussianEliminationInverse(double[][] matrix, int size) {
    double[][] identityMatrix = new double[size][size];
    for (int i = 0; i < size; i++) {
        identityMatrix[i][i] = 1;
    }

    double[][] augmentedMatrix = new double[size][2 * size];
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            augmentedMatrix[i][j] = matrix[i][j];
            augmentedMatrix[i][j + size] = identityMatrix[i][j];
        }
    }

    for (int i = 0; i < size; i++) {
        if (augmentedMatrix[i][i] == 0) {
            for (int k = i + 1; k < size; k++) {
                if (augmentedMatrix[k][i] != 0) {
                    double[] temp = augmentedMatrix[i];
                    augmentedMatrix[i] = augmentedMatrix[k];
                    augmentedMatrix[k] = temp;
                    break;
                }
            }
        }

        double diagonalValue = augmentedMatrix[i][i];
        for (int j = 0; j < 2 * size; j++) {
            augmentedMatrix[i][j] /= diagonalValue;
        }

        for (int k = 0; k < size; k++) {
            if (k != i) {
                double factor = augmentedMatrix[k][i];
                for (int j = 0; j < 2 * size; j++) {
                    augmentedMatrix[k][j] -= factor * augmentedMatrix[i][j];
                }
            }
        }
    }

    double[][] inverseMatrix = new double[size][size];
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            inverseMatrix[i][j] = augmentedMatrix[i][j + size];
        }
    }

    return inverseMatrix;
}

/**
 * Check if a matrix is invertible
 * @param matrix Matrix to invert
 * @param size Row or column length of the matrix
 * @return Returns true if the matrix is invertible
 */
public static boolean isInvertible(double[][] matrix, int size) {
    double determinant = computeDeterminant(matrix, size);
    return determinant != 0;
}

/**
 * Compute determinant
 * @param matrix Matrix to compute
 * @param size Row or column length of the matrix
 * @return Determinant of the matrix
 */
public static double computeDeterminant(double[][] matrix, int size) {
    if (size == 2) {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    double determinant = 0;
    for (int i = 0; i < size; i++) {
        double[][] subMatrix = new double[size - 1][size - 1];
        for (int j = 1; j < size; j++) {
            int subCol = 0;
            for (int k = 0; k < size; k++) {
                if (k == i) continue;
                subMatrix[j - 1][subCol++] = matrix[j][k];
            }
        }
        determinant += Math.pow(-1, i) * matrix[0][i] * computeDeterminant(subMatrix, size - 1);
    }
    return determinant;
}

/**
 * Print the double matrix
 * @param matrix Matrix to print
 */
public static void printDoubleMatrix(double[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
            System.out.print(matrix[i][j] + " ");
        }
        System.out.println();
    }
}


    /**
     * Matrix multiplication
     */
    public static void multiplication(){
        System.out.println("Multiplication");
        System.out.println("Enter the number of rows and columns of the first matrix: ");
        int rows1 = safePositiveIntInput("Rows: ");
        int columns1 = safePositiveIntInput("Columns: ");

        int[][] matrix1 = new int[rows1][columns1];
        System.out.println("Enter the elements of the first matrix: ");

        for(int i=0; i<rows1; i++)
        {
            for(int j=0; j<columns1; j++)
            {
                matrix1[i][j] = SafeIntInput("Element at [" + (i+1) + "][" + (j+1) + "]: ");
            }
        }
        printMatrix(matrix1);


        System.out.println("Enter the number of rows and columns of the second matrix: ");
        int rows2 = safePositiveIntInput("Rows: ");
        while(rows2 != columns1){
            System.out.println("Rows of the second matrix can't be different from the columns of the first matrix.");
            rows2 = safePositiveIntInput("Rows: ");
        }
        int columns2 = safePositiveIntInput("Columns: ");

        int[][] matrix2 = new int[rows2][columns2];
        System.out.println("Enter the elements of the second matrix: ");

        for(int i=0; i<rows2; i++)
        {
            for(int j=0; j<columns2; j++)
            {
                matrix2[i][j] = SafeIntInput("Element at [" + (i+1) + "][" + (j+1) + "]: ");
            }
        }

        System.out.println("\nYour Second Matrix: ");
        printMatrix(matrix2);

        int[][] matrix3 = new int [rows1][columns2];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                int sum = 0;
                for (int k = 0; k < columns1; k++) {
                    sum += matrix1[i][k] * matrix2[k][j];
                }
                matrix3[i][j] = sum;
            }
        }

        System.out.println("\nThe result of the multiplication: ");
        printMatrix(matrix3);

        ReturnToMenu();
    }

    /**
     * Element-wise multiplication
     */
    public static void elementWiseMultiplication(){
        System.out.println("Enter the number of rows and columns of the matrices: ");
        int rows = safePositiveIntInput("Rows: ");
        int columns = safePositiveIntInput("Columns: ");

        int[][] matrix1 = new int[rows][columns];
        System.out.println("Enter the elements of the first matrix: ");

        for(int i=0; i<rows; i++)
        {
            for(int j=0; j<columns; j++)
            {
                matrix1[i][j] = SafeIntInput("Element at [" + (i+1) + "][" + (j+1) + "]: ");
            }
        }

        System.out.println("\nYour First Matrix: ");
        printMatrix(matrix1);

        int[][] matrix2 = new int[rows][columns];
        System.out.println("Enter the elements of the second matrix: ");
        for(int i=0; i<rows; i++)
        {
            for(int j=0; j<columns; j++)
            {
                matrix2[i][j] = SafeIntInput("Element at [" + (i+1) + "][" + (j+1) + "]: ");
            }
        }

        System.out.println("\nYour Second Matrix: ");
        printMatrix(matrix2);

        int[][] result = new int[rows][columns];
        for (int i=0; i<rows; i++)
        {
            for (int j=0; j<columns; j++) 
            {
                result[i][j] = matrix1[i][j] * matrix2[i][j];
            }
        }

        System.out.println("Result of Element-wise Multiplication: ");
        printMatrix(result);
        ReturnToMenu();
    }

    /**
     * Prints the matrix
     * @param matrix Integer matrix to be printed
     */
    public static void printMatrix(int[][] matrix){
        int rows = matrix.length;
        int columns = matrix[0].length;
        String str = "|\t";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                str += matrix[i][j] + "\t";
            }
            System.out.println(str + "|");
            str = "|\t";
        }
    }
}
