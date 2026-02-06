package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TCourseAssessmentMethod;
import fun.server.model.TCourseAssessmentMethodExample;
import fun.server.service.TCourseAssessmentMethodService;
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
 * 考核方式管理 相关业务处理
 */
@RestController
@RequestMapping("/s/courseassessmentmethod")
public class CourseAssessmentMethod {

    private final TCourseAssessmentMethodService tCourseAssessmentMethodService;
    private final TUserService tUserService;

    public CourseAssessmentMethod(TCourseAssessmentMethodService tCourseAssessmentMethodService, TUserService tUserService) {
        this.tCourseAssessmentMethodService = tCourseAssessmentMethodService;
        this.tUserService = tUserService;
    }


    /**
     * 获取考核方式信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycourseassessmentmethod", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycourseassessmentmethod(HttpServletRequest request)
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
            JSONArray courseassessmentmethod_Array = new JSONArray();
            // 查询条件
            TCourseAssessmentMethodExample TCourseAssessmentMethodExample = new TCourseAssessmentMethodExample();
            TCourseAssessmentMethodExample.Criteria criteria = TCourseAssessmentMethodExample.createCriteria();
            if(name!=null && !name.equals("")){
                criteria.andFmethodnameLike("%"+name+"%");
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
                TCourseAssessmentMethodExample.setOrderByClause(orderSql.substring(1));
            } else {
                TCourseAssessmentMethodExample.setOrderByClause("FMethodName asc");
            }
            PageInfo<TCourseAssessmentMethod> courseassessmentmethodPageInfo = tCourseAssessmentMethodService.findByExampleMapper(TCourseAssessmentMethodExample, (page - 1) * results, results);
            List<TCourseAssessmentMethod> courseassessmentmethod_list = courseassessmentmethodPageInfo.getList();
            for (TCourseAssessmentMethod courseassessmentmethod : courseassessmentmethod_list) {
                JSONObject courseassessmentmethod_object = new JSONObject();
                courseassessmentmethod_object.put("key", ParamTools.getEnParam(courseassessmentmethod.getFkeyid().toString()));
                courseassessmentmethod_object.put("FCourseID", ParamTools.getEnParam(courseassessmentmethod.getFcourseid().toString()));
                if (dataall == 1) {
                    courseassessmentmethod_object.put("FMethodName", courseassessmentmethod.getFmethodname() == null ? "" : courseassessmentmethod.getFmethodname());
                    courseassessmentmethod_object.put("FWeight", courseassessmentmethod.getFweight() == null ? 0 : courseassessmentmethod.getFweight());
                    courseassessmentmethod_object.put("FCID", courseassessmentmethod.getFcid());
                    courseassessmentmethod_object.put("FUID", courseassessmentmethod.getFuid());
                    courseassessmentmethod_object.put("FCDATE", courseassessmentmethod.getFcdate());
                    courseassessmentmethod_object.put("FUDATE", courseassessmentmethod.getFudate());
                } else {
                    courseassessmentmethod_object.put("FMethodName", courseassessmentmethod.getFmethodname() == null ? "" : courseassessmentmethod.getFmethodname());
                    courseassessmentmethod_object.put("FWeight", courseassessmentmethod.getFweight() == null ? 0 : courseassessmentmethod.getFweight());
                    courseassessmentmethod_object.put("FCID", "*****");
                    courseassessmentmethod_object.put("FUID", "*****");
                    courseassessmentmethod_object.put("FCDATE", "*****");
                    courseassessmentmethod_object.put("FUDATE", "*****");
                }

                courseassessmentmethod_object.put("FState", courseassessmentmethod.getFstate());
                courseassessmentmethod_Array.add(courseassessmentmethod_object);
            }
            // 返回值
            object.put("list", courseassessmentmethod_Array);
            object.put("total", courseassessmentmethodPageInfo.getTotal()); // 总行数
            object.put("page", courseassessmentmethodPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取考核方式信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatacourseassessmentmethodSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatacourseassessmentmethodSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");


        try {
            // 获取数据库记录
            JSONArray courseassessmentmethod_Array = new JSONArray();
            TCourseAssessmentMethodExample courseassessmentmethodExample = new TCourseAssessmentMethodExample();
            TCourseAssessmentMethodExample.Criteria criteria = courseassessmentmethodExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFmethodnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            courseassessmentmethodExample.setOrderByClause("fname asc");
            List<TCourseAssessmentMethod> courseassessmentmethod_list = tCourseAssessmentMethodService.findByExample(courseassessmentmethodExample);
            for (TCourseAssessmentMethod courseassessmentmethod : courseassessmentmethod_list) {
                JSONObject courseassessmentmethod_object = new JSONObject();
                courseassessmentmethod_object.put("id", ParamTools.getEnParam(courseassessmentmethod.getFkeyid().toString()));
                courseassessmentmethod_object.put("text", courseassessmentmethod.getFmethodname());
                courseassessmentmethod_Array.add(courseassessmentmethod_object);
            }
            // 返回值

            object.put("list", courseassessmentmethod_Array);
            object.put("result", "success");
            object.put("results", courseassessmentmethod_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 根据ID获取考核方式信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycourseassessmentmethodInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycourseassessmentmethodInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询考核方式信息
            TCourseAssessmentMethod courseassessmentmethod = tCourseAssessmentMethodService.findById(key);
            JSONObject courseassessmentmethod_object = new JSONObject();
            courseassessmentmethod_object.put("key", ParamTools.getEnParam(courseassessmentmethod.getFkeyid().toString()));
            courseassessmentmethod_object.put("FMethodName", courseassessmentmethod.getFmethodname() == null ? "" : courseassessmentmethod.getFmethodname());
            courseassessmentmethod_object.put("FWeight", courseassessmentmethod.getFweight() == null ? 0 : courseassessmentmethod.getFweight());
            courseassessmentmethod_object.put("FCourseID", ParamTools.getEnParam(courseassessmentmethod.getFcourseid().toString()));
            courseassessmentmethod_object.put("FCID", courseassessmentmethod.getFcid());
            courseassessmentmethod_object.put("FUID", courseassessmentmethod.getFuid());
            courseassessmentmethod_object.put("FCDATE", courseassessmentmethod.getFcdate());
            courseassessmentmethod_object.put("FUDATE", courseassessmentmethod.getFudate());
            courseassessmentmethod_object.put("FState", courseassessmentmethod.getFstate());
            // 返回值
            object.put("info", courseassessmentmethod_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加考核方式信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加考核方式信息")
    @ResponseBody
    @RequestMapping(value = "/addcourseassessmentmethod", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addcourseassessmentmethod(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FMethodName = jsonParam.getString("FMethodName");
        float FWeight = jsonParam.getFloat("FWeight");
        String FCourseID = jsonParam.getString("FCourseID");
        try {
            FCourseID = FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);

            float FMethodWeight2 = 0;
            TCourseAssessmentMethodExample tCourseAssessmentMethodExample = new TCourseAssessmentMethodExample();
            TCourseAssessmentMethodExample.Criteria criteria2 = tCourseAssessmentMethodExample.createCriteria();
            criteria2.andFcourseidEqualTo(Long.parseLong(FCourseID));
            List<TCourseAssessmentMethod> courseAssessmentMethodList = tCourseAssessmentMethodService.findByExample(tCourseAssessmentMethodExample);
            if (courseAssessmentMethodList.size() > 0) {
                for (TCourseAssessmentMethod tCourseAssessmentMethod : courseAssessmentMethodList) {
                    FMethodWeight2 += tCourseAssessmentMethod.getFweight() == null ? 0 : tCourseAssessmentMethod.getFweight();
                }
            }
            if (100 - FMethodWeight2 - FWeight >= 0) {
                TCourseAssessmentMethodExample courseassessmentmethodExample = new TCourseAssessmentMethodExample();
                TCourseAssessmentMethodExample.Criteria criteria = courseassessmentmethodExample.createCriteria();
                criteria.andFmethodnameEqualTo(FMethodName);
                criteria.andFcourseidEqualTo(Long.parseLong(FCourseID));
                List<TCourseAssessmentMethod> levelList = tCourseAssessmentMethodService.findByExample(courseassessmentmethodExample);
                if (levelList.size() == 0) {
                    String CookiesLogincourseassessmentmethodID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                    String uid = ""; // 当前登录用户ID
                    if (CookiesLogincourseassessmentmethodID != null && !CookiesLogincourseassessmentmethodID.equals("")) {
                        uid = ParamTools.getdeParam(CookiesLogincourseassessmentmethodID);
                    }
                    // 新建考核方式信息
                    TCourseAssessmentMethod courseassessmentmethod = new TCourseAssessmentMethod();
                    courseassessmentmethod.setFmethodname(FMethodName);
                    courseassessmentmethod.setFweight(FWeight);
                    courseassessmentmethod.setFcourseid(Long.parseLong(FCourseID));
                    courseassessmentmethod.setFcid(Long.parseLong(uid));
                    courseassessmentmethod.setFcdate(new Date());
                    tCourseAssessmentMethodService.save(courseassessmentmethod);
                    // 返回值
                    object.put("result", "success");
                } else {
                    // 返回值
                    object.put("result", "fail");
                }
            }else {
                object.put("result", "error2");
                object.put("error", "总权重值超出了100的上限");
                object.put("FMethodWeight2", 100 - FMethodWeight2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 修改考核方式信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改考核方式信息")
    @ResponseBody
    @RequestMapping(value = "/updatecourseassessmentmethod", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatecourseassessmentmethod(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FMethodName = jsonParam.getString("FMethodName");
        float FWeight = jsonParam.getFloat("FWeight");
        String FCourseID = jsonParam.getString("FCourseID");
        try {
            FCourseID = FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);

            float FMethodWeight2 = 0;
            TCourseAssessmentMethodExample tCourseAssessmentMethodExample = new TCourseAssessmentMethodExample();
            TCourseAssessmentMethodExample.Criteria criteria2 = tCourseAssessmentMethodExample.createCriteria();
            criteria2.andFcourseidEqualTo(Long.parseLong(FCourseID)).andFkeyidNotEqualTo(Long.parseLong(id));
            List<TCourseAssessmentMethod> courseAssessmentMethodList = tCourseAssessmentMethodService.findByExample(tCourseAssessmentMethodExample);
            if (courseAssessmentMethodList.size() > 0) {
                for (TCourseAssessmentMethod tCourseAssessmentMethod : courseAssessmentMethodList) {
                    FMethodWeight2 += tCourseAssessmentMethod.getFweight() == null ? 0 : tCourseAssessmentMethod.getFweight();
                }
            }
            if (100 - FMethodWeight2 - FWeight >= 0) {
                TCourseAssessmentMethodExample courseassessmentmethodExample = new TCourseAssessmentMethodExample();
                TCourseAssessmentMethodExample.Criteria criteria = courseassessmentmethodExample.createCriteria();
                criteria.andFmethodnameEqualTo(FMethodName);
                criteria.andFcourseidEqualTo(Long.parseLong(FCourseID));
                criteria.andFkeyidNotEqualTo(key);
                List<TCourseAssessmentMethod> levelList = tCourseAssessmentMethodService.findByExample(courseassessmentmethodExample);
                if (levelList.size() == 0) {
                    String CookiesLogincourseassessmentmethodID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                    String uid = ""; // 当前登录用户ID
                    if (CookiesLogincourseassessmentmethodID != null && !CookiesLogincourseassessmentmethodID.equals("")) {
                        uid = ParamTools.getdeParam(CookiesLogincourseassessmentmethodID);
                    }
                    // 更新考核方式信息
                    TCourseAssessmentMethod courseassessmentmethod = new TCourseAssessmentMethod();
                    courseassessmentmethod.setFkeyid(key);
                    courseassessmentmethod.setFmethodname(FMethodName);
                    courseassessmentmethod.setFweight(FWeight);
                    courseassessmentmethod.setFuid(Long.parseLong(uid));
                    courseassessmentmethod.setFudate(new Date());
                    tCourseAssessmentMethodService.update(courseassessmentmethod);
                    // 返回值
                    object.put("result", "success");
                } else {
                    // 返回值
                    object.put("result", "fail");
                }
            }else {
                object.put("result", "error2");
                object.put("error", "总权重值超出了100的上限");
                object.put("FMethodWeight2", 100 - FMethodWeight2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 删除考核方式信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除考核方式信息")
    @ResponseBody
    @RequestMapping(value = "/delcourseassessmentmethod", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delcourseassessmentmethod(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            tCourseAssessmentMethodService.deleteById(Long.parseLong(id));
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
     * 根据ID获取能力等级考核方式信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryassessmentmethodqz", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryassessmentmethodqz(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            float FMethodWeight = 0;

            TCourseAssessmentMethodExample courseassessmentmethodExample = new TCourseAssessmentMethodExample();
            TCourseAssessmentMethodExample.Criteria criteria = courseassessmentmethodExample.createCriteria();
            criteria.andFcourseidEqualTo(key);

            List<TCourseAssessmentMethod> assessmentMethodList = tCourseAssessmentMethodService.findByExample(courseassessmentmethodExample);
            if (assessmentMethodList.size() > 0) {
                for (TCourseAssessmentMethod tCourseAssessmentMethod : assessmentMethodList) {
                    FMethodWeight += tCourseAssessmentMethod.getFweight() == null ? 0 : tCourseAssessmentMethod.getFweight();
                }
            }
            object.put("FMethodWeight", (100 - FMethodWeight) < 0 ? 0 : (100 - FMethodWeight));
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


}