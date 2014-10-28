package sample.board;

import java.util.ArrayList;
import java.util.List;

public class Map {

    public static List<String> getMap() {
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

}
