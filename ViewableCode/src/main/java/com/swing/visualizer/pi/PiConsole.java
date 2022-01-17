package com.swing.visualizer.pi;

import java.awt.*;

/**
 * Created by hutianhang on 2022/1/16
 */
public class PiConsole {

    public static void main(String[] args) {
        PiConsole piConsole = new PiConsole(800, 1_000_000);
        piConsole.run();
    }

    private final int squareSide;
    private final int n;

    public PiConsole(int squareSide, int n) {
        this.squareSide = squareSide;
        this.n = n;
    }

    public void run() {
        Circle circle = new Circle(squareSide >> 1, squareSide >> 1, squareSide >> 1);
        PiData piData = new PiData(circle);
        int i1 = n / 10;
        for (int i = 0; i < n; i++) {
            if (i % i1 == 0) {
                System.out.println(piData.estimatePi());
            }

            piData.addPoint(new Point((int) (Math.random() * squareSide), (int) (Math.random() * squareSide)));
        }

        System.out.println("result: " + piData.estimatePi());
    }
}
