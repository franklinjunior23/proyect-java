package org.proyect;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.proyect.views.SignView;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        try {
            // Usa el look & feel del sistema operativo: estable, seguro y sin fondos negros
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            SignView signView = new SignView();
            signView.setVisible(true);
        });
    }
}
