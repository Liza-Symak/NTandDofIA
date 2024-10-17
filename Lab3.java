import java.util.Scanner;

class GameBoard {
    private char[][] board;

    public GameBoard() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void displayBoard() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public boolean makeMove(int row, int col, Player player) {
        if (board[row][col] == ' ') {
            board[row][col] = player.getSymbol();
            return true;
        }
        return false;
    }

    public boolean checkWinner() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return true;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                return true;
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

class Player {
    private String name;
    private char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public int[] makeMove(Scanner scanner) {
        int[] move = new int[2];
        while (true) {
            try {
                System.out.print(name + ", введіть рядок (0-2): ");
                move[0] = Integer.parseInt(scanner.nextLine());
                System.out.print(name + ", введіть стовпчик (0-2): ");
                move[1] = Integer.parseInt(scanner.nextLine());

                if (move[0] >= 0 && move[0] < 3 && move[1] >= 0 && move[1] < 3) {
                    return move;
                } else {
                    System.out.println("Неправильне значення. Спробуйте ще раз.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Будь ласка, введіть числа.");
            }
        }
    }
}

public class Lab3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Створюємо сканер тут
        GameBoard board = new GameBoard();
        Player player1 = new Player("Гравець 1", 'X');
        Player player2 = new Player("Гравець 2", 'O');
        Player currentPlayer = player1;

        while (true) {
            board.displayBoard();
            int[] move = currentPlayer.makeMove(scanner); // Передаємо сканер
            int row = move[0];
            int col = move[1];

            if (board.makeMove(row, col, currentPlayer)) {
                if (board.checkWinner()) {
                    board.displayBoard();
                    System.out.println(currentPlayer.getName() + " виграв!");
                    break;
                } else if (board.isFull()) {
                    board.displayBoard();
                    System.out.println("Нічия!");
                    break;
                }
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            } else {
                System.out.println("Це місце вже зайняте. Спробуйте інше.");
            }
        }
        scanner.close(); // Закриваємо сканер наприкінці
    }
}
