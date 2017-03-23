/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingapp;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;



/**
 *
 * @author Darren Combs
 */
public class SwingPaint {

    JButton clearBtn, blackBtn, blueBtn, greenBtn, redBtn, underlayBtn;
    JTextField char_in;
    DrawingApp drawingapp;

    
    ActionListener actionListener = new ActionListener(){
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == clearBtn) {
            drawingapp.clear();
        }else if (e.getSource() == blackBtn){
            drawingapp.black();
        }else if (e.getSource() == blueBtn){
            drawingapp.blue();
        }else if (e.getSource() == greenBtn){
            drawingapp.green();
        }else if (e.getSource() == redBtn){
            drawingapp.red();
        }else if (e.getSource() == underlayBtn){
            //drawingapp.clear();
            drawingapp.underlay();
        }else if (e.getSource() == char_in){
            drawingapp.getchar(char_in.getText().toCharArray());
        }
    }    
    };
    public static void main(String[] args){

        new SwingPaint().show();
    }
        public void show() {
        //create main frame
       JFrame frame = new JFrame("Swing Paint");
       Container content = frame.getContentPane();
       //set layout on content pane
       content.setLayout(new BorderLayout());
       //create drawing area
       drawingapp = new DrawingApp();
       //add to content pane
       System.out.println("Before1");
       content.add(drawingapp, BorderLayout.CENTER);
       
       //create controls for colors and call clear feature
       JPanel controls = new JPanel();
       clearBtn = new JButton("Clear");
       clearBtn.addActionListener(actionListener);
       blackBtn = new JButton("Black");
       blackBtn.addActionListener(actionListener);
       blueBtn = new JButton("Blue");
       blueBtn.addActionListener(actionListener);
       greenBtn = new JButton("Green");
       greenBtn.addActionListener(actionListener);
       redBtn = new JButton("Red");
       redBtn.addActionListener(actionListener);
       underlayBtn = new JButton("Underlay");
       underlayBtn.addActionListener(actionListener);
       char_in = new JTextField("A1",4);
       char_in.addActionListener(actionListener);
       
       //Add controls
       controls.add(blackBtn);
       controls.add(blueBtn);
       controls.add(greenBtn);
       controls.add(redBtn);
       controls.add(clearBtn);
       controls.add(underlayBtn);
       controls.add(char_in);
       //add to content pane
       content.add(controls, BorderLayout.NORTH);
       frame.setSize(600,600);
       //can close frame
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);      
       
        }
       
   } 
