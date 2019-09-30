import currency.Currency;
import currency.CurrencyManager;
import currency.UpdateCurrency;
import currency.json.ReadJSONFile;
import currency.json.WriteJSONFile;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestCurrency {

    void updateTest() {
        System.out.println(" | TEST | > currency/Currency [1] : Update currencies");
        UpdateCurrency.update();
        ArrayList<Currency> currencyArrayList = ReadJSONFile.get();
        assertNotNull(currencyArrayList);
    }

    @Test
    void getInformationsAboutCurrency() {
        System.out.println(" | TEST | > currency/Currency [2] : Try to get informations about the Euros");
        Currency currency = (new CurrencyManager().get("EUR"));
        assertEquals("EUR", currency.getShort_name());
        assertEquals(1, currency.getRates());
        assertEquals("Euros (EUR)", currency.getFull_name());
        assertEquals("eu",currency.getFlag_name());
    }
}