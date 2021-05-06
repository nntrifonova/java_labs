package Morse;
import java.io.*;
import java.util.*;



class Decoder implements Encode {
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
    public void code(InputStreamReader file, Alphabet alph,HashSet<Statistic> char_stat){
        int ch;
        int count = 0;
        StringBuilder string_b = new StringBuilder();
        Writer output = null;
        try{
            output = new OutputStreamWriter(new FileOutputStream("decoded.txt"));
            while((ch = file.read()) != -1){
                if(ch == ' '){
                    if(count == 0 && string_b.length() != 0){
                        char c = alph.decode(string_b.toString());
                        if(c == '$') output.write("\n", 0, 1);
                        else output.write(Character.toString(c), 0, 1);
                        string_b = new StringBuilder();
                    }
                    ++count;
                }
                else{
                    if(count > 1){
                        for(int i = 0; i < count/6; i++) output.write(" ", 0, 1);
                    }
                    count = 0;
                    string_b.append((char)ch);
                }
                char_stat.add(new Statistic((char)ch));

            }
            if(string_b.length() - 1 > 0){
                string_b.deleteCharAt(string_b.length() - 1);
                char c = alph.decode(string_b.toString());
                if(c == '$') output.write("\n", 0, 1);
                else output.write(Character.toString(c), 0, 1);
                string_b = new StringBuilder();
            }
            stat_file(char_stat, "statistics_d.txt");

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
