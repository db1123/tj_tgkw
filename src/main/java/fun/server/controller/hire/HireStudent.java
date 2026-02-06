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
@RequestMapping("/s/hireStudent")
public class HireStudent {

    private final THireService tHireService;
    private final THireStudentService tHireStudentService;
    private final TEnterpriseService tEnterpriseService;
    private final TStudentService tStudentService;
    private final TMajorService tMajorService;
    private final TInterfaceAiService tInterfaceAiService;
    
    
    public HireStudent(THireService tHireService,THireStudentService tHireStudentService,TEnterpriseService tEnterpriseService,TStudentService tStudentService, TMajorService tMajorService,TInterfaceAiService tInterfaceAiService  ) {
        this.tHireService = tHireService;
        this.tEnterpriseService = tEnterpriseService;
        this.tHireStudentService=tHireStudentService;
        this.tStudentService=tStudentService;
        this.tMajorService = tMajorService;
        this.tInterfaceAiService=tInterfaceAiService;
        
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
    @RequestMapping(value = "/queryhireStudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryhireStudent(HttpServletRequest request)
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
            //查询状态为1-发布的招聘信息
            criteria.andFstateEqualTo(1);

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
                    //ability_object.put("FAsk", ability.getFask() == null ? "" : ability.getFask());
                    
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
     * 招聘-匹配学生
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/matchStudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String matchStudent(HttpServletRequest request)
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
            

			String sql = "";
			Connection con =null;
			con = MysqlDbTools.getConnection(); 
			Statement st = null;
			ResultSet rs = null; 
			PreparedStatement pst = null; 
			     
			sql+=" SELECT DISTINCT (c.FKeyID) fstudentid  FROM  t_hire_ability a ,t_student_ability b,t_student c  ";
			sql+=" where a.FAbilityID=b.FAbilityID                                                              ";
			sql+=" and a.FAbilityLevelID=b.FAbilityLevelID                                                      ";
			sql+=" and a.FHireID='"+id+"'                                                                            ";
			sql+=" and b.FStudentID=c.FKeyID                                                                    ";
			sql+=" and b.FState=1                                                                               ";
			sql+=" and a.FState=1                                                                               ";
			
			st = con.createStatement();
			rs = st.executeQuery(sql);			
			while (rs.next()) {
				//查询是否存在已匹配学生数据
				Statement st2 = null;
				ResultSet rs2 = null; 
				Connection con2 =null;
				con2 = MysqlDbTools.getConnection(); 
				st2 = con2.createStatement();
				rs2 = st2.executeQuery("select * from t_hire_student where FHireID='"+id+"' ");		
				if (rs2.next()==true) {
					 // 返回值
		            object.put("result", "success");
				}else {
					sql = "INSERT INTO t_hire_student (FKeyID,FCID,FUID,FCDATE,FUDATE,FHireID,FStudentID,FMode,FState,FScore  )";
		        	sql += " values(?,?,?,?,?,?,?,?,?,?)";
		        	pst = con.prepareStatement(sql);
		        	SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
		            long key = idWorker.nextId();
					pst.setLong(1,  key);
					pst.setString(2,  uid);
					pst.setString(3,  uid);
					pst.setTimestamp(4,  KeyID.getMySqlDate());
					pst.setTimestamp(5,  KeyID.getMySqlDate());
					pst.setString(6, id);
					pst.setString(7, rs.getString("fstudentid"));
					pst.setString(8, "1");
					pst.setString(9, "0");
					pst.setString(10, "98");//综合评分
					
					pst.execute();
					pst.close();
		            
		        
		            // 返回值
		            object.put("result", "success");
				}
				rs2.close();st2.close();con2.close();
			}
			rs.close();st.close();con.close();
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
    
    
    /**
     * 获取招聘学生列表信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryhireStudent_list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryhireStudent_list(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String fkeyid = jsonParam.getString("fkeyid");
     
        try {
            // 获取数据库记录
            JSONArray ability_Array = new JSONArray();
            // 查询条件
            THireStudentExample THireStudentExample = new THireStudentExample();
            THireStudentExample.Criteria criteria = THireStudentExample.createCriteria();
            //查询招聘匹配的学生信息
            fkeyid = fkeyid == null ? "0" : (fkeyid.equals("0") ? "0" : ParamTools.getdeParam(fkeyid));
            criteria.andFhireidEqualTo(Long.parseLong(fkeyid));
            criteria.andFstateEqualTo(1);//状态-1选择
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            THireStudentExample.setOrderByClause("FCDATE desc , FScore asc");
            PageInfo<THireStudent> abilityPageInfo = tHireStudentService.findByExampleMapper(THireStudentExample, (page - 1) * results, results);
            List<THireStudent> ability_list = abilityPageInfo.getList();

            for (THireStudent ability : ability_list) {
                JSONObject ability_object = new JSONObject();
                
                	TStudent tStudent = tStudentService.findById(ability.getFstudentid());
                    ability_object.put("FStudent", tStudent.getFname() == null ? "" : tStudent.getFname());
                    ability_object.put("FStudent_tel", tStudent.getFtel() == null ? "" : tStudent.getFtel());
                    
                    if(ability.getFmode().equals(1)) {
                    	 ability_object.put("FMode", "选择");
                    }else {
                    	 ability_object.put("FMode", "申请");
                    }
                    if(ability.getFstate().equals(1)) {
                    	 ability_object.put("FState","已选择");
                    }else {
                    	 ability_object.put("FState", "未选择");
                    }
                    ability_object.put("FScore", ability.getFscore() == null ? "" : ability.getFscore());
                    ability_object.put("FCID", ability.getFcid());
                    ability_object.put("FUID", ability.getFuid());
                    ability_object.put("FCDATE", ability.getFcdate());
                    ability_object.put("FUDATE", ability.getFudate());
                
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
     * 获取招聘学生列表信息-应聘列表
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryhireStudent_list_yp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryhireStudent_list_yp(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String fkeyid = jsonParam.getString("fkeyid");
     
        try {
            // 获取数据库记录
            JSONArray ability_Array = new JSONArray();
            // 查询条件
            THireStudentExample THireStudentExample = new THireStudentExample();
            THireStudentExample.Criteria criteria = THireStudentExample.createCriteria();
            //查询招聘匹配的学生信息
            fkeyid = fkeyid == null ? "0" : (fkeyid.equals("0") ? "0" : ParamTools.getdeParam(fkeyid));
            criteria.andFhireidEqualTo(Long.parseLong(fkeyid));
            criteria.andFstateEqualTo(0);//状态-1选择
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            THireStudentExample.setOrderByClause("FCDATE desc , FScore asc");
            PageInfo<THireStudent> abilityPageInfo = tHireStudentService.findByExampleMapper(THireStudentExample, (page - 1) * results, results);
            List<THireStudent> ability_list = abilityPageInfo.getList();

            for (THireStudent ability : ability_list) {
                JSONObject ability_object = new JSONObject();
                
                	TStudent tStudent = tStudentService.findById(ability.getFstudentid());
                    ability_object.put("FStudent", tStudent.getFname() == null ? "" : tStudent.getFname());
                    ability_object.put("FStudent_tel", tStudent.getFtel() == null ? "" : tStudent.getFtel());
                    
                    if(ability.getFmode().equals(1)) {
                    	 ability_object.put("FMode", "选择");
                    }else {
                    	 ability_object.put("FMode", "申请");
                    }
                    if(ability.getFstate().equals(1)) {
                    	 ability_object.put("FState","已选择");
                    }else {
                    	 ability_object.put("FState", "未选择");
                    }
                    ability_object.put("FScore", ability.getFscore() == null ? "" : ability.getFscore());
                    ability_object.put("FCID", ability.getFcid());
                    ability_object.put("FUID", ability.getFuid());
                    ability_object.put("FCDATE", ability.getFcdate());
                    ability_object.put("FUDATE", ability.getFudate());
                    ability_object.put("key", ParamTools.getEnParam(ability.getFkeyid().toString()));		
                    
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
     * 聘用学生
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("聘用学生")
    @ResponseBody
    @RequestMapping(value = "/stu_matchStudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stu_matchStudent(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        
        String stu_fkeyid = jsonParam.getString("stu_fkeyid");
        String hire_id = jsonParam.getString("hire_id");
        String stu_point = jsonParam.getString("stu_point");
  
    
     
        try {
            stu_fkeyid = stu_fkeyid == null ? "0" : ParamTools.getdeParam(stu_fkeyid);
            hire_id = hire_id == null ? "0" : ParamTools.getdeParam(hire_id);
         
            // 查询条件
            THireStudentExample THireStudentExample = new THireStudentExample();
            THireStudentExample.Criteria criteria = THireStudentExample.createCriteria();
            criteria.andFstudentidEqualTo(Long.parseLong(stu_fkeyid));
            List<THireStudent> abilityList = tHireStudentService.findByExample(THireStudentExample);
            if (abilityList.size() == 0) {
                String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilityID);
                }
               
                SnowflakeIdWorker idWorker2 = new SnowflakeIdWorker(1, 1);
                long key2 = idWorker2.nextId();
                // 新建招聘能力信息
                THireStudent tHirestu = new THireStudent();
                tHirestu.setFkeyid(key2);
                tHirestu.setFhireid(Long.parseLong(hire_id));
                tHirestu.setFstudentid(Long.parseLong(stu_fkeyid));
                tHirestu.setFscore(Integer.parseInt(stu_point));
                tHirestu.setFmode(1);
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
                TStudent student = new TStudent();
                student.setFkeyid(Long.parseLong(stu_fkeyid));
                student.setFuid(Long.parseLong(uid));
                student.setFudate(new Date());
                student.setFworkstate(Integer.valueOf(1));//修改学生工作状态
                tStudentService.update(student);
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
     * 聘用学生-应聘列表
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("聘用学生-应聘列表")
    @ResponseBody
    @RequestMapping(value = "/stu_matchStudent_yp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stu_matchStudent_yp(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String stu_fkeyid = jsonParam.getString("stu_fkeyid");
     
        try {
            stu_fkeyid = stu_fkeyid == null ? "0" : ParamTools.getdeParam(stu_fkeyid);

                String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilityID);
                }
                //学生招聘表id查询
                String hire_stu_id="";          	
                Connection con =null;
				con = MysqlDbTools.getConnection(); 
				Statement st = null;
				ResultSet rs = null; 
				st = con.createStatement();
				rs = st.executeQuery(" SELECT FStudentID  FROM  t_hire_student where FKeyID='"+stu_fkeyid+"'");	
				
				while (rs.next()) {
					hire_stu_id=rs.getString("FStudentID");
				}
           
                // 修改招聘学生表状态
                THireStudent tHirestu = new THireStudent();
                tHirestu.setFkeyid(Long.parseLong(stu_fkeyid));
                tHirestu.setFstate(1);
                tHirestu.setFuid(Long.parseLong(uid));
                tHirestu.setFudate(new Date());
                tHireStudentService.update(tHirestu);
                
                //修改招聘表状态-2完成
//                THire ability = new THire();
//                ability.setFkeyid(Long.parseLong(hire_id));
//                ability.setFuid(Long.parseLong(uid));
//                ability.setFudate(new Date());
//                ability.setFstate(Integer.valueOf(2));
//                tHireService.update(ability);
                
                //修改学生表招聘状态
                TStudent student = new TStudent();
                student.setFkeyid(Long.parseLong(hire_stu_id));
                student.setFuid(Long.parseLong(uid));
                student.setFudate(new Date());
                student.setFworkstate(Integer.valueOf(1));//修改学生工作状态
                tStudentService.update(student);
                
                rs.close();st.close();con.close();
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
     * 获取学生信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytstudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytstudent(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray tstudent_Array = new JSONArray();
            // 查询条件
            TStudentExample TStudentExample = new TStudentExample();
            TStudentExample.Criteria criteria = TStudentExample.createCriteria();
            criteria.andFworkstateEqualTo(0);
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                TStudentExample.setOrderByClause(orderSql.substring(1));
            } else {
                TStudentExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TStudent> tstudentPageInfo = tStudentService.findByExampleMapper(TStudentExample, (page - 1) * results, results);
            List<TStudent> tstudent_list = tstudentPageInfo.getList();

            for (TStudent tstudent : tstudent_list) {
                JSONObject tstudent_object = new JSONObject();
                tstudent_object.put("key", ParamTools.getEnParam(tstudent.getFkeyid().toString()));
                tstudent_object.put("FUserID", ParamTools.getEnParam(tstudent.getFuserid().toString()));
                if (dataall == 1) {
                    tstudent_object.put("FName", tstudent.getFname() == null ? "" : tstudent.getFname());
                    tstudent_object.put("FMode", tstudent.getFmode());
                    tstudent_object.put("FNo", tstudent.getFno() == null ? "" : tstudent.getFno());
                    tstudent_object.put("FSex", tstudent.getFsex());
                    tstudent_object.put("FBirthday", tstudent.getFbirthday() == null ? "" : tstudent.getFbirthday());
                    tstudent_object.put("FIDNumber", tstudent.getFidnumber() == null ? "" : tstudent.getFidnumber().substring(0, 3) + "****************");
                    tstudent_object.put("FTel", tstudent.getFtel() == null ? "" : tstudent.getFtel());
                    tstudent_object.put("FEmail", tstudent.getFemail() == null ? "" : tstudent.getFemail());
                    tstudent_object.put("FStartSchoolDate", tstudent.getFstartschooldate() == null ? "" : tstudent.getFstartschooldate());
                    tstudent_object.put("FEndSchoolDate", tstudent.getFendschooldate() == null ? "" : tstudent.getFendschooldate());
                    tstudent_object.put("FMajor", tstudent.getFmajor() == null ? "" : tstudent.getFmajor());
                    tstudent_object.put("FEducation", tstudent.getFeducation() == null ? "" : tstudent.getFeducation());
                    tstudent_object.put("FPolitical", tstudent.getFpolitical() == null ? "" : tstudent.getFpolitical());
                    tstudent_object.put("FAddr", tstudent.getFaddr() == null ? "" : tstudent.getFaddr());
                    tstudent_object.put("FHonor", tstudent.getFhonor() == null ? "" : tstudent.getFhonor());
                    tstudent_object.put("FPunish", tstudent.getFpunish() == null ? "" : tstudent.getFpunish());
                    tstudent_object.put("FHealth", tstudent.getFhealth() == null ? "" : tstudent.getFhealth());
                    tstudent_object.put("FNote", tstudent.getFnote() == null ? "" : tstudent.getFnote());
                    tstudent_object.put("FPoints", tstudent.getFpoints() == null ? "" : tstudent.getFpoints());
                    tstudent_object.put("FWorkState", tstudent.getFworkstate() == null ? "" : tstudent.getFworkstate());
                    tstudent_object.put("FGradeLevel", tstudent.getFgradelevel() == null ? 0 : tstudent.getFgradelevel());

                    TClassCS tClassCS = new TClassCS();
                    tClassCS.setFStudentID(tstudent.getFkeyid());
                    List<TCLassData> selectedClassList = tMajorService.selectClassInfo(tClassCS);
                    if(selectedClassList.size() > 0){
                        String classname ="";

                        for (TCLassData classData : selectedClassList) {
                            classname  += " 【" + classData.getFClassName() + "】，";
                        }
                        if(classname.length() > 0){
                            classname = classname.substring(0, classname.length() - 1);
                        }
                        tstudent_object.put("FClassName", classname);
                    } else {
                        tstudent_object.put("FClassName", "");
                    }
                    tstudent_object.put("FCID", tstudent.getFcid());
                    tstudent_object.put("FUID", tstudent.getFuid());
                    tstudent_object.put("FCDATE", tstudent.getFcdate());
                    tstudent_object.put("FUDATE", tstudent.getFudate());
                } else {
                    tstudent_object.put("FClassName", "*****");
                    tstudent_object.put("FClassID", ParamTools.getEnParam("-1"));
                    tstudent_object.put("FName", "*****");
                    tstudent_object.put("FMode", "*****");
                    tstudent_object.put("FNo", "*****");
                    tstudent_object.put("FSex", "*****");
                    tstudent_object.put("FBirthday", "*****");
                    tstudent_object.put("FIDNumber", "*****");
                    tstudent_object.put("FTel", "*****");
                    tstudent_object.put("FEmail", "*****");
                    tstudent_object.put("FStartSchoolDate", "*****");
                    tstudent_object.put("FEndSchoolDate", "*****");
                    tstudent_object.put("FMajor", "*****");
                    tstudent_object.put("FEducation", "*****");
                    tstudent_object.put("FPolitical", "*****");
                    tstudent_object.put("FAddr", "*****");
                    tstudent_object.put("FHonor", "*****");
                    tstudent_object.put("FPunish", "*****");
                    tstudent_object.put("FHealth", "*****");
                    tstudent_object.put("FNote", "*****");
                    tstudent_object.put("FPoints", "*****");
                    tstudent_object.put("FWorkState", "*****");
                    tstudent_object.put("FGradeLevel", "*****");
                    tstudent_object.put("FClassName", "*****");
                    tstudent_object.put("FCID", "*****");
                    tstudent_object.put("FUID", "*****");
                    tstudent_object.put("FCDATE", "*****");
                    tstudent_object.put("FUDATE", "*****");
                }

                tstudent_object.put("FState", tstudent.getFstate());
                tstudent_Array.add(tstudent_object);
            }
            // 返回值
            object.put("list", tstudent_Array);
            object.put("total", tstudentPageInfo.getTotal()); // 总行数
            object.put("page", tstudentPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
    

    /**
     * 招聘-AI匹配学生列表
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/hire_ai_ability_student_list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String hire_ai_ability_student_list(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");//招聘ID
      
        try {
        	   id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
        	
        	  // 查询信息
        	   TInterfaceAiExample interfaceAiExample = new TInterfaceAiExample();
               TInterfaceAiExample.Criteria criteria = interfaceAiExample.createCriteria();
               criteria.andFtypeEqualTo(2);
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
        				rs = st.executeQuery(" SELECT fask  FROM  t_hire where fkeyid='"+id+"'");	
        				
        				while (rs.next()) {
        					//筛选数据
        					JSONObject one_object = new JSONObject();
        					one_object.put("fask", rs.getString("fask"));
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
        				
        				st2 = con2.createStatement();
        				rs2 = st2.executeQuery(sql);			
        				while (rs2.next()) {
        					//筛选数据
        					JSONObject two_object = new JSONObject();
        					two_object.put("FStudentid", rs2.getString("FStudentid"));
        					two_object.put("FStudentname", rs2.getString("FStudentname"));
        					two_object.put("FAbilityname", rs2.getString("FAbilityname"));
        					two_object.put("FAbilitylevelname", rs2.getString("FAbilitylevelname"));
        					
        					two_Array.add(two_object);
        					
        				}
        				rs2.close();st2.close();con2.close();
        				
        				///////////AI接口调用/////////////////
        			     String require="请按照数据1中招聘要求的数据内容，匹配数据2中学生具有的相关能力和能力等级,并选择出最符合招聘要求的学生（数据要求大于1条,只显示匹配成功的能力）。";
        			     String return_ai="{\"FStudentid\":\"FStudentid\",\"FStudentname\":\"FStudentname\",\"FAbilityname\":\"FAbilityname\",\"FAbilitylevelname\":\"FAbilitylevelname\"}";
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
        		 	                tinterfaceai.setFtype(2);//招聘人员类型
        		 	                tinterfaceai.setFtypeid(Long.parseLong(id));//招聘ID
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
     * 招聘-AI匹配学生列表-重新匹配
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/hire_ai_ability_student_list_too", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String hire_ai_ability_student_list_too(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");//招聘ID
      
        try {
        	   id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
        	   
               // 删除接口表数据
               TInterfaceAiExample conditionExample3 = new TInterfaceAiExample();
               conditionExample3.createCriteria().andFtypeEqualTo(2);
               conditionExample3.createCriteria().andFtypeidEqualTo(Long.parseLong(id));
               tInterfaceAiService.deleteByByExample(conditionExample3);
        	
        	  // 查询信息
//        	   TInterfaceAiExample interfaceAiExample = new TInterfaceAiExample();
//               TInterfaceAiExample.Criteria criteria = interfaceAiExample.createCriteria();
//               criteria.andFtypeEqualTo(2);
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
        				rs = st.executeQuery(" SELECT fask  FROM  t_hire where fkeyid='"+id+"'");	
        				
        				while (rs.next()) {
        					//筛选数据
        					JSONObject one_object = new JSONObject();
        					one_object.put("fask", rs.getString("fask"));
        					one_Array.add(one_object);
        				}
        			
        				rs.close();st.close();con.close();
        				//选择学生表-数据 
        				String sql="";
        				//sql+=" SELECT a.fkeyid as FStudentid ,a.fname as FStudentname,a.fsex ,a.FBirthday,a.ftel,a.FEmail,  ";
        				//sql+=" a.FClassName,c.fname as FAbilityname,d.fname as FAbilitylevelname                            ";
        				//sql+=" from t_student a,t_student_ability b,t_ability_tree c ,t_ability_tree d                      ";
        				//sql+=" where a.FKeyID=b.FStudentID                                                                  ";
        				//sql+=" and b.FAbilityID=c.FKeyID                                                                    ";
        				//sql+=" and b.FAbilityLevelID=d.FKeyID                                                               ";
        				//sql+=" and a.FWorkState=0                                                            ";
        				sql+=" SELECT a.fkeyid AS FStudentid ,a.fname AS FStudentname,a.fsex ,a.FBirthday,a.ftel,a.FEmail,   ";
        				sql+=" a.FClassName,e.FName AS fconditionname                                                        ";
        				sql+=" FROM t_student a,t_student_ability b,t_ability_level_condition e                              ";
        				sql+=" WHERE a.FKeyID=b.FStudentID                                                                   ";
        				sql+=" AND b.FConditionID = e.FKeyID                                                                 ";
        				sql+=" AND a.FWorkState=0                                                                            ";
        				
        				
        				
        				st2 = con2.createStatement();
        				rs2 = st2.executeQuery(sql);			
        				while (rs2.next()) {
        					//筛选数据
        					JSONObject two_object = new JSONObject();
        					two_object.put("FStudentid", rs2.getString("FStudentid"));
        					two_object.put("FStudentname", rs2.getString("FStudentname"));
        					//two_object.put("FAbilityname", rs2.getString("FAbilityname"));
        					//two_object.put("FAbilitylevelname", rs2.getString("FAbilitylevelname"));
        					two_object.put("fconditionname", rs2.getString("fconditionname"));
        					two_Array.add(two_object);
        					
        				}
        				rs2.close();st2.close();con2.close();
        				
        				///////////AI接口调用/////////////////
        			     String require="请按照数据1中招聘要求的数据内容，匹配数据2中学生具有的相关能力和能力等级,并选择出最符合招聘要求的学生（数据要求大于1条,只显示匹配成功的能力）。";
        			     //String return_ai="{\"FStudentid\":\"FStudentid\",\"FStudentname\":\"FStudentname\",\"FAbilityname\":\"FAbilityname\",\"FAbilitylevelname\":\"FAbilitylevelname\"}";
        			     String return_ai="{\"FStudentid\":\"FStudentid\",\"FStudentname\":\"FStudentname\",\"fconditionname\":\"fconditionname\"}";
        			     
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
        		 	                tinterfaceai.setFtype(2);//招聘人员类型
        		 	                tinterfaceai.setFtypeid(Long.parseLong(id));//招聘ID
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

   
}