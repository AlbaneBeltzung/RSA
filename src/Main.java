import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIGlobalBinding;

import java.awt.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        KeyGenerator keys = new KeyGenerator();
        keys.ggt();


        try {
            Utilities.writeKeys("./ressources/sk.txt", "./ressources/pk.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Aufgabe 4
        //sieveOfEratosthenes(100000);

        //Aufgabe 7


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

}
