package Morse;
//import Morse.Alphabet;
import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) {

        Scanner scanner_ = new Scanner(System.in);
        Alphabet alphabet = new Alphabet();
        BufferedReader read_alph = null;
        HashSet<Statistic> char_stat = new HashSet<>();
        InputStreamReader file = null;

        FileReader file_reader = null;
        try {
            file_reader = new FileReader("morse_alph.txt");
            Scanner scanner = new Scanner(file_reader);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Character letter = line.charAt(0);
                String code = line.substring(1);
                alphabet.build_table_code(letter, code);
                alphabet.build_table_decode(code, letter);
            }
            alphabet.build_table_code('\n', "\n");
            alphabet.build_table_code('\r', "\r");
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        } finally {
            if (null != file_reader) {
                try {
                    file_reader.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }

        }

        String[] command = (scanner_.nextLine()).split(" ");
        if(command[0].equals("exit")) return;

        else{
            try{
            file = new InputStreamReader(new FileInputStream(command[1]));

                if (command[0].equals("code")){

                    Coder coder = new Coder();
                    coder.code(file, alphabet, char_stat);
                }

                if (command.equals("decode")){
                    Decoder decoder = new Decoder();
                    decoder.code(file, alphabet, char_stat);
                }
            }
            catch (IOException e){
                System.err.println("Error while reading file: " + e.getLocalizedMessage());
            }
            finally {
                if (null != file)
                {
                    try
                    {
                        file.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace(System.err);
                    }
                }
            }
        }
    }

}