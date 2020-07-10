package a;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Input {
    private static final Logger logger = LogManager.getLogger();

    static List<Integer> GetProgram(String fn) {
        logger.info("Reading program from: " + fn);

        List<Integer> prg = new ArrayList<>();
        try (FileReader r = new FileReader(fn)) {
            BufferedReader br = new BufferedReader(r);
            for (String t : br.readLine().split(",")) {
                prg.add(Integer.parseInt(t.trim()));
            }
        } catch (IOException e) {
            logger.error("Couldn't open file: " + e.toString());
        }

        return prg;
    }
}
