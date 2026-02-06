package fun.tools;

import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ChineseSemanticSimilarityTools {


    // 语义一致性阈值（可调整，0~1，值越大要求越严格）
    private static final double SEMANTIC_THRESHOLD = 0.6;
    /**
     * 核心方法：计算语义相似度（基于分词+杰卡德相似度）
     */
    public static double calculateSemanticSimilarity(String text1, String text2) {
        // 1. 文本预处理：去空格
        String cleanText1 = text1 == null ? "" : text1.trim();
        String cleanText2 = text2 == null ? "" : text2.trim();

        // 2. 空文本处理
        if (cleanText1.isEmpty() && cleanText2.isEmpty()) return 1.0;
        if (cleanText1.isEmpty() || cleanText2.isEmpty()) return 0.0;

        // 3. 分词 + 过滤停用词 + 保留核心词性（得到关键词语集合）
        Set<String> keyWords1 = getKeyWords(cleanText1);
        Set<String> keyWords2 = getKeyWords(cleanText2);

        // 4. 计算杰卡德相似度：交集大小 / 并集大小
        int intersectionSize = getIntersectionSize(keyWords1, keyWords2); // 共同词语数
        int unionSize = getUnionSize(keyWords1, keyWords2); // 所有不重复词语数

        // 避免除零（无关键词语场景）
        return unionSize == 0 ? 0.0 : (double) intersectionSize / unionSize;
    }

    /**
     * 辅助方法：分词 + 过滤停用词 + 保留核心词性 → 关键词语集合（去重）
     */
    private static Set<String> getKeyWords(String text) {
        // 步骤1：HanLP 标准分词
        List<Term> termList = StandardTokenizer.segment(text);

        // 步骤2：过滤逻辑：
        // 1. 过滤停用词（HanLP 内置停用词表）
        // 2. 保留名词n、动词v、形容词a（核心语义词性）
        // 3. 过滤空字符串，去重（用 Set 自动去重）
        return termList.stream()
                .filter(term -> !CoreStopWordDictionary.contains(term.word)) // 过滤停用词
                .filter(term -> {
                    String nature = term.nature.toString();
                    return nature.startsWith("n") || nature.startsWith("v") || nature.startsWith("a");
                })
                .map(term -> term.word) // 提取词语
                .filter(word -> !word.isEmpty()) // 过滤空词语
                .collect(Collectors.toSet()); // 转为 Set 去重（忽略词语重复）
    }

    /**
     * 辅助方法：计算两个集合的交集大小（共同词语数）
     */
    private static int getIntersectionSize(Set<String> set1, Set<String> set2) {
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2); // 保留两个集合的共同元素
        return intersection.size();
    }

    /**
     * 辅助方法：计算两个集合的并集大小（所有不重复词语数）
     */
    private static int getUnionSize(Set<String> set1, Set<String> set2) {
        Set<String> union = new HashSet<>(set1);
        union.addAll(set2); // 合并两个集合（自动去重）
        return union.size();
    }

    /**
     * 业务方法：判断两个文本是否语义一致
     */
    public static boolean isSemanticallyEqual(String text1, String text2) {
        return calculateSemanticSimilarity(text1, text2) >= SEMANTIC_THRESHOLD;
    }
}
