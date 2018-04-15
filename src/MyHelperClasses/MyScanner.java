package MyHelperClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Fast scanner for competitive coding as Scanner is really slow since it parses data
 * while scanning it character by character. So using BufferedReader and StringTokenizer
 * to create a custom scanner similar to Scanner class.
 */
public class MyScanner {
    private BufferedReader bufferedReader;
    private StringTokenizer stringTokenizer;

    /**
     * MyHelperClasses.MyScanner constructor
     * @param inputStream something like System.in or new FileInputStream("f.txt")
     */
    public MyScanner(InputStream inputStream) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * @return next String token in the stream.
     */
    public String next() {
        while (stringTokenizer == null || !stringTokenizer.hasMoreTokens())
            try {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        return stringTokenizer.nextToken();
    }

    /**
     * @return next integer found in the stream.
     */
    public int nextInt() {
        return Integer.parseInt(next());
    }

    /**
     * @return next long found in the stream.
     */
    public long nextLong() {
        return Long.parseLong(next());
    }

    /**
     * @return next sentence found in the stream.
     * @throws IOException
     */
    public String nextLine() throws IOException {
        return bufferedReader.readLine();
    }

    /**
     * @return if more tokens are there in input stream.
     */
    public boolean hasNext() {
        while (stringTokenizer == null || !stringTokenizer.hasMoreTokens())
            try {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            } catch (IOException e) {
                return false;
            }
        return true;
    }
}
