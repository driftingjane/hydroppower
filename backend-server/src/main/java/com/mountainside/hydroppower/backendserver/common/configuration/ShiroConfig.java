package com.mountainside.hydroppower.backendserver.common.configuration;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : sxj
 * @Date : 2018/11/30 8:22
 * @Version : 1.0
 */
@Configuration
public class ShiroConfig {
    @Bean
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {

        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {

        final DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //todo session和redis
//        securityManager.setSessionManager(sessionManager());
//        securityManager.setCacheManager(redisCacheManager());
        securityManager.setRealm(getUserRealm());
        return securityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {

        final AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager());
        return new AuthorizationAttributeSourceAdvisor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {

        final DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {

        final ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        return shiroFilterFactoryBean;
    }
}
