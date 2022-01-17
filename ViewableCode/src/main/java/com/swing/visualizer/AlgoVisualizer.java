package com.swing.visualizer;

import com.swing.AlgoFrame;
import com.swing.AlgoHelper;
import com.swing.Circle;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by hutianhang on 2022/1/16
 */
public class AlgoVisualizer {

    public static void main(String[] args) {
        new AlgoVisualizer(500, 500, 8);
    }

    private final  Circle[] circles;
    private final AlgoFrame<Circle[]> frame;
    private boolean isAnimated = true;

    public AlgoVisualizer(int w, int h, int n) {
        circles = new Circle[n];
        int R = 50;
        for (int i = 0; i < circles.length; i++) {
            int x = (int) (Math.random() * (w - 2 * R) + R);
            int y = (int) (Math.random() * (h - 2 * R) + R);
            int vx = (int) (Math.random() * 11 - 5);
            int vy = (int) (Math.random() * 11 - 5);
            circles[i] = new Circle(x, y, R, vx == 0 ? 1 : vx, vy == 0 ? 1 : vy);
        }

        frame = new AlgoFrame<>("Welcome", w, h) {

            @Override
            protected void draw(Graphics2D g2d, Circle[] data) {
                for (Circle circle : circles) {
                    if (circle.isFilled) {
                        AlgoHelper.fillCircle(g2d, circle.x, circle.y, circle.getR());
                    } else {
                        AlgoHelper.stokeCircle(g2d, circle.x, circle.y, circle.getR());
                    }
                }
            }
        };
        frame.addKeyListener(new KeyListener());
        frame.addMouseListener(new MouseListener());

        EventQueue.invokeLater(() -> new Thread(this::run).start());
    }

    private void run() {
        for (;;) {
            if (isAnimated) {
                frame.render(circles);
                for (Circle c : circles) {
                    c.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());
                }
            }
            AlgoHelper.pause(10);
        }
    }

    private class MouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            e.translatePoint(0, -(frame.getBounds().height - frame.getCanvasHeight()));
            Point point = e.getPoint();

            for (Circle circle : circles) {
                if (circle.contain(point)) {
                    circle.isFilled = !circle.isFilled;
                }
            }
        }
    }

    private class KeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("key " + e.getKeyChar());
            if (e.getKeyChar() == ' ') {
                isAnimated = !isAnimated;
            }
        }
    }
}
