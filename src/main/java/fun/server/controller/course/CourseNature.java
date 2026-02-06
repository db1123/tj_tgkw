package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TCourse;
import fun.server.model.TCourseExample;
import fun.server.model.TCourseNature;
import fun.server.model.TCourseNatureExample;
import fun.server.service.TCourseNatureService;
import fun.server.service.TCourseService;
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
 * 课程性质管理 相关业务处理
 */
@RestController
@RequestMapping("/s/coursenature")
public class CourseNature {

    private final TCourseNatureService tCourseNatureService;
    private final TUserService tUserService;

    private final TCourseService tCourseService;

    public CourseNature(TCourseNatureService tCourseNatureService, TUserService tUserService, TCourseService tCourseService) {
        this.tCourseNatureService = tCourseNatureService;
        this.tUserService = tUserService;
        this.tCourseService = tCourseService;
    }


    /**
     * 获取课程性质信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycoursenature", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycoursenature(HttpServletRequest request)
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
            JSONArray coursenature_Array = new JSONArray();
            // 查询条件
            TCourseNatureExample TCourseNatureExample = new TCourseNatureExample();
            TCourseNatureExample.Criteria criteria = TCourseNatureExample.createCriteria();
            if(name!=null && !name.equals("")){
                criteria.andFnameLike("%"+name+"%");
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
                TCourseNatureExample.setOrderByClause(orderSql.substring(1));
            } else {
                TCourseNatureExample.setOrderByClause("FNum asc");
            }
            PageInfo<TCourseNature> coursenaturePageInfo = tCourseNatureService.findByExampleMapper(TCourseNatureExample, (page - 1) * results, results);
            List<TCourseNature> coursenature_list = coursenaturePageInfo.getList();
            for (TCourseNature coursenature : coursenature_list) {
                JSONObject coursenature_object = new JSONObject();
                coursenature_object.put("key", ParamTools.getEnParam(coursenature.getFkeyid().toString()));

                if (dataall == 1) {
                    coursenature_object.put("FName", coursenature.getFname() == null ? "" : coursenature.getFname());
                    coursenature_object.put("FCID", coursenature.getFcid());
                    coursenature_object.put("FUID", coursenature.getFuid());
                    coursenature_object.put("FCDATE", coursenature.getFcdate());
                    coursenature_object.put("FUDATE", coursenature.getFudate());
                } else {
                    coursenature_object.put("FName", "*****");
                    coursenature_object.put("FNum", "*****");
                    coursenature_object.put("FCID", "*****");
                    coursenature_object.put("FUID", "*****");
                    coursenature_object.put("FCDATE", "*****");
                    coursenature_object.put("FUDATE", "*****");
                }

                coursenature_object.put("FState", coursenature.getFstate());
                coursenature_Array.add(coursenature_object);
            }
            // 返回值
            object.put("list", coursenature_Array);
            object.put("total", coursenaturePageInfo.getTotal()); // 总行数
            object.put("page", coursenaturePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取课程性质信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatacoursenatureSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatacoursenatureSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        JSONObject jsonParam = ParamTools.getParameters(request);
        String  ftype = request.getParameter("ftype");

        try {

            // 获取数据库记录
            JSONArray coursenature_Array = new JSONArray();
            TCourseNatureExample coursenatureExample = new TCourseNatureExample();
            TCourseNatureExample.Criteria criteria = coursenatureExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            if(ftype!=null && !ftype.equals("")&& ftype.equals("2")){
                JSONObject coursenature_object = new JSONObject();
                coursenature_object.put("id", ParamTools.getEnParam("-1"));
                coursenature_object.put("text","全部");
                coursenature_Array.add(coursenature_object);
            }
            criteria.andFstateEqualTo(1);
            coursenatureExample.setOrderByClause("FName asc");
            List<TCourseNature> coursenature_list = tCourseNatureService.findByExample(coursenatureExample);
            for (TCourseNature coursenature : coursenature_list) {
                JSONObject coursenature_object = new JSONObject();
                coursenature_object.put("id", ParamTools.getEnParam(coursenature.getFkeyid().toString()));
                coursenature_object.put("text", coursenature.getFname());
                coursenature_Array.add(coursenature_object);
            }
            // 返回值
            object.put("list", coursenature_Array);
            object.put("result", "success");
            object.put("results", coursenature_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 根据ID获取课程性质信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycoursenatureInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycoursenatureInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询课程性质信息
            TCourseNature coursenature = tCourseNatureService.findById(key);
            JSONObject coursenature_object = new JSONObject();
            coursenature_object.put("key", ParamTools.getEnParam(coursenature.getFkeyid().toString()));
            coursenature_object.put("FName", coursenature.getFname());


            coursenature_object.put("FCID", coursenature.getFcid());
            coursenature_object.put("FUID", coursenature.getFuid());
            coursenature_object.put("FCDATE", coursenature.getFcdate());
            coursenature_object.put("FUDATE", coursenature.getFudate());
            coursenature_object.put("FState", coursenature.getFstate());
            // 返回值
            object.put("info", coursenature_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加课程性质信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加课程性质信息")
    @ResponseBody
    @RequestMapping(value = "/addcoursenature", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addcoursenature(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FName = jsonParam.getString("FName");

        try {

            TCourseNatureExample coursenatureExample2 = new TCourseNatureExample();
            TCourseNatureExample.Criteria criteria2 = coursenatureExample2.createCriteria();

            coursenatureExample2.setOrderByClause("FName desc");


            TCourseNatureExample coursenatureExample = new TCourseNatureExample();
            TCourseNatureExample.Criteria criteria = coursenatureExample.createCriteria();
            criteria.andFnameEqualTo(FName);
            List<TCourseNature> levelList = tCourseNatureService.findByExample(coursenatureExample);
            if (levelList.size() == 0) {
                String CookiesLogincoursenatureID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogincoursenatureID != null && !CookiesLogincoursenatureID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogincoursenatureID);
                }
                // 新建课程性质信息
                TCourseNature coursenature = new TCourseNature();
                coursenature.setFname(FName);
                coursenature.setFcid(Long.parseLong(uid));
                coursenature.setFcdate(new Date());
                tCourseNatureService.save(coursenature);
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
     * 修改课程性质信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改课程性质信息")
    @ResponseBody
    @RequestMapping(value = "/updatecoursenature", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatecoursenature(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FName = jsonParam.getString("FName");

        try {

            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            TCourseNatureExample coursenatureExample = new TCourseNatureExample();
            TCourseNatureExample.Criteria criteria = coursenatureExample.createCriteria();
            criteria.andFnameEqualTo(FName);

            criteria.andFkeyidNotEqualTo(key);
            List<TCourseNature> levelList = tCourseNatureService.findByExample(coursenatureExample);
            if (levelList.size() == 0) {
                String CookiesLogincoursenatureID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogincoursenatureID != null && !CookiesLogincoursenatureID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogincoursenatureID);
                }
                // 更新课程性质信息
                TCourseNature coursenature = new TCourseNature();
                coursenature.setFkeyid(key);
                coursenature.setFname(FName);
//                coursenature.setFcourseid(Long.parseLong(FCourseID));
                coursenature.setFuid(Long.parseLong(uid));
                coursenature.setFudate(new Date());
                tCourseNatureService.update(coursenature);
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
     * 删除课程性质信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除课程性质信息")
    @ResponseBody
    @RequestMapping(value = "/delcoursenature", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delcoursenature(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TCourseExample tCourseExample = new TCourseExample();
            TCourseExample.Criteria criteria = tCourseExample.createCriteria();
            criteria.andFcnatureEqualTo(Long.parseLong(id));
            List<TCourse> tCourseList = tCourseService.findByExample(tCourseExample);
            if (tCourseList.size() == 0) {
                tCourseNatureService.deleteById(Long.parseLong(id));
                // 返回值
                object.put("result", "success");
            }else{
                object.put("result", "error");
                object.put("error", "已被使用不能删除！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


}