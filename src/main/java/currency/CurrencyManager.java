package currency;

import currency.json.ReadJSONFile;

import java.util.ArrayList;
import java.util.Date;

public class CurrencyManager {
    private ArrayList<Currency> currencies;

    public CurrencyManager(ArrayList<Currency> currencies) {
        this.currencies = currencies;
    }

    public CurrencyManager() {
        currencies = ReadJSONFile.get();
    }

    //-> Setter
    public void setDatabase(ArrayList<Currency> currencies) {
        this.currencies = currencies;
    }

    //-> Public methods
    public Currency get(String short_name) {
        for (Currency c : currencies) {
            if (c.getShort_name().equals(short_name)) {
                return c;
            }
        }
        for (Currency c : currencies) {
            if (c.getFull_name().equals((short_name))) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Currency> getCurrencies() {
        return (this.currencies);
    }

    public Date getDateUpdate() {
        return (get("EUR").getUpdate_date());
    }

    public ArrayList<Currency> getRemaining(ArrayList<Currency> alrCur) {
        ArrayList<Currency> result = new ArrayList<>();
        for (Currency c : currencies) {
            boolean alr = false;
            for (Currency ac : alrCur) {
                if (c == ac)
                    alr = true;
            }
            if (!alr)
                result.add(c);
        }
        return result;
    }
}
