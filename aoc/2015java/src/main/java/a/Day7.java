package a;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

public class Day7 {

	@Data
	static class Signal {
		private List<String> args;
		private GateType gateType;

		public Signal(List<String> wires, GateType gateType) {
			this.args = wires;
			this.gateType = gateType;
		}
	}

	enum GateType {
		AND, OR, NOT, LSHIFT, RSHIFT, IDENT
	}

	static Map<String, GateType> gateTypeLookup = Map.of(
		"AND", GateType.AND,
		"OR", GateType.OR,
		"LSHIFT", GateType.LSHIFT,
		"RSHIFT", GateType.RSHIFT
	);

	public static int getWireSignal(String wireID, Map<String, Signal> wireSignalData, Map<String, Integer> signalCache) {
		try {
			return Integer.parseInt(wireID);
		} catch (NumberFormatException e) {
			// If wireID is an int return immediately
			// eg 1 AND r -> s
		}

		System.out.println(wireSignalData.get(wireID));

		// Have we already figured out signal for wireID?
		if (signalCache.get(wireID) != null) {
			return signalCache.get(wireID);
		}

		Signal s = wireSignalData.get(wireID);
		List<String> args = s.getArgs();
		int x;
		int y;
		switch (s.getGateType()) {
			case NOT:
				x = getWireSignal(args.get(0), wireSignalData, signalCache);
				x ^= 0xFFFF;
				break;
			case IDENT:
				// 1 arg that might be a number or another wire
				try {
					x = Integer.parseInt(args.get(0));
				} catch (NumberFormatException e) {
					x = getWireSignal(args.get(0), wireSignalData, signalCache);
				}
				break;
			case LSHIFT:
				x = getWireSignal(args.get(0), wireSignalData, signalCache);
				x = x << Integer.parseInt(args.get(1));
				break;
			case RSHIFT:
				x = getWireSignal(args.get(0), wireSignalData, signalCache);
				x = x >> Integer.parseInt(args.get(1));
				break;
			case AND:
				x = getWireSignal(args.get(0), wireSignalData, signalCache);
				y = getWireSignal(args.get(1), wireSignalData, signalCache);
				x = x & y;
				break;
			case OR:
				x = getWireSignal(args.get(0), wireSignalData, signalCache);
				y = getWireSignal(args.get(1), wireSignalData, signalCache);
				x = x | y;
				break;
			default:
				throw new RuntimeException("Bad gate type: " + s.getGateType());
		}

		signalCache.put(wireID, x);
		return x;
	}

	public static Map<String, Signal> parseInput(List<String> data) {
		Map<String, Signal> wireToSignalMap = new HashMap<>();
		for (String wire : data) {
			String[] split = wire.split(" -> ");
			String rhs = split[1];

			String lhs = split[0];
			split = lhs.split(" ");

			List<String> wires = new ArrayList<>();
			switch (split.length) {
				case 1:
					wires.add(split[0]);
					wireToSignalMap.put(rhs, new Signal(wires, GateType.IDENT));
					break;
				case 2:
					wires.add(split[1]);
					wireToSignalMap.put(rhs, new Signal(wires, GateType.NOT));
					break;
				case 3:
					wires.add(split[0]);
					wires.add(split[2]);
					wireToSignalMap.put(rhs, new Signal(wires, gateTypeLookup.get(split[1])));
					break;
				default:
					throw new RuntimeException("Too many args in wire: " + wire);
			}
		}
		return wireToSignalMap;
	}

	public static void main(String[] args) throws Exception {
		List<String> data = Files.readAllLines(Path.of("input/7.txt"));
		Map<String, Signal> wireData = parseInput(data);
		System.out.println(wireData);
		System.out.println(getWireSignal("a", wireData, new HashMap<>()));
		// 3176

		// Part b
		wireData.put("b", new Signal(List.of("3176"), GateType.IDENT));
		System.out.println(getWireSignal("a", wireData, new HashMap<>()));
	}
}
