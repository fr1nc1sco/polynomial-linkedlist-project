package classes;

public class PolyTerm {
	// attributes
	private double coef;
	private int expo;

	// constructors for PolyTerm
	public PolyTerm() {
		coef = 0.0;
		expo = 0;
	}

	public PolyTerm(double c, int e) {
		coef = c;
		expo = e;
	}

	// sets completely new data
	public void setPolyTerm(double c, int e) {
		coef = c;
		expo = e;
	}

	// getter and setter for exponents
	public int getExpo() {
		return expo;
	}

	public void setExpo(int expo) {
		this.expo = expo;
	}

	// getter and setter for coefficients
	public double getCoef() {
		return coef;
	}

	public void setCoef(double coef) {
		this.coef = coef;
	}

	// print Poly term
	@Override
	public String toString() {
		return (this.coef + "x^" + this.expo);
	}

	public void printNode() {
		System.out.print(this.toString());
	}

	//methods
	//negate coefficients
	public void negateCoef() {
		this.coef *= -1;
	}
}