package GUI;

import currency.Currency;
import currency.CurrencyCalculator;
import currency.CurrencyManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CurrencyList extends JPanel {

    private HashMap<Currency, JPanel> currenciesJ;
    private ArrayList<Currency> currencies;
    public CurrencyManager cm = new CurrencyManager();
    public CurrencyCalculator cc = new CurrencyCalculator(cm.get("EUR"));
    private JPanel gl = new JPanel();

    private static CurrencyList currencyList = null;

    public static CurrencyList getInstance() {
        if (currencyList == null)
            currencyList= new CurrencyList();
        return currencyList;
    }

    public CurrencyList() {
        super();
        System.out.println("GUI/CurrencyList : Preparing visual content for the currency.");
        this.setLayout(new BorderLayout());
        gl.setLayout(new GridLayout(5,-1));
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        currenciesJ = new HashMap<>();
        currencies = new ArrayList<>();

        currenciesJ.put(cm.get("EUR"),getJPanelFor(cm.get("EUR")));
        currenciesJ.put(cm.get("USD"),getJPanelFor(cm.get("USD")));
        currenciesJ.put(cm.get("JPY"),getJPanelFor(cm.get("JPY")));
        currenciesJ.put(cm.get("CHF"),getJPanelFor(cm.get("CHF")));

        Iterator<Map.Entry<Currency, JPanel>> iterator = currenciesJ.entrySet ().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Currency, JPanel> entry = iterator.next();
            currencies.add(entry.getKey());
            gl.add(entry.getValue());
        }
        setBase(cm.get("EUR"));

        this.add(gl,BorderLayout.CENTER);
        this.add(getAddButton(), BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(600,330));
        this.setVisible(true);
        currencyList = this;
    }

    public void setBase(Currency c) {
        for (JPanel jp : currenciesJ.values()) {
            jp.setBackground(Color.WHITE);
        }
        cc.setBase(c);
        currenciesJ.get(c).setBackground(Color.LIGHT_GRAY);
        updateResult();
    }

    public void remCur(Currency c ) {
        gl.remove(currenciesJ.get(c));
        currenciesJ.remove(c);
        currencies.remove(c);
        if (c == cc.getBase() && currencies.size() > 0)
            setBase(currencies.get(0));
        updateResult();
        updateUI();
    }

    public void addCur(Currency c) {
        currenciesJ.put(c, getJPanelFor(c));
        gl.add(currenciesJ.get(c));
        currencies.add(c);
        updateResult();
    }

    public void updateResult() {
        String result = Window.output.getText();
        Double dresult;
        try {
            dresult = Double.parseDouble(result);
        } catch (Exception e) {
            return;
        }
        Iterator<Map.Entry<Currency, JPanel>> iterator = currenciesJ.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<Currency, JPanel> entry = iterator.next();
            ((JLabel) entry.getValue().getComponent(2)).setText(cc.getDf().format(cc.getValueFor(entry.getKey(), dresult)));
        }

    }

    private JPanel getJPanelFor(Currency cur) {
        JPanel curPan = new JPanel();
        curPan.setBackground(Color.WHITE);
        curPan.setLayout(new BorderLayout());

        MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    remCur(cm.get(((JLabel) ((JLabel) evt.getSource()).getParent().getComponent(1)).getText()));
                } else {
                    setBase(cm.get(((JLabel) ((JLabel) evt.getSource()).getParent().getComponent(1)).getText()));
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };

        /* LEFT */
        JLabel imageIcon;
        try {
            URL url = new URL(cur.getFlagURL());
            cur.setImage(ImageIO.read(url));
            imageIcon = new JLabel(new ImageIcon(cur.getImage()), JLabel.LEFT);
        } catch (NullPointerException e) {
            imageIcon = new JLabel("                 ", JLabel.LEFT);
        } catch (IOException e) {
            imageIcon = new JLabel("                 ", JLabel.LEFT);
        }
        imageIcon.addMouseListener(ml);
        /* CENTER */
        JLabel curShort = new JLabel(cur.getFull_name(), JLabel.LEFT);
        curShort.addMouseListener(ml);
        /* RIGHT */
        JLabel RateCur = new JLabel(String.valueOf(cur.getRates()), JLabel.RIGHT);
        RateCur.addMouseListener(ml);
        curPan.add(imageIcon, BorderLayout.WEST);
        curPan.add(curShort, BorderLayout.CENTER);
        curPan.add(RateCur, BorderLayout.EAST);

        return curPan;
    }

    private JPanel getAddButton() {
        JPanel addButton = new JPanel();
        addButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        addButton.setLayout(new BorderLayout());
        JButton button = new JButton("Add");
        addButton.add(button, BorderLayout.SOUTH);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrencyListAdd cla = new CurrencyListAdd(cm.getRemaining(currencies));
            }
        });
        return addButton;
    }

}
