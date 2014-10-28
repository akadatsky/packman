package sample.board;

import java.util.ArrayList;
import java.util.List;

public class Map {

    public static List<String> getMap(){
        List<String> result = new ArrayList<String>();

        // 0 - empty
        // 1 - stone
        // g - gold
        // a - hero
        // e - enemy

        result.add("111111111");
        result.add("1a0000001");
        result.add("10101g101");
        result.add("100g000e1");
        result.add("101010101");
        result.add("10g000001");
        result.add("10101g1e1");
        result.add("10e000001");
        result.add("111111111");

        return result;
    }

}
