package a;

public class Jar {
    private int limit;
    private int next;

    public Jar(int marbles) {
        this.limit = marbles;
    }

    public int count() {
        return limit;
    }

    public boolean isEmpty() {
        return 0 == limit;
    }

    public Marble getMarble() {
        Marble m = new Marble(next);
        limit--; next++;
        if (limit % 10000 == 0) {
            System.out.println(limit + " marbles left");
        }
        return m;
    }
}
