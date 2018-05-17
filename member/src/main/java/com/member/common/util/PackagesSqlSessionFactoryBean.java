package com.member.common.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
/**
 * 配置SqlSessionFactory对象<br>
 * 为解决typeAliasesPackage不能使用通配符的问题<br>
 * typeAliasesPackage 默认只能扫描某一个路径下，或以逗号等分割的 几个路径下的内容，不支持通配符和正则，采用重写的方式解决<br>
 * <!--扫描entity包 使用别名-->
 * <property name="typeAliasesPackage" value="com.eagle.**.vo"/>
 * @author http://blog.csdn.net/bian1729183741/article/details/52193882
 * Created by wzhz on 2016-12-06.
 * @update 重复扫描问题 wzhz 2018年1月11日 （去重方法将List暂时改为Set）
 */
public class PackagesSqlSessionFactoryBean extends SqlSessionFactoryBean {

	private static final String DEFAULT_RESOURCE_PATTERN = "*.class";

	private static Logger logger = LoggerFactory.getLogger(PackagesSqlSessionFactoryBean.class);

	@Override
	public void setTypeAliasesPackage(String typeAliasesPackage) {
		ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
		MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
		typeAliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(typeAliasesPackage) + "/" + DEFAULT_RESOURCE_PATTERN;

		// 将加载多个绝对匹配的所有Resource
		// 将首先通过ClassLoader.getResource("META-INF")加载非模式路径部分
		// 然后进行遍历模式匹配
		try {
			Set<String> packageSet = new HashSet<>();
			Resource[] resources = resolver.getResources(typeAliasesPackage);
			if (resources != null && resources.length > 0) {
				MetadataReader metadataReader = null;
				for (Resource resource : resources) {
					if (resource.isReadable()) {
						metadataReader = metadataReaderFactory.getMetadataReader(resource);
						try {
							String className = metadataReader.getClassMetadata().getClassName();
							String name = Class.forName(className).getPackage().getName();
							packageSet.add(name);
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
							logger.error(e.toString());
						} finally {
						}
					}
				}
			}
			if (packageSet != null) {
				super.setTypeAliasesPackage(StringUtils.join(packageSet.toArray(), ","));
			} else {
				logger.warn("参数typeAliasesPackage:" + typeAliasesPackage + "，未找到任何包");
			}
			logger.info("vo扫描完成!");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}

}
