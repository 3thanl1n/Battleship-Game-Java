import java.util.HashSet;
import java.util.Scanner;

public class BattleshipGame {

    private Ocean ocean;

    public static void main(String[] args) {
        Ocean ocean = new Ocean();
        HashSet<String> firedShots = new HashSet<>();
        ocean.placeAllShipsRandomly();
        ocean.print();
        Scanner scanner = new Scanner(System.in);

        while (!ocean.isGameOver()) {
            System.out.print("Enter row (0-9): ");
            int row = scanner.nextInt();
            while (row < 0 || row >= 10) {
                System.out.print("Invalid input. Enter row (0-9): ");
                row = scanner.nextInt();
            }

            System.out.print("Enter column (0-9): ");
            int column = scanner.nextInt();
            while (column < 0 || column >= 10) {
                System.out.print("Invalid input. Enter column (0-9): ");
                column = scanner.nextInt();
            }

            String coordinate= row + ", " + column;
            if (firedShots.contains(coordinate)){
                System.out.println("You already choose this location");
                continue;
            }else{
                firedShots.add(coordinate);
            }

            boolean hit = ocean.shootAt(row, column);
            if (hit) {
                System.out.println("Hit!");
            } else {
                System.out.println("Miss!");
            }

            System.out.println("Shots fired: " + ocean.getShotsFired());
            System.out.println("Hits: " + ocean.getHitCount());
            System.out.println("Ships sunk: " + ocean.getShipsSunk());
            System.out.println();
            ocean.print();
        }
    }
}


