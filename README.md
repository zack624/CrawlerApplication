project:DService
-
1. web.xml
	- spring
	
	~~~
	<context-param>
    	<param-name>contextConfigLocation</param-name>
    	<param-value>classpath*:config/spring-*.xml</param-value>
  	</context-param>
	<!--ContextLoaderListener-->
	<listener>
	   <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>	
	</listener>
	<!--spring mvc -->
	<servlet>
	  <servlet-name>springMVC</servlet-name>
	  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	  <init-param>
	    <param-name>contextConfigLocation</param-name>
	    <param-value>classpath*:config/spring-mvc.xml</param-value>
	  </init-param>
	  <load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
	  <servlet-name>springMVC</servlet-name>
	  <url-pattern>/</url-pattern>
	</servlet-mapping>
	<!--encodingFilter -->
	<filter>
	    <filter-name>encodingFilter</filter-name>
	    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
	    <init-param>
	      <param-name>encoding</param-name>
	      <param-value>UTF-8</param-value>
	    </init-param>
	    <init-param>
	      <param-name>forceEncoding</param-name>
	      <param-value>true</param-value>
	    </init-param>
  	</filter>
  	<filter-mapping>
	    <filter-name>encodingFilter</filter-name>
	    <url-pattern>/*</url-pattern>
  	</filter-mapping>
	~~~
	- log4j
	
	~~~
	<!--Log4j-->
	<context-param>
		<param-name>log4jConfigLocation</param-name>
		<param-value>classpath:/config/log4j/log4j.xml</param-value>
	</context-param>
	<listener>
		<listener-class>org.springframework.web.util.Log4jConfigListener</listener-class>
	</listener>
	~~~
	- opensession
	- jersey rest service
2. spring-mvc.xml

	~~~
	<!-- ×¢½âÉ¨Ãè°ü -->  
	<context:component-scan base-package="com.dummy" />  
	
	<!-- ¿ªÆô×¢½â -->  
	<mvc:annotation-driven />  
	
	<!-- ¼ÓÔØ¾²Ì¬ÎÄ¼þ£¬·ÀÖ¹±»À¹½Ø -->
	<mvc:resources mapping="/resourse/**" location="/resourse/" />
	
	<bean class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerExceptionResolver" />
	
	
	<!-- ¶¨ÒåÊÓÍ¼½âÎöÆ÷   p:prefix="/WEB-INF/jsp/" p:suffix=".jsp"-->    
	<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">  
	    <property name="prefix" value="/WEB-INF/jsp/"></property>  
	    <property name="suffix" value=".jsp"></property>  
	</bean>  
	
	<!-- Ö§³ÖÉÏ´«ÎÄ¼þ -->  
	<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"/> 
	   
	   
	 <!-- ·µ»Ølist»òÕßmap×ªjson -->
	 <bean class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
		<property name="messageConverters">
			<list>   
				<bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter" />
			</list>
		</property>
	</bean>
	~~~
3. spring-common.xml

	~~~
	<!-- ÅäÖÃÊý¾ÝÔ´ -->  
	<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource"  >  
	    <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>  
	    <property name="url" value="jdbc:mysql://10.179.175.100:3306/dummy?characterEncoding=utf8"></property>  
	    <property name="username" value="root"></property>  
	    <property name="password" value="root"></property> 
	    <property name="connection.characterEncoding" value="utf-8"></property> 
	</bean>  	
	<!-- ÅäÖÃSessionFactory -->  
	<bean id="sessionFactory" class="org.springframework.orm.hibernate4.LocalSessionFactoryBean"  >  
	     <property name="dataSource" ref="dataSource" />  
	     <property name="hibernateProperties">  
	         <props>  
	             <prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>  
	             <prop key="hibernate.hbm2ddl.auto">update</prop>  
	             <prop key="hibernate.show_sql">true</prop>  
	             <prop key="hibernate.format_sql">true</prop>  
	             <prop key="current_session_context_class">thread</prop>
	         </props>  
	     </property>     
	    <property name="packagesToScan">  
	        <list>  
	            <value>com.dummy.bean</value>  
	        </list>  
	    </property>  
	</bean>  	  
	<!-- ÅäÖÃÒ»¸öÊÂÎñ¹ÜÀíÆ÷ -->  
	<bean id="transactionManager" class="org.springframework.orm.hibernate4.HibernateTransactionManager">  
	    <property name="sessionFactory" ref="sessionFactory"/>  
	</bean>  	  
	<!-- ÅäÖÃÊÂÎñ£¬Ê¹ÓÃ´úÀíµÄ·½Ê½ -->  
	<bean id="transactionProxy" class="org.springframework.transaction.interceptor.TransactionProxyFactoryBean" abstract="true">    
	    <property name="transactionManager" ref="transactionManager"></property>    
	    <property name="transactionAttributes">    
	        <props>    
	            <prop key="add*">PROPAGATION_REQUIRED,-Exception</prop>    
	            <prop key="modify*">PROPAGATION_REQUIRED,-myException</prop>    
	            <prop key="del*">PROPAGATION_REQUIRED</prop>    
	            <prop key="*">PROPAGATION_REQUIRED</prop>    
	        </props>    
	    </property>    
	</bean>
	~~~
4. paging resolve
	+ model
		+ class:page
		+ field:currentpageindex¡¢everypagerecordcount¡¢totalrecordcount¡¢totalpagecount¡¢everypagerecords
		+ construction argument:currentpageindex¡¢everypagerecordcount¡¢totalrecordcount
		+ construction body:calculate totalpagecount
	+ data access object
		+ method argument:currentpageindex¡¢everypagerecordcount
		+ method body:select currentpagerecords
	+ service
		+ method argument:currentpageindex¡¢everypagerecordcount
		+ method body:select totalrecordcount and everypagerecordcount referencing dao,to construct page object.
	+ controller
		+ request parameter:currentpageindex
		+ method body:set currentpageindex 1 just when first request and set everypagerecordcount,reference service method.at last,set attribute page to jsp.
	+ jsp
		+ label:table
		+ jstl:c:forEach

	
