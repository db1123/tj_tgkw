package fun.server.controller.myproject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fun.server.model.TProjectSecret;
import fun.server.model.TProjectSecretExample;
import fun.server.service.TProjectSecretService;
import fun.tools.ParamTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 密级管理 相关业务处理
 */
@RestController
@RequestMapping("/s/secret")
public class Secret {

    private final TProjectSecretService   tProjectSecretService;

    public Secret(TProjectSecretService tProjectSecretService) {
        this.tProjectSecretService = tProjectSecretService;
    }


    /**
     * 获取密级信息(下拉列表)
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatasecretSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatasecretSelect( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray secret_Array = new JSONArray();
            TProjectSecretExample secretExample = new TProjectSecretExample();
            TProjectSecretExample.Criteria criteria = secretExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            secretExample.setOrderByClause("fname asc");
            List<TProjectSecret> secret_list = tProjectSecretService.findByExample(secretExample);
            for (TProjectSecret secret: secret_list) {
                JSONObject secret_object = new JSONObject();
                secret_object.put("id", ParamTools.getEnParam(secret.getFkeyid().toString()));
                secret_object.put("text", secret.getFname());
                secret_Array.add(secret_object);
            }
            // 返回值

            object.put("list", secret_Array);
            object.put("result", "success");
            object.put("results", secret_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


}