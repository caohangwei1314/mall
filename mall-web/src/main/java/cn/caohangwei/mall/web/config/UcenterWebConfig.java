package cn.caohangwei.mall.ucenter.rpc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class UcenterWebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    UcenterArgumentResolver ucenterArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(ucenterArgumentResolver);
    }
}
