package com.test.lombok;

import lombok.EqualsAndHashCode;

/**
 * Created by Ryan Miao on 1/18/18.
 */
@EqualsAndHashCode(exclude={"id", "shape"})
public class EqualsAndHashCodeExample {
    private transient int transientVar = 10;
    private String name;
    private double score;
    private ToStringExample.Shape shape = new Square(5, 10);
    private String[] tags;
    private int id;

    public String getName() {
        return this.name;
    }

    @EqualsAndHashCode(callSuper=true)
    public static class Square extends ToStringExample.Shape {
        private final int width, height;

        public Square(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        EqualsAndHashCodeExample example = new EqualsAndHashCodeExample();
        EqualsAndHashCodeExample example1 = new EqualsAndHashCodeExample();
        boolean equals = example.equals(example1);
        boolean b = example.canEqual(example);
        int i = example.hashCode();
    }
}
