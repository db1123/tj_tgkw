package fun.server.controller.basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import com.alibaba.fastjson.JSONObject;
public class SetPost_old {
	
	//接口调用
    public static String sendPostAddUser(String username, String password, String name, 
            String sex, String email, String phone, 
            String address, String sourceGroup) {
			// 1. 设置不验证SSL证书（不安全！仅用于测试）
		    disableSSLVerification();
			String result = "";
			try {
			// 新接口地址
			String url = "https://192.168.18.34:8000/api/interface_user_add";
			HttpURLConnection connection = null;
			BufferedReader reader = null;
			URL url2 = new URL(url);
			
			connection = (HttpURLConnection) url2.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			
			OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			JSONObject paramObject = new JSONObject();
			
			// 设置请求参数
			paramObject.put("username", username);      // 用户名(必填)
			paramObject.put("password", password);      // 密码(必填)
			paramObject.put("name", name);              // 姓名(必填)
			if (sex != null && !sex.isEmpty()) {
			paramObject.put("sex", sex);            // 性别(可选) 1男 2女
			}
			if (email != null && !email.isEmpty()) {
			paramObject.put("email", email);        // 邮箱(可选)
			}
			if (phone != null && !phone.isEmpty()) {
			paramObject.put("phone", phone);        // 电话(可选)
			}
			if (address != null && !address.isEmpty()) {
			paramObject.put("address", address);    // 地址(可选)
			}
			paramObject.put("source_group", sourceGroup); // 来源组(必填)，固定值1
			
			paramout.write(paramObject.toString());
			paramout.flush();
			paramout.close();
			
			// 读取响应结果
			StringBuffer data = new StringBuffer();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			
			while ((line = reader.readLine()) != null) {
			result += line;
			}
			} catch (Exception e) {
			System.err.println("处理异常: " + e.getMessage());
			e.printStackTrace();
			}
			return result;
			}
    
    // 禁用SSL验证的方法
 	private static void disableSSLVerification() {
 		try {
 			// 创建信任所有证书的TrustManager
 			TrustManager[] trustAllCerts = new TrustManager[] {
 					new X509TrustManager() {
 						public X509Certificate[] getAcceptedIssuers() {
 							return null;
 						}
 						public void checkClientTrusted(X509Certificate[] certs, String authType) {
 						}
 						public void checkServerTrusted(X509Certificate[] certs, String authType) {
 						}
 					}
 			};

 			// 创建SSLContext并使用我们定义的TrustManager
 			SSLContext sc = SSLContext.getInstance("SSL");
 			sc.init(null, trustAllCerts, new java.security.SecureRandom());
 			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

 			// 创建不验证主机名的HostnameVerifier
 			HostnameVerifier allHostsValid = new HostnameVerifier() {
 				public boolean verify(String hostname, SSLSession session) {
 					return true;
 				}
 			};

 			// 安装HostnameVerifier
 			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 	}
 	
 	
	public static void main(String[] args) throws IOException {
		// 调用用户添加接口
		String result = sendPostAddUser(
		    "testuserzs",    // 用户名(必填)
		    "543216",   // 密码(必填)
		    "张三",           // 姓名(必填)
		    "1",              // 性别(可选，1=男)
		    "zhangsan@test.com", // 邮箱(可选)
		    "13812345678",       // 电话(可选)
		    "",     // 地址(可选)
		    "1"               // 来源组(必填，固定值1)
		);
		System.out.println(result);
	    JSONObject jsonObject1 = JSONObject.parseObject(result);
        String code = jsonObject1.getString("code");
        String message = jsonObject1.getString("message");
        String id = jsonObject1.getString("id");
        System.out.println(code);
        System.out.println(message);
        System.out.println(id);
        if(code.equals("200")) {
        	
        }else {
        	
        }
	}
}