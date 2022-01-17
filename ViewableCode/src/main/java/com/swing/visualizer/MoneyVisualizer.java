package com.swing.visualizer;

import com.swing.AlgoFrame;
import com.swing.AlgoHelper;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by hutianhang on 2022/1/16
 */
public class MoneyVisualizer {
    private static final int INIT_MONEY = 10;
    private static final int COUNT = 100;
    private static final int SPEED = 10;
    // 可负债
    private static final boolean DEBIT_ABLE = true;

    public static void main(String[] args) {
        new MoneyVisualizer(1000, 600);
    }

    private final int[] money;
    private final AlgoFrame<int[]> frame;

    public MoneyVisualizer(int w, int h) {
        money = new int[COUNT];
        Arrays.fill(money, INIT_MONEY);

        frame = new AlgoFrame<>("Welcome", w, h) {

            @Override
            protected void draw(Graphics2D g2d, int[] data) {
                AlgoHelper.setColor(g2d, Color.BLUE);
                int itemW = getCanvasWidth() / money.length;
                for (int i = 0; i < data.length; i++) {
                    if (DEBIT_ABLE) {
                        if (money[i] < 0) {
                            AlgoHelper.setColor(g2d, Color.RED);
                            AlgoHelper.fillRectangle(g2d, i * itemW + 1, getCanvasHeight()/2, itemW-1, -money[i]);
                        } else {
                            AlgoHelper.setColor(g2d, Color.BLUE);
                            AlgoHelper.fillRectangle(g2d, i * itemW + 1, getCanvasHeight()/2-money[i], itemW-1, money[i]);
                        }
                    } else {
                        AlgoHelper.fillRectangle(g2d, i * itemW + 1, getCanvasHeight() - money[i], itemW - 1, money[i]);
                    }
                }
            }
        };

        EventQueue.invokeLater(() -> new Thread(this::run).start());
    }

    private void run() {
        for (;;) {
            Arrays.sort(money);
            frame.render(money);
            AlgoHelper.pause(10);

            for (int k = 0; k < SPEED; k++) {
                for (int i = 0; i < money.length; i++) {
                    if (DEBIT_ABLE || money[i] > 0) {
                        int j = (int) (Math.random() * money.length);
                        money[i] -= 1;
                        money[j] += 1;
                    }
                }
            }
        }
    }
}
