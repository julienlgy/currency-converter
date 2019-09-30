package currency.data;

import java.util.HashMap;

public class CurrencyData {
    public static HashMap<String, HashMap<String, String>> CURInfos;

    public CurrencyData() {
        CURInfos = new HashMap<>();
        CURInfos.put("EUR",new HashMap<String,String>() {
            {
                put("full_name", "Euros");
                put("flag_name", "eu");
            }
        });
        CURInfos.put("USD",new HashMap<String,String>() {
            {
                put("full_name", "US Dollars");
                put("flag_name", "us");
            }
        });
        CURInfos.put("AED",new HashMap<String,String>() {
            {
                put("full_name", "Emirates Dirham");
                put("flag_name", "ae");
            }
        });
        CURInfos.put("AEF",new HashMap<String,String>() {
            {
                put("full_name", "Afghan Afghani");
                put("flag_name", "af");
            }
        });
        CURInfos.put("CAD",new HashMap<String,String>() {
            {
                put("full_name", "Canadian Dollar");
                put("flag_name", "ca");
            }
        });
        CURInfos.put("CHF",new HashMap<String,String>() {
            {
                put("full_name", "Swiss Franc");
                put("flag_name", "ch");
            }
        });
        CURInfos.put("JPY",new HashMap<String,String>() {
            {
                put("full_name", "Japan Yen");
                put("flag_name", "jp");
            }
        });
        CURInfos.put("GBP",new HashMap<String,String>() {
            {
                put("full_name", "Pound Sterling");
                put("flag_name", "uk");
            }
        });
        CURInfos.put("GGP",new HashMap<String,String>() {
            {
                put("full_name", "Guernsey pound");
                put("flag_name", "uk");
            }
        });
    }

    public HashMap<String, String> getCURInfos(String devise) {
        if (CURInfos.containsKey(devise)) {
            return CURInfos.get(devise);
        } else {
            return new HashMap<String,String>() {
                {
                    put("full_name", "Unknown");
                    put("flag_name", "aq");
                }
            };
        }
    }
}
