import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GUI extends JFrame
{
    private Table table;
    private ControlPanel cp;
    public GUI()
    {
        super();
        setLayout(new BorderLayout());
        table = new Table();
        cp = new ControlPanel(table);
        add(table, BorderLayout.CENTER);
        add(cp, BorderLayout.SOUTH);
        
        setTitle("Thirteens");
        setSize(400,275);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
