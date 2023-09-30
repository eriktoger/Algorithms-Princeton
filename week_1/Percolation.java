import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufNoVirtualBottom;
    private int sideLength;
    private int numberOfOpen;
    private boolean[][] openGrid;
    private int gridSize;

    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("n cannot be zero or negative");
        }
        sideLength = n;
        openGrid = new boolean[n][n];
        numberOfOpen = 0;

        gridSize = n * n;
        uf = new WeightedQuickUnionUF(gridSize + 2);
        ufNoVirtualBottom = new WeightedQuickUnionUF(gridSize + 1);

    }

    private void union(int rowA, int colA, int rowB, int colB) {
        int numberA = rowA * sideLength + colA;
        int numberB = rowB * sideLength + colB;
        uf.union(numberA, numberB);
        ufNoVirtualBottom.union(numberA, numberB);

    }

    private void throwing(int row, int col) {
        if (row < 1 || col < 1 || row > sideLength || col > sideLength) {
            throw new IllegalArgumentException();
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        throwing(row, col);

        if (!isOpen(row, col)) {
            numberOfOpen++;
        }

        row--;
        col--;

        // connect to top node
        if (row == 0) {
            union(row, col, sideLength, 0);
        }
        // connect to bottom node without connecting to virtual bottom node
        if (row == sideLength - 1) {
            int number = row * sideLength + col;
            uf.union(number, gridSize + 1);
        }

        openGrid[row][col] = true;
        int up = row - 1;
        if (up >= 0 && openGrid[up][col]) {
            union(row, col, up, col);
        }

        int down = row + 1;
        if (down < sideLength && openGrid[down][col]) {
            union(row, col, down, col);
        }

        int left = col - 1;
        if (left >= 0 && openGrid[row][left]) {
            union(row, col, row, left);
        }

        int right = col + 1;
        if (right < sideLength && openGrid[row][right]) {
            union(row, col, row, right);
        }

    }

    public boolean isOpen(int row, int col) {

        throwing(row, col);
        row--;
        col--;

        return openGrid[row][col];
    }

    // is the site (row, col) full? open and connected to the top layer
    public boolean isFull(int row, int col) {
        boolean open = isOpen(row, col);
        row--;
        col--;
        int p = ufNoVirtualBottom.find(row * sideLength + col);
        return open && p == ufNoVirtualBottom.find(gridSize);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpen;
    }

    // does the system percolate?
    public boolean percolates() {

        return uf.find(gridSize) == uf.find(gridSize + 1);
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(1);

        StdOut.println("mean: " + p.percolates());
    }

}
