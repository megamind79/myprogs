package dynamicProgramming;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StockBuySell {
    HashMap<Integer, Integer> hashMap = new HashMap<>();

    /**
     * Buy and sell a single stock only once at https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
     * @param prices list of stock prices
     * @return max profit
     */
    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int diff_array[] = new int[n];
        for (int i = 0; i<n-1; i++)
            diff_array[i] = prices[i+1] - prices[i];
        int profit = 0, s = 0, f = 0, sale = 0, max_sale = 0;
        for (int i = 0; i<n-1; i++) {
            sale += diff_array[i];
            if (max_sale < sale)
                max_sale = sale;
            if (sale < 0) {
                sale = 0;
                profit += max_sale;
            }
        }
        return max_sale;
    }

    /**
     * Buy and sell a single stock multiple times, i.e you can't own more than 1 stock at a time
     * at http://www.geeksforgeeks.org/stock-buy-sell/,
     * @param prices list of stock prices
     * @return max profit
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int diff_array[] = new int[n];
        for (int i = 0; i<n-1; i++)
            diff_array[i] = prices[i+1] - prices[i];
        int profit = 0, s = 0, f = 0, sale = 0, max_sale = 0;
        for (int i = 0; i<n; i++) {
            if (diff_array[i] > 0) {
                sale += diff_array[i];
                if (max_sale < sale)
                    max_sale = sale;
            } else {
                profit += max_sale;
                if (max_sale > 0)
                    hashMap.put(s, i);
                s = i+1;
                max_sale = sale = 0;
            }
        }

        for (Map.Entry<Integer, Integer> entry: hashMap.entrySet()) {
            System.out.println ("Buy on day " + entry.getKey() + "\tSell on day " + entry.getValue());
        }

        return profit;
    }

    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);

        int prices[] = {100, 180, 260, 310, 120, 100, 695};

        StockBuySell obj = new StockBuySell();
        System.out.println (obj.maxProfit1(prices));

//        for (Map.Entry<Integer, Integer> entry: obj.hashMap.entrySet()) {
//            System.out.println ()
//        }
    }
}
