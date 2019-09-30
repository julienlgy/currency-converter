import currency.Currency;
import currency.CurrencyCalculator;
import currency.CurrencyManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestCurrencyCalculator {
    CurrencyManager cm = new CurrencyManager();
    CurrencyCalculator cc = new CurrencyCalculator(cm.get("USD"));

    @Test
    void getInstance() {
        System.out.println(" | TEST | > currency/CurrencyCalculator [1] | Instance success");
        assertNotNull(cm);
        assertNotNull(cc);
    }

    void EurUsdSwitching() {
        System.out.println(" | TEST | > currency/CurrencyCalculator [2] | Trying to switch USD/EUR comparison");
        double usd_to_eur = cc.getValueFor(cm.get("EUR"));
        cc.setBase(cm.get("EUR"));
        double eur_to_usd = cc.getValueFor(cm.get("USD"));
    }

    @Test
    void EurChfMultiplicator() {
        System.out.println(" | TEST | > currency/CurrencyCalculator [3] | Result for 500€ > x CHF");
        cc.setBase(cm.get("EUR"));
        double result = cc.getValueFor(cm.get("CHF"), 500);
        assertEquals(cm.get("CHF").getRates() * 500, result);
    }

}