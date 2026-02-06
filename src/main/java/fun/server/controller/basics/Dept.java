package fun.server.controller.basics;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TDept;
import fun.server.model.TDeptExample;

import fun.server.service.TDeptService;
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
 * 主管部门管理 相关业务处理
 */
@RestController
@RequestMapping("/s/dept")
public class Dept {

    private final TDeptService tDeptService;

    public Dept(TDeptService tDeptService) {
        this.tDeptService = tDeptService;
    }


    /**
     * 获取主管部门信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querydept", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querydept(HttpServletRequest request)
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
            JSONArray dept_Array = new JSONArray();
            // 查询条件
            TDeptExample TDeptExample = new TDeptExample();
            TDeptExample.Criteria criteria = TDeptExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");

            }
            TDeptExample.setOrderByClause("FCDATE desc");
            PageInfo<TDept> deptPageInfo = tDeptService.findByExampleMapper(TDeptExample, (page - 1) * results, results);
            List<TDept> dept_list = deptPageInfo.getList();

            for (TDept dept : dept_list) {
                JSONObject dept_object = new JSONObject();
                dept_object.put("key", ParamTools.getEnParam(dept.getFkeyid().toString()));
                if (dataall == 1) {
                    dept_object.put("FName", dept.getFname());
                    dept_object.put("FCID", dept.getFcid());
                    dept_object.put("FUID", dept.getFuid());
                    dept_object.put("FCDATE", dept.getFcdate());
                    dept_object.put("FUDATE", dept.getFudate());
                } else {
                    dept_object.put("FName", "*****");
                    dept_object.put("FCID", "*****");
                    dept_object.put("FUID", "*****");
                    dept_object.put("FCDATE", "*****");
                    dept_object.put("FUDATE", "*****");
                }

                dept_object.put("FState", dept.getFstate());
                dept_Array.add(dept_object);
            }
            // 返回值
            object.put("list", dept_Array);
            object.put("total", deptPageInfo.getTotal()); // 总行数
            object.put("page", deptPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取主管部门信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatadeptSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatadeptSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray dept_Array = new JSONArray();
            TDeptExample deptExample = new TDeptExample();
            TDeptExample.Criteria criteria = deptExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            deptExample.setOrderByClause("fname asc");
            List<TDept> dept_list = tDeptService.findByExample(deptExample);
            for (TDept dept : dept_list) {
                JSONObject dept_object = new JSONObject();
                dept_object.put("id", ParamTools.getEnParam(dept.getFkeyid().toString()));
                dept_object.put("text", dept.getFname());
                dept_Array.add(dept_object);
            }
            // 返回值

            object.put("list", dept_Array);
            object.put("result", "success");
            object.put("results", dept_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取主管部门信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querydeptInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querydeptInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询主管部门信息
            TDept dept = tDeptService.findById(key);
            JSONObject dept_object = new JSONObject();
            dept_object.put("key", ParamTools.getEnParam(dept.getFkeyid().toString()));
            dept_object.put("FName", dept.getFname());
            dept_object.put("FCID", dept.getFcid());
            dept_object.put("FUID", dept.getFuid());
            dept_object.put("FCDATE", dept.getFcdate());
            dept_object.put("FUDATE", dept.getFudate());
            dept_object.put("FState", dept.getFstate());
            // 返回值
            object.put("info", dept_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加主管部门信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加部门信息")
    @ResponseBody
    @RequestMapping(value = "/adddept", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String adddept(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String Fname = jsonParam.getString("Fname");

        try {
            if (repeaTDept(0L, Fname, "1") == 0) {
                String CookiesLogindeptID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogindeptID != null && !CookiesLogindeptID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogindeptID);
                }
                // 新建主管部门信息
                TDept dept = new TDept();
                dept.setFname(Fname);
                dept.setFcid(Long.parseLong(uid));
                dept.setFcdate(new Date());
                tDeptService.save(dept);
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
     * 修改主管部门信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改部门信息")
    @ResponseBody
    @RequestMapping(value = "/updatedept", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatedept(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String Fname = jsonParam.getString("Fname");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            if (repeaTDept(key, Fname, "2") == 0) {
                String CookiesLogindeptID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogindeptID != null && !CookiesLogindeptID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogindeptID);
                }
                // 更新主管部门信息
                TDept dept = new TDept();
                dept.setFkeyid(key);
                dept.setFname(Fname);
                dept.setFuid(Long.parseLong(uid));
                dept.setFudate(new Date());
                tDeptService.update(dept);
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
     * 删除主管部门信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除部门信息")
    @ResponseBody
    @RequestMapping(value = "/deldept", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deldept(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogindeptID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogindeptID != null && !CookiesLogindeptID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));


            tDeptService.deleteById(Long.parseLong(id));
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
     * 变更主管部门信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statedept", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statedept(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogindeptID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogindeptID != null && !CookiesLogindeptID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TDept dept = new TDept();
            dept.setFkeyid(Long.parseLong(id));
            dept.setFuid(Long.parseLong(uid));
            dept.setFudate(new Date());
            dept.setFstate(Integer.valueOf(state));
            tDeptService.update(dept);
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
     * 验证主管部门是否存在
     */
    private int repeaTDept(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TDeptExample deptExample = new TDeptExample();
            TDeptExample.Criteria criteria = deptExample.createCriteria();
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
            List<TDept> deptList = tDeptService.findByExample(deptExample);
            if (deptList.size() == 0) {
                code = 0;
            } else {
                code = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }


    //根据ID查询主管部门名称
    public String getName(Long id) {
        TDept byId = tDeptService.findById(id);
        if (byId != null) {
            return byId.getFname();
        } else {
            return "";
        }

    }
}