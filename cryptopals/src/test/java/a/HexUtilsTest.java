package a;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class HexUtilsTest {
	@Test
	public void hexStringMustHaveAnEvenNumberOfCharacters() {
		try {
			HexUtils.convertHexToBase64("a");
			fail();
		} catch (Exception e) {
			// Shouldn't get here
		}
	}

	@Test
	public void convertHexString() {
		String s = HexUtils.convertHexToBase64("49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d");
		assertEquals("SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t", s);
	}

	@Test
	public void xor() {
		String s = HexUtils.xor("1c0111001f010100061a024b53535009181c",
			"686974207468652062756c6c277320657965");
		assertEquals("746865206b696420646f6e277420706c6179", s);
	}
}
