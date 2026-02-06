package fun.server.controller.myproject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fun.server.model.TFileFilter;
import fun.server.model.TFileFilterExample;
import fun.server.service.TFileFilterService;
import fun.tools.ParamTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传过滤 相关业务处理
 */
@RestController
@RequestMapping("/s/filefilter")
public class FileFilter {
    private final TFileFilterService tFileFilterService;


    public FileFilter(TFileFilterService tFileFilterService) {
        this.tFileFilterService = tFileFilterService;
    }


    /**
     * 获取参考资料类型信息(下拉列表)
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryFileFilterSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatadataTypeSelect( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray dataType_Array = new JSONArray();
            TFileFilterExample tFileFilterExample = new TFileFilterExample();
            TFileFilterExample.Criteria criteria = tFileFilterExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            tFileFilterExample.setOrderByClause("fname asc");
            List<TFileFilter> tFileFilters = tFileFilterService.findByExample(tFileFilterExample);
            List<String> hzm=new ArrayList<>();
            for (TFileFilter tFileFilter: tFileFilters) {
                JSONObject dataType_object = new JSONObject();
                dataType_object.put("id", ParamTools.getEnParam(tFileFilter.getFkeyid().toString()));
                dataType_object.put("text", tFileFilter.getFname());
                dataType_Array.add(dataType_object);
                hzm.add(tFileFilter.getFname());
            }
            // 返回值
//            System.out.println(hzm.toString());
            object.put("list", hzm);
            object.put("result", "success");
            object.put("results", dataType_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取参考资料类型信息(下拉列表)
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryFileFilterSelect_dian", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryFileFilterSelect_dian( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        try {
            // 获取数据库记录
            JSONArray dataType_Array = new JSONArray();
            TFileFilterExample tFileFilterExample = new TFileFilterExample();
            TFileFilterExample.Criteria criteria = tFileFilterExample.createCriteria();
            criteria.andFstateEqualTo(1);
            tFileFilterExample.setOrderByClause("fname asc");
            List<TFileFilter> tFileFilters = tFileFilterService.findByExample(tFileFilterExample);
            List<String> hzm=new ArrayList<>();
            List<String> hzm2=new ArrayList<>();
            String name="";
            String named="";
            for (TFileFilter tFileFilter: tFileFilters) {
                JSONObject dataType_object = new JSONObject();
                dataType_object.put("id", ParamTools.getEnParam(tFileFilter.getFkeyid().toString()));
                dataType_object.put("text", tFileFilter.getFname().replace(".",""));
                dataType_Array.add(dataType_object);
                hzm.add(tFileFilter.getFname().replace(".",""));
                hzm2.add(tFileFilter.getFname());
                name+=tFileFilter.getFname()+",";
                named+=tFileFilter.getFname().replace(".","") +",";
            }
            if (name.length() > 0){
                name=name.substring(0, name.length()-1);
            }
            if (named.length() > 0){
                named=named.substring(0, named.length()-1);
            }
            // 返回值
//            System.out.println(hzm.toString());
            object.put("list", hzm);
            object.put("list2", hzm2);
            object.put("listname", name);
            object.put("listnamed", named);
            object.put("result", "success");
            object.put("results", dataType_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
}
