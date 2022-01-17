package com.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by hutianhang on 2022/1/16
 */
public class AlgoHelper {
    public static final Color Red = new Color(0xF44336);
    public static final Color Pink = new Color(0xE91E63);
    public static final Color Purple = new Color(0x9C27B0);
    public static final Color DeepPurple = new Color(0x673ab7);
    public static final Color Indigo = new Color(0x3f51b5);
    public static final Color Blue = new Color(0x2196f3);
    public static final Color LightBlue = new Color(0x03a9f4);
    public static final Color Cyan = new Color(0x00bcd4);
    public static final Color Teal = new Color(0x009688);
    public static final Color Green = new Color(0x4caf50);
    public static final Color LightGreen = new Color(0x8bc34a);
    public static final Color Lime = new Color(0xcddc39);
    public static final Color Yellow = new Color(0xffeb3b);
    public static final Color Amber = new Color(0xffc107);
    public static final Color Orange = new Color(0xff9800);
    public static final Color DeepOrange = new Color(0xff5722);
    public static final Color Brown = new Color(0x795548);
    public static final Color Grey = new Color(0x9e9e9e);
    public static final Color BlueGrey = new Color(0x607d8b);
    public static final Color Black = new Color(0x000000);
    public static final Color White = new Color(0xffffff);

    public static void setStokeWidth(Graphics2D g, int width) {
        g.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }
    public static void setColor(Graphics2D g, Color color) {
        g.setColor(color);
    }

    public static void fillCircle(Graphics2D g2d, int x, int y, int r) {
        g2d.fill(new Ellipse2D.Float(x - r, y - r, r << 1, r << 1));
    }

    public static void stokeCircle(Graphics2D g2d, int x, int y, int r) {
        g2d.draw(new Ellipse2D.Float(x - r, y - r, r << 1, r << 1));
    }

    public static void stokeRectangle(Graphics2D g2d, int x, int y, int w, int h) {
        g2d.draw(new Rectangle2D.Double(x, y, w, h));
    }

    public static void fillRectangle(Graphics2D g2d, int x, int y, int w, int h) {
        g2d.fill(new Rectangle2D.Double(x, y, w, h));
    }

    public static void putImage(Graphics2D g2d, int x, int y, String filename) {
        ImageIcon imageIcon = new ImageIcon(filename);
        g2d.drawImage(imageIcon.getImage(), x, y, null);
    }

    public static void drawText(Graphics2D g2d, String text, int cx, int cy) {
        FontMetrics metrics = g2d.getFontMetrics();
        int w = metrics.stringWidth(text);
        int h = metrics.getDescent();
        g2d.drawString(text, cx - w/2, cy + h);
    }

    public static void pause(long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
