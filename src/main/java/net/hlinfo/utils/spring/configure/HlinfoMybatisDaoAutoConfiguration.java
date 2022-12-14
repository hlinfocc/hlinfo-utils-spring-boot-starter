package net.hlinfo.utils.spring.configure;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import net.hlinfo.mybatis.dao.MybatisDao;
import net.hlinfo.mybatis.dao.impl.MybatisDaoImpl;
import net.hlinfo.mybatis.opt.HlinfoDriverClassConfig;
import net.hlinfo.mybatis.service.MybatisService;
import net.hlinfo.mybatis.service.impl.MybatisServiceImpl;

@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(HlinfoDriverClassConfig.class)
@AutoConfigureAfter({MybatisAutoConfiguration.class})
public class HlinfoMybatisDaoAutoConfiguration {
	 private HlinfoDriverClassConfig driverClassConfig;

	public HlinfoMybatisDaoAutoConfiguration(HlinfoDriverClassConfig driverClassConfig) {
		super();
		this.driverClassConfig = driverClassConfig;
	}

	/**
	 * 配置MybatisDao，注入SqlSessionTemplate
	 * @param sqlSessionTemplate
	 * @return mybatisDao实例化对象
	 */
    @Bean
    public MybatisDao mybatisDao(SqlSessionTemplate sqlSessionTemplate){
    	MybatisDao mybatisDao = new MybatisDaoImpl(sqlSessionTemplate);
    	return mybatisDao;
    }
    
    /**
     * 配置MybatisService，对MybatisDao的一些操作的扩展
     * @param mybatisDao mybatisDao实例化对象
     * @return mybatisService实例化对象
     */
    @Bean
    public MybatisService mybatisService(MybatisDao mybatisDao){
    	MybatisService mybatisService = new MybatisServiceImpl(mybatisDao,driverClassConfig);
    	return mybatisService;
    }
}
