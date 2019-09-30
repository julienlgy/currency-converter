package currency;

import currency.json.WriteJSONFile;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class UpdateCurrency {
    private static String api_key = "6880e6cfb7a570db279c5dc264809c3c";
    private static String api_url = "http://data.fixer.io/api/latest";

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static boolean update() {
        try {
            InputStream is = new URL(api_url + "?access_key=" + api_key).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            org.json.simple.JSONObject json = (JSONObject) JSONValue.parse(jsonText);
            WriteJSONFile.write(json);
            return (true);
        } catch (MalformedURLException e) {
            System.out.println("ERROR : UpdateCurrency [1] : Malformed URL.");
            return false;
        } catch (IOException e) {
            System.out.println("ERROR : UpdateCurrency [2] : Can't update. Check the network.");
            return false;
        }
    }
}
