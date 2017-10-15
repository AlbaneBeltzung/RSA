import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;

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
    public static void writeKeys(String skpath, String pkpath)throws IOException{
        KeyGenerator kg = new KeyGenerator();
        System.out.println("writeKeys " + kg.SK[0] + kg.SK[1] );
        String sk = kg.SK[0] +","+ kg.SK[1];
        String pk = kg.PK[0] +","+ kg.PK[1];
        saveToFile(sk, skpath);
        saveToFile(pk, pkpath);
    }


}
