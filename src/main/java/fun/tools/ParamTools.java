package fun.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public class ParamTools {
    /**
     * 参数加密方法秘钥
     */
    private static final String Key = "mw@f#kle^wf3j*fm3w2De3Lr4&UJM^YHN";

    /**
     * 加密
     */
    public static String getEnParam(String param) throws Exception {
        return AESTools.enCrypt(param, Key);
    }

    /**
     * 解密
     */
    public static String getdeParam(String param) throws Exception {
        return AESTools.deCrypt(param, Key);
    }

    /**
     * 生成随机数字和字母
     */
    public static String getStringRandom(int length) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        //参数length，表示生成几位随机数  
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字  
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val.append((char) (random.nextInt(26) + temp));
            } else {
                val.append(random.nextInt(10));
            }
        }
        return val.toString();
    }

    /**
     * 获得全球唯一性的id
     */
    public static String getId() {
        String id = UUID.randomUUID().toString(); //生成的id942cd30b-16c8-449e-8dc5-028f38495bb5中间含有横杠
        id = id.replace("-", ""); //替换掉中间的那个斜杠
        return id;
    }

    /**
     * 获取当前登录用户Cookies中用户ID
     */
    public static String getCookiesLoginUserID(HttpServletRequest request) {
        // String uid = "C38FB8E8346D1E544FFB7841C14BE4E5A9B79251381BB57C874B0EF99F69B09E"; // 管理员
        String uid = "";
        try {
            Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
            if (cookies != null) {
                for (Cookie cookieRead : cookies) {
                    if (cookieRead.getName().equals("simulation_dmsnid")) {
                        uid = cookieRead.getValue();
                    }
                }
            } else {
                if (request.getHeader("simulation_dmsnid") != null) {
                    uid = request.getHeader("simulation_dmsnid");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if (uid.equals("null")) {
//            uid = "";
//        }
        return uid;
    }


    /**
     * 获取当前供应商登录用户Cookies中用户ID
     */
    public static String getCookiesLoginSupplierUserID(HttpServletRequest request) {
        // String uid = "C38FB8E8346D1E544FFB7841C14BE4E5A9B79251381BB57C874B0EF99F69B09E"; // 管理员
        String uid = "";
        try {
            Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
            if (cookies != null) {
                for (Cookie cookieRead : cookies) {
                    if (cookieRead.getName().equals("simulation_supplierdmsnid")) {
                        uid = cookieRead.getValue();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uid;
    }

    /**
     * 通过request来获取到json数据
     */
    public static JSONObject getParameters(HttpServletRequest request)
            throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        String acceptJson = sb.toString();
        JSONObject jo = null;
        if (!acceptJson.equals("")) {
            jo = JSONObject.parseObject(acceptJson);
        }
        return jo;
    }

    /**
     * 转换Null为''
     */
    public static String NullToStr(String temp) {
        String str = "";
        if (temp != null) {
            str = temp;
        }
        return str;
    }

    /**
     * 特殊字符过滤
     *
     * @param str
     * @return
     */
    public static String stringFilter(String str) {
        String regEx = "[\\u00A0\\s\"`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 转换中英文字符
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String[] enSymbol = {"\"", "'", ",", "\t", "\n", "\r"};
        String[] cnSymbol = {"“", "‘", "，", "", "", "", ""}; //后面的想换成什么字符
        for (int i = 0; i < enSymbol.length; i++) {
            str = str.replaceAll(enSymbol[i], cnSymbol[i]);
        }
        return str;
    }
}