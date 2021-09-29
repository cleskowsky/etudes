package a;

import java.util.Base64;

public class HexUtils {

	/**
	 * Returns base64 encoding of hex string s
	 */
	public static String convertHexToBase64(String s) {
		if (s.length() % 2 == 1) {
			throw new RuntimeException("Hex string must have an even number of characters");
		}
		byte[] bytes = getBytesFromHexString(s);
		Base64.Encoder e = Base64.getEncoder();
		return e.encodeToString(bytes);
	}

	private static byte[] getBytesFromHexString(String s) {
		byte[] bytes = new byte[s.length() / 2];
		for (int i = 0; i < s.length(); i += 2) {
			int x = Character.getNumericValue(s.charAt(i));
			int y = Character.getNumericValue(s.charAt(i + 1));
			bytes[i / 2] = (byte) ((x << 4) + y);
		}
		return bytes;
	}

	private static String getHexStringFromBytes(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	/**
	 * Returns hex string XOR of hex strings s and t
	 */
	public static String xor(String s, String t) {
		if (s.length() != t.length()) {
			throw new RuntimeException("s and t must be equal length hex strings");
		}
		byte[] x = getBytesFromHexString(s);
		byte[] y = getBytesFromHexString(t);
		byte[] z = new byte[x.length];
		for (int i = 0; i < x.length; i++) {
			z[i] = (byte) (x[i] ^ y[i]);
		}
		return getHexStringFromBytes(z);
	}
}
