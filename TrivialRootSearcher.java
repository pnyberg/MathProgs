import java.util.ArrayList;
import java.util.List;

public class TrivialRootSearcher {
	public static List<Integer> getTrivialRoots(Polynom polynom) {
		List<Integer> possibleTrivialRoots = TrivialRootSearcher.getPossibleTrivialRoots(polynom);
		List<Integer> trivialRoots = new ArrayList<>();
		for (int possibleRoot : possibleTrivialRoots) {
			if (polynom.calculate(possibleRoot) == 0) {
				trivialRoots.add(possibleRoot);
			}
		}
		return trivialRoots;
	} 

	public static List<Integer> getPossibleTrivialRoots(Polynom polynom) {
		int constant = polynom.getCoefficient(0);
		boolean negativeConstant = constant < 0;
		if (negativeConstant) {
			constant *= -1;
		}

		List<Integer> possibleRoots = new ArrayList<>();
		if (constant == 0) {
			possibleRoots.add(0);
			return possibleRoots;
		}

		addPossibleRoot(possibleRoots, negativeConstant, 1);
		for (int n = 2 ; n <= constant/2 ; n++) {
			if (constant % n == 0) {
				addPossibleRoot(possibleRoots, negativeConstant, n);
			}
		}
		if (constant != 1) {
			addPossibleRoot(possibleRoots, negativeConstant, constant);
		}

		return possibleRoots;
	}

	private static void addPossibleRoot(List rootList, boolean negativeConstant, int value) {
		rootList.add(-value); // since f(alpha) = 0 => (x - alpha)
		if (negativeConstant) {
			rootList.add(value);
		}
	}
}