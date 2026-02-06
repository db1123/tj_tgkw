package fun.server.controller.student;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.model.customQuery.major.TClassStudentCS;
import fun.server.model.customQuery.major.TClassStudentData;
import fun.server.model.customQuery.major.TMajorCS;
import fun.server.model.customQuery.major.TMajorData;
import fun.server.service.TCollegeService;
import fun.server.service.TMajorService;
import fun.tools.ParamTools;
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
 * 专业管理 相关业务处理
 */
@RestController
@RequestMapping("/s/major")
public class Major {

    private final TMajorService tMajorService;

    private final TCollegeService tCollegeService;

    public Major(TMajorService tMajorService, TCollegeService tCollegeService) {
        this.tMajorService = tMajorService;
        this.tCollegeService = tCollegeService;
    }


    /**
     * 获取专业信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querymajor", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querymajor(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
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
            JSONArray major_Array = new JSONArray();
            // 查询条件
            TMajorExample TMajorExample = new TMajorExample();
            TMajorExample.Criteria criteria = TMajorExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFmajornameLike("%" + name + "%");

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
                TMajorExample.setOrderByClause(orderSql.substring(1));
            } else {
                TMajorExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TMajor> majorPageInfo = tMajorService.findByExampleMapper(TMajorExample, (page - 1) * results, results);
            List<TMajor> major_list = majorPageInfo.getList();

            for (TMajor major : major_list) {
                JSONObject major_object = new JSONObject();
                major_object.put("key", ParamTools.getEnParam(major.getFkeyid().toString()));
                major_object.put("FCollegeID", major.getFcollegeid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(major.getFcollegeid().toString()));
                if (dataall == 1) {
                    major_object.put("FMajorName", major.getFmajorname() == null ? "" : major.getFmajorname());
                    TCollege tCollege = tCollegeService.findById(major.getFcollegeid());
                    major_object.put("FCollegeName", tCollege == null ? "" : tCollege.getFcollegename());
                    major_object.put("FCID", major.getFcid());
                    major_object.put("FUID", major.getFuid());
                    major_object.put("FCDATE", major.getFcdate());
                    major_object.put("FUDATE", major.getFudate());
                } else {
                    major_object.put("FName", "*****");
                    major_object.put("FCollegeName", "*****");
                    major_object.put("FCID", "*****");
                    major_object.put("FUID", "*****");
                    major_object.put("FCDATE", "*****");
                    major_object.put("FUDATE", "*****");
                }

                major_object.put("FState", major.getFstate());
                major_Array.add(major_object);
            }
            // 返回值
            object.put("list", major_Array);
            object.put("total", majorPageInfo.getTotal()); // 总行数
            object.put("page", majorPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取专业信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatamajorSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatamajorSelect(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray major_Array = new JSONArray();
            TMajorExample majorExample = new TMajorExample();
            TMajorExample.Criteria criteria = majorExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFmajornameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            majorExample.setOrderByClause("fname asc");
            List<TMajor> major_list = tMajorService.findByExample(majorExample);
            for (TMajor major : major_list) {
                JSONObject major_object = new JSONObject();
                major_object.put("id", ParamTools.getEnParam(major.getFkeyid().toString()));
                major_object.put("text", major.getFmajorname());
                major_Array.add(major_object);
            }
            // 返回值

            object.put("list", major_Array);
            object.put("results", major_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取专业信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatamajorSelectsql", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatamajorSelectsql(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray major_Array = new JSONArray();

            TMajorCS tMajorCS = new TMajorCS();
            if (search != null && !search.equals("")) {
                tMajorCS.setFName(search);
            }
            tMajorCS.setOrderBy("tc.FCollegeName DESC ,tm.FMajorName");
            List<TMajorData> major_list = tMajorService.queryDatamajorSelect(tMajorCS);
            for (TMajorData major : major_list) {
                JSONObject major_object = new JSONObject();
                major_object.put("id", ParamTools.getEnParam(major.getFKeyID().toString()));
                major_object.put("text", major.getFName());
                major_Array.add(major_object);
            }
            // 返回值

            object.put("list", major_Array);
            object.put("results", major_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取专业信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatamajorSelectall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatamajorSelectall(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray major_Array = new JSONArray();
            TMajorExample majorExample = new TMajorExample();
            TMajorExample.Criteria criteria = majorExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFmajornameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            majorExample.setOrderByClause("fname asc");
            JSONObject major_object = new JSONObject();
            major_object.put("id", "-1");
            major_object.put("text", "全部");
            major_Array.add(major_object);
            List<TMajor> major_list = tMajorService.findByExample(majorExample);
            for (TMajor major : major_list) {
                major_object = new JSONObject();
                major_object.put("id", ParamTools.getEnParam(major.getFkeyid().toString()));
                major_object.put("text", major.getFmajorname());
                major_Array.add(major_object);
            }
            // 返回值

            object.put("list", major_Array);
            object.put("results", major_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取专业信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querymajorInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querymajorInfo(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询专业信息
            TMajor major = tMajorService.findById(key);
            JSONObject major_object = new JSONObject();
            major_object.put("key", ParamTools.getEnParam(major.getFkeyid().toString()));
            major_object.put("FCollegeID", major.getFcollegeid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(major.getFcollegeid().toString()));
            major_object.put("FMajorName", major.getFmajorname() == null ? "" : major.getFmajorname());
            TCollege tCollege = tCollegeService.findById(major.getFcollegeid());
            major_object.put("FCollegeName", tCollege == null ? "" : tCollege.getFcollegename());
            major_object.put("FCID", major.getFcid());
            major_object.put("FUID", major.getFuid());
            major_object.put("FCDATE", major.getFcdate());
            major_object.put("FUDATE", major.getFudate());
            major_object.put("FState", major.getFstate());
            // 返回值
            object.put("info", major_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加专业信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加专业信息")
    @ResponseBody
    @RequestMapping(value = "/addmajor", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addmajor(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FMajorName = jsonParam.getString("FMajorName");
        String FCollegeID = jsonParam.getString("FCollegeID");

        try {

            FCollegeID = FCollegeID == null ? "0" : ParamTools.getdeParam(FCollegeID);
            TMajorExample tMajorExample = new TMajorExample();
            TMajorExample.Criteria criteria = tMajorExample.createCriteria();
            criteria.andFmajornameEqualTo(FMajorName);
            criteria.andFcollegeidEqualTo(Long.parseLong(FCollegeID));
            List<TMajor> tMajorList = tMajorService.findByExample(tMajorExample);
            if (tMajorList.size() == 0) {
                String CookiesLoginmajorID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginmajorID != null && !CookiesLoginmajorID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginmajorID);
                }
                // 新建专业信息
                TMajor major = new TMajor();
                major.setFmajorname(FMajorName);
                major.setFcollegeid(Long.parseLong(FCollegeID));
                major.setFcid(Long.parseLong(uid));
                major.setFcdate(new Date());
                tMajorService.save(major);
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
     * 修改专业信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改专业信息")
    @ResponseBody
    @RequestMapping(value = "/updatemajor", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatemajor(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FMajorName = jsonParam.getString("FMajorName");
        String FCollegeID = jsonParam.getString("FCollegeID");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            FCollegeID = FCollegeID == null ? "0" : ParamTools.getdeParam(FCollegeID);
            TMajorExample tMajorExample = new TMajorExample();
            TMajorExample.Criteria criteria = tMajorExample.createCriteria();
            criteria.andFmajornameEqualTo(FMajorName);
            criteria.andFcollegeidEqualTo(Long.parseLong(FCollegeID));
            criteria.andFkeyidNotEqualTo(key);
            List<TMajor> tMajorList = tMajorService.findByExample(tMajorExample);
            if (tMajorList.size() == 0) {
                String CookiesLoginmajorID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginmajorID != null && !CookiesLoginmajorID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginmajorID);
                }
                // 更新专业信息
                TMajor major = new TMajor();
                major.setFkeyid(key);
                major.setFmajorname(FMajorName);
                major.setFcollegeid(Long.parseLong(FCollegeID));
                major.setFuid(Long.parseLong(uid));
                major.setFudate(new Date());
                tMajorService.update(major);
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
     * 删除专业信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除专业信息")
    @ResponseBody
    @RequestMapping(value = "/delmajor", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delmajor(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            tMajorService.deleteById(Long.parseLong(id));
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
     * 变更专业信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statemajor", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statemajor(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginmajorID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmajorID != null && !CookiesLoginmajorID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TMajor major = new TMajor();
            major.setFkeyid(Long.parseLong(id));
            major.setFuid(Long.parseLong(uid));
            major.setFudate(new Date());
            major.setFstate(Integer.valueOf(state));
            tMajorService.update(major);
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
     * 获取专业年级班级树形结构信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/quermajortree", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querapplyfiletype(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String fSchool = jsonParam.getString("FSchool");
        try {

//            { id:1, pId:0, name:"父节点1", open:true},
////            { id:11, pId:1, name:"父节点11"},
////            { id:111, pId:11, name:"叶子节点111"},
////            { id:112, pId:11, name:"叶子节点112"},
////            { id:113, pId:11, name:"叶子节点113"},
////            { id:114, pId:11, name:"叶子节点114"},
////            { id:12, pId:1, name:"父节点12"},
////            { id:121, pId:12, name:"叶子节点121"},
////            { id:122, pId:12, name:"叶子节点122"},


            // 获取数据库记录
            JSONArray filetype_Array = new JSONArray();
            //查询文件分组
            JSONObject filetype_object = new JSONObject();
            filetype_object = new JSONObject();
            filetype_object.put("id", ParamTools.getEnParam("999999"));
            filetype_object.put("pId", ParamTools.getEnParam("-1"));
            filetype_object.put("name", fSchool);
            filetype_object.put("issc", 2);//是否可以添加学生 1-可以 2-不可以
            filetype_object.put("ftype", -1);// -1根节点 1=专业 2=年级 3=班级
            filetype_object.put("open", true);
//            filetype_object.put("icon", "/module/capability/student/images/school.png");
            filetype_object.put("iconSkin", "school-icon");

            filetype_Array.add(filetype_object);

            //查询所有学院
            TCollegeExample collegeExample = new TCollegeExample();
            TCollegeExample.Criteria criteria1 = collegeExample.createCriteria();
            criteria1.andFstateEqualTo(1);
            List<TCollege> collegeList = tCollegeService.findByExample(collegeExample);
            if (collegeList.size() > 0) {
                for (TCollege college : collegeList) {
                    filetype_object = new JSONObject();
                    filetype_object.put("id", ParamTools.getEnParam(college.getFkeyid().toString()));
                    filetype_object.put("pId", ParamTools.getEnParam("999999"));
                    filetype_object.put("name", college.getFcollegename() == null ? "" : college.getFcollegename());
                    filetype_object.put("issc", 2);//是否可以添加学生 1-可以 2-不可以
                    filetype_object.put("ftype", 0);// -1根节点 1=专业 2=年级 3=班级
                    filetype_object.put("open", false);
                    filetype_object.put("iconSkin", "college-icon");
                    filetype_Array.add(filetype_object);

                    //查询所有专业
                    TMajorExample majorExample = new TMajorExample();
                    TMajorExample.Criteria criteria = majorExample.createCriteria();
                    criteria.andFstateEqualTo(1);//可用状态
                    criteria.andFcollegeidEqualTo(college.getFkeyid());
                    majorExample.setOrderByClause("FMajorName asc");
                    List<TMajor> majorList = tMajorService.findByExample(majorExample);
                    if (majorList.size() > 0) {
                        TMajorCS tMajorCS = null;
                        List<TMajorData> selectedTGrade = null;
                        for (TMajor major : majorList) {
                            filetype_object = new JSONObject();
                            filetype_object.put("id", ParamTools.getEnParam(major.getFkeyid().toString()));
                            filetype_object.put("pId", ParamTools.getEnParam(college.getFkeyid().toString()));
                            filetype_object.put("name", major.getFmajorname() == null ? "" : major.getFmajorname());
                            filetype_object.put("issc", 2);//是否可以添加学生 1-可以 2-不可以
                            filetype_object.put("ftype", 1);// -1根节点 1=专业 2=年级 3=班级
                            filetype_object.put("open", false);
                            filetype_object.put("iconSkin", "major-icon");
                            filetype_Array.add(filetype_object);
                            tMajorCS = new TMajorCS();
                            tMajorCS.setFMajorID(major.getFkeyid());
                            tMajorCS.setOrderBy("FAdmissionYear desc");
                            //每个专业下有哪些年级和班级
                            selectedTGrade = tMajorService.selectTGrade(tMajorCS);
                            if (selectedTGrade.size() > 0) {
                                for (TMajorData tMajorData : selectedTGrade) {
                                    filetype_object = new JSONObject();
                                    filetype_object.put("id", ParamTools.getEnParam(tMajorData.getFKeyID().toString()));
                                    filetype_object.put("pId", ParamTools.getEnParam(major.getFkeyid().toString()));
                                    filetype_object.put("name", tMajorData.getFName() == null ? "" : tMajorData.getFName());
                                    filetype_object.put("FCLassID", ParamTools.getEnParam(tMajorData.getFCLassID().toString()));
                                    filetype_object.put("FGradeID", ParamTools.getEnParam(tMajorData.getFGradeID().toString()));
                                    filetype_object.put("FMajorID", ParamTools.getEnParam(tMajorData.getFMajorID().toString()));
                                    filetype_object.put("issc", 1);//是否可以添加学生 1-可以 2-不可以
                                    filetype_object.put("ftype", 3);// -1根节点 1=专业 2=年级 3=班级
                                    filetype_object.put("iconSkin", "class-icon");
                                    filetype_Array.add(filetype_object);
                                }
                            }
                        }
                    }
                }
            }
            // 返回值
            object.put("zNodes", filetype_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }



    /**
     * 获取专业年级班级树形结构信息-班级下拉
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/quermajortreeselect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String quermajortreeselect(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String fSchool = jsonParam.getString("FSchool");
        try {

//            { id:1, pId:0, name:"父节点1", open:true},
////            { id:11, pId:1, name:"父节点11"},
////            { id:111, pId:11, name:"叶子节点111"},
////            { id:112, pId:11, name:"叶子节点112"},
////            { id:113, pId:11, name:"叶子节点113"},
////            { id:114, pId:11, name:"叶子节点114"},
////            { id:12, pId:1, name:"父节点12"},
////            { id:121, pId:12, name:"叶子节点121"},
////            { id:122, pId:12, name:"叶子节点122"},


            // 获取数据库记录
            JSONArray filetype_Array = new JSONArray();
            //查询文件分组
            JSONObject filetype_object = new JSONObject();
            filetype_object = new JSONObject();
            filetype_object.put("id", ParamTools.getEnParam("999999"));
            filetype_object.put("pId", ParamTools.getEnParam("-1"));
            filetype_object.put("name", fSchool);
            filetype_object.put("issc", 2);//是否可以添加学生 1-可以 2-不可以
            filetype_object.put("ftype", -1);// -1根节点 1=专业 2=年级 3=班级
            filetype_object.put("open", true);
//            filetype_object.put("icon", "/module/capability/student/images/school.png");
            filetype_object.put("iconSkin", "school-icon");
            filetype_object.put("nocheck", true);
            filetype_Array.add(filetype_object);

            //查询所有学院
            TCollegeExample collegeExample = new TCollegeExample();
            TCollegeExample.Criteria criteria1 = collegeExample.createCriteria();
            criteria1.andFstateEqualTo(1);
            List<TCollege> collegeList = tCollegeService.findByExample(collegeExample);
            if (collegeList.size() > 0) {
                for (TCollege college : collegeList) {
                    filetype_object = new JSONObject();
                    filetype_object.put("id", ParamTools.getEnParam(college.getFkeyid().toString()));
                    filetype_object.put("pId", ParamTools.getEnParam("999999"));
                    filetype_object.put("name", college.getFcollegename() == null ? "" : college.getFcollegename());
                    filetype_object.put("issc", 2);//是否可以添加学生 1-可以 2-不可以
                    filetype_object.put("ftype", 0);// -1根节点 1=专业 2=年级 3=班级
                    filetype_object.put("open", false);
                    filetype_object.put("iconSkin", "college-icon");
                    filetype_object.put("nocheck", true);
                    filetype_Array.add(filetype_object);

                    //查询所有专业
                    TMajorExample majorExample = new TMajorExample();
                    TMajorExample.Criteria criteria = majorExample.createCriteria();
                    criteria.andFstateEqualTo(1);//可用状态
                    criteria.andFcollegeidEqualTo(college.getFkeyid());
                    majorExample.setOrderByClause("FMajorName asc");
                    List<TMajor> majorList = tMajorService.findByExample(majorExample);
                    if (majorList.size() > 0) {
                        TMajorCS tMajorCS = null;
                        List<TMajorData> selectedTGrade = null;
                        for (TMajor major : majorList) {
                            filetype_object = new JSONObject();
                            filetype_object.put("id", ParamTools.getEnParam(major.getFkeyid().toString()));
                            filetype_object.put("pId", ParamTools.getEnParam(college.getFkeyid().toString()));
                            filetype_object.put("name", major.getFmajorname() == null ? "" : major.getFmajorname());
                            filetype_object.put("issc", 2);//是否可以添加学生 1-可以 2-不可以
                            filetype_object.put("ftype", 1);// -1根节点 1=专业 2=年级 3=班级
                            filetype_object.put("open", false);
                            filetype_object.put("iconSkin", "major-icon");
                            filetype_object.put("nocheck", true);
                            filetype_Array.add(filetype_object);
                            tMajorCS = new TMajorCS();
                            tMajorCS.setFMajorID(major.getFkeyid());
                            tMajorCS.setOrderBy("FAdmissionYear desc");
                            //每个专业下有哪些年级和班级
                            selectedTGrade = tMajorService.selectTGrade(tMajorCS);
                            if (selectedTGrade.size() > 0) {
                                for (TMajorData tMajorData : selectedTGrade) {
                                    filetype_object = new JSONObject();
                                    filetype_object.put("id", ParamTools.getEnParam(tMajorData.getFKeyID().toString()));
                                    filetype_object.put("pId", ParamTools.getEnParam(major.getFkeyid().toString()));
                                    filetype_object.put("name", tMajorData.getFName() == null ? "" : tMajorData.getFName());
                                    filetype_object.put("FCLassID", ParamTools.getEnParam(tMajorData.getFCLassID().toString()));
                                    filetype_object.put("FGradeID", ParamTools.getEnParam(tMajorData.getFGradeID().toString()));
                                    filetype_object.put("FMajorID", ParamTools.getEnParam(tMajorData.getFMajorID().toString()));
                                    filetype_object.put("issc", 1);//是否可以添加学生 1-可以 2-不可以
                                    filetype_object.put("ftype", 3);// -1根节点 1=专业 2=年级 3=班级
                                    filetype_object.put("iconSkin", "class-icon");
                                    filetype_Array.add(filetype_object);
                                }
                            }
                        }
                    }
                }
            }
            // 返回值
            object.put("zNodes", filetype_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取班级下的学生信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryclassstudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryclassstudent(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        String fno = jsonParam.getString("fno");
        String ftel = jsonParam.getString("ftel");
        String fsex = jsonParam.getString("fsex");
        String classstudentid = jsonParam.getString("classstudentid");

        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray major_Array = new JSONArray();
            // 查询条件
            TMajorCS tMajorCS = new TMajorCS();
            if (name != null && !name.equals("")) tMajorCS.setFName(name);
            if (fno != null && !fno.equals("")) tMajorCS.setFNo(fno);
            if (ftel != null && !ftel.equals("")) tMajorCS.setFTel(ftel);

            if (fsex != null && !fsex.equals("") && !fsex.equals("-1")) {
                tMajorCS.setFSex(Integer.parseInt(fsex));
            }
            if (classstudentid != null && !classstudentid.equals("") && !classstudentid.equals("-1"))
                classstudentid = ParamTools.getdeParam(classstudentid);
            tMajorCS.setFMajorID(Long.parseLong(classstudentid));
            tMajorCS.setOrderBy("FName asc");
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                tMajorCS.setOrderBy(orderSql.substring(1));
            } else {
                tMajorCS.setOrderBy("tcs.FCDATE desc");
            }
            PageInfo<TClassStudentData> majorPageInfo = tMajorService.selectclassstudent(tMajorCS, (page - 1) * results, results);
            List<TClassStudentData> major_list = majorPageInfo.getList();
            for (TClassStudentData major : major_list) {
                JSONObject major_object = new JSONObject();
                major_object.put("key", ParamTools.getEnParam(major.getFKeyID().toString()));
                major_object.put("FStudentID", ParamTools.getEnParam(major.getFStudentID()));
                if (dataall == 1) {
                    major_object.put("FName", major.getFName());
                    major_object.put("FNo", major.getFNo());
                    major_object.put("FSex", major.getFSex());
                    major_object.put("FSexName", major.getFSex());
                    major_object.put("FBirthday", major.getFBirthday());
                    major_object.put("FTel", major.getFTel());
                } else {
                    major_object.put("FName", "*****");
                    major_object.put("FNo", "*****");
                    major_object.put("FSex", "*****");
                    major_object.put("FSexName", "*****");
                    major_object.put("FBirthday", "*****");
                    major_object.put("FTel", "*****");
                }
                major_Array.add(major_object);
            }
            // 返回值
            object.put("list", major_Array);
            object.put("total", majorPageInfo.getTotal()); // 总行数
            object.put("page", majorPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }



    /**
     * 获取学生信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatstudentSelect_class", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatstudentSelect_class(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {

            TClassStudentCS tClassStudentCS = new TClassStudentCS();
            tClassStudentCS.setFName(search);
            tClassStudentCS.setOrderBy("s.FName asc");
            tClassStudentCS.setFState(1);

            JSONArray tstudent_Array = new JSONArray();
            List<TClassStudentData> classstudented = tMajorService.classstudentSelect(tClassStudentCS);
            for (TClassStudentData tClassStudentData : classstudented) {
                JSONObject tstudent_object = new JSONObject();
                tstudent_object.put("id", ParamTools.getEnParam(tClassStudentData.getFKeyID().toString()));
                tstudent_object.put("text", tClassStudentData.getFName());
                tstudent_Array.add(tstudent_object);
            }
            // 返回值
            object.put("list", tstudent_Array);
            object.put("results", tstudent_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }




}