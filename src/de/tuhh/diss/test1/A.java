package de.tuhh.diss.test1;
public class A {
 private static int x = 0;
 private int y;

 public A() {
 y = ++x;
 System.out.println( x +", "+ y);
 }

 public void print() {
 print("A");
 }

 protected void print(String src) {
 System.out.println(src +": "+ x +", "+ y);
 }

 public static void main(String[] args) {
 A a, b, c;
 a = new A();
 b = a;
 a = new A();
 c = new B();
 a.print();
 b.print();
 c.print();
 }
}