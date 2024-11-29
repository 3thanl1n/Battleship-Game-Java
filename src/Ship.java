public abstract class Ship {
    protected int bowColumn;
    protected int bowRow;
    protected boolean[] hit;
    protected boolean horizontal;
    protected int length;

    // Only abstract method
    public abstract String getShipType();

    // All other methods should have implementations
    public int getBowColumn() {
        return bowColumn;
    }

    public int getBowRow() {
        return bowRow;
    }

    public int getLength() {
        return length;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setBowColumn(int column) {
        this.bowColumn = column;
    }

    public void setBowRow(int row) {
        this.bowRow = row;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public boolean isSunk() {
        for (boolean target : hit) {
            if (!target) return false;
        }
        return true;
    }

    public boolean shootAt(int row, int column) {
        if (isHorizontal()== true){
            if ( row == bowRow  && column >= bowColumn && column <= bowColumn + length - 1){
                int index= column - bowColumn;
                if (hit[index]){
                    return false;
                } else{
                    hit[index]= true;
                    return true;
                }
            }
        } else if (isHorizontal() == false) {
            if (column == bowColumn  && row >= bowRow && row <= bowRow +length -1){
                int index= row - bowRow;
                if (hit[index]){
                    return false;
                } else{
                    hit[index]= true;
                    return true;
                }
            }
        }


        return false;
    }

    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        // Implementation for checking valid placement

    }

    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        // Implementation for placing ship
    }

    public String toString() {
        // Implementation for string representation
    }



}
