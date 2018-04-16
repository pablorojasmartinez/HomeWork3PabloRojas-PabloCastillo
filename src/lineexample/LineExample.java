/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineexample;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Pablo Rojas Martínez
 */
public class LineExample extends JPanel {

    public LineExample() {
        this.setPreferredSize(new Dimension(800, 600));
    } // constructor

    private void draw(Graphics g) throws InterruptedException {

        drawLines(g);

    } // draw

    public void drawLines(Graphics g) throws InterruptedException {

        int i = 0;

        while (i < 1000) {

            int red = (int) ((Math.random() * 255) + 0);
            int green = (int) ((Math.random() * 255) + 0);
            int blue = (int) ((Math.random() * 255) + 0);

            Color color = new Color(red, green, blue);
            g.setColor(color);

            int x0 = (int) ((Math.random() * this.getWidth())); // Para que haga números no superiores al ancho
            int y0 = (int) ((Math.random() * this.getHeight())); // Para que haga números no superiores al alto

            int x1 = (int) ((Math.random() * this.getWidth())); // Para que haga números no superiores al ancho
            int y1 = (int) ((Math.random() * this.getHeight()));// Para que haga números no superiores al alto

            int pointX = x1 - x0;
            int pointY = y1 - y0;

            if (Math.abs(pointX) > Math.abs(pointY)) {          // Si la pendiente es negativa 
                float m = (float) pointY / (float) pointX;
                float b = y0 - m * x0;
                if (pointX < 0) {
                    pointX = -1;
                } else {
                    pointX = 1;
                }
                while (x0 != x1) {

                    x0 += pointX;
                    y0 = Math.round(m * x0 + b);
                    g.drawLine(x0, y0, x0, y0);

                }
            } else if (pointY != 0) {                              //Si la pendiente es positiva 
                float m = (float) pointX / (float) pointY;
                float b = x0 - m * y0;
                if (pointY < 0) {
                    pointY = -1;
                } else {
                    pointY = 1;
                }
                while (y0 != y1) {
                    y0 += pointY;
                    x0 = Math.round(m * y0 + b);
                    g.drawLine(x0, y0, x0, y0);

                }
            }
            i++;

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);
            draw(g);

        } catch (InterruptedException ex) {
            Logger.getLogger(LineExample.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Graphing Function");
        window.setContentPane(new LineExample());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.pack();
        window.setResizable(false);
        window.setLocation(180, 50);
        window.setVisible(true);
    }

} // fin clase

// Algoritmo tomado de las páginas: 
//http://galia.fc.uaslp.mx/~medellin/Applets/LineasRectas/Recta.htm
//http://graficacion2016b.blogspot.com/2016/08/deltax-deltay-m-algoritmo-de-bresenham.html
//https://www.scribd.com/document/105561908/Algoritmo-Dda-Bresenham-JAVA
