package a;

// Part 1: 411 players; last marble is worth 71170 points
// Need to add 1 to this number to account for the '0' marble :)
// Part 2: 411 players; last marble is worth 7117000 points
// Too low: 862735200
// Too low: 1070339715
// Answer: 3526561003

public class Main {

    public static void main(String[] args) {
        Game g = new Game(411, 71171 * 100);
        while (!g.isFinished()) {
            g.turn();
        }
        System.out.println(g.getHighScore());
    }
}
