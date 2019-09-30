package currency.json;

import currency.Currency;
import currency.UpdateCurrency;
import currency.data.CurrencyData;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ReadJSONFile {
    public static ArrayList<Currency> get() {
        System.out.println("Loading currencies to currencies.json file...");
        ArrayList<Currency> currencies = new ArrayList<>();
        JSONParser parser = new JSONParser();
        CurrencyData data = new CurrencyData();
        try {
            JSONObject cur_json = (JSONObject) parser.parse(new FileReader("currencies.json"));
            /* Doing base currency */
            Currency base = new Currency(data.getCURInfos((String) cur_json.get("base")).get("full_name"),
                    (String) cur_json.get("base"),
                    data.getCURInfos((String) cur_json.get("base")).get("flag_name"),
                    null,
                    1,
                    (Long) cur_json.get("timestamp"));
            currencies.add(base);
            /* Doing all rates */
            JSONObject rates = (JSONObject) cur_json.get("rates");
            Iterator<Object> keys = rates.entrySet().iterator();
            while (keys.hasNext()) {
                Map.Entry pair = (Map.Entry)keys.next();
                String key = (String) pair.getKey();
                double rate = Double.valueOf(String.valueOf(pair.getValue()));
                HashMap<String, String> infos = data.getCURInfos(key);
                Currency currency = new Currency(infos.get("full_name"), key, infos.get("flag_name"), base, rate, (Long) cur_json.get("timestamp"));
                currencies.add(currency);
            }
            return currencies;
        } catch (FileNotFoundException e) {
            System.out.println("FATAL ERROR : ReadJSONFile [1] : File not found - trying to update - You need to restart");
            UpdateCurrency.update();
            System.exit(0);
            return null;
        } catch (ParseException e) {
            System.out.println("FATAL ERROR : ReadJSONFile [2] : Error when parsing JSON. Try update");
            System.exit(0);
            return null;
        } catch(IOException e) {
            System.out.println("FATAL ERROR : ReadJSONFile [3] : Error when parsing JSON. Try update");
            System.out.println(e);
            System.exit(0);
            return null;
        }
    }
}
