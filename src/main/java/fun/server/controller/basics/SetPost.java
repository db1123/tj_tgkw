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

public class SetPost {
	
	// 接口调用 - 添加用户
    public static String sendPostAddUser(String username, String password, String name, 
            String sex, String email, String phone, 
            String address, String sourceGroup) {
			// 1. 设置不验证SSL证书（不安全！仅用于测试）
		    disableSSLVerification();
			String result = "";
			try {
			// 新接口地址
			String url = "https://172.30.75.189:9495/api/interface_user_add";
			//String url = "https://127.0.0.1:9495/api/interface_user_add";
			
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
    
    // 接口调用 - 查询用户详情
    public static String sendPostGetUserDetail(String keyId) {
        // 设置不验证SSL证书（仅用于测试）
        disableSSLVerification();
        String result = "";
        try {
            // 查询接口地址
            String url = "https://172.30.75.189:9495/api/interface_user_detail";
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            URL url2 = new URL(url);
            
            connection = (HttpURLConnection) url2.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            
            OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            JSONObject paramObject = new JSONObject();
            
            // 设置请求参数（key_id为必填项）
            paramObject.put("key_id", keyId);
            
            paramout.write(paramObject.toString());
            paramout.flush();
            paramout.close();
            
            // 读取响应结果
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            
            while ((line = reader.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.err.println("查询用户异常: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    
    // 接口调用 - 删除用户
    public static String sendPostDeleteUser(String keyId) {
        // 设置不验证SSL证书（仅用于测试）
        disableSSLVerification();
        String result = "";
        try {
            // 删除接口地址
            //String url = "https://127.0.0.1:9495/api/interface_user_delete";
            String url = "https://172.30.75.189:9495/api/interface_user_delete";
            
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            URL url2 = new URL(url);
            
            connection = (HttpURLConnection) url2.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            
            OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            JSONObject paramObject = new JSONObject();
            
            // 设置请求参数（key_id为必填项）
            paramObject.put("key_id", keyId);
            
            paramout.write(paramObject.toString());
            paramout.flush();
            paramout.close();
            
            // 读取响应结果
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            
            while ((line = reader.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.err.println("删除用户异常: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    
    // 接口调用 - 更新用户信息
    public static String sendPostUpdateUser(String keyId, String username, String password, 
            String name, String sex, String email, String phone, String address) {
        // 设置不验证SSL证书（仅用于测试）
        disableSSLVerification();
        String result = "";
        try {
            // 更新接口地址
            //String url = "https://127.0.0.1:9495/api/interface_user_update";
            String url = "https://172.30.75.189:9495/api/interface_user_update";
            
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            URL url2 = new URL(url);
            
            connection = (HttpURLConnection) url2.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            
            OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            JSONObject paramObject = new JSONObject();
            
            // 设置请求参数（key_id为必填项，其他为可选）
            paramObject.put("key_id", keyId);  // 标识ID(必填)
            
            if (username != null && !username.isEmpty()) {
                paramObject.put("username", username);  // 用户名(可选)
            }
            if (password != null && !password.isEmpty()) {
                paramObject.put("password", password);  // 密码(可选)
            }
            if (name != null && !name.isEmpty()) {
                paramObject.put("name", name);  // 姓名(可选)
            }
            if (sex != null && !sex.isEmpty()) {
                paramObject.put("sex", sex);  // 性别(可选) 1男 2女
            }
            if (email != null && !email.isEmpty()) {
                paramObject.put("email", email);  // 邮箱(可选)
            }
            if (phone != null && !phone.isEmpty()) {
                paramObject.put("phone", phone);  // 电话(可选)
            }
            if (address != null && !address.isEmpty()) {
                paramObject.put("address", address);  // 地址(可选)
            }
            
            paramout.write(paramObject.toString());
            paramout.flush();
            paramout.close();
            
            // 读取响应结果
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            
            while ((line = reader.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.err.println("更新用户异常: " + e.getMessage());
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
//		String addResult = sendPostAddUser(
//		    "testuserzs001",    // 用户名(必填)
//		    "543216",   // 密码(必填)
//		    "张三1",           // 姓名(必填)
//		    "11",              // 性别(可选，1=男)
//		    "zhangsan1@test.com", // 邮箱(可选)
//		    "13812345678",       // 电话(可选)
//		    "",     // 地址(可选)
//		    "1"               // 来源组(必填，固定值1)
//		);
//		System.out.println("添加用户结果: " + addResult);
//	    JSONObject addJson = JSONObject.parseObject(addResult);
//        String code = addJson.getString("code");
//        String message = addJson.getString("message");
//        String userId = addJson.getString("id");
//        System.out.println("添加结果代码: " + code);
//        //System.out.println("添加结果信息: " + message);
//        System.out.println("用户ID: " + userId);
//        
//        // 如果添加成功，演示查询、更新和删除操作
//        if("200".equals(code) && userId != null && !userId.isEmpty()) {
//        	// 查询用户详情
//        	String detailResult = sendPostGetUserDetail(userId);
//        	System.out.println("用户详情: " + detailResult);
//        	
//        	// 更新用户信息
//        	String updateResult = sendPostUpdateUser(
//        			userId,        // key_id(必填)
//        			"testuserzs001",          // 不更新用户名
//        			"newpassword", // 更新密码
//        			"张三三",      // 更新姓名
//        			null,          // 不更新性别
//        			"new_zhangsan@test.com", // 更新邮箱
//        			null,          // 不更新电话
//        			"北京市海淀区"  // 更新地址
//        	);
//        	System.out.println("更新用户结果: " + updateResult);
//        	
//        	// 删除用户
//        	String deleteResult = sendPostDeleteUser(userId);
//        	System.out.println("删除用户结果: " + deleteResult);
//        }
        
        
     // 查询用户详情
    	String detailResult = sendPostGetUserDetail("gAAAAABoyM6RGUNz90mH8bhGkFz5WlQKgQWAjTYhM9UVwFqbKnPHGh5fYIcZ8Sth8hPetMDXRuyafWeji2VCwONTDXzyzp3lqRDcrfav-weUz6K-12kW4ls=");
    	System.out.println("用户详情: " + detailResult);
    	
	}
}
