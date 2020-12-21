package com.example.demo.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author wangxc
 */
@Configuration
public class DruidStatViewServlet extends StatViewServlet{
        private static final long serialVersionUID = -7799874061866519411L;
        @Bean
        public ServletRegistrationBean druidStatViewServle(){
                ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
                servletRegistrationBean.addInitParameter("allow","127.0.0.1");
                servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
                servletRegistrationBean.addInitParameter("loginUsername", "admin");
                servletRegistrationBean.addInitParameter("loginPassword", "123456");
                servletRegistrationBean.addInitParameter("resetEnable", "false");
                return servletRegistrationBean;
        }

}
