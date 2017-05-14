package com.github.easai.math;

public class Phasor {
	double mag;
	double angle;

	Phasor() {
	}

	public static Phasor fromRadian(double mag, double angle) {
		Phasor p = new Phasor();
		p.mag = mag;
		p.angle = angle;
		return p;
	}

	public static Phasor fromDegree(double mag, double angle) {
		Phasor p = new Phasor();
		p.mag = mag;
		p.angle = angle / 180.0 * Math.PI;
		return p;
	}

	public static Phasor fromRec(double r, double i) {
		Phasor p = new Phasor();
		p.mag = Math.sqrt(r * r + i * i);
		p.angle = Math.atan(i / r);
		return p;
	}

	public static Phasor capacitorZ(double omega, double capacitance) {
		return Phasor.fromRec(0.0, -1.0 / (omega * capacitance));
	}

	public static Phasor inductorZ(double omega, double inductance) {
		return Phasor.fromRec(0.0, omega * inductance);
	}

	double getRe() {
		return mag * Math.cos(angle);
	}

	double getIm() {
		return mag * Math.sin(angle);
	}

	static Phasor add(Phasor p, Phasor q) {
		return Phasor.fromRec(p.getRe() + q.getRe(), p.getIm() + q.getIm());
	}

	static Phasor sub(Phasor p, Phasor q) {
		return Phasor.fromRec(p.getRe() - q.getRe(), p.getIm() - q.getIm());
	}

	static Phasor mul(Phasor p, Phasor q) {
		return Phasor.fromRadian(p.mag * q.mag, p.angle + q.angle);
	}

	static Phasor div(Phasor p, Phasor q) {
		return Phasor.fromRadian(p.mag / q.mag, p.angle - q.angle);
	}

	static Phasor parallel(Phasor p, Phasor q) {
		return Phasor.div(Phasor.mul(p, q), Phasor.add(p, q));
	}

	public void print() {
		System.out.println(mag + " /_  " + angle / Math.PI * 180.0);
	}

	public void printRec() {
		System.out.println(getRe() + " +  " + getIm() + " j");
	}

	public static void main(String args[]) {
		/*
		 * Phasor p=Phasor.fromRec(3,-2); p.print(); p.printRec(); Phasor
		 * q=Phasor.fromRec(6,4); q.print(); q.printRec(); Phasor
		 * a=Phasor.add(p,q); a.print(); a.printRec(); Phasor b=Phasor.sub(p,q);
		 * b.print(); b.printRec(); Phasor m=Phasor.mul(p,q); m.print();
		 * m.printRec(); Phasor n=Phasor.div(p,q); n.print(); n.printRec();
		 * Phasor par=Phasor.parallel(p,q); par.print(); par.printRec();
		 */
		/*
		 * Phasor a=Phasor.fromRec(6,4); a.print(); a.printRec(); Phasor
		 * b=Phasor.fromRadian(a.mag, a.angle); b.print(); b.printRec(); Phasor
		 * c=Phasor.fromDegree(1,45.0); c.print(); c.printRec();
		 */
		/*
		 * Phasor l=Phasor.inductorZ(1.0E4, .4*1.0E-3); l.print(); l.printRec();
		 * Phasor c=Phasor.capacitorZ(1.0E4, .05*1.0E-3); c.print();
		 * c.printRec();
		 */
		// Phasor p=Phasor.fromRec(-1.6470588235294, 1.4117647058824);
		// Phasor p=Phasor.fromRec(8,-4);
		Phasor p = Phasor.fromRec(2, 4);
		p.print();
	}
}
