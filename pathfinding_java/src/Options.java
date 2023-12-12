import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

public class Options extends JCheckBox implements ActionListener {
    private final Font font = new Font("Consolas", Font.PLAIN, 20);
    public boolean clicked;

    public Options(String text) {
        setText(text);
        setFocusable(false);
        setFont(font);
        addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clicked = isSelected();
    }

}