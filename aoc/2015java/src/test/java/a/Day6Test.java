package a;

import org.junit.jupiter.api.Test;

import a.Day6.Action;
import a.Day6.Input;
import a.Day6.Instruction;
import a.Day6.Point;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6Test {

	@Test
	public void parseInstruction() {

		assertEquals(
			new Instruction(Action.TURN_ON, new Point(887, 9), new Point(959, 629)),
			Input.parseInstruction("turn on 887,9 through 959,629")
		);
		assertEquals(
			new Instruction(Action.TOGGLE, new Point(887, 9), new Point(959, 629)),
			Input.parseInstruction("toggle 887,9 through 959,629")
		);
	}
}