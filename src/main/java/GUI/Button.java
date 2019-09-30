package GUI;

import javax.swing.*;

public abstract class Button extends JButton {

    public Button(String title, int x, int y) {
        super(title);
        super.setLocation(x, y);
    }

}
