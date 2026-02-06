package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TCourseOffering;
import fun.server.model.TCourseOfferingExample;
import fun.server.model.TSemester;
import fun.server.model.TSemesterExample;
import fun.server.service.TCourseOfferingService;
import fun.server.service.TSemesterService;
import fun.tools.ParamTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 学期管理 相关业务处理
 */
@RestController
@RequestMapping("/s/semester")
public class Semester {

    private final TSemesterService tSemesterService;

    private final TCourseOfferingService tCourseOfferingService;

    public Semester(TSemesterService tSemesterService, TCourseOfferingService tCourseOfferingService) {
        this.tSemesterService = tSemesterService;
        this.tCourseOfferingService = tCourseOfferingService;
    }


    /**
     * 获取学期信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querysemester", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querysemester(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        String FStartDate = jsonParam.getString("FStartDate");
        String FEndDate = jsonParam.getString("FEndDate");
        Integer FType = jsonParam.getInteger("FType");
        Integer FStatus = jsonParam.getInteger("FStatus");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray semester_Array = new JSONArray();
            // 查询条件
            TSemesterExample TSemesterExample = new TSemesterExample();
            TSemesterExample.Criteria criteria = TSemesterExample.createCriteria();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//            System.out.println(name);
//            System.out.println(FStartDate);
//            System.out.println(FEndDate);
//            System.out.println(FType);
//            System.out.println(FStatus);

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }

            if (FType != null   && FType !=-1) {
                criteria.andFtypeEqualTo(FType);
            }

            if (FStatus != null  && FStatus!=-1) {
                criteria.andFstatusEqualTo(FStatus);
            }

            try {
                if (FStartDate != null && !FStartDate.equals("")) {
                    criteria.andFstartdateEqualTo(sdf.parse(FStartDate));
                }

                if (FEndDate != null && !FEndDate.equals("")) {
                    criteria.andFenddateEqualTo(sdf.parse(FEndDate));
                }
            } catch (ParseException e) {
                criteria.andFstartdateEqualTo(sdf.parse("0000-00-00"));
                criteria.andFenddateEqualTo(sdf.parse("0000-00-00"));
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
                TSemesterExample.setOrderByClause(orderSql.substring(1));
            } else {
                TSemesterExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TSemester> semesterPageInfo = tSemesterService.findByExampleMapper(TSemesterExample, (page - 1) * results, results);
            List<TSemester> semester_list = semesterPageInfo.getList();

            for (TSemester semester : semester_list) {
                JSONObject semester_object = new JSONObject();
                semester_object.put("key", ParamTools.getEnParam(semester.getFkeyid().toString()));
                if (dataall == 1) {
                    semester_object.put("FName", semester.getFname() == null ? "" : semester.getFname());
                    semester_object.put("FStartDate", semester.getFstartdate() == null ? "" : sdf.format(semester.getFstartdate()));
                    semester_object.put("FEndDate", semester.getFenddate() == null ? "" : sdf.format(semester.getFenddate()));
                    semester_object.put("FType", semester.getFtype() == null ? -1 : semester.getFtype());
                    semester_object.put("FStatus", semester.getFstatus() == null ? -1 : semester.getFstatus());
                    semester_object.put("FNote", semester.getFnote() == null ? "" : semester.getFnote());
                    semester_object.put("FRegistrationStart", semester.getFregistrationstart() == null ? "" : sdf.format(semester.getFregistrationstart()));
                    semester_object.put("FRegistrationEnd", semester.getFregistrationend() == null ? "" : sdf.format(semester.getFregistrationend()));
                    semester_object.put("FCID", semester.getFcid());
                    semester_object.put("FUID", semester.getFuid());
                    semester_object.put("FCDATE", semester.getFcdate());
                    semester_object.put("FUDATE", semester.getFudate());
                } else {

                    semester_object.put("FStartDate", "*****");
                    semester_object.put("FEndDate", "*****");
                    semester_object.put("FType", "*****");
                    semester_object.put("FStatus", "*****");
                    semester_object.put("FRegistrationStart", "*****");
                    semester_object.put("FRegistrationEnd", "*****");
                    semester_object.put("FName", "*****");
                    semester_object.put("FCID", "*****");
                    semester_object.put("FUID", "*****");
                    semester_object.put("FCDATE", "*****");
                    semester_object.put("FUDATE", "*****");
                }

                semester_object.put("FState", semester.getFstate());
                semester_Array.add(semester_object);
            }
            // 返回值
            object.put("list", semester_Array);
            object.put("total", semesterPageInfo.getTotal()); // 总行数
            object.put("page", semesterPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取学期信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatasemesterSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatasemesterSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray semester_Array = new JSONArray();
            TSemesterExample semesterExample = new TSemesterExample();
            TSemesterExample.Criteria criteria = semesterExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            criteria.andFstatusEqualTo(1);//进行中的
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            semesterExample.setOrderByClause("FCDATE desc,FName asc");
            List<TSemester> semester_list = tSemesterService.findByExample(semesterExample);
            for (TSemester semester : semester_list) {
                JSONObject semester_object = new JSONObject();
                semester_object.put("id", ParamTools.getEnParam(semester.getFkeyid().toString()));
                semester_object.put("text", semester.getFname()+"/" + sdf.format(semester.getFstartdate()) + "~" + sdf.format(semester.getFenddate()));
                semester_Array.add(semester_object);
            }
            // 返回值
            object.put("list", semester_Array);
            object.put("results", semester_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
    /**
     * 获取学期信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatasemesterSelectall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatasemesterSelectall(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray semester_Array = new JSONArray();
            TSemesterExample semesterExample = new TSemesterExample();
            TSemesterExample.Criteria criteria = semesterExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameEqualTo("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            semesterExample.setOrderByClause("FCDATE desc");
            JSONObject semester_object = new JSONObject();
            semester_object.put("id", "-1");
            semester_object.put("text", "全部");
            semester_Array.add(semester_object);
            List<TSemester> semester_list = tSemesterService.findByExample(semesterExample);
            for (TSemester semester : semester_list) {
                semester_object = new JSONObject();
                semester_object.put("id", ParamTools.getEnParam(semester.getFkeyid().toString()));
                semester_object.put("text", semester.getFname());
                semester_Array.add(semester_object);
            }
            // 返回值

            object.put("list", semester_Array);
            object.put("results", semester_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取学期信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querysemesterInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querysemesterInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询学期信息
            TSemester semester = tSemesterService.findById(key);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            JSONObject semester_object = new JSONObject();
            semester_object.put("key", ParamTools.getEnParam(semester.getFkeyid().toString()));
            semester_object.put("FName", semester.getFname() == null ? "" : semester.getFname());
            semester_object.put("FStartDate", semester.getFstartdate() == null ? "" : sdf.format(semester.getFstartdate()));
            semester_object.put("FEndDate", semester.getFenddate() == null ? "" : sdf.format(semester.getFenddate()));
            semester_object.put("FType", semester.getFtype() == null ? -1 : semester.getFtype());
            semester_object.put("FStatus", semester.getFstatus() == null ? -1 : semester.getFstatus());
            semester_object.put("FRegistrationStart", semester.getFregistrationstart() == null ? "" : sdf.format(semester.getFregistrationstart()));
            semester_object.put("FRegistrationEnd", semester.getFregistrationend() == null ? "" : sdf.format(semester.getFregistrationend()));
            semester_object.put("FNote", semester.getFnote() == null ? "" : semester.getFnote());
            semester_object.put("FCID", semester.getFcid());
            semester_object.put("FUID", semester.getFuid());
            semester_object.put("FCDATE", semester.getFcdate());
            semester_object.put("FUDATE", semester.getFudate());
            semester_object.put("FState", semester.getFstate());
            // 返回值
            object.put("info", semester_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加学期信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加学期信息")
    @ResponseBody
    @RequestMapping(value = "/addsemester", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addsemester(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FName = jsonParam.getString("FName");
        String FStartDate = jsonParam.getString("FStartDate");
        String FEndDate = jsonParam.getString("FEndDate");
        String FRegistrationStart = jsonParam.getString("FRegistrationStart");
        String FRegistrationEnd = jsonParam.getString("FRegistrationEnd");
        String FNote = jsonParam.getString("FNote");
        Integer FType = jsonParam.getInteger("FType");
        try {

            if (repeaTSemester(0L, FName, "1") == 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String CookiesLoginsemesterID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginsemesterID != null && !CookiesLoginsemesterID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginsemesterID);
                }
                // 新建学期信息
                TSemester semester = new TSemester();
                semester.setFname(FName);
                semester.setFstartdate(sdf.parse(FStartDate));
                semester.setFenddate(sdf.parse(FEndDate));
                if(FRegistrationStart!= null && !FRegistrationStart.equals(""))
                    semester.setFregistrationstart(sdf.parse(FRegistrationStart));
                if(FRegistrationEnd!= null && !FRegistrationEnd.equals(""))
                    semester.setFregistrationend(sdf.parse(FRegistrationEnd));
                semester.setFtype(FType);
                semester.setFnote(FNote);
                semester.setFstatus(1);
                semester.setFcid(Long.parseLong(uid));
                semester.setFcdate(new Date());
                tSemesterService.save(semester);
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
     * 修改学期信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改学期信息")
    @ResponseBody
    @RequestMapping(value = "/updatesemester", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatesemester(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FName = jsonParam.getString("FName");
        String FStartDate = jsonParam.getString("FStartDate");
        String FEndDate = jsonParam.getString("FEndDate");
        String FRegistrationStart = jsonParam.getString("FRegistrationStart");
        String FRegistrationEnd = jsonParam.getString("FRegistrationEnd");
        String FNote = jsonParam.getString("FNote");
        Integer FType = jsonParam.getInteger("FType");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            if (repeaTSemester(key, FName, "2") == 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String CookiesLoginsemesterID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginsemesterID != null && !CookiesLoginsemesterID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginsemesterID);
                }
                // 更新学期信息
                TSemester semester = new TSemester();
                semester.setFkeyid(key);
                semester.setFname(FName);
                semester.setFstartdate(sdf.parse(FStartDate));
                semester.setFenddate(sdf.parse(FEndDate));
                if(FRegistrationStart!= null && !FRegistrationStart.equals(""))
                    semester.setFregistrationstart(sdf.parse(FRegistrationStart));
                if(FRegistrationEnd!= null && !FRegistrationEnd.equals(""))
                    semester.setFregistrationend(sdf.parse(FRegistrationEnd));
                semester.setFtype(FType);
                semester.setFnote(FNote);
                semester.setFuid(Long.parseLong(uid));
                semester.setFudate(new Date());
                tSemesterService.update(semester);
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
     * 删除学期信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除学期信息")
    @ResponseBody
    @RequestMapping(value = "/delsemester", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delsemester(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            TCourseOfferingExample tCourseOfferingExample =new TCourseOfferingExample();
            TCourseOfferingExample.Criteria criteria = tCourseOfferingExample.createCriteria();
            criteria.andFsemesterEqualTo(Long.parseLong(id));
            List<TCourseOffering> offeringList = tCourseOfferingService.findByExample(tCourseOfferingExample);
            if (!offeringList.isEmpty()){
                object.put("result", "error");
                object.put("error", "学期已被使用，不能删除！");
            }else{
                tSemesterService.deleteById(Long.parseLong(id));
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
     * 变更学期信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statesemester", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statesemester(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginsemesterID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginsemesterID != null && !CookiesLoginsemesterID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TSemester semester = new TSemester();
            semester.setFkeyid(Long.parseLong(id));
            semester.setFuid(Long.parseLong(uid));
            semester.setFudate(new Date());
            semester.setFstate(Integer.valueOf(state));
            tSemesterService.update(semester);
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
     * 验证学期是否存在
     */
    private int repeaTSemester(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TSemesterExample semesterExample = new TSemesterExample();
            TSemesterExample.Criteria criteria = semesterExample.createCriteria();
            if (ftype.equals("2")) { // 修改
                if (id != null) {
//                    criteria.andFkeyidEqualTo((id));
                    criteria.andFkeyidNotEqualTo(id);
                }
                if (name != null && !name.equals("")) {
                    criteria.andFnameEqualTo(name);
                }
            } else { // 新增
                if (name != null && !name.equals("")) {
                    criteria.andFnameEqualTo(name);
                }
            }
            List<TSemester> semesterList = tSemesterService.findByExample(semesterExample);
            if (semesterList.size() == 0) {
                code = 0;
            } else {
                code = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }


}