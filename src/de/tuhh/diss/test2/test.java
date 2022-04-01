package de.tuhh.diss.test2;

public class test {
    public static void main(String[] args) {
    	Cuboid cub2 = new Cuboid(2, 2, 2);
    	Cuboid cub1 = new Cuboid(1, 1, 1);
    	System.out.println(cub1.fits(cub2));
    }
}