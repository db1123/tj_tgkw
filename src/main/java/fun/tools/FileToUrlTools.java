package fun.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileToUrlTools {
    // 处理 预览或者下载文件时路径问题

    //储存上传文件的文件夹名称
    private static final String FWJNAME="TJ_tgkw";

    public static String convertPathToUrl(String BASE_URL,String filePath) {
        // 定义匹配路径的正则表达式
        Pattern pattern = Pattern.compile(".*"+FWJNAME+"/([^/]+)/([^/]+)(?:/[^/]+)?/(\\d{4}-\\d{2})/(\\d+\\..+)");
        Matcher matcher = pattern.matcher(filePath.replace("//", "/")); // 统一使用正斜杠

        if (matcher.find()) {
            String category = matcher.group(2);  // p, c等
            String dateFolder = matcher.group(3); // 2025-06等
            String fileName = matcher.group(4);   // 1385231310653489152.xlsx等
            return BASE_URL + "/" + category + "/" + dateFolder + "/" + fileName;
        }
        // 如果没有匹配到，返回原始路径或处理错误
        return filePath;
    }
}
