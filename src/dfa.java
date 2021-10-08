import java.util.HashMap;
import java.util.Map;

public class dfa {
    HashMap<String, String> map = new HashMap<>();
    identifier idf = new identifier();

    public dfa() {
        map.put("if", "If");
        map.put("else", "Else");
        map.put("while", "While");
        map.put("break", "Break");
        map.put("continue", "Continue");
        map.put("return", "Return");

        map.put("=", "Assign");
        map.put(";", "Semicolon");
        map.put("(", "LPar");
        map.put(")", "RPar");
        map.put("{", "LBrace");
        map.put("}", "RBrace");
        map.put("+", "Plus");
        map.put("*", "Mult");
        map.put("/", "Div");
        map.put("<", "Lt");
        map.put(">", "Gt");
        map.put("==", "Eq");
    }

    public void lex(String in) throws Exception {
        int len = in.length();
        int pointer = 0;
        while (pointer < len) {
            char start = in.charAt(pointer);
            String begin = in.substring(pointer, pointer + 1);
            String left = in.substring(pointer, len);
            if (idf.nonDigit(start)) {
                int lenOfIdent = idf.ident(left);
                pointer += lenOfIdent;
                String Ident = left.substring(0, lenOfIdent);
                if (lenOfIdent == 0) {
                    throw new Exception("0 length of nonDigit start word");
                }
                if (map.containsKey(Ident)) {
                    System.out.println(map.get(Ident));
                    // 如果是特殊类标识符单词，输出
                } else {
                    System.out.println("Ident(" + Ident + ")");
                    // 是标识符单词，输出
                }
            }// 首字母为NonDigit，处理ident和部分特殊符号情况
            else if (idf.digit(start)) {
                int lenOfNum = idf.num(left);
                pointer += lenOfNum;
                String Num = left.substring(0, lenOfNum);
                if (lenOfNum == 0) {
                    throw new Exception("0 length of Digit start word");
                }
                System.out.println("Number(" + Num + ")");//输出数字
            } //处理数字开头的类
            else if (map.containsKey(begin) && start != '=') {
                pointer++;
                System.out.println(map.get(begin));
            }// 处理非“=”的单字符特殊类
            else if (start == '=') {
                int lenOfEq = idf.eq(left);
                pointer += lenOfEq;
                switch (lenOfEq) {
                    case 1 -> System.out.println("Assign");
                    case 2 -> System.out.println("Eq");
                    default -> throw new Exception("illegal length of eq");
                }
            }// 处理'='开头的单词
            else {
                throw new Exception("illegal character");
            }
        }
    }// 处理没有换行符、空格分隔的一个单元

    static class identifier {
        public boolean letter(char x) {
            return (x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z');
        }

        public boolean digit(char x) {
            return x <= '9' && x >= '0';
        }

        public boolean underline(char x) {
            return x == '_';
        }

        public boolean nonDigit(char x) {
            return letter(x) || underline(x);
        }

        public int ident(String x) {
            if (!nonDigit(x.charAt(0))) return 0;
            int len = x.length();
            for (int i = 1; i < len; i++) {
                if (!(digit(x.charAt(i)) || nonDigit(x.charAt(i)))) {
                    return i;
                }
            }
            return len;
        } //get the length of ident

        public int num(String x) {
            if (!digit(x.charAt(0))) return 0;
            int len = x.length();
            for (int i = 1; i < len; i++) {
                if (!digit(x.charAt(i))) {
                    return i;
                }
            }
            return len;
        } //get the length of ident

        public int eq(String x) {
            if (x.charAt(0) != '=') return 0;
            int len = x.length();
            if (len < 2) return 1;
            else if (x.charAt(1) != '=') return 1;
            return 2;
        }
    }

}

