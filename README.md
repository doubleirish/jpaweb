# jpaWebXml
JPA and Spring Web integration with XML based config

Uses an embedded derby server with auto-populated tables 
to display a list of books on a web page from rows stored in a BOOKS TABLE
 - ```May 26th Added OpenEntityManagerInViewFilter to prevent lazy init errors on associations ```
 - ```May 27th Added Sitemesh so headers,footers and nav menus don't need to be repeated on every page ```

Build
-----

cd into the jpaweb project directory 
mvn package 

Install
-------
COPY /Y  target \jpaweb-0.0.1-SNAPSHOT.war  c:\tomcat\webapps\ROOT.war

Run
---
cd c:\tomcat\bin
catalina.BAT run 


Open Browser
------------
http://localhost:8080/home.html

You should see the Book listing  page 

Change Datasource
-----------------
The datasource used is currently defined in the following file 

jpaweb\src\main\resources\jpaweb-spring.xml

if you want to switch to a standalone datasource then you can comment out the embedded datasource and switch tp the 
datasource-production-standalone.xml.  you can choose the location of the derby datasource

This Sample webapp does not use a JNDI defined datasource.  

A separate branch will be used to illustrate how to connect to a JNDI datasource resource defined in tomcat/s server.xml 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	   					   http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--<import resource="datasource-production-standalone.xml"/>-->

   <import resource="datasource-production-embedded-prepop.xml"/>

   <import resource="services-spring.xml"/>

</beans>
```



- TODO add SiteMesh with Header,Footer and Nav panels
- TODO Enable Caching in Java Config
- TODO Enable Pagination in book list

