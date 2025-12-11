package org.proyect;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import java.sql.Connection;

import org.proyect.config.Database;
import org.proyect.views.SignView;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        SignView signView = new SignView();
        signView.setVisible(true);

    }
}