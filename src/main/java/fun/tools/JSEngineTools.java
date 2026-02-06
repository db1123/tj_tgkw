package fun.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class JSEngineTools {
    public HtmlUnitDriver webDriver = null;
    private String url;
  
    public JSEngineTools() {
        // 网页模拟器
        webDriver = new HtmlUnitDriver();
        // 设置支持js
        webDriver.setJavascriptEnabled(true);
        // 获取配置信息
        url = getPropertiesValue("url");
    }

    // 获取properties属性
    public static String getPropertiesValue(String name) {
        String value = "";
        try {
            Properties properties = new Properties();
            // 使用ClassLoader加载properties配置文件生成对应的输入流
            InputStream in = JSEngineTools.class.getClassLoader().getResourceAsStream("hu.properties");
            // 使用properties对象加载输入流
            properties.load(in);
            //获取key对应的value值
            value = properties.getProperty(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    // 获取文件内容
    public static String getFileContent(String filePath) {
        String content = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
            String contentLine = br.readLine();
            content = contentLine + "\r\n";
            while (contentLine != null) {
                contentLine = br.readLine();
                content += contentLine + "\r\n";
            }
        } catch (FileNotFoundException fnFe) {
            System.out.println("文件不存在" + fnFe);
        } catch (IOException ioe) {
            System.out.println("I/O 错误: " + ioe);
        } finally {
            try {
                if (br!=null) {
                    br.close();
                }
            } catch (IOException ioe) {
                System.out.println("关闭InputStream句柄错误: " + ioe);
            }
        }
        return content;
    }

    // 返回执行结果 HtmlUnit
    public Object callJsHU(String ParamID) throws Exception {
        WebClient wc = new WebClient();
        wc.getOptions().setJavaScriptEnabled(true);  // 启用JS解释器，默认为true
        wc.getOptions().setCssEnabled(false);  // 禁用css支持
        // 设置Ajax异步处理控制器即启用Ajax支持
        wc.setAjaxController(new NicelyResynchronizingAjaxController());
        // 当出现Http error时，程序不抛异常继续执行
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
        // 防止js语法错误抛出异常
        wc.getOptions().setThrowExceptionOnScriptError(false);
        // 拿到这个网页
        HtmlPage page = wc.getPage( "http://127.0.0.1:8080/module/getJsData.html" );
        // 关闭连接
        wc.close();
        // 获取网页内容
        String xml = page.asXml();
        // 解析xml
        Document document = Jsoup.parse(xml);
        Element div = document.getElementById("v");
        // 返回结果
        return div.text();
    }

    // 返回执行结果 HtmlUnitDriver
    public Object callJsHUDriver(String ParamID) throws Exception {
        // 执行
        String visit = "http://" + url + "/module/getJsData.html?id=" + ParamID;
        webDriver.get(visit);
        // 获取网页内容
        String xml = webDriver.getPageSource();
        // 解析xml
        Document document = Jsoup.parse(xml);
        Element div = document.getElementById("v");
        // 返回结果
        return div == null ? null : div.text();
    }

    // 关闭 HtmlUnitDriver
    public void callJsHUDriverClose() throws Exception {
        webDriver.close();
    }
}