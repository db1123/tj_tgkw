package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TCourseTarget;
import fun.server.model.TCourseTargetExample;
import fun.server.model.TCourseTargetType;
import fun.server.service.TCourseTargetService;
import fun.server.service.TCourseTargetTypeService;
import fun.server.service.TUserService;
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
 * 课程目标管理 相关业务处理
 */
@RestController
@RequestMapping("/s/coursetarget")
public class CourseTarget {

    private final TCourseTargetService tCourseTargetService;
    private final TUserService tUserService;
    private final TCourseTargetTypeService tCourseTargetTypeService;
    public CourseTarget(TCourseTargetService tCourseTargetService, TUserService tUserService, TCourseTargetTypeService tCourseTargetTypeService) {
        this.tCourseTargetService = tCourseTargetService;
        this.tUserService = tUserService;
        this.tCourseTargetTypeService = tCourseTargetTypeService;
    }


    /**
     * 获取课程目标信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycoursetarget", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycoursetarget(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String FCourseID = jsonParam.getString("FCourseID");
        String name = jsonParam.getString("name");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray coursetarget_Array = new JSONArray();
            // 查询条件
            TCourseTargetExample TCourseTargetExample = new TCourseTargetExample();
            TCourseTargetExample.Criteria criteria = TCourseTargetExample.createCriteria();
            if(name!=null && !name.equals("")){
                criteria.andFnameLike("%"+name+"%");
            }
            FCourseID = FCourseID == null ? "0" : FCourseID.equals("0") ? "" : ParamTools.getdeParam(FCourseID);
            criteria.andFcourseidEqualTo(Long.parseLong(FCourseID));
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                TCourseTargetExample.setOrderByClause(orderSql.substring(1));
            } else {
                TCourseTargetExample.setOrderByClause("FNum asc");
            }
            PageInfo<TCourseTarget> coursetargetPageInfo = tCourseTargetService.findByExampleMapper(TCourseTargetExample, (page - 1) * results, results);
            List<TCourseTarget> coursetarget_list = coursetargetPageInfo.getList();
            for (TCourseTarget coursetarget : coursetarget_list) {
                JSONObject coursetarget_object = new JSONObject();
                coursetarget_object.put("key", ParamTools.getEnParam(coursetarget.getFkeyid().toString()));
                coursetarget_object.put("FCourseID", ParamTools.getEnParam(coursetarget.getFcourseid().toString()));
                if (dataall == 1) {
                    coursetarget_object.put("FName", coursetarget.getFname() == null ? "" : coursetarget.getFname());
                    coursetarget_object.put("FNum", coursetarget.getFnum() == null ? 1 : coursetarget.getFnum());


                    coursetarget_object.put("FType", coursetarget.getFtype() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(coursetarget.getFtype().toString()));
                    TCourseTargetType targetType = tCourseTargetTypeService.findById(coursetarget.getFtype());
                    coursetarget_object.put("FTypeName", targetType == null ? "" : targetType.getFname());

                    coursetarget_object.put("FCID", coursetarget.getFcid());
                    coursetarget_object.put("FUID", coursetarget.getFuid());
                    coursetarget_object.put("FCDATE", coursetarget.getFcdate());
                    coursetarget_object.put("FUDATE", coursetarget.getFudate());
                } else {
                    coursetarget_object.put("FName", "*****");
                    coursetarget_object.put("FNum", "*****");
                    coursetarget_object.put("FCID", "*****");
                    coursetarget_object.put("FUID", "*****");
                    coursetarget_object.put("FCDATE", "*****");
                    coursetarget_object.put("FUDATE", "*****");
                }

                coursetarget_object.put("FState", coursetarget.getFstate());
                coursetarget_Array.add(coursetarget_object);
            }
            // 返回值
            object.put("list", coursetarget_Array);
            object.put("total", coursetargetPageInfo.getTotal()); // 总行数
            object.put("page", coursetargetPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取课程目标信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatacoursetargetSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatacoursetargetSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FCourseID = jsonParam.getString("FCourseID");
        String FType = jsonParam.getString("FType");

        try {
            FCourseID = FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);
            FType = FType == null ? "0" : ParamTools.getdeParam(FType);
//            System.out.println("FCourseID:" + FCourseID);
            // 获取数据库记录
            JSONArray coursetarget_Array = new JSONArray();
            TCourseTargetExample coursetargetExample = new TCourseTargetExample();
            TCourseTargetExample.Criteria criteria = coursetargetExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }

            if(!FType.equals("0") && !FType.equals("-1")){
                criteria.andFtypeEqualTo(Long.parseLong(FType));
            }
            criteria.andFcourseidEqualTo(Long.parseLong(FCourseID));
            criteria.andFstateEqualTo(1);
            coursetargetExample.setOrderByClause("FType asc , fnum asc");
            List<TCourseTarget> coursetarget_list = tCourseTargetService.findByExample(coursetargetExample);
            for (TCourseTarget coursetarget : coursetarget_list) {
                JSONObject coursetarget_object = new JSONObject();
                coursetarget_object.put("id", ParamTools.getEnParam(coursetarget.getFkeyid().toString()));
                TCourseTargetType targetType = tCourseTargetTypeService.findById(coursetarget.getFtype());
                coursetarget_object.put("text", coursetarget.getFname());
                coursetarget_object.put("num", coursetarget.getFnum());
                coursetarget_object.put("ftype", targetType == null ? "" : targetType.getFname());
                coursetarget_Array.add(coursetarget_object);
            }
            // 返回值

            object.put("list", coursetarget_Array);
            object.put("result", "success");
            object.put("results", coursetarget_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 根据ID获取课程目标信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycoursetargetInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycoursetargetInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询课程目标信息
            TCourseTarget coursetarget = tCourseTargetService.findById(key);
            JSONObject coursetarget_object = new JSONObject();
            coursetarget_object.put("key", ParamTools.getEnParam(coursetarget.getFkeyid().toString()));
            coursetarget_object.put("FName", coursetarget.getFname());
            coursetarget_object.put("FCourseID", ParamTools.getEnParam(coursetarget.getFcourseid().toString()));
            coursetarget_object.put("FNum", coursetarget.getFnum() == null ? 1 : coursetarget.getFnum());
            coursetarget_object.put("FType", coursetarget.getFtype() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(coursetarget.getFtype().toString()));
            TCourseTargetType targetType = tCourseTargetTypeService.findById(coursetarget.getFtype());
            coursetarget_object.put("FTypeName", targetType == null ? "" : targetType.getFname());
            coursetarget_object.put("FCID", coursetarget.getFcid());
            coursetarget_object.put("FUID", coursetarget.getFuid());
            coursetarget_object.put("FCDATE", coursetarget.getFcdate());
            coursetarget_object.put("FUDATE", coursetarget.getFudate());
            coursetarget_object.put("FState", coursetarget.getFstate());
            // 返回值
            object.put("info", coursetarget_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加课程目标信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加课程目标信息")
    @ResponseBody
    @RequestMapping(value = "/addcoursetarget", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addcoursetarget(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FName = jsonParam.getString("FName");
        String FType = jsonParam.getString("FType");
        String FCourseID = jsonParam.getString("FCourseID");
        try {
            FCourseID = FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);
            FType = FType == null ? "0" : ParamTools.getdeParam(FType);

            TCourseTargetExample coursetargetExample2 = new TCourseTargetExample();
            TCourseTargetExample.Criteria criteria2 = coursetargetExample2.createCriteria();
            if(FType!=null && !FType.equals("") && !FType.equals("0")){
                criteria2.andFcourseidEqualTo(Long.parseLong(FCourseID)).andFtypeEqualTo(Long.parseLong(FType));
            }else{
                criteria2.andFcourseidEqualTo(Long.parseLong(FCourseID));
            }
            coursetargetExample2.setOrderByClause("FNum desc");

            List<TCourseTarget> targetList = tCourseTargetService.findByExample(coursetargetExample2);
            int fum = 1;
            if(targetList.size() > 0){
                fum = targetList.get(0).getFnum() + 1;
            }
            TCourseTargetExample coursetargetExample = new TCourseTargetExample();
            TCourseTargetExample.Criteria criteria = coursetargetExample.createCriteria();
            criteria.andFnameEqualTo(FName);
            criteria.andFcourseidEqualTo(Long.parseLong(FCourseID));
            List<TCourseTarget> levelList = tCourseTargetService.findByExample(coursetargetExample);
            if (levelList.size() == 0) {
                String CookiesLogincoursetargetID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogincoursetargetID != null && !CookiesLogincoursetargetID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogincoursetargetID);
                }
                // 新建课程目标信息
                TCourseTarget coursetarget = new TCourseTarget();
                coursetarget.setFname(FName);
                coursetarget.setFtype(Long.parseLong(FType));
                coursetarget.setFnum(fum);
                coursetarget.setFcourseid(Long.parseLong(FCourseID));
                coursetarget.setFcid(Long.parseLong(uid));
                coursetarget.setFcdate(new Date());
                tCourseTargetService.save(coursetarget);
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
     * 修改课程目标信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改课程目标信息")
    @ResponseBody
    @RequestMapping(value = "/updatecoursetarget", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatecoursetarget(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FName = jsonParam.getString("FName");
        String FType = jsonParam.getString("FType");
        String FCourseID = jsonParam.getString("FCourseID");
        try {
            FCourseID = FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);
            FType = FType == null ? "0" : ParamTools.getdeParam(FType);
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            TCourseTargetExample coursetargetExample = new TCourseTargetExample();
            TCourseTargetExample.Criteria criteria = coursetargetExample.createCriteria();
            criteria.andFnameEqualTo(FName);
            criteria.andFcourseidEqualTo(Long.parseLong(FCourseID));
            criteria.andFkeyidNotEqualTo(key);
            List<TCourseTarget> levelList = tCourseTargetService.findByExample(coursetargetExample);
            if (levelList.size() == 0) {
                String CookiesLogincoursetargetID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogincoursetargetID != null && !CookiesLogincoursetargetID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogincoursetargetID);
                }
                // 更新课程目标信息
                TCourseTarget coursetarget = new TCourseTarget();
                coursetarget.setFkeyid(key);
                coursetarget.setFname(FName);
                coursetarget.setFtype(Long.parseLong(FType));
//                coursetarget.setFcourseid(Long.parseLong(FCourseID));
                coursetarget.setFuid(Long.parseLong(uid));
                coursetarget.setFudate(new Date());
                tCourseTargetService.update(coursetarget);
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
     * 删除课程目标信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除课程目标信息")
    @ResponseBody
    @RequestMapping(value = "/delcoursetarget", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delcoursetarget(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            tCourseTargetService.deleteById(Long.parseLong(id));
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