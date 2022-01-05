package a;

import java.util.List;

public class Day1 {
	public int findExpenses(List<Integer> expenses) {
		// I tried to do expenses.forEach() which didn't work because of
		// that early return I'm doing there. Apparently that method of
		// iteration does not allow early returns?
		for (int x : expenses) {
			for (int y : expenses) {
				if ((x + y) == 2020) {
					return x * y;
				}
			}
		}
		return 0;
	}
	
	public int findExpenses2(List<Integer> expenses) {
		for (int x : expenses) {
			for (int y : expenses) {
				for (int z : expenses) {
					if ((x + y + z) == 2020) {
						return x * y * z;
					}
				}
			}
		}
		return 0;
	}
}
