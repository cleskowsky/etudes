package a;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Computer {
    protected static final Logger logger = LogManager.getLogger();

    private static final int INPUT = 1;

    private int getArg(int n, int type, List<Integer> prg) {
        if (0 == type) {
            // positional
            int arg = prg.get(n);
            logger.debug(String.format("Positional param: slot %d, value %d", n, arg));
            return arg;
        } else if (1 == type) {
            // immediate
            logger.debug("Immediate param: value " + n);
            return n;
        }
        throw new RuntimeException("Shouldn't get here");
    }

    /**
     * Return value in position 0 after computing
     * program instructions
     *
     * @return program[0]
     */
    public int compute(List<Integer> prg) {
        int arg1, arg2, out;
        int pc = 0;
        int lastDiagnosticCode = -1;

        // tick
        while (true) {
            int op = prg.get(pc) % 100;
            int argTypes = prg.get(pc) / 100;
            System.out.format("pc: %d, encoded: %d, op: %d, argTypes: %d%n", pc, prg.get(pc), op, argTypes);

            switch (op) {
                case 1:
                    // add
                    logger.debug("adding");
                    arg1 = getArg(prg.get(pc + 1), argTypes % 10, prg);
                    logger.debug("arg1: " + arg1);
                    argTypes /= 10;
                    arg2 = getArg(prg.get(pc + 2), argTypes % 10, prg);
                    logger.debug("arg2: " + arg2);
                    argTypes /= 10;
                    out = prg.get(pc + 3);
                    prg.set(out, arg1 + arg2);
                    logger.debug(String.format("set slot value: slot %d, value %d", out, arg1 + arg2));
                    pc += 4;
                    break;

                case 2:
                    // mult
                    arg1 = getArg(prg.get(pc + 1), argTypes % 10, prg);
                    logger.debug("arg1: " + arg1);
                    argTypes /= 10;
                    arg2 = getArg(prg.get(pc + 2), argTypes % 10, prg);
                    logger.debug("arg2: " + arg2);
                    argTypes /= 10;
                    out = prg.get(pc + 3);
                    prg.set(out, arg1 * arg2);
                    logger.debug(String.format("set slot value: slot %d, value %d", out, arg1 * arg2));
                    pc += 4;
                    break;

                case 3:
                    // input
                    logger.debug("getting input");
                    arg1 = prg.get(pc + 1);
                    logger.debug("slot: " + arg1);
                    prg.set(arg1, INPUT);
                    pc += 2;
                    break;

                case 4:
                    // output
                    logger.debug("outputing diagnostic code");
                    int dc = prg.get(pc + 1);
                    logger.debug("diagnostic code: " + dc);
                    lastDiagnosticCode = prg.get(dc);
                    pc += 2;
                    break;

                case 99:
                    // exit
                    return lastDiagnosticCode;

                default:
                    throw new RuntimeException("Opcode doesn't exist");
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> prg = Input.GetProgram("in.txt");
        Computer c = new Computer();
        System.out.println(c.compute(prg));
    }
}