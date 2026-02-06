package fun.server.controller.hire;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.controller.interfaceD.AI_interface;
import fun.server.model.*;
import fun.server.model.customQuery.major.TCLassData;
import fun.server.model.customQuery.major.TClassCS;
import fun.server.service.TAbilityLevelService;
import fun.server.service.TAbilityService;
import fun.server.service.THireService;
import fun.server.service.THireStudentService;
import fun.server.service.TInterfaceAiService;
import fun.server.service.TMajorService;
import fun.server.service.TStudentService;
import fun.server.service.TAbilityTypeService;
import fun.server.service.TEnterpriseService;
import fun.server.service.THireAbilityService;
import fun.tools.MysqlDbTools;
import fun.tools.ParamTools;
import fun.tools.SnowflakeIdWorker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

/**
 * 能力管理 相关业务处理
 */
@RestController
@RequestMapping("/s/hireStudent_AI")
public class HireStudent_AI {

    private final THireService tHireService;
    private final THireStudentService tHireStudentService;
    private final TEnterpriseService tEnterpriseService;
    private final TStudentService tStudentService;
    private final TMajorService tMajorService;
    private final TInterfaceAiService tInterfaceAiService;
    
    
    public HireStudent_AI(THireService tHireService,THireStudentService tHireStudentService,TEnterpriseService tEnterpriseService,TStudentService tStudentService, TMajorService tMajorService,TInterfaceAiService tInterfaceAiService  ) {
        this.tHireService = tHireService;
        this.tEnterpriseService = tEnterpriseService;
        this.tHireStudentService=tHireStudentService;
        this.tStudentService=tStudentService;
        this.tMajorService = tMajorService;
        this.tInterfaceAiService=tInterfaceAiService;
        
    }
    
    /**
     * 获取招聘企业信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryhire_list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryhire_list(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
       // String name = jsonParam.getString("name");
       // String enterpriseID = jsonParam.getString("enterpriseID");//企业
        String stu_id = jsonParam.getString("stu_id");//应聘学生ID
        Integer dataall = jsonParam.getInteger("dataall");
        try {
        	stu_id = stu_id == null ? "0" : ParamTools.getdeParam(stu_id);
            // 获取数据库记录
            JSONArray ability_Array = new JSONArray();
            // 查询条件-招聘学生表
            THireStudentExample THireStudentExample = new THireStudentExample();
            THireStudentExample.Criteria criteria_stu = THireStudentExample.createCriteria();
            //查询学生是否应聘该企业
            criteria_stu.andFstudentidEqualTo(Long.parseLong(stu_id));
            List<THireStudent> aaa = tHireStudentService.findByExample(THireStudentExample);
            String aa_id="";
            for (THireStudent hir_stu : aaa) {
            	aa_id=hir_stu.getFhireid().toString();
		    }
            // 查询条件
            THireExample THireExample = new THireExample();
            THireExample.Criteria criteria = THireExample.createCriteria();
            if(aa_id.equals(null)||aa_id.equals("")) {
                //查询状态为1-发布的招聘信息
                criteria.andFstateEqualTo(1);
            }else {
                //查询状态为1-发布的招聘信息
                criteria.andFstateEqualTo(1);
                criteria.andFkeyidNotEqualTo(Long.parseLong(aa_id));
            }
           
            
//            if (name != null && !name.equals("")) {
//                criteria.andFnameLike("%" + name + "%");
//
//            }
//            
//            if (enterpriseID != null && !enterpriseID.equals("") && !enterpriseID.equals("-1")) {
//            	enterpriseID = enterpriseID == null ? "0" : ParamTools.getdeParam(enterpriseID);
//                criteria.andFenterpriseidEqualTo(Long.parseLong(enterpriseID));
//                
//            }
            // 排序
            String orderSql = "";
//            for (Object order : order_JA) {
//                JSONObject order_Object = (JSONObject) order;
//                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
//                String colName = column_Object.getString("data");
//                orderSql += "," + colName + " " + order_Object.getString("dir");
//            }
            if (orderSql.length() > 0) {
            	THireExample.setOrderByClause(orderSql.substring(1));
            } else {
            	THireExample.setOrderByClause("FDate desc , FName asc");
            }
            
            
            PageInfo<THire> abilityPageInfo = tHireService.findByExampleMapper(THireExample, (page - 1) * results, results);
            List<THire> ability_list = abilityPageInfo.getList();

            for (THire ability : ability_list) {
                JSONObject ability_object = new JSONObject();
                ability_object.put("key", ParamTools.getEnParam(ability.getFkeyid().toString()));
                if (dataall == 1) {
                  
                	TEnterprise TEnterprise = tEnterpriseService.findById(ability.getFenterpriseid());
                    ability_object.put("FName", ability.getFname() == null ? "" : ability.getFname());
                    ability_object.put("Fenterprise", TEnterprise== null ? "" : TEnterprise.getFname());
                    ability_object.put("FNum", ability.getFnum() == null ? "" : ability.getFnum());
                    ability_object.put("FWages", ability.getFwages() == null ? "" : ability.getFwages());
                    ability_object.put("FBenefit", ability.getFbenefit() == null ? "" : ability.getFbenefit());
                    ability_object.put("FWorkDate", ability.getFworkdate() == null ? "" : ability.getFworkdate());
                    ability_object.put("FAddr", ability.getFaddr() == null ? "" : ability.getFaddr());
                    ability_object.put("FCon", ability.getFcon() == null ? "" : ability.getFcon());
                   // ability_object.put("FAsk", ability.getFask() == null ? "" : ability.getFask());
                    
                    ability_object.put("FCID", ability.getFcid());
                    ability_object.put("FUID", ability.getFuid());
                    ability_object.put("FCDATE", ability.getFcdate());
                    ability_object.put("FUDATE", ability.getFudate());
                } else {
                    ability_object.put("FName", "*****");
                    ability_object.put("Fenterprise", "*****");
                    ability_object.put("FCID", "*****");
                    ability_object.put("FUID", "*****");
                    ability_object.put("FCDATE", "*****");
                    ability_object.put("FUDATE", "*****");
                }

                ability_object.put("FState", ability.getFstate());
                ability_Array.add(ability_object);
            }
            // 返回值
            object.put("list", ability_Array);
            object.put("total", abilityPageInfo.getTotal()); // 总行数
            object.put("page", abilityPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
    

    /**
     * 招聘-AI匹配企业列表
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/hire_ai_list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String hire_ai_list(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");//学生ID
      
        try {
        	   id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
        	
        	  // 查询信息
        	   TInterfaceAiExample interfaceAiExample = new TInterfaceAiExample();
               TInterfaceAiExample.Criteria criteria = interfaceAiExample.createCriteria();
               criteria.andFtypeEqualTo(3); //查询状态
               criteria.andFtypeidEqualTo(Long.parseLong(id));
               
        	   List<TInterfaceAi> interfaceAi_list = tInterfaceAiService.findByExample(interfaceAiExample);
        	   if( interfaceAi_list.size()==0) {
        			   
        			    String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
        	            String uid = ""; // 当前登录用户ID
        	            if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
        	                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
        	            }
        	         
        	            
        				Connection con =null;
        				con = MysqlDbTools.getConnection(); 
        				Statement st = null;
        				ResultSet rs = null; 
        				//PreparedStatement pst = null; 
        				Connection con2 =null;
        				con2 = MysqlDbTools.getConnection(); 
        				Statement st2 = null;
        				ResultSet rs2 = null; 
        				//PreparedStatement pst2 = null; 
        				//AI接口调用
        				//String url="http://192.168.18.34:8100/api/interface_llm_start";
        			    String question="";
        			    String temperature="";
        			    String answer_parameters="";
        			    String result="";
        			    JSONArray one_Array = new JSONArray();
        			    JSONArray two_Array = new JSONArray();
        				//选择招聘表-数据 
        				st = con.createStatement();
        				rs = st.executeQuery(" SELECT a.FKeyID ,a.FEnterpriseID,a.FName,a.fask ,b.FName as FEnterpriseName FROM  t_hire a ,t_enterprise b where a.FEnterpriseID=b.fkeyid ");	
        				
        				while (rs.next()) {
        					//筛选数据
        					JSONObject one_object = new JSONObject();
        					one_object.put("fask", rs.getString("fask"));
        					one_object.put("FName", rs.getString("FName"));
        					one_object.put("FEnterpriseID", rs.getString("FEnterpriseID"));
        					one_object.put("FKeyID", rs.getString("FKeyID"));
        					one_object.put("FEnterpriseName", rs.getString("FEnterpriseName"));
        					
        					
        					one_Array.add(one_object);
        				}
        			
        				rs.close();st.close();con.close();
        				//选择学生表-数据 
        				String sql="";
        				sql+=" SELECT a.fkeyid as FStudentid ,a.fname as FStudentname,a.fsex ,a.FBirthday,a.ftel,a.FEmail,  ";
        				sql+=" a.FClassName,c.fname as FAbilityname,d.fname as FAbilitylevelname                            ";
        				sql+=" from t_student a,t_student_ability b,t_ability_tree c ,t_ability_tree d                      ";
        				sql+=" where a.FKeyID=b.FStudentID                                                                  ";
        				sql+=" and b.FAbilityID=c.FKeyID                                                                    ";
        				sql+=" and b.FAbilityLevelID=d.FKeyID                                                               ";
        				sql+=" and a.FWorkState=0                                                            ";
        				sql+=" and a.fkeyid='"+id+"'";
        				
        				st2 = con2.createStatement();
        				rs2 = st2.executeQuery(sql);			
        				while (rs2.next()) {
        					//筛选数据
        					JSONObject two_object = new JSONObject();
        					//two_object.put("FStudentid", rs2.getString("FStudentid"));
        					two_object.put("FStudentname", rs2.getString("FStudentname"));
        					two_object.put("FAbilityname", rs2.getString("FAbilityname"));
        					two_object.put("FAbilitylevelname", rs2.getString("FAbilitylevelname"));
        					
        					two_Array.add(two_object);
        					
        				}
        				rs2.close();st2.close();con2.close();
        				
        				///////////AI接口调用/////////////////
        			     String require="请按照数据2中学生具有的相关能力和能力等级,匹配出符合数据1中企业招聘要求的学生，并筛选出最符合学生的招聘企业（数据要求大于等于1条,只显示匹配成功的企业）。";
        			     String return_ai="{\"FKeyID\":\"FKeyID\",\"FEnterpriseID\":\"FEnterpriseID\",\"FEnterpriseName\":\"FEnterpriseName\",\"FName\":\"FName\"}";
        				 question="数据1："+one_Array+"数据2："+two_Array+"根据以上数据，"+require+"返回格式如下："+ return_ai;
        		         temperature="0.01";//调用成熟度
        		         answer_parameters="[{\"name\": \"ai_result\", \"description\": \""+require+"\"}]";
        			    
        		         try {
        		        	 //调用AI接口
            			     result=AI_interface.sendPost_json(question, temperature, answer_parameters);
							
						} catch (Exception e) {
							// TODO: handle exception
							    e.printStackTrace();
					            object.put("result", "error");
					            object.put("error", e.toString());
						}
        		        
        			    
        			     
        		         JSONObject jsonObject1 = JSONObject.parseObject(result);
        		         String code = jsonObject1.getString("code");
        		         String message = jsonObject1.getString("message");
        		         String ai_id = jsonObject1.getString("key_id");//大模型返回的AI查询id
        		                 if(code.equals("200")) {
        		                	//添加数据-ai存储表
        		 	                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
        		 	                long key = idWorker.nextId();
        		 	                // 新建
        		 	                TInterfaceAi tinterfaceai = new TInterfaceAi();
        		 	                tinterfaceai.setFkeyid(key);
        		 	                tinterfaceai.setFstate(1);
        		 	                tinterfaceai.setFcdate(new Date());
        		 	                tinterfaceai.setFcid(Long.parseLong(uid));
        		 	                tinterfaceai.setFtype(3);//招聘企业匹配
        		 	                tinterfaceai.setFtypeid(Long.parseLong(id));//学生ID
        		 	                tinterfaceai.setFinterfacellmid(ai_id);//大模型返回ID
        		 	                tinterfaceai.setFinterfacellmstate(1);
        		 	               
        		 	                tInterfaceAiService.save(tinterfaceai);
        		                	 
        		                	// 返回值
        		                	object.put("result", "success");
        		 	             	object.put("ai_id", ai_id);
        		                	 
        		                 }else {
        		                	 // 返回值
        		                	 object.put("result", "ai_error");
        			 	             object.put("ai_error", message);
        		                 }
        				///////////////////////////
        			   
        			   
               
        	   }else {
	        		   for (TInterfaceAi interfaceAi : interfaceAi_list) {
	        			// 返回值
	                   	object.put("result", "success");
	   	             	object.put("ai_id", interfaceAi.getFinterfacellmid());
	        		   }
        		   }
   				
        	
            
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
    
    
    

    /**
     * 招聘-AI匹配企业列表-重新匹配
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/hire_ai_list_too", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String hire_ai_list_too(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");//学生ID
      
        try {
        	   id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
        	   // 删除接口表数据
               TInterfaceAiExample conditionExample3 = new TInterfaceAiExample();
               conditionExample3.createCriteria().andFtypeEqualTo(3);//招聘企业类型
               conditionExample3.createCriteria().andFtypeidEqualTo(Long.parseLong(id));
               tInterfaceAiService.deleteByByExample(conditionExample3);
        	
        	  // 查询信息
//        	   TInterfaceAiExample interfaceAiExample = new TInterfaceAiExample();
//               TInterfaceAiExample.Criteria criteria = interfaceAiExample.createCriteria();
//               criteria.andFtypeEqualTo(3); //查询状态
//               criteria.andFtypeidEqualTo(Long.parseLong(id));
//               
//        	   List<TInterfaceAi> interfaceAi_list = tInterfaceAiService.findByExample(interfaceAiExample);
//        	   if( interfaceAi_list.size()==0) {
        			   
        			    String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
        	            String uid = ""; // 当前登录用户ID
        	            if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
        	                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
        	            }
        	         
        	            
        				Connection con =null;
        				con = MysqlDbTools.getConnection(); 
        				Statement st = null;
        				ResultSet rs = null; 
        				//PreparedStatement pst = null; 
        				Connection con2 =null;
        				con2 = MysqlDbTools.getConnection(); 
        				Statement st2 = null;
        				ResultSet rs2 = null; 
        				//PreparedStatement pst2 = null; 
        				//AI接口调用
        				//String url="http://192.168.18.34:8100/api/interface_llm_start";
        			    String question="";
        			    String temperature="";
        			    String answer_parameters="";
        			    String result="";
        			    JSONArray one_Array = new JSONArray();
        			    JSONArray two_Array = new JSONArray();
        				//选择招聘表-数据 
        				st = con.createStatement();
        				rs = st.executeQuery(" SELECT a.FKeyID ,a.FEnterpriseID,a.FName,a.fask ,b.FName as FEnterpriseName FROM  t_hire a ,t_enterprise b where a.FEnterpriseID=b.fkeyid ");	
        				
        				while (rs.next()) {
        					//筛选数据
        					JSONObject one_object = new JSONObject();
        					one_object.put("fask", rs.getString("fask"));
        					one_object.put("FName", rs.getString("FName"));
        					one_object.put("FEnterpriseID", rs.getString("FEnterpriseID"));
        					one_object.put("FKeyID", rs.getString("FKeyID"));
        					one_object.put("FEnterpriseName", rs.getString("FEnterpriseName"));
        					
        					
        					one_Array.add(one_object);
        				}
        			
        				rs.close();st.close();con.close();
        				//选择学生表-数据 
        				String sql="";
        				sql+=" SELECT a.fkeyid as FStudentid ,a.fname as FStudentname,a.fsex ,a.FBirthday,a.ftel,a.FEmail,  ";
        				sql+=" a.FClassName,c.fname as FAbilityname,d.fname as FAbilitylevelname                            ";
        				sql+=" from t_student a,t_student_ability b,t_ability_tree c ,t_ability_tree d                      ";
        				sql+=" where a.FKeyID=b.FStudentID                                                                  ";
        				sql+=" and b.FAbilityID=c.FKeyID                                                                    ";
        				sql+=" and b.FAbilityLevelID=d.FKeyID                                                               ";
        				sql+=" and a.FWorkState=0                                                            ";
        				sql+=" and a.fkeyid='"+id+"'";
        				
        				st2 = con2.createStatement();
        				rs2 = st2.executeQuery(sql);			
        				while (rs2.next()) {
        					//筛选数据
        					JSONObject two_object = new JSONObject();
        					//two_object.put("FStudentid", rs2.getString("FStudentid"));
        					two_object.put("FStudentname", rs2.getString("FStudentname"));
        					two_object.put("FAbilityname", rs2.getString("FAbilityname"));
        					two_object.put("FAbilitylevelname", rs2.getString("FAbilitylevelname"));
        					
        					two_Array.add(two_object);
        					
        				}
        				rs2.close();st2.close();con2.close();
        				
        				///////////AI接口调用/////////////////
        			     String require="请按照数据2中学生具有的相关能力和能力等级,匹配出符合数据1中企业招聘要求的学生，并筛选出最符合学生的招聘企业（数据要求大于等于1条,只显示匹配成功的企业）。";
        			     String return_ai="{\"FKeyID\":\"FKeyID\",\"FEnterpriseID\":\"FEnterpriseID\",\"FEnterpriseName\":\"FEnterpriseName\",\"FName\":\"FName\"}";
        				 question="数据1："+one_Array+"数据2："+two_Array+"根据以上数据，"+require+"返回格式如下："+ return_ai;
        		         temperature="0.01";//调用成熟度
        		         answer_parameters="[{\"name\": \"ai_result\", \"description\": \""+require+"\"}]";
        			     //调用AI接口
        			     result=AI_interface.sendPost_json(question, temperature, answer_parameters);
        			             
        		         JSONObject jsonObject1 = JSONObject.parseObject(result);
        		         String code = jsonObject1.getString("code");
        		         String message = jsonObject1.getString("message");
        		         String ai_id = jsonObject1.getString("key_id");//大模型返回的AI查询id
        		                 if(code.equals("200")) {
        		                	//添加数据-ai存储表
        		 	                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
        		 	                long key = idWorker.nextId();
        		 	                // 新建
        		 	                TInterfaceAi tinterfaceai = new TInterfaceAi();
        		 	                tinterfaceai.setFkeyid(key);
        		 	                tinterfaceai.setFstate(1);
        		 	                tinterfaceai.setFcdate(new Date());
        		 	                tinterfaceai.setFcid(Long.parseLong(uid));
        		 	                tinterfaceai.setFtype(3);//招聘企业匹配
        		 	                tinterfaceai.setFtypeid(Long.parseLong(id));//学生ID
        		 	                tinterfaceai.setFinterfacellmid(ai_id);//大模型返回ID
        		 	                tinterfaceai.setFinterfacellmstate(1);
        		 	               
        		 	                tInterfaceAiService.save(tinterfaceai);
        		                	 
        		                	// 返回值
        		                	object.put("result", "success");
        		 	             	object.put("ai_id", ai_id);
        		                	 
        		                 }else {
        		                	 // 返回值
        		                	 object.put("result", "ai_error");
        			 	             object.put("ai_error", message);
        		                 }
        				///////////////////////////
        			   
        			   
//               
//        	   }else {
//	        		   for (TInterfaceAi interfaceAi : interfaceAi_list) {
//	        			// 返回值
//	                   	object.put("result", "success");
//	   	             	object.put("ai_id", interfaceAi.getFinterfacellmid());
//	        		   }
//        		   }
   				
        	
            
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    
    /**
     * 查询AI数据接口信息-招聘企业调用接口查看
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
	@LogOperation("查询AI数据接口信息-调用查看")
	@ResponseBody
	@RequestMapping(value = "/hire_interface_search_hire", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String hire_interface_search_hire(HttpServletRequest request) throws IOException {
	    JSONObject object = new JSONObject();
	    // 获取请求参数
	    JSONObject jsonParam = ParamTools.getParameters(request);
	    String id = jsonParam.getString("id");
	    String stu_id = jsonParam.getString("stu_id");
	    //String url = "http://192.168.18.34:8100/api/interface_llm_end";
	    String result = "";
	    int maxRetries = 5; // 最大重试次数
	    int retryCount = 0;
	    try {
	    	stu_id = stu_id == null ? "0" : ParamTools.getdeParam(stu_id);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        long stu_id_key = Long.parseLong(stu_id);
	    // 查询信息
 	    TInterfaceAiExample interfaceAiExample = new TInterfaceAiExample();
        TInterfaceAiExample.Criteria criteria = interfaceAiExample.createCriteria();
        criteria.andFtypeEqualTo(3);
        criteria.andFtypeidEqualTo(stu_id_key);
        criteria.andFinterfacellmidEqualTo(id);
 	    List<TInterfaceAi> interfaceAi_list = tInterfaceAiService.findByExample(interfaceAiExample);
 	    for (TInterfaceAi interfaceAi : interfaceAi_list) {
 	    	if(interfaceAi.getFinterfacellmReturn()==null||interfaceAi.getFinterfacellmReturn().equals("")) {

 	   	    do {
 	   	        try {
 	   		        	
 	   		             //System.out.println("类型id:"+hire_id_key);
 	   		             //System.out.println("大模型ID："+id);
 	   		             
 	   		            // 调用AI接口
 	   		            result = AI_interface.sendPost_json_search(id);
 	   		            JSONObject jsonObject1 = JSONObject.parseObject(result);
 	   		            String code = jsonObject1.getString("code");
 	   		            //System.out.println(jsonObject1);
 	   		            if (code.equals("200")) {
 	   		                String info = jsonObject1.getString("info");
 	   		                JSONObject jsonObject1_info = JSONObject.parseObject(info);
 	   		                String answer = jsonObject1_info.getString("answer");
 	   		                JSONObject jsonObject1_answer = JSONObject.parseObject(answer);
 	   		                String ai_result = jsonObject1_answer.getString("ai_result");
 	   	
 	   	                if (ai_result != null && !ai_result.isEmpty()) {
 	   	                	
 	   	                	 // 更新信息
 	   	                	 String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
 	   	                     String uid = ""; // 当前登录用户ID
 	   	                     if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
 	   	                         uid = ParamTools.getdeParam(CookiesLoginabilityID);
 	   	                     }
 	   	                    
 	   	              	    
 	   	              	    
 	   	              	        TInterfaceAi tinterface2 = new TInterfaceAi();
 	   	              	        tinterface2.setFkeyid(interfaceAi.getFkeyid());
 	   		                    tinterface2.setFinterfacellmid(id);
 	   		                    tinterface2.setFtypeid(stu_id_key);
 	   		                    tinterface2.setFuid(Long.parseLong(uid));
 	   		                    tinterface2.setFinterfacellmstate(3); // 修改AI接口模型状态3
 	   		                    tinterface2.setFinterfacellmReturn(ai_result); // 修改AI接口返回值
 	   		                    tinterface2.setFudate(new Date());
 	   		                    tInterfaceAiService.update(tinterface2);
 	   	                     
 	   	              	     
 	   		                  
 	   	                	
 	   	                	
 	   	                    // 返回值
 	   	                    object.put("result", "success");
 	   	                    object.put("ai_result", ai_result);
 	   	                    break; // 成功获取到有效结果，退出重试循环
 	   	                }
 	   	            }
 	   	        } catch (Exception e) {
 	   	            e.printStackTrace();
 	   	            object.put("result", "error");
 	   	            object.put("error", e.toString());
 	   	            break; // 发生异常，退出重试循环
 	   	        }
 	   	        retryCount++;
 	   	    } while (retryCount <= maxRetries);
 	   	
 	   		    if (retryCount > maxRetries) {
 	   		        object.put("result", "error");
 	   		        object.put("error", "多次尝试后仍未获取到有效结果");
 	   		    }
 	    	}else {
 	    		  // 返回值
                    object.put("result", "success");
                    object.put("ai_result",interfaceAi.getFinterfacellmReturn() );
 	    		
 	    	}
 	    
	
 	    }
	
	    return object.toString();
	}
    
	
	/**
     * 学生应聘
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("学生应聘")
    @ResponseBody
    @RequestMapping(value = "/stu_matchHire", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stu_matchHire(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        
        String stu_id = jsonParam.getString("stu_id");
        String hire_id = jsonParam.getString("hire_id");
       // String stu_point = jsonParam.getString("stu_point");
  
    
     
        try {
        	stu_id = stu_id == null ? "0" : ParamTools.getdeParam(stu_id);
            hire_id = hire_id == null ? "0" : ParamTools.getdeParam(hire_id);
         
            // 查询条件
            THireStudentExample THireStudentExample = new THireStudentExample();
            THireStudentExample.Criteria criteria = THireStudentExample.createCriteria();
            criteria.andFstudentidEqualTo(Long.parseLong(stu_id));
            List<THireStudent> abilityList = tHireStudentService.findByExample(THireStudentExample);
            if (abilityList.size() == 0) {
                String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilityID);
                }
               
                SnowflakeIdWorker idWorker2 = new SnowflakeIdWorker(1, 1);
                long key2 = idWorker2.nextId();
                //查询学生综合得分
                TStudent TStudent = tStudentService.findById(Long.parseLong(stu_id));
                
                // 新建招聘能力信息
                THireStudent tHirestu = new THireStudent();
                tHirestu.setFkeyid(key2);
                tHirestu.setFhireid(Long.parseLong(hire_id));
                tHirestu.setFstudentid(Long.parseLong(stu_id));
                tHirestu.setFscore(TStudent.getFpoints());
                tHirestu.setFmode(2);//申请-应聘
                tHirestu.setFstate(0);//状态-0-未选择
                tHirestu.setFcid(Long.parseLong(uid));
                tHirestu.setFcdate(new Date());
                tHireStudentService.save(tHirestu);
                
                //修改招聘表状态-2完成
//                THire ability = new THire();
//                ability.setFkeyid(Long.parseLong(hire_id));
//                ability.setFuid(Long.parseLong(uid));
//                ability.setFudate(new Date());
//                ability.setFstate(Integer.valueOf(2));
//                tHireService.update(ability);
                
                //修改学生表招聘状态
//                TStudent student = new TStudent();
//                student.setFkeyid(Long.parseLong(stu_id));
//                student.setFuid(Long.parseLong(uid));
//                student.setFudate(new Date());
//                student.setFworkstate(Integer.valueOf(1));//修改学生工作状态
//                tStudentService.update(student);
                // 返回值
                object.put("result", "success");
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
  

   
}