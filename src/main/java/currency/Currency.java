package currency;

import java.awt.*;
import java.util.Date;

public class Currency {
    private String full_name;
    private String short_name;
    private String flag_name;
    private Currency base;
    private double rates;
    private Date update_date;
    private Image image = null;

    public Currency(String full_name, String short_name, String flag_name, Currency base, double rates, long date_update)
    {
        this.full_name = full_name;
        this.short_name = short_name;
        this.base = base;
        this.rates = rates;
        this.update_date = new Date(date_update * 1000);
        this.flag_name = flag_name;
    }

    //-> Getter
    public String getFull_name() {
        if (full_name.equals("Unknown"))
            return short_name;
        return full_name + " (" + short_name + ")";
    }
    public String getShort_name() {
        return short_name;
    }
    public String getFlag_name() {
        return flag_name;
    }
    public Currency getBase() {
        if (base == null)
            return this;
        return base;
    }
    public double getRates() {
        return rates;
    }
    public Date getUpdate_date() {
        return update_date;
    }
    public String getFlagURL() {
        return ("https://www.countryflags.io/" + getFlag_name() + "/flat/64.png");
    }
    public Image getImage() {
        return image;
    }

    //-> Setter
    public void setImage(Image image) {
        this.image = image;
    }
}
