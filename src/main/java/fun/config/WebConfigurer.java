package fun.config;

import fun.config.intercepors.LoginInterceptor;
import fun.tools.RedisTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:upload.properties")
public class WebConfigurer implements WebMvcConfigurer {

//    @Value("${flow.path}")
//    private String flowPath;
  
    @Autowired
    private RedisTools redisTools;

    @Bean
    public LoginInterceptor getSecurityInterceptor() {
        return new LoginInterceptor(redisTools);
    }

    // 这个方法是用来配置静态资源的，比如html，js，css，等等
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/download/**").addResourceLocations("file:///C:/BaiduNetdiskDownload/Server0412/Server/InforCenter/Integrate/Config/"); // 课题工具
//        registry.addResourceHandler("/download/**").addResourceLocations("file:///" + flowPath.replace("//", "/")); // 流程节点附件
//        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/"); // UEditor使用
//    }

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getSecurityInterceptor()).addPathPatterns("/module/**").excludePathPatterns(
                "/module/go.html",
                "/module/login.html",
                "/module/register.html",
                "/module/AFK0392UEQ618NTXQ.html",
                "/module/AFK0392UEQ618NTX.html");
    }
}
