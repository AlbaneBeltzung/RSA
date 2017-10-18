import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        KeyGenerator keys = new KeyGenerator(512);

        //Aufgabe 1
        try {
            Utilities.writeKeys("./ressources/sk.txt", "./ressources/pk.txt", keys);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Aufgabe 2
        try {
            Utilities.encryptText("./ressources/text2.txt","./ressources/chiffre2.txt", keys.PK);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Aufgabe 3
        try {
            Utilities.decryptText("./ressources/chiffre2.txt", "./ressources/text-d.txt", keys.SK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Aufgabe 4
        try {
            Utilities.readKeys("./ressources/sk-what.txt", "./ressources/pk.txt",keys);
            Utilities.decryptText("./ressources/chiffre-what.txt", "./ressources/text-d.txt", keys.SK);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /*
   Recherchieren Sie, was das “Sieb des Eratostenes” ist,
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
    Schreiben Sie ein Java-Programm, welches zwei 1024-Bit Primzahlen
    zufällig erzeugt, das Produkt dieser Zahlen berechnet und alle drei Zahlen ausgibt.
    Recherchieren Sie, welche Klassen es bereits gibt. (Diese dürfen Sie dann auch verwenden.)
    --> wurde erledigt, und für übung weitervewendet.
     */

}
