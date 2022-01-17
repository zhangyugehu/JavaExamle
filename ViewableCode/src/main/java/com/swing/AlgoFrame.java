package com.swing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hutianhang on 2022/1/16
 */
public abstract class AlgoFrame<T> extends JFrame {
    private final int width, height;
    public AlgoFrame(String title, int w, int h) throws HeadlessException {
        super(title);
        width = w;
        height = h;
        AlgoCanvas canvas = new AlgoCanvas();
//        canvas.setPreferredSize(new Dimension(w, h));
        setContentPane(canvas);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

//    private Circle[] circles;
    private T data;

    public void render(T data) {
        this.data = data;
        repaint();
    }

    public int getCanvasWidth() {
        return width;
    }

    public int getCanvasHeight() {
        return height;
    }

    private class AlgoCanvas extends JPanel {
        public AlgoCanvas() {
            super(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.RED);
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
            AlgoHelper.setStokeWidth(g2d, 2);

            if (data != null) {
                draw(g2d, data);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(width, height);
        }
    }

    protected abstract void draw(Graphics2D g2d, T data);
}
