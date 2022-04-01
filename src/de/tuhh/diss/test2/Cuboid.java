package de.tuhh.diss.test2;

public class Cuboid extends Rectangle {
 public int d;

 public Cuboid( int height, int width, int depth ) {
 super(height, width);
 d = depth;
 }

 public int getVolume() {
 return getArea() * d;
 }
 
 public boolean fits(Cuboid cub) {
	 System.out.println(d);
	 System.out.println(getArea());
	 if (d < cub.d) {
		 if (getArea() < cub.getArea()) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }
	 else {
		 return false;
	 }
 }
 }
