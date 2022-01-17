package com.swing.visualizer.pi;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hutianhang on 2022/1/16
 */
public class PiData {
    public Circle circle;
    private final List<Point> points;
    private int insideCircle;

    public PiData(Circle circle) {
        this.circle = circle;
        this.points = new LinkedList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
        if (circle.contain(point)) insideCircle++;
    }

    public Point getPoint(int i) {
        return points.get(i);
    }

    public int getPointCount() {
        return points.size();
    }

    public double estimatePi() {
        if (points.size() == 0) return 0.0;
        return (double) insideCircle * 4 / points.size();
    }
}
