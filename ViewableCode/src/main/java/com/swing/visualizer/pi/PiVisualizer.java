package com.swing.visualizer.pi;

import com.swing.AlgoFrame;
import com.swing.AlgoHelper;

import java.awt.*;

/**
 * Created by hutianhang on 2022/1/16
 */
public class PiVisualizer {
    private static final int POINT_COUNT = 20000;

    public static void main(String[] args) {
        new PiVisualizer(500);
    }

    private final int size;
    public PiVisualizer(int size) {
        this.size = size;
        EventQueue.invokeLater(() -> new Thread(this::run).start());
    }

    private void run() {
        AlgoFrame<PiData> frame = new AlgoFrame<>("Get Pi with Monte Carlo", size, size) {

            @Override
            protected void draw(Graphics2D g2d, PiData data) {
                AlgoHelper.setStokeWidth(g2d, 2);
                AlgoHelper.setColor(g2d, AlgoHelper.Blue);

                Circle circle = data.circle;
                AlgoHelper.stokeCircle(g2d, circle.getX(), circle.getY(), circle.getR());

                for (int i = 0; i < data.getPointCount(); i++) {
                    Point point = data.getPoint(i);
                    AlgoHelper.setColor(g2d, circle.contain(point) ? AlgoHelper.Red : AlgoHelper.Green);
                    AlgoHelper.fillCircle(g2d, point.x, point.y, 3);
                }
            }
        };
        PiData piData = new PiData(new Circle(size / 2, size / 2, size / 2));
        for (int i = 0; i < POINT_COUNT; i++) {
            if (i % 10 == 0) {
                frame.render(piData);
                AlgoHelper.pause(15);
                System.out.println("pi: " + piData.estimatePi());
            }
            piData.addPoint(new Point((int) (Math.random() * size), (int) (Math.random() * size)));
        }
    }
}
