import java.text.DecimalFormat;

public class Main {
    // Returns sum of the first n terms in a series
    private String sumSeries(int n) {
        int x = 4;
        int step = 3;
        double sum = 1.0;
        for (int i = 1; i < n; i++) {
            sum += 1.0 / x;
            x += step;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(sum);
        // alt
        // return String.format(".2f", sum);
        //
        // Stream version
        // IntStream.range(0, m).mapToDouble(x -> 1.0 / (1 + 3 * x)).sum()
        // Stream style programming looks cute but ... I don't know ...
        //   How many people in Java-land are comfortable thinking this
        //   way?
    }

    public static void main(String[] args) {
        System.out.println("hello, world");

        Main m = new Main();
        assert "1.00".equals(m.sumSeries(1));
        assert "1.25".equals(m.sumSeries(2));
        assert "1.57".equals(m.sumSeries(5));
        assert "1.77".equals(m.sumSeries(9));
        assert "1.94".equals(m.sumSeries(15));
    }
}
