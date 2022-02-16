package com.tic.tac.toe.team3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gryffindor extends JPanel implements ActionListener {
    final int PANEL_WIDTH = 450;        //size of the panel, modified it to the size of the background image
    final int PANEL_HEIGHT = 800;
    Image winner, background;           //creating winner variable of type Image to store our image
    Timer timer;                        //A timer fires one or more ActionEvents at specified intervals.
    int xVelocity = 2;                  //how many pixels this image is moving on the x-axis
    int yVelocity = 1;
    int x, y = 0;                       //setting x and y position

    Gryffindor(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.black);
        winner = new ImageIcon("resources/snitch.png").getImage();              //ImageIcon(String) finds and creates the image using the path.
        background = new ImageIcon("resources/DailyProphetH.png").getImage();
        timer = new Timer(10, this::actionPerformed );    //[10 ms = 0.01s how quickly the image is moving, this::actionPerformed performs the action that is being taken]
    }

    public void paint(Graphics g){                      //paint() is a method in Jcomponent that takes a paramater of type Graphics where we will paint
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, null);
        g2D.drawImage(winner, x, y, null);
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(x >= PANEL_WIDTH- winner.getWidth(null) || x < 0){       //if the picture reaches the border, we flip the signs on xVelocity to change the direction it is going
            xVelocity = xVelocity * -1;
        }
        x = x + xVelocity;

        if(y >= PANEL_HEIGHT- winner.getHeight(null) || y < 0){
            yVelocity = yVelocity * -1;
        }
        y = y + yVelocity;
        repaint();                                          //cannot call paint() method again, must use the method repaint() to draw the objects again
    }
}
