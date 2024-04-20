package ppl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class KalkulatorTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
    
    
    @Test
    public void testValidasiRangeAngka() {
        provideInput("35000\n10000\n1\n");
        Kalkulator.main(new String[0]);
        assertFalse(getOutput().contains("Error: Angka yang dihitung harus berada dalam range -32,768 hingga 32,767."));
    }
    
    
    @Test
    public void testValidasiPembagianDenganNol() {
        provideInput("10\n0\n4\n");
        Kalkulator.main(new String[0]);
        assertFalse(getOutput().contains("Error: Pembagi tidak boleh bernilai nol."));
    }

    @Test
    public void testValidasiOperator() {
        provideInput("5\n5\n1\n5\n5\n2\n5\n5\n3\n5\n5\n4\n5\n5\n5\n");
        Kalkulator.main(new String[0]);
        assertFalse(getOutput().contains("Error: Operator tidak dikenal."));
    }
    
    @Test
    public void testValidasi() {
        // Kondisi normal
        assertEquals(10, Kalkulator.Validasi(new Scanner(String.format("5%n5%n")), '1', 5, 5), 0.0001); // Penambahan
        assertEquals(0, Kalkulator.Validasi(new Scanner(String.format("5%n5%n")), '2', 5, 5), 0.0001); // Pengurangan
        assertEquals(25, Kalkulator.Validasi(new Scanner(String.format("5%n5%n")), '3', 5, 5), 0.0001); // Perkalian
        assertEquals(1, Kalkulator.Validasi(new Scanner(String.format("5%n5%n")), '4', 5, 5), 0.0001); // Pembagian
    
        // Kondisi angka di luar range
        assertEquals(Double.NaN, Kalkulator.Validasi(new Scanner(String.format("-50000%n5%n")), '1', -50000, 5), 0.0001); // Penambahan
        assertEquals(Double.NaN, Kalkulator.Validasi(new Scanner(String.format("5%n50000%n")), '2', 5, 50000), 0.0001); // Pengurangan
        assertEquals(Double.NaN, Kalkulator.Validasi(new Scanner(String.format("50000%n5%n")), '3', 50000, 5), 0.0001); // Perkalian
        assertEquals(Double.NaN, Kalkulator.Validasi(new Scanner(String.format("5%n0%n")), '4', 5, 0), 0.0001); // Pembagian dengan nol
    
        // Kondisi pembagian dengan nol
        assertEquals(Double.NaN, Kalkulator.Validasi(new Scanner(String.format("5%n0%n")), '4', 5, 0), 0.0001); // Pembagian
    }

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
