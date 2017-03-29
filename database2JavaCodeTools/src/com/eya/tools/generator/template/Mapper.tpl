<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="${daoPackage}.${className}Mapper">

	<resultMap id="BaseResultMap" type="${packageName}.${className}">
		<#list records as rec>
		<#if rec.pk>
		<id column="${rec.fieldName}" property="${rec.property}"   />
		<#else>
		<result column="${rec.fieldName}" property="${rec.property}"  />
		</#if>
		</#list>
	</resultMap>

	<sql id="Base_Column_List">
		<#list records as rec><#if rec_index == 0>${rec.fieldName}<#else>, ${rec.fieldName}</#if></#list>
	</sql>
 
	<sql id="Example_Where_Clause">  
	      <foreach collection="oredCriteria" item="criteria" separator="or">  
	        <if test="criteria.valid">  
	          <trim prefix="(" prefixOverrides="and" suffix=")">  
	            <foreach collection="criteria.criteria" item="criterion">  
	              <choose>  
	                <when test="criterion.noValue">  
	                  and    ${"${"+"criterion.condition"+"}"}
	                </when>  
	                <when test="criterion.singleValue">  
	                  and ${"${"+"criterion.condition"+"}"+" #{"+"criterion.value"+"}"}
	                </when>  
	                <when test="criterion.betweenValue">  
	                  and ${"${"+"criterion.condition"+"}"+" #{"+"criterion.value}"+" and "+"#{"+"criterion.secondValue"+"}"}  
	                </when>  
	                <when test="criterion.listValue">  
	                  and   ${"${"+"criterion.condition"+"}"}
	                  <foreach close=")" collection="criterion.value" item="listItem" open="(" separator=",">  
	                    ${"#{"+"listItem"+"}" }
	                  </foreach>  
	                </when>  
	              </choose>  
	            </foreach>  
	          </trim>  
	        </if>  
	      </foreach>  
	  </sql>
 
	   <!-- 分页查询 -->
	   <select id="selectByPaging"
			parameterType="com.eya.core.vo.BaseExample"
			resultMap="BaseResultMap">
	
			select <if test="distinct">distinct</if>
			<include refid='Base_Column_List' />
				from (select rownum r,tp.* from (select * from  ${tablename}  where
			<if test="_parameter!= null">
				<include refid="Example_Where_Clause" />
			</if> 
			<if test="orderByClause != null">order by  ${"${"}orderByClause}</if>) tp where  
			<![CDATA[ rownum <= ${"#{"}end}]]>      )  t    
			where  t.r >${"#{"}start}
		</select>
			
		<!-- 统计数据的共计的行数 -->
		<select id="selectPagingCount" parameterType="com.eya.core.vo.BaseExample" resultType="java.lang.Integer">
			select count(1)   from ${tablename}   where
			<if test="_parameter!= null">
				<include refid="Example_Where_Clause" />
			</if>
		</select>
	 
		<!-- 按条件新增对象 -->
		<insert id="insert" parameterType="${packageName}.${className}">
			insert into ${tablename}
			<selectKey keyProperty="${pkRecords[0].property}" order="BEFORE" resultType="String">
				select SYS_GUID() as  ${pkRecords[0].property}  from dual
			</selectKey>
			<trim prefix="(" suffix=")" suffixOverrides=",">
				<#list records as rec>
				<if test="${rec.property} != null">
					${rec.fieldName},
				</if>
		 		</#list>
			</trim>
			
			<trim prefix="values (" suffix=")" suffixOverrides=",">
				<#list records as rec>
				<if test="${rec.property} != null">
					${"#{"}${rec.property} },
				</if>
				</#list>
			</trim>
		</insert>
			
		<!-- 根据编号删除 -->
		<delete id="deleteByPrimaryKey" parameterType="map">
			delete from ${tablename}  where  ${pkRecords[0].fieldName}  =${"#{"}id}
		</delete>
		<!-- 批量删除 -->
		<delete id="deleteByBatch" parameterType="map">
			delete from  ${tablename}  where ${pkRecords[0].fieldName}  in
			<foreach collection="list" item="id" open="(" separator=","
				close=")">
				${"#{"}id}
			</foreach>
		</delete>
			
		<!-- 根据指定条件查询 -->
		<select id="selectByExample"
			parameterType="com.eya.core.vo.BaseExample"
			resultMap="BaseResultMap">
			select
			<include refid="Base_Column_List" />
			from ${tablename} t
			<if test="_parameter!= null">
				<include refid="Example_Where_Clause" />
			</if>
		</select>
	    
    	<!-- 根据编号查询 -->
		<select id="selectByPrimaryKey" parameterType="String" resultMap="BaseResultMap">
			select  <include refid='Base_Column_List' />
			from ${tablename}  t where t.${pkRecords[0].fieldName} = ${"#{"}id}
		</select>
  
		<!-- 按条件更新对象 -->
		<update id="updateByPrimaryKey" parameterType="${packageName}.${className}">
			update ${tablename}
			<set>
				<#list records as rec>
				<if test="${rec.property} != null">
					${rec.fieldName} = ${"#{"}${rec.property}},
				</if>
				</#list>
			</set>
			where ${pkRecords[0].fieldName} = ${"#{"}${pkRecords[0].property}}
		</update>
 
</mapper>