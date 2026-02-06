package fun.server.controller.sys;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import com.alibaba.fastjson.JSONObject;

//import fun.server.controller.flow.ProjectFlowCell;
import fun.tools.HttpTools;
import fun.tools.ParamTools;

/**
 * 用户访问系统 相关业务处理
 */
@RestController
@RequestMapping("/s/ows")
public class OutsideWebService {
    
//    /**
//     * 对登录验证码进行加密处理
//     * @param request
//     * @param response
//     * @return
//     * @throws UnsupportedEncodingException
//     * @throws IOException
//     */
//    @ResponseBody
//    @RequestMapping(value = "/getCloudCADUrl", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String getCloudCADUrl(HttpServletRequest request, HttpServletResponse response )
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String url = jsonParam.getString("url");
//        String clientID = jsonParam.getString("clientID");
//        String clientSecret = jsonParam.getString("clientSecret");
//        String urlParam = jsonParam.getString("urlParam");
//        String userName = jsonParam.getString("userName");
//        String projectName = jsonParam.getString("projectName");
//        // System.out.println("url:" + url);
//        // System.out.println("clientID:" + clientID);
//        // System.out.println("clientSecret:" + clientSecret);
//        // System.out.println("urlParam:" + urlParam);
//        // System.out.println("userName:" + userName);
//        // System.out.println("projectName:" + projectName);
//        // 创建XPath对象
//        XPathFactory xpathFactory = XPathFactory.newInstance();
//        XPath xpath = xpathFactory.newXPath();
//        try {
//            // 获取Token
//            System.out.println(url + "InforCenter/CloudCAD/CloudWebMethod.asmx/getToken?" + "userName=" + userName + "&clientID=" + clientID + "&clientSecret=" + clientSecret + "&url=" + urlParam);
//            String TokenXML = HttpTools.sendPost(
//                url + "InforCenter/CloudCAD/CloudWebMethod.asmx/getToken",
//                "userName=" + userName + "&clientID=" + clientID + "&clientSecret=" + clientSecret + "&url=" + urlParam
//                );
//            Document TokenDoc = ProjectFlowCell.getXMLDocument(TokenXML);
//            String Token = (String)xpath.evaluate("//string", TokenDoc, XPathConstants.STRING);
//            // System.out.println("Token:" + Token);
//            // 创建云CAD项目
//            String CADProjectXML = HttpTools.sendPost(
//                url + "InforCenter/CloudCAD/CloudWebMethod.asmx/createProject",
//                "projectName=" + projectName + "&&Authorization=" + Token + "&clientID=" + clientID + "&clientSecret=" + clientSecret + "&url=" + urlParam
//                );
//            Document CADProjectDoc = ProjectFlowCell.getXMLDocument(CADProjectXML);
//            String CADProject = (String)xpath.evaluate("//string", CADProjectDoc, XPathConstants.STRING);
//            // System.out.println("CADProject:" + CADProject);
//            // 获取访问云CAD URL
//            String CADURLXML = HttpTools.sendPost(
//                "http://8.143.200.121:8092/InforCenter/CloudCAD/CloudWebMethod.asmx/getCloudCADUrl",
//                "projectId=" + CADProject + "&token=" + Token + "&url=" + urlParam
//                );
//            Document CADURLDoc = ProjectFlowCell.getXMLDocument(CADURLXML);
//            String CADURL = (String)xpath.evaluate("//string", CADURLDoc, XPathConstants.STRING);
//            object.put("CADURL", CADURL);
//            object.put("result", "success");
//        } catch (Exception e){
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return  object.toString();
//    }
}
