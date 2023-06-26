package kz.job4j.pool;

import java.util.Objects;

public class Sums {
    private int rowSum;
    private int colSum;

    public Sums() {

    }

    public Sums(int rowSum, int colSum) {
        this.colSum = colSum;
        this.rowSum = rowSum;
    }

    public void setColSum(int colSum) {
        this.colSum = colSum;
    }

    public void setRowSum(int rowSum) {
        this.rowSum = rowSum;
    }

    public int getColSum() {
        return colSum;
    }

    public int getRowSum() {
        return rowSum;
    }

    public String toString() {
        return "{" + rowSum + ", " + colSum + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Sums other = (Sums) obj;
        return rowSum == other.getRowSum() && colSum == other.getColSum();
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowSum, colSum);
    }
}