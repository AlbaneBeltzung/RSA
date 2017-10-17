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

    public KeyGenerator() {
        this.p = BigInteger.probablePrime(8, new Random());
        this.q = BigInteger.probablePrime(8, new Random());
        this.n = p.multiply(q);
        this.m = p.subtract(one).multiply(q.subtract(one));
        this.phiN = (p.subtract(one).multiply(q.subtract(one)));
        this.e = BigInteger.valueOf(new Random().nextInt(phiN.intValue() - 2) + 2);
        while (e.gcd(phiN).compareTo(BigInteger.ONE) != 0) {
            e = BigInteger.valueOf(new Random().nextInt(phiN.intValue() - 2) + 2);
        }
        System.out.println("d should be: "+m.modInverse(e));
        ggt();
        System.out.println("d is: "+SK[1]);
    }

    /*
    Get the ggt and y0, which is d
     */
    public void ggt() {
        BigInteger newA = e;
        BigInteger newB = phiN;
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

       // System.out.println("gdc = " + newA);

        this.PK[0]=n;
        this.PK[1]=e;
        this.SK[0]=n;
        this.SK[1]=y0.mod(phiN);
    }

    public void writePublicKey(String path){

    }


}
