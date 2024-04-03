package ppl;

import java.util.Scanner;

public class Kalkulator {

    static double hasil;

    public static double pertambahan(double a, double b) {
        return a + b;
    }

    public static double pengurangan(double a, double b) {
        return a - b;
    }

    public static double perkalian(double a, double b) {
        return a * b;
    }

    public static double pembagian(double a, double b) {
        return a / b;
    }

    public static double compute(double angka1, double angka2, char operator) {
        // Validasi range nilai angka
        if (!isValidRange(angka1) || !isValidRange(angka2)) {
            System.err.println("Error: Angka yang dihitung harus berada dalam range -32,768 hingga 32,767.");
            return Double.NaN;
        }

        // Validasi pembagian dengan nol
        if (operator == '4' && angka2 == 0) {
            System.err.println("Error: Pembagi tidak boleh bernilai nol.");
            return Double.NaN;
        }

        switch (operator) {
            case '1':
                return pertambahan(angka1, angka2);
            case '2':
                return pengurangan(angka1, angka2);
            case '3':
                return perkalian(angka1, angka2);
            case '4':
                return pembagian(angka1, angka2);
            default:
                System.err.println("Error: Operator tidak dikenal.");
                return Double.NaN;
        }
    }

    private static boolean isValidRange(double angka) {
        return angka >= -32768 && angka <= 32767;
    }

    private static boolean isValidOperator(char operator) {
        return operator == '1' || operator == '2' || operator == '3' || operator == '4';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double angka1 = 0;
        double angka2 = 0;
        char operator = '0'; 

        while (true) {
            System.out.println("Masukkan angka pertama: ");
            if (scanner.hasNextDouble()) {
                angka1 = scanner.nextDouble();
                break;
            } else {
                System.out.println("Error: Masukan harus berupa angka.");
                scanner.next(); 
            }
        }

        while (true) {
            System.out.println("Masukkan angka kedua: ");
            if (scanner.hasNextDouble()) {
                angka2 = scanner.nextDouble();
                break;
            } else {
                System.out.println("Error: Masukan harus berupa angka.");
                scanner.next(); 
            }
        }

        while (true) {
            System.out.println("Pilih operasi: ");
            System.out.println("1. Penambahan");
            System.out.println("2. Pengurangan");
            System.out.println("3. Perkalian");
            System.out.println("4. Pembagian");
            String inputOperator = scanner.next();
            if (isValidOperator(inputOperator.charAt(0))) {
                operator = inputOperator.charAt(0);
                break;
            } else {
                System.out.println("Error: Masukan harus merupakan operator yang valid.");
            }
        }

        double hasil = compute(angka1, angka2, operator);
        if (!Double.isNaN(hasil)) {
            System.out.println("Hasil: " + hasil);
        }

        scanner.close();
    }
}
