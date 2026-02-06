package fun.server.controller.courseoffering;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TCourseOffering;
import fun.server.model.TCourseSchedule;
import fun.server.model.TCourseScheduleExample;
import fun.server.model.TSemester;
import fun.server.model.customQuery.courseSchedule.courseScheduleCS;
import fun.server.model.customQuery.courseSchedule.courseScheduleData;
import fun.server.service.TCourseOfferingService;
import fun.server.service.TCourseScheduleService;
import fun.server.service.TSemesterService;
import fun.tools.ParamTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 开课时间管理 相关业务处理
 */
@RestController
@RequestMapping("/s/courseschedule")
public class CourseSchedule {

    private final TCourseScheduleService tCourseScheduleService;

    private final TCourseOfferingService tCourseOfferingService;

    private final TSemesterService tSemesterService;



    public CourseSchedule(TCourseScheduleService tCourseScheduleService, TCourseOfferingService tCourseOfferingService, TSemesterService tSemesterService) {
        this.tCourseScheduleService = tCourseScheduleService;
        this.tCourseOfferingService = tCourseOfferingService;
        this.tSemesterService = tSemesterService;
    }


    /**
     * 获取开课时间信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycourseschedule", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycourseschedule(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String FCOID = jsonParam.getString("FCOID");
        String FCRMName = jsonParam.getString("FCRMName");
//        Integer dataall = jsonParam.getInteger("dataall");
        Integer FWeekday = jsonParam.getInteger("FWeekday");
        try {
            // 获取数据库记录
            JSONArray courseschedule_Array = new JSONArray();
            // 查询条件
            courseScheduleCS courseScheduleCS = new courseScheduleCS();
            FCOID = FCOID == null ? "0" : ParamTools.getdeParam(FCOID);
            courseScheduleCS.setFCOID(Long.parseLong(FCOID));
            courseScheduleCS.setFCRMName(FCRMName);

            courseScheduleCS.setFWeekday(FWeekday);
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                courseScheduleCS.setOrderBy(orderSql.substring(1));
            } else {
                courseScheduleCS.setOrderBy("tcs.FCDATE desc");
            }
            PageInfo<courseScheduleData> courseschedulePageInfo = tCourseScheduleService.getTCourseScheduleList(courseScheduleCS, (page - 1) * results, results);
            List<courseScheduleData> courseschedule_list = courseschedulePageInfo.getList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            for (courseScheduleData courseschedule : courseschedule_list) {
                JSONObject courseschedule_object = new JSONObject();
                courseschedule_object.put("key", ParamTools.getEnParam(courseschedule.getFKeyID().toString()));
                courseschedule_object.put("FCOID", ParamTools.getEnParam(courseschedule.getFCOID().toString()));
                courseschedule_object.put("FCRMID", ParamTools.getEnParam(courseschedule.getFCRMID().toString()));
                courseschedule_object.put("FCRMName", courseschedule.getFCRMName());
                courseschedule_object.put("FWeekday", courseschedule.getFWeekday());
                courseschedule_object.put("FStartTime", sdf.format(sdf.parse(courseschedule.getFStartTime())));
                courseschedule_object.put("FEndTime", sdf.format(sdf.parse(courseschedule.getFEndTime())));
                courseschedule_object.put("FSession", courseschedule.getFSession());
                courseschedule_object.put("FNote", courseschedule.getFNote());
                courseschedule_Array.add(courseschedule_object);
            }
            // 返回值
            object.put("list", courseschedule_Array);
            object.put("total", courseschedulePageInfo.getTotal()); // 总行数
            object.put("page", courseschedulePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 根据ID获取开课时间信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycoursescheduleInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycoursescheduleInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            courseScheduleCS courseScheduleCS = new courseScheduleCS();
            courseScheduleCS.setFKeyID(key);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            // 查询开课时间信息
            courseScheduleData courseschedule = tCourseScheduleService.getTCourseScheduleInfo(courseScheduleCS);
            JSONObject courseschedule_object = new JSONObject();
            courseschedule_object.put("key", ParamTools.getEnParam(courseschedule.getFKeyID().toString()));
            courseschedule_object.put("FCOID", ParamTools.getEnParam(courseschedule.getFCOID().toString()));
            courseschedule_object.put("FCRMID", ParamTools.getEnParam(courseschedule.getFCRMID().toString()));
            courseschedule_object.put("FCRMName", courseschedule.getFCRMName());
            courseschedule_object.put("FWeekday", courseschedule.getFWeekday());
            courseschedule_object.put("FStartTime", sdf.format(sdf.parse(courseschedule.getFStartTime())));
            courseschedule_object.put("FEndTime", sdf.format(sdf.parse(courseschedule.getFEndTime())));
            courseschedule_object.put("FSession", courseschedule.getFSession());
            courseschedule_object.put("FNote", courseschedule.getFNote());
            // 返回值
            object.put("info", courseschedule_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取学期时间范围
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycoursescheduleInfoxq", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycoursescheduleInfoxq(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            // 查询开课时间信息
            TSemester tSemester = tSemesterService.findById(key);
            JSONObject courseschedule_object = new JSONObject();
            courseschedule_object.put("minDate",sdf.format(sdf.parse(sdf.format(tSemester.getFstartdate()))));
            courseschedule_object.put("maxDate",sdf.format(sdf.parse(sdf.format(tSemester.getFenddate()))));
            // 返回值
            object.put("info", courseschedule_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加开课时间信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加开课时间信息")
    @ResponseBody
    @RequestMapping(value = "/addcourseschedule", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addcourseschedule(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FCOID = jsonParam.getString("FCOID");
        String FCRMID = jsonParam.getString("FCRMID");
        Integer FWeekday = jsonParam.getInteger("FWeekday");
        String FStartTime = jsonParam.getString("FStartTime");
        String FEndTime = jsonParam.getString("FEndTime");
        String FSession = jsonParam.getString("FSession");
        String FNote = jsonParam.getString("FNote");

        try {
            String CookiesLogincoursescheduleID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincoursescheduleID != null && !CookiesLogincoursescheduleID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLogincoursescheduleID);
            }
            FCOID = FCOID == null ? "0" : ParamTools.getdeParam(FCOID);
            FCRMID = FCRMID == null ? "0" : ParamTools.getdeParam(FCRMID);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            if(!FStartTime.equals("") && !FEndTime.equals("") ){
                TCourseScheduleExample tCourseScheduleExample = new TCourseScheduleExample();
                TCourseScheduleExample.Criteria criteria = tCourseScheduleExample.createCriteria();
                criteria.andFcrmidEqualTo(Long.parseLong(FCRMID));
                criteria.andFstarttimeLessThanOrEqualTo(sdf.parse(FStartTime));
                criteria.andFendtimeGreaterThanOrEqualTo(sdf.parse(FStartTime));
                //如果新增的开始时间，在这个教室 已有时间的范围内 ，则冲突
                List<TCourseSchedule> courseScheduleList = tCourseScheduleService.findByExample(tCourseScheduleExample);
                if (courseScheduleList.size() == 0){
                    // 新建开课时间信息
                    TCourseSchedule courseschedule = new TCourseSchedule();
                    courseschedule.setFcoid(Long.parseLong(FCOID));
                    courseschedule.setFcrmid(Long.parseLong(FCRMID));
                    courseschedule.setFweekday(FWeekday);
                    courseschedule.setFstarttime(sdf.parse(FStartTime));
                    courseschedule.setFendtime(sdf.parse(FEndTime));
                    courseschedule.setFsession(FSession);
                    courseschedule.setFnote(FNote);
                    courseschedule.setFcid(Long.parseLong(uid));
                    courseschedule.setFcdate(new Date());
                    tCourseScheduleService.save(courseschedule);
                    // 返回值
                    object.put("result", "success");
                }else{
                    // 返回值
                    object.put("result", "fail");
                }
            }else{
                object.put("result", "error");
                object.put("error", "请选择开始时间或者结束时间！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 修改开课时间信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改开课时间信息")
    @ResponseBody
    @RequestMapping(value = "/updatecourseschedule", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatecourseschedule(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FCRMID = jsonParam.getString("FCRMID");
        Integer FWeekday = jsonParam.getInteger("FWeekday");
        String FStartTime = jsonParam.getString("FStartTime");
        String FEndTime = jsonParam.getString("FEndTime");
        String FSession = jsonParam.getString("FSession");
        String FNote = jsonParam.getString("FNote");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            FCRMID = FCRMID == null ? "0" : ParamTools.getdeParam(FCRMID);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String CookiesLogincoursescheduleID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincoursescheduleID != null && !CookiesLogincoursescheduleID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLogincoursescheduleID);
            }
            if(!FStartTime.equals("") && !FEndTime.equals("") ){
                TCourseScheduleExample tCourseScheduleExample = new TCourseScheduleExample();
                TCourseScheduleExample.Criteria criteria = tCourseScheduleExample.createCriteria();
                criteria.andFcrmidEqualTo(Long.parseLong(FCRMID));
                criteria.andFstarttimeLessThanOrEqualTo(sdf.parse(FStartTime));
                criteria.andFendtimeGreaterThanOrEqualTo(sdf.parse(FStartTime));
                criteria.andFkeyidNotEqualTo(key);
                //如果新增的开始时间，在这个教室 已有时间的范围内 ，则冲突
                List<TCourseSchedule> courseScheduleList = tCourseScheduleService.findByExample(tCourseScheduleExample);
                if (courseScheduleList.size() == 0){
                    // 更新开课时间信息
                    TCourseSchedule courseschedule = new TCourseSchedule();
                    courseschedule.setFkeyid(key);
                    courseschedule.setFcrmid(Long.parseLong(FCRMID));
                    courseschedule.setFweekday(FWeekday);
                    courseschedule.setFstarttime(sdf.parse(FStartTime));
                    courseschedule.setFendtime(sdf.parse(FEndTime));
                    courseschedule.setFsession(FSession);
                    courseschedule.setFnote(FNote);
                    courseschedule.setFuid(Long.parseLong(uid));
                    courseschedule.setFudate(new Date());
                    tCourseScheduleService.update(courseschedule);
                    // 返回值
                    object.put("result", "success");
                }else{
                    object.put("result", "fail");
                }
            }else{
                object.put("result", "error");
                object.put("error", "请选择开始时间或者结束时间！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 删除开课时间信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除开课时间信息")
    @ResponseBody
    @RequestMapping(value = "/delcourseschedule", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delcourseschedule(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String FCOID = jsonParam.getString("FCOID");
        try {
            String CookiesLogincoursescheduleID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincoursescheduleID != null && !CookiesLogincoursescheduleID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            FCOID = FCOID == null ? "0" : (FCOID.equals("0") ? "0" : ParamTools.getdeParam(FCOID));

            TCourseOffering tCourseOffering = tCourseOfferingService.findById(Long.parseLong(id));
            if(tCourseOffering!=null){
                if (tCourseOffering.getFstatus() == 0){
                    tCourseScheduleService.deleteById(Long.parseLong(id));
                    // 返回值
                    object.put("result", "success");
                }else{
                    object.put("result", "error");
                    object.put("error","已开课，不能删除！");
                }
            }else{
                object.put("result", "error");
                object.put("error", "未获取到相关信息，请刷新后重试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
}