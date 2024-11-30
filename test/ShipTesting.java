import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShipTesting {
    private Battleship battleship;
    private Destroyer destroyer;
    private Submarine submarine;
    private Cruiser cruiser;
    private Ocean ocean;

    @Before
    public void setUptest() {
        battleship = new Battleship();
        submarine= new Submarine();
        destroyer= new Destroyer();
        cruiser= new Cruiser();
        ocean = new Ocean();
    }

    @Test
    public void BowRowtest() {
        battleship.setBowRow(5);
        assertEquals(5, battleship.getBowRow());
        battleship.setBowRow(0);
        assertEquals(0, battleship.getBowRow());
    }

    @Test
    public void BowColumntest() {
        battleship.setBowColumn(3);
        assertEquals(3, battleship.getBowColumn());
        battleship.setBowColumn(9);
        assertEquals(9, battleship.getBowColumn());
    }

    @Test
    public void Lengthtest() {
        assertEquals(4, battleship.getLength());
    }

    @Test
    public void Horizontaltest() {
        battleship.setHorizontal(true);
        assertTrue(battleship.isHorizontal());
        battleship.setHorizontal(false);
        assertFalse(battleship.isHorizontal());
    }

    // Test ship placement
    @Test
    public void OkToPlaceShipAtValidtest() {
        assertTrue(battleship.okToPlaceShipAt(0, 0, true, ocean));  // horizontal at top left
        assertTrue(battleship.okToPlaceShipAt(5, 5, false, ocean)); // vertical in middle
    }

    @Test
    public void OkToPlaceShipAtInvalidtest() {
        assertFalse(battleship.okToPlaceShipAt(0, 7, true, ocean));  // would go off right edge
        assertFalse(battleship.okToPlaceShipAt(7, 0, false, ocean)); // would go off bottom edge
        assertFalse(battleship.okToPlaceShipAt(-1, 0, true, ocean)); // negative position
    }

    // Test shooting mechanics
    @Test
    public void ShootAtHorizontaltest() {
        battleship.placeShipAt(5, 5, true, ocean);
        assertTrue(battleship.shootAt(5, 5));  // bow
        assertTrue(battleship.shootAt(5, 6));  // second position
        assertTrue(battleship.shootAt(5, 7));  // third position
        assertTrue(battleship.shootAt(5, 8));  // fourth position
        assertFalse(battleship.shootAt(5, 9)); // miss
    }

    @Test
    public void ShootAtVerticaltest() {
        battleship.placeShipAt(3, 3, false, ocean);
        assertTrue(battleship.shootAt(3, 3));  // bow
        assertTrue(battleship.shootAt(4, 3));  // second position
        assertTrue(battleship.shootAt(5, 3));  // third position
        assertTrue(battleship.shootAt(6, 3));  // fourth position
        assertFalse(battleship.shootAt(7, 3)); // miss
    }


    @Test
    public void IsSunkFalstest() {
        battleship.placeShipAt(5, 5, true, ocean);
        battleship.shootAt(5, 5);  // only one hit
        assertFalse(battleship.isSunk());

        battleship.shootAt(5, 6);  // two hits
        battleship.shootAt(5, 7);  // three hits
        assertFalse(battleship.isSunk());
    }

    @Test
    public void IsSunkTruetest() {
        battleship.placeShipAt(5, 5, true, ocean);
        battleship.shootAt(5, 5);
        battleship.shootAt(5, 6);
        battleship.shootAt(5, 7);
        battleship.shootAt(5, 8);
        assertTrue(battleship.isSunk());
    }

    // Test get ship type
    @Test
    public void testGetShipType() {
        assertEquals("Battleship", battleship.getShipType());
        assertEquals("Submarine", submarine.getShipType());
        assertEquals("Destroyer", destroyer.getShipType());
        assertEquals("Cruiser", cruiser.getShipType());

    }
}