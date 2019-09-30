package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DarkButton extends JToggleButton {

    public DarkButton() {
        super("Bright");
        this.setPreferredSize(new Dimension(90, 30));
        super.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    setText("Dark");
                    Window.brightTheme();
                } else if(e.getStateChange()==ItemEvent.DESELECTED){
                    setText("Bright");
                    Window.darkTheme();
                }
            }
        });
    }

}
