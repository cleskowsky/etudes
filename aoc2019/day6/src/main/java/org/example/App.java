package org.example;

import java.util.Map;

/**
 * Hello world!
 */
public class App {
	/**
	 * Return number of direct and indirect orbits
	 * @param data A map of orbiter: orbited pairs
	 * @return The number of orbits
	 */
	public int countOrbits(Map<String, String> data) {
		int orbits = 0;
		for (String k : data.keySet()) {
			String orbited = data.get(k);
			while (true) {
				orbits++;
				if (orbited.equals("COM")) {
					// we're done
					break;
				}
				// keep going until we get to COM
				String orbiter = orbited;
				orbited = data.get(orbiter);
			}
		}
		return orbits;
	}
}
