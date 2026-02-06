package fun.server.controller.interfaceD;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import fun.Controller.JavaFileGenerator;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.service.TInterfaceService;
import fun.server.service.TInterfaceTypeService;
import fun.tools.ParamTools;
import fun.tools.SnowflakeIdWorker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * 数据接口管理 相关业务处理
 */
@RestController
@RequestMapping("/s/interface")
public class Interface {
	   private final TInterfaceTypeService tInterfaceTypeService;
	   private final TInterfaceService tInterfaceService;

	    public Interface(TInterfaceTypeService tInterfaceTypeService, TInterfaceService tInterfaceService) {
	        this.tInterfaceTypeService = tInterfaceTypeService;
	        this.tInterfaceService = tInterfaceService;
	    }
	    
	    
	    /**
	     * 获取数据匹配接口信息
	     *
	     * @param request 客户端请求
	     * @return 响应结果
	     * @throws UnsupportedEncodingException 未知编码异常
	     * @throws IOException                  输入输出异常
	     */
	    @ResponseBody
	    @RequestMapping(value = "/queryinterface", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	    public String queryability(HttpServletRequest request)
	            throws UnsupportedEncodingException, IOException {
	        JSONObject object = new JSONObject();
	        // 获取请求参数
	        JSONObject jsonParam = ParamTools.getParameters(request);
	        int results = jsonParam.getInteger("results");
	        int page = jsonParam.getInteger("page");
	        JSONArray columns_JA = jsonParam.getJSONArray("columns");
	        JSONArray order_JA = jsonParam.getJSONArray("order");
	        String name = jsonParam.getString("name");
	        String interfacetype = jsonParam.getString("interfacetype");
	       // System.out.println("interfacetype:"+interfacetype);
	        Integer dataall = jsonParam.getInteger("dataall");
	        try {
	            // 获取数据库记录
	            JSONArray interface_Array = new JSONArray();
	            // 查询条件
	            TInterfaceExample TInterfaceExample = new TInterfaceExample();
	            TInterfaceExample.Criteria criteria = TInterfaceExample.createCriteria();

	            if (name != null && !name.equals("")) {
	                criteria.andFnameLike("%" + name + "%");

	            }
	            
	            if (interfacetype != null && !interfacetype.equals("") && !interfacetype.equals("1")) {
	            	interfacetype = interfacetype == null ? "0" : ParamTools.getdeParam(interfacetype);
	                criteria.andFtypeidEqualTo(Long.parseLong(interfacetype));
	                
	            }
	            // 排序
	            String orderSql = "";
	            for (Object order : order_JA) {
	                JSONObject order_Object = (JSONObject) order;
	                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
	                String colName = column_Object.getString("data");
	                orderSql += "," + colName + " " + order_Object.getString("dir");
	            }
	            if (orderSql.length() > 0) {
	            	TInterfaceExample.setOrderByClause(orderSql.substring(1));
	            } else {
	            	TInterfaceExample.setOrderByClause("FDate desc , FName asc");
	            }
	            PageInfo<TInterface> interfacePageInfo = tInterfaceService.findByExampleMapper(TInterfaceExample, (page - 1) * results, results);
	            List<TInterface> interface_list = interfacePageInfo.getList();

	            for (TInterface tinterface : interface_list) {
	                JSONObject interface_object = new JSONObject();
	                interface_object.put("key", ParamTools.getEnParam(tinterface.getFkeyid().toString()));
	             
	                if (dataall == 1) {
	                
	                	interface_object.put("FName", tinterface.getFname() == null ? "" : tinterface.getFname());
	                	interface_object.put("FInputParameters", tinterface.getFinputparameters() == null ? "" : tinterface.getFinputparameters());
	                	interface_object.put("FReturnParameters", tinterface.getFreturnparameters() == null ? "" : tinterface.getFreturnparameters());
	                	interface_object.put("FRequire", tinterface.getFrequire() == null ? "" : tinterface.getFrequire());
	                	interface_object.put("FState", tinterface.getFstate() == null ? "" : tinterface.getFstate());
	                	interface_object.put("FMaturity", tinterface.getFmaturity() == null ? "" : tinterface.getFmaturity());
	                	
	                	interface_object.put("FInterfacellmState", tinterface.getFinterfacellmstate() == null ? "" : tinterface.getFinterfacellmstate());
	                	interface_object.put("FInterfacellmID", tinterface.getFinterfacellmid() == null ? "" : tinterface.getFinterfacellmid());
	                	
	                			
	                	interface_object.put("FCID", tinterface.getFcid());
	                	interface_object.put("FUID", tinterface.getFuid());
	                	interface_object.put("FCDATE", tinterface.getFcdate());
	                	interface_object.put("FUDATE", tinterface.getFudate());
	                    
	                	TInterfaceType TInterfaceType = tInterfaceTypeService.findById(tinterface.getFtypeid());
	                	interface_object.put("FInterfaceType",TInterfaceType== null ? "" : TInterfaceType.getFname());
	                	
	                } else {
	                	interface_object.put("FName", "*****");
	                	interface_object.put("FInputParameters", "*****");
	                	interface_object.put("FReturnParameters", "*****");
	                	interface_object.put("FMaturity", "*****");
	                	interface_object.put("FState", "*****");
	                	interface_object.put("FCID", "*****");
	                	interface_object.put("FUID", "*****");
	                	interface_object.put("FCDATE", "*****");
	                	interface_object.put("FUDATE", "*****");
	                	interface_object.put("FInterfacellmState", "*****");
	                }

	                interface_object.put("FState", tinterface.getFstate());
	                interface_Array.add(interface_object);
	            }
	            // 返回值
	            object.put("list", interface_Array);
	            object.put("total",interfacePageInfo.getTotal()); // 总行数
	            object.put("page", interfacePageInfo.getPageNum()); // 当前页数
	            object.put("result", "success");
	        } catch (Exception e) {
	            e.printStackTrace();
	            object.put("result", "error");
	            object.put("error", e.toString());
	        }
	        return object.toString();
	    }
	    
	    /**
	     * 变更接口状态
	     *
	     * @param request 客户端请求
	     * @return 响应结果
	     * @throws UnsupportedEncodingException 未知编码异常
	     * @throws IOException                  输入输出异常
	     */
	    @ResponseBody
	    @RequestMapping(value = "/stateinterface", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	    public String stateinterface(HttpServletRequest request)
	            throws UnsupportedEncodingException, IOException {
	        JSONObject object = new JSONObject();
	        // 获取请求参数
	        JSONObject jsonParam = ParamTools.getParameters(request);
	        String id = jsonParam.getString("id");
	        String state = jsonParam.getString("state");
	        try {
	            String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
	            String uid = ""; // 当前登录用户ID
	            if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
	                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
	            }
	            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
	            state = state.equals("1") ? "0" : "1";
	            
	            TInterface tinterface = new TInterface();
	            tinterface.setFkeyid(Long.parseLong(id));
	            tinterface.setFuid(Long.parseLong(uid));
	            tinterface.setFudate(new Date());
	            tinterface.setFstate(Integer.valueOf(state));
	            tInterfaceService.update(tinterface);
	            // 返回值
	            object.put("result", "success");
	        } catch (Exception e) {
	            e.printStackTrace();
	            object.put("result", "error");
	            object.put("error", e.toString());
	        }
	        return object.toString();
	    }
	    
	    /**
	     * 删除接口信息
	     *
	     * @param request 客户端请求
	     * @return 响应结果
	     * @throws UnsupportedEncodingException 未知编码异常
	     * @throws IOException                  输入输出异常
	     */
	    @LogOperation("删除接口信息")
	    @ResponseBody
	    @RequestMapping(value = "/delinterface", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	    public String delinterface(HttpServletRequest request)
	            throws UnsupportedEncodingException, IOException {
	        JSONObject object = new JSONObject();
	        // 获取请求参数
	        JSONObject jsonParam = ParamTools.getParameters(request);
	        String id = jsonParam.getString("id");
	        try {
	            String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
	            String uid = ""; // 当前登录用户ID
	            if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
	                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
	            }
	            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));


	            tInterfaceService.deleteById(Long.parseLong(id));
	            // 返回值
	            object.put("result", "success");

	        } catch (Exception e) {
	            e.printStackTrace();
	            object.put("result", "error");
	            object.put("error", e.toString());
	        }
	        return object.toString();
	    }
	    
	    /**
	     * 添加数据接口信息
	     *
	     * @param request 客户端请求
	     * @return 响应结果
	     * @throws UnsupportedEncodingException 未知编码异常
	     * @throws IOException                  输入输出异常
	     */
	    @LogOperation("添加数据接口信息")
	    @ResponseBody
	    @RequestMapping(value = "/addinterface", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	    public String addinterface(HttpServletRequest request)
	            throws UnsupportedEncodingException, IOException {
	        JSONObject object = new JSONObject();
	        // 获取请求参数
	        JSONObject jsonParam = ParamTools.getParameters(request);
	        String FName = jsonParam.getString("FName");
	        String FInputParameters = jsonParam.getString("FInputParameters");
	        String FReturnParameters = jsonParam.getString("FReturnParameters");
	        String FRequire = jsonParam.getString("FRequire");
	        String FMaturity = jsonParam.getString("FMaturity");


	        String Finterfacetype = jsonParam.getString("Finterfacetype");
	     
	        try {
	        		Finterfacetype = Finterfacetype == null ? "1" : ParamTools.getdeParam(Finterfacetype);
	          
	                String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
	                String uid = ""; // 当前登录用户ID
	                if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
	                    uid = ParamTools.getdeParam(CookiesLoginabilityID);
	                }
	                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
	                long key = idWorker.nextId();
	                // 新建能力信息
	                TInterface tinterface = new TInterface();
	                tinterface.setFkeyid(key);
	                tinterface.setFname(FName);
	                tinterface.setFtypeid(Long.parseLong(Finterfacetype));
	                tinterface.setFinputparameters(FInputParameters);
	                tinterface.setFreturnparameters(FReturnParameters);
	                tinterface.setFrequire(FRequire);
	                tinterface.setFmaturity(Float.parseFloat(FMaturity));
	                tinterface.setFstate(1);
	                tinterface.setFcdate(new Date());
	                tinterface.setFcid(Long.parseLong(uid));
	                tInterfaceService.save(tinterface);
	                // 返回值
	                object.put("result", "success");
	                
	                ///////////////////添加http脚本文件///////////////////////
//	                String[] fields = {
//	                		"   import java.io.File;\r\n"
//	                		+ "import java.io.IOException;\r\n"
//	                		+ "import java.io.UnsupportedEncodingException;\r\n"
//	                		+ "import javax.servlet.http.HttpServletRequest;\r\n"
//	                		+ "import org.springframework.web.bind.annotation.GetMapping;\r\n"
//	                		+ "import org.springframework.web.bind.annotation.RequestMapping;\r\n"
//	                		+ "import org.springframework.web.bind.annotation.RequestMethod;\r\n"
//	                		+ "import org.springframework.web.bind.annotation.RequestParam;\r\n"
//	                		+ "import org.springframework.web.bind.annotation.ResponseBody;\r\n"
//	                		+ "import org.springframework.web.bind.annotation.RestController;\r\n"
//	                		+ "\r\n"
//	                		+ "import com.alibaba.fastjson.JSON;\r\n"
//	                		+ "import com.alibaba.fastjson.JSONArray;\r\n"
//	                		+ "import com.alibaba.fastjson.JSONObject;\r\n"
//	                		+ "\r\n"
//	                		+ "@RestController\r\n"
//	                		+ "public class "+"add"+FMaturity+" {\r\n"
//	                		+ "    @ResponseBody                                                                                                            \r\n"
//	                		+ "       @RequestMapping(value = \"/"+FMaturity+"\", method = RequestMethod.POST, produces = \"application/json;charset=UTF-8\")    \r\n"
//	                		+ "       public String "+FMaturity+"(HttpServletRequest request)                                                                \r\n"
//	                		+ "               throws UnsupportedEncodingException, IOException {         \r\n"
//	                		+ "    	String FName = \""+FName+"\";\r\n"
//	                		+ "        String FInputParameters =\""+FInputParameters.replace("\"", "'").replace("\r\n", " ")+"\";\r\n"
//	                		+ "        JSONObject FInputParameters_jsonObject = JSON.parseObject(FInputParameters); \r\n"
//	                		+ "        \r\n"
//	                		+ "        String FReturnParameters = \""+FReturnParameters.replace("\"", "'").replace("\r\n", " ")+"\";\r\n"
//	                		+ "        JSONObject FReturnParameters_jsonObject = JSON.parseObject(FReturnParameters); \r\n"
//	                		+ "        \r\n"
//	                		+ "        String FRequire = \""+FRequire.replace("\"", "'").replace("\r\n", " ")+"\";\r\n"
//	                		+ "        JSONObject FRequire_jsonObject = JSON.parseObject(FRequire); \r\n"
//	                		+ "        String FMaturity = \"/"+FMaturity+"\";\r\n"
//	                		+ "		JSONArray Array = new JSONArray();                                                                                                                 \r\n"
//	                		+ "       	JSONObject object = new JSONObject();                                                                             \r\n"
//	                		+ "       	object.put(\"接口名称\",FName);     \r\n"
//	                		+ "    	object.put(\"接口调用地址\",FMaturity);     \r\n"
//	                		+ "    	\r\n"
//	                		+ "   		Array.add(object);\r\n"
//	                		+ "   		Array.add(FInputParameters_jsonObject);\r\n"
//	                		+ "   		Array.add(FReturnParameters_jsonObject);\r\n"
//	                		+ "   		Array.add(FRequire_jsonObject);\r\n"
//	                		+ "   	 	return Array.toString();                                                                                             \r\n"
//	                		+ "    			                                                                                                              \r\n"
//	                		+ "       	                                                                                                                  \r\n"
//	                		+ "       }"        		
//	                
//	                };
//	                
	                //Windows-生成java,calss文件
	                //JavaFileGenerator.generateJavaClass("fun.Controller", "add"+FMaturity, fields, "D:\\work\\eclipse_2021_workspace\\student_capability_evaluation_git\\src\\main\\java");
	                //Ubuntu-生成java,calss文件
	                //JavaFileGenerator.generateJavaClass("fun.Controller", "add"+FMaturity, fields, "/data/web_project/student_capability_evaluation/WEB-INF/classes");
	               
	                //////////////////////////////////////////////////////
	                
	                
	        } catch (Exception e) {
	            e.printStackTrace();
	            object.put("result", "error");
	            object.put("error", e.toString());
	        }
	        return object.toString();
	    }
	    

		/**
	     * 修改数据接口信息
	     *
	     * @param request 客户端请求
	     * @return 响应结果
	     * @throws UnsupportedEncodingException 未知编码异常
	     * @throws IOException                  输入输出异常
	     */
	    @LogOperation("修改数据接口信息")
	    @ResponseBody
	    @RequestMapping(value = "/updateinterface", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	    public String updateinterface(HttpServletRequest request)
	            throws UnsupportedEncodingException, IOException {
	        JSONObject object = new JSONObject();
	        // 获取请求参数
	        JSONObject jsonParam = ParamTools.getParameters(request);
	        String id = jsonParam.getString("FKeyID");
	        
	        String FName = jsonParam.getString("FName");
	        String FInputParameters = jsonParam.getString("FInputParameters");
	        String FReturnParameters = jsonParam.getString("FReturnParameters");
	        String FRequire = jsonParam.getString("FRequire");
	        String FMaturity = jsonParam.getString("FMaturity");

	        String Finterfacetype = jsonParam.getString("Finterfacetype");
	       

	        try {
	            id = id == null ? "0" : ParamTools.getdeParam(id);
	            long key = Long.parseLong(id);
	            Finterfacetype = Finterfacetype == null ? "1" : ParamTools.getdeParam(Finterfacetype);
	            
	            TInterfaceExample tInterfaceExample = new TInterfaceExample();
	            TInterfaceExample.Criteria criteria = tInterfaceExample.createCriteria();
	            criteria.andFnameEqualTo(FName);
	            criteria.andFkeyidNotEqualTo(key);
	            
	            List<TInterface> interfaceList = tInterfaceService.findByExample(tInterfaceExample);
	            if (interfaceList.size() == 0) {
	                String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
	                String uid = ""; // 当前登录用户ID
	                if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
	                    uid = ParamTools.getdeParam(CookiesLoginabilityID);
	                }
	                // 更新信息
	                TInterface tinterface = new TInterface();
	                tinterface.setFkeyid(key);
	                tinterface.setFname(FName);
	                tinterface.setFtypeid(Long.parseLong(Finterfacetype));
	                tinterface.setFinputparameters(FInputParameters);
	                tinterface.setFreturnparameters(FReturnParameters);
	                tinterface.setFrequire(FRequire);
	                tinterface.setFmaturity(Float.parseFloat(FMaturity));
	                tinterface.setFstate(1);
	                tinterface.setFuid(Long.parseLong(uid));
	                tinterface.setFudate(new Date());
	                tInterfaceService.update(tinterface);
	              
	                
	                // 返回值
	                object.put("result", "success");
	                
	                
	                ///////////////////添加http脚本文件///////////////////////
//	                String[] fields = {
//	                		"   import java.io.File;\r\n"
//	                		+ "import java.io.IOException;\r\n"
//	                		+ "import java.io.UnsupportedEncodingException;\r\n"
//	                		+ "import javax.servlet.http.HttpServletRequest;\r\n"
//	                		+ "import org.springframework.web.bind.annotation.GetMapping;\r\n"
//	                		+ "import org.springframework.web.bind.annotation.RequestMapping;\r\n"
//	                		+ "import org.springframework.web.bind.annotation.RequestMethod;\r\n"
//	                		+ "import org.springframework.web.bind.annotation.RequestParam;\r\n"
//	                		+ "import org.springframework.web.bind.annotation.ResponseBody;\r\n"
//	                		+ "import org.springframework.web.bind.annotation.RestController;\r\n"
//	                		+ "\r\n"
//	                		+ "import com.alibaba.fastjson.JSON;\r\n"
//	                		+ "import com.alibaba.fastjson.JSONArray;\r\n"
//	                		+ "import com.alibaba.fastjson.JSONObject;\r\n"
//	                		+ "\r\n"
//	                		+ "@RestController\r\n"
//	                		+ "public class "+"add"+FMaturity+" {\r\n"
//	                		+ "    @ResponseBody                                                                                                            \r\n"
//	                		+ "       @RequestMapping(value = \"/"+FMaturity+"\", method = RequestMethod.POST, produces = \"application/json;charset=UTF-8\")    \r\n"
//	                		+ "       public String "+FMaturity+"(HttpServletRequest request)                                                                \r\n"
//	                		+ "               throws UnsupportedEncodingException, IOException {         \r\n"
//	                		+ "    	String FName = \""+FName+"\";\r\n"
//	                		+ "        String FInputParameters =\""+FInputParameters.replace("\"", "'").replace("\r\n", " ")+"\";\r\n"
//	                		+ "        JSONObject FInputParameters_jsonObject = JSON.parseObject(FInputParameters); \r\n"
//	                		+ "        \r\n"
//	                		+ "        String FReturnParameters = \""+FReturnParameters.replace("\"", "'").replace("\r\n", " ")+"\";\r\n"
//	                		+ "        JSONObject FReturnParameters_jsonObject = JSON.parseObject(FReturnParameters); \r\n"
//	                		+ "        \r\n"
//	                		+ "        String FRequire = \""+FRequire.replace("\"", "'").replace("\r\n", " ")+"\";\r\n"
//	                		+ "        JSONObject FRequire_jsonObject = JSON.parseObject(FRequire); \r\n"
//	                		+ "        String FMaturity = \"/"+FMaturity+"\";\r\n"
//	                		+ "		JSONArray Array = new JSONArray();                                                                                                                 \r\n"
//	                		+ "       	JSONObject object = new JSONObject();                                                                             \r\n"
//	                		+ "       	object.put(\"接口名称\",FName);     \r\n"
//	                		+ "    	object.put(\"接口调用地址\",FMaturity);     \r\n"
//	                		+ "    	\r\n"
//	                		+ "   		Array.add(object);\r\n"
//	                		+ "   		Array.add(FInputParameters_jsonObject);\r\n"
//	                		+ "   		Array.add(FReturnParameters_jsonObject);\r\n"
//	                		+ "   		Array.add(FRequire_jsonObject);\r\n"
//	                		+ "   	 	return Array.toString();                                                                                             \r\n"
//	                		+ "    			                                                                                                              \r\n"
//	                		+ "       	                                                                                                                  \r\n"
//	                		+ "       }"        		
//	                
//	                };
	                
	                //Windows-生成java,calss文件
	                //JavaFileGenerator.generateJavaClass("fun.Controller", "add"+FMaturity, fields, "D:\\work\\eclipse_2021_workspace\\student_capability_evaluation_git\\src\\main\\java");
	                //Ubuntu-生成java,calss文件
	                //JavaFileGenerator.generateJavaClass("fun.Controller", "add"+FMaturity, fields, "/data/web_project/student_capability_evaluation/WEB-INF/classes");
		               
	                //////////////////////////////////////////////////////
	                
	                
	            } else {
	                // 返回值
	                object.put("result", "fail");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            object.put("result", "error");
	            object.put("error", e.toString());
	        }
	        return object.toString();
	    }
	    
	    /**
	     * 根据ID获取数据接口信息
	     *
	     * @param request 客户端请求
	     * @return 响应结果
	     * @throws UnsupportedEncodingException 未知编码异常
	     * @throws IOException                  输入输出异常
	     */
	    @ResponseBody
	    @RequestMapping(value = "/queryinterfaceInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	    public String queryinterfaceInfo(HttpServletRequest request)
	            throws UnsupportedEncodingException, IOException {
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
	            interface_object.put("key", ParamTools.getEnParam(tinterface.getFkeyid().toString()));
	            //获取数据接口模板ID-名称
	            interface_object.put("FtInterfaceTypeID", ParamTools.getEnParam(tinterface.getFtypeid().toString()));
	            TInterfaceType tInterfaceType = tInterfaceTypeService.findById(tinterface.getFtypeid());
	            interface_object.put("FtInterfaceTypeName", tInterfaceType == null ? "" : tInterfaceType.getFname());
	            
	            
	           
	         
	            interface_object.put("FName", tinterface.getFname() == null ? "" : tinterface.getFname());
	            interface_object.put("FInputParameters", tinterface.getFinputparameters() == null ? "" : tinterface.getFinputparameters());
	            interface_object.put("FReturnParameters", tinterface.getFreturnparameters() == null ? "" : tinterface.getFreturnparameters());
	            interface_object.put("FRequire", tinterface.getFrequire() == null ? "" : tinterface.getFrequire());
	            interface_object.put("FMaturity", tinterface.getFmaturity() == null ? "" : tinterface.getFmaturity());
	        	
	            
	            interface_object.put("FCID", tinterface.getFcid());
	            interface_object.put("FUID", tinterface.getFuid());
	            interface_object.put("FCDATE", tinterface.getFcdate());
	            interface_object.put("FUDATE", tinterface.getFudate());
	            interface_object.put("FState", tinterface.getFstate());
	            // 返回值
	            object.put("info", interface_object);
	            object.put("result", "success");
	        } catch (Exception e) {
	            e.printStackTrace();
	            object.put("result", "error");
	            object.put("error", e.toString());
	        }
	        return object.toString();
	    }
	    

	    /**
	     * 获取数据匹配接口信息——调用
	     *
	     * @param request 客户端请求
	     * @return 响应结果
	     * @throws UnsupportedEncodingException 未知编码异常
	     * @throws IOException                  输入输出异常
	     */
	    @ResponseBody
	    @RequestMapping(value = "/queryinterface_search", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	    public String queryinterface_search(HttpServletRequest request)
	            throws UnsupportedEncodingException, IOException {
	        JSONObject object = new JSONObject();
	        // 获取请求参数
	        JSONObject jsonParam = ParamTools.getParameters(request);
	        int results = jsonParam.getInteger("results");
	        int page = jsonParam.getInteger("page");
	        JSONArray columns_JA = jsonParam.getJSONArray("columns");
	        JSONArray order_JA = jsonParam.getJSONArray("order");
	  
	        Integer dataall = jsonParam.getInteger("dataall");
	        try {
	            // 获取数据库记录
	            JSONArray interface_Array = new JSONArray();
	            // 查询条件
	            TInterfaceExample TInterfaceExample = new TInterfaceExample();
	            TInterfaceExample.Criteria criteria = TInterfaceExample.createCriteria();
	            criteria.andFinterfacellmstateEqualTo(2);//设置接口状态为已匹配完成
	            criteria.andFinterfacellmidIsNotNull();//设置接口回传ID不为空
	            criteria.andFkeyidNotEqualTo(Long.parseLong("1"));//设置fkeyid不等于1
	        
	            // 排序
	            String orderSql = "";
	            for (Object order : order_JA) {
	                JSONObject order_Object = (JSONObject) order;
	                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
	                String colName = column_Object.getString("data");
	                orderSql += "," + colName + " " + order_Object.getString("dir");
	            }
//	            if (orderSql.length() > 0) {
//	            	TInterfaceExample.setOrderByClause(orderSql.substring(2));//按名称排序
//	            } else {
//	            	TInterfaceExample.setOrderByClause("FDate desc , FName asc");
//	            }
	            PageInfo<TInterface> interfacePageInfo = tInterfaceService.findByExampleMapper(TInterfaceExample, (page - 1) * results, results);
	            List<TInterface> interface_list = interfacePageInfo.getList();

	            for (TInterface tinterface : interface_list) {
	                JSONObject interface_object = new JSONObject();
	                interface_object.put("key", ParamTools.getEnParam(tinterface.getFkeyid().toString()));
	             
	                if (dataall == 1) {
	                
	                	interface_object.put("FName", tinterface.getFname() == null ? "" : tinterface.getFname());
	                	interface_object.put("FInputParameters", tinterface.getFinputparameters() == null ? "" : tinterface.getFinputparameters());
	                	interface_object.put("FReturnParameters", tinterface.getFreturnparameters() == null ? "" : tinterface.getFreturnparameters());
	                	interface_object.put("FRequire", tinterface.getFrequire() == null ? "" : tinterface.getFrequire());
	                	interface_object.put("FState", tinterface.getFstate() == null ? "" : tinterface.getFstate());
	                	interface_object.put("FMaturity", tinterface.getFmaturity() == null ? "" : tinterface.getFmaturity());
	                	
	                	interface_object.put("FInterfacellmState", tinterface.getFinterfacellmstate() == null ? "" : tinterface.getFinterfacellmstate());
	                	interface_object.put("FInterfacellmID", tinterface.getFinterfacellmid() == null ? "" : tinterface.getFinterfacellmid());
	                	
	                			
	                	interface_object.put("FCID", tinterface.getFcid());
	                	interface_object.put("FUID", tinterface.getFuid());
	                	interface_object.put("FCDATE", tinterface.getFcdate());
	                	interface_object.put("FUDATE", tinterface.getFudate());
	                    
	                	TInterfaceType TInterfaceType = tInterfaceTypeService.findById(tinterface.getFtypeid());
	                	interface_object.put("FInterfaceType",TInterfaceType== null ? "" : TInterfaceType.getFname());
	                	
	                } else {
	                	interface_object.put("FName", "*****");
	                	interface_object.put("FInputParameters", "*****");
	                	interface_object.put("FReturnParameters", "*****");
	                	interface_object.put("FMaturity", "*****");
	                	interface_object.put("FState", "*****");
	                	interface_object.put("FCID", "*****");
	                	interface_object.put("FUID", "*****");
	                	interface_object.put("FCDATE", "*****");
	                	interface_object.put("FUDATE", "*****");
	                	interface_object.put("FInterfacellmState", "*****");
	                }

	                interface_object.put("FState", tinterface.getFstate());
	                interface_Array.add(interface_object);
	            }
	            // 返回值
	            object.put("list", interface_Array);
	            object.put("total",interfacePageInfo.getTotal()); // 总行数
	            object.put("page", interfacePageInfo.getPageNum()); // 当前页数
	            object.put("result", "success");
	        } catch (Exception e) {
	            e.printStackTrace();
	            object.put("result", "error");
	            object.put("error", e.toString());
	        }
	        return object.toString();
	    }

}