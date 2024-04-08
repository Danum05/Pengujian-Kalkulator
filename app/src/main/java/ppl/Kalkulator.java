package ppl;

import java.util.Scanner;

public class Kalkulator {

    public static double Validasi(Scanner scanner, char operator, double angka1, double angka2) {
        // Validasi range nilai angka
        if (angka1 < -32768 || angka1 > 32767 || angka2 < -32768 || angka2 > 32767) {
            System.err.println("Error: Angka yang dihitung harus berada dalam range -32,768 hingga 32,767.");
            return Double.NaN;
        }

        // Validasi pembagian dengan nol
        if (operator == '4' && angka2 == 0) {
            System.err.println("Error: Pembagi tidak boleh bernilai nol.");
            return Double.NaN;
        }

        // Validasi operator
        if (operator != '1' && operator != '2' && operator != '3' && operator != '4') {
            System.err.println("Error: Operator tidak dikenal.");
            return Double.NaN;
        }

        return komputasi(angka1, angka2, operator);
    }

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

    public static double komputasi(double angka1, double angka2, char operator) {
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
                return Double.NaN;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double angka1 = 0;
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

        double angka2 = 0;
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

        char operator = '0';

        // Input operator
        while (true) {
            System.out.println("Pilih operasi: ");
            System.out.println("1. Penambahan");
            System.out.println("2. Pengurangan");
            System.out.println("3. Perkalian");
            System.out.println("4. Pembagian");
            String inputOperator = scanner.next();
            if (inputOperator.length() == 1) {
                operator = inputOperator.charAt(0);
                if (operator == '1' || operator == '2' || operator == '3' || operator == '4') {
                    double hasilValidasi = Validasi(scanner, operator, angka1, angka2);
                    if (!Double.isNaN(hasilValidasi)) {
                        System.out.println("Hasil: " + hasilValidasi);
                    }
                    break;
                }
            }
            System.out.println("Error: Operator tidak dikenal.");
        }

        scanner.close();
    }
}