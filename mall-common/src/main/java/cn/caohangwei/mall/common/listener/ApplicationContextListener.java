package cn.caohangwei.mall.common.listener;

import cn.caohangwei.mall.common.annotation.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;
import java.util.Map;

public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (null == contextRefreshedEvent.getApplicationContext().getParent()) {
            LOGGER.debug(">>>>> Spring 初始化完毕 <<<<<");
            Map<String, Object> services = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(BaseService.class);
            for (Object service : services.values()) {
                try {
                    Method method = service.getClass().getMethod("initMapper");
                    method.invoke(service);
                } catch (Exception e) {
                    LOGGER.error("Mapper 初始化失败：", e);
                }
            }
        }
    }
}
