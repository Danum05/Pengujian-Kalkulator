package ppl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KalkulatorTest {

    @Test
    void testPertambahan() {
        assertEquals(4, Kalkulator.pertambahan(2, 2)); // Kondisi normal
        assertEquals(0, Kalkulator.pertambahan(-2, 2)); // Kondisi salah satu angka negatif
        assertEquals(7.5, Kalkulator.pertambahan(3.5, 4)); // Kondisi angka desimal
        assertEquals(-10, Kalkulator.pertambahan(-20, 10)); // Kondisi negatif
        assertEquals(0, Kalkulator.pertambahan(0, 0)); // Kondisi angka nol
    }

    @Test
    void testPengurangan() {
        assertEquals(3, Kalkulator.pengurangan(5, 2)); // Kondisi normal
        assertEquals(-7, Kalkulator.pengurangan(-5, 2)); // Kondisi salah satu angka negatif
        assertEquals(2.5, Kalkulator.pengurangan(5, 2.5)); // Kondisi angka desimal
        assertEquals(-30, Kalkulator.pengurangan(-20, 10)); // Kondisi negatif
        assertEquals(0, Kalkulator.pengurangan(0, 0)); // Kondisi angka nol
    }

    @Test
    void testPerkalian() {
        assertEquals(10, Kalkulator.perkalian(5, 2)); // Kondisi normal
        assertEquals(-10, Kalkulator.perkalian(-5, 2)); // Kondisi salah satu angka negatif
        assertEquals(12.5, Kalkulator.perkalian(5, 2.5)); // Kondisi angka desimal
        assertEquals(-200, Kalkulator.perkalian(-20, 10)); // Kondisi negatif
        assertEquals(0, Kalkulator.perkalian(0, 5)); // Kondisi angka nol
    }

    @Test
    void testPembagian() {
        assertEquals(2.5, Kalkulator.pembagian(5, 2)); // Kondisi normal
        assertEquals(-2.5, Kalkulator.pembagian(-5, 2)); // Kondisi salah satu angka negatif
        assertEquals(2, Kalkulator.pembagian(5, 2.5)); // Kondisi angka desimal
        assertEquals(-2, Kalkulator.pembagian(-20, 10)); // Kondisi negatif
        assertEquals(Double.POSITIVE_INFINITY, Kalkulator.pembagian(5, 0)); // Kondisi pembagian dengan nol
    }

    @Test
    void testValidasi() {
        // Kondisi normal
        assertEquals(10, Kalkulator.Validasi(5, 5, '1')); // Penambahan
        assertEquals(0, Kalkulator.Validasi(5, 5, '2')); // Pengurangan
        assertEquals(25, Kalkulator.Validasi(5, 5, '3')); // Perkalian
        assertEquals(1, Kalkulator.Validasi(5, 5, '4')); // Pembagian

        // Kondisi angka di luar range
        assertEquals(Double.NaN, Kalkulator.Validasi(-50000, 5, '1')); // Penambahan
        assertEquals(Double.NaN, Kalkulator.Validasi(5, 50000, '2')); // Pengurangan
        assertEquals(Double.NaN, Kalkulator.Validasi(50000, 5, '3')); // Perkalian
        assertEquals(Double.NaN, Kalkulator.Validasi(5, 0, '4')); // Pembagian dengan nol

        // Kondisi pembagian dengan nol
        assertEquals(Double.NaN, Kalkulator.Validasi(5, 0, '4')); // Pembagian
    }

    @Test
    void testKomputasi() {
        // Kondisi normal
        assertEquals(10, Kalkulator.komputasi(5, 5, '1')); // Penambahan
        assertEquals(0, Kalkulator.komputasi(5, 5, '2')); // Pengurangan
        assertEquals(25, Kalkulator.komputasi(5, 5, '3')); // Perkalian
        assertEquals(1, Kalkulator.komputasi(5, 5, '4')); // Pembagian

        // Kondisi operator tidak dikenal
        assertEquals(Double.NaN, Kalkulator.komputasi(5, 5, '5')); // Operator tidak dikenal

    }
}
