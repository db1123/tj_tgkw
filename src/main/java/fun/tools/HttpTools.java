package fun.tools;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.net.HttpURLConnection;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class HttpTools {

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性

            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static HttpURLConnection getCon(String url) {
        try {
            URL uri = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(6 * 1000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=---------------------------");
            return conn;
        } catch (MalformedURLException e) {
            System.out.println("请求超时");

        } catch (IOException e) {

        }
        return null;

    }

    public static String sendPostF(String url, File file, Map<String, Object> params, String filename1) {
        String res = "";
        ByteArrayOutputStream ret = null;

        // boundary就是request头和上传文件内容的分隔符
        String boundary = "---------------------------";
        DataInputStream in = null;
        BufferedReader reader = null;
        FileInputStream fileInputStream = null;
        try {
            String rn = "\r\n";
            HttpURLConnection conn = getCon(url);
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // text
            if (params != null) {
                StringBuilder strBuf = new StringBuilder();
                Iterator<Map.Entry<String, Object>> iter = params.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, Object> entry = iter.next();
                    String inputName = entry.getKey();
                    Object inputValue = entry.getValue();
                    if (null == inputValue) {
                        continue;
                    }
                    strBuf.append(rn).append("--").append(boundary).append(rn);
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"" + rn + rn);
                    strBuf.append(inputValue);
                }
                out.write(strBuf.toString().getBytes());
            }
            // file
            if (file != null) {
                StringBuilder strBuf = new StringBuilder();
                strBuf.append(rn).append("--").append(boundary).append(rn);
                strBuf.append("Content-Disposition: form-data; name=\"pic\"; filename=\"" + filename1 + "\"" + rn);
                strBuf.append("Content-Type:application/octet-stream " + rn + rn);
                out.write(strBuf.toString().getBytes());
                fileInputStream = new FileInputStream(file);
                in = new DataInputStream(fileInputStream);

                int bytes;

                byte[] bufferOut = new byte[(int)file.length()];
                while ((bytes = in.read(bufferOut)) != -1) {
                    out.write(bufferOut, 0, bytes);
                }
                in.close();
                fileInputStream.close();
            }

            byte[] endData = ("\r\n--" + boundary + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();

            // *******************************************
            // 读取返回数据
            /*
             * StringBuilder strBuf = new StringBuilder(); reader = new
             * BufferedReader( new InputStreamReader(conn.getInputStream()));
             * String line; while ((line = reader.readLine()) != null) {
             * strBuf.append(line).append("\n"); } res = strBuf.toString();
             * System.out.println(" 返回：" + res); //System.out.println(" 返回：" +
             * DESUtil.decrypt(res)); reader.close();
             */
            // ****newread*****************************************

            InputStream input = conn.getInputStream();
            // 字节数组
            byte[] bcache = new byte[2048];
            int readSize = 0;// 每次读取的字节长度



            ret = new ByteArrayOutputStream();
            try {
                // 一次性读取2048字节
                while ((readSize = input.read(bcache)) > 0) {
                    ret.write(bcache, 0, readSize);
                }
            } catch (IOException e1) {
                System.out.println(e1.getMessage());
            } finally {
                try {
                    // 输入流关闭
                    input.close();
                } catch (IOException e) {

                }
            }

            System.out.println(" 返回：" + ret.toString());

            // *****newreadend
            // ***************************************************************

            conn.disconnect();
        } catch (IOException e) {

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (reader != null) {
                    reader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }


        return ret.toString();

    }


    private static final int TIME_OUT = 8 * 1000;                          //超时时间
    private static final String CHARSET = "utf-8";                         //编码格式
    private static final String PREFIX = "--";                            //前缀
    private static final String BOUNDARY = UUID.randomUUID().toString();  //边界标识 随机生成
    private static final String CONTENT_TYPE = "multipart/form-data";     //内容类型
    private static final String LINE_END = "\r\n";                        //换行


    /**
     * post请求方法
     * */
    public static String postRequest(String requestUrl,final Map<String, String> strParams, final Map<String, File> fileParams) {
        final BufferedReader[] reader = {null};
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    URL url = new URL(requestUrl);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setReadTimeout(TIME_OUT);
                    conn.setConnectTimeout(TIME_OUT);
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);//Post 请求不能使用缓存
                    //设置请求头参数
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("Charset", "UTF-8");
                    conn.setRequestProperty("Content-Type", CONTENT_TYPE+";boundary=" + BOUNDARY);
                    /**
                     * 请求体
                     */
                    //上传参数
                    DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                    //getStrParams()为一个
                    dos.writeBytes( getStrParams(strParams).toString() );
                    dos.flush();

                    //文件上传
                    StringBuilder fileSb = new StringBuilder();
                    for (Map.Entry<String, File> fileEntry: fileParams.entrySet()){
                        fileSb.append(PREFIX)
                                .append(BOUNDARY)
                                .append(LINE_END)
                                /**
                                 * 这里重点注意： name里面的值为服务端需要的key 只有这个key 才可以得到对应的文件
                                 * filename是文件的名字，包含后缀名的 比如:abc.png
                                 */
                                .append("Content-Disposition: form-data; name=\"file\"; filename=\""
                                        + fileEntry.getKey() + "\"" + LINE_END)
                                .append("Content-Type: image/jpg" + LINE_END) //此处的ContentType不同于 请求头 中Content-Type
                                .append("Content-Transfer-Encoding: 8bit" + LINE_END)
                                .append(LINE_END);// 参数头设置完以后需要两个换行，然后才是参数内容
                        dos.writeBytes(fileSb.toString());
                        dos.flush();
                        InputStream is = new FileInputStream(fileEntry.getValue());
                        byte[] buffer = new byte[1024];
                        int len = 0;
                        while ((len = is.read(buffer)) != -1){
                            dos.write(buffer,0,len);
                        }
                        is.close();
                        dos.writeBytes(LINE_END);
                    }
                    //请求结束标志
                    dos.writeBytes(PREFIX + BOUNDARY + PREFIX + LINE_END);
                    dos.flush();
                    dos.close();
//                    Log.e(TAG, "postResponseCode() = "+conn.getResponseCode() );
                    //读取服务器返回信息
                    InputStream in = conn.getInputStream();
                    reader[0] = new BufferedReader(new InputStreamReader(in));
                    String line = null;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader[0].readLine()) != null) {
                        response.append(line);
                    }
//                    if (conn.getResponseCode() == 200) {
//
////                        Log.e(TAG, "run: " + response);
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                }
            }
        }).start();

        return reader[0].toString();
    }

    /**
     * 对post参数进行编码处理
     * */
    private static StringBuilder getStrParams(Map<String,String> strParams){
        StringBuilder strSb = new StringBuilder();
        for (Map.Entry<String, String> entry : strParams.entrySet() ){
            strSb.append(PREFIX)
                    .append(BOUNDARY)
                    .append(LINE_END)
                    .append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINE_END)
                    .append("Content-Type: text/plain; charset=" + CHARSET + LINE_END)
                    .append("Content-Transfer-Encoding: 8bit" + LINE_END)
                    .append(LINE_END)// 参数头设置完以后需要两个换行，然后才是参数内容
                    .append(entry.getValue())
                    .append(LINE_END);
        }
        return strSb;
    }

    public static String getIpByRequest(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(",")) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals( "0:0:0:0:0:0:0:1" )){
            ip = "127.0.0.1";//ip格式处理
        }
        return ip;
    }
}
