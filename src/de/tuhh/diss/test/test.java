package de.tuhh.diss.test;

public class test {
    public static void main(String[] args) {
    	Point a = new Point(1, 1);
    	Point b = new Point(2, 2);
    	Point c = new Point(3, 3);
        Line line1 = new Line(a, b);
        Line line2 = new Line(a, c);
        System.out.println(line1.getSlope());
        System.out.println(line2.isParallel(line1, 2));
    }
}