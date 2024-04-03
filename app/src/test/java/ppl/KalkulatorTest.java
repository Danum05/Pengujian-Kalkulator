package ppl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KalkulatorTest {

    @Test
    public void testPertambahan() {
        double hasil = Kalkulator.pertambahan(5, 3);
        assertEquals(8, hasil);
    }

    @Test
    public void testPertambahanNegatif() {
        double hasil = Kalkulator.pertambahan(-5, -3);
        assertEquals(-8, hasil);
    }

    @Test
    public void testPengurangan() {
        double hasil = Kalkulator.pengurangan(5, 3);
        assertEquals(2, hasil);
    }

    @Test
    public void testPenguranganNegatif() {
        double hasil = Kalkulator.pengurangan(-5, -3);
        assertEquals(-2, hasil);
    }

    @Test
    public void testPerkalian() {
        double hasil = Kalkulator.perkalian(5, 3);
        assertEquals(15, hasil);
    }

    @Test
    public void testPerkalianNegatif() {
        double hasil = Kalkulator.perkalian(-5, -3);
        assertEquals(15, hasil);
    }

    @Test
    public void testPembagian() {
        double hasil = Kalkulator.pembagian(10, 5);
        assertEquals(2, hasil);
    }

    @Test
    public void testPembagianNegatif() {
        double hasil = Kalkulator.pembagian(-10, -5);
        assertEquals(2, hasil);
    }

    @Test
    public void testComputePenambahan() {
        double hasil = Kalkulator.compute(5, 3, '1');
        assertEquals(8, hasil);
    }

    @Test
    public void testComputePengurangan() {
        double hasil = Kalkulator.compute(5, 3, '2');
        assertEquals(2, hasil);
    }

    @Test
    public void testComputePerkalian() {
        double hasil = Kalkulator.compute(5, 3, '3');
        assertEquals(15, hasil);
    }

    @Test
    public void testComputePembagian() {
        double hasil = Kalkulator.compute(10, 5, '4');
        assertEquals(2, hasil);
    }

    @Test
    public void testInvalidRangeAtas() {
        double hasil = Kalkulator.compute(50000, 500, '1');
        assertTrue(Double.isNaN(hasil));
    }

    @Test
    public void testInvalidRangeBawah() {
        double hasil = Kalkulator.compute(-50000, 500, '1');
        assertTrue(Double.isNaN(hasil));
    }

    @Test
    public void testInvalidOperator() {
        double hasil = Kalkulator.compute(10, 5, '9');
        assertTrue(Double.isNaN(hasil));
    }

    @Test
    public void testDivideByZero() {
        double hasil = Kalkulator.compute(10, 0, '4');
        assertTrue(Double.isNaN(hasil));
    }
}
