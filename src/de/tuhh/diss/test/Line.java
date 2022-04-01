package de.tuhh.diss.test;

public class Line {
	private Point p, q;
	public Line(Point p1, Point p2) {
		this.p = new Point(p1.getX(), p1.getY());
		this.q = new Point(p2.getX(), p2.getY());
		
	}
	public Line(double p1x, double p1y, double p2x, double p2y) {
		this.p = new Point(p1x, p1y);
		this.q = new Point(p2x, p2y);
	}
	public double getSlope() {
		double slope = (p.getY()-q.getY())/(p.getX()-q.getX());
		return slope;
	}
	public boolean isParallel(Line line2, double tol) {
		double slope1 = getSlope();
		double slope2 = line2.getSlope();
		if(Math.abs(slope2-slope1) <= tol) {
			return true;
		}
		else {
			return false;
		}
	}
}
