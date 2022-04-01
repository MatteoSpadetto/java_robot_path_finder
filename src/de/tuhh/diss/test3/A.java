package de.tuhh.diss.test3;
public class A {
 private static int x = 0;

 public A() {
 ++x;
 }

 public void print() {
 print("A");
 }

 protected void print(String src) {
 System.out.println(src +": "+ x);
 }

 public static void main(String[] args) {
 M m = new M();
 A a1 = new A();
 A a2 = new B(m);
 a1.print();
 a2.print();
 m.print();
 }
 }