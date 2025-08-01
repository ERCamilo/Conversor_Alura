/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import com.formdev.flatlaf.*;
import java.awt.Color;
import javax.swing.*;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 *
 * @author the_b
 */
public class ClassRecursos {

    public ImageIcon activado(String x) {

        ImageIcon selectedIcon = new ImageIcon(getClass().getResource("/Recursos/" + x + ".png"));
        return selectedIcon;
    }

    public ImageIcon desactivado(String x) {

        ImageIcon unselectedIcon = new ImageIcon(getClass().getResource("/Recursos/" + x + "_negate.png"));
        return unselectedIcon;
    }

    public void cambiarTema(JFrame x) {
        if (UIManager.getLookAndFeel().getClass().getName().equals(FlatDarkLaf.class.getName())) {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                SwingUtilities.updateComponentTreeUI(x);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (UIManager.getLookAndFeel().getClass().getName().equals(FlatLightLaf.class.getName())) {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                SwingUtilities.updateComponentTreeUI(x);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    /**/
    public void cambioTemaIconos(JToggleButton Swith, JLabel aluraLogo, JPanel jPanel2,
            JToggleButton BtnDistancia, JToggleButton BtnMoneda, JToggleButton BtnMasa, JToggleButton BtnTemperatura, JToggleButton BtnVolumen) {

        JToggleButton[] btn = {BtnDistancia, BtnMoneda, BtnMasa, BtnTemperatura, BtnVolumen};
        String[] iconName = {"Distancia", "Moneda", "Masa", "Temperatura", "Volumen"};

        if (Swith.isSelected()) {
            jPanel2.setBackground(new java.awt.Color(0, 0, 0));
            Swith.setIcon(activado("dark (Custom)"));
            aluraLogo.setIcon(this.desactivado("aluraLatam"));

            for (int i = 0; i < btn.length; i++) {
                btn[i].setIcon(this.desactivado(iconName[i]));
            }
        } else {
            aluraLogo.setIcon(this.activado("aluraLatam"));
            jPanel2.setBackground(new java.awt.Color(14, 51, 96));

            Swith.setIcon(activado("light (Custom1)"));

            for (int i = 0; i < btn.length; i++) {
                btn[i].setIcon(this.activado(iconName[i]));
            }

        }

    }

    public void animacion(JToggleButton btn/*, int r1, int g1,int b1, int r2, int g2,int b2*/) {

        if (btn.isSelected()) {

            btn.setBackground(new java.awt.Color(0, 122, 255));
        } else {
            btn.setBackground(new java.awt.Color(0, 142, 237));

        }
        btn.setForeground(Color.white);

    }

    public void animacion(JButton btn/*, int r1, int g1,int b1, int r2, int g2,int b2*/) {

        btn.setBackground(new java.awt.Color(0, 142, 237));

        btn.setForeground(Color.white);

    }

    public void animacionOff(JToggleButton btn, JToggleButton Switch/*, int r1, int g1,int b1, int r2, int g2,int b2*/) {

        if (!Switch.isSelected()) {

            btn.setForeground(new java.awt.Color(38, 38, 38));

            btn.setBackground(new java.awt.Color(255, 255, 255));
        } else {
            btn.setForeground(new java.awt.Color(187, 187, 187));

            btn.setBackground(new java.awt.Color(75, 75, 75));
        }
    }

    public void cambioConSwitch(JToggleButton Swith,
            JToggleButton BtnDistancia, JToggleButton BtnMoneda, JToggleButton BtnMasa, JToggleButton BtnTemperatura, JToggleButton BtnVolumen) {

        JToggleButton[] btn = {BtnDistancia, BtnMasa, BtnMoneda, BtnTemperatura, BtnVolumen};

        if (!Swith.isSelected()) {
            for (int i = 0; i < btn.length; i++) {

                btn[i].setForeground(new java.awt.Color(38, 38, 38));

                btn[i].setBackground(new java.awt.Color(255, 255, 255));
            }
        } else {
            for (int i = 0; i < btn.length; i++) {

                btn[i].setForeground(new java.awt.Color(187, 187, 187));

                btn[i].setBackground(new java.awt.Color(75, 75, 75));
            }
        }

    }

    public void ImagenDeFondo(JToggleButton Swith, String nombreImagen, JLabel JbackG, JLabel JbackG1, JLabel JbackG2) {

        String carpetaRecursos = "/Recursos/";
        if (Swith.isSelected()) {
            JbackG1.setIcon(new javax.swing.ImageIcon(getClass().getResource(carpetaRecursos + nombreImagen))); // NOI18N
            JbackG.setIcon(new javax.swing.ImageIcon(getClass().getResource(carpetaRecursos + nombreImagen))); // NOI18N
            JbackG2.setIcon(new javax.swing.ImageIcon(getClass().getResource(carpetaRecursos + nombreImagen))); // NOI18N
        } else {
            JbackG1.setIcon(null);
            JbackG.setIcon(null);
            JbackG2.setIcon(null);
        }

    }

    public void textoArcoiris(int tiempo, JLabel a) {

        Timer colorTimer;
        colorTimer = new Timer(tiempo, new ActionListener() {
            /*Color[] colors = {Color.BLACK, Color.BLUE};
            int colorIndex = 0;*/
            Random random = new Random();

            @Override
            public void actionPerformed(ActionEvent e) {

                int red = random.nextInt(256);
                int green = random.nextInt(256);
                int blue = random.nextInt(256);
                Color randomColor = new Color(red,green,blue);
                a.setForeground(randomColor); /*(colors[colorIndex]);*/ /*colorIndex = (colorIndex + 1) % colors.length;*/
            }
        });
        colorTimer.start();
    }

}
