package de.tuhh.diss.test2;
public class Rectangle {
 public int h, w;

 public Rectangle( int height, int width ) {
 h = height;
 w = width;
 }

 public int getArea() {
 return h * w;
 }

 public boolean fits( Rectangle other ) {
 if ( other.h < h && other.w < w ) {
 return true;
 } else {
 return false;
 }
 }
 }