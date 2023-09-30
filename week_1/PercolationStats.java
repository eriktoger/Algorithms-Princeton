import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int gridSize;
    private double[] means;
    private int t;
    private double std196 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n or trails cannot be zero or negative");
        }
        gridSize = n * n;
        means = new double[trials];
        t = trials;

        int currentTrial = 0;
        while (currentTrial < trials) {

            Percolation percolation = new Percolation(n);
            while (true) {
                int randomNode = StdRandom.uniformInt(0, gridSize);

                int row = randomNode / n + 1;
                int col = randomNode % n + 1;
                percolation.open(row, col);

                if (percolation.percolates()) {
                    double openSites = percolation.numberOfOpenSites();
                    means[currentTrial] = openSites / gridSize;
                    break;
                }
            }

            currentTrial++;

        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(means);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(means);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double s = stddev();
        double sqrtT = Math.sqrt(t);
        return mean() - std196 * s / sqrtT;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double s = stddev();
        double sqrtT = Math.sqrt(t);
        return mean() + std196 * s / sqrtT;
    }

    // test client (see below)
    public static void main(String[] args) {

        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        StdOut.println("mean = " + percolationStats.mean());
        StdOut.println("stddev = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", "
                + percolationStats.confidenceHi() + "]");

    }

}
