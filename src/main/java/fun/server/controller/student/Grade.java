package fun.server.controller.student;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TClass;
import fun.server.model.TClassExample;
import fun.server.model.TGrade;
import fun.server.model.TGradeExample;
import fun.server.service.TClassService;
import fun.server.service.TGradeService;
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
 * 年级管理 相关业务处理
 */
@RestController
@RequestMapping("/s/grade")
public class Grade {

    private final TGradeService tGradeService;

    private final TClassService tClassService;

    public Grade(TGradeService tGradeService, TClassService tClassService) {
        this.tGradeService = tGradeService;
        this.tClassService = tClassService;
    }


    /**
     * 获取年级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querygrade", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querygrade(HttpServletRequest request)
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
            JSONArray grade_Array = new JSONArray();
            // 查询条件
            TGradeExample TGradeExample = new TGradeExample();
            TGradeExample.Criteria criteria = TGradeExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFgradenameLike("%" + name + "%");

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
                TGradeExample.setOrderByClause(orderSql.substring(1));
            } else {
                TGradeExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TGrade> gradePageInfo = tGradeService.findByExampleMapper(TGradeExample, (page - 1) * results, results);
            List<TGrade> grade_list = gradePageInfo.getList();

            for (TGrade grade : grade_list) {
                JSONObject grade_object = new JSONObject();
                grade_object.put("key", ParamTools.getEnParam(grade.getFkeyid().toString()));
                grade_object.put("FMajorID ", grade.getFmajorid() == null ? "" : ParamTools.getEnParam(grade.getFmajorid().toString()));
                if (dataall == 1) {
                    grade_object.put("FGradeName", grade.getFgradename() == null ? "" : grade.getFgradename());
                    grade_object.put("FAdmissionYear", grade.getFadmissionyear() == null ? "" : grade.getFadmissionyear());
                    grade_object.put("FMonth", grade.getFmonth() == null ? "" : grade.getFmonth());
                    grade_object.put("FSeason", grade.getFseason() == null ? "" : grade.getFseason());
                    grade_object.put("FCID", grade.getFcid());
                    grade_object.put("FUID", grade.getFuid());
                    grade_object.put("FCDATE", grade.getFcdate());
                    grade_object.put("FUDATE", grade.getFudate());
                } else {
                    grade_object.put("FName", "*****");
                    grade_object.put("FCID", "*****");
                    grade_object.put("FUID", "*****");
                    grade_object.put("FCDATE", "*****");
                    grade_object.put("FUDATE", "*****");
                }

                grade_object.put("FState", grade.getFstate());
                grade_Array.add(grade_object);
            }
            // 返回值
            object.put("list", grade_Array);
            object.put("total", gradePageInfo.getTotal()); // 总行数
            object.put("page", gradePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取年级信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatagradeSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatagradeSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray grade_Array = new JSONArray();
            TGradeExample gradeExample = new TGradeExample();
            TGradeExample.Criteria criteria = gradeExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFgradenameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            gradeExample.setOrderByClause("FGradeName asc");
            List<TGrade> grade_list = tGradeService.findByExample(gradeExample);


            for (TGrade grade : grade_list) {
                String season = "";
                switch (grade.getFseason()) {
                    case 1:
                        season = "春";
                        break;
                    case 2:
                        season = "秋";
                        break;
                    default:
                        season = "";
                        break;
                }
                JSONObject grade_object = new JSONObject();
                grade_object.put("id", ParamTools.getEnParam(grade.getFkeyid().toString()));
                grade_object.put("text", grade.getFgradename() + grade.getFmonth()+"月" + season);
                grade_Array.add(grade_object);
            }
            // 返回值

            object.put("list", grade_Array);
            object.put("results", grade_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取年级信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatagradeSelectall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatagradeSelectall(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray grade_Array = new JSONArray();
            TGradeExample gradeExample = new TGradeExample();
            TGradeExample.Criteria criteria = gradeExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFgradenameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            gradeExample.setOrderByClause("FGradeName asc");
            JSONObject grade_object = new JSONObject();
            grade_object.put("id", "-1");
            grade_object.put("text", "全部");
            grade_Array.add(grade_object);
            List<TGrade> grade_list = tGradeService.findByExample(gradeExample);
            String season = "";
            for (TGrade grade : grade_list) {
                switch (grade.getFseason()) {
                    case 1:
                        season = "春";
                        break;
                    case 2:
                        season = "秋";
                        break;
                    default:
                        season = "";
                        break;
                }
                grade_object = new JSONObject();
                grade_object.put("id", ParamTools.getEnParam(grade.getFkeyid().toString()));
                grade_object.put("text", grade.getFgradename() + grade.getFmonth()+"月" + season);
                grade_Array.add(grade_object);
            }
            // 返回值

            object.put("list", grade_Array);
            object.put("results", grade_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取年级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querygradeInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querygradeInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询年级信息
            TGrade grade = tGradeService.findById(key);
            JSONObject grade_object = new JSONObject();
            grade_object.put("key", ParamTools.getEnParam(grade.getFkeyid().toString()));
            grade_object.put("FMajorID ", grade.getFmajorid() == null ? "" : ParamTools.getEnParam(grade.getFmajorid().toString()));
            grade_object.put("FGradeName", grade.getFgradename() == null ? "" : grade.getFgradename());
            grade_object.put("FAdmissionYear", grade.getFadmissionyear() == null ? "" : grade.getFadmissionyear());
            grade_object.put("FMonth", grade.getFmonth() == null ? "" : grade.getFmonth());
            String season = "";
            switch (grade.getFseason()) {
                case 1:
                    season = "春";
                    break;
                case 2:
                    season = "秋";
                    break;
                default:
                    season = "";
                    break;
            }
            grade_object.put("FSeason", grade.getFseason() == null ? "" : grade.getFseason() + grade.getFmonth()+"月" + season);
            grade_object.put("FCID", grade.getFcid());
            grade_object.put("FUID", grade.getFuid());
            grade_object.put("FCDATE", grade.getFcdate());
            grade_object.put("FUDATE", grade.getFudate());
            grade_object.put("FState", grade.getFstate());
            // 返回值
            object.put("info", grade_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加年级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加年级信息")
    @ResponseBody
    @RequestMapping(value = "/addgrade", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addgrade(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FGradeName = jsonParam.getString("FGradeName");
        int FAdmissionYear = jsonParam.getInteger("FAdmissionYear");
        int FMonth = jsonParam.getInteger("FMonth");
        int FSeason = jsonParam.getInteger("FSeason");

        try {

            String CookiesLogingradeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogingradeID != null && !CookiesLogingradeID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLogingradeID);
            }
            TGradeExample tGradeExample = new TGradeExample();
            tGradeExample.createCriteria().andFgradenameEqualTo(FGradeName).andFadmissionyearEqualTo(FAdmissionYear).andFmonthEqualTo(FMonth);
            List<TGrade> gradeList = tGradeService.findByExample(tGradeExample);
            if (gradeList.size() == 0) {
                // 新建年级信息
                TGrade grade = new TGrade();
                grade.setFgradename(FGradeName);
                grade.setFmonth(FMonth);
                grade.setFseason(FSeason);
                grade.setFcid(Long.parseLong(uid));
                grade.setFadmissionyear(FAdmissionYear);
                grade.setFcdate(new Date());
                tGradeService.save(grade);
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
     * 修改年级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改年级信息")
    @ResponseBody
    @RequestMapping(value = "/updategrade", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updategrade(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FGradeName = jsonParam.getString("FGradeName");
        int FAdmissionYear = jsonParam.getInteger("FAdmissionYear");
        int FMonth = jsonParam.getInteger("FMonth");
        int FSeason = jsonParam.getInteger("FSeason");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);

            String CookiesLogingradeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogingradeID != null && !CookiesLogingradeID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLogingradeID);
            }
            TGradeExample tGradeExample = new TGradeExample();
            tGradeExample.createCriteria()
                    .andFgradenameEqualTo(FGradeName)
                    .andFadmissionyearEqualTo(FAdmissionYear)
                    .andFmonthEqualTo(FMonth)
                    .andFkeyidNotEqualTo(key);
            List<TGrade> gradeList = tGradeService.findByExample(tGradeExample);
            if (gradeList.size() == 0) {
                // 更新年级信息
                TGrade grade = new TGrade();
                grade.setFkeyid(key);
                grade.setFmonth(FMonth);
                grade.setFseason(FSeason);
                grade.setFgradename(FGradeName);
                grade.setFadmissionyear(FAdmissionYear);
                grade.setFuid(Long.parseLong(uid));
                grade.setFudate(new Date());
                tGradeService.update(grade);
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
     * 删除年级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除年级信息")
    @ResponseBody
    @RequestMapping(value = "/delgrade", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delgrade(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TClassExample tClassExample = new TClassExample();
            tClassExample.createCriteria().andFgradeidEqualTo(Long.parseLong(id));
            List<TClass> classList = tClassService.findByExample(tClassExample);
            if (classList.size() > 0) {
                object.put("result", "error");
                object.put("error", "年级信息已被使用，不能删除！");
            } else {
                tGradeService.deleteById(Long.parseLong(id));
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
     * 变更年级信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/stategrade", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stategrade(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogingradeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogingradeID != null && !CookiesLogingradeID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TGrade grade = new TGrade();
            grade.setFkeyid(Long.parseLong(id));
            grade.setFuid(Long.parseLong(uid));
            grade.setFudate(new Date());
            grade.setFstate(Integer.valueOf(state));
            tGradeService.update(grade);
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