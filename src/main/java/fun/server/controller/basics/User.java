package fun.server.controller.basics;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.controller.interfaceD.AI_interface;
import fun.server.model.*;
import fun.server.service.*;
import fun.tools.ParamTools;
import fun.tools.SnowflakeIdWorker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户管理 相关业务处理
 */
@RestController
@RequestMapping("/s/user")
public class User {

    private final TUserService userService;
    private final TRoleService roleService;
    private final TPowerRoleUserService powerRoleUserService;
    private final TDeptService tDeptService;
    private final TPostService tPostService;
    private final TTeacherService teacherService;
    private final TCollegeService tcollegeService;
    

    public User(TUserService userService, TRoleService roleService, TPowerRoleUserService powerRoleUserService, TDeptService tDeptService, TPostService tPostService, TTeacherService teacherService,TCollegeService tcollegeService) {
        this.userService = userService;
        this.roleService = roleService;
        this.powerRoleUserService = powerRoleUserService;
        this.tDeptService = tDeptService;
        this.tPostService = tPostService;
        this.teacherService=teacherService;
        this.tcollegeService=tcollegeService;
    }


    /**
     * 获取用户信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryUser( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String email = jsonParam.getString("email");
        String tel = jsonParam.getString("tel");
        String name = jsonParam.getString("name");
//        String supplierId = jsonParam.getString("supplierId");
//        String active = jsonParam.getString("active");
        Integer dataall = jsonParam.getInteger("dataall");
        Integer ftype = 1;
        try {
            ftype = jsonParam.getInteger("ftype");
        } catch (Exception e) {
            ftype =1;
        }
        try {
//            supplierId = (supplierId == null || supplierId.equals("") || supplierId.equals("-1")) ? "0" : ParamTools.getdeParam(supplierId);
            // 获取数据库记录
            JSONArray User_Array = new JSONArray();
            // 查询条件
            TUserExample UserExample = new TUserExample();
            TUserExample.Criteria criteria = UserExample.createCriteria();
            if (email != null && !email.equals("")) {
                criteria.andFEmailLike("%" + email + "%");
            }
            if (tel != null && !tel.equals("")) {
                criteria.andFTelLike("%" + tel + "%");
            }
            if (name != null && !name.equals("")) {
                criteria.andFNameLike("%" + name + "%");
            }
//            if (!supplierId.equals("0")) {
//                criteria.andFSupplierIdEqualTo(Long.parseLong(supplierId));
//            }
//            if (active != null && !active.equals("") && !active.equals("-1")) {
//                criteria.andFActiveEqualTo(Integer.parseInt(active));
//            }
            criteria.andFIsAdminEqualTo(0);
            criteria.andFTypeEqualTo(ftype);
            // 排序
            String orderSql = "";
            for (Object order: order_JA) {
                JSONObject order_Object = (JSONObject)order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                UserExample.setOrderByClause(orderSql.substring(1));
            } else {
                UserExample.setOrderByClause("f_c_date desc");
            }
            PageInfo<TUser> UserPageInfo = userService.findByExampleMapper(UserExample, (page-1)*results, results);
            List<TUser> User_list = UserPageInfo.getList();
            for (TUser User: User_list) {
                JSONObject User_object = new JSONObject();
                User_object.put("key", ParamTools.getEnParam(User.getfKeyId().toString()));
                if(dataall == 1){


                    if (User.getfDept() == null) {
                        User_object.put("f_dept", "");
                        User_object.put("dept_name", "");
                    } else {
                        User_object.put("f_dept", ParamTools.getEnParam(User.getfDept().toString()));
                        TDept tDept = tDeptService.findById(User.getfDept());
                        User_object.put("dept_name",tDept == null ? "" : tDept.getFname());
                    }
                    if (User.getfPost() == null) {
                        User_object.put("f_post", "");
                        User_object.put("post_name", "");
                    } else {
                        User_object.put("f_post", ParamTools.getEnParam(User.getfPost().toString()));
                        TPost tPost = tPostService.findById(User.getfPost());
                        User_object.put("post_name",tPost ==null ? "" : tPost.getFname());
                    }
                    //查询教师表数据
                    if (User.getfJoinId() == null) {
                    	 User_object.put("t_gender", "");//性别
                         User_object.put("t_ftitle", "");//职称
                         User_object.put("t_fstatus", "");//在职状态
                         User_object.put("t_fcollege", "");//所属学院
                         
                    }else {
                    	TTeacher ttea=teacherService.findById(User.getfJoinId());
                    	int tea_gender= ttea.getFgender();
                    	int tea_fstatus= ttea.getFstatus();
                    	String tea_ftitle= ttea.getFtitle().toString();
                     	long tea_fcollege= ttea.getFcollegeid();
                     	TCollege tcol=tcollegeService.findById(tea_fcollege);
                     	String tea_collegename=tcol.getFcollegename();
                     	
                     	if(tea_gender==1) {
                     		 User_object.put("t_gender", "男");//性别
                     	}else {
                     		 User_object.put("t_gender", "女");//性别
                     	}
                     	
                     	if(tea_fstatus==1) {
                     		 User_object.put("t_fstatus", "在职");
                     	}else if(tea_fstatus==2){
                     		 User_object.put("t_fstatus", "离职");
                     	}else {
                     		User_object.put("t_fstatus", "休假");
                     	}
                     	
                         User_object.put("t_ftitle", tea_ftitle);//职称
                         User_object.put("t_fcollege", tea_collegename);//所属学院
                    }
                   
                    
                    
                    User_object.put("f_userno", User.getfUserno() == null ? ""  : User.getfUserno());
                    User_object.put("f_name", User.getfName());
                    User_object.put("f_login", User.getfLogin());
                    User_object.put("f_email", User.getfEmail());
                    User_object.put("f_tel", User.getfTel());
                    User_object.put("f_note", User.getfNote() == null ? "" : User.getfNote());
                }else{

                    User_object.put("f_supplier_id", ParamTools.getEnParam(User.getfSupplierId().toString()));
                    User_object.put("supplier_name", "*****");



                    User_object.put("f_dept", ParamTools.getEnParam(User.getfDept().toString()));
                    User_object.put("dept_name","*****");


                    User_object.put("f_post", ParamTools.getEnParam(User.getfPost().toString()));
                    User_object.put("post_name","*****");

                    User_object.put("f_userno", "*****");
                    User_object.put("f_name", "*****");
                    User_object.put("f_login", "*****");
                    User_object.put("f_email", "*****");
                    User_object.put("f_tel", "*****");
                    User_object.put("f_note", "*****");
                    User_object.put("t_gender", "**");//性别
                    User_object.put("t_ftitle", "**");//职称
                    User_object.put("t_fstatus", "**");//在职状态
                    User_object.put("t_fcollege", "**");//所属学院

                }
                User_object.put("f_is_admin", User.getfIsAdmin());
                User_object.put("f_state", User.getfState());
                User_object.put("f_active", User.getfActive());
                User_Array.add(User_object);
            }
            // 返回值
            object.put("list", User_Array);
            object.put("total", UserPageInfo.getTotal()); // 总行数
            object.put("page", UserPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取用户信息(下拉列表)
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataUserSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataUserSelect( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            List<Integer> typelist = new ArrayList<>();
            typelist.add(1);
            typelist.add(4);
            // 获取数据库记录
            JSONArray User_Array = new JSONArray();
            TUserExample UserExample = new TUserExample();
            TUserExample.Criteria criteria = UserExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFNameLike("%" + search + "%");
            }
            criteria.andFKeyIdNotEqualTo(674579941710499839L);
            criteria.andFTypeIn(typelist);
            UserExample.setOrderByClause("f_name asc");
            List<TUser> User_list = userService.findByExample(UserExample);
            for (TUser User: User_list) {
                JSONObject User_object = new JSONObject();
                User_object.put("id", ParamTools.getEnParam(User.getfKeyId().toString()));
                User_object.put("text", User.getfName());
                User_Array.add(User_object);
            }
            // 返回值
            object.put("results", User_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取用户信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryUserInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryUserInfo( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询用户信息
            TUser User = userService.findById(key);
            JSONObject user_object = new JSONObject();
            user_object.put("key", ParamTools.getEnParam(User.getfKeyId().toString()));


            if (User.getfDept() == null) {
                user_object.put("f_dept", "");
                user_object.put("dept_name", "");
            } else {
                user_object.put("f_dept", ParamTools.getEnParam(User.getfDept().toString()));
                TDept tDept = tDeptService.findById(User.getfDept());
                user_object.put("dept_name",tDept == null ? "" : tDept.getFname());
            }
            if (User.getfPost() == null) {
                user_object.put("f_post", "");
                user_object.put("post_name", "");
            } else {
                user_object.put("f_post", ParamTools.getEnParam(User.getfPost().toString()));
                TPost tPost = tPostService.findById(User.getfPost());
                user_object.put("post_name",tPost ==null ? "" : tPost.getFname());
            }
            user_object.put("f_userno", User.getfUserno() == null ? ""  : User.getfUserno());
            user_object.put("f_login", User.getfLogin());
            user_object.put("f_name", User.getfName());
            user_object.put("f_tel", User.getfTel());
            user_object.put("f_pass", ParamTools.getdeParam(User.getfPass()));
            user_object.put("f_email", User.getfEmail());
            user_object.put("f_note", User.getfNote());
            user_object.put("f_type", User.getfType());
            JSONArray role_Array = new JSONArray();
            TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
            powerRoleUserExample.or().andFUserIdEqualTo(key);
            List<TPowerRoleUser> list = powerRoleUserService.findByExample(powerRoleUserExample);
            for (TPowerRoleUser powerRoleUser: list) {
                TRole role = roleService.findById(powerRoleUser.getfRoleId());
                if(role!=null){
                    JSONObject roleObject = new JSONObject();
                    roleObject.put("id", ParamTools.getEnParam(role.getfKeyId().toString()));
                    roleObject.put("name", role.getfName());
                    role_Array.add(roleObject);
                }
            }
            user_object.put("roles", role_Array);
            // 返回值
            object.put("info", user_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加用户信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("添加用户信息")
    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addUser( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
//        String f_supplier_id = jsonParam.getString("f_supplier_id");
        String f_login = jsonParam.getString("f_login");
        String f_pass = jsonParam.getString("f_pass");
        String f_name = jsonParam.getString("f_name");
        String f_tel = jsonParam.getString("f_tel");
        String f_email = jsonParam.getString("f_email");
        String f_note = jsonParam.getString("f_note");
        String roles = jsonParam.getString("roles");

        String f_dept = jsonParam.getString("f_dept");
        String f_post = jsonParam.getString("f_post");
        String f_userno = jsonParam.getString("f_userno");
        try {

            f_dept = f_dept == null || f_dept.equals("") ? "0" : ParamTools.getdeParam(f_dept);
            f_post = f_post == null || f_post.equals("") ? "0" : ParamTools.getdeParam(f_post);


            if (repeatUser(0L, f_login, "1") == 0) {
                String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginUserID);
                }
                // 新建用户信息
                TUser User = new TUser();
                User.setfLogin(f_login);
                User.setfPass(ParamTools.getEnParam(f_pass));
                User.setfName(f_name);
                User.setfTel(f_tel);
                User.setfEmail(f_email);
                User.setfNote(f_note);
                User.setfState(1);
                User.setfIsAdmin(0);
                User.setfCId(Long.parseLong(uid));
                User.setfDept(Long.parseLong(f_dept));
                User.setfPost(Long.parseLong(f_post));
                User.setfUserno(f_userno);
                User.setfCDate(new Date());
                userService.save(User);
                // 新建角色信息
                if (roles != null && !roles.equals("")) {
                  //增加新选的
                  JSONArray powerRoles_Array = JSONArray.parseArray(roles);
                  for (Object roleId: powerRoles_Array) {
                      Long fRoleId = Long.parseLong(ParamTools.getdeParam(roleId.toString()));
                      TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
                      powerRoleUserExample.or()
                              .andFUserIdEqualTo(User.getfKeyId())
                              .andFRoleIdEqualTo(fRoleId);
                      List<TPowerRoleUser> list = powerRoleUserService.findByExample(powerRoleUserExample);
                      if (list.size() <= 0) {
                          TPowerRoleUser powerRoleUser = new TPowerRoleUser();
                          powerRoleUser.setfCId(Long.parseLong(uid));
                          powerRoleUser.setfRoleId(fRoleId);
                          powerRoleUser.setfUserId(User.getfKeyId());
                          powerRoleUserService.save(powerRoleUser);
                      }
                  }
              }
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
     * 修改用户信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("修改用户信息")
    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateUser( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
                System.out.println("执行用户修改方法");
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("f_key_id");
        String f_login = jsonParam.getString("f_login");
        String f_pass = jsonParam.getString("f_pass");
        String f_name = jsonParam.getString("f_name");
        String f_tel = jsonParam.getString("f_tel");
        String f_email = jsonParam.getString("f_email");
        String f_note = jsonParam.getString("f_note");
        String roles = jsonParam.getString("roles");
        String f_dept = jsonParam.getString("f_dept");
        String f_post = jsonParam.getString("f_post");
        String f_userno = jsonParam.getString("f_userno");
        try {

            f_dept = f_dept == null || f_dept.equals("") ? "0" : ParamTools.getdeParam(f_dept);
            f_post = f_post == null || f_post.equals("") ? "0" : ParamTools.getdeParam(f_post);
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            if (repeatUser(key, f_login, "2") == 0) {
                String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginUserID);
                }
                // 更新用户信息
                TUser User = new TUser();
                User.setfKeyId(key);
                User.setfLogin(f_login);
                User.setfPass(ParamTools.getEnParam(f_pass));
                User.setfName(f_name);
                User.setfTel(f_tel);
                User.setfEmail(f_email);
                User.setfNote(f_note);
                User.setfDept(Long.parseLong(f_dept));
                User.setfPost(Long.parseLong(f_post));
                User.setfUserno(f_userno);
                User.setfUId(Long.parseLong(uid));
                User.setfUDate(new Date());
                userService.update(User);
                // 更新角色信息
                if (roles != null) {
                  if (roles.equals("")) {
                    //删除全部
                    TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
                    powerRoleUserExample.or().andFUserIdEqualTo(key);
                    powerRoleUserService.deleteByByExample(powerRoleUserExample);
                  } else {
                      //增加新选的
                      JSONArray powerRoles_Array = JSONArray.parseArray(roles);
                      List<Long> tempRoleID = new ArrayList<>();
                      for (Object roleId: powerRoles_Array) {
                          Long fRoleId = Long.parseLong(ParamTools.getdeParam(roleId.toString()));
                          TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
                          powerRoleUserExample.or()
                                  .andFRoleIdEqualTo(fRoleId)
                                  .andFUserIdEqualTo(key);
                          List<TPowerRoleUser> list = powerRoleUserService.findByExample(powerRoleUserExample);
                          if (list.size() <= 0) {
                              TPowerRoleUser powerRoleUser = new TPowerRoleUser();
                              powerRoleUser.setfCId(Long.parseLong(uid));
                              powerRoleUser.setfRoleId(fRoleId);
                              powerRoleUser.setfUserId(key);
                              powerRoleUserService.save(powerRoleUser);
                          }
                          tempRoleID.add(fRoleId);
                      }
                      //删除多余的
                      TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
                      if (tempRoleID.size() > 0) {
                          powerRoleUserExample.or()
                                  .andFUserIdEqualTo(key)
                                  .andFRoleIdNotIn(tempRoleID);
                      } else {
                          powerRoleUserExample.or().andFUserIdEqualTo(key);
                      }
                      powerRoleUserService.deleteByByExample(powerRoleUserExample);
                  }
                }
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
     * 删除用户信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("删除用户信息")
    @ResponseBody
    @RequestMapping(value = "/delUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delUser( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            //删除图谱用户数据
            TUser tuser=userService.findById(key);
            TTeacher tteacher=teacherService.findById(tuser.getfJoinId());
            String tp_result = SetPost.sendPostDeleteUser(tteacher.getFtpid());
            JSONObject jsonObject1 = JSONObject.parseObject(tp_result);
            String code = jsonObject1.getString("code");
            
            //System.out.println("code:"+code);
            if(code.equals("200")) {
            	
            }else {
         	   object.put("result", "tp_error");
            }
        	//删除教师表信息
            teacherService.deleteById(tuser.getfJoinId());
            // 删除用户记录
            userService.deleteById(key);
            
            
            // 删除角色信息
            TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
            powerRoleUserExample.or().andFUserIdEqualTo(key);
            powerRoleUserService.deleteByByExample(powerRoleUserExample);
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
     * 修改用户基本信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("修改用户基本信息")
    @ResponseBody
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateUserInfo( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("f_key_id");
        String f_name = jsonParam.getString("f_name");
        String f_tel = jsonParam.getString("f_tel");
        String f_email = jsonParam.getString("f_email");
        String f_note = jsonParam.getString("f_note");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
			
		     // 查询图谱平台ID
            TUser User_teacher = userService.findById(key);
            TTeacher teacher=teacherService.findById(User_teacher.getfJoinId());
            String tp_id=teacher.getFtpid();
            String tp_username=User_teacher.getfLogin();

            //修改图谱用户信息/////
            String tp_result= SetPost.sendPostUpdateUser(tp_id, tp_username, "", f_name,"", f_email, f_tel, "");
            JSONObject jsonObject1 = JSONObject.parseObject(tp_result);
            String code = jsonObject1.getString("code");
             //System.out.println(code);
            if(code.equals("200")) {
            	// 更新用户信息
                TUser User = new TUser();
                User.setfKeyId(key);
                User.setfName(f_name);
                User.setfTel(f_tel);
                User.setfEmail(f_email);
                User.setfNote(f_note);
                User.setfUId(Long.parseLong(uid));
                User.setfUDate(new Date());
                userService.update(User);
                // 返回值
                object.put("result", "success");	
         	}else {
         		 // 返回值
                object.put("result", "tp_user_fail");
         	}
            
         
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 修改密码
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    @LogOperation("修改密码")
    @ResponseBody
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatePassword(HttpServletRequest request, HttpServletResponse response )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String old_pass = jsonParam.getString("old_pass");
        String new_pass = jsonParam.getString("new_pass");
        String confirm_pass = jsonParam.getString("confirm_pass");
        try{
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            // 查询条件
            TUserExample UserExample = new TUserExample();
            UserExample.or()
                    .andFKeyIdEqualTo(Long.parseLong(uid))
                    .andFPassEqualTo(ParamTools.getEnParam(old_pass))
                    .andFStateEqualTo(1);
            List<TUser> userList = userService.findByExample(UserExample);
            if (userList.size() > 0) {
                if (new_pass.equals(confirm_pass)) {
                	

       		     // 查询图谱平台ID
                   TUser User_teacher = userService.findById(Long.parseLong(uid));
                   TTeacher teacher=teacherService.findById(User_teacher.getfJoinId());
                   String tp_id=teacher.getFtpid();
                   String tp_username=User_teacher.getfLogin();
                   

                   //修改图谱用户密码/////
                   String tp_result= SetPost.sendPostUpdateUser(tp_id, tp_username, new_pass,"","", "","", "");
                   JSONObject jsonObject1 = JSONObject.parseObject(tp_result);
                   String code = jsonObject1.getString("code");
                    //System.out.println(code);
                   if(code.equals("200")) {

                       // 更新用户信息
                       TUser User = new TUser();
                       User.setfKeyId(Long.parseLong(uid));
                       User.setfPass(ParamTools.getEnParam(new_pass));
                       User.setfUId(Long.parseLong(uid));
                       User.setfUDate(new Date());
                       userService.update(User);
                   }else {
                	   object.put("result", "tp_error");
                   }
                	
                	
                } else {
                    object.put("result", "error");
                    object.put("error", "确认密码与新密码不符！");
                }
                // 返回结果
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "旧密码错误！");
            }
        }catch (Exception e){
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return  object.toString();
    }
    
    
    /**
     * 修改密码-前端
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    @LogOperation("修改密码-前端")
    @ResponseBody
    @RequestMapping(value = "/updatePassword_user", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatePassword_user(HttpServletRequest request, HttpServletResponse response )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String old_pass = jsonParam.getString("old_pass");
        String new_pass = jsonParam.getString("new_pass");
        String confirm_pass = jsonParam.getString("confirm_pass");
        String username = jsonParam.getString("username");//用户名
    	Long uid = null;
        try{
            
            // 查询条件
            TUserExample UserExample = new TUserExample();
            UserExample.or()
                    .andFLoginEqualTo(username)
                    .andFPassEqualTo(ParamTools.getEnParam(old_pass))
                    .andFStateEqualTo(1);
            List<TUser> userList = userService.findByExample(UserExample);
            if (userList.size() > 0) {
                if (new_pass.equals(confirm_pass)) {
                	for (TUser User: userList) {
                		uid=User.getfKeyId();
	                }
                	// 查询图谱平台ID
                	
                   TUser User_teacher = userService.findById(uid);
                   TTeacher teacher=teacherService.findById(User_teacher.getfJoinId());
                   String tp_id=teacher.getFtpid();
                   String tp_username=User_teacher.getfLogin();
                   

                   //修改图谱用户密码/////
                   String tp_result= SetPost.sendPostUpdateUser(tp_id, tp_username, new_pass,"","", "","", "");
                   JSONObject jsonObject1 = JSONObject.parseObject(tp_result);
                   String code = jsonObject1.getString("code");
                    //System.out.println(code);
                   if(code.equals("200")) {

                       // 更新用户信息
                       TUser User = new TUser();
                       User.setfKeyId(uid);
                       User.setfPass(ParamTools.getEnParam(new_pass));
                       User.setfUId(uid);
                       User.setfUDate(new Date());
                       userService.update(User);
                   }else {
                	   object.put("result", "tp_error");
                   }
                	
                	
                } else {
                    object.put("result", "error");
                    object.put("error", "确认密码与新密码不符！");
                }
                // 返回结果
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "旧密码错误！");
            }
        }catch (Exception e){
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return  object.toString();
    }
    
    

    /**
     * 重置密码
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    @LogOperation("重置密码")
    @ResponseBody
    @RequestMapping(value = "/changepwd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String changepwd(HttpServletRequest request, HttpServletResponse response )
            throws Exception {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String fkeyid = jsonParam.getString("id");//用户表fkeyid
        fkeyid = fkeyid == null ? "0" : (fkeyid.equals("0") ? "0" : ParamTools.getdeParam(fkeyid));
        long uid = Long.parseLong(fkeyid);
        String new_pass="543216";
    	
        try{
         
                	// 查询图谱平台ID
                   TUser User_teacher = userService.findById(uid);
                   TTeacher teacher=teacherService.findById(User_teacher.getfJoinId());
                   String tp_id=teacher.getFtpid();
                   String tp_username=User_teacher.getfLogin();
                   

                   //修改图谱用户密码/////
                   String tp_result= SetPost.sendPostUpdateUser(tp_id, tp_username, new_pass,"","", "","", "");
                   JSONObject jsonObject1 = JSONObject.parseObject(tp_result);
                   String code = jsonObject1.getString("code");
                    //System.out.println(code);
                   if(code.equals("200")) {

                       // 更新用户信息
                       TUser User = new TUser();
                       User.setfKeyId(uid);
                       User.setfPass(ParamTools.getEnParam(new_pass));
                       User.setfUId(uid);
                       User.setfUDate(new Date());
                       userService.update(User);
                   }else {
                	   object.put("result", "tp_error");
                   }
                	
                	
              
                // 返回结果
                object.put("result", "success");
          
        }catch (Exception e){
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return  object.toString();
    }


//    /**
//     * 注册
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException 输入输出异常
//     */
//     @ResponseBody
//     @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//     public String register( HttpServletRequest request )
//             throws UnsupportedEncodingException, IOException {
//         JSONObject object = new JSONObject();
//         // 获取请求参数
//         JSONObject jsonParam = ParamTools.getParameters(request);
//         String f_login = jsonParam.getString("f_login");
//         String f_pass = jsonParam.getString("f_pass");
//         String f_name = jsonParam.getString("f_name");
//         String f_email = jsonParam.getString("f_email");
//         String f_note = jsonParam.getString("f_note");
//         String f_gender= jsonParam.getString("f_gender");
//         String f_title= jsonParam.getString("f_title");
//         String f_phone= jsonParam.getString("f_phone");
//         String f_status= jsonParam.getString("f_status");
//         String FCollegeID= jsonParam.getString("FCollegeID");
//         
//         try {
//             if (repeatUser(0L, f_login, "4") == 0) {
//            	 
//                 //同步添加图谱用户start/////
//                String tp_result= SetPost.sendPostAddUser(f_login, f_pass, f_name, f_gender, f_email, f_phone, "", "1");
//                JSONObject jsonObject1 = JSONObject.parseObject(tp_result);
//                String code = jsonObject1.getString("code");
//                //System.out.println(code);
//              
//                if(code.equals("200")) {
//                	  String tp_id = jsonObject1.getString("id");//图谱系统用户ID
//                	
//                 	  //新建教师信息
//                 	  SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
//                      long key = idWorker.nextId();
//                      TTeacher teacher = new TTeacher();
//                      teacher.setFkeyid(key);
//                      teacher.setFstate(1);
//                      teacher.setFname(f_name);
//                      teacher.setFgender( Integer.parseInt(f_gender));
//                      teacher.setFtitle(f_title);
//                      teacher.setFemail(f_email);
//                      teacher.setFphone(f_phone);
//                      teacher.setFstatus(Integer.parseInt(f_status));
//                      teacher.setFnote(f_note);
//                      teacher.setFcollegeid(Long.parseLong(ParamTools.getdeParam(FCollegeID)));
//                      teacher.setFtpid(tp_id);//添加图谱用户ID
//                      
//                      teacherService.save(teacher);
//                      // 新建用户信息
//                      TUser User = new TUser();
//                      User.setfLogin(f_login);
//                      User.setfPass(ParamTools.getEnParam(f_pass));
//                      User.setfName(f_name);
//                      User.setfTel(f_phone);
//                      User.setfEmail(f_email);
//                      User.setfNote(f_note);
//                      User.setfUserno(TJRAC+);//用户编号-TJRAC+4位不重复数字
//                      
//                      User.setfState(1);
//                      User.setfType(4);//教师用户
//                      User.setfJoinId(key);//教师表ID
//                      User.setfIsAdmin(0);
//                 
//                      User.setfCDate(new Date());
//                      
//                      userService.save(User);
//                      // 新建教师权限信息
//                      TPowerRoleUser powerRoleUser = new TPowerRoleUser();
//                      powerRoleUser.setfCId(User.getfKeyId());
//                      powerRoleUser.setfRoleId(1409872075195289600L);
//                      powerRoleUser.setfUserId(User.getfKeyId());
//                      powerRoleUserService.save(powerRoleUser);
//                      
//                	  // 返回值
//                      object.put("result", "success");
//                }else {
//                	 // 返回值
//                    object.put("result", "tp_user_fail");
//                }
//                 //添加图谱用户end///////
//                
//
//             } else {
//                 // 返回值
//                 object.put("result", "fail");
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//             object.put("result", "error");
//             object.put("error", e.toString());
//         }
//         return object.toString();
//     }
    /**
     * 注册
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String register(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String f_login = jsonParam.getString("f_login");
        String f_pass = jsonParam.getString("f_pass");
        String f_name = jsonParam.getString("f_name");
        String f_email = jsonParam.getString("f_email");
        String f_note = jsonParam.getString("f_note");
        String f_gender = jsonParam.getString("f_gender");
        String f_title = jsonParam.getString("f_title");
        String f_phone = jsonParam.getString("f_phone");
        String f_status = jsonParam.getString("f_status");
        String FCollegeID = jsonParam.getString("FCollegeID");
        
        try {
            if (repeatUser(0L, f_login, "4") == 0) {
                // 同步添加图谱用户start/////
                String tp_result = SetPost.sendPostAddUser(f_login, f_pass, f_name, f_gender, f_email, f_phone, "", "1");
                JSONObject jsonObject1 = JSONObject.parseObject(tp_result);
                String code = jsonObject1.getString("code");
                  
                if (code.equals("200")) {
                    String tp_id = jsonObject1.getString("id");// 图谱系统用户ID
                    	
                    // 新建教师信息
                    SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
                    long key = idWorker.nextId();
                    TTeacher teacher = new TTeacher();
                    teacher.setFkeyid(key);
                    teacher.setFstate(1);
                    teacher.setFname(f_name);
                    teacher.setFgender(Integer.parseInt(f_gender));
                    teacher.setFtitle(f_title);
                    teacher.setFemail(f_email);
                    teacher.setFphone(f_phone);
                    teacher.setFstatus(Integer.parseInt(f_status));
                    teacher.setFnote(f_note);
                    teacher.setFcollegeid(Long.parseLong(ParamTools.getdeParam(FCollegeID)));
                    teacher.setFtpid(tp_id);// 添加图谱用户ID
                      
                    teacherService.save(teacher);
                    // 新建用户信息
                    TUser user = new TUser();
                    user.setfLogin(f_login);
                    user.setfPass(ParamTools.getEnParam(f_pass));
                    user.setfName(f_name);
                    user.setfTel(f_phone);
                    user.setfEmail(f_email);
                    user.setfNote(f_note);
                    
                    // 生成唯一用户编号：TJRAC+4位不重复数字
                    String userNoPrefix = "TJRAC";
                    int maxNum = 0;
                    
                    // 假设userService有查询所有用户的方法（或分页查询符合前缀的用户）
                    // 此处使用查询所有用户并过滤的方式（数据量大时建议优化）
                    List<TUser> allUsers = userService.findAll(); // 假设存在该方法
                    for (TUser u : allUsers) {
                        String userno = u.getfUserno();
                        if (userno != null && userno.startsWith(userNoPrefix) && userno.length() == userNoPrefix.length() + 4) {
                            try {
                                // 提取数字部分
                                String numStr = userno.substring(userNoPrefix.length());
                                int num = Integer.parseInt(numStr);
                                if (num > maxNum) {
                                    maxNum = num;
                                }
                            } catch (NumberFormatException e) {
                                // 忽略格式错误的编号
                                continue;
                            }
                        }
                    }
                    
                    // 计算下一个编号（确保在1-9999范围内）
                    int nextNum = maxNum + 1;
                    if (nextNum > 9999) {
                        throw new RuntimeException("用户编号已达上限（TJRAC9999）");
                    }
                    
                    // 格式化为4位数字
                    String userNo = userNoPrefix + String.format("%04d", nextNum);
                    user.setfUserno(userNo);
                    
                    user.setfState(1);
                    user.setfType(4);// 教师用户
                    user.setfJoinId(key);// 教师表ID
                    user.setfIsAdmin(0);
                    user.setfCDate(new Date());
                      
                    userService.save(user);
                    // 新建教师权限信息
                    TPowerRoleUser powerRoleUser = new TPowerRoleUser();
                    powerRoleUser.setfCId(user.getfKeyId());
                    powerRoleUser.setfRoleId(1409872075195289600L);
                    powerRoleUser.setfUserId(user.getfKeyId());
                    powerRoleUserService.save(powerRoleUser);
                      
                    // 返回值
                    object.put("result", "success");
                } else {
                    // 返回值
                    object.put("result", "tp_user_fail");
                }
                // 添加图谱用户end///////
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
     * 变更用户信息状态
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("变更用户信息状态")
    @ResponseBody
    @RequestMapping(value = "/stateUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stateUser( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TUser User = new TUser();
            User.setfKeyId(Long.parseLong(id));
            User.setfUId(Long.parseLong(uid));
            User.setfUDate(new Date());
            User.setfState(Integer.valueOf(state));
            userService.update(User);
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
     * 验证用户登录名称是否重复
     */
    private int repeatUser(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TUserExample UserExample = new TUserExample();
            TUserExample.Criteria criteria = UserExample.createCriteria();
            if (ftype.equals("2")) { // 修改
                if (id != null) {
                    criteria.andFKeyIdNotEqualTo(id);
                }
                if (name != null && !name.equals("")) {
                    criteria.andFLoginEqualTo(name);
                }
            } else { // 新增
                if (name != null && !name.equals("")) {
                    criteria.andFLoginEqualTo(name);
                }
            }
            List<TUser> userList = userService.findByExample(UserExample);
            if (userList.size() == 0) {
                code = 0;
            } else {
                code = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  code;
    }
}