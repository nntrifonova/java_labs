package Morse;
import java.io.*;
import java.util.HashSet;

public interface Encode {
    void code(InputStreamReader file, Alphabet alph, HashSet<Statistic> char_stat) throws java.io.IOException;
    void stat_file(HashSet<Statistic> set, String file);
}
