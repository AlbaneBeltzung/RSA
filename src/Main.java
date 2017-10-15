import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIGlobalBinding;

import java.awt.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        //Aufgabe 4
        sieveOfEratosthenes(100000);

        //Aufgabe 7
        generateRSA();

    }

    /*
    Aufgabe 4. Recherchieren Sie, was das “Sieb des Eratostenes” ist,
     implementieren Sie das Verfahren in Java und bestimmen Sie damit alle Primzahlen bis 100.000.
     */
    public static void sieveOfEratosthenes(int n) {

        /*
        Array gets filled with true booleans fromm 2 to n (which is max)
         */
        boolean[] primes = new boolean[n + 1];
        int counter = 2;
        while (counter <= n) {
            primes[counter] = true;
            counter++;
        }

        /*
        Algorithm is being applied
         */
        for (int i = 2; i * i <= n; i++) {

            if (primes[i]) {
                for (int j = i; i * j <= n; j++) {
                    primes[i * j] = false;
                }
            }
        }

        /*
        Counting posititves primes in the array
         */
        int primeCount = 0;
        for (int i = 2; i <= n; i++) {
            if (primes[i]) primeCount++;
        }
        System.out.println("Anzahl Prinzahlen mit max " + n + " = " + primeCount);
    }

    /*
    Aufgabe 7. Schreiben Sie ein Java-Programm, welches zwei 1024-Bit Primzahlen
    zufällig erzeugt, das Produkt dieser Zahlen berechnet und alle drei Zahlen ausgibt.
    Recherchieren Sie, welche Klassen es bereits gibt. (Diese dürfen Sie dann auch verwenden.)
    --> wurde erledigt, und für übung weitervewendet.
     */
    public static void generateRSA() {

        BigInteger one = new BigInteger("1");
        BigInteger zero = new BigInteger("0");
        BigInteger p = BigInteger.probablePrime(8, new Random());
        BigInteger q = BigInteger.probablePrime(8, new Random());
        BigInteger n = p.multiply(q);
        BigInteger m = p.subtract(one).multiply(q.subtract(one));
        BigInteger e = new BigInteger("2");

        while (e.mod(one) != m.mod(one) && e.compareTo(m) < 0) {
            e = new BigInteger(8, new Random());
        }

//        System.out.println("Zufall-Primzahl n1: " + p);
//        System.out.println("Zufall-Primzahl n2: " + p);
//        System.out.println("n1 * n2 = " + n);

        BigInteger a = new BigInteger("18");
        BigInteger newA = a;
        BigInteger b = new BigInteger("7");
        BigInteger newB = b;
        BigInteger x0 = new BigInteger("1");
        BigInteger y0 = new BigInteger("0");
        BigInteger x1 = new BigInteger("0");
        BigInteger y1 = new BigInteger("1");
        BigInteger newQ = new BigInteger("0");
        BigInteger newR = new BigInteger("0");

        while (newB.compareTo(zero) == 0){
            newQ = newA.divide(newB);
            newR = newA.mod(newB);
            newA = newB;
            newB = newR;
            BigInteger x0temp = x0;
            BigInteger y0temp = y0;
            x0 = x1;
            y0 = y1;
            x1 = x0temp.subtract(newQ.multiply(x1));
            y1 = y0temp.subtract(newQ.multiply(y1));
        }
        System.out.println(newB + " " + x0.multiply(a).add(y0.multiply(b)));

    }



}
