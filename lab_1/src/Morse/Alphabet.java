package Morse;
import java.util.*;

public class Alphabet {
    private final Map<Character, String> alph_code;
    private final Map<String, Character> alph_decode;

    Alphabet(){
        alph_code = new HashMap<>();
        alph_decode = new HashMap<>();
    }

    void build_table_code(Character ch, String str){
        alph_code.put(ch, str);
    }

    void build_table_decode(String str, Character ch){

        alph_decode.put(str, ch);
    }

    String code(Character ch){
        return alph_code.get(ch);
    }

    Character decode(String str) {
        return alph_decode.get(str);
    }

}