/**
 * Eya.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package ${servicePackage}.impl;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.eya.core.service.BaseServiceImpl;
import ${daoPackage}.${className}Mapper;
import ${servicePackage}.${className}Service;
import ${packageName}.${className};  

 
/**
 * ${tablecomment}业务层访问接口实现
 * @author  ${author}
 * @version $Id: ${classObjectName}ServiceImpl.java, v 0.1  ${date?datetime}  ${author} Exp $
 */
@Service
public class ${className}ServiceImpl extends BaseServiceImpl<${className}> implements ${className}Service {

    /**${tablecomment}数据层访问接口实现**/
	@Autowired
	private ${className}Mapper ${classObjectName}Mapper;

 
	@PostConstruct
    public void setBaseMapper() {
        this.baseMapper =  ${classObjectName}Mapper;
    }
	 
}