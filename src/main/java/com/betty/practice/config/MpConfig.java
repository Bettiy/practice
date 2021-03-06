package com.betty.practice.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.betty.practice.component.MyOptimisticLockerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @desc MybatisPlus配置
 * @author Betty
 */
@Configuration
public class MpConfig {

    /**
     * 旧版--乐观锁插件
     */
    /*@Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }*/

    /**
     * 新版
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // 添加乐观锁插件
        mybatisPlusInterceptor.addInnerInterceptor(new MyOptimisticLockerInterceptor());
        return mybatisPlusInterceptor;
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        return new PaginationInnerInterceptor();
    }

    /**
     * 逻辑删除插件
     */
    /*@Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }*/

    /**
     * SQL执行性能分析插件
     * 开发环境使用，线上不推荐。maxTime指的是sql最大执行时长
     *
     * 三种环境
     * dev:开发环境
     * test:测试环境
     * prod:生产环境
     *
     * mybatisPlus-3.3.0以上移除了SQL执行性能分析插件
     */
    /*@Bean
    @Profile({"dev","test"})//设置dev test环境开启
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //ms,超过此处设置的ms则sql不执行
        performanceInterceptor.setMaxTime(3000);
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }*/

}
