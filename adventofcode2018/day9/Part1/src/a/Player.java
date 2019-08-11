package a;

public class Player {
    private long score;

    public Player() {
        this.score = 0;
    }

    public long getScore() {
        return score;
    }

    public void addScore(int n) {
        score += n;
    }
}
