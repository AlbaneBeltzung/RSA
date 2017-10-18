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
        //his.p = new BigInteger("31");
        //this.q = new BigInteger("7");
        this.n = p.multiply(q);
        this.m = p.subtract(one).multiply(q.subtract(one));
        this.phiN = (p.subtract(one).multiply(q.subtract(one)));

        //this.e = BigInteger.valueOf(new Random().nextInt(phiN.intValue() + 1000000));
        this.e = BigInteger.probablePrime(512, new Random());
        while ((phiN.gcd(e).compareTo(BigInteger.ONE) != 0) && (e.compareTo(phiN)<=0)) {
            //e = BigInteger.valueOf(new Random().nextInt(phiN.intValue() + 1000000));
            this.e = BigInteger.probablePrime(512, new Random());
        }
        System.out.println("e is: "+e);
        System.out.println("phin is: "+phiN);
        System.out.println("d should be: "+e.modInverse(phiN));
        ggt();
        System.out.println("d is: "+SK[1]);
    }

    /*
    Get the ggt and y0, which is d
     */
    public void ggt() {

        //System.out.println("e is " + e);
        //System.out.println("phiN is " + phiN);
        BigInteger newA = e;
        BigInteger newB = phiN;
        //BigInteger newA = new BigInteger("18");
        //BigInteger newB = new BigInteger("7");

        BigInteger x0 = new BigInteger("1");
        BigInteger y0 = new BigInteger("0");
        BigInteger x1 = new BigInteger("0");
        BigInteger y1 = new BigInteger("1");
        BigInteger newQ = new BigInteger("0");
        BigInteger newR = new BigInteger("0");
        int i = 0;
        while (newB.signum() == 1) {
            System.out.println("-------Iteration "+i+"---------");
            System.out.println("newA is "+newA);
            System.out.println("newB is "+newB);
            newQ = newA.divide(newB);
            newR = newA.mod(newB);
            newA = newB;
            newB = newR;
            BigInteger x0temp = x0;
            BigInteger y0temp = y0;

            System.out.println("x0 is "+x0);
            System.out.println("y0 is "+y0);
            System.out.println("x1 is "+x1);
            System.out.println("y1 is "+y1);
            System.out.println("newQ is "+newQ);
            System.out.println("newQ is "+newR);


            x0 = x1;
            y0 = y1;


            x1 = x0temp.subtract(newQ.multiply(x1));
            y1 = y0temp.subtract(newQ.multiply(y1));



            i+=1;
        }

        //System.out.println("gdc = " + newA);

        this.PK[0]=n;
        this.PK[1]=e;
        this.SK[0]=n;
        this.SK[1]=x0.mod(phiN);
    }

    public void writePublicKey(String path){

    }


}
