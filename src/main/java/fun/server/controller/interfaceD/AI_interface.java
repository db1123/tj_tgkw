package fun.server.controller.interfaceD;

import com.alibaba.fastjson.JSONObject;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TInterface;
import fun.server.model.TInterfaceAi;
import fun.server.model.TInterfaceAiExample;
import fun.server.service.TInterfaceAiService;
import fun.server.service.TInterfaceService;
import fun.server.service.TInterfaceTypeService;
import fun.tools.ParamTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;

/**
 * AI数据接口管理 相关业务处理
 */
@RestController
@RequestMapping("/s/ai_interface")
public class AI_interface {
	   private final TInterfaceTypeService tInterfaceTypeService;
	   private final TInterfaceService tInterfaceService;
	   private final TInterfaceAiService tInterfaceAiService;

	    public AI_interface(TInterfaceTypeService tInterfaceTypeService, TInterfaceService tInterfaceService,TInterfaceAiService tInterfaceAiService) {
	        this.tInterfaceTypeService = tInterfaceTypeService;
	        this.tInterfaceService = tInterfaceService;
	        this.tInterfaceAiService = tInterfaceAiService;
	    }
	
	/**
     * 添加AI数据接口信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加AI数据接口信息")
    @ResponseBody
    @RequestMapping(value = "/addai_interface", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addinterface(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
    	 JSONObject object = new JSONObject();
	        // 获取请求参数
	        JSONObject jsonParam = ParamTools.getParameters(request);
	        String id = jsonParam.getString("id");
	        //String url="http://192.168.18.34:8100/api/interface_llm_start";
	        String question="";
	        String temperature="";
	        String answer_parameters="";
	        String result="";

	        try {
	            id = id == null ? "0" : ParamTools.getdeParam(id);
	            long key = Long.parseLong(id);
	            //System.out.print(key);
	            TInterface tinterface = tInterfaceService.findById(key);
	            question=tinterface.getFinputparameters()+"根据以上数据，"+tinterface.getFrequire()+"返回格式如下："+ tinterface.getFreturnparameters();
	            temperature=tinterface.getFmaturity().toString();
	            answer_parameters="[{\"name\": \"ai_result\", \"description\": \""+tinterface.getFrequire()+"\"}]";
		             //调用AI接口
		             result=AI_interface.sendPost_json(question, temperature, answer_parameters);
		             //System.out.println(result);
		             
	           		 JSONObject jsonObject1 = JSONObject.parseObject(result);
	                 String code = jsonObject1.getString("code");
	                 String message = jsonObject1.getString("message");
	                 String ai_id = jsonObject1.getString("key_id");
	                 if(code.equals("200")) {
	 	            	//更新AI模型返回的ID
	 	                String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
	 	                String uid = ""; // 当前登录用户ID
	 	                if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
	 	                    uid = ParamTools.getdeParam(CookiesLoginabilityID);
	 	                }
	 	                // 更新信息
	 	                TInterface tinterface2 = new TInterface();
	 	                tinterface2.setFkeyid(key);
	 	                tinterface2.setFinterfacellmid(ai_id);
	 	                tinterface2.setFuid(Long.parseLong(uid));
	 	                tinterface2.setFinterfacellmstate(1);//修改AI接口模型状态
	 	                tinterface2.setFudate(new Date());
	 	                tInterfaceService.update(tinterface2);
	 	                // 返回值
	 	                object.put("result", "success");
	                	 
	                 }else {
	                	 // 返回值
	                	 object.put("result", "ai_error");
		 	             object.put("ai_error", message);
	                 }
	            
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
    
    /**
     * 查询AI数据接口信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("查询AI数据接口信息")
    @ResponseBody
    @RequestMapping(value = "/addai_interface_search", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addai_interface_search(HttpServletRequest request)
            throws IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String FInterfacellmID = jsonParam.getString("FInterfacellmID");

        //String url = "http://192.168.18.34:8100/api/interface_llm_end";
        String result = "";

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            
            // 查询信息
            TInterface tinterface = tInterfaceService.findById(key);
            JSONObject interface_object = new JSONObject();
            
            if(tinterface.getFinterfacellmReturn().equals("")||tinterface.getFinterfacellmReturn()==null) {
            	 // 调用AI接口
                result = AI_interface.sendPost_json_search(FInterfacellmID);
                JSONObject jsonObject1 = JSONObject.parseObject(result);
                String code = jsonObject1.getString("code");

                if (code.equals("200")) {
                    String info = jsonObject1.getString("info");
                    JSONObject jsonObject1_info = JSONObject.parseObject(info);
                    String answer = jsonObject1_info.getString("answer");
                    JSONObject jsonObject1_answer = JSONObject.parseObject(answer);
                    String ai_result = jsonObject1_answer.getString("ai_result");
                    // 更新AI模型返回的ID
                    String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                    String uid = ""; // 当前登录用户ID
                    if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
                        uid = ParamTools.getdeParam(CookiesLoginabilityID);
                    }

                    // 更新信息
                    TInterface tinterface2 = new TInterface();
                    tinterface2.setFkeyid(key);
                    // tinterface2.setFinterfacellmid(ai_id);
                    tinterface2.setFuid(Long.parseLong(uid));
                    tinterface2.setFinterfacellmstate(2); // 修改AI接口模型状态
                    tinterface2.setFinterfacellmReturn(ai_result); // 修改AI接口返回值
                    tinterface2.setFudate(new Date());
                    tInterfaceService.update(tinterface2);
                    // 返回值
                    object.put("result", "success");
                    object.put("ai_result", ai_result);
                } else {
                    // 返回值
                    //object.put("result", "success");
                    //object.put("ai_result", interface_object);
                }
            }else {
            	  interface_object.put("FInterfacellm_Return", tinterface.getFinterfacellmReturn() == null ? "" : tinterface.getFinterfacellmReturn());
                  // 返回值
                  object.put("result", "success");
                  object.put("ai_result", interface_object.get("FInterfacellm_Return"));
                 
            }
            
          
            
           
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
    
    
    /**
     * 查询AI数据接口信息-调用接口查看
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("查询AI数据接口信息-调用查看")
    @ResponseBody
    @RequestMapping(value = "/hire_interface_search", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String hire_interface_search(HttpServletRequest request)
            throws IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            
            // 查询信息
            TInterface tinterface = tInterfaceService.findById(key);
            JSONObject interface_object = new JSONObject();
            interface_object.put("FInterfacellm_Return", tinterface.getFinterfacellmReturn() == null ? "" : tinterface.getFinterfacellmReturn());
            // 返回值
            object.put("result", "success");
            object.put("ai_result", interface_object.get("FInterfacellm_Return"));
           
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
    
    

//    
//    /**
//     * 查询AI数据接口信息-调用接口查看
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//	@LogOperation("查询AI数据接口信息-调用查看")
//	@ResponseBody
//	@RequestMapping(value = "/hire_interface_search_id", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//	public String hire_interface_search_id(HttpServletRequest request) throws IOException {
//	    JSONObject object = new JSONObject();
//	    // 获取请求参数
//	    JSONObject jsonParam = ParamTools.getParameters(request);
//	    String id = jsonParam.getString("id");
//	    String hire_id = jsonParam.getString("hire_id");
//	    //String url = "http://192.168.18.34:8100/api/interface_llm_end";
//	    String result = "";
//	    int maxRetries = 10; // 最大重试次数
//	    int retryCount = 0;
//	    try {
//			hire_id = hire_id == null ? "0" : ParamTools.getdeParam(hire_id);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//        long hire_id_key = Long.parseLong(hire_id);
//	    // 查询信息
// 	    TInterfaceAiExample interfaceAiExample = new TInterfaceAiExample();
//        TInterfaceAiExample.Criteria criteria = interfaceAiExample.createCriteria();
//        criteria.andFtypeEqualTo(2);
//        criteria.andFtypeidEqualTo(hire_id_key);
//        criteria.andFinterfacellmidEqualTo(id);
// 	    List<TInterfaceAi> interfaceAi_list = tInterfaceAiService.findByExample(interfaceAiExample);
// 	    for (TInterfaceAi interfaceAi : interfaceAi_list) {
// 	    	if(interfaceAi.getFinterfacellmReturn()==null||interfaceAi.getFinterfacellmReturn().equals("")) {
//
// 	   	    do {
// 	   	        try {
// 	   		        	
// 	   		             //System.out.println("类型id:"+hire_id_key);
// 	   		             //System.out.println("大模型ID："+id);
// 	   	        		Thread.sleep(5000);
// 	   		            // 调用AI接口
// 	   		            result = AI_interface.sendPost_json_search(id);
// 	   		            JSONObject jsonObject1 = JSONObject.parseObject(result);
// 	   		            String code = jsonObject1.getString("code");
// 	   		           //System.out.println(jsonObject1);
// 	   		            if (code.equals("200")) {
// 	   		                String info = jsonObject1.getString("info");
// 	   		                JSONObject jsonObject1_info = JSONObject.parseObject(info);
// 	   		                String answer = jsonObject1_info.getString("answer");
// 	   		                JSONObject jsonObject1_answer = JSONObject.parseObject(answer);
// 	   		               // System.out.println("jsonObject1_answer:"+jsonObject1_answer);
// 	   		                String ai_result = jsonObject1_answer.getString("ai_result");
// 	   		                //System.out.println("ai_result:"+ai_result);
// 	   	                if (ai_result != null && !ai_result.isEmpty()) {
// 	   	                	
// 	   	                	 // 更新信息
// 	   	                	 String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
// 	   	                     String uid = ""; // 当前登录用户ID
// 	   	                     if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
// 	   	                         uid = ParamTools.getdeParam(CookiesLoginabilityID);
// 	   	                     }
// 	   	                    
// 	   	              	    
// 	   	              	    
// 	   	              	        TInterfaceAi tinterface2 = new TInterfaceAi();
// 	   	              	        tinterface2.setFkeyid(interfaceAi.getFkeyid());
// 	   		                    tinterface2.setFinterfacellmid(id);
// 	   		                    tinterface2.setFtypeid(hire_id_key);
// 	   		                    tinterface2.setFuid(Long.parseLong(uid));
// 	   		                    tinterface2.setFinterfacellmstate(2); // 修改AI接口模型状态
// 	   		                    tinterface2.setFinterfacellmReturn(ai_result); // 修改AI接口返回值
// 	   		                    tinterface2.setFudate(new Date());
// 	   		                    tInterfaceAiService.update(tinterface2);
// 	   	                     
// 	   	              	     
// 	   		                  
// 	   	                	
// 	   	                	
// 	   	                    // 返回值
// 	   	                    object.put("result", "success");
// 	   	                    object.put("ai_result", ai_result);
// 	   	                    break; // 成功获取到有效结果，退出重试循环
// 	   	                }
// 	   	            }
// 	   	        } catch (Exception e) {
// 	   	            e.printStackTrace();
// 	   	            object.put("result", "error");
// 	   	            object.put("error", e.toString());
// 	   	            break; // 发生异常，退出重试循环
// 	   	        }
// 	   	        retryCount++;
// 	   	    } while (retryCount <= maxRetries);
// 	   	
// 	   		    if (retryCount > maxRetries) {
// 	   		        object.put("result", "error");
// 	   		        object.put("error", "多次尝试后仍未获取到有效结果");
// 	   		    }
// 	    	}else {
// 	    		  // 返回值
//                    object.put("result", "success");
//                    object.put("ai_result",interfaceAi.getFinterfacellmReturn() );
// 	    		
// 	    	}
// 	    
//	
// 	    }
//	
//	    return object.toString();
//	}
    /**
     * 查询AI数据接口信息-调用接口查看
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
	@LogOperation("查询AI数据接口信息-调用查看")
	@ResponseBody
	@RequestMapping(value = "/hire_interface_search_id", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String hire_interface_search_id(HttpServletRequest request) throws IOException {
	    JSONObject object = new JSONObject();
	    // 获取请求参数
	    JSONObject jsonParam = ParamTools.getParameters(request);
	    String id = jsonParam.getString("id");
	    String hire_id = jsonParam.getString("hire_id");
	    
	    String result = "";
	    int maxRetries = 50; // 最大重试次数，5秒*20=100秒超时
	    int retryCount = 0;
	    
	    try {
			hire_id = hire_id == null ? "0" : ParamTools.getdeParam(hire_id);
		} catch (Exception e1) {
			e1.printStackTrace();
			object.put("result", "error");
			object.put("error", "解析hire_id失败: " + e1.getMessage());
			return object.toString();
		}
	    
	    try {
	        long hire_id_key = Long.parseLong(hire_id);
	        
	        // 查询信息
	        TInterfaceAiExample interfaceAiExample = new TInterfaceAiExample();
	        TInterfaceAiExample.Criteria criteria = interfaceAiExample.createCriteria();
	        criteria.andFtypeEqualTo(2);
	        criteria.andFtypeidEqualTo(hire_id_key);
	        criteria.andFinterfacellmidEqualTo(id);
	        
	        List<TInterfaceAi> interfaceAi_list = tInterfaceAiService.findByExample(interfaceAiExample);
	        
	        for (TInterfaceAi interfaceAi : interfaceAi_list) {
	            // 检查是否已有有效返回结果
	            if (interfaceAi.getFinterfacellmReturn() != null && 
	                !interfaceAi.getFinterfacellmReturn().isEmpty() &&
	                !"null".equalsIgnoreCase(interfaceAi.getFinterfacellmReturn().trim()) &&
	                !"[]".equals(interfaceAi.getFinterfacellmReturn().trim())) {
	                
	                object.put("result", "success");
	                object.put("ai_result", interfaceAi.getFinterfacellmReturn());
	                return object.toString();
	            }
	            
	            // 没有有效结果，开始轮询获取
	            boolean gotValidData = false;
	            do {
	                try {
	                    // 等待5秒后再请求
	                    Thread.sleep(5000);
	                    
	                    // 调用AI接口
	                    result = AI_interface.sendPost_json_search(id);
	                    
	                    // 检查接口返回是否有效
	                    if (result == null || result.trim().isEmpty()) {
	                        retryCount++;
	                        continue;
	                    }
	                    
	                    JSONObject jsonObject1 = JSONObject.parseObject(result);
	                    String code = jsonObject1.getString("code");
	                    
	                    // 检查状态码
	                    if (!"200".equals(code)) {
	                        retryCount++;
	                        continue;
	                    }
	                    
	                    // 检查info字段
	                    String info = jsonObject1.getString("info");
	                    if (info == null || info.trim().isEmpty()) {
	                        retryCount++;
	                        continue;
	                    }
	                    
	                    JSONObject jsonObject1_info = JSONObject.parseObject(info);
	                    String answer = jsonObject1_info.getString("answer");
	                    
	                    // 检查answer字段
	                    if (answer == null || answer.trim().isEmpty()) {
	                        retryCount++;
	                        continue;
	                    }
	                    
	                    JSONObject jsonObject1_answer = JSONObject.parseObject(answer);
	                    String ai_result = jsonObject1_answer.getString("ai_result");
	                    
	                    // 检查ai_result是否为有效数据
	                    if (ai_result != null && !ai_result.trim().isEmpty() &&
	                        !"null".equalsIgnoreCase(ai_result.trim()) &&
	                        !"[]".equals(ai_result.trim())) {
	                        
	                        // 更新信息
	                        String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request);
	                        String uid = "";
	                        if (CookiesLoginabilityID != null && !CookiesLoginabilityID.isEmpty()) {
	                            uid = ParamTools.getdeParam(CookiesLoginabilityID);
	                        }
	                        
	                        TInterfaceAi tinterface2 = new TInterfaceAi();
	                        tinterface2.setFkeyid(interfaceAi.getFkeyid());
	                        tinterface2.setFinterfacellmid(id);
	                        tinterface2.setFtypeid(hire_id_key);
	                        if (!uid.isEmpty()) {
	                            tinterface2.setFuid(Long.parseLong(uid));
	                        }
	                        tinterface2.setFinterfacellmstate(2); // 修改AI接口模型状态
	                        tinterface2.setFinterfacellmReturn(ai_result); // 保存AI接口返回值
	                        tinterface2.setFudate(new Date());
	                        tInterfaceAiService.update(tinterface2);
	                        
	                        // 返回成功结果
	                        object.put("result", "success");
	                        object.put("ai_result", ai_result);
	                        gotValidData = true;
	                        break; // 成功获取有效数据，退出循环
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    object.put("result", "error");
	                    object.put("error", "请求过程发生异常: " + e.getMessage());
	                    return object.toString(); // 发生异常，直接返回
	                }
	                retryCount++;
	            } while (retryCount <= maxRetries);
	            
	            // 处理重试结果
	            if (!gotValidData) {
	                object.put("result", "error");
	                object.put("error", "超过最大重试次数(" + maxRetries + ")，未获取到有效结果");
	            }
	            
	            // 处理完第一个符合条件的接口记录后退出循环
	            break;
	        }
	        
	        // 如果没有找到对应的接口记录
	        if (interfaceAi_list.isEmpty()) {
	            object.put("result", "error");
	            object.put("error", "未找到对应的AI接口记录");
	        }
	        
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	        object.put("result", "error");
	        object.put("error", "hire_id格式错误: " + e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	        object.put("result", "error");
	        object.put("error", "系统异常: " + e.getMessage());
	    }
	    
	    return object.toString();
	}

    
	
	
	public static void main(String[] args) throws IOException {

//		try {
//		    String url = "http://192.168.18.34:8100/api/interface_llm_start";
//		    // 表单格式调用
//		    String formParams = "question=请列出西游记的主要角色、简介以及最终结局。格式如下:{name:\"人物姓名\",memo:\"人物简介\",result:\"结局\"}&temperature=0.5&answer_parameters=[{\"name\": \"biography\", \"description\": \"西游记的主要角色、简介以及最终结局\"}]";
//		    String response2 = sendPost(url, formParams, "application/x-www-form-urlencoded; charset=UTF-8");
//		    System.out.println(response2);
//		} catch (Exception e) {
//		    System.err.println("处理异常: " + e.getMessage());
//		    e.printStackTrace();
//		}
		//JSON-AI接口
//		HttpURLConnection connection = null;
//		BufferedReader reader = null;
//		URL url = null;
//		url = new URL( "http://192.168.18.34:8100/api/interface_llm_start");
//		connection = (HttpURLConnection)url.openConnection();
//		connection.setRequestMethod("POST");
//		connection.setDoOutput(true);
//		connection.setRequestProperty("Content-Type", "application/json");
//		OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
//		JSONObject paramObject = new JSONObject();
//		paramObject.put("question", "请列出西游记的主要角色、简介以及最终结局。格式如下:{\"name\":\"人物姓名\",\"memo\":\"人物简介\",\"result\":\"结局\"}");
//		paramObject.put("temperature", "0.5");
//		paramObject.put("answer_parameters", "[{\"name\": \"biography\", \"description\": \"西游记的主要角色、简介以及最终结局\"}]");
//		paramout.write(paramObject.toString());
//		paramout.flush();
//		paramout.close();
//		StringBuffer data = new StringBuffer(); 
//		reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
//		String line;
//		String result = "";
//        while ((line = reader.readLine()) != null) {
//              result += line;
//              System.out.println(result);
//          }
		
		
		//AI查询结果
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		URL url = null;
		url = new URL( "https://192.168.18.34:8100/api/interface_llm_end");
		connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
		JSONObject paramObject = new JSONObject();
		paramObject.put("key_id", "gAAAAABoCKfPVlzVbF7OGqmgS_rPZuN9SdX-h-SJNi8EijryxSDqvu80n2yLQNdkLiV9Pd5WQ02nBeUXFGsSJg0S-Arn-7pUjVHLK9V6pE8OUZ17mq87JPA=");
		paramout.write(paramObject.toString());
		paramout.flush();
		paramout.close();
		StringBuffer data = new StringBuffer(); 
		reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line;
		String result = "";
        while ((line = reader.readLine()) != null) {
              result += line;
              System.out.println(result);
          }
		       
	}
	//AI模型接口调用-String传参类型
	public static String sendPost(String url, String param, String contentType) {
	    PrintWriter out = null;
	    BufferedReader in = null;
	    String result = "";
	    try {
	        URL realUrl = new URL(url);
	        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

	        // 设置通用请求头
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("accept", "*/*");
	        conn.setRequestProperty("connection", "Keep-Alive");
	        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	        conn.setRequestProperty("Content-Type", contentType); // 动态设置 Content-Type
	        conn.setRequestProperty("Charset", "UTF-8");
	        conn.setDoOutput(true);
	        conn.setDoInput(true);

	        // 发送请求参数
	        try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")) {
	            writer.write(param);
	            writer.flush();
	        }

	        // 读取响应
	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                result += line;
	            }
	        }

	    } catch (Exception e) {
	        System.out.println("发送 POST 请求出现异常！" + e);
	        e.printStackTrace();
	    }
	    return result;
	}
	
	
	public static String sendPost_json(String question, String temperature,String answer_parameters) {
		// 1. 设置不验证SSL证书（不安全！仅用于测试）
		disableSSLVerification();
		String result = "";
		try {
			//String url="https://192.168.18.34:8000/api/interface_llm_start";
			String url="https://127.0.0.1:9495/api/interface_llm_start";
			HttpURLConnection connection = null;
			BufferedReader reader = null;
			URL url2 = null;
			url2 = new URL(url);
			connection = (HttpURLConnection)url2.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
			JSONObject paramObject = new JSONObject();
			paramObject.put("question", question);
			paramObject.put("temperature", temperature);
			paramObject.put("answer_parameters", answer_parameters);
			paramout.write(paramObject.toString());
			paramout.flush();
			paramout.close();
			StringBuffer data = new StringBuffer(); 
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
		
	        while ((line = reader.readLine()) != null) {
	              result += line;
	              //System.out.println(result);
	          }
		} catch (Exception e) {
		    System.err.println("处理异常: " + e.getMessage());
		    e.printStackTrace();
		}
		return result;   
	        
	}
	
	public static String sendPost_json_search( String key_id) {
		// 1. 设置不验证SSL证书（不安全！仅用于测试）
		disableSSLVerification();
		String result = "";
		try {
			//String url="https://192.168.18.34:8000/api/interface_llm_end";
			String url="https://127.0.0.1:9495/api/interface_llm_end";
			HttpURLConnection connection = null;
			BufferedReader reader = null;
			URL url2 = null;
			url2 = new URL(url);
			connection = (HttpURLConnection)url2.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
			JSONObject paramObject = new JSONObject();
			paramObject.put("key_id", key_id);
			paramout.write(paramObject.toString());
			paramout.flush();
			paramout.close();
			StringBuffer data = new StringBuffer(); 
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
		
	        while ((line = reader.readLine()) != null) {
	              result += line;
	              //System.out.println(result);
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
}