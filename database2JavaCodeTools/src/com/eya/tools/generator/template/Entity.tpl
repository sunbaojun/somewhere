/**
 * Eya.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package ${packageName};
import com.eya.core.model.BaseModel;

/**
 * ${tablecomment}
 * @author  ${author}
 * @version $Id: ${className}.java, v 0.1 ${date?datetime}   ${author} Exp $
 */
public class ${className} extends BaseModel implements java.io.Serializable{

	<#list records as rec>
	/**${rec.comment}*/
	private ${rec.type}            ${rec.property};
	
	</#list>
	
	<#list records as rec> 
	
	 /**
     * Setter method for property <tt>${rec.property}</tt>.
     * 
     * @param ${rec.property} value to be assigned to property ${rec.property}
     */
	public void set${rec.property?cap_first}(${rec.type} ${rec.property}) {
		this.${rec.property} = ${rec.property};
	}
	
	 /**
     * Getter method for property <tt>${rec.property}</tt>.
     * 
     * @return ${rec.property} value of ${rec.property}
     */
	public ${rec.type} get${rec.property?cap_first}() {
		return this.${rec.property};
	}
	</#list>
}