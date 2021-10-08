import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class lexical {
    public static void main(String[] args) {
        dfa DFA = new dfa();
        String InputDir;
        for (String arg : args) {
            InputDir = arg;
            try {
                FileInputStream fr = new FileInputStream(InputDir);
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(fr));
                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                    ArrayList<String> words = getWord(str);
                    for (String word : words) {
                        DFA.lex(word);
                    }
                }
            } catch (Exception ignored) {
                System.out.println("Err");
            }
        }
    }

    static ArrayList<String> getWord(String x) {
        int len = x.length();
        ArrayList<String> result = new ArrayList<>();
        StringBuilder tmp = new StringBuilder("");
        for (int i = 0; i < len; i++) {
            if (x.charAt(i) != ' ' && x.charAt(i) != '\n' && x.charAt(i) != '\t' && x.charAt(i) != '\r') {
                tmp.append(x.charAt(i));
            } else {
                if (tmp.length() != 0) {
                    result.add(tmp.toString());
                    tmp = new StringBuilder("");
                }
            }
        }
        if (tmp.length() > 0) {
            result.add(tmp.toString());
        }
        return result;
    }
}
