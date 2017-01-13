import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ControlPanel extends JPanel
{
    private Table table;
    public ControlPanel(final Table t)
    {
        super();
        table = t;
        setLayout(new FlowLayout());
        
        JButton reset = new JButton("Reset");
        add(reset);
        reset.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    t.reset();
                }
            }
        );
        
        
    }
}
