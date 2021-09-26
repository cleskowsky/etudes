package a.set1;

import java.util.Base64;

public class ConvertHexToBase64 {

	public static String convertHexToBase64(String s) {
		if (s.length() % 2 == 1) {
			throw new RuntimeException("Hex string must have an even number of characters");
		}

		// Convert hex string to bytes
		byte[] bytes = new byte[s.length() / 2];
		for (int i = 0; i < s.length(); i += 2) {
			int x = Character.getNumericValue(s.charAt(i));
			int y = Character.getNumericValue(s.charAt(i + 1));
			bytes[i / 2] = (byte) ((x << 4) + y);
		}

		// Base64 encode bytes
		Base64.Encoder e = Base64.getEncoder();
		return e.encodeToString(bytes);
	}
}
