import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class Table extends JPanel
{
    protected Card[][] table;
    Random gen = new Random();
    protected static ArrayList<String> cards = new ArrayList<String>();
    private static int currentValue;
    private static int number;
    private int cardsLeft = 52;
    private int cardsInDeck = 42;
    ControlPanel cp;

    protected String twos[] = 
        {"2clubs.gif","2spades.gif","2hearts.gif","2diamonds.gif",
            "3clubs.gif","3spades.gif","3hearts.gif","3diamonds.gif",
            "4clubs.gif","4spades.gif","4hearts.gif","4diamonds.gif",
            "5clubs.gif","5spades.gif","5hearts.gif","5diamonds.gif",
            "6clubs.gif","6spades.gif","6hearts.gif","6diamonds.gif",
            "7clubs.gif","7spades.gif","7hearts.gif","7diamonds.gif",
            "8clubs.gif","8spades.gif","8hearts.gif","8diamonds.gif",
            "9clubs.gif","9spades.gif","9hearts.gif","9diamonds.gif",
            "10clubs.gif","10spades.gif","10hearts.gif","10diamonds.gif",
            "11clubs.gif","11spades.gif","11hearts.gif","11diamonds.gif",
            "12clubs.gif","12spades.gif","12hearts.gif","12diamonds.gif",
            "13clubs.gif","13spades.gif","13hearts.gif","13diamonds.gif",
            "1clubs.gif","1spades.gif","1hearts.gif","1diamonds.gif"}; 

    private String x = twos[1];

    public Table()
    {


        table = new Card[2][5];
        setLayout(new GridLayout(2,5));

        for(int r = 0; r<twos.length;r++){
            cards.add(twos[r]);
        }
        for(int loopR = 0;loopR<2;loopR++){
            for(int loopC = 0;loopC<5;loopC++){
                final int r = loopR;
                final int c = loopC;
                int rNum = gen.nextInt(cards.size());

                table[r][c]= new Card("deck",cards.get(rNum), 2);
                cards.remove(rNum);
                add(table[r][c]);

                table[r][c].addActionListener(
                    new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent event){
                            BufferedImage img2 = null;
                            BufferedImage img = null;
                            int pos = table[r][c].getName().indexOf(".");
                            String s = table[r][c].getName().substring(0,pos) + "S" + table[r][c].getName().substring(pos, table[r][c].getName().length());
                            try{
                                img = ImageIO.read(new File(table[r][c].getPat()+"/"+table[r][c].getName()));
                            }catch(IOException e){
                                e.printStackTrace();
                            }
                            try{
                                img2 = ImageIO.read(new File(table[r][c].getPat()+"/"+s));
                            }catch (IOException e){
                                e.printStackTrace();
                            }                
                            if(!table[r][c].isAlive()){
                                ImageIcon imgFace2 = new ImageIcon(img2);
                                table[r][c].setIcon(imgFace2);
                                table[r][c].changeState();
                                number++;
                                currentValue = currentValue + table[r][c].getValue();
                            }else{
                                ImageIcon imgFace = new ImageIcon(img);
                                table[r][c].setIcon(imgFace);
                                table[r][c].changeState();
                                number--;
                                currentValue = currentValue - table[r][c].getValue();
                            }           

                            if(number == 2){
                                if(currentValue == 13){
                                    number = 0;
                                    currentValue = 0;
                                    if(cards.size() > 1){
                                        for(int r = 0; r < 2; r++){
                                            for(int c = 0; c < 5; c++){
                                                int num;
                                                try{
                                                    num = gen.nextInt(cards.size());
                                                }catch(Exception e){
                                                    num = gen.nextInt(1);
                                                }
                                                int rNum = num;
                                                if(rNum > cards.size()){
                                                    rNum = cards.size();
                                                }
                                                if(table[r][c].isAlive()){

                                                    Card card = new Card("deck", cards.get(rNum), 2);
                                                    String fname = card.getName();
                                                    try{
                                                        String str = fname.substring(0,2);
                                                        int val = Integer.parseInt(str);
                                                        card.setValue(val);
                                                    }
                                                    catch(Exception e){
                                                        int val = Integer.parseInt(fname.substring(0,1));
                                                        card.setValue(val);
                                                    }
                                                    int value = card.getValue();

                                                    table[r][c].setValue(value);
                                                    table[r][c].setName(fname);
                                                    BufferedImage image = null;
                                                    int position = card.getName().indexOf(".");
                                                    try{
                                                        image = ImageIO.read(new File(table[r][c].getPat()+"/"+fname));
                                                    }catch(IOException e){
                                                        e.printStackTrace();
                                                    }
                                                    ImageIcon img4 = new ImageIcon(image);
                                                    table[r][c].setIcon(img4);
                                                    table[r][c].changeState();
                                                    cards.remove(rNum);
                                                    revalidate();
                                                    repaint();
                                                }
                                            }
                                        }
                                        cardsInDeck -= 2;
                                        cardsLeft -= 2;
                                    }else if(cards.size() == 0){
                                        for(int r = 0; r < 2; r++){
                                            for(int c = 0; c < 5; c++){
                                                if(table[r][c].isAlive()){
                                                    remove(table[r][c]);
                                                    revalidate();
                                                    repaint();
                                                }
                                            }
                                        }
                                    }else if(cards.size() == 1){
                                        Card card = new Card("deck", cards.get(0), 2);
                                        String fname = card.getName();
                                        try{
                                            String str = fname.substring(0,2);
                                            int val = Integer.parseInt(str);
                                            card.setValue(val);
                                        }
                                        catch(Exception e){
                                            int val = Integer.parseInt(fname.substring(0,1));
                                            card.setValue(val);
                                        }
                                        int value = card.getValue();

                                        table[r][c].setValue(value);
                                        table[r][c].setName(fname);
                                        BufferedImage image = null;
                                        int position = card.getName().indexOf(".");
                                        try{
                                            image = ImageIO.read(new File(table[r][c].getPat()+"/"+fname));
                                        }catch(IOException e){
                                            e.printStackTrace();
                                        }
                                        ImageIcon img4 = new ImageIcon(image);
                                        table[r][c].setIcon(img4);
                                        table[r][c].changeState();
                                        cards.remove(0);
                                        revalidate();
                                        repaint();
                                        cardsInDeck--;
                                        cardsLeft -= 2;
                                        for(int r = 0; r < 2; r++){
                                            for(int c = 0; c < 5; c++){
                                                if(table[r][c].isAlive()){
                                                    remove(table[r][c]);
                                                }
                                            }
                                        }

                                    }

                                }else{
                                    currentValue = 0;
                                    number = 0;
                                    for(int r = 0; r < 2; r++){
                                        for(int c = 0; c < 5; c++){
                                            if(table[r][c].isAlive()){
                                                BufferedImage imgur = null;
                                                int pos1 = table[r][c].getName().indexOf(".");
                                                try{
                                                    img = ImageIO.read(new File(table[r][c].getPat()+"/"+table[r][c].getName()));
                                                }catch(IOException e){
                                                    e.printStackTrace();
                                                }
                                                ImageIcon theImage = new ImageIcon(img);
                                                table[r][c].setIcon(theImage);
                                                table[r][c].changeState();
                                            }
                                        }
                                    }
                                }
                            }else if(number == 1){
                                if(currentValue == 13){
                                    int num;
                                    try{
                                        num = gen.nextInt(cards.size());
                                    }catch(Exception e){
                                        num = gen.nextInt(1);
                                    }
                                    int rNum = num;
                                    number = 0;
                                    currentValue = 0;
                                    if(cardsInDeck > 0){
                                        for(int r = 0; r < 2; r++){
                                            for(int c = 0; c < 5; c++){
                                                if(table[r][c].isAlive()){
                                                    num = gen.nextInt(cards.size());
                                                    Card card = new Card("deck", cards.get(rNum), 2);

                                                    String fname = card.getName();
                                                    try{
                                                        String str = fname.substring(0,2);
                                                        int val = Integer.parseInt(str);
                                                        card.setValue(val);
                                                    }
                                                    catch(Exception e){
                                                        int val = Integer.parseInt(fname.substring(0,1));
                                                        card.setValue(val);
                                                    }

                                                    table[r][c].setValue(card.getValue());
                                                    table[r][c].setName(fname);
                                                    BufferedImage image = null;
                                                    int position = card.getName().indexOf(".");
                                                    try{
                                                        image = ImageIO.read(new File(table[r][c].getPat()+"/"+fname));
                                                    }catch(IOException e){
                                                        e.printStackTrace();
                                                    }
                                                    ImageIcon img4 = new ImageIcon(image);
                                                    table[r][c].setIcon(img4);
                                                    table[r][c].changeState();
                                                    cards.remove(rNum);
                                                    revalidate();
                                                    repaint();

                                                }
                                            }
                                        }
                                        cardsInDeck--;
                                        cardsLeft--;
                                    }else{
                                        remove(table[r][c]);
                                        revalidate();
                                        repaint();
                                    }
                                    cardsLeft--;
                                }
                            }
                           
                        }
                    }
                );

                try{
                    String str = table[r][c].getName().substring(0,2);
                    int val = Integer.parseInt(str);
                    table[r][c].setValue(val);
                }
                catch(Exception e){
                    int val = Integer.parseInt(table[r][c].getName().substring(0,1));
                    table[r][c].setValue(val);
                }
            }

        }
        setCP(cp);
    }

    public void reset()
    {
        cards.clear();
        for(int r = 0; r<twos.length;r++){
            cards.add(twos[r]);
        }
        for(int r = 0; r < 2; r++){
            for(int c = 0; c < 5; c++){
                remove(table[r][c]);
                revalidate();
                repaint();
            }
        }
        cardsLeft = 52;
        cardsInDeck = 42;
        table = new Card[2][5];
        setLayout(new GridLayout(2,5));
        for(int loopR = 0;loopR<2;loopR++){
            for(int loopC = 0;loopC<5;loopC++){
                final int r = loopR;
                final int c = loopC;
                int rNum = gen.nextInt(cards.size());

                table[r][c]= new Card("deck",cards.get(rNum), 2);
                cards.remove(rNum);
                add(table[r][c]);

                table[r][c].addActionListener(
                    new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent event){
                            BufferedImage img2 = null;
                            BufferedImage img = null;
                            int pos = table[r][c].getName().indexOf(".");
                            String s = table[r][c].getName().substring(0,pos) + "S" + table[r][c].getName().substring(pos, table[r][c].getName().length());
                            try{
                                img = ImageIO.read(new File(table[r][c].getPat()+"/"+table[r][c].getName()));
                            }catch(IOException e){
                                e.printStackTrace();
                            }
                            try{
                                img2 = ImageIO.read(new File(table[r][c].getPat()+"/"+s));
                            }catch (IOException e){
                                e.printStackTrace();
                            }                
                            if(!table[r][c].isAlive()){
                                ImageIcon imgFace2 = new ImageIcon(img2);
                                table[r][c].setIcon(imgFace2);
                                table[r][c].changeState();
                                number++;
                                currentValue = currentValue + table[r][c].getValue();
                            }else{
                                ImageIcon imgFace = new ImageIcon(img);
                                table[r][c].setIcon(imgFace);
                                table[r][c].changeState();
                                number--;
                                currentValue = currentValue - table[r][c].getValue();
                            }           

                            if(number == 2){
                                if(currentValue == 13){
                                    number = 0;
                                    currentValue = 0;
                                    if(cards.size() > 1){
                                        for(int r = 0; r < 2; r++){
                                            for(int c = 0; c < 5; c++){
                                                int num;
                                                try{
                                                    num = gen.nextInt(cards.size());
                                                }catch(Exception e){
                                                    num = gen.nextInt(1);
                                                }
                                                int rNum = num;
                                                if(rNum > cards.size()){
                                                    rNum = cards.size();
                                                }
                                                if(table[r][c].isAlive()){

                                                    Card card = new Card("deck", cards.get(rNum), 2);
                                                    String fname = card.getName();
                                                    try{
                                                        String str = fname.substring(0,2);
                                                        int val = Integer.parseInt(str);
                                                        card.setValue(val);
                                                    }
                                                    catch(Exception e){
                                                        int val = Integer.parseInt(fname.substring(0,1));
                                                        card.setValue(val);
                                                    }
                                                    int value = card.getValue();

                                                    table[r][c].setValue(value);
                                                    table[r][c].setName(fname);
                                                    BufferedImage image = null;
                                                    int position = card.getName().indexOf(".");
                                                    try{
                                                        image = ImageIO.read(new File(table[r][c].getPat()+"/"+fname));
                                                    }catch(IOException e){
                                                        e.printStackTrace();
                                                    }
                                                    ImageIcon img4 = new ImageIcon(image);
                                                    table[r][c].setIcon(img4);
                                                    table[r][c].changeState();
                                                    cards.remove(rNum);
                                                    revalidate();
                                                    repaint();
                                                }
                                            }
                                        }
                                        cardsInDeck -= 2;
                                        cardsLeft -= 2;
                                    }else if(cards.size() == 0){
                                        for(int r = 0; r < 2; r++){
                                            for(int c = 0; c < 5; c++){
                                                if(table[r][c].isAlive()){
                                                    remove(table[r][c]);
                                                    revalidate();
                                                    repaint();
                                                }
                                            }
                                        }
                                    }else if(cards.size() == 1){
                                        Card card = new Card("deck", cards.get(0), 2);
                                        String fname = card.getName();
                                        try{
                                            String str = fname.substring(0,2);
                                            int val = Integer.parseInt(str);
                                            card.setValue(val);
                                        }
                                        catch(Exception e){
                                            int val = Integer.parseInt(fname.substring(0,1));
                                            card.setValue(val);
                                        }
                                        int value = card.getValue();

                                        table[r][c].setValue(value);
                                        table[r][c].setName(fname);
                                        BufferedImage image = null;
                                        int position = card.getName().indexOf(".");
                                        try{
                                            image = ImageIO.read(new File(table[r][c].getPat()+"/"+fname));
                                        }catch(IOException e){
                                            e.printStackTrace();
                                        }
                                        ImageIcon img4 = new ImageIcon(image);
                                        table[r][c].setIcon(img4);
                                        table[r][c].changeState();
                                        cards.remove(0);
                                        revalidate();
                                        repaint();
                                        cardsInDeck--;
                                        cardsLeft -= 2;
                                        for(int r = 0; r < 2; r++){
                                            for(int c = 0; c < 5; c++){
                                                if(table[r][c].isAlive()){
                                                    remove(table[r][c]);
                                                }
                                            }
                                        }

                                    }

                                }else{
                                    currentValue = 0;
                                    number = 0;
                                    for(int r = 0; r < 2; r++){
                                        for(int c = 0; c < 5; c++){
                                            if(table[r][c].isAlive()){
                                                BufferedImage imgur = null;
                                                int pos1 = table[r][c].getName().indexOf(".");
                                                try{
                                                    img = ImageIO.read(new File(table[r][c].getPat()+"/"+table[r][c].getName()));
                                                }catch(IOException e){
                                                    e.printStackTrace();
                                                }
                                                ImageIcon theImage = new ImageIcon(img);
                                                table[r][c].setIcon(theImage);
                                                table[r][c].changeState();
                                            }
                                        }
                                    }
                                }
                            }else if(number == 1){
                                if(currentValue == 13){
                                    int num;
                                    try{
                                        num = gen.nextInt(cards.size());
                                    }catch(Exception e){
                                        num = gen.nextInt(1);
                                    }
                                    int rNum = num;
                                    number = 0;
                                    currentValue = 0;
                                    if(cardsInDeck > 0){
                                        for(int r = 0; r < 2; r++){
                                            for(int c = 0; c < 5; c++){
                                                if(table[r][c].isAlive()){
                                                    num = gen.nextInt(cards.size());
                                                    Card card = new Card("deck", cards.get(rNum), 2);

                                                    String fname = card.getName();
                                                    try{
                                                        String str = fname.substring(0,2);
                                                        int val = Integer.parseInt(str);
                                                        card.setValue(val);
                                                    }
                                                    catch(Exception e){
                                                        int val = Integer.parseInt(fname.substring(0,1));
                                                        card.setValue(val);
                                                    }

                                                    table[r][c].setValue(card.getValue());
                                                    table[r][c].setName(fname);
                                                    BufferedImage image = null;
                                                    int position = card.getName().indexOf(".");
                                                    try{
                                                        image = ImageIO.read(new File(table[r][c].getPat()+"/"+fname));
                                                    }catch(IOException e){
                                                        e.printStackTrace();
                                                    }
                                                    ImageIcon img4 = new ImageIcon(image);
                                                    table[r][c].setIcon(img4);
                                                    table[r][c].changeState();
                                                    cards.remove(rNum);
                                                    revalidate();
                                                    repaint();

                                                }
                                            }
                                        }
                                        cardsInDeck--;
                                        cardsLeft--;
                                    }else{
                                        remove(table[r][c]);
                                        revalidate();
                                        repaint();
                                    }
                                    cardsLeft--;
                                }
                            }
                            if(cardsLeft == 0){
                                reset();
                            }
                        }
                    }
                );

                try{
                    String str = table[r][c].getName().substring(0,2);
                    int val = Integer.parseInt(str);
                    table[r][c].setValue(val);
                }
                catch(Exception e){
                    int val = Integer.parseInt(table[r][c].getName().substring(0,1));
                    table[r][c].setValue(val);
                }
            }

        }
        setCP(cp);

    }

    public void setCP(ControlPanel c)
    {
        cp = c;
    }

    public int getCardsLeft()
    {
        return cardsLeft;
    }

}
