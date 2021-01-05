#### TM_WEB-ROCK
TM_WEB-ROCK is an open source Java-based framework used to create a micro Service and is used to build stand-alone and production ready spring applications.Micro Service is an architecture that allows the developers to develop and deploy services independently. Each service running has its own process and this achieves the lightweight model to support business applications.
TM_WEB-ROCK provides a good platform for Java developers to develop a stand-alone and production-grade application that you can just run. You can get started with minimum configurations.
|Packages|                                                                                         Description|
| --- | --- |
|com.thinking.machines.webrock      |                                         Provides the necessary classes at server startup to provide necessary DataStructures for faster                                                                                     response and processing.|
|com.thinking.machines.annotations     |                                      Provides the necessary annotations.|
|com.thinking.machines.pojo          |                                        Provides all the necessary details for the classes and methods that uses TM_WEB-ROCK.|
|com.thinking.machines.services       |                                       Provides the classes for different 'scopes' with respect to J2ee architecture.|
|com.thinking.machines.model           |                                      Provides necessary data structures for internal processing of TM_WEB-ROCK.|
|tm_webrock.jar                                                              Generates pdf regarding all the services privided by TM_WEB-ROCK to classes that uses it.|

# Uses and Examples

# *Providing/Mapping the name of the folder to the classes,here bobby is user package folder
```
<context-param>
<param-name>SERVICE_PACKAGE_PREFIX</param-name>
<param-value>bobby</param-value>
</context-param>
```
# *Providing/Mapping the name StartingUp classs:TM_WEB-ROCK Starter_class
```
<servlet>
<servlet-name>START_UP_SERVLET</servlet-name>
<servlet-class>com.thinking.machines.webrock.TMWebRockStarter</servlet-class>
<load-on-startup>0</load-on-startup>
</servlet>
<servlet-mapping>
<servlet-name>START_UP_SERVLET</servlet-name>
<url-pattern>/TMWebRockStarter</url-pattern>
</servlet-mapping>
```
# *Providing/Mapping the name of jsFile to load to servlet class 'getjsFle' here Department.js is the file to be mapped
```
<context-param>
<param-name>JS_FILE</param-name>
<param-value>Department.js</param-value>
</context-param>
```


# *Mapping up 'jsFile' Servlet with url as needed by user

```
<servlet>
<servlet-name>JSFILE</servlet-name>
<servlet-class>com.thinking.machines.webrock.jsFile</servlet-class>
<load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
<servlet-name>JSFILE</servlet-name>
<url-pattern>/jsFile</url-pattern>
</servlet-mapping>
```

# *Mapping the getJs File to be loaded from servlet
```
<servlet>
<servlet-name>GETJSFILE</servlet-name>
<servlet-class>com.thinking.machines.webrock.getJsFile</servlet-class>
</servlet>

<servlet-mapping>
<servlet-name>GETJSFILE</servlet-name>
<url-pattern>/getJsFile/*</url-pattern>
</servlet-mapping>
```

# *Mapping up the Services class & url pattern(according to user) that provides all the services user has annoted or marked, here 
# *url-Pattern :/schoolService  and class provided 'com.thinking.machines.webrock.TMWebRock'

```
<servlet>
<servlet-name>Service</servlet-name>
<servlet-class>com.thinking.machines.webrock.TMWebRock</servlet-class>
</servlet>

<servlet-mapping>
<servlet-name>Service</servlet-name>
<url-pattern>/schoolService/*</url-pattern>
</servlet-mapping>
```


# *Getting js File mapped through Servlet here jsFile Name:'Department.js'
```
<script src='getJsFile?name=Department.js'></script>
```

# *Loading the classes to their priority provided by user uising @Startup(priority=number)
# * here class bbb will be loaded first then aaa according to priority: having '1' highest priority
```
@Startup(priority=4)
Class aaa
{
}
---------------
@Startup(priority=1)
Class bbb
{
}
```

# *Providing Path to classes using @Path("")
```
@Path("/Test")
public class Test
{
}
```
# *declaring to inject Session Scope using @injectSeesionScope
```
@injectSessionScope
class Test
{
private SessionScope sessionScope;
public void setSessionScope(SessionScope sessionScope)
{
this.sessionScope=sessionScope;
System.out.println("set Session Scope method got called ");
}
}//class Test ends
```
# *declaring to inject ApplicationScope or RequestScope using @injectApplicationScope and @injectRequestScope
```
@injectApplicationScope
class Test
{
private ApplicationScope applicationScope;
public void setRequestScope(ApplicationScope sessionScope)
{
this.applicationScope=applicationScope;
System.out.println("set Application Scope method got called ");
}
}//class Test ends
```
# *Mapping path to methods
```
@Path("/add")
public int add(int a,int b)
{
return a+b;
}
```

# *Mapping method to forward to another Method or jsp or etc..
```
@Forward("/index.jsp")
@Path("/add")
public int add(int a,int b)
{
return a+b;
}
```
# *Setting up the class to be accept GET or POST type request
```
@GET or @POST
@Path("/aaa")
class aaa
{
}
```
# *Providing Secured Access to method that have to run before the method mapped with it
# * here class-Name: bobby.Test 
# * here method-Name: checkLoginStatus
```
@SecuredAccess(checkPost="bobby.Test",gaurd="checkLoginStatus")
public void getStudent
{
//
}
```
# *setting up the @JSON if the method accepts customClass Object or json
```
@JSON
public void printStdentDetails(Student student)
{
System.out.println(student.name);
}
```

# * setting up @RequestParameter if the method requests parameter from the scope
```
public int sub1(@RequestParameter("aaa")int a,@RequestParameter("bbb")int b)
{
return a-b;
}
```
# *Prividing value to be assigned to a field from Application,Session or RequestScope   :@AutoWired
# * uses CamelCase methods Names for setters and getters
```
Path("\aaa")
class aaa
{
@AutoWired(name="xyz")
private int autoWiredTestingField;
public void setAutoWiredTestingField(int x)
{
this.autoWiredTestingField=x;
System.out.println("setAutoTestingField got called---------------"+this.autoWiredTestingField);
}
}//class aaa ends here
```

### * Getting All the Services/Paths Provding to the annoted Classes And methods using TM_WEB-ROCK for exceution of the Services

# uses tmrock.jar
# pdf-Name: Services_Doc.jar
```
javac -classpath tmrock.jar;. ...path to the classes  ....path to the pdf to be generated

example: arg[0]--> give the absolute or relative path to folder containing classes ,arg[1]--> give the path for generated pdf to be saved named as SeviceS_Doc.pdf 

>javac -classpath tmrock.jar;c:\itext7\*; c:\tomcat9\webapps\webrock\WEB-INF\classes\bobby c:\tomcat9\webapps\webrock\pdf
```






