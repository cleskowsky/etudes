package a;

public class Day10 {
	public static final Boolean DEBUG = false;
	public static final Boolean PART_A = false;

	public static void main(String[] args) {
		if (PART_A) {
			String s = lookAndSayWithRounds("1113122113", 40);
			System.out.println(s.length());
		} else {
			String s = lookAndSayWithRounds("1113122113", 50);
			System.out.println(s.length());
		}
	}

	/**
	 * Returns a new look-and-say string
	 * eg "11" -> "2 ones" -> "21"
	 * @param s An integer string
	 * @param n Number of rounds (1 or more)
	 */
	public static String lookAndSayWithRounds(String s, int n) {
		if (DEBUG) {
			System.out.println("Entering lookAndSay with: " + s);
		}
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			throw new RuntimeException("Couldn't parse integer string: " + s, e);
		}
		if (n < 1) {
			throw new RuntimeException("n should be a number > 1");
		}
		String x = s;
		for (int i = 0; i < n; i++) {
			x = lookAndSay(x);
		}
		return x;
	}

	private static String lookAndSay(String s) {
		int cnt = 0;
		Character lastChar = null;
		// Wow! What a difference between string concatenation
		// here and using string builder (Several seconds for 40
		// rounds -> milliseconds)
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length() + 1; i++) {
			if (i < s.length()) {
				char currChar = s.charAt(i);
				if (lastChar == null) {
					lastChar = currChar;
					cnt++;
				} else {
					if (lastChar == currChar) {
						cnt++;
					} else {
						sb.append(cnt);
						sb.append(lastChar);
						lastChar = currChar;
						cnt = 1;
					}
				}
			} else {
				// Need a final round of the loop after
				// we finish processing the last char in s
				sb.append(cnt);
				sb.append(lastChar);
			}
		}
		return sb.toString();
	}
}
