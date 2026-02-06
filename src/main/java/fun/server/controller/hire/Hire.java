package fun.server.controller.hire;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.service.TAbilityLevelService;
import fun.server.service.TAbilityService;
import fun.server.service.THireService;
import fun.server.service.TAbilityTypeService;
import fun.server.service.TEnterpriseService;
import fun.server.service.THireAbilityService;
import fun.tools.ParamTools;
import fun.tools.SnowflakeIdWorker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * 能力管理 相关业务处理
 */
@RestController
@RequestMapping("/s/hire")
public class Hire {

    private final THireService tHireService;
    private final TEnterpriseService tEnterpriseService;
    private final THireAbilityService tHireAbilityService;
    private final TAbilityLevelService tAbilityLevelService;
    private final TAbilityService tAbilityService;
    
    
    
    public Hire(THireService tHireService,TEnterpriseService tEnterpriseService,THireAbilityService tHireAbilityService,TAbilityService tAbilityService,TAbilityLevelService tAbilityLevelService) {
        this.tHireService = tHireService;
        this.tEnterpriseService = tEnterpriseService;
        this.tHireAbilityService= tHireAbilityService;
        this.tAbilityLevelService=tAbilityLevelService;
        this.tAbilityService=tAbilityService;
        
    }

    /**
     * 获取能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryhire", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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
        String enterpriseID = jsonParam.getString("enterpriseID");//企业
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray ability_Array = new JSONArray();
            // 查询条件
            THireExample THireExample = new THireExample();
            THireExample.Criteria criteria = THireExample.createCriteria();
            
            
            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            
            if (enterpriseID != null && !enterpriseID.equals("") && !enterpriseID.equals("-1")) {
            	enterpriseID = enterpriseID == null ? "0" : ParamTools.getdeParam(enterpriseID);
                criteria.andFenterpriseidEqualTo(Long.parseLong(enterpriseID));
                
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
            	THireExample.setOrderByClause(orderSql.substring(1));
            } else {
            	THireExample.setOrderByClause("FDate desc , FName asc");
            }
            PageInfo<THire> abilityPageInfo = tHireService.findByExampleMapper(THireExample, (page - 1) * results, results);
            List<THire> ability_list = abilityPageInfo.getList();

            for (THire ability : ability_list) {
                JSONObject ability_object = new JSONObject();
                ability_object.put("key", ParamTools.getEnParam(ability.getFkeyid().toString()));
                // ability_object.put("FTypeID", ParamTools.getEnParam(ability.getFtypeid().toString()));
                if (dataall == 1) {
                    //TAbilityType tAbilityType = tAbilityTypeService.findById(ability.getFtypeid());
                   // ability_object.put("FTypeName", tAbilityType == null ? "" : tAbilityType.getFname());
                	
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
     * 变更招聘状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statehire", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stateability(HttpServletRequest request)
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
            THire ability = new THire();
            ability.setFkeyid(Long.parseLong(id));
            ability.setFuid(Long.parseLong(uid));
            ability.setFudate(new Date());
            ability.setFstate(Integer.valueOf(state));
            tHireService.update(ability);
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
     * 删除招聘信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除招聘信息")
    @ResponseBody
    @RequestMapping(value = "/delhire", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delability(HttpServletRequest request)
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


            tHireService.deleteById(Long.parseLong(id));
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
     * 添加招聘信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加招聘信息")
    @ResponseBody
    @RequestMapping(value = "/addhire", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addhire(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FName = jsonParam.getString("FName");
        String FNum = jsonParam.getString("FNum");
        String FWages = jsonParam.getString("FWages");
        String FBenefit = jsonParam.getString("FBenefit");
        String FWorkDate = jsonParam.getString("FWorkDate");
        String FAddr = jsonParam.getString("FAddr");
        String FCon = jsonParam.getString("FCon");
        //String FAsk = jsonParam.getString("FAsk");
        
        //String FHire_Type = jsonParam.getString("FHire_Type");
        //String FHire_Type_Level = jsonParam.getString("FHire_Type_Level");
        String FEnterprise = jsonParam.getString("FEnterprise");
     
        try {
        	   
            //FHire_Type = FHire_Type == null ? "0" : ParamTools.getdeParam(FHire_Type);
            //FHire_Type_Level = FHire_Type_Level == null ? "0" : ParamTools.getdeParam(FHire_Type_Level);
            FEnterprise = FEnterprise == null ? "0" : ParamTools.getdeParam(FEnterprise);
          
         
        	//THireExample abilityExample = new THireExample();
        	//THireExample.Criteria criteria = abilityExample.createCriteria();
            //criteria.andFnameEqualTo(FName);
       
           // List<THire> abilityList = tAbilityService.findByExample(abilityExample);
           // if (abilityList.size() == 0) {
                String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilityID);
                }
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
                long key = idWorker.nextId();
                // 新建能力信息
                THire ability = new THire();
                ability.setFkeyid(key);
                ability.setFname(FName);
                ability.setFnum(Integer.parseInt(FNum));
                ability.setFenterpriseid(Long.parseLong(FEnterprise));
                ability.setFwages(FWages);
                ability.setFbenefit(FBenefit);
                ability.setFworkdate(FWorkDate);
                ability.setFaddr(FAddr);
                ability.setFcon(FCon);
               // ability.setFask(FAsk);
                ability.setFstate(1);
                ability.setFcdate(new Date());
                tHireService.save(ability);
                
                ///添加招聘能力表T_Hire_Ability
                //SnowflakeIdWorker idWorker2 = new SnowflakeIdWorker(1, 1);
                //long key2 = idWorker2.nextId();
                // 新建招聘能力信息
                //THireAbility tHireAbility = new THireAbility();
                //tHireAbility.setFkeyid(key2);
                //tHireAbility.setFhireid(key);
                //tHireAbility.setFabilityid(Long.parseLong(FHire_Type));
                //tHireAbility.setFabilitylevelid(Long.parseLong(FHire_Type_Level));
                //tHireAbility.setFweight(1);
                //tHireAbility.setFcid(Long.parseLong(uid));
                //tHireAbility.setFcdate(new Date());
                //tHireAbilityService.save(tHireAbility);
                


                // 返回值
                object.put("result", "success");
           // } else {
                // 返回值
           //     object.put("result", "fail");
           // }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
    
    /**
     * 根据ID获取招聘信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryhireInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryhireInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询招聘信息
            THire ability = tHireService.findById(key);
            JSONObject ability_object = new JSONObject();
            ability_object.put("key", ParamTools.getEnParam(ability.getFkeyid().toString()));
            //获取企业ID-名称
            ability_object.put("FEnterpriseID", ParamTools.getEnParam(ability.getFenterpriseid().toString()));
            TEnterprise tEnterprise = tEnterpriseService.findById(ability.getFenterpriseid());
            ability_object.put("FEnterpriseName", tEnterprise == null ? "" : tEnterprise.getFname());
            
            //获取招聘能力表信息
            //THireAbilityExample tHireAbilityExample = new THireAbilityExample();
            //THireAbilityExample.Criteria criteria = tHireAbilityExample.createCriteria();
            //criteria.andFhireidEqualTo(ability.getFkeyid());
            //List<THireAbility> abilityList = tHireAbilityService.findByExample(tHireAbilityExample);
            //for (THireAbility thireAbility : abilityList) {
            	//获取能力ID-名称
            //	TAbility aa= tAbilityService.findById(thireAbility.getFabilityid());
            //	if(aa!= null) {
            //		ability_object.put("FHire_TypeID", ParamTools.getEnParam(aa.getFkeyid().toString()));
            //		ability_object.put("FHire_TypeName", aa.getFname());
            //	}
            	//获取能力等级ID-名称
            //	TAbilityLevel bb=tAbilityLevelService.findById(thireAbility.getFabilitylevelid());
            //	if(bb!= null) {
            //		ability_object.put("FHire_Type_LevelID", ParamTools.getEnParam(bb.getFkeyid().toString()));
            //		ability_object.put("FHire_Type_LevelName", bb.getFname());
            //	}
           // }
           
         
            ability_object.put("FName", ability.getFname() == null ? "" : ability.getFname());
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
            ability_object.put("FState", ability.getFstate());
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
     * 修改招聘信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改招聘信息")
    @ResponseBody
    @RequestMapping(value = "/updatehire", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatehire(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        
        String FName = jsonParam.getString("FName");
        String FNum = jsonParam.getString("FNum");
        String FWages = jsonParam.getString("FWages");
        String FBenefit = jsonParam.getString("FBenefit");
        String FWorkDate = jsonParam.getString("FWorkDate");
        String FAddr = jsonParam.getString("FAddr");
        String FCon = jsonParam.getString("FCon");
        //String FAsk = jsonParam.getString("FAsk");
        
        //String FHire_Type =jsonParam.getString("FHire_Type");
        //String FHire_Type_Level = jsonParam.getString("FHire_Type_Level");
        String FEnterprise =jsonParam.getString("FEnterprise");
       

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
        
            //FHire_Type = FHire_Type == null ? "0" : ParamTools.getdeParam(FHire_Type);
            //FHire_Type_Level = FHire_Type_Level == null ? "0" : ParamTools.getdeParam(FHire_Type_Level);
            FEnterprise = FEnterprise == null ? "0" : ParamTools.getdeParam(FEnterprise);
            
            THireExample tHireExample = new THireExample();
            THireExample.Criteria criteria = tHireExample.createCriteria();
            criteria.andFnameEqualTo(FName);
            criteria.andFkeyidNotEqualTo(key);
            
            List<THire> abilityList = tHireService.findByExample(tHireExample);
            if (abilityList.size() == 0) {
                String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilityID);
                }
                // 更新招聘信息
                THire thire = new THire();
                thire.setFkeyid(key);
                thire.setFname(FName);
                thire.setFnum(Integer.parseInt(FNum));
                thire.setFenterpriseid(Long.parseLong(FEnterprise));
                thire.setFwages(FWages);
                thire.setFbenefit(FBenefit);
                thire.setFworkdate(FWorkDate);
                thire.setFaddr(FAddr);
                thire.setFcon(FCon);
               // thire.setFask(FAsk);
                thire.setFstate(1);
                thire.setFuid(Long.parseLong(uid));
                thire.setFudate(new Date());
                tHireService.update(thire);
                
                //修改招聘能力表
                //THireAbilityExample tHireAbilityExample2 = new THireAbilityExample();
                //THireAbilityExample.Criteria criteria2 = tHireAbilityExample2.createCriteria();
                //criteria2.andFhireidEqualTo(key);
                //long key2 = 0;
                //List<THireAbility> abilityList2 = tHireAbilityService.findByExample(tHireAbilityExample2);
                //for (THireAbility thireAbility2 : abilityList2) {
                //	key2=thireAbility2.getFkeyid();
                //}
                
                //THireAbility tHireAbility = new THireAbility();
                //tHireAbility.setFkeyid(key2);
                //tHireAbility.setFhireid(key);
                //tHireAbility.setFabilityid(Long.parseLong(FHire_Type));
                //tHireAbility.setFabilitylevelid(Long.parseLong(FHire_Type_Level));
                //tHireAbility.setFweight(1);
                //tHireAbility.setFuid(Long.parseLong(uid));
                //tHireAbility.setFudate(new Date());
                //tHireAbilityService.update(tHireAbility);
                
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
            	//获取能力ID-名称
            	TAbility aa= tAbilityService.findById(thireAbility.getFabilityid());
            	if(aa!= null) {
            		//abilitylevel_object.put("FHire_TypeID", ParamTools.getEnParam(aa.getFkeyid().toString()));
            		abilitylevel_object.put("FAbility", aa.getFname());
            	}
            	//获取能力等级ID-名称
            	TAbilityLevel bb=tAbilityLevelService.findById(thireAbility.getFabilitylevelid());
            	if(bb!= null) {
            		//abilitylevel_object.put("FHire_Type_LevelID", ParamTools.getEnParam(bb.getFkeyid().toString()));
            		abilitylevel_object.put("FAbility_Level", bb.getFname());
            	}
            	 abilitylevel_object.put("FWeight", thireAbility.getFweight());
            	 abilitylevel_object.put("key", thireAbility.getFkeyid());
            	 abilitylevel_object.put("FHireid", thireAbility.getFhireid());
            	 
            	 
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

}