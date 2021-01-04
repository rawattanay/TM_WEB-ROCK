package com.thinking.machines.webrock;
import com.thinking.machines.webrock.pojo.*;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.model.*;
import com.thinking.machines.webrock.services.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.Map.Entry.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.lang.reflect.*;
import java.io.File;
import java.lang.annotation.*; 
import java.lang.annotation.RetentionPolicy; 
import com.google.gson.*;
public class TMWebRock extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
System.out.println(request.getServletPath());   // /servlet/MyServlet
System.out.println(request.getPathInfo());         // /a/b;c=123
String jsonString=null;
ServletContext context=getServletContext();
Map<String,Service> model=(Map)context.getAttribute("model");
String fullPath=request.getPathInfo();
System.out.println(   request.getParameter("className") +"   1");
jsonString=request.getParameter("jsonObject");
System.out.println(   jsonString +"   1");
System.out.println(">>>>>>>>>>>>>>>");
Object obj=QueryProcessor.Process(model,fullPath,request,response,"GET",context);
System.out.println(obj);
PrintWriter pw=response.getWriter();
response.setContentType("text/html");
Gson gson=new Gson();
pw.println(gson.toJson(obj));
}
catch(Exception e)
{
try
{
response.sendError(HttpServletResponse.SC_NOT_FOUND);
}catch(Exception ee)
{
System.out.println(ee);
}
System.out.println(e);
}
}//doGet

public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
System.out.println(request.getServletPath());   // /servlet/MyServlet
System.out.println(request.getPathInfo());         // /a/b;c=123
ServletContext context=getServletContext();
Map<String,Service> model=(Map)context.getAttribute("model");
String fullPath=request.getPathInfo();
Object obj=QueryProcessor.Process(model,fullPath,request,response,"POST",context);
System.out.println(obj);
PrintWriter pw=response.getWriter();
response.setContentType("text/html");
pw.println(obj);
}
catch(Exception e)
{
try
{
response.sendError(HttpServletResponse.SC_NOT_FOUND);
}catch(Exception ee)
{
System.out.println(ee);
}
System.out.println(e);
}
}//doPost
}//class TMWebRock

class QueryProcessor extends HttpServlet
{
public static Object Process(Map<String,Service> model,String fullPath,HttpServletRequest request,HttpServletResponse response,String requestType,ServletContext context)
{
Object obj=null;
Class clss=null;
ArrayList<Class> cl=(ArrayList)context.getAttribute("classList");
try
{
String s[]=fullPath.split("/");
System.out.println(s.length+"S");
System.out.println(s[0]);
System.out.println(s[1]);
String queryPath,attributePath;
queryPath="/"+s[1]+"/"+s[2];
System.out.println("queryPath-->"+queryPath);
Object a[]=new Object[s.length-3];
System.out.println(a.length+"A");
Service service=model.get(queryPath);
System.out.println(service+"<----service");
clss=service.getServiceClass();

Object classObject=clss.newInstance();
//--code to find whether a class is @GET or @POST annotation
AutoWired autoWiredOnField;
Field[] fields=clss.getDeclaredFields();

Method m=null;
System.out.println("*"); 
String fieldSetter;
String fieldMethodName;
Object o2;
HttpSession sessionScopeObject=null;
ServletContext applicationScopeObject=null;

for(int x=0;x<fields.length;x++)
{
try
{
autoWiredOnField=(AutoWired)fields[x].getAnnotation(AutoWired.class);
fieldSetter=fields[x].getName();
fieldMethodName="set"+fieldSetter.substring(0,1).toUpperCase() + fieldSetter.substring(1);
System.out.println(fieldMethodName);
System.out.println(autoWiredOnField.name());
Object o1=fields[x].getType();
System.out.println(o1);
Class c1=fields[x].getType();
System.out.println(c1);

sessionScopeObject=request.getSession();
int xx=20;
sessionScopeObject.setAttribute("xyz",xx);
o2=sessionScopeObject.getAttribute(autoWiredOnField.name());

try
{
m=clss.getMethod(fieldMethodName,fields[x].getType());
m.invoke(classObject,sessionScopeObject.getAttribute(autoWiredOnField.name()));
}catch(Exception ee)
{
//response.sendError(HttpServletResponse.SC_NOT_FOUND);
System.out.println(ee);
}

try
{
ServletContext applicationScope=request.getServletContext();
o2=applicationScope.getAttribute(autoWiredOnField.name());
m=clss.getMethod(fieldMethodName,fields[x].getType());
m.invoke(classObject,applicationScope.getAttribute(autoWiredOnField.name()));
}catch(Exception ee)
{
//response.sendError(HttpServletResponse.SC_NOT_FOUND);
System.out.println(ee);
}


try
{
o2=request.getAttribute(autoWiredOnField.name());
if(o2.getClass().isInstance(c1))
m=clss.getMethod(fieldMethodName,fields[x].getType());
m.invoke(classObject,request.getAttribute(autoWiredOnField.name()));
}catch(Exception ee)
{
//response.sendError(HttpServletResponse.SC_NOT_FOUND);
System.out.println(ee);
}

}catch(Exception e)
{
//response.sendError(HttpServletResponse.SC_NOT_FOUND);
System.out.println(e);
}
}//for fields

GET getOnType=(GET)clss.getAnnotation(GET.class);
POST postOnType=(POST)clss.getAnnotation(POST.class);
boolean eligible=false;
SessionScope ss=null;
RequestScope rs =null;
ApplicationScope as=null;
//
System.out.println(service.getInjectSessionScope()+"----------");
if(service.getInjectSessionScope())
{
for(int x=0;x<fields.length;x++)
{
if(fields[x].getType().toString().equalsIgnoreCase(SessionScope.class.toString()))
{
String fieldName=fields[x].getName();
String methodName="set"+fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
System.out.println(methodName);
//Method m=null;
try
{
m=clss.getMethod(methodName,SessionScope.class);
HttpSession session=request.getSession();
ss=new SessionScope(session);
m.invoke(classObject,ss);
}catch(Exception e) 
{
//response.sendError(HttpServletResponse.SC_NOT_FOUND);
System.out.println(e);
}
}
}//for(fields)
}//if InjectSessionScope

if(service.getApplicationScope())
{
for(int x=0;x<fields.length;x++)
{
if(fields[x].getType().toString().equalsIgnoreCase(ApplicationScope.class.toString()))
{
String fieldName=fields[x].getName();
String methodName="set"+fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
System.out.println(methodName);
try
{
m=clss.getMethod(methodName,ApplicationScope.class);
ServletContext session=request.getServletContext();
as=new ApplicationScope(session);
m.invoke(classObject,as);
}catch(Exception e)
{
//response.sendError(HttpServletResponse.SC_NOT_FOUND);
System.out.println(e);
}
}
}//for(fields)
}//if InjectApplicationScope

if(service.getRequestScope())
{
for(int x=0;x<fields.length;x++)
{
if(fields[x].getType().toString().equalsIgnoreCase(RequestScope.class.toString()))
{
String fieldName=fields[x].getName();
String methodName="set"+fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
System.out.println(methodName);
try
{
m=clss.getMethod(methodName,RequestScope.class);
if(m==null) continue;
rs=new RequestScope(request);
m.invoke(classObject,rs);
}catch(Exception e)
{
//response.sendError(HttpServletResponse.SC_NOT_FOUND);
System.out.println(e);
}//done
}
}//for(fields)
}//if InjectRequestScope

//Check if GET Type Request Or POST Type request is valid or not
if(requestType.equalsIgnoreCase("GET"))
{
if(getOnType==null && postOnType==null) eligible=true;
if(getOnType==null && postOnType!=null) eligible=false;
if(getOnType!=null && postOnType==null) eligible=true;
System.out.println(eligible+"1");
}
System.out.println(getOnType);
System.out.println(postOnType);

if(requestType.equalsIgnoreCase("POST"))
{
if(getOnType==null && postOnType==null) eligible=true;
if(getOnType!=null && postOnType==null) eligible=false;
if(getOnType==null && postOnType!=null) eligible=true;
System.out.println(eligible+"2");
}

if(!eligible) response.sendError(HttpServletResponse.SC_BAD_REQUEST);
System.out.println(eligible);
//End of Check of GET Type Request Or POST Type request is valid or not

String path=service.getPath();
Method method=service.getService();
//code if Secured Access OnMethod

System.out.println(service.getSecuredAccess()+"     SecuredAccess");
SecuredAccess securedAccessOnMethod=(SecuredAccess)method.getAnnotation(SecuredAccess.class);
if(service.getSecuredAccess() && securedAccessOnMethod!=null)
{
try
{
String securedClassName=securedAccessOnMethod.checkPost();
String gaurdMethod=securedAccessOnMethod.gaurd();
System.out.println(securedClassName);
System.out.println(gaurdMethod);
Class cs1=null;
for(Class c4:cl) if(securedClassName.equals(c4.getName())) cs1=c4;
if(cs1==null) 
{
response.sendError(HttpServletResponse.SC_NOT_FOUND);
System.out.println("Class not found as in given in @securedAccess");
}

Object obj1=cs1.newInstance();
Method m2[]=cs1.getDeclaredMethods();
Method m1=null;
for(Method m3:m2) if(m3.getName().equals(gaurdMethod)) m1=m3;
//Method m1=cs1.getDeclaredMethod(gaurdMethod);

Parameter parameters1[]=null;
parameters1=m1.getParameters();
Object[] args1=new Object[parameters1.length];
int flag1=0;
for(int i=0;i<parameters1.length;i++)
{
if(parameters1[i].getType()==SessionScope.class) 
{
if(ss==null) args1[i]=new SessionScope(request.getSession());
else args1[i]=ss;
flag1=1;
}
if(parameters1[i].getType()==RequestScope.class) 
{
if(rs==null) args1[i]=new RequestScope(request);
else args1[i]=rs;
flag1=1;
}

if(parameters1[i].getType()==ApplicationScope.class) 
{
if(as==null) args1[i]=new ApplicationScope(request.getServletContext());
else args1[i]=as;
flag1=1;
}
System.out.println(parameters1[i]+"------------------------------");
}//parameter loop ends here
//done;
System.out.println(parameters1.length);
System.out.println(args1.length);
System.out.println(m1.toString());
if(parameters1.length==0) m1.invoke(obj1);
else m1.invoke(obj1,args1);
}catch(Exception e)
{
response.sendError(HttpServletResponse.SC_NOT_FOUND);
System.out.println(e);
}
}
//code if Secured Access OnMethod ends here
Parameter[] parameters=null;
if(service.getRequestParameter() || service.getJsonRequest())
{
Class cs=null;
String jsonString=null;
Gson gson=null;
Object aa=null;

parameters=method.getParameters();
Object[] args=new Object[parameters.length];
int flag1=0;
for(int i=0;i<parameters.length;i++)
{
System.out.println(parameters[i].getType()+"-----parameters");

if(parameters[i].getType()==int.class && parameters[i].getAnnotation(RequestParameter.class)!=null) 
{
args[i]=(int)request.getAttribute(     ((RequestParameter)parameters[i].getAnnotation(RequestParameter.class)).value()      );
flag1=1;
}
if(parameters[i].getType()==String.class && parameters[i].getAnnotation(RequestParameter.class)!=null) 
{
args[i]=(String)request.getAttribute(     ((RequestParameter)parameters[i].getAnnotation(RequestParameter.class)).value()      );
flag1=1;
}
if(parameters[i].getType()==boolean.class && parameters[i].getAnnotation(RequestParameter.class)!=null) 
{
args[i]=(boolean)request.getAttribute(     ((RequestParameter)parameters[i].getAnnotation(RequestParameter.class)).value()      );
flag1=1;
}
if(parameters[i].getType()==byte.class && parameters[i].getAnnotation(RequestParameter.class)!=null) 
{
args[i]=(byte)request.getAttribute(     ((RequestParameter)parameters[i].getAnnotation(RequestParameter.class)).value()      );
flag1=1;
}
if(parameters[i].getType()==long.class && parameters[i].getAnnotation(RequestParameter.class)!=null) 
{
args[i]=(long)request.getAttribute(     ((RequestParameter)parameters[i].getAnnotation(RequestParameter.class)).value()      );
flag1=1;
}
if(parameters[i].getType()==double.class && parameters[i].getAnnotation(RequestParameter.class)!=null) 
{
args[i]=(double)request.getAttribute(     ((RequestParameter)parameters[i].getAnnotation(RequestParameter.class)).value()      );
flag1=1;
}
if(parameters[i].getType()==float.class && parameters[i].getAnnotation(RequestParameter.class)!=null) 
{
args[i]=(float)request.getAttribute(     ((RequestParameter)parameters[i].getAnnotation(RequestParameter.class)).value()      );
flag1=1;
}
if(parameters[i].getType()==short.class && parameters[i].getAnnotation(RequestParameter.class)!=null) 
{
args[i]=(short)request.getAttribute(     ((RequestParameter)parameters[i].getAnnotation(RequestParameter.class)).value()      );
flag1=1;
}
if(parameters[i].getType()==char.class && parameters[i].getAnnotation(RequestParameter.class)!=null) 
{
String ss1=(String)request.getAttribute(     ((RequestParameter)parameters[i].getAnnotation(RequestParameter.class)).value()      );
args[i]=ss1.charAt(0);
flag1=1;
}
if(parameters[i].getType()==SessionScope.class) 
{
if(ss==null) args[i]=new SessionScope(request.getSession());
else args[i]=ss;
flag1=1;
}
if(parameters[i].getType()==RequestScope.class) 
{
if(rs==null) args[i]=new RequestScope(request);
else args[i]=rs;
flag1=1;
}
if(parameters[i].getType()==ApplicationScope.class) 
{
if(as==null) args[i]=new ApplicationScope(request.getServletContext());
else args[i]=as;
flag1=1;
}
//if(flag1==0) args[i]=(parameters[x].getType().class) request.getAttribute(     ((RequestParameter)parameters[i].getAnnotation(RequestParameter.class)).value()      );
if(request.getParameter("jsonObject")!=null && flag1==0)
{
flag1=1;
System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
if(i!=0) response.sendError(HttpServletResponse.SC_BAD_REQUEST);

jsonString=request.getParameter("jsonObject");
String className=request.getParameter("className");
for(Class cc:cl)
{
System.out.println("@");
System.out.println(cc.toString());
System.out.println(cc.getName());
System.out.println(className);
System.out.println(cc.getName().equalsIgnoreCase(className));
if(cc.getName().equalsIgnoreCase(className)) cs=cc;
}//for class
System.out.println("-1");
if(cs==null) response.sendError(HttpServletResponse.SC_NOT_FOUND);
System.out.println(cs);
System.out.println(jsonString);
try
{
gson=new Gson();
System.out.println("?");
aa=gson.fromJson(jsonString,cs);
args[i]=aa;
System.out.println(i);
System.out.println("2");
}catch(Exception jsone)
{
response.sendError(HttpServletResponse.SC_NOT_FOUND);
System.out.println(jsone);
}
}
}
for(int i=0;i<parameters.length;i++)
{
System.out.println("------------");
System.out.println(parameters[i]);
System.out.println(args[i]);
System.out.println("++++++");
}

System.out.println(args.length+" ------------argument length");
System.out.println(parameters.length+" ------------parameters length");
System.out.println(args[0]+" ------------argument[0]");
System.out.println(classObject+" ------------invoking Clas Object class Test1");
System.out.println(method.toString()+" ------------method Name.toString");
System.out.println(clss+" ------------invoking Class Instance()");
obj=method.invoke(classObject,args);//done
obj="Done";
}//if.getRequestParameter
else
{
//---code to extract parameter size and invoke
int paramSize=method.getParameters().length;
for(int x=3;x<s.length;x++)
{
a[x-3]=Integer.valueOf(s[x]);
}
if(method.getParameters().length>1)obj=method.invoke(classObject,a);
else obj=method.invoke(classObject,a);
}//else

String forwardPath=service.getForward();
System.out.println("forwatd Path:  "+forwardPath);
if(forwardPath.equalsIgnoreCase("null")) return obj;
else
{
String pathArray[]=forwardPath.split("/");
System.out.println(pathArray.length+"  length");
if(pathArray.length>2) 
{
System.out.println("/"+pathArray[1]+"/"+pathArray[2]);
if(model.get("/"+pathArray[1]+"/"+pathArray[2])!=null) return Process(model,forwardPath,request,response,requestType,context);
}
else
{
RequestDispatcher requestDispatcher=request.getRequestDispatcher(forwardPath);
requestDispatcher.forward(request,response);
return null;
}
}
}catch(Throwable e)
{
try
{response.sendError(HttpServletResponse.SC_NOT_FOUND);
}catch(Exception e1) {System.out.println(e);}
System.out.println("??");
System.out.println(e);
System.out.println(e.getCause());
//e.printStackTrace();
//System.out.println()
}
return obj;
}

}



