package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TClassroom;
import fun.server.model.TClassroomExample;
import fun.server.model.TCourseSchedule;
import fun.server.model.TCourseScheduleExample;
import fun.server.service.TClassroomService;
import fun.server.service.TCourseScheduleService;
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
 * 教室管理 相关业务处理
 */
@RestController
@RequestMapping("/s/classroom")
public class Classroom {

    private final TClassroomService tClassroomService;

    private final TCourseScheduleService tCourseScheduleService;

    public Classroom(TClassroomService tClassroomService, TCourseScheduleService tCourseScheduleService) {
        this.tClassroomService = tClassroomService;
        this.tCourseScheduleService = tCourseScheduleService;
    }

    /**
     * 获取教室信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryclassroom", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryclassroom(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("FName");
        String FNo = jsonParam.getString("FNo");
        String FClassroom = jsonParam.getString("FClassroom");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray classroom_Array = new JSONArray();
            // 查询条件
            TClassroomExample TClassroomExample = new TClassroomExample();
            TClassroomExample.Criteria criteria = TClassroomExample.createCriteria();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }

             if (FNo != null && !FNo.equals("")) {
                criteria.andFnoLike("%" + FNo + "%");
            }

             if (FClassroom != null && !FClassroom.equals("")) {
                criteria.andFclassroomLike("%" + FClassroom + "%");
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
                TClassroomExample.setOrderByClause(orderSql.substring(1));
            } else {
                TClassroomExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TClassroom> classroomPageInfo = tClassroomService.findByExampleMapper(TClassroomExample, (page - 1) * results, results);
            List<TClassroom> classroom_list = classroomPageInfo.getList();

            for (TClassroom classroom : classroom_list) {
                JSONObject classroom_object = new JSONObject();
                classroom_object.put("key", ParamTools.getEnParam(classroom.getFkeyid().toString()));
                if (dataall == 1) {
                    classroom_object.put("FName", classroom.getFname() == null ? "" : classroom.getFname());
                    classroom_object.put("FNo", classroom.getFno() == null ? "" :classroom.getFno());
                    classroom_object.put("FClassroom", classroom.getFclassroom() == null ? "" : classroom.getFclassroom());
                    classroom_object.put("FCapacity", classroom.getFcapacity() == null ? 0 : classroom.getFcapacity());
                    classroom_object.put("FEquipment", classroom.getFequipment() == null ? "" : classroom.getFequipment());
                    classroom_object.put("FNote", classroom.getFnote() == null ? "" :classroom.getFnote());
                    classroom_object.put("FCID", classroom.getFcid());
                    classroom_object.put("FUID", classroom.getFuid());
                    classroom_object.put("FCDATE", classroom.getFcdate());
                    classroom_object.put("FUDATE", classroom.getFudate());
                } else {
                    classroom_object.put("FCID", "*****");
                    classroom_object.put("FUID", "*****");
                    classroom_object.put("FCDATE", "*****");
                    classroom_object.put("FUDATE", "*****");
                }

                classroom_object.put("FState", classroom.getFstate());
                classroom_Array.add(classroom_object);
            }
            // 返回值
            object.put("list", classroom_Array);
            object.put("total", classroomPageInfo.getTotal()); // 总行数
            object.put("page", classroomPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取教室信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataclassroomSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataclassroomSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray classroom_Array = new JSONArray();
            TClassroomExample classroomExample = new TClassroomExample();
            if (search != null && !search.equals("")) {
                classroomExample.or().andFnameLike("%" + search + "%").andFstateEqualTo(1);
                classroomExample.or().andFnoLike("%" + search + "%").andFstateEqualTo(1);
                classroomExample.or().andFclassroomLike("%" + search + "%").andFstateEqualTo(1);
            }
            classroomExample.setOrderByClause("FCDATE desc,FName asc");
            List<TClassroom> classroom_list = tClassroomService.findByExample(classroomExample);
            for (TClassroom classroom : classroom_list) {
                JSONObject classroom_object = new JSONObject();
                classroom_object.put("id", ParamTools.getEnParam(classroom.getFkeyid().toString()));
                classroom_object.put("text", classroom.getFno() + "_" + classroom.getFname()+ "_" + classroom.getFclassroom());
                classroom_Array.add(classroom_object);
            }
            // 返回值
            object.put("list", classroom_Array);
            object.put("results", classroom_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取教室信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataclassroomSelectall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataclassroomSelectall(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray classroom_Array = new JSONArray();
            TClassroomExample classroomExample = new TClassroomExample();
            if (search != null && !search.equals("")) {
                classroomExample.or().andFnameLike("%" + search + "%").andFstateEqualTo(1);
                classroomExample.or().andFnoLike("%" + search + "%").andFstateEqualTo(1);
                classroomExample.or().andFclassroomLike("%" + search + "%").andFstateEqualTo(1);
            }
            classroomExample.setOrderByClause("FCDATE desc");
            JSONObject classroom_object = new JSONObject();
            classroom_object.put("id", "-1");
            classroom_object.put("text", "全部");
            classroom_Array.add(classroom_object);
            List<TClassroom> classroom_list = tClassroomService.findByExample(classroomExample);
            for (TClassroom classroom : classroom_list) {
                classroom_object = new JSONObject();
                classroom_object.put("id", ParamTools.getEnParam(classroom.getFkeyid().toString()));
                classroom_object.put("text", classroom.getFno() + "_" + classroom.getFname()+ "_" + classroom.getFclassroom());
                classroom_Array.add(classroom_object);
            }
            // 返回值

            object.put("list", classroom_Array);
            object.put("results", classroom_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取教室信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryclassroomInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryclassroomInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询教室信息
            TClassroom classroom = tClassroomService.findById(key);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            JSONObject classroom_object = new JSONObject();
            classroom_object.put("key", ParamTools.getEnParam(classroom.getFkeyid().toString()));
            classroom_object.put("FName", classroom.getFname() == null ? "" : classroom.getFname());
            classroom_object.put("FNo", classroom.getFno() == null ? "" :classroom.getFno());
            classroom_object.put("FNote", classroom.getFnote() == null ? "" :classroom.getFnote());
            classroom_object.put("FClassroom", classroom.getFclassroom() == null ? -1 : classroom.getFclassroom());
            classroom_object.put("FCapacity", classroom.getFcapacity() == null ? 0 : classroom.getFcapacity());
            classroom_object.put("FEquipment", classroom.getFequipment() == null ? -1 : classroom.getFequipment());
            classroom_object.put("FCID", classroom.getFcid());
            classroom_object.put("FUID", classroom.getFuid());
            classroom_object.put("FCDATE", classroom.getFcdate());
            classroom_object.put("FUDATE", classroom.getFudate());
            classroom_object.put("FState", classroom.getFstate());
            // 返回值
            object.put("info", classroom_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加教室信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加教室信息")
    @ResponseBody
    @RequestMapping(value = "/addclassroom", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addclassroom(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FName = jsonParam.getString("FName");
        String FNo = jsonParam.getString("FNo");
        String FClassroom = jsonParam.getString("FClassroom");
        Integer FCapacity = jsonParam.getInteger("FCapacity");
        String FEquipment = jsonParam.getString("FEquipment");
        try {

            if (repeaTClassroom(0L, FNo, "1") == 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String CookiesLoginclassroomID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginclassroomID != null && !CookiesLoginclassroomID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginclassroomID);
                }
                // 新建教室信息
                TClassroom classroom = new TClassroom();
                classroom.setFname(FName);
                classroom.setFno(FNo);
                classroom.setFclassroom(FClassroom);
                classroom.setFcapacity(FCapacity);
                classroom.setFequipment(FEquipment);
                classroom.setFcid(Long.parseLong(uid));
                classroom.setFcdate(new Date());
                tClassroomService.save(classroom);
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
     * 修改教室信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改教室信息")
    @ResponseBody
    @RequestMapping(value = "/updateclassroom", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateclassroom(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FName = jsonParam.getString("FName");
        String FNo = jsonParam.getString("FNo");
        String FClassroom = jsonParam.getString("FClassroom");
        Integer FCapacity = jsonParam.getInteger("FCapacity");
        String FEquipment = jsonParam.getString("FEquipment");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            if (repeaTClassroom(key, FNo, "2") == 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String CookiesLoginclassroomID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginclassroomID != null && !CookiesLoginclassroomID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginclassroomID);
                }
                // 更新教室信息
                TClassroom classroom = new TClassroom();
                classroom.setFkeyid(key);
                classroom.setFname(FName);
                classroom.setFno(FNo);
                classroom.setFclassroom(FClassroom);
                classroom.setFcapacity(FCapacity);
                classroom.setFequipment(FEquipment);
                classroom.setFuid(Long.parseLong(uid));
                classroom.setFudate(new Date());
                tClassroomService.update(classroom);
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
     * 删除教室信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除教室信息")
    @ResponseBody
    @RequestMapping(value = "/delclassroom", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delclassroom(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));


            TCourseScheduleExample tCourseScheduleExample =new TCourseScheduleExample();
            tCourseScheduleExample.createCriteria().andFcrmidEqualTo(Long.parseLong(id));

            List<TCourseSchedule> scheduleList = tCourseScheduleService.findByExample(tCourseScheduleExample);
            if (!scheduleList.isEmpty()){
                object.put("result", "error");
                object.put("error", "教室信息已被使用，不能删除！");
            }else{
                tClassroomService.deleteById(Long.parseLong(id));
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
     * 变更教室信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/stateclassroom", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stateclassroom(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginclassroomID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginclassroomID != null && !CookiesLoginclassroomID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TClassroom classroom = new TClassroom();
            classroom.setFkeyid(Long.parseLong(id));
            classroom.setFuid(Long.parseLong(uid));
            classroom.setFudate(new Date());
            classroom.setFstate(Integer.valueOf(state));
            tClassroomService.update(classroom);
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
     * 验证教室是否存在
     */
    private int repeaTClassroom(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TClassroomExample classroomExample = new TClassroomExample();
            TClassroomExample.Criteria criteria = classroomExample.createCriteria();
            if (ftype.equals("2")) { // 修改
                if (id != null) {
//                    criteria.andFkeyidEqualTo((id));
                    criteria.andFkeyidNotEqualTo(id);
                }
                if (name != null && !name.equals("")) {
                    criteria.andFnoEqualTo(name);
                }
            } else { // 新增
                if (name != null && !name.equals("")) {
                    criteria.andFnoEqualTo(name);
                }
            }
            List<TClassroom> classroomList = tClassroomService.findByExample(classroomExample);
            if (classroomList.size() == 0) {
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