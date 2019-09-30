import currency.Currency;
import currency.CurrencyManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestCurrencyManager {
    CurrencyManager cm = new CurrencyManager();

    @Test
    void getInstance() {
        System.out.println(" | TEST | > currency/CurrencyManager [1] | Trying to get an instance..");
        assertNotNull(cm);
    }

    @Test
    void getSpecificCurrency() {
        System.out.println(" | TEST | > currency/CurrencyManager [2] | Getting informations of a currency");
        Currency c = cm.get("USD");
        assertEquals("us", c.getFlag_name());
    }

    @Test
    void getNonExistingCurrency() {
        System.out.println(" | TEST | > currency/CurrencyManager [3] | Wrong currency getter Null expectation");
        Currency c = cm.get("HEHOOOLAFAMILLE");
        assertNull(c);
    }

    @Test
    void getRemainingCurrencies() {
        System.out.println(" | TEST | > currency/CurrencyManager [4] | getRemaining + Array = All size of the currencyManager");
        ArrayList<Currency> c = new ArrayList<>();
        c.add(cm.get("EUR"));
        c.add(cm.get("USD"));
        c.add(cm.get("CHF"));
        ArrayList<Currency> allRC = cm.getRemaining(c);
        ArrayList<Currency> allC = cm.getCurrencies();
        int nb_of_currencies = allC.size();
        int nb_of_remaining = allRC.size() + c.size();
        assertEquals(nb_of_currencies, nb_of_remaining);
    }

}