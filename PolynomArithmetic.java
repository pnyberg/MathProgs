import java.util.ArrayList;
import java.util.List;

public class PolynomArithmetic {
	public static Polynom add(Polynom first, Polynom second) {
		int newPolynomDegree = Math.max(first.getDegree(), second.getDegree());
		int[] coefficients = new int[newPolynomDegree + 1];

		for (int k = 0 ; k <= newPolynomDegree ; k++) {
			int firstCoefficient = (k <= first.getDegree() ? first.getCoefficient(k) : 0);
			int secondCoefficient = (k <= second.getDegree() ? second.getCoefficient(k) : 0);
			coefficients[newPolynomDegree - k] = firstCoefficient + secondCoefficient;
		}

		return new Polynom(coefficients);
	}

	public static Polynom subtract(Polynom first, Polynom second) {
		int highestDegree = Math.max(first.getDegree(), second.getDegree());
		List<Integer> coefficients = new ArrayList<>();

		for (int k = 0 ; k <= highestDegree ; k++) {
			int firstCoefficient = (k <= first.getDegree() ? first.getCoefficient(k) : 0);
			int secondCoefficient = (k <= second.getDegree() ? second.getCoefficient(k) : 0);
			coefficients.add(0, firstCoefficient - secondCoefficient);
		}

		while (coefficients.size() > 0 && coefficients.get(0) == 0) {
			coefficients.remove(0);
		}

		int[] polynomCoefficients = new int[coefficients.size()];
		for (int i = 0 ; i < coefficients.size() ; i++) {
			polynomCoefficients[i] = coefficients.get(i);
		}

		return new Polynom(polynomCoefficients);
	}

	public static Polynom multiply(Polynom first, Polynom second) {
		int newPolynomDegree = first.getDegree() + second.getDegree();
		int[] coefficients = new int[newPolynomDegree + 1];

		for (int k = 0 ; k <= first.getDegree() ; k++) {
			for (int m = 0 ; m <= second.getDegree() ; m++) {
				coefficients[k+m] += first.getCoefficient(k) * second.getCoefficient(m);
			}
		}

		return new Polynom(coefficients);
	}

	public static Polynom divide(Polynom first, Polynom second) {
		int newPolynomDegree = first.getDegree() - second.getDegree();

		if (newPolynomDegree < 0) {
			new Exception("Cannot divide polynom with bigger polynom");
		}
		int[] quota = new int[newPolynomDegree + 1];
		int[] polynomCoefficient = new int[first.getDegree() + 1];

		for (int i = 0 ; i < polynomCoefficient.length ; i++) {
			polynomCoefficient[i] = first.getCoefficient(i);
		}

		for (int k = first.getDegree() ; k >= second.getDegree() ; k--) {
			int quotaCoefficient = polynomCoefficient[k] / second.getCoefficient(second.getDegree());
			quota[first.getDegree() - k] = quotaCoefficient;
			for (int m = 0 ; m <= second.getDegree() ; m++) {
				polynomCoefficient[k - m] -= quotaCoefficient * second.getCoefficient(second.getDegree() - m); 
			}
		}

		return new Polynom(quota);
	}
}