package Morse;

import java.io.*;
import java.util.HashSet;
import java.util.Set;


class Coder implements Encode {
    @Override
    public void stat_file(HashSet<Statistic> set, String file) {
        FileWriter f_w = null;
        try {
            f_w = new FileWriter("src/Morse/" + file);
            for (Statistic ch : set) {
                f_w.write(ch.toString() + '\n');
            }
        } catch (IOException e) {
            System.err.println("Reading error: " + e.getLocalizedMessage());
        } finally {
            if (null != f_w) {
                try {
                    f_w.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
    @Override
    public void code(InputStreamReader file, Alphabet alph, HashSet<Statistic> char_stat){
        int ch;
        Writer output = null;
        try {
            output = new OutputStreamWriter(new FileOutputStream("coded.txt"));
            while((ch = file.read()) != -1) {
                ch = Character.toUpperCase(ch);
                if (ch == ' ') {
                    output.write("      ", 0, 6);
                } else if (ch == '\n') {
                    String str = alph.code('$')+System.lineSeparator();
                    output.write(str, 0, str.length());
                } else {
                    String str = alph.code((char) ch) + " ";
                    output.write(str, 0, str.length());
                }
                char_stat.add(new Statistic((char)ch));
            }
            stat_file(char_stat, "statistics.txt");
        }
        catch (IOException e){
            System.err.println("Writing error: " + e.getLocalizedMessage());
        }
        finally {
            if (null != output) {
                try {
                    output.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }

    }
}
