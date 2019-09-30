package GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Label extends JLabel {

    public Label() {
        super("0", SwingConstants.CENTER);
        this.setBorder(new EmptyBorder(0, 0, 0, 25));
        this.setOpaque(true);
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.WHITE);
    }
}
