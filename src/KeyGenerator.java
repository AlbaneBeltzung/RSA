import java.math.BigInteger;
import java.util.Random;

public class KeyGenerator {
    BigInteger one = new BigInteger("1");
    BigInteger zero = new BigInteger("0");
    BigInteger p;
    BigInteger q;
    BigInteger n;
    BigInteger m;
    BigInteger e;
    BigInteger phiN;
    //Public Key (n,e)
    BigInteger[] PK = new BigInteger[2];
    //Secret Key (n,d)
    BigInteger[] SK = new BigInteger[2];

    /*
    Creates all variables
     */

    public KeyGenerator(int numberOfBits) {
        this.p = BigInteger.probablePrime(numberOfBits, new Random());
        this.q = BigInteger.probablePrime(numberOfBits, new Random());
        this.n = p.multiply(q);
        this.m = p.subtract(one).multiply(q.subtract(one));
        this.phiN = (p.subtract(one).multiply(q.subtract(one)));
        this.e = new BigInteger(numberOfBits,new Random());


        while (ggt(e,phiN).compareTo(BigInteger.ONE) != 0) {
            e = new BigInteger(numberOfBits,new Random());
        }
        System.out.println("d should be: "+e.modInverse(phiN));
        ggt(e,phiN);
        System.out.println("d is: "+SK[1]);
    }

    /*
    Get the ggt of e and phiN
     */
    public BigInteger ggt(BigInteger a,BigInteger b) {
        BigInteger newA = a;
        BigInteger newB = b;
        BigInteger x0 = new BigInteger("1");
        BigInteger y0 = new BigInteger("0");
        BigInteger x1 = new BigInteger("0");
        BigInteger y1 = new BigInteger("1");
        BigInteger newQ = new BigInteger("0");
        BigInteger newR = new BigInteger("0");

        while (newB.compareTo(zero) != 0) {
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

        this.PK[0]=n;
        this.PK[1]=e;
        this.SK[0]=n;
        this.SK[1]=x0.mod(phiN);

        //return ggt
        return newA;
    }


}
