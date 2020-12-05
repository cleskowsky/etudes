package a;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Day4 {
	
	public static void main(String[] args) throws Exception {
		List<Map<String, String>> passports = new ArrayList<>();
		
		// Read input
		String data = Files.readString(Path.of("inputs/4.txt"));
		String[] entries = data.split("\\n\\n");
		for (String entry : entries) {
			Map<String, String> m = new HashMap<>();
			String[] lines = entry.split("\\n");
			for (String line : lines) {
				String[] fields = line.split(" ");
				for (String field : fields) {
					String[] kv = field.split(":");
					if (kv[0].equals("cid")) {
						continue;
					}
					m.put(kv[0], kv[1]);
				}
			}
			boolean valid = true;
			for (String s : List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")) {
				if (!m.containsKey(s)) {
					valid = false;
				}
			}
			if (valid) {
				passports.add(m);
			}
		}
		
		// Part a
		System.out.println(passports.size());
		
		// Part b
		int strict = 0;
		for (Map<String, String> p : passports) {
			int byr = Integer.parseInt(p.get("byr"));
			if (byr < 1920 || byr > 2002) {
				continue;
			}
			
			int iyr = Integer.parseInt(p.get("iyr"));
			if (iyr < 2010 || iyr > 2020) {
				continue;
			}
			
			int eyr = Integer.parseInt(p.get("eyr"));
			if (eyr < 2020 || eyr > 2030) {
				continue;
			}
			
			String s = p.get("hgt");
			if (s.indexOf("cm") == -1 && s.indexOf("in") == -1) {
				continue;
			}
			int hgt = Integer.parseInt(s.substring(0, s.length() - 2));
			String unit = s.substring(s.length() - 2);
			if (unit.equals("cm")) {
				if (hgt < 150 || hgt > 193) {
					continue;
				}
			} else {
				if (hgt < 59 || hgt > 76) {
					continue;
				}
			}
			
			if (!Pattern.matches("^#[0-9a-z]{6}$", p.get("hcl"))) {
				continue;
			}
			
			List<String> eyeColours = List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
			String ecl = p.get("ecl");
			if (!eyeColours.contains(ecl)) {
				continue;
			}
			
			if (!Pattern.matches("^[0-9]{9}$", p.get("pid"))) {
				continue;
			}
			
			strict++;
		}
		System.out.println(strict);
	}
}
