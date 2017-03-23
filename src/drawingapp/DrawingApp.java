/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingapp;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Darren Combs
 */
public class DrawingApp extends JComponent{
  //Image in which we will draw  
private BufferedImage BGimage, ULimage;
private Image image;
//used to draw on
private Graphics2D g2, g2n;
private Graphics gtemp;
ImageIcon fileIcon;
//Mouse coordinates
private int currentX, currentY, oldX, oldY;
private char c[] = new char[3];
Color white = new Color(255,255,255);


//Flag for underlay
boolean loadnow = false;
    //File
    File localfile = null;
    String filename = null;
public DrawingApp() {
    setDoubleBuffered(true);   
    addMouseListener(new MouseAdapter() {
       @Override 
       public void mousePressed(MouseEvent e){
           //save coord when mouse pressed
           oldX = e.getX();
           oldY = e.getY();
           if (g2 != null){
           //(char[], offset, length, x, y,)
           //-5 & +5 to help center the chars on the mouse
           g2.drawChars(c, 0, c.length, oldX-5, oldY+5);
           repaint();
           }
       } 
    });
    
   addMouseMotionListener(new MouseMotionAdapter(){        
        @Override
        public void mouseDragged(MouseEvent e){
        currentX = e.getX();
        currentY = e.getY();
        
        if (g2 != null) {
            // draw line if context is not null
            g2.drawLine(oldX, oldY, currentX, currentY);
            repaint();
            oldX = currentX;
            oldY = currentY;
        }
        }         
   });
   }
@Override
protected void paintComponent(Graphics g){
//super.paintComponent(g);
    System.out.println("In Paint Component");
    if(ULimage == null){
        System.out.println("In No Underlay");
   
    }else if (ULimage != null && loadnow == false){
        System.out.println("In Underlay Update");
        g.drawImage(ULimage,0,0,null);
    }
    //loadnow = false;  
}
//Create Exposed Methods
public void clear() {
    g2.setPaint(Color.white);
    g2.fillRect(0, 0, getSize().width, getSize().height);
    g2.setPaint(Color.black);
    repaint();
}
public void red(){
   g2.setPaint(Color.red); 
}
public void black(){
    g2.setPaint(Color.black);
}
public void green(){
    g2.setPaint(Color.green);
}
public void blue(){
    g2.setPaint(Color.blue);
}
public void getchar(char[] c){
    this.c = c;
}
protected void underlay(){
    //Get File 
    JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Pictures", "jpg", "png");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            localfile = fc.getSelectedFile();
            filename = localfile.getAbsolutePath();            
        }else{return;}       
        ULimage = null;
        try{
        //URL url = new URL(new File(filename));
        ULimage = ImageIO.read(new File(filename));
        }catch(IOException e){
            e.printStackTrace();
        }
        //Create Graphics Object
        g2 = ULimage.createGraphics();
        //Establish AntiAliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        //Send to Paint Component to Show Image on Screen
        repaint();       
}
}

