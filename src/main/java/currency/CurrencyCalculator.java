package currency;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CurrencyCalculator {

    private DecimalFormat df = new DecimalFormat("#.###");
    private Currency base;
    private ArrayList<Currency> currencies;

    public CurrencyCalculator(Currency base) {
        this.base = base;
        df.setRoundingMode(RoundingMode.CEILING);
    }
    public CurrencyCalculator(Currency base, ArrayList<Currency> currencies) {
        this.base = base;
        this.currencies = currencies;
        df.setRoundingMode(RoundingMode.CEILING);
    }

    //-> Setter

    public void setBase(Currency base) {
        this.base = base;
    }
    public void setCurrencies(ArrayList<Currency> currencies) {
        this.currencies = currencies;
    }
    public void addCurrency(Currency c) {
        this.currencies.add(c);
    }
    public void remCurrency(Currency c ){
        this.currencies.remove(c);
    }

    //-> Getter

    public DecimalFormat getDf() {
        return this.df;
    }

    public Currency getBase() {
        return base;
    }
    public ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    //-> Public Method
    public double getValueFor(Currency cur) {
        Double result = cur.getRates() / base.getRates();
        return (result);
    }

    public double getValueFor(Currency cur, double multiplicator) {
        return (getValueFor(cur) * multiplicator);
    }
    public HashMap<Currency, Double> getValues() {
        HashMap<Currency, Double> result = new HashMap<Currency, Double>();
        for (Currency c : currencies) {
            result.put(c, getValueFor(c));
        }
        return result;
    }
}
