package cn.qxhua21.words.server;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringConfigTool implements ApplicationContextAware {// extends ApplicationObjectSupport{
    private static ApplicationContext ac =
            new ClassPathXmlApplicationContext("application.xml");
    private static SpringConfigTool springConfigTool = null;
    public synchronized static SpringConfigTool init() {
        if(springConfigTool == null) {
            springConfigTool= new SpringConfigTool();
        }
        return springConfigTool;
    }
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac= applicationContext;
    }
    public synchronized static Object getBean(String beanName) {
        return ac.getBean(beanName);
    }
}