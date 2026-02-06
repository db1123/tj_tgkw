package fun.tools;

import cn.hutool.extra.pinyin.PinyinUtil;

public class SimplePinyinTools {
    // 最简：中文转全拼（无声调）
    public static String chineseToPinyin(String chinese) {
        // 空值兜底 + 转拼音（非中文字符直接保留）
        return chinese == null ? "" : PinyinUtil.getPinyin(chinese, "");
    }

    // 最简：中文转首字母
    public static String chineseToFirstLetter(String chinese) {
        return chinese == null ? "" : PinyinUtil.getFirstLetter(chinese, "");
    }
}
