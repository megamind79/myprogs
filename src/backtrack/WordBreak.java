package backtrack;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by dell on 10-09-2017.
 */
public class WordBreak {
    HashSet<String> dictionary;
    String sentence;

    WordBreak (HashSet<String> dictionary, String sentence) {
        this.dictionary = dictionary;
        this.sentence = sentence;
    }

    boolean break_word (ArrayList<String> strings, int index, String word) {
        // u can create sentences either with the first matching word included, or with the next matching word
        // eg: (icecream) - can b done as [ice, (cream)] - include first matching word or icecream - don't include first matching word
        boolean withWord = false;
        if (dictionary.contains(word)) {
            strings.add(word);
            if (index == sentence.length()) {
                System.out.println (strings);
                return true;
            }
            withWord = break_word(new ArrayList<String>(strings), index, "");
            strings.remove(strings.size() - 1);
        }
        if (index == sentence.length())
            return false;
        word += sentence.charAt(index);
        return break_word(new ArrayList<String>(strings), index+1, word) || withWord;
    }

    public static void main(String[] args) {
        HashSet<String> dictionary = new HashSet<String>();
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("q");
        dictionary.add("i");
        dictionary.add("like");
        dictionary.add("sam");
        dictionary.add("sung");
        dictionary.add("samsung");
        dictionary.add("mobile");
        dictionary.add("ice");
        dictionary.add("and");
        dictionary.add("cream");
        dictionary.add("icecream");
        dictionary.add("man");
        dictionary.add("go");
        dictionary.add("mango");
        System.out.println ("dictionary : " + dictionary);
        WordBreak wordBreak = new WordBreak(dictionary, "ilikeicecreamandmango");

        if (!wordBreak.break_word(new ArrayList<String>(), 0, "")) {
            System.out.println ("No Solution");
        }
    }
}
