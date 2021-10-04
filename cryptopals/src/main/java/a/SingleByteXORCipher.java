package a;

import java.util.HashMap;
import java.util.Map;

/**
 * Set 1, Challenge 3
 * <p>
 * Found a discussion of the problem here:
 * https://www.codementor.io/@arpitbhayani/deciphering-single-byte-xor-ciphertext-17mtwlzh30
 * <p>
 * New things learned:
 * - "Fitting quotient" : Not sure this exactly the right term. I can't seem to find it.
 * - Related : Line fitting, linear regression but in this case we're trying to fit
 * more than a line
 */
public class SingleByteXORCipher {
	public static Map<Character, Double> letterFrequencies = new HashMap<>();

	// Frequency table : http://pi.math.cornell.edu/~mec/2003-2004/cryptography/subs/frequencies.html
	static {
		letterFrequencies.put('a', 8.12);
		letterFrequencies.put('b', 1.49);
		letterFrequencies.put('c', 2.71);
		letterFrequencies.put('d', 4.32);
		letterFrequencies.put('e', 12.02);
		letterFrequencies.put('f', 2.30);
		letterFrequencies.put('g', 2.03);
		letterFrequencies.put('h', 5.92);
		letterFrequencies.put('i', 7.31);
		letterFrequencies.put('j', 0.10);
		letterFrequencies.put('k', 0.69);
		letterFrequencies.put('l', 3.98);
		letterFrequencies.put('m', 2.61);
		letterFrequencies.put('n', 6.95);
		letterFrequencies.put('o', 7.68);
		letterFrequencies.put('p', 1.82);
		letterFrequencies.put('q', 0.11);
		letterFrequencies.put('r', 6.02);
		letterFrequencies.put('s', 6.28);
		letterFrequencies.put('t', 9.10);
		letterFrequencies.put('u', 2.88);
		letterFrequencies.put('v', 1.11);
		letterFrequencies.put('w', 2.09);
		letterFrequencies.put('x', 0.17);
		letterFrequencies.put('y', 2.11);
		letterFrequencies.put('z', 0.07);
	}

	public static String decode(String encoded) {
		byte[] bytes = HexUtils.getBytesFromHexString(encoded);
		float minFQ = Float.MAX_VALUE;
		String originalText = null;
		for (int i = 0; i <= 256; i++) {
			StringBuilder buf = new StringBuilder();
			for (byte b : bytes) {
				buf.append(Character.toString(b ^ i));
			}
			String _text = buf.toString().toLowerCase();

			float q = fittingQuotient(_text);
			if (q < minFQ) {
				minFQ = q;
				originalText = _text;
			}
			if (q < 75) {
				// May not be enough text to fit well with english letter occurrences in this case,
				// so I'll set a threshold
				System.out.println("q: " + q);
				System.out.println("original text: " + _text);
			}
		}
		System.out.println("lowest fitting quotient: " + minFQ);
		System.out.println("original text with lowest fitting quotient: " + originalText);
		return originalText;
	}

	private static float fittingQuotient(String text) {
		Map<Character, Integer> occurrences = countLetterFrequency(text);
		float sum = 0;
		for (char c : letterFrequencies.keySet()) {
			sum += Math.abs(letterFrequencies.get(c) - ((occurrences.getOrDefault(c, 0) * 100) / (float) text.length()));
		}
		return sum;
	}

	private static Map<Character, Integer> countLetterFrequency(String s) {
		Map<Character, Integer> x = new HashMap<>();
		for (char c : s.toCharArray()) {
			int y = x.getOrDefault(c, 0) + 1;
			x.put(c, y);
		}
		return x;
	}

	public static void main(String[] args) {
		// Encoded message : Cooking MC's like a pound of bacon
		// Apparently this is a vanilla ice reference :)
		// https://getyarn.io/yarn-clip/fd3aba55-4c7d-4564-a3ae-74dee1adb86e
		String data = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";
		decode(data);
	}
}
