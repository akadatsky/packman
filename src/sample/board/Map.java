package sample.board;

import sample.Config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Map {

    public static List<String> getMap() {
        return readFile();
    }

    public static List<String> getDefoltMap() {
        List<String> result = new ArrayList<String>();

        // 0 - empty
        // 1 - stone
        // g - gold
        // a - hero
        // e - enemy

        result.add("11111111111");
        result.add("1a0000000e1");
        result.add("10101g10101");
        result.add("10000000001");
        result.add("10101010101");
        result.add("10g00000001");
        result.add("10101g10101");
        result.add("1e0000000e1");
        result.add("11111111111");

        return result;
    }

    private static List<String> readFile() {
        Path path = Paths.get(Config.MAP_FILE);
        List<String> result = getDefoltMap();
        try {
            result = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            // do nothing
        }
        return result;
    }

}
