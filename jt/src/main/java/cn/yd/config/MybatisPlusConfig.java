package cn.yd.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 什么是bean spring 容器管理的对象叫做bean
 * */
//什么是bean spring容器管理的对象叫做bean
@Configuration //标识这是一个配置类 相当于早期的xml文件
public class MybatisPlusConfig {

    /**
     * @return
     * @Bean作用: 将方法的返回值交给Spring容器管理
     * JS-钩子函数-
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MARIADB));
        return interceptor;
    }


  /* @Bean
   public User user(){
       User user = new User();
       user.setId(10).setUsername("xxxxx");
       return user;
   }*/
}
