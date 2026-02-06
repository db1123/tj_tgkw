package fun.server.controller.sys;

import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.upload.StorageManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
 
@RestController
@RequestMapping("ueditor")
public class UeditorController {
 
    @RequestMapping(value = "/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        // 读取配置文件中配置的上传文件保存地址
        String rootPath = this.getClass().getClassLoader().getResource("").getPath();
        String configPath = "public/plugins/ueditor/cfg/config.json";
        try {
            String exec = new ActionEnter(new StorageManager(), request, rootPath, configPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}