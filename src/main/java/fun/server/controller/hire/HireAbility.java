package fun.server.controller.hire;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.controller.interfaceD.AI_interface;
import fun.server.model.*;
import fun.server.service.TAbilityLevelService;
import fun.server.service.TAbilityService;
import fun.server.service.TAbilityTreeService;
import fun.server.service.THireService;
import fun.server.service.TInterfaceAiService;
import fun.server.service.TInterfaceService;
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
@RequestMapping("/s/HireAbility")
public class HireAbility {

    private final THireService tHireService;
    private final TEnterpriseService tEnterpriseService;
    private final THireAbilityService tHireAbilityService;
    private final TAbilityLevelService tAbilityLevelService;
    private final TAbilityService tAbilityService;
    private final TAbilityTreeService tAbilityTreeService;
    private final TAbilityTypeService tAbilityTypeService;
    private final TInterfaceAiService tInterfaceAiService;
    
    public HireAbility(THireService tHireService,TEnterpriseService tEnterpriseService,THireAbilityService tHireAbilityService,TAbilityService tAbilityService,TAbilityLevelService tAbilityLevelService, TAbilityTreeService tAbilityTreeService,TAbilityTypeService tAbilityTypeService, TInterfaceAiService tInterfaceAiService) {
        this.tHireService = tHireService;
        this.tEnterpriseService = tEnterpriseService;
        this.tHireAbilityService= tHireAbilityService;
        this.tAbilityLevelService=tAbilityLevelService;
        this.tAbilityService=tAbilityService;
        this.tAbilityTreeService = tAbilityTreeService;
        this.tAbilityTypeService = tAbilityTypeService;
        this.tInterfaceAiService = tInterfaceAiService;
    }


    /**
     * 点击获取招聘能力信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryhireability", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryhireability(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String id = jsonParam.getString("id");

        Integer dataall = jsonParam.getInteger("dataall");
        try {
        	id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 获取数据库记录
            JSONArray abilitylevel_Array = new JSONArray();
            //获取招聘能力表信息
            THireAbilityExample tHireAbilityExample = new THireAbilityExample();
            THireAbilityExample.Criteria criteria = tHireAbilityExample.createCriteria();
            criteria.andFhireidEqualTo(key);
            PageInfo<THireAbility> abilitylevelPageInfo = tHireAbilityService.findByExampleMapper(tHireAbilityExample, (page - 1) * results, results);
            List<THireAbility> abilitylevel_list = abilitylevelPageInfo.getList();
            for (THireAbility thireAbility : abilitylevel_list) {
            	 JSONObject abilitylevel_object = new JSONObject();
//            	//获取能力ID-名称
//            	TAbility aa= tAbilityService.findById(thireAbility.getFabilityid());
//            	if(aa!= null) {
//            		//abilitylevel_object.put("FHire_TypeID", ParamTools.getEnParam(aa.getFkeyid().toString()));
//            		abilitylevel_object.put("FAbility", aa.getFname());
//            	}
//            	//获取能力等级ID-名称
//            	TAbilityLevel bb=tAbilityLevelService.findById(thireAbility.getFabilitylevelid());
//            	if(bb!= null) {
//            		//abilitylevel_object.put("FHire_Type_LevelID", ParamTools.getEnParam(bb.getFkeyid().toString()));
//            		abilitylevel_object.put("FAbility_Level", bb.getFname());
//            	}
            	 
            	//获取能力ID-名称-tree
             	TAbilityTree aa= tAbilityTreeService.findById(thireAbility.getFabilityid());
             	if(aa!= null) {
             		//拼接能力名称
             		TAbilityType aa_type= tAbilityTypeService.findById(aa.getFtypeid());
             		abilitylevel_object.put("FAbility", aa_type.getFname()+"——"+aa.getFname());
             	}
             	//获取能力等级ID--tree
             	TAbilityTree bb=tAbilityTreeService.findById(thireAbility.getFabilitylevelid());
             	if(bb!= null) {
             		abilitylevel_object.put("FAbility_Level", bb.getFname());
             	}
            	 abilitylevel_object.put("FWeight", thireAbility.getFweight());
            	 abilitylevel_object.put("key", ParamTools.getEnParam(thireAbility.getFkeyid().toString()));
            	 abilitylevel_object.put("FHireid", ParamTools.getEnParam(thireAbility.getFhireid().toString()));
            	 
            	 
            	 abilitylevel_Array.add(abilitylevel_object);
            	 
            }
            // 返回值
            object.put("list", abilitylevel_Array);
            object.put("total", abilitylevelPageInfo.getTotal()); // 总行数
            object.put("page", abilitylevelPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
    /**
     * 根据ID获取招聘能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryhireAbilityInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryhireAbilityInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String hireid = jsonParam.getString("hireid");
        String HireAbilityID = jsonParam.getString("HireAbilityID");
        
        try {
        	HireAbilityID = HireAbilityID == null ? "0" : ParamTools.getdeParam(HireAbilityID);
            long key = Long.parseLong(HireAbilityID);
            
            hireid = hireid == null ? "0" : ParamTools.getdeParam(hireid);
            long hireid2 = Long.parseLong(hireid);
            
            JSONObject ability_object = new JSONObject();
            ability_object.put("key", ParamTools.getEnParam(HireAbilityID));
            ability_object.put("hireid", ParamTools.getEnParam(hireid));
            //获取招聘能力表信息
            THireAbilityExample tHireAbilityExample = new THireAbilityExample();
            THireAbilityExample.Criteria criteria = tHireAbilityExample.createCriteria();
            criteria.andFkeyidEqualTo(key);
            criteria.andFhireidEqualTo(hireid2);
            
            List<THireAbility> abilityList = tHireAbilityService.findByExample(tHireAbilityExample);
            for (THireAbility thireAbility : abilityList) {
//            	//获取能力ID-名称
//            	TAbility aa= tAbilityService.findById(thireAbility.getFabilityid());
//            	if(aa!= null) {
//            		ability_object.put("FHire_TypeID", ParamTools.getEnParam(aa.getFkeyid().toString()));
//            		ability_object.put("FHire_TypeName", aa.getFname());
//            	}
            	//获取能力ID-名称--t_ability_tree
            	TAbilityTree aa= tAbilityTreeService.findById(thireAbility.getFabilityid());
            	if(aa!= null) {
            		ability_object.put("FHire_TypeID", ParamTools.getEnParam(aa.getFkeyid().toString()));
            		ability_object.put("FHire_TypeName", aa.getFname());
            	}
            	
            	
//            	//获取能力等级ID-名称
//            	TAbilityLevel bb=tAbilityLevelService.findById(thireAbility.getFabilitylevelid());
//            	if(bb!= null) {
//            		ability_object.put("FHire_Type_LevelID", ParamTools.getEnParam(bb.getFkeyid().toString()));
//            		ability_object.put("FHire_Type_LevelName", bb.getFname());
//            	}
            	
            	//获取能力等级ID-名称--t_ability_tree
            	TAbilityTree bb=tAbilityTreeService.findById(thireAbility.getFabilitylevelid());
            	if(bb!= null) {
            		ability_object.put("FHire_Type_LevelID", ParamTools.getEnParam(bb.getFkeyid().toString()));
            		ability_object.put("FHire_Type_LevelName", bb.getFname());
            	}
            	
            	
            	ability_object.put("FWeight", thireAbility.getFweight());
                ability_object.put("FCID", thireAbility.getFcid());
                ability_object.put("FUID", thireAbility.getFuid());
                ability_object.put("FCDATE", thireAbility.getFcdate());
                ability_object.put("FUDATE", thireAbility.getFudate());
                ability_object.put("FState", thireAbility.getFstate());
           }
           
         
            // 返回值
            object.put("info", ability_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
    /**
     * 添加招聘能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加招聘能力信息")
    @ResponseBody
    @RequestMapping(value = "/addhireAbility", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addhireAbility(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
       // String FName = jsonParam.getString("FName");
       // String FNum = jsonParam.getString("FNum");
       // String FWages = jsonParam.getString("FWages");
        //String FBenefit = jsonParam.getString("FBenefit");
        //String FWorkDate = jsonParam.getString("FWorkDate");
        //String FAddr = jsonParam.getString("FAddr");
        //String FCon = jsonParam.getString("FCon");
        //String FAsk = jsonParam.getString("FAsk");
        
        //String FWeight = jsonParam.getString("FWeight");
        String FHire_Type = jsonParam.getString("FHire_Type");
        String FHire_Type_Level = jsonParam.getString("FHire_Type_Level");
        
        String FKeyID = jsonParam.getString("FKeyID"); 
        String FHireid = jsonParam.getString("FHireid");
  
        //String FEnterprise = jsonParam.getString("FEnterprise");
     
        try {
        	   
            FHire_Type = FHire_Type == null ? "0" : ParamTools.getdeParam(FHire_Type);
            FHire_Type_Level = FHire_Type_Level == null ? "0" : ParamTools.getdeParam(FHire_Type_Level);
            //FEnterprise = FEnterprise == null ? "0" : ParamTools.getdeParam(FEnterprise);
            FKeyID = FKeyID == null ? "0" : ParamTools.getdeParam(FKeyID);
            FHireid = FHireid == null ? "0" : ParamTools.getdeParam(FHireid);
         
        	THireAbilityExample abilityExample = new THireAbilityExample();
        	THireAbilityExample.Criteria criteria = abilityExample.createCriteria();
            criteria.andFhireidEqualTo(Long.parseLong(FHireid));
            criteria.andFabilityidEqualTo(Long.parseLong(FHire_Type));
            
            
            List<THireAbility> abilityList = tHireAbilityService.findByExample(abilityExample);
            if (abilityList.size() == 0) {
                String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilityID);
                }
                
                ///添加招聘能力表T_Hire_Ability
                SnowflakeIdWorker idWorker2 = new SnowflakeIdWorker(1, 1);
                long key2 = idWorker2.nextId();
                // 新建招聘能力信息
                THireAbility tHireAbility = new THireAbility();
                tHireAbility.setFkeyid(key2);
                tHireAbility.setFhireid(Long.parseLong(FHireid));
                tHireAbility.setFabilityid(Long.parseLong(FHire_Type));
                tHireAbility.setFabilitylevelid(Long.parseLong(FHire_Type_Level));
                //tHireAbility.setFweight(Integer.parseInt(FWeight) );
                tHireAbility.setFcid(Long.parseLong(uid));
                tHireAbility.setFcdate(new Date());
                tHireAbilityService.save(tHireAbility);
                


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
    

    /**
     * 修改招聘能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改招聘能力信息")
    @ResponseBody
    @RequestMapping(value = "/updatehireAbility", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatehireAbility(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FHireid = jsonParam.getString("FHireid");
        String FHire_Type =jsonParam.getString("FHire_Type");
        String FHire_Type_Level = jsonParam.getString("FHire_Type_Level");
        //String FWeight = jsonParam.getString("FWeight");
       

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            
            FHireid = FHireid == null ? "0" : ParamTools.getdeParam(FHireid);
            long key2 = Long.parseLong(FHireid);
        
            FHire_Type = FHire_Type == null ? "0" : ParamTools.getdeParam(FHire_Type);
            FHire_Type_Level = FHire_Type_Level == null ? "0" : ParamTools.getdeParam(FHire_Type_Level);
           
        	THireAbilityExample abilityExample = new THireAbilityExample();
        	THireAbilityExample.Criteria criteria = abilityExample.createCriteria();
            criteria.andFkeyidEqualTo(key);
            criteria.andFhireidEqualTo(key2);
            List<THireAbility> abilityList = tHireAbilityService.findByExample(abilityExample);
            if (abilityList.size() == 1) {
                String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilityID);
                }
              
                
                //修改招聘能力表
                THireAbility tHireAbility = new THireAbility();
                tHireAbility.setFkeyid(key);
                tHireAbility.setFhireid(key2);
                tHireAbility.setFabilityid(Long.parseLong(FHire_Type));
                tHireAbility.setFabilitylevelid(Long.parseLong(FHire_Type_Level));
               // tHireAbility.setFweight(Integer.parseInt(FWeight));
                tHireAbility.setFuid(Long.parseLong(uid));
                tHireAbility.setFudate(new Date());
                tHireAbilityService.update(tHireAbility);
                
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
    /**
     * 删除招聘能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除招聘能力信息")
    @ResponseBody
    @RequestMapping(value = "/delhireability", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delhireability(HttpServletRequest request)
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


            tHireAbilityService.deleteById(Long.parseLong(id));
            // 返回值
            object.put("result", "success");

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
    

//    /**
//     * 招聘-AI匹配能力
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/hire_ai_ability", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String hire_ai_ability(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//	        JSONObject object = new JSONObject();
//	        // 获取请求参数
//	        JSONObject jsonParam = ParamTools.getParameters(request);
//	        String id = jsonParam.getString("id");
//	
//	        try {
//	            // 获取当前登录用户ID
//	            String cookiesLoginAbilityID = ParamTools.getCookiesLoginUserID(request);
//	            String uid = "";
//	            if (cookiesLoginAbilityID != null && !cookiesLoginAbilityID.isEmpty()) {
//	                uid = ParamTools.getdeParam(cookiesLoginAbilityID);
//	            }
//	
//	            // 处理ID参数
//	            id = (id == null) ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
//	
//	            // 数据库连接和查询招聘表数据
//	            Connection con = MysqlDbTools.getConnection();
//	            Statement st = con.createStatement();
//	            ResultSet rs = st.executeQuery("SELECT fask FROM t_hire WHERE fkeyid='" + id + "'");
//	
//	            JSONArray one_Array = new JSONArray();
//	            while (rs.next()) {
//	                JSONObject one_object = new JSONObject();
//	                one_object.put("fask", rs.getString("fask"));
//	                one_Array.add(one_object);
//	            }
//	            rs.close();
//	            st.close();
//	            con.close();
//	
//	            // 数据库连接和查询能力表数据
//	            Connection con2 = MysqlDbTools.getConnection();
//	            Statement st2 = con2.createStatement();
//	            String sql = "SELECT c.FName as FAbilityTypeName, a.fname as FAbilityName, " +
//	                         "b.fname as FAbilityLevelName, a.fkeyid as FAbilityID, " +
//	                         "b.fkeyid as FAbilityLevelID " +
//	                         "FROM t_ability_tree a, t_ability_tree b, t_ability_type c " +
//	                         "WHERE a.FPID = -1 " +
//	                         "AND b.FPID = a.FKeyID " +
//	                         "AND a.FTypeID = c.FKeyID " +
//	                         "ORDER BY c.FName ASC";
//	            ResultSet rs2 = st2.executeQuery(sql);
//	
//	            JSONArray two_Array = new JSONArray();
//	            while (rs2.next()) {
//	                JSONObject two_object = new JSONObject();
//	                two_object.put("FAbilityTypeName", rs2.getString("FAbilityTypeName"));
//	                two_object.put("FAbilityName", rs2.getString("FAbilityName"));
//	                two_object.put("FAbilityLevelName", rs2.getString("FAbilityLevelName"));
//	                two_object.put("FAbilityID", rs2.getString("FAbilityID"));
//	                two_object.put("FAbilityLevelID", rs2.getString("FAbilityLevelID"));
//	                two_Array.add(two_object);
//	            }
//	            rs2.close();
//	            st2.close();
//	            con2.close();
//	
//	            // AI接口调用参数设置
//	            //String url = "http://192.168.18.34:8100/api/interface_llm_start";
//	            String require = "请按照数据1中招聘要求的数据内容，匹配数据2中的相关招聘能力,选择出最符合要求的3条数据。";
//	            String return_ai = "{\"FAbilityName\":\"FAbilityName\",\"FAbilityLevelName\":\"FAbilityLevelName\",\"FAbilityID\":\"FAbilityID\",\"FAbilityLevelID\":\"FAbilityLevelID\"}";
//	            String question = "数据1：" + one_Array + "数据2：" + two_Array + "根据以上数据，" + require + "返回格式如下：" + return_ai;
//	            String temperature = "0.01";
//	            String answer_parameters = "[{\"name\": \"ai_result\", \"description\": \"" + require + "\"}]";
//	
//	            // 调用AI接口
//	            String result = AI_interface.sendPost_json(question, temperature, answer_parameters);
//	            //System.out.println("result:"+result);
//	            
//	            JSONObject jsonObject1 = JSONObject.parseObject(result);
//	            String code = jsonObject1.getString("code");
//	            String message = jsonObject1.getString("message");
//	            String ai_id = jsonObject1.getString("key_id");
//	
//	            if (code.equals("200")) {
//	                // 删除接口表数据
//	                TInterfaceAiExample conditionExample3 = new TInterfaceAiExample();
//	                conditionExample3.createCriteria().andFtypeEqualTo(1);
//	                conditionExample3.createCriteria().andFtypeidEqualTo(Long.parseLong(id));
//	                tInterfaceAiService.deleteByByExample(conditionExample3);
//	
//	                // 保存AI查询ID等信息
//	                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
//	                long key = idWorker.nextId();
//	                TInterfaceAi tinterfaceai = new TInterfaceAi();
//	                tinterfaceai.setFkeyid(key);
//	                tinterfaceai.setFstate(1);
//	                tinterfaceai.setFcdate(new Date());
//	                tinterfaceai.setFcid(Long.parseLong(uid));
//	                tinterfaceai.setFtype(1);
//	                tinterfaceai.setFtypeid(Long.parseLong(id));
//	                tinterfaceai.setFinterfacellmid(ai_id);
//	                tinterfaceai.setFinterfacellmstate(1);
//	                tInterfaceAiService.save(tinterfaceai);
//	
//	                // 根据AIid查询返回数据信息
//	                int maxRetries = 10;
//	                int retryCount = 0;
//	                do {
//	                    try {
//	                        Thread.sleep(50000);
//	                        String result_res = AI_interface.sendPost_json_search(ai_id);
//	                        //System.out.println("result_res:"+result_res);
//	                        JSONObject jsonObject1_res = JSONObject.parseObject(result_res);
//	                        String code_res = jsonObject1_res.getString("code");
//	
//	                        if (code_res.equals("200")) {
//	                            String info = jsonObject1_res.getString("info");
//	                            JSONObject jsonObject1_info = JSONObject.parseObject(info);
//	                            String answer = jsonObject1_info.getString("answer");
//	                            JSONObject jsonObject1_answer = JSONObject.parseObject(answer);
//	                            String ai_result = jsonObject1_answer.getString("ai_result");
//	
//	                            if (ai_result != null && !ai_result.isEmpty()) {
//	                                // 删除能力信息
//	                                THireAbilityExample thireAbilityExample = new THireAbilityExample();
//	                                thireAbilityExample.createCriteria().andFhireidEqualTo(Long.parseLong(id));
//	                                tHireAbilityService.deleteByByExample(thireAbilityExample);
//	
//	                                // 解析JSON数组并添加到能力表
//	                                JSONArray jsonArray = JSONArray.parseArray(ai_result);
//	                                for (int i = 0; i < jsonArray.size(); i++) {
//	                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//	                                    String FAbilityID = jsonObject.getString("FAbilityID");
//	                                    String FAbilityLevelID = jsonObject.getString("FAbilityLevelID");
//	                                   
//	                                    // 新建招聘能力信息
//	                                    SnowflakeIdWorker idWorker2 = new SnowflakeIdWorker(1, 1);
//	                                    long key2 = idWorker2.nextId();
//	                                    THireAbility tHireAbility = new THireAbility();
//	                                    tHireAbility.setFkeyid(key2);
//	                                    tHireAbility.setFhireid(Long.parseLong(id));
//	                                    tHireAbility.setFabilityid(Long.parseLong(FAbilityID));
//	                                    tHireAbility.setFabilitylevelid(Long.parseLong(FAbilityLevelID));
//	                                    tHireAbility.setFweight(Integer.parseInt("10"));
//	                                    tHireAbility.setFcid(Long.parseLong(uid));
//	                                    tHireAbility.setFcdate(new Date());
//	                                   
//	                                    tHireAbilityService.save(tHireAbility);
//	                                    Thread.sleep(5000);//生成后等待ID更新
//	                                    
//	                                }
//	
//	                                // 返回值
//	                                object.put("result", "success");
//	                                object.put("ai_result", ai_result);
//	                                break;
//	                            }
//	                        }
//	                    } catch (Exception e) {
//	                        object.put("ai_error", e.toString());
//	                        object.put("error", e.toString());
//	                        break;
//	                    }
//	                    retryCount++;
//	                } while (retryCount <= maxRetries);
//	
//	                if (retryCount > maxRetries) {
//	                    object.put("result", "error");
//	                    object.put("error", "多次尝试后仍未获取到有效结果");
//	                }
//	            } else {
//	                // 返回值
//	                object.put("result", "ai_error");
//	                object.put("ai_error", message);
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            object.put("result", "error");
//	            object.put("error", e.toString());
//	        }
//	        return object.toString();
//    }    
    
    /**
     * 招聘-AI匹配能力
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/hire_ai_ability", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String hire_ai_ability(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");

        try {
            // 获取当前登录用户ID
            String cookiesLoginAbilityID = ParamTools.getCookiesLoginUserID(request);
            String uid = "";
            if (cookiesLoginAbilityID != null && !cookiesLoginAbilityID.isEmpty()) {
                uid = ParamTools.getdeParam(cookiesLoginAbilityID);
            }

            // 处理ID参数
            id = (id == null) ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            // 数据库连接和查询招聘表数据
            Connection con = MysqlDbTools.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT fask FROM t_hire WHERE fkeyid='" + id + "'");

            JSONArray one_Array = new JSONArray();
            while (rs.next()) {
                JSONObject one_object = new JSONObject();
                one_object.put("fask", rs.getString("fask"));
                one_Array.add(one_object);
            }
            rs.close();
            st.close();
            con.close();

            // 数据库连接和查询能力表数据
            Connection con2 = MysqlDbTools.getConnection();
            Statement st2 = con2.createStatement();
            String sql = "SELECT c.FName as FAbilityTypeName, a.fname as FAbilityName, " +
                         "b.fname as FAbilityLevelName, a.fkeyid as FAbilityID, " +
                         "b.fkeyid as FAbilityLevelID " +
                         "FROM t_ability_tree a, t_ability_tree b, t_ability_type c " +
                         "WHERE a.FPID = -1 " +
                         "AND b.FPID = a.FKeyID " +
                         "AND a.FTypeID = c.FKeyID " +
                         "ORDER BY c.FName ASC";
            ResultSet rs2 = st2.executeQuery(sql);

            JSONArray two_Array = new JSONArray();
            while (rs2.next()) {
                JSONObject two_object = new JSONObject();
                two_object.put("FAbilityTypeName", rs2.getString("FAbilityTypeName"));
                two_object.put("FAbilityName", rs2.getString("FAbilityName"));
                two_object.put("FAbilityLevelName", rs2.getString("FAbilityLevelName"));
                two_object.put("FAbilityID", rs2.getString("FAbilityID"));
                two_object.put("FAbilityLevelID", rs2.getString("FAbilityLevelID"));
                two_Array.add(two_object);
            }
            rs2.close();
            st2.close();
            con2.close();

            // AI接口调用参数设置
            //String url = "http://192.168.18.34:8100/api/interface_llm_start";
            String require = "请按照数据1中招聘要求的数据内容，匹配数据2中的相关招聘能力,选择出最符合要求的3条数据。";
            String return_ai = "{\"FAbilityName\":\"FAbilityName\",\"FAbilityLevelName\":\"FAbilityLevelName\",\"FAbilityID\":\"FAbilityID\",\"FAbilityLevelID\":\"FAbilityLevelID\"}";
            String question = "数据1：" + one_Array + "数据2：" + two_Array + "根据以上数据，" + require + "返回格式如下：" + return_ai;
            String temperature = "0.01";
            String answer_parameters = "[{\"name\": \"ai_result\", \"description\": \"" + require + "\"}]";

            // 调用AI接口
            String result = AI_interface.sendPost_json(question, temperature, answer_parameters);
            //System.out.println("result:"+result);
            
            JSONObject jsonObject1 = JSONObject.parseObject(result);
            String code = jsonObject1.getString("code");
            String message = jsonObject1.getString("message");
            String ai_id = jsonObject1.getString("key_id");

            if (code.equals("200")) {
                // 删除接口表数据
                TInterfaceAiExample conditionExample3 = new TInterfaceAiExample();
                conditionExample3.createCriteria().andFtypeEqualTo(1);
                conditionExample3.createCriteria().andFtypeidEqualTo(Long.parseLong(id));
                tInterfaceAiService.deleteByByExample(conditionExample3);

                // 保存AI查询ID等信息
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
                long key = idWorker.nextId();
                TInterfaceAi tinterfaceai = new TInterfaceAi();
                tinterfaceai.setFkeyid(key);
                tinterfaceai.setFstate(1);
                tinterfaceai.setFcdate(new Date());
                tinterfaceai.setFcid(Long.parseLong(uid));
                tinterfaceai.setFtype(1);
                tinterfaceai.setFtypeid(Long.parseLong(id));
                tinterfaceai.setFinterfacellmid(ai_id);
                tinterfaceai.setFinterfacellmstate(1);
                tInterfaceAiService.save(tinterfaceai);

                // 根据AIid查询返回数据信息，每5秒请求一次，直到获取有效数据
                int maxRetries = 50;  // 最多尝试50次
                int retryCount = 0;
                boolean gotValidData = false;
                
                do {
                    try {
                        // 等待5秒后再查询
                        Thread.sleep(5000);
                        
                        // 请求AI接口获取结果
                        String result_res = AI_interface.sendPost_json_search(ai_id);
                        //System.out.println("result_res:"+result_res);
                        
                        // 检查返回结果是否有效
                        if (result_res == null || result_res.trim().isEmpty()) {
                            retryCount++;
                            continue;
                        }
                        
                        JSONObject jsonObject1_res = JSONObject.parseObject(result_res);
                        String code_res = jsonObject1_res.getString("code");

                        if (code_res.equals("200")) {
                            String info = jsonObject1_res.getString("info");
                            // 检查info是否有效
                            if (info == null || info.trim().isEmpty()) {
                                retryCount++;
                                continue;
                            }
                            
                            JSONObject jsonObject1_info = JSONObject.parseObject(info);
                            String answer = jsonObject1_info.getString("answer");
                            // 检查answer是否有效
                            if (answer == null || answer.trim().isEmpty()) {
                                retryCount++;
                                continue;
                            }
                            
                            JSONObject jsonObject1_answer = JSONObject.parseObject(answer);
                            String ai_result = jsonObject1_answer.getString("ai_result");

                            // 检查ai_result是否有效
                            if (ai_result != null && !ai_result.trim().isEmpty() && 
                                !"null".equalsIgnoreCase(ai_result) && 
                                !"[]".equals(ai_result.trim())) {
                                
                                // 删除能力信息
                                THireAbilityExample thireAbilityExample = new THireAbilityExample();
                                thireAbilityExample.createCriteria().andFhireidEqualTo(Long.parseLong(id));
                                tHireAbilityService.deleteByByExample(thireAbilityExample);

                                // 解析JSON数组并添加到能力表
                                JSONArray jsonArray = JSONArray.parseArray(ai_result);
                                for (int i = 0; i < jsonArray.size(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String FAbilityID = jsonObject.getString("FAbilityID");
                                    String FAbilityLevelID = jsonObject.getString("FAbilityLevelID");
                                   
                                    // 新建招聘能力信息
                                    SnowflakeIdWorker idWorker2 = new SnowflakeIdWorker(1, 1);
                                    long key2 = idWorker2.nextId();
                                    THireAbility tHireAbility = new THireAbility();
                                    tHireAbility.setFkeyid(key2);
                                    tHireAbility.setFhireid(Long.parseLong(id));
                                    tHireAbility.setFabilityid(Long.parseLong(FAbilityID));
                                    tHireAbility.setFabilitylevelid(Long.parseLong(FAbilityLevelID));
                                    tHireAbility.setFweight(Integer.parseInt("10"));
                                    tHireAbility.setFcid(Long.parseLong(uid));
                                    tHireAbility.setFcdate(new Date());
                                   
                                    tHireAbilityService.save(tHireAbility);
                                    Thread.sleep(5000);//生成后等待ID更新
                                }

                                // 返回值
                                object.put("result", "success");
                                object.put("ai_result", ai_result);
                                gotValidData = true;
                                break;
                            }
                        }
                    } catch (Exception e) {
                        object.put("ai_error", e.toString());
                        object.put("error", e.toString());
                        break;
                    }
                    retryCount++;
                } while (retryCount <= maxRetries);

                if (!gotValidData) {
                    object.put("result", "error");
                    object.put("error", "多次尝试后仍未获取到有效结果");
                }
            } else {
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
    
    	

}