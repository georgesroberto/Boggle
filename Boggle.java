import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Boggle {
    private String[][] board;


    //PART ONE: Board Generation (Constructor)


    // Constructor for initializing the Boggle board with dice shaking
    public Boggle(String[] dice, long seed) {
        int size = (int) Math.sqrt(dice.length);
        board = new String[size][size];

        // Shuffle the dice based on the provided seed
        shuffleDice(dice, seed);

        // Fill the board by randomly selecting one side from each die
        fillBoard(dice);
    }

    // Helper method to shuffle the dice array based on the provided seed
    private void shuffleDice(String[] dice, long seed) {
        Random random = new Random(seed);

        for (int i = dice.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            // Swap dice[i] and dice[j]
            String temp = dice[i];
            dice[i] = dice[j];
            dice[j] = temp;
        }
    }

    // Helper method to fill the Boggle board with letters from shuffled dice
    private void fillBoard(String[] dice) {
        int index = 0;
        Random random = new Random();  // Add this line to create a new Random instance

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // Select a random side of the current die
                String letter = String.valueOf(dice[index].charAt(random.nextInt(6)));
                board[i][j] = letter;

                index++;
            }
        }
    }


    //PART TWO: Reading Board from File

    // Constructor for reading the board from a file
    public Boggle(String fileName) {
        try {
            readBoardFromFile("Words/board-points3.txt"); // Replace with your actual file name
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found");
        }
    }
    

    // Helper method to read the board from a file
    private void readBoardFromFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        int rows = 0;
        int cols = 0;

        // Determine the number of rows and columns in the file
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            rows++;
            cols = line.length();
        }

        // Reset the scanner to the beginning of the file
        scanner = new Scanner(new File(fileName));

        // Initialize the board based on the determined size
        board = new String[rows][cols];

        // Fill the board with characters from the file
        for (int i = 0; i < rows; i++) {
            String line = scanner.nextLine();

            for (int j = 0; j < cols; j++) {
                board[i][j] = String.valueOf(line.charAt(j));
            }
        }

        scanner.close();
    }

    //Constructor foor initializing the Boggle board with a predefined board
    public Boggle(String[][] board){
        this.board = board;
    }
    
    //PART THREE: Word Matching

    public boolean matchWord(String word) {
        // Convert the word to uppercase to match the board
        word = word.toUpperCase();
    
        // Iterate through each cell on the board as a starting point
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // Check if the word can be formed starting from this cell
                if (matchWordFromCell(word, i, j, new boolean[board.length][board[0].length])) {
                    return true;
                }
            }
        }
    
        // Word not found on the board
        return false;
    }
    
    // Helper method for recursive word matching
    private boolean matchWordFromCell(String word, int row, int col, boolean[][] visited) {
        // Base case: If the word is empty, it's found
        if (word.isEmpty()) {
            return true;
        }
    
        // Check if the current cell is within bounds and not visited
        if (isValidCell(row, col) && !visited[row][col]) {
            // Check if the current cell matches the first letter of the word
            if (board[row][col].equals(String.valueOf(word.charAt(0)))) {
                // Mark the current cell as visited
                visited[row][col] = true;
    
                // Explore adjacent cells for the next letter of the word
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (i != 0 || j != 0) {  // Skip the current cell
                            if (matchWordFromCell(word.substring(1), row + i, col + j, visited)) {
                                return true;
                            }
                        }
                    }
                }
    
                // Backtrack: Mark the current cell as not visited
                visited[row][col] = false;
            }
        }
    
        // Word not found from the current cell
        return false;
    }
    
    // Helper method to check if the cell coordinates are valid
    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

    
    //PART FOUR: Generating All Valid Words


    // Method to generate all valid words based on a given dictionary and board
    public static List<String> getAllValidWords(String dictionaryName, String boardName) {
        List<String> dictionary = readDictionary("Words/dictionary.txt");
        String[][] board = readBoard("Words/board-points3.txt"); 
    
        // Create a new Boggle instance with the read board
        Boggle boggle = new Boggle(board);
    
        Set<String> validWords = new HashSet<>();
    
        // Iterate through each word in the dictionary
        for (String word : dictionary) {
            // Check if the word can be formed on the board
            if (boggle.matchWord(word)) {
                validWords.add(word);
            }
        }
    
        return new ArrayList<>(validWords);
    }
    

    // Helper method to read the dictionary from a file
    private static List<String> readDictionary(String dictionaryName) {
        List<String> dictionary = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(dictionaryName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line.trim().toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dictionary;
    }


    // Helper method to read the Boggle board from a file
    private static String[][] readBoard(String boardName) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(boardName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.trim().toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int numRows = lines.size();
        int numCols = lines.get(0).length();

        String[][] board = new String[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                board[i][j] = String.valueOf(lines.get(i).charAt(j));
            }
        }

        return board;
    }


    // Override toString to display the current state of the Boggle board
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String[] row : board) {
            for (String letter : row) {
                sb.append(letter).append(" ");
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }


    // Example additional method (you might need to add more based on your requirements)
    // private boolean isValidPosition(int row, int col) {
    //     return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    // }


    public static void main(String[] args) {
        // Example: Create an instance of Boggle using the constructor with dice shaking
        String[] dice = {
            "LRYTTE", "VTHRWE", "EGHWNE", "SEOTIS",
            "ANAEEG", "IDSYTT", "OATTOW", "MTOICU",
            "AFPKFS", "XLDERI", "HCPOAS", "ENSIEU",
            "YLDEVR", "ZNRNHL", "NMIQHU", "OBBAOJ"
        };
        long seed = 97;
        Boggle boggle = new Boggle(dice, seed);

        // Call methods or perform actions as needed
        System.out.println("Boggle Board:");
        System.out.println(boggle);

        // Example: Check if a word can be formed on the board
        String testWord = "test";
        boolean canFormWord = boggle.matchWord(testWord);
        System.out.println("Can form the word '" + testWord + "': " + canFormWord);
    }

}

