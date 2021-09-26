package a.set1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ConvertHexToBase64Test {
	@Test
	public void hexStringMustHaveAnEvenNumberOfCharacters() {
		try {
			ConvertHexToBase64.convertHexToBase64("a");
			fail();
		} catch (Exception e) {
			// Shouldn't get here
		}
	}

	@Test
	public void convertHexString() {
		String s = ConvertHexToBase64.convertHexToBase64("49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d");
		assertEquals("SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t", s);
	}
}
