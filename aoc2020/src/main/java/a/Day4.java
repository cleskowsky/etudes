package a;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Day4 {
	
	public static void main(String[] args) {
		List<Map<String, String>> passports = readInput();
		
		// Part a
		System.out.println(passports.stream().filter(p -> p.keySet().size() >= 7).count());
		
		// Part b
		int x = 0;
		for (Map<String, String> p : passports) {
			if (checkPassport(p)) {
				x++;
			}
		}
		System.out.println(x);
		
	}
	
	public static boolean checkPassport(Map<String, String> p) {
		if (p.keySet().size() < 7) {
			return false;
		}

		int byr = Integer.parseInt(p.get("byr"));
		if (byr < 1920 || byr > 2002) {
			return false;
		}
		
		int iyr = Integer.parseInt(p.get("iyr"));
		if (iyr < 2010 || iyr > 2020) {
			return false;
		}
		
		int eyr = Integer.parseInt(p.get("eyr"));
		if (eyr < 2020 || eyr > 2030) {
			return false;
		}
		
		String s = p.get("hgt");
		if (s.indexOf("cm") == -1 && s.indexOf("in") == -1) {
			return false;
		}
		int hgt = Integer.parseInt(s.substring(0, s.length() - 2));
		String unit = s.substring(s.length() -2);
		if (unit.equals("cm")) {
			if (hgt < 150 || hgt > 193) {
				return false;
			}
		} else {
			if (hgt < 59 || hgt > 76) {
				return false;
			}
		}
		
		if (!Pattern.matches("^#[0-9a-z]{6}$", p.get("hcl"))) {
			return false;
		}
		
		List<String> eyeColours = List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
		String ecl = p.get("ecl");
		if (!eyeColours.contains(ecl)) {
			return false;
		}
		
		if (!Pattern.matches("^[0-9]{9}$", p.get("pid"))) {
			return false;
		}
		
		return true;
	}
	
	public static List<Map<String, String>> readInput() {
		List<Map<String, String>> passports = new ArrayList<>();
		Map<String, String> p = new HashMap<>();
		passports.add(p);
		
		// Parse input
		try {
			List<String> lines = Files.readAllLines(Path.of("inputs/4.txt"));
			for (String l : lines) {
				if (l.isBlank()) {
					p = new HashMap<>();
					passports.add(p);
					continue;
				}
				String[] fields = l.split(" ");
				for (String f : fields) {
					String[] parts = f.split(":");
					if (parts[0].equals("cid")) {
						continue;
					}
					p.put(parts[0], parts[1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return passports;
	}
}