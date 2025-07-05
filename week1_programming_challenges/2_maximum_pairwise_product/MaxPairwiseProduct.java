import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
    static int getMaxPairwiseProduct(int[] numbers) {
        // Naive approach, compare all possible pairs takes O(N^2) , highly un-optimized for larger inputs
        int max_product = 0;
        int n = numbers.length;

        for (int first = 0; first < n; ++first) {
            for (int second = first + 1; second < n; ++second) {
                max_product = Math.max(max_product,
                        numbers[first] * numbers[second]);
            }
        }

        return max_product;
    }

    static long getMaxPairwiseProductFast(int[] numbers) {
        // more optimized solution takes O(N) time
        int n = numbers.length;
        if (n < 2)
            throw new IllegalArgumentException("Need at least two numbers");

        int index1 = 0;
        for (int i = 1; i < n; i++) {
            if (numbers[i] > numbers[index1]) {
                index1 = i;
            }
        }

        int index2 = (index1 == 0) ? 1 : 0;
        for (int i = 0; i < n; i++) {
            // we should skip the index1 instead of skipping the number, bcz two largest numbers can be same like {5, 5}.
            if (i != index1 && numbers[i] > numbers[index2]) {
                index2 = i;
            }
        }
        return 1L * numbers[index1] * numbers[index2];
    }

    static long getMaxPairWiseProductSorted(int[] numbers) {
        int n = numbers.length;

        // one possible solution but takes O(nlogn) time due to sorting , can be reduced to the O(N).
        Arrays.sort(numbers);
        return 1L * numbers[n - 1] * numbers[n - 2];
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProductFast(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}
