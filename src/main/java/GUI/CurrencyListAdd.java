package GUI;

import currency.Currency;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class CurrencyListAdd {
    private String clicked;

    public CurrencyListAdd(ArrayList<Currency> currencies) {
        JFrame frame = new JFrame("Add a currency");
        frame.setSize(300, 900);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        List<String> currenciesName = new ArrayList<>();
        for (Currency c : currencies)
            currenciesName.add(c.getFull_name() + " - " + c.getRates() + " [" + c.getBase().getShort_name() + "]");
        JList list = new JList(currenciesName.toArray());
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                clicked = ((String) list.getSelectedValue()).split("-")[0];
                clicked = clicked.substring(0, clicked.length() - 1);
            }
        });
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    CurrencyList.getInstance().addCur(CurrencyList.getInstance().cm.get(clicked));
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    // Double-click detected

                }
            }
        });

        frame.add(new JScrollPane(list));

        frame.setVisible(true);
    }
}
