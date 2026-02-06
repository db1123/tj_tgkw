package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TAbilityExample;
import fun.server.model.TCourseCategory;
import fun.server.model.TCourseCategoryExample;
import fun.server.service.TAbilityService;
import fun.server.service.TCourseCategoryService;
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
 * 课程类别管理 相关业务处理
 */
@RestController
@RequestMapping("/s/coursecategory")
public class CourseType {

    private final TCourseCategoryService tCourseCategoryService;
    private final TAbilityService tAbilityService;

    public CourseType(TCourseCategoryService tCourseCategoryService, TAbilityService tAbilityService) {
        this.tCourseCategoryService = tCourseCategoryService;
        this.tAbilityService = tAbilityService;
    }


//    /**
//     * 获取课程类别信息
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/querycoursecategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String querycoursecategory(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        int results = jsonParam.getInteger("results");
//        int page = jsonParam.getInteger("page");
//        JSONArray columns_JA = jsonParam.getJSONArray("columns");
//        JSONArray order_JA = jsonParam.getJSONArray("order");
//        String name = jsonParam.getString("name");
//        Integer dataall = jsonParam.getInteger("dataall");
//        try {
//            // 获取数据库记录
//            JSONArray coursecategory_Array = new JSONArray();
//            // 查询条件
//            TCourseCategoryExample TCourseCategoryExample = new TCourseCategoryExample();
//            TCourseCategoryExample.Criteria criteria = TCourseCategoryExample.createCriteria();
//
//            if (name != null && !name.equals("")) {
//                criteria.andFnameLike("%" + name + "%");
//
//            }
//            TCourseCategoryExample.setOrderByClause("FCDATE desc");
//            PageInfo<TCourseCategory> coursecategoryPageInfo = tCourseCategoryService.findByExampleMapper(TCourseCategoryExample, (page - 1) * results, results);
//            List<TCourseCategory> coursecategory_list = coursecategoryPageInfo.getList();
//
//            for (TCourseCategory coursecategory : coursecategory_list) {
//                JSONObject coursecategory_object = new JSONObject();
//                coursecategory_object.put("key", ParamTools.getEnParam(coursecategory.getFkeyid().toString()));
//                if (dataall == 1) {
//                    coursecategory_object.put("FName", coursecategory.getFname());
//                    coursecategory_object.put("FCID", coursecategory.getFcid());
//                    coursecategory_object.put("FUID", coursecategory.getFuid());
//                    coursecategory_object.put("FCDATE", coursecategory.getFcdate());
//                    coursecategory_object.put("FUDATE", coursecategory.getFudate());
//                } else {
//                    coursecategory_object.put("FName", "*****");
//                    coursecategory_object.put("FCID", "*****");
//                    coursecategory_object.put("FUID", "*****");
//                    coursecategory_object.put("FCDATE", "*****");
//                    coursecategory_object.put("FUDATE", "*****");
//                }
//
//                coursecategory_object.put("FState", coursecategory.getFstate());
//                coursecategory_Array.add(coursecategory_object);
//            }
//            // 返回值
//            object.put("list", coursecategory_Array);
//            object.put("total", coursecategoryPageInfo.getTotal()); // 总行数
//            object.put("page", coursecategoryPageInfo.getPageNum()); // 当前页数
//            object.put("result", "success");
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }

//    /**
//     * 获取课程类别信息(下拉列表)
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/queryDatacoursecategorySelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String queryDatacoursecategorySelect(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        String search = request.getParameter("search");
//        try {
//            // 获取数据库记录
//            JSONArray coursecategory_Array = new JSONArray();
//            TCourseCategoryExample coursecategoryExample = new TCourseCategoryExample();
//            TCourseCategoryExample.Criteria criteria = coursecategoryExample.createCriteria();
//            if (search != null && !search.equals("")) {
//                criteria.andFnameLike("%" + search + "%");
//            }
//            criteria.andFstateEqualTo(1);
//            coursecategoryExample.setOrderByClause("fname asc");
//            List<TCourseCategory> coursecategory_list = tCourseCategoryService.findByExample(coursecategoryExample);
//            for (TCourseCategory coursecategory : coursecategory_list) {
//                JSONObject coursecategory_object = new JSONObject();
//                coursecategory_object.put("id", ParamTools.getEnParam(coursecategory.getFkeyid().toString()));
//                coursecategory_object.put("text", coursecategory.getFname());
//                coursecategory_Array.add(coursecategory_object);
//            }
//            // 返回值
//
//            object.put("list", coursecategory_Array);
//            object.put("results", coursecategory_Array);
//            object.put("result", "success");
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }
//
//    /**
//     * 获取课程类别信息(下拉列表)
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/queryDatacoursecategorySelectall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String queryDatacoursecategorySelectall(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        String search = request.getParameter("search");
//        try {
//            // 获取数据库记录
//            JSONArray coursecategory_Array = new JSONArray();
//            TCourseCategoryExample coursecategoryExample = new TCourseCategoryExample();
//            TCourseCategoryExample.Criteria criteria = coursecategoryExample.createCriteria();
//            if (search != null && !search.equals("")) {
//                criteria.andFnameLike("%" + search + "%");
//            }
//            criteria.andFstateEqualTo(1);
//            coursecategoryExample.setOrderByClause("fname asc");
//            JSONObject coursecategory_object = new JSONObject();
//            coursecategory_object.put("id", "-1");
//            coursecategory_object.put("text","全部");
//            coursecategory_Array.add(coursecategory_object);
//            List<TCourseCategory> coursecategory_list = tCourseCategoryService.findByExample(coursecategoryExample);
//            for (TCourseCategory coursecategory : coursecategory_list) {
//                coursecategory_object = new JSONObject();
//                coursecategory_object.put("id", ParamTools.getEnParam(coursecategory.getFkeyid().toString()));
//                coursecategory_object.put("text", coursecategory.getFname());
//                coursecategory_Array.add(coursecategory_object);
//            }
//            // 返回值
//
//            object.put("list", coursecategory_Array);
//            object.put("results", coursecategory_Array);
//            object.put("result", "success");
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }
//
//    /**
//     * 根据ID获取课程类别信息
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/querycoursecategoryInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String querycoursecategoryInfo(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String id = jsonParam.getString("id");
//        try {
//            id = id == null ? "0" : ParamTools.getdeParam(id);
//            long key = Long.parseLong(id);
//            // 查询课程类别信息
//            TCourseCategory coursecategory = tCourseCategoryService.findById(key);
//            JSONObject coursecategory_object = new JSONObject();
//            coursecategory_object.put("key", ParamTools.getEnParam(coursecategory.getFkeyid().toString()));
//            coursecategory_object.put("FName", coursecategory.getFname());
//            coursecategory_object.put("FCID", coursecategory.getFcid());
//            coursecategory_object.put("FUID", coursecategory.getFuid());
//            coursecategory_object.put("FCDATE", coursecategory.getFcdate());
//            coursecategory_object.put("FUDATE", coursecategory.getFudate());
//            coursecategory_object.put("FState", coursecategory.getFstate());
//            // 返回值
//            object.put("info", coursecategory_object);
//            object.put("result", "success");
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }
//
//    /**
//     * 添加课程类别信息
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @LogOperation("添加课程类别信息")
//    @ResponseBody
//    @RequestMapping(value = "/addcoursecategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String addcoursecategory(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String Fname = jsonParam.getString("Fname");
//
//        try {
//            if (repeaTCourseCategory(0L, Fname, "1") == 0) {
//                String CookiesLogincoursecategoryID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//                String uid = ""; // 当前登录用户ID
//                if (CookiesLogincoursecategoryID != null && !CookiesLogincoursecategoryID.equals("")) {
//                    uid = ParamTools.getdeParam(CookiesLogincoursecategoryID);
//                }
//                // 新建课程类别信息
//                TCourseCategory coursecategory = new TCourseCategory();
//                coursecategory.setFname(Fname);
//                coursecategory.setFcid(Long.parseLong(uid));
//                coursecategory.setFcdate(new Date());
//                tCourseCategoryService.save(coursecategory);
//                // 返回值
//                object.put("result", "success");
//            } else {
//                // 返回值
//                object.put("result", "fail");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }
//
//    /**
//     * 修改课程类别信息
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @LogOperation("修改课程类别信息")
//    @ResponseBody
//    @RequestMapping(value = "/updatecoursecategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String updatecoursecategory(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String id = jsonParam.getString("FKeyID");
//        String Fname = jsonParam.getString("Fname");
//
//        try {
//            id = id == null ? "0" : ParamTools.getdeParam(id);
//            long key = Long.parseLong(id);
//            if (repeaTCourseCategory(key, Fname, "2") == 0) {
//                String CookiesLogincoursecategoryID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//                String uid = ""; // 当前登录用户ID
//                if (CookiesLogincoursecategoryID != null && !CookiesLogincoursecategoryID.equals("")) {
//                    uid = ParamTools.getdeParam(CookiesLogincoursecategoryID);
//                }
//                // 更新课程类别信息
//                TCourseCategory coursecategory = new TCourseCategory();
//                coursecategory.setFkeyid(key);
//                coursecategory.setFname(Fname);
//                coursecategory.setFuid(Long.parseLong(uid));
//                coursecategory.setFudate(new Date());
//                tCourseCategoryService.update(coursecategory);
//                // 返回值
//                object.put("result", "success");
//            } else {
//                // 返回值
//                object.put("result", "fail");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }
//
//    /**
//     * 删除课程类别信息
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @LogOperation("删除课程类别信息")
//    @ResponseBody
//    @RequestMapping(value = "/delcoursecategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String delcoursecategory(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String id = jsonParam.getString("id");
//        try {
//            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
//            TAbilityExample tAbilityExample = new TAbilityExample();
//            tAbilityExample.createCriteria().andFtypeidEqualTo(Long.parseLong(id));
//            List<TAbility> abilityList = tAbilityService.findByExample(tAbilityExample);
//            if (abilityList.size() == 0) {
//                tCourseCategoryService.deleteById(Long.parseLong(id));
//                // 返回值
//                object.put("result", "success");
//            }else{
//                object.put("result", "该课程类别已被使用，不能删除(不想使用可禁用)！");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }
//
//    /**
//     * 变更课程类别信息状态
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/statecoursecategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String statecoursecategory(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String id = jsonParam.getString("id");
//        String state = jsonParam.getString("state");
//        try {
//            String CookiesLogincoursecategoryID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//            String uid = ""; // 当前登录用户ID
//            if (CookiesLogincoursecategoryID != null && !CookiesLogincoursecategoryID.equals("")) {
//                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
//            }
//            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
//            state = state.equals("1") ? "0" : "1";
//            TCourseCategory coursecategory = new TCourseCategory();
//            coursecategory.setFkeyid(Long.parseLong(id));
//            coursecategory.setFuid(Long.parseLong(uid));
//            coursecategory.setFudate(new Date());
//            coursecategory.setFstate(Integer.valueOf(state));
//            tCourseCategoryService.update(coursecategory);
//            // 返回值
//            object.put("result", "success");
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }
//
//    /**
//     * 验证课程类别是否存在
//     */
//    private int repeaTCourseCategory(Long id, String name, String ftype) {
//        int code = 0;
//        try {
//            // 查询条件
//            TCourseCategoryExample coursecategoryExample = new TCourseCategoryExample();
//            TCourseCategoryExample.Criteria criteria = coursecategoryExample.createCriteria();
//            if (ftype.equals("2")) { // 修改
//                if (id != null) {
////                    criteria.andFkeyidEqualTo((id));
//                    criteria.andFkeyidNotEqualTo(id);
//                }
//                if (name != null && !name.equals("")) {
//                    criteria.andFnameEqualTo(name);
//                }
//            } else { // 新增
//                if (name != null && !name.equals("")) {
//                    criteria.andFnameEqualTo(name);
//                }
//            }
//            List<TCourseCategory> coursecategoryList = tCourseCategoryService.findByExample(coursecategoryExample);
//            if (coursecategoryList.size() == 0) {
//                code = 0;
//            } else {
//                code = 1;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return code;
//    }
//
//
//    //根据ID查询课程类别名称
//    public String getName(Long id) {
//        TCourseCategory byId = tCourseCategoryService.findById(id);
//        if (byId != null) {
//            return byId.getFname();
//        } else {
//            return "";
//        }
//
//    }



    /**
     * 获取类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/querycourseCategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycourseCategory( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        JSONArray objectList = new JSONArray();
        try {
            if (id == null) {
                JSONObject object = new JSONObject();
                object.put("id", 1);
                object.put("pId", 0);
                TCourseCategoryExample tCourseCategoryExample = new TCourseCategoryExample();
                TCourseCategoryExample.Criteria criteria = tCourseCategoryExample.createCriteria();
                criteria.andFpidEqualTo(1L);
                List<TCourseCategory> list = tCourseCategoryService.findByExample(tCourseCategoryExample);
                if (list.size() > 0) {
                    object.put("isParent", 1);
                } else {
                    object.put("isParent", 0);
                }
                object.put("name", "根节点");
                objectList.add(object);
            } else {
                String pid = id.equals("1") ? "1" : ParamTools.getdeParam(id);
                TCourseCategoryExample tCourseCategoryExample = new TCourseCategoryExample();
                TCourseCategoryExample.Criteria criteria = tCourseCategoryExample.createCriteria();
                criteria.andFpidEqualTo(Long.parseLong(pid));
                List<TCourseCategory> list = tCourseCategoryService.findByExample(tCourseCategoryExample);
                for (TCourseCategory tCourseCategory : list) {
                    JSONObject object = new JSONObject();
                    object.put("id", ParamTools.getEnParam(tCourseCategory.getFkeyid().toString()));
                    object.put("pId", pid);
                    object.put("name", tCourseCategory.getFname());
                    if (tCourseCategory.getFisleaf() == 1) {
                        object.put("isParent", 0);
                    } else {
                        object.put("isParent", 1);
                    }
                    objectList.add(object);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectList.toJSONString();
    }

    /**
     * 添加类型信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("添加课程类别信息")
    @ResponseBody
    @RequestMapping(value = "/addcourseCategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addcourseCategory( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String f_p_id = jsonParam.getString("FPID");
        String f_name = jsonParam.getString("FName");
        String FNote = jsonParam.getString("FNote");
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            Long pId = f_p_id.equals("1") ? 1L : Long.parseLong(ParamTools.getdeParam(f_p_id));

            TCourseCategoryExample tCourseCategoryExample = new TCourseCategoryExample();
            TCourseCategoryExample.Criteria criteria = tCourseCategoryExample.createCriteria();
            criteria.andFpidEqualTo(pId);
            criteria.andFnameEqualTo(f_name);
            List<TCourseCategory> courseCategoryList = tCourseCategoryService.findByExample(tCourseCategoryExample);
            if(courseCategoryList.size() == 0){
                // 获取父节点路径
                String path = "";
                TCourseCategory tCourseCategory = tCourseCategoryService.findById(pId);
                if (tCourseCategory == null) {
                    path = "|_1_|";
                } else {
                    path = tCourseCategory.getFpath();
                }
                // 添加数据
                String id = "";
                TCourseCategory courseCategory = new TCourseCategory();
                courseCategory.setFcid(Long.parseLong(uid));
                courseCategory.setFpid(pId);
                courseCategory.setFname(f_name);
                courseCategory.setFisleaf(1);
                courseCategory.setFnote(FNote);
                tCourseCategoryService.save(courseCategory);
                id = ParamTools.getEnParam(courseCategory.getFkeyid().toString());
                courseCategory.setFpath(path + "|_" + courseCategory.getFkeyid().toString() + "_|");
                tCourseCategoryService.update(courseCategory);
                // 刷新父节点
                courseCategory = new TCourseCategory();
                courseCategory.setFkeyid(pId);
                courseCategory.setFisleaf(0);
                tCourseCategoryService.update(courseCategory);

                // 返回值
                object.put("id", id);
                object.put("result", "success");
            }else{
                object.put("result", "fial");
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 修改类型信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("修改课程类别信息")
    @ResponseBody
    @RequestMapping(value = "/updatecourseCategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatecourseCategory( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String f_name = jsonParam.getString("FName");
        String FNote = jsonParam.getString("FNote");
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            id = id == null ? "0" : ParamTools.getdeParam(id);
            TCourseCategoryExample tCourseCategoryExample = new TCourseCategoryExample();
            TCourseCategoryExample.Criteria criteria = tCourseCategoryExample.createCriteria();
            criteria.andFkeyidNotEqualTo(Long.parseLong(id));
            criteria.andFnameEqualTo(f_name);
            List<TCourseCategory> courseCategoryList = tCourseCategoryService.findByExample(tCourseCategoryExample);
            if(courseCategoryList.size() == 0){
                // 更新主记录
                TCourseCategory courseCategory1 = new TCourseCategory();
                courseCategory1.setFkeyid(Long.parseLong(id));
                courseCategory1.setFuid(Long.parseLong(uid));
                courseCategory1.setFudate(new Date());
                courseCategory1.setFname(f_name);
                courseCategory1.setFnote(FNote);
                tCourseCategoryService.update(courseCategory1);
                // 返回值
                object.put("result", "success");
            }else{
                object.put("result", "fial");
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 删除类型信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("删除课程类别信息")
    @ResponseBody
    @RequestMapping(value = "/delcourseCategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delcourseCategory( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }

            // 获取当前删除记录
            TCourseCategory courseCategory = tCourseCategoryService.findById(key);
            // 判断是否可删除
            if (courseCategory.getFisleaf() == 0) {
                object.put("result", "error");
                object.put("error", "只能删除最下层节点");
            } else {
                TAbilityExample tAbilityExample = new TAbilityExample();
                TAbilityExample.Criteria criteria = tAbilityExample.createCriteria();
                criteria.andFtypeidEqualTo(key);
                if (tAbilityService.findByExample(tAbilityExample).size() > 0) {
                    object.put("result", "error");
                    object.put("error", "此节点在使用中无法删除");
                } else {
                    // 删除记录
                    tCourseCategoryService.deleteById(key);
                    // 判断是否需要修改父节点状态
                    TCourseCategoryExample paramTypeExample = new TCourseCategoryExample();
                    paramTypeExample.or().andFpidEqualTo(courseCategory.getFpid());
                    if (tCourseCategoryService.findByExample(paramTypeExample).size() == 0) {
                        TCourseCategory SCTObject = new TCourseCategory();
                        SCTObject.setFkeyid(courseCategory.getFpid());
                        SCTObject.setFisleaf(1);
                        tCourseCategoryService.update(SCTObject);
                        object.put("PIsLeaf", 1);
                    } else {
                        object.put("PIsLeaf", 0);
                    }
                    // 返回值
                    object.put("result", "success");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/querycourseCategoryALL", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycourseCategoryALL( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        try {
            // 返回值
            object.put("zNodes", recursionTypes(1L));
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取类型树(递归)
     * @param pid 父节点
     * @return 响应结果
     * @throws Exception 顶层异常类
     */
    private JSONArray recursionTypes(Long pid)
            throws Exception {
        JSONArray objectList = new JSONArray();
        TCourseCategoryExample tCourseCategoryExample = new TCourseCategoryExample();
        tCourseCategoryExample.or().andFpidEqualTo(pid);
        tCourseCategoryExample.setOrderByClause("FCDATE asc");
        List<TCourseCategory> tCourseCategoryList = tCourseCategoryService.findByExample(tCourseCategoryExample);

        for (TCourseCategory courseCategory : tCourseCategoryList) {
            JSONObject object = new JSONObject();
            object.put("id", ParamTools.getEnParam(courseCategory.getFkeyid().toString()));
            object.put("pId", pid == 1 ? 1 : ParamTools.getEnParam(pid.toString()));
            object.put("name", courseCategory.getFname());
            object.put("showname", getPName(pid,courseCategory.getFname()));
            JSONArray next_list = recursionTypes(courseCategory.getFkeyid());
            if (next_list.size() > 0) {
                object.put("children", next_list);
            }
            objectList.add(object);
        }
        return objectList;
    }


    private String getPName(Long pid,String oldname){
        String newname="";
        if(pid == 1){
            newname=oldname;
        }else{
            TCourseCategory courseCategory = tCourseCategoryService.findById(pid);
            if(courseCategory!= null){
                newname = courseCategory.getFname() + "/"+ oldname ;
                getPName(courseCategory.getFpid(),newname);
            }

        }
        return newname;
    }



    /**
     * 获取类型信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryCourseTypeInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryParamTypeInfo( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 获取数据库记录
            TCourseCategory paramType = tCourseCategoryService.findById(key);
            JSONObject paramType_object = new JSONObject();
            paramType_object.put("key", ParamTools.getEnParam(paramType.getFkeyid().toString()));
            paramType_object.put("FName", paramType.getFname());
            paramType_object.put("FPID", ParamTools.getEnParam(paramType.getFpid().toString()));
            if(paramType.getFpid() == 1 ){
                paramType_object.put("FPName", "根节点");
            }else{
                paramType = tCourseCategoryService.findById(paramType.getFpid());
                paramType_object.put("FPName", paramType.getFname());
            }
            // 返回值
            object.put("info", paramType_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
}