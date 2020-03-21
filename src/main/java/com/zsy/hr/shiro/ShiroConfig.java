package com.zsy.hr.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Classname ShiroConfig
 * @Description TODO
 * @Date 2020/3/21 15:27
 * @Created by Yinghao.He
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
 //       shiroRealm.setCacheManager(cacheManager1());
        return shiroRealm;
    }
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(shiroRealm());
//        defaultWebSecurityManager.setCacheManager(cacheManager1());
//        defaultWebSecurityManager.setSessionManager(sessionManager());
        return defaultWebSecurityManager;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //可改
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/getRole");
        Map<String, Filter> filterMap=new LinkedHashMap<>();
 //       filterMap.put("kickout",kickoutSessionControlFilter());
       // filterMap.put("jwt",new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        //有序Map
        Map<String,String> map=new LinkedHashMap<>();
//        map.put("/swagger-ui.html/**","anon");
//        map.put("/swagger-resources/**", "anon");
//        map.put("/v2/api-docs", "anon");
//        map.put("/webjars/springfox-swagger-ui/**", "anon");
//        map.put("/SysUser/login","anon");
 //       map.put("/SysUser/loginOut","anon");
        //拦截绘话把session
    //    map.put("/SysUser/**","noSessionCreation,jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
    //使标签生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    //开启aop对shiro的bean的动态管理
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
//    @Bean
//    public DefaultWebSessionManager sessionManager() {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setSessionDAO(redisSessionDAO());
//        sessionManager.setCacheManager(cacheManager1());
//        return sessionManager;
//    }
//    @Bean
//    public RedisSessionDAO redisSessionDAO() {
//        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
//        redisSessionDAO.setKeyPrefix("SessionDao");
//        redisSessionDAO.setRedisManager(redisManager());
//        return redisSessionDAO;
  //  }
//    @Bean
//    public RedisManager redisManager() {
//        RedisManager redisManager = new RedisManager();
//        redisManager.setHost("106.75.48.9");
//        redisManager.setPort(32002);
//        redisManager.setExpire(1800);// 配置缓存过期时间
//        redisManager.setTimeout(5000);
//        // redisManager.setPassword(password);
//        return redisManager;
//    }
//    @Bean
//    public RedisCacheManager cacheManager1() {
//        RedisCacheManager redisCacheManager = new RedisCacheManager();
//        redisCacheManager.setRedisManager(redisManager());
//        redisCacheManager.setKeyPrefix("Cache");
//        return redisCacheManager;
//    }
//    @Bean
//    public KickoutSessionControlFilter kickoutSessionControlFilter(){
//        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
//        kickoutSessionControlFilter.setCacheManager(cacheManager1());
//        kickoutSessionControlFilter.setKickoutAfter(false);
//        kickoutSessionControlFilter.setMaxSession(1);
//        kickoutSessionControlFilter.setKickoutUrl("/SysUser/loginOut");
//        kickoutSessionControlFilter.setSessionManager(sessionManager());
//        return kickoutSessionControlFilter;
//    }
}
