package algo;

public class Position implements Cloneable {
    public int row, col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Position(row, col);
    }

}
