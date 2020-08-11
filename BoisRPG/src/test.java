
public class test {


    public static void main(String[] args) {
        int n = 6;
        triNum(n);
//        System.out.println(triNum(n));
//        System.out.println(calculatingTriNumber(n));
    }

    public static int fib(int n) {

        if (n >= 3) {
            return fib(n - 1) + fib(n - 2);
        } else {
            return 1;
        }

    }

    //staircase problem, not using dynamic programming
    public static int numWays(int n) {
        System.out.println(System.currentTimeMillis());
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return numWays(n - 1) + numWays(n - 2);
        }
    }

    public static int triNum(int n) {
        if (n == 1) {
            System.out.println("Returned 1");
            return 1;
        } else {
            int result = n + triNum(n -1);
            System.out.println("Returned " + result);
            return result;
        }
    }

    public static int calculatingTriNumber(int n) {
        int triangularNum = 0;

        while (n > 0) {
            triangularNum += n--;
        }
        return triangularNum;
    }


    //staircase problem using dynamic programming and a bottom up approach
    public static int numWaysBottomUp(int n) {
        if (n == 0 || n == 1) return 1;
        int[] nums = new int[n + 1];
        nums[0] = 1;
        nums[1] = 1;
        for (int i = 2; i <= n; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
        }

        return nums[n];
    }

    public static int fact(int n) {
        if (n >= 1) {
            return n * fact(n - 1);
        } else {
            return 1;
        }
    }
}
