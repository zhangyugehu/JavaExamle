package com.code;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * 两个栈实现一个队列
 * Created by hutianhang on 2022/1/16
 */
public class TwoStackQueue {

    static class Queue<E> {
        Stack<E> in;
        Stack<E> out;

        public Queue() {
            in = new Stack<>();
            out = new Stack<>();
        }

        public void push(E ele) {
            in.push(ele);
        }

        public E pop() {
            if (!out.isEmpty()) {
                return out.pop();
            } else if (!in.isEmpty()) {
                while (!in.empty()) {
                    out.push(in.pop());
                }
                return out.pop();
            } else {
                return null;
            }
        }
    }
    @Test
    void client() {
        Queue<String> queue = new Queue<>();
        queue.push("A");
        queue.push("B");
        queue.push("C");
        System.out.println(queue.pop());
        queue.push("D");
        System.out.println(queue.pop());
        queue.push("E");
        System.out.println(queue.pop());
        queue.push("F");
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        queue.push("G");
        queue.push("H");
        System.out.println(queue.pop());
        queue.push("I");
        queue.push("J");
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }
}
