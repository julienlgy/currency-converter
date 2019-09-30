package currency.json;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteJSONFile {
    public static boolean write(JSONObject jsonObject) {
        System.out.println("Writing the update to currencies.json file...");
        try {
            PrintWriter out1 = new PrintWriter("currencies.json", "UTF-8");
            out1.write(jsonObject.toJSONString());
            out1.close();
        } catch (IOException e) {
            System.out.println("ERROR : WriteJSONFile [1] : Cannot write this file");
        }
        return false;
    }
}
