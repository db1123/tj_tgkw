package fun.server.controller.student;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TCollege;
import fun.server.model.TCollegeExample;
import fun.server.service.TCollegeService;
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
 * 学院管理 相关业务处理
 */
@RestController
@RequestMapping("/s/college")
public class College {

    private final TCollegeService tCollegeService;

    public College(TCollegeService tCollegeService) {
        this.tCollegeService = tCollegeService;
    }


    /**
     * 获取学院信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycollege", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycollege(HttpServletRequest request)
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
            JSONArray college_Array = new JSONArray();
            // 查询条件
            TCollegeExample TCollegeExample = new TCollegeExample();
            TCollegeExample.Criteria criteria = TCollegeExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFcollegenameLike("%" + name + "%");

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
                TCollegeExample.setOrderByClause(orderSql.substring(1));
            } else {
                TCollegeExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TCollege> collegePageInfo = tCollegeService.findByExampleMapper(TCollegeExample, (page - 1) * results, results);
            List<TCollege> college_list = collegePageInfo.getList();

            for (TCollege college : college_list) {
                JSONObject college_object = new JSONObject();
                college_object.put("key", ParamTools.getEnParam(college.getFkeyid().toString()));
                if (dataall == 1) {
                    college_object.put("FCollegeName", college.getFcollegename() == null ? "" : college.getFcollegename());
                    college_object.put("FCID", college.getFcid());
                    college_object.put("FUID", college.getFuid());
                    college_object.put("FCDATE", college.getFcdate());
                    college_object.put("FUDATE", college.getFudate());
                } else {
                    college_object.put("FName", "*****");
                    college_object.put("FCID", "*****");
                    college_object.put("FUID", "*****");
                    college_object.put("FCDATE", "*****");
                    college_object.put("FUDATE", "*****");
                }

                college_object.put("FState", college.getFstate());
                college_Array.add(college_object);
            }
            // 返回值
            object.put("list", college_Array);
            object.put("total", collegePageInfo.getTotal()); // 总行数
            object.put("page", collegePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取学院信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatacollegeSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatacollegeSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray college_Array = new JSONArray();
            TCollegeExample collegeExample = new TCollegeExample();
            TCollegeExample.Criteria criteria = collegeExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFcollegenameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            collegeExample.setOrderByClause("FCDATE desc,FCollegeName asc");
            List<TCollege> college_list = tCollegeService.findByExample(collegeExample);
            for (TCollege college : college_list) {
                JSONObject college_object = new JSONObject();
                college_object.put("id", ParamTools.getEnParam(college.getFkeyid().toString()));
                college_object.put("text", college.getFcollegename());
                college_Array.add(college_object);
            }
            // 返回值
            object.put("list", college_Array);
            object.put("results", college_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取学院信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatacollegeSelectall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatacollegeSelectall(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray college_Array = new JSONArray();
            TCollegeExample collegeExample = new TCollegeExample();
            TCollegeExample.Criteria criteria = collegeExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFcollegenameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            collegeExample.setOrderByClause("FCDATE desc");
            JSONObject college_object = new JSONObject();
            college_object.put("id", "-1");
            college_object.put("text","全部");
            college_Array.add(college_object);
            List<TCollege> college_list = tCollegeService.findByExample(collegeExample);
            for (TCollege college : college_list) {
                college_object = new JSONObject();
                college_object.put("id", ParamTools.getEnParam(college.getFkeyid().toString()));
                college_object.put("text", college.getFcollegename());
                college_Array.add(college_object);
            }
            // 返回值

            object.put("list", college_Array);
            object.put("results", college_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取学院信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycollegeInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycollegeInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询学院信息
            TCollege college = tCollegeService.findById(key);
            JSONObject college_object = new JSONObject();
            college_object.put("key", ParamTools.getEnParam(college.getFkeyid().toString()));
            college_object.put("FCollegeName", college.getFcollegename() == null ? "" : college.getFcollegename());
            college_object.put("FCID", college.getFcid());
            college_object.put("FUID", college.getFuid());
            college_object.put("FCDATE", college.getFcdate());
            college_object.put("FUDATE", college.getFudate());
            college_object.put("FState", college.getFstate());
            // 返回值
            object.put("info", college_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加学院信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加学院信息")
    @ResponseBody
    @RequestMapping(value = "/addcollege", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addcollege(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FCollegeName  = jsonParam.getString("FCollegeName");

        try {
            if (repeaTCollege(0L, FCollegeName , "1") == 0) {
                String CookiesLogincollegeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogincollegeID != null && !CookiesLogincollegeID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogincollegeID);
                }
                // 新建学院信息
                TCollege college = new TCollege();
                college.setFcollegename(FCollegeName );
                college.setFcid(Long.parseLong(uid));
                college.setFcdate(new Date());
                tCollegeService.save(college);
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
     * 修改学院信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改学院信息")
    @ResponseBody
    @RequestMapping(value = "/updatecollege", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatecollege(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FCollegeName  = jsonParam.getString("FCollegeName");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            if (repeaTCollege(key, FCollegeName , "2") == 0) {
                String CookiesLogincollegeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogincollegeID != null && !CookiesLogincollegeID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogincollegeID);
                }
                // 更新学院信息
                TCollege college = new TCollege();
                college.setFkeyid(key);
                college.setFcollegename(FCollegeName );
                college.setFuid(Long.parseLong(uid));
                college.setFudate(new Date());
                tCollegeService.update(college);
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
     * 删除学院信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除学院信息")
    @ResponseBody
    @RequestMapping(value = "/delcollege", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delcollege(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            tCollegeService.deleteById(Long.parseLong(id));
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
     * 变更学院信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statecollege", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statecollege(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogincollegeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincollegeID != null && !CookiesLogincollegeID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TCollege college = new TCollege();
            college.setFkeyid(Long.parseLong(id));
            college.setFuid(Long.parseLong(uid));
            college.setFudate(new Date());
            college.setFstate(Integer.valueOf(state));
            tCollegeService.update(college);
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
     * 验证学院是否存在
     */
    private int repeaTCollege(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TCollegeExample collegeExample = new TCollegeExample();
            TCollegeExample.Criteria criteria = collegeExample.createCriteria();
            if (ftype.equals("2")) { // 修改
                if (id != null) {
//                    criteria.andFkeyidEqualTo((id));
                    criteria.andFkeyidNotEqualTo(id);
                }
                if (name != null && !name.equals("")) {
                    criteria.andFcollegenameEqualTo(name);
                }
            } else { // 新增
                if (name != null && !name.equals("")) {
                    criteria.andFcollegenameEqualTo(name);
                }
            }
            List<TCollege> collegeList = tCollegeService.findByExample(collegeExample);
            if (collegeList.size() == 0) {
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