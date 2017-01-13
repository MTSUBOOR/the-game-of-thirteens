import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Card extends JButton
{
    private String face;
    private String suit;
    private int value;
    private boolean clicked;
    private boolean alive;
    private String pat;
    private String fname;

    //private String ImgStorage[] = {"2clubs.jpg","2clubsS.jpg","2diamonds.gif"};
    public Card(String path, String filename, int value)
    {
        int pos = filename.indexOf(".");
        this.face = filename.substring(0,pos-1);
        this.suit = filename.substring(pos-1,pos);
        fname = filename;
        this.value = value;
        BufferedImage img = null;
        pat = path;
        alive = false;
        try{
            img = ImageIO.read(new File(pat+ "/"+fname));
        }catch(IOException e){
            e.printStackTrace();
        }

        ImageIcon imgFace = new ImageIcon(img);
        setIcon(imgFace);

        
        setVisible(true);
        //update();
    }

    public boolean isAlive(){
        return alive;        
    }
    
    public void setName(String s)
    {
        fname = s;
    }

    public void changeState(){
        alive = !alive;
        update();
    }

    private void update(){
        if(alive){
            this.setBackground(Color.GREEN);
        }else{
            this.setBackground(null);
        }
    }

    public String getName()
    {
        return fname;
    }

    public void setValue(int v)
    {
        value = v;
    }

    public int getValue()
    {
        return value;
    }

    public String getPat()
    {
        return pat;
    }
}
