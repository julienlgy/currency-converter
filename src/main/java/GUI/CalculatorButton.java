package GUI;

import Calculator.Traductor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalculatorButton extends Button implements ActionListener {

    private final String[] operators = new String[] {"+", "-", "*", "/"};

    public CalculatorButton(String text, int x, int y) {
        super(text, x, y);
        this.addMouseListener(hoverButton(this));
        super.addActionListener(this);
    }


    public MouseAdapter hoverButton(Button button) {
        MouseAdapter res = new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (Window.isDark) {
                    button.setBackground(Color.LIGHT_GRAY);
                    button.setForeground(Color.BLACK);
                }
                else {
                    button.setBackground(Color.ORANGE);
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (Window.isDark) {
                    button.setBackground(Color.GRAY);
                    button.setForeground(Color.LIGHT_GRAY);
                }
                else {
                    button.setBackground(Color.WHITE);
                }
            }
        };
        return res;
    }

    public void actionPerformed(ActionEvent event) {
        switch(event.getActionCommand()) {
            case "C":
                Window.output.setText("1");
                break;
            case "=":
                Window.output.setText("" + Traductor.getInstance().strToDouble(Window.output.getText()));
                break;
            case "DEL":
                if (Window.output.getText().length() != 0) {
                    if (Window.output.getText().length() == 1)
                        Window.output.setText("1");
                    else
                        Window.output.setText(Window.output.getText().substring(0, Window.output.getText().length() - 1));
                }
                break;
            default:
                if ((Window.output.getText() == "1" || Window.output.getText() == "0.0") && !Arrays.asList(operators).contains(event.getActionCommand()))
                    Window.output.setText(event.getActionCommand());
                else
                    Window.output.setText(Window.output.getText() + event.getActionCommand());
        }
    }

}
