import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utilities {

    public static String readFiles(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }

    /*
    Saves the message to the .txt files
     */
    public static void saveToFile(String string, String path) throws IOException{
        Files.write(Paths.get(path), string.getBytes(StandardCharsets.UTF_8));
    }

    /*
    Gets the PK and SK and calls saveToFile(...)
     */
    public static void writeKeys(String skpath, String pkpath, KeyGenerator kg)throws IOException{
        System.out.println("writeKeys " + kg.SK[0] + kg.SK[1] );
        String sk = kg.SK[0] +","+ kg.SK[1];
        String pk = kg.PK[0] +","+ kg.PK[1];
        saveToFile(sk, skpath);
        saveToFile(pk, pkpath);
    }

    public static void readSecureKey(String skpath,KeyGenerator keys)throws IOException{
        String sk= new String(Files.readAllBytes(Paths.get(skpath)), StandardCharsets.UTF_8);
        sk=sk.substring(1,sk.length()-1);
        String[] temp=sk.split(",");
        keys.SK[0]=new BigInteger(temp[0]);
        keys.SK[1]=new BigInteger(temp[1]);

    }

    public static void encryptText(String input, String output, BigInteger[] pk) throws IOException{
        String inputText = readFiles(input);
        int[] ascii = inputText.chars().toArray();
        BigInteger[] encryptedInput = Cryptography.encryptText(ascii, pk);
        StringBuilder builder = new StringBuilder();
        for (BigInteger big : encryptedInput){
            builder.append(big);
            builder.append(",");
        }
        builder.deleteCharAt(builder.length()-1);
        saveToFile(builder.toString(), output);
    }

    public static void decryptText(String input, String output, BigInteger[] sk) throws IOException{
        String inputText = readFiles(input);
        String[] inputText2 = inputText.split(",");
        BigInteger[] temp = new BigInteger[inputText2.length];
        for(int i = 0; i< temp.length; i++){
            temp[i] = new BigInteger(inputText2[i]);
        }
        int[] decryptedInput = Cryptography.decryptText(temp, sk);


        StringBuilder builder = new StringBuilder();
        for (int i : decryptedInput){
            builder.append(((char)i));
        }
        saveToFile(builder.toString(), output);
    }

}
