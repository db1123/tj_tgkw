package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TCourseTextbook;
import fun.server.model.TCourseTextbookExample;
import fun.server.service.TCourseTextbookService;
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
 * 教材管理 相关业务处理
 */
@RestController
@RequestMapping("/s/coursetextbook")
public class CourseTextBook {

    private final TCourseTextbookService tCourseTextbookService;
    private final TUserService tUserService;

    public CourseTextBook(TCourseTextbookService tCourseTextbookService, TUserService tUserService) {
        this.tCourseTextbookService = tCourseTextbookService;
        this.tUserService = tUserService;
    }


    /**
     * 获取教材信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycoursetextbook", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycoursetextbook(HttpServletRequest request)
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
        Integer FType = jsonParam.getInteger("FType");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray coursetextbook_Array = new JSONArray();
            // 查询条件
            TCourseTextbookExample TCourseTextbookExample = new TCourseTextbookExample();
            TCourseTextbookExample.Criteria criteria = TCourseTextbookExample.createCriteria();

            FCourseID = FCourseID == null ? "0" : FCourseID.equals("0") ? "" : ParamTools.getdeParam(FCourseID);
            criteria.andFcourseidEqualTo(Long.parseLong(FCourseID));
            if(name!=null && !name.equals("")){
                criteria.andFnameLike("%"+name+"%");
            }
            criteria.andFtypeEqualTo(FType);
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                TCourseTextbookExample.setOrderByClause(orderSql.substring(1));
            } else {
                TCourseTextbookExample.setOrderByClause("FName asc");
            }
            PageInfo<TCourseTextbook> coursetextbookPageInfo = tCourseTextbookService.findByExampleMapper(TCourseTextbookExample, (page - 1) * results, results);
            List<TCourseTextbook> coursetextbook_list = coursetextbookPageInfo.getList();
            for (TCourseTextbook coursetextbook : coursetextbook_list) {
                JSONObject coursetextbook_object = new JSONObject();
                coursetextbook_object.put("key", ParamTools.getEnParam(coursetextbook.getFkeyid().toString()));
                coursetextbook_object.put("FCourseID", ParamTools.getEnParam(coursetextbook.getFcourseid().toString()));
                if (dataall == 1) {
                    coursetextbook_object.put("FName", coursetextbook.getFname() == null ? "" : coursetextbook.getFname());
                    coursetextbook_object.put("FType", coursetextbook.getFtype() == null ? -1 : coursetextbook.getFtype());
                    coursetextbook_object.put("FCID", coursetextbook.getFcid());
                    coursetextbook_object.put("FUID", coursetextbook.getFuid());
                    coursetextbook_object.put("FCDATE", coursetextbook.getFcdate());
                    coursetextbook_object.put("FUDATE", coursetextbook.getFudate());
                } else {
                    coursetextbook_object.put("FName", "*****");
                    coursetextbook_object.put("FType", "*****");
                    coursetextbook_object.put("FCID", "*****");
                    coursetextbook_object.put("FUID", "*****");
                    coursetextbook_object.put("FCDATE", "*****");
                    coursetextbook_object.put("FUDATE", "*****");
                }

                coursetextbook_object.put("FState", coursetextbook.getFstate());
                coursetextbook_Array.add(coursetextbook_object);
            }
            // 返回值
            object.put("list", coursetextbook_Array);
            object.put("total", coursetextbookPageInfo.getTotal()); // 总行数
            object.put("page", coursetextbookPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取教材信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatacoursetextbookSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatacoursetextbookSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");


        try {
            // 获取数据库记录
            JSONArray coursetextbook_Array = new JSONArray();
            TCourseTextbookExample coursetextbookExample = new TCourseTextbookExample();
            TCourseTextbookExample.Criteria criteria = coursetextbookExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            coursetextbookExample.setOrderByClause("fname asc");
            List<TCourseTextbook> coursetextbook_list = tCourseTextbookService.findByExample(coursetextbookExample);
            for (TCourseTextbook coursetextbook : coursetextbook_list) {
                JSONObject coursetextbook_object = new JSONObject();
                coursetextbook_object.put("id", ParamTools.getEnParam(coursetextbook.getFkeyid().toString()));
                coursetextbook_object.put("text", coursetextbook.getFname());
                coursetextbook_Array.add(coursetextbook_object);
            }
            // 返回值

            object.put("list", coursetextbook_Array);
            object.put("result", "success");
            object.put("results", coursetextbook_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 根据ID获取教材信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycoursetextbookInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycoursetextbookInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询教材信息
            TCourseTextbook coursetextbook = tCourseTextbookService.findById(key);
            JSONObject coursetextbook_object = new JSONObject();
            coursetextbook_object.put("key", ParamTools.getEnParam(coursetextbook.getFkeyid().toString()));
            coursetextbook_object.put("FName", coursetextbook.getFname());
            coursetextbook_object.put("FCourseID", ParamTools.getEnParam(coursetextbook.getFcourseid().toString()));
            coursetextbook_object.put("FType", coursetextbook.getFtype() == null ? -1 : coursetextbook.getFtype());
            coursetextbook_object.put("FCID", coursetextbook.getFcid());
            coursetextbook_object.put("FUID", coursetextbook.getFuid());
            coursetextbook_object.put("FCDATE", coursetextbook.getFcdate());
            coursetextbook_object.put("FUDATE", coursetextbook.getFudate());
            coursetextbook_object.put("FState", coursetextbook.getFstate());
            // 返回值
            object.put("info", coursetextbook_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加教材信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加教材信息")
    @ResponseBody
    @RequestMapping(value = "/addcoursetextbook", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addcoursetextbook(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FName = jsonParam.getString("FName");
        String FCourseID = jsonParam.getString("FCourseID");
        Integer fType = jsonParam.getInteger("FType");
        try {
            FCourseID = FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);
            TCourseTextbookExample coursetextbookExample = new TCourseTextbookExample();
            TCourseTextbookExample.Criteria criteria = coursetextbookExample.createCriteria();
            criteria.andFnameEqualTo(FName);
            criteria.andFcourseidEqualTo(Long.parseLong(FCourseID));
            criteria.andFtypeEqualTo(fType);
            List<TCourseTextbook> levelList = tCourseTextbookService.findByExample(coursetextbookExample);
            if (levelList.size() == 0) {
                String CookiesLogincoursetextbookID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogincoursetextbookID != null && !CookiesLogincoursetextbookID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogincoursetextbookID);
                }
                // 新建教材信息
                TCourseTextbook coursetextbook = new TCourseTextbook();
                coursetextbook.setFname(FName);
                coursetextbook.setFtype(fType);
                coursetextbook.setFcourseid(Long.parseLong(FCourseID));
                coursetextbook.setFcid(Long.parseLong(uid));
                coursetextbook.setFcdate(new Date());
                tCourseTextbookService.save(coursetextbook);
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
     * 修改教材信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改教材信息")
    @ResponseBody
    @RequestMapping(value = "/updatecoursetextbook", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatecoursetextbook(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FName = jsonParam.getString("FName");
        String FCourseID = jsonParam.getString("FCourseID");
        try {
            FCourseID = FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            TCourseTextbookExample coursetextbookExample = new TCourseTextbookExample();
            TCourseTextbookExample.Criteria criteria = coursetextbookExample.createCriteria();
            criteria.andFnameEqualTo(FName);
            criteria.andFcourseidEqualTo(Long.parseLong(FCourseID));
            criteria.andFkeyidNotEqualTo(key);
            List<TCourseTextbook> levelList = tCourseTextbookService.findByExample(coursetextbookExample);
            if (levelList.size() == 0) {
                String CookiesLogincoursetextbookID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogincoursetextbookID != null && !CookiesLogincoursetextbookID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogincoursetextbookID);
                }
                // 更新教材信息
                TCourseTextbook coursetextbook = new TCourseTextbook();
                coursetextbook.setFkeyid(key);
                coursetextbook.setFname(FName);
//                coursetextbook.setFcourseid(Long.parseLong(FCourseID));
                coursetextbook.setFuid(Long.parseLong(uid));
                coursetextbook.setFudate(new Date());
                tCourseTextbookService.update(coursetextbook);
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
     * 删除教材信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除教材信息")
    @ResponseBody
    @RequestMapping(value = "/delcoursetextbook", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delcoursetextbook(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            tCourseTextbookService.deleteById(Long.parseLong(id));
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