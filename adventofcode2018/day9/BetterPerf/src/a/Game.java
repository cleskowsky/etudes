package a;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
    private Deque<Integer> board;
    private Map<Integer, Long> players;
    private int nplayers, nmarbles;

    public Game(int players, int marbles) {
        this.nplayers = players;
        this.nmarbles = marbles;
        this.board = new LinkedList<>();
        board.add(0);
        this.players = new HashMap<>();
    }

    public void play() {
        for (int i = 1; i < nmarbles; i++) {
            int player = i % nplayers;
            if (i % 23 == 0) {

                // shift the board
                for (int j = 0; j < 7; j++) {
                    board.addLast(board.removeFirst());
                }
                Integer removed = board.removeFirst();
                board.addFirst(board.removeLast());

                // Update player score
                long score = players.getOrDefault(player, 0L);
                // add current marble value
                score += i;
                // add the one i'm removing
                score += removed;
                players.put(player, score);

            } else {
                board.addFirst(board.removeLast());
                board.addFirst(i);
            }
        }
    }

    public long getHighScore() {
        long max = 0;
        for (Integer p : players.keySet()) {
            long score = players.get(p);
            if (score > max) {
                max = score;
            }
        }
        return max;
    }
}