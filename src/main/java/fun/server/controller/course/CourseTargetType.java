package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TCourseTargetType;
import fun.server.model.TCourseTargetTypeExample;
import fun.server.service.TCourseTargetTypeService;
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
 * 课程目标类型管理 相关业务处理
 */
@RestController
@RequestMapping("/s/coursetargettype")
public class CourseTargetType {

    private final TCourseTargetTypeService tCourseTargetTypeService;

    public CourseTargetType(TCourseTargetTypeService tCourseTargetTypeService) {
        this.tCourseTargetTypeService = tCourseTargetTypeService;
    }


    /**
     * 获取课程目标类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycoursetargettype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycoursetargettype(HttpServletRequest request)
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
            JSONArray coursetargettype_Array = new JSONArray();
            // 查询条件
            TCourseTargetTypeExample TCourseTargetTypeExample = new TCourseTargetTypeExample();
            TCourseTargetTypeExample.Criteria criteria = TCourseTargetTypeExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");

            }
            TCourseTargetTypeExample.setOrderByClause("FCDATE desc");
            PageInfo<TCourseTargetType> coursetargettypePageInfo = tCourseTargetTypeService.findByExampleMapper(TCourseTargetTypeExample, (page - 1) * results, results);
            List<TCourseTargetType> coursetargettype_list = coursetargettypePageInfo.getList();

            for (TCourseTargetType coursetargettype : coursetargettype_list) {
                JSONObject coursetargettype_object = new JSONObject();
                coursetargettype_object.put("key", ParamTools.getEnParam(coursetargettype.getFkeyid().toString()));
                if (dataall == 1) {
                    coursetargettype_object.put("FName", coursetargettype.getFname());
                    coursetargettype_object.put("FCID", coursetargettype.getFcid());
                    coursetargettype_object.put("FUID", coursetargettype.getFuid());
                    coursetargettype_object.put("FCDATE", coursetargettype.getFcdate());
                    coursetargettype_object.put("FUDATE", coursetargettype.getFudate());
                } else {
                    coursetargettype_object.put("FName", "*****");
                    coursetargettype_object.put("FCID", "*****");
                    coursetargettype_object.put("FUID", "*****");
                    coursetargettype_object.put("FCDATE", "*****");
                    coursetargettype_object.put("FUDATE", "*****");
                }

                coursetargettype_object.put("FState", coursetargettype.getFstate());
                coursetargettype_Array.add(coursetargettype_object);
            }
            // 返回值
            object.put("list", coursetargettype_Array);
            object.put("total", coursetargettypePageInfo.getTotal()); // 总行数
            object.put("page", coursetargettypePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取课程目标类型信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatacoursetargettypeSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatacoursetargettypeSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray coursetargettype_Array = new JSONArray();
            TCourseTargetTypeExample coursetargettypeExample = new TCourseTargetTypeExample();
            TCourseTargetTypeExample.Criteria criteria = coursetargettypeExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            coursetargettypeExample.setOrderByClause("fname asc");
            List<TCourseTargetType> coursetargettype_list = tCourseTargetTypeService.findByExample(coursetargettypeExample);
            for (TCourseTargetType coursetargettype : coursetargettype_list) {
                JSONObject coursetargettype_object = new JSONObject();
                coursetargettype_object.put("id", ParamTools.getEnParam(coursetargettype.getFkeyid().toString()));
                coursetargettype_object.put("text", coursetargettype.getFname());
                coursetargettype_Array.add(coursetargettype_object);
            }
            // 返回值

            object.put("list", coursetargettype_Array);
            object.put("result", "success");
            object.put("results", coursetargettype_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取课程目标类型信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatacoursetargettypeSelect_wu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatacoursetargettypeSelect_wu(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray coursetargettype_Array = new JSONArray();
            TCourseTargetTypeExample coursetargettypeExample = new TCourseTargetTypeExample();
            TCourseTargetTypeExample.Criteria criteria = coursetargettypeExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            coursetargettypeExample.setOrderByClause("fname asc");
            JSONObject coursetargettype_object2 = new JSONObject();
            coursetargettype_object2.put("id", ParamTools.getEnParam("-1"));
            coursetargettype_object2.put("text", "无");
            coursetargettype_Array.add(coursetargettype_object2);
            List<TCourseTargetType> coursetargettype_list = tCourseTargetTypeService.findByExample(coursetargettypeExample);
            for (TCourseTargetType coursetargettype : coursetargettype_list) {
                JSONObject coursetargettype_object = new JSONObject();
                coursetargettype_object.put("id", ParamTools.getEnParam(coursetargettype.getFkeyid().toString()));
                coursetargettype_object.put("text", coursetargettype.getFname());
                coursetargettype_Array.add(coursetargettype_object);
            }
            // 返回值

            object.put("list", coursetargettype_Array);
            object.put("result", "success");
            object.put("results", coursetargettype_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取课程目标类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycoursetargettypeInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycoursetargettypeInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询课程目标类型信息
            TCourseTargetType coursetargettype = tCourseTargetTypeService.findById(key);
            JSONObject coursetargettype_object = new JSONObject();
            coursetargettype_object.put("key", ParamTools.getEnParam(coursetargettype.getFkeyid().toString()));
            coursetargettype_object.put("FName", coursetargettype.getFname());
            coursetargettype_object.put("FCID", coursetargettype.getFcid());
            coursetargettype_object.put("FUID", coursetargettype.getFuid());
            coursetargettype_object.put("FCDATE", coursetargettype.getFcdate());
            coursetargettype_object.put("FUDATE", coursetargettype.getFudate());
            coursetargettype_object.put("FState", coursetargettype.getFstate());
            // 返回值
            object.put("info", coursetargettype_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加课程目标类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加部门信息")
    @ResponseBody
    @RequestMapping(value = "/addcoursetargettype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addcoursetargettype(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FName = jsonParam.getString("FName");

        try {
            if (repeaTCourseTargetType(0L, FName, "1") == 0) {
                String CookiesLogincoursetargettypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogincoursetargettypeID != null && !CookiesLogincoursetargettypeID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogincoursetargettypeID);
                }
                // 新建课程目标类型信息
                TCourseTargetType coursetargettype = new TCourseTargetType();
                coursetargettype.setFname(FName);
                coursetargettype.setFcid(Long.parseLong(uid));
                coursetargettype.setFcdate(new Date());
                tCourseTargetTypeService.save(coursetargettype);
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
     * 修改课程目标类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改部门信息")
    @ResponseBody
    @RequestMapping(value = "/updatecoursetargettype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatecoursetargettype(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FName = jsonParam.getString("FName");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            if (repeaTCourseTargetType(key, FName, "2") == 0) {
                String CookiesLogincoursetargettypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogincoursetargettypeID != null && !CookiesLogincoursetargettypeID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogincoursetargettypeID);
                }
                // 更新课程目标类型信息
                TCourseTargetType coursetargettype = new TCourseTargetType();
                coursetargettype.setFkeyid(key);
                coursetargettype.setFname(FName);
                coursetargettype.setFuid(Long.parseLong(uid));
                coursetargettype.setFudate(new Date());
                tCourseTargetTypeService.update(coursetargettype);
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
     * 删除课程目标类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除部门信息")
    @ResponseBody
    @RequestMapping(value = "/delcoursetargettype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delcoursetargettype(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogincoursetargettypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincoursetargettypeID != null && !CookiesLogincoursetargettypeID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));


            tCourseTargetTypeService.deleteById(Long.parseLong(id));
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
     * 变更课程目标类型信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statecoursetargettype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statecoursetargettype(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogincoursetargettypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincoursetargettypeID != null && !CookiesLogincoursetargettypeID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TCourseTargetType coursetargettype = new TCourseTargetType();
            coursetargettype.setFkeyid(Long.parseLong(id));
            coursetargettype.setFuid(Long.parseLong(uid));
            coursetargettype.setFudate(new Date());
            coursetargettype.setFstate(Integer.valueOf(state));
            tCourseTargetTypeService.update(coursetargettype);
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
     * 验证课程目标类型是否存在
     */
    private int repeaTCourseTargetType(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TCourseTargetTypeExample coursetargettypeExample = new TCourseTargetTypeExample();
            TCourseTargetTypeExample.Criteria criteria = coursetargettypeExample.createCriteria();
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
            List<TCourseTargetType> coursetargettypeList = tCourseTargetTypeService.findByExample(coursetargettypeExample);
            if (coursetargettypeList.size() == 0) {
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