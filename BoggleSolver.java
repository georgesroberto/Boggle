import java.util.HashSet;
import java.util.Set;

public class BoggleSolver {
    private char[][] board;
    private int rows;
    private int cols;
    private Set<String> dictionary;
    private Set<String> wordsFound;

    public BoggleSolver(char[][] board, Set<String> dictionary) {
        this.board = board;
        this.rows = board.length;
        this.cols = board[0].length;
        this.dictionary = dictionary;
        this.wordsFound = new HashSet<>();
    }

    public boolean isValidWord(String word) {
        return dictionary.contains(word);
    }

    public boolean isValidPosition(int row, int col, boolean[][] visited) {
        return row >= 0 && row < rows && col >= 0 && col < cols && !visited[row][col];
    }

    public void exploreWord(int row, int col, boolean[][] visited, StringBuilder currentWord) {
        visited[row][col] = true;
        currentWord.append(board[row][col]);

        if (currentWord.length() >= 3 && isValidWord(currentWord.toString())) {
            wordsFound.add(currentWord.toString());
        }

        // Explore all valid neighbors
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (isValidPosition(newRow, newCol, visited)) {
                    exploreWord(newRow, newCol, copyVisited(visited), new StringBuilder(currentWord));
                }
            }
        }
    }

    private boolean[][] copyVisited(boolean[][] visited) {
        boolean[][] copy = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(visited[i], 0, copy[i], 0, cols);
        }
        return copy;
    }

    public Set<String> solve() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                exploreWord(i, j, new boolean[rows][cols], new StringBuilder());
            }
        }

        return wordsFound;
    }

    public static void main(String[] args) {
        // Sample 4x4 board
        char[][] board = {
                {'a', 'b', 'c', 'd'},
                {'e', 'f', 'g', 'h'},
                {'i', 'j', 'k', 'l'},
                {'m', 'n', 'o', 'p'}
        };

        // Sample dictionary
        Set<String> dictionary = Set.of("abc", "def", "ghij", "mnop", "ok");

        BoggleSolver solver = new BoggleSolver(board, dictionary);
        Set<String> foundWords = solver.solve();

        System.out.println("Words found: " + foundWords);
    }
}
