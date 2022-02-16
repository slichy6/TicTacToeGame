package com.tic.tac.toe.team3;

import javax.swing.*;

public class Window extends JFrame{
    Gryffindor gryffindor;
    Slytherin mySlytherin;
    Window() {
    }
    public void display(boolean check){
        if(check == true){
            gryffindor = new Gryffindor();
            this.add(gryffindor);
        }
        else{
            mySlytherin = new Slytherin();
            this.add(mySlytherin);
        }
        this.pack();
        this.setLocationRelativeTo(null);       //appears in the middle of the screen
        this.setVisible(true);                  //makes it visible
    }
}

