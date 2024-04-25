Kelompok B7 
Anggota : 
1. Danu Mahesa (211524037) (Username Github: Danum05)
2. Husna Maulana (2115240)
3. Rofa'ul Akrom H (211524061)

Program ini merupakan program kalkulator untuk memenuhi tugas testing mata kuiah Pengujian Perangkat Lunak Praktikum

# **Kelompok B7**
# Pengujian Program Kalkulator
## 1. Directory Tree project
```md
B7-Pengujian-Calculator(project-root)
- .gradle
  │   - 8.0.2
  │   │   - checksums
  │   │   - dependencies-accessors
  │   │   - executionHistory
  │   │   - fileChanges
  │   │   - fileHashes
  │   │   - vcsMetadata
  │   │   - gc.properties
  │   - buildOutputCleanup
  │   │   - buildOutputCleanup.lock
  │   │   - cache.properties
  │   │   - outputFiles.bin
  │   - vcs-1
  │   │   - gc.properties
  │   - file-system.probe
- .vscode
  │   - launch.json
  │   - settings.json
- app
  │   - build
  │   │   - classes
  │   │   - distributions
  │   │   - generated
  │   │   - jacoco
  │   │   - libs
  │   │   - reports
  │   │   │   - tests
  │   │   │   │   - test
  │   │   │   │   │   - classes
  │   │   │   │   │   - css
  │   │   │   │   │   - js
  │   │   │   │   │   - packages
  │   │   │   │   │   - index.html
  │   │   - resources
  │   │   - scripts
  │   │   - test-results
  │   │   - tmp
  │   - src
  │   │   - main
  │   │   │   - java
  │   │   │   │   - ppl
  │   │   │   │   │   - Kalkulator.java
  │   │   │   - resources
  │   │   - test
  │   │   │   - java
  │   │   │   │   - ppl
  │   │   │   │   │   - KalkulatorTest.java
  │   │   │   - resources
  │   - build.gradle
- gradle
  │   - wrapper
  │   │   - gradle-wrapper.jar
  │   │   - gradle-wrapper.properties
- .gitattributes
- .gitignore
- gradlew
- gradlew.bat
- settings.gradle
```
1. app: Folder yang berisi kode sumber aplikasi Java yang sedang dikembangkan. Di dalamnya terdapat folder src yang memuat kode sumber utama (main) dan kode sumber pengujian (test).
2. build.gradle: Berkas konfigurasi Gradle untuk proyek ini. Berkas ini berisi konfigurasi dependencies, plugins, tasks, dan pengaturan proyek lainnya.
3. src/main/java: Folder untuk menyimpan file-file Java yang merupakan kode sumber utama aplikasi.
4. src/test/java: Folder untuk menyimpan file-file Java yang merupakan kode sumber pengujian aplikasi.
5. gradle: Folder yang berisi file-file terkait dengan wrapper Gradle. Berkas gradle-wrapper.jar dan gradle-wrapper.properties dalam subfolder wrapper penting karena digunakan untuk menjalankan Gradle pada proyek ini.
6. gitignore: Berkas yang berisi daftar pola file dan direktori yang tidak ingin dimasukkan ke dalam repositori Git. Ini penting untuk mengatur mana file yang tidak perlu dilacak oleh Git.
7. settings.gradle: Berkas konfigurasi Gradle yang berisi pengaturan proyek seperti daftar modul. Ini penting karena mendefinisikan struktur proyek dan modul-modulnya.
8. index.html yang terdapat dalam folder app/build/reports/tests/test Merupakan laporan yang di generate dari hasil test. File index.html ini berisi ringkasan hasil pengujian unit yang telah dilakukan pada program kalkulator.


## 2. Cara Membuat Test Script
buat file (.java) pada ```app/src/test/java/ppl```

misal **app/src/test/java/ppl/KalkulatorTest.java**

didalam test script yang harus diperhatikan :
* **package** disini digunakan sehingga tidak perlu mengimpor file dari modul yang ingin di test
* Import junit yang digunakan, disini menggunakan default sudah ada dependency saat membuat project awal yaitu **junit jupiter**
* Tag **@Test** ini diperlukan untuk menandai bahwa method dibawahnya merupakan unit test
* Pastikan **nama method representatif** dari tes apa yang akan dilakukan karena nama method tersebut yang akan muncul di report
* lalu gunakan notasi test yang ada berdasarkan junit yang digunakan, dikarenakan ada **perbedaan notasi yang digunakan jika berbeda versi junit misal junit 4 dan 5**
```java
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
```

## 3. Cara menjalankan test script
jalankan ```./gradlew test```


## 4. Cara menjalankan program
+ jalankan ```./gradlew build``` untuk membangun program
+ lalu jalankan ```./gradlew runApp``` untuk melakukan run aplikasi kalkulator

## Hasil report 
+ jalankan ```./gradlew clean test``` untuk melakukan generate laporan
laporan pengujian yang digenerate oleh gradle ada pada ```app\build\reports\tests\test\index.html```



