package dynamicProgramming;

public class LongestNonRepeatingCharacterSubstring {
    /**
     * returns a longest non repeating character substring of a string at http://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
     * @param str input string
     * @return longest non repeating character substring
     */
    static String longestUniqueSubstring (String str) {
        int previous_index = 0;
        int visited[] = new int[26];
        for (int i = 0; i<26; i++)
            visited[i] = -1;
        visited[str.charAt(0)-'A'] = 0;
        int current_length = 1, max_length = 1, maxi = 0, maxj = 0;
        for (int i = 1; i<str.length(); i++) {
            previous_index = visited[str.charAt(i)-'A'] + 1;
            if (visited[str.charAt(i)-'A'] == -1 || i+1-current_length > previous_index)
                current_length++;
            else {
                if (current_length > max_length) {
                    maxi = previous_index;
                    maxj = i;
                    max_length = current_length;
                }
                current_length = i + 1 - previous_index;
            }
            visited[str.charAt(i)-'A'] = i;
        }

        return str.substring(maxi, maxj+1);
    }

    public static void main(String args[] ) throws Exception {
        String str = "ABDEFGABEF";
        System.out.println("The input string is " + str);
        String op = longestUniqueSubstring (str);
        int len = op.length();
        System.out.println("The length of the longest non repeating character string " + op + " is " + len);
    }
}
