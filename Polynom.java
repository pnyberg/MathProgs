import java.util.ArrayList;
import java.util.List;

public class Polynom {
	private ArrayList<Integer> polynomCoefficients;

	public Polynom(int... coefficients) {
		polynomCoefficients = new ArrayList<>();

		for (int c : coefficients) {
			polynomCoefficients.add(0, c);
		}
	}

	/**
	 * Methods
	 */
	public int calculate(int x) {
		int value = 0;

		for (int i = 0 ; i < polynomCoefficients.size() ; i++) {
			int k = polynomCoefficients.get(i);

			value += k * Math.pow(x, i);
		}

		return value;
	}

	public Polynom derive() {
		int[] newPolynomCoefficients = new int[polynomCoefficients.size() - 1];
		for (int i = 0 ; i < (polynomCoefficients.size() - 1) ; i++) {
			int value = polynomCoefficients.get(i+1) * (i+1);
			newPolynomCoefficients[i] = value;
		}
		return new Polynom(newPolynomCoefficients);
	}

	/**
	 * TODO: factorize the polynom into irreducible factors
	 */
	public Polynom[] factorize() {
		return null;
	}


	/**
	 * Getters
	 */
	public int getCoefficient(int index) {
		if (polynomCoefficients.size() == 0) {
			return 0;
		}
		return polynomCoefficients.get(index);
	}

	public int getDegree() {
		if (polynomCoefficients.size() == 0) {
			return 0;
		}
		return polynomCoefficients.size() - 1;
	}

	public String toString() {
		String text = "";

		for (int i = 0 ; i < polynomCoefficients.size() ; i++) {
			int k = polynomCoefficients.get(i);

			if (k == 0) {
				continue;
			}

			boolean negativeK = k < 0;
			if (negativeK) {
				k *= -1;
			}
			text = (negativeK ? " - " : " + ") + (k > 1 || i == 0 ? k : "") + (i == 0 ? "" : "x") + (i > 1 ? "^" + i : "") + text;
		}

		if (text.length() >= 3) {
			text = text.substring(3, text.length());
		} else {
			text = "0";
		}

		return text;
	}

	public static void main(String[] args) {
		Polynom a = new Polynom(1, 2, 1);
		Polynom b = new Polynom(1, 3, 3, 1);
		Polynom c = new Polynom(2, 0, 3);
		Polynom d = new Polynom(0, 0, 0);
		Polynom e = new Polynom(1, 0, 0);
		Polynom f = new Polynom(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Polynom g = new Polynom();
		Polynom h = new Polynom(1, 10, 35, 50, 24);
		Polynom f1 = new Polynom(1, 0, -4);

		System.out.println(a + ": a(2) = " + a.calculate(2));
		System.out.println(b + ": b(2) = " + b.calculate(2));
		System.out.println(c + ": c(2) = " + c.calculate(2));
		System.out.println(d + ": d(2) = " + d.calculate(2));
		System.out.println(e + ": e(2) = " + e.calculate(2));
		System.out.println(f + ": f(2) = " + f.calculate(2));
		System.out.println(g + ": g(2) = " + g.calculate(2));
		System.out.println(h + ": h(2) = " + h.calculate(2));
		System.out.println(f1 + ": f1(2) = " + f1.calculate(2));

		System.out.println(" -------- ");
		System.out.println(PolynomArithmetic.divide(h, PolynomArithmetic.divide(b, a)));
		System.out.println(" -------- ");

		List<Integer> foundRoots = TrivialRootSearcher.getTrivialRoots(h);
		System.out.print("Trivial roots = {");
		for (int k = 0 ; k < foundRoots.size() ; k++) {
			System.out.print(foundRoots.get(k));
			if (k < foundRoots.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("}");
	}
}