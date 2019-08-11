package a;

import java.util.ArrayList;
import java.util.List;

/**
 * State of a game in progress.
 * <p>
 * The number of players and marbles are known in advance. Marbles are
 * placed one at a time until they are all consumed. The player with
 * the highest score wins.
 * <p>
 * Points are awarded at every 23rd marble.
 */
class Game {
    private List<Marble> board;
    private Jar jar;

    private Player[] players;
    private int currentPlayer;
    private int currentMarble;

    public Game(int players, int marbles) {
        this.players = new Player[players];
        for (int i = 0; i < players; i++) {
            this.players[i] = new Player();
        }
        this.jar = new Jar(marbles);

        this.board = new ArrayList<>();

        // setup initial board state
        board.add(jar.getMarble());
        currentMarble = 0;
    }

    public boolean isFinished() {
        return jar.isEmpty();
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public Jar getJar() {
        return jar;
    }

    public void turn() {
        if (isFinished()) {
            return;
        }

        Marble m = jar.getMarble();
        if (m.getValue() % 23 == 0) {
            // add my marble to score
            players[currentPlayer].addScore(m.getValue());

            // add the marble i'm removing to score
            currentMarble -= 7;
            if (currentMarble < 0) {
                currentMarble = board.size() - (0 - currentMarble);
            }
            Marble removed = board.remove(currentMarble);
            players[currentPlayer].addScore(removed.getValue());
        } else {
            int rightNeighbourIdx = currentMarble + 1; // My right neighbour
            if (rightNeighbourIdx >= board.size()) {
                rightNeighbourIdx = 0;
            }
            int insertIdx = rightNeighbourIdx + 1;
            if (insertIdx > board.size()) {
                insertIdx = 0;
            }
            board.add(insertIdx, m);
            currentMarble = insertIdx;
        }

        currentPlayer++;
        if (currentPlayer >= players.length) {
            currentPlayer = 0;
        }

//        printBoard();
    }

    public int getMarbleAt(int n) {
        Marble m = board.get(n);
        return m.getValue();
    }

    public int getCurrentMarble() {
        return currentMarble;
    }

    public void printBoard() {
        for (int i = 0; i < board.size(); i++) {
            if (i == getCurrentMarble()) {
                System.out.print("(");
            }
            System.out.print(board.get(i).getValue());
            if (i == getCurrentMarble()) {
                System.out.print(")");
            }
            System.out.print(" ");
        }
        System.out.println("");
    }

    public long getHighScore() {
        long max = 0;
        for (Player p : players) {
            if (max < p.getScore()) {
                max = p.getScore();
            }
        }
        return max;
    }
}