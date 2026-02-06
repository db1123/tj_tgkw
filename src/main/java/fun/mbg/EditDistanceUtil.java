package fun.mbg;

public class EditDistanceUtil {
    /**
     * 基础版：计算两个字符串的编辑距离（未优化空间）
     * 优点：逻辑直观；缺点：空间复杂度 O(n*m)
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 编辑距离（值越小，相似度越高）
     */
    public static int calculate(String s1, String s2) {
        // 空值处理
        if (s1 == null || s1.isEmpty()) {
            return s2 == null ? 0 : s2.length();
        }
        if (s2 == null || s2.isEmpty()) {
            return s1.length();
        }

        int len1 = s1.length();
        int len2 = s2.length();
        // 构建二维数组：dp[i][j] 表示 s1前i个字符 转 s2前j个字符的最小操作数
        int[][] dp = new int[len1 + 1][len2 + 1];

        // 初始化：s1空 → s2前j个字符，需要插入j次
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }
        // 初始化：s2空 → s1前i个字符，需要删除i次
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        // 动态规划计算
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 字符相等，无需操作，继承左上角值
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 字符不等，取「插入、删除、替换」中最小操作数 +1
                    int insert = dp[i][j - 1]; // 插入：s1前i → s2前j-1，再插入s2[j-1]
                    int delete = dp[i - 1][j]; // 删除：s1前i-1 → s2前j，再删除s1[i-1]
                    int replace = dp[i - 1][j - 1]; // 替换：s1[i-1] 替换为 s2[j-1]
                    dp[i][j] = Math.min(Math.min(insert, delete), replace) + 1;
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * 优化版：空间复杂度 O(min(n,m))（适合长字符串）
     * 原理：仅保留上一行的计算结果，减少内存占用
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 编辑距离
     */
    public static int calculateOptimize(String s1, String s2) {
        if (s1 == null || s1.isEmpty()) {
            return s2 == null ? 0 : s2.length();
        }
        if (s2 == null || s2.isEmpty()) {
            return s1.length();
        }

        // 确保 s1 是较短的字符串，减少数组长度
        if (s1.length() > s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }

        int len1 = s1.length();
        int len2 = s2.length();
        // 仅用一维数组存储上一行结果
        int[] dp = new int[len1 + 1];

        // 初始化第一行
        for (int i = 0; i <= len1; i++) {
            dp[i] = i;
        }

        // 动态规划计算
        for (int j = 1; j <= len2; j++) {
            int prev = dp[0]; // 保存左上角值（dp[i-1][j-1]）
            dp[0] = j; // 第一列初始化（s1空 → s2前j个字符）
            for (int i = 1; i <= len1; i++) {
                int temp = dp[i]; // 保存当前值（下一轮的左上角）
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i] = prev; // 字符相等，直接用左上角值
                } else {
                    // 取「插入（dp[i]）、删除（dp[i-1]）、替换（prev）」最小值 +1
                    dp[i] = Math.min(Math.min(dp[i], dp[i - 1]), prev) + 1;
                }
                prev = temp; // 更新左上角值
            }
        }
        return dp[len1];
    }
}
