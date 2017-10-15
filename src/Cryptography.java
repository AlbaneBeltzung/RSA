import java.math.BigInteger;

public class Cryptography {

    public static BigInteger[] encryptText(int[] text, BigInteger[] pk) {
        BigInteger[] output = new BigInteger[text.length];
        for (int i = 0; i < output.length; i++) {
            output[i] = modularPow(BigInteger.valueOf(text[i]), pk[1], pk[0]);
        }
        return output;
    }

    public static int[] decryptText(BigInteger[] text, BigInteger[] sk) {
        int[] output = new int[text.length];
        for (int i = 0; i < output.length; i++) {
            output[i] = modularPow(text[i], sk[1], sk[0]).intValue();
        }
        return output;
    }

    /*
    function modular_pow(base, exponent, modulus)
    if modulus = 1 then return 0
    Assert :: (modulus - 1) * (modulus - 1) does not overflow base
    result := 1
    base := base mod modulus
    while exponent > 0
        if (exponent mod 2 == 1):
           result := (result * base) mod modulus
        exponent := exponent >> 1
        base := (base * base) mod modulus
    return result

    Quelle: https://en.wikipedia.org/wiki/Modular_exponentiation
     */

    public static BigInteger modularPow(BigInteger base, BigInteger exponent, BigInteger modulus) {
        BigInteger result = BigInteger.ONE;
        base = base.mod(modulus);
        while (exponent.compareTo(BigInteger.ZERO) > 0) {
            if (exponent.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE))
                result = result.multiply(base).mod(modulus);

            exponent = exponent.shiftRight(1);
            base = base.multiply(base).mod(modulus);
        }
        return result;
    }

}
