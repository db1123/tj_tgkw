package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.service.*;
import fun.tools.ParamTools;
import fun.tools.SnowflakeIdWorker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 授课教师管理 相关业务处理
 */
@RestController
@RequestMapping("/s/teacher")
public class Teacher {

    private final TTeacherService tTeacherService;

    private final TCollegeService tCollegeService;

    private final TUserService tUserService;

    private final TPowerRoleUserService powerRoleUserService;

    private final TCourseOfferingService tCourseOfferingService;

    private final TRoleService roleService;

    public Teacher(TTeacherService tTeacherService, TCollegeService tCollegeService, TUserService tUserService, TPowerRoleUserService powerRoleUserService, TCourseOfferingService tCourseOfferingService, TRoleService roleService) {
        this.tTeacherService = tTeacherService;
        this.tCollegeService = tCollegeService;
        this.tUserService = tUserService;
        this.powerRoleUserService = powerRoleUserService;
        this.tCourseOfferingService = tCourseOfferingService;
        this.roleService = roleService;
    }


    /**
     * 获取授课教师信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryteacher", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryteacher(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        Integer FGender = jsonParam.getInteger("FGender");
        Integer FStatus = jsonParam.getInteger("FStatus");
        String FCollegeID = jsonParam.getString("FCollegeID");
        String FEmail = jsonParam.getString("FEmail");
        String FPhone = jsonParam.getString("FPhone");
        String FHireDate = jsonParam.getString("FHireDate");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 获取数据库记录
            JSONArray teacher_Array = new JSONArray();
            // 查询条件
            TTeacherExample TTeacherExample = new TTeacherExample();
            TTeacherExample.Criteria criteria = TTeacherExample.createCriteria();

            if (name != null && !name.isEmpty()) {
                criteria.andFnameLike("%" + name + "%");
            }
            if (FEmail != null && !FEmail.isEmpty()) {
                criteria.andFemailLike("%" + FEmail + "%");
            }
            if (FPhone != null && !FPhone.isEmpty()) {
                criteria.andFphoneLike("%" + FPhone + "%");
            }
            if (FHireDate != null && !FHireDate.isEmpty()) {
                criteria.andFhiredateEqualTo(sdf.parse(FHireDate));
            }
            if (FGender != null && FGender != -1) {
                criteria.andFgenderEqualTo(FGender);
            }
            if (FStatus != null && FStatus != -1) {
                criteria.andFstatusEqualTo(FStatus);
            }
            if (FCollegeID != null && !FCollegeID.isEmpty() && !FCollegeID.equals("-1")) {
                FCollegeID = ParamTools.getdeParam(FCollegeID);
                criteria.andFcollegeidEqualTo(Long.parseLong(FCollegeID));
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
                TTeacherExample.setOrderByClause(orderSql.substring(1));
            } else {
                TTeacherExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TTeacher> teacherPageInfo = tTeacherService.findByExampleMapper(TTeacherExample, (page - 1) * results, results);
            List<TTeacher> teacher_list = teacherPageInfo.getList();

            for (TTeacher teacher : teacher_list) {
                JSONObject teacher_object = new JSONObject();
                teacher_object.put("key", ParamTools.getEnParam(teacher.getFkeyid().toString()));
                if (dataall == 1) {
                    teacher_object.put("FName", teacher.getFname() == null ? "" : teacher.getFname());
                    teacher_object.put("FGender", teacher.getFgender() == null ? -1 : teacher.getFgender());
                    teacher_object.put("FTitle", teacher.getFtitle() == null ? "" : teacher.getFtitle());
                    teacher_object.put("FCollegeID", teacher.getFcollegeid() == null ? "" : ParamTools.getEnParam(teacher.getFcollegeid().toString()));
                    TCollege tCollege = tCollegeService.findById(teacher.getFcollegeid());
                    teacher_object.put("FCollegeName", tCollege== null ? "" : tCollege.getFcollegename());
                    teacher_object.put("FEmail", teacher.getFemail() == null ? "" : teacher.getFemail());
                    teacher_object.put("FPhone", teacher.getFphone() == null ? "" : teacher.getFphone());
                    teacher_object.put("FHireDate", teacher.getFhiredate() == null ? "" : sdf.format(teacher.getFhiredate()));
                    teacher_object.put("FStatus", teacher.getFstatus() == null ? -1 : teacher.getFstatus());
                    teacher_object.put("FNote", teacher.getFnote() == null ? "" : teacher.getFnote());
                    teacher_object.put("FCID", teacher.getFcid());
                    teacher_object.put("FUID", teacher.getFuid());
                    teacher_object.put("FCDATE", teacher.getFcdate());
                    teacher_object.put("FUDATE", teacher.getFudate());
                } else {
                    teacher_object.put("FName", "*****");
                    teacher_object.put("FCID", "*****");
                    teacher_object.put("FUID", "*****");
                    teacher_object.put("FCDATE", "*****");
                    teacher_object.put("FUDATE", "*****");
                }

                teacher_object.put("FState", teacher.getFstate());
                teacher_Array.add(teacher_object);
            }
            // 返回值
            object.put("list", teacher_Array);
            object.put("total", teacherPageInfo.getTotal()); // 总行数
            object.put("page", teacherPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取授课教师信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatateacherSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatateacherSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray teacher_Array = new JSONArray();
            TTeacherExample teacherExample = new TTeacherExample();
            TTeacherExample.Criteria criteria = teacherExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            teacherExample.setOrderByClause("FCDATE desc");
            List<TTeacher> teacher_list = tTeacherService.findByExample(teacherExample);
            for (TTeacher teacher : teacher_list) {
                JSONObject teacher_object = new JSONObject();
                teacher_object.put("id", ParamTools.getEnParam(teacher.getFkeyid().toString()));
                teacher_object.put("text", teacher.getFname());
                teacher_Array.add(teacher_object);
            }
            // 返回值
            object.put("list", teacher_Array);
            object.put("results", teacher_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取授课教师信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatateacherSelectall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatateacherSelectall(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray teacher_Array = new JSONArray();
            TTeacherExample teacherExample = new TTeacherExample();
            TTeacherExample.Criteria criteria = teacherExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            teacherExample.setOrderByClause("FCDATE desc");
            JSONObject teacher_object = new JSONObject();
            teacher_object.put("id", "-1");
            teacher_object.put("text", "全部");
            teacher_Array.add(teacher_object);
            List<TTeacher> teacher_list = tTeacherService.findByExample(teacherExample);
            for (TTeacher teacher : teacher_list) {
                teacher_object = new JSONObject();
                teacher_object.put("id", ParamTools.getEnParam(teacher.getFkeyid().toString()));
                teacher_object.put("text", teacher.getFname());
                teacher_Array.add(teacher_object);
            }
            // 返回值

            object.put("list", teacher_Array);
            object.put("results", teacher_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取授课教师信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryteacherInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryteacherInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询授课教师信息
            TTeacher teacher = tTeacherService.findById(key);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            JSONObject teacher_object = new JSONObject();
            teacher_object.put("key", ParamTools.getEnParam(teacher.getFkeyid().toString()));
            teacher_object.put("FName", teacher.getFname() == null ? "" : teacher.getFname());
            teacher_object.put("FGender", teacher.getFgender() == null ? -1 : teacher.getFgender());
            teacher_object.put("FTitle", teacher.getFtitle() == null ? "" : teacher.getFtitle());
            teacher_object.put("FCollegeID", teacher.getFcollegeid() == null ? "" : ParamTools.getEnParam(teacher.getFcollegeid().toString()) );
            TCollege tCollege = tCollegeService.findById(teacher.getFcollegeid());
            teacher_object.put("FCollegeName", tCollege== null ? "" : tCollege.getFcollegename());
            teacher_object.put("FEmail", teacher.getFemail() == null ? "" : teacher.getFemail());
            teacher_object.put("FPhone", teacher.getFphone() == null ? "" : teacher.getFphone());
            teacher_object.put("FHireDate", teacher.getFhiredate() == null ? "" : sdf.format(teacher.getFhiredate()));
            teacher_object.put("FStatus", teacher.getFstatus() == null ? -1 : teacher.getFstatus());
            teacher_object.put("FNote", teacher.getFnote() == null ? "" : teacher.getFnote());
            teacher_object.put("FCID", teacher.getFcid());
            teacher_object.put("FUID", teacher.getFuid());
            teacher_object.put("FCDATE", teacher.getFcdate());
            teacher_object.put("FUDATE", teacher.getFudate());
            teacher_object.put("FState", teacher.getFstate());

            TUserExample tUserExample = new TUserExample();
            tUserExample.createCriteria().andFJoinIdEqualTo(teacher.getFkeyid());
            List<TUser> tUsers = tUserService.findByExample(tUserExample);
            if (tUsers.size() > 0) {
                for (TUser tUser : tUsers) {
                    JSONArray role_Array = new JSONArray();
                    TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
                    powerRoleUserExample.or().andFUserIdEqualTo(tUser.getfKeyId());
                    List<TPowerRoleUser> list = powerRoleUserService.findByExample(powerRoleUserExample);
                    for (TPowerRoleUser powerRoleUser : list) {
                        TRole role = roleService.findById(powerRoleUser.getfRoleId());
                        if (role != null) {
                            JSONObject roleObject = new JSONObject();
                            roleObject.put("id", ParamTools.getEnParam(role.getfKeyId().toString()));
                            roleObject.put("name", role.getfName());
                            role_Array.add(roleObject);
                        }
                    }
                    teacher_object.put("roles", role_Array);
                }
            }




            // 返回值
            object.put("info", teacher_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加授课教师信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加授课教师信息")
    @ResponseBody
    @RequestMapping(value = "/addteacher", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addteacher(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FName = jsonParam.getString("FName");
        Integer FGender = jsonParam.getInteger("FGender");
        String FTitle = jsonParam.getString("FTitle");
        String FCollegeID = jsonParam.getString("FCollegeID");
        String FEmail = jsonParam.getString("FEmail");
        String FPhone = jsonParam.getString("FPhone");
        String FHireDate = jsonParam.getString("FHireDate");
        Integer FStatus = jsonParam.getInteger("FStatus");
        String FNote = jsonParam.getString("FNote");
        String roles = jsonParam.getString("roles");
        try {

            TTeacherExample teacherExample = new TTeacherExample();
            TTeacherExample.Criteria criteria = teacherExample.createCriteria();
            criteria.andFphoneEqualTo(FPhone).andFnameEqualTo(FName);
            List<TTeacher> teacherList = tTeacherService.findByExample(teacherExample);
            if (teacherList.size() == 0) {
                FCollegeID = FCollegeID == null ? "0" : ParamTools.getdeParam(FCollegeID);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String CookiesLoginteacherID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginteacherID != null && !CookiesLoginteacherID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginteacherID);
                }
                // 新建授课教师信息
                TTeacher teacher = new TTeacher();
                teacher.setFname(FName);
                teacher.setFgender(FGender);
                teacher.setFtitle(FTitle);
                teacher.setFcollegeid(Long.parseLong(FCollegeID));
                teacher.setFemail(FEmail);
                teacher.setFphone(FPhone);
                teacher.setFhiredate(sdf.parse(FHireDate));
                teacher.setFstatus(FStatus);
                teacher.setFnote(FNote);
                teacher.setFcid(Long.parseLong(uid));
                teacher.setFcdate(new Date());
                tTeacherService.save(teacher);

                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
                long key = idWorker.nextId();
                //用户表添加信息
                TUser tuser = new TUser();
                tuser.setfKeyId(key);
                tuser.setfLogin(FPhone);
                tuser.setfPass(ParamTools.getEnParam("666666"));
                tuser.setfTel(FPhone);
                tuser.setfEmail(FEmail);
                tuser.setfJoinId(teacher.getFkeyid());
                tuser.setfUserno("");
                tuser.setfName(FName);
                tuser.setfType(4);//教师
                tuser.setfState(1);
                tuser.setfIsAdmin(0);
                tUserService.save(tuser);

                // 新建角色信息
                if (roles != null && !roles.equals("")) {
                    //增加新选的
                    JSONArray powerRoles_Array = JSONArray.parseArray(roles);
                    for (Object roleId : powerRoles_Array) {
                        Long fRoleId = Long.parseLong(ParamTools.getdeParam(roleId.toString()));
                        TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
                        powerRoleUserExample.or()
                                .andFUserIdEqualTo(key)
                                .andFRoleIdEqualTo(fRoleId);
                        List<TPowerRoleUser> list = powerRoleUserService.findByExample(powerRoleUserExample);
                        if (list.size() <= 0) {
                            TPowerRoleUser powerRoleUser = new TPowerRoleUser();
                            powerRoleUser.setfCId(Long.parseLong(uid));
                            powerRoleUser.setfRoleId(fRoleId);
                            powerRoleUser.setfUserId(key);
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
     * 修改授课教师信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改授课教师信息")
    @ResponseBody
    @RequestMapping(value = "/updateteacher", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateteacher(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FName = jsonParam.getString("FName");
        Integer FGender = jsonParam.getInteger("FGender");
        String FTitle = jsonParam.getString("FTitle");
        String FCollegeID = jsonParam.getString("FCollegeID");
        String FEmail = jsonParam.getString("FEmail");
        String FPhone = jsonParam.getString("FPhone");
        String FHireDate = jsonParam.getString("FHireDate");
        Integer FStatus = jsonParam.getInteger("FStatus");
        String FNote = jsonParam.getString("FNote");
        String roles = jsonParam.getString("roles");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            TTeacherExample teacherExample = new TTeacherExample();
            TTeacherExample.Criteria criteria = teacherExample.createCriteria();
            criteria.andFphoneEqualTo(FPhone).andFnameEqualTo(FName).andFkeyidNotEqualTo(key);
            List<TTeacher> teacherList = tTeacherService.findByExample(teacherExample);
            if (teacherList.size() == 0) {
                String CookiesLoginteacherID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginteacherID != null && !CookiesLoginteacherID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginteacherID);
                }
                FCollegeID = FCollegeID == null ? "0" : ParamTools.getdeParam(FCollegeID);
                // 更新授课教师信息
                TTeacher teacher = new TTeacher();
                teacher.setFkeyid(key);
                teacher.setFname(FName);
                teacher.setFgender(FGender);
                teacher.setFtitle(FTitle);
                teacher.setFcollegeid(Long.parseLong(FCollegeID));
                teacher.setFemail(FEmail);
                teacher.setFphone(FPhone);
                teacher.setFhiredate(sdf.parse(FHireDate));
                teacher.setFstatus(FStatus);
                teacher.setFnote(FNote);
                teacher.setFuid(Long.parseLong(uid));
                teacher.setFudate(new Date());
                tTeacherService.update(teacher);


                TUserExample tUserExample = new TUserExample();
                tUserExample.createCriteria().andFJoinIdEqualTo(teacher.getFkeyid());
                List<TUser> tUserList = tUserService.findByExample(tUserExample);
                if (tUserList.size() > 0) {
                    Long userID = tUserList.get(0).getfKeyId();
                    TUser tUser = new TUser();
                    tUser.setfKeyId(userID);
                    tUser.setfTel(FPhone);
                    tUser.setfLogin(FPhone);
                    tUser.setfEmail(FEmail);
                    tUser.setfName(FName);
                    tUserService.update(tUser);
                    // 更新角色信息
                    if (roles != null) {
                        if (roles.equals("")) {
                            //删除全部
                            TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
                            powerRoleUserExample.or().andFUserIdEqualTo(userID);
                            powerRoleUserService.deleteByByExample(powerRoleUserExample);
                        } else {
                            //增加新选的
                            JSONArray powerRoles_Array = JSONArray.parseArray(roles);
                            List<Long> tempRoleID = new ArrayList<>();
                            for (Object roleId : powerRoles_Array) {
                                Long fRoleId = Long.parseLong(ParamTools.getdeParam(roleId.toString()));
                                TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
                                powerRoleUserExample.or()
                                        .andFRoleIdEqualTo(fRoleId)
                                        .andFUserIdEqualTo(userID);
                                List<TPowerRoleUser> list = powerRoleUserService.findByExample(powerRoleUserExample);
                                if (list.size() <= 0) {
                                    TPowerRoleUser powerRoleUser = new TPowerRoleUser();
                                    powerRoleUser.setfCId(Long.parseLong(uid));
                                    powerRoleUser.setfRoleId(fRoleId);
                                    powerRoleUser.setfUserId(userID);
                                    powerRoleUserService.save(powerRoleUser);
                                }
                                tempRoleID.add(fRoleId);
                            }
                            //删除多余的
                            TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
                            if (tempRoleID.size() > 0) {
                                powerRoleUserExample.or()
                                        .andFUserIdEqualTo(userID)
                                        .andFRoleIdNotIn(tempRoleID);
                            } else {
                                powerRoleUserExample.or().andFUserIdEqualTo(userID);
                            }
                            powerRoleUserService.deleteByByExample(powerRoleUserExample);
                        }
                    }
                }




                // 返回值
                object.put("result", "success");
            } else {
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
     * 删除授课教师信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除授课教师信息")
    @ResponseBody
    @RequestMapping(value = "/delteacher", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delteacher(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            TCourseOfferingExample tCourseOfferingExample = new TCourseOfferingExample();
            TCourseOfferingExample.Criteria criteria = tCourseOfferingExample.createCriteria();
            criteria.andFteacherEqualTo(Long.parseLong(id));
            List<TCourseOffering> courseOfferingList = tCourseOfferingService.findByExample(tCourseOfferingExample);
            if (!courseOfferingList.isEmpty()) {
                object.put("result", "error");
                object.put("error", "授课教师已被选择，不能删除!");
            }else{
                TUserExample tUserExample = new TUserExample();
                tUserExample.createCriteria().andFJoinIdEqualTo(Long.parseLong(id));
                List<TUser> list = tUserService.findByExample(tUserExample);
                if (list.size() > 0) {
                    for (TUser tUser : list){
                        TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
                        powerRoleUserExample.or().andFUserIdEqualTo(tUser.getfKeyId());
                        powerRoleUserService.deleteByByExample(powerRoleUserExample);
                    }
                    tUserService.deleteByByExample(tUserExample);
                }
                tTeacherService.deleteById(Long.parseLong(id));
                // 返回值
                object.put("result", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 变更授课教师信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/stateteacher", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stateteacher(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginteacherID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginteacherID != null && !CookiesLoginteacherID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TTeacher teacher = new TTeacher();
            teacher.setFkeyid(Long.parseLong(id));
            teacher.setFuid(Long.parseLong(uid));
            teacher.setFudate(new Date());
            teacher.setFstate(Integer.valueOf(state));
            tTeacherService.update(teacher);
            // 返回值
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


}