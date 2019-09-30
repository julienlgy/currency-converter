package GUI;

import currency.UpdateCurrency;
import currency.json.ReadJSONFile;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

public class Window {

    private static ArrayList<Button> buttons = new ArrayList<Button>();
    public static JLabel output = new Label();
    public static boolean isDark = true;
    private JPanel status = new JPanel();
    private JPanel calculator = new JPanel();

    public Window() {
        System.out.println("GUI/Window : Loading components..");
        buttons.add(new CalculatorButton("DEL", 3, 0));
        buttons.add(new CalculatorButton("7", 0, 1));
        buttons.add(new CalculatorButton("8", 1, 1));
        buttons.add(new CalculatorButton("9", 2, 1));
        buttons.add(new CalculatorButton("C", 3, 1));
        buttons.add(new CalculatorButton("4", 0, 2));
        buttons.add(new CalculatorButton("5", 1, 2));
        buttons.add(new CalculatorButton("6", 2, 2));
        buttons.add(new CalculatorButton("*", 3, 2));
        buttons.add(new CalculatorButton("1", 0, 3));
        buttons.add(new CalculatorButton("2", 1, 3));
        buttons.add(new CalculatorButton("3", 2, 3));
        buttons.add(new CalculatorButton("/", 3, 3));
        buttons.add(new CalculatorButton("0", 0, 4));
        buttons.add(new CalculatorButton("+", 1, 4));
        buttons.add(new CalculatorButton("-", 2, 4));
        buttons.add(new CalculatorButton("=", 3, 4));

        JFrame frame = new JFrame("Currency converter");
        frame.setSize(600, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JPanel currency = new CurrencyList();

        calculator.setBounds(0, 330, 600, 500);
        calculator.setBackground(Color.DARK_GRAY);
        calculator.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 3;

        output.setPreferredSize(new Dimension(100, 25));
        output.setText("1");
        output.addPropertyChangeListener("text", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                ((CurrencyList) currency).updateResult();
            }
        });
        calculator.add(output, gc);
        gc.gridwidth = 1;
        for (Button button : buttons) {
            gc.weightx = gc.weighty = 2;
            gc.gridx = button.getX();
            gc.gridy = button.getY();
            button.setBackground(Color.GRAY);
            button.setForeground(Color.LIGHT_GRAY);
            button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            calculator.add(button, gc);
        }


        status.setBounds(0, 830, 600, 20);
        status.setBackground(Color.GREEN);
        status.setLayout(new BoxLayout(status, BoxLayout.X_AXIS));
        JLabel textStatus = new JLabel("Last updated : " + ((CurrencyList) currency).cm.getDateUpdate().toString(), JLabel.CENTER);
        textStatus.addMouseListener(new MouseListener() {
            private boolean update = false;
            @Override
            public void mouseClicked(MouseEvent e) {
                update=true;
                ((JLabel) e.getSource()).getParent().setBackground(Color.ORANGE);
                ((JLabel) e.getSource()).setText("Updating...");
                if (UpdateCurrency.update()) {
                    ((JLabel) e.getSource()).setText("Successfully Updated !");
                    ((JLabel) e.getSource()).getParent().setBackground(Color.GREEN);
                    ((CurrencyList) currency).cm.setDatabase(ReadJSONFile.get());
                } else {
                    ((JLabel) e.getSource()).setText("Can't update.. Please try again later.");
                    ((JLabel) e.getSource()).getParent().setBackground(Color.RED);
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
              if (!update)
                    ((JLabel) e.getSource()).setText("Are you sure you wan't to update ?");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!update)
                    ((JLabel) e.getSource()).setText("Last updated : " + ((CurrencyList) currency).cm.getDateUpdate().toString());
            }
        });
        status.add(textStatus);
        status.add(Box.createHorizontalGlue());

        JToggleButton darkTheme = new DarkButton();
        status.add(darkTheme);
        status.setBorder(new EmptyBorder(5, 5, 5, 5));

        frame.add(currency);
        frame.add(calculator);
        frame.add(status);
        frame.setVisible(true);
        ((CurrencyList) currency).updateResult();
    }

    //PROTOTYPE, JE NE SAIS PAS COMMENT LIER À DARKBUTTON
    public static void brightTheme() {
        for (Button button : buttons) {
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
        }
        isDark = false;
    }

    public static void darkTheme() {
        for (Button button : buttons) {
            button.setBackground(Color.GRAY);
            button.setForeground(Color.LIGHT_GRAY);
            isDark = true;
        }

    }

}
