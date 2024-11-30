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
        Ship[][] ships = ocean.getShipArray();

        // Check for negative coordinates
        if (row < 0 || column < 0) {
            return false;
        }

        // Check boundaries based on orientation
        if (horizontal && column + length > 10) {
            return false;
        }
        if (!horizontal && row + length > 10) {
            return false;
        }

        if (horizontal == true) {
            int colT = column;
            int rowT = row;

            for (int rowI = Math.max(0, row - 1); rowI <= Math.min(9, row + 1); rowI += 1) {
                if (rowI == row - 1 || rowI == row + 1) {
                    for (int colI = Math.max(0, column - 1); colI <= Math.min(9, column + length); colI++) {
                        if (ocean.isOccupied(rowI, colI)) {
                            return false;
                        }
                    }
                } else {  // ship's row
                    if (column > 0 && ocean.isOccupied(rowI, column - 1)) return false;
                    if (column + length < 10 && ocean.isOccupied(rowI, column + length)) return false;
                }
            }
        } else {
            int colT = column;
            int rowT = row;

            for (int colI = Math.max(0, column - 1); colI <= Math.min(9, column + 1); colI += 1) {
                if (colI == column - 1 || colI == column + 1) {
                    for (int rowI = Math.max(0, row - 1); rowI <= Math.min(9, row + length); rowI++) {
                        if (ocean.isOccupied(rowI, colI)) {
                            return false;
                        }
                    }
                } else {
                    if (row > 0 && ocean.isOccupied(row - 1, colI)) return false;
                    if (row + length < 10 && ocean.isOccupied(row + length, colI)) return false;
                }
            }

        }
        return true;
    }


    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
       Ship[][] ships = ocean.getShipArray();
       this.horizontal= horizontal;
       this.bowRow= row;
       this.bowColumn= column;
       hit= new boolean[length];

       if(horizontal){
           for (int colI= column; colI < column + length; colI += 1){
                   ships[row][colI]= this;
               }
           }else{
           for (int rowI= row; rowI < row + length; rowI += 1){
               ships[rowI][column]= this;
           }
       }

       }


    public String toString() {
        int trueCounter= 0;
        for (boolean hits : hit){
            if (hits == true){
                trueCounter += 1;
            }
        }
        if (isSunk() == true){
            return "x";
        } else if (trueCounter < length && trueCounter > 0){
            return "S";
        }

        return ".";
    }



}
