package com.app.prracesimulator.controllers;

import com.app.prracesimulator.models.entities.Race;
import com.app.prracesimulator.views.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

/**
 * @author : Gabriel Huertas <gabriel970826@gmail.com>
 * Date: 8/02/2020
 * Time: 2:25 p. m.
 */
public class Controller implements ActionListener {

    private Timer timer;
    private Race race;
    private MainWindow mainWindow;

    public Controller(){
        this.race = new Race();
        this.mainWindow = new MainWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Actions.valueOf(e.getActionCommand())) {
            case SIMULATE:
                simulate();
                break;
            default:
                break;
        }
    }

    private void simulate() {
        //TODO
    }
}
