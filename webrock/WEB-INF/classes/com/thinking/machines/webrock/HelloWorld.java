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
public class HelloWorld extends HttpServlet
{



public void init() throws ServletException
{
System.out.println("--------------------------------hello world------------------------------");

try
{

ServletContext context=getServletContext();

String param=(String)context.getInitParameter("SERVICE_PACKAGE_PREFIX");


String folderName=param;
//File f= new File(System.getProperty("user.dir"));
File f= new File(context.getRealPath("/"));

String filePath="";
System.out.println("1"+f.getPath());
filePath=f.getPath()+File.separator+"WEB-INF"+File.separator+"classes";
System.out.println(filePath+"----");


System.out.println("2");
String fullPathOfDirectory=filePath+File.separator+param;
File file=new File(fullPathOfDirectory);
String []c1=file.list();


List<String> tmp=new ArrayList<>();
File[] files=file.listFiles();
List<File> tmpfiles=new ArrayList<>();
List<File> directory=new ArrayList<>();
for(File f1:files)
{
if(f1.isDirectory()) directory.add(f1);
else tmpfiles.add(f1);
}
files=null;
System.out.println(tmpfiles);
while(!(directory.size()==0))
{
//System.out.println(directory.size()+"size");
file=(directory.get(0));
for(File ff:file.listFiles())
{
if(ff.isDirectory()) directory.add(ff);
else tmpfiles.add(ff);
}
System.out.println(file);
directory.remove(0);
//System.out.println(directory.size());
}




List<String> mainFiles=new ArrayList<>();
String[] array=null;
for(File s1:tmpfiles) mainFiles.add(s1.getPath());
for(String s2:mainFiles)System.out.println(s2);
List<String> mainFiles1=new ArrayList<>();
int q=0;
String zz=null;
//System.out.println(main);
for(int z=0;z<mainFiles.size();z++)
{
q=0;
zz=mainFiles.get(z);
System.out.println(zz+"------------"+z);
array=null;
if(zz.endsWith(".class"))
{
array=zz.split("\\\\");
System.out.println("Splited");

for(q=0;q<array.length;q++)
{
if(array[q].equalsIgnoreCase("classes")) break; 
}//for array
System.out.println(array[q]);
q++;
zz="";
for(int i=q;i<array.length;i++)
{
if(array[i].endsWith(".class"))
{
zz=zz+array[i].substring(0,array[i].length()-6);
break;
}
zz=zz+array[i]+".";
}
array=null;
System.out.println(zz+"     __");
mainFiles1.add(zz);
}//if .class
}


System.out.println("???????????????????");

for(String s3:mainFiles1) System.out.println(s3);
List<Class> classList=new ArrayList<>();
/*
*/
//System.out.println(Class.forName("bobby.bobby1.Test1"));

System.out.println("????????????????????");


for(String s3:mainFiles1)
{
System.out.println(s3);
classList.add(Class.forName(s3));
}

/*
Class c4=Class.forName("bobby.Test");
System.out.println(c4);
classList.add(c4);
c4=Class.forName("bobby.Student_s");
System.out.println(c4);
classList.add(c4);
System.out.println("????????????????????");
//file.close();
classList.add(Class.forName("bobby.bobby1.Test1"));
classList.add(Class.forName("bobby.bobby1.emp"));
*/



for(Class c5:classList) System.out.println(c5);




WebRockModel modelObject=new WebRockModel();
Path pathOnType;
Path pathOnMethod;
Method methods[];
String fPath;
Service service=null;
Forward forwardOnType;
Forward forwardOnMethod;
GET getOnType;
GET getOnMethod;
POST postOnType;
POST postOnMethod;
injectApplicationScope ias;
injectSessionScope iss;
injectRequestScope irs;
injectApplicationDirectory iad;
SecuredAccess securedAccessOnMethod;
SecuredAccess securedAccessOnType;
JSON jom;
Startup startupOnType;
List<Integer> priorityQueue=new ArrayList<>();
List<PClass> priorityClass=new ArrayList<>();
PClass pClass;
RequestParameter rpOnMethod=null;
Parameter[] parameters;
int pflag=0;
int scopeFlag=0;
for(Class c:classList)
{
System.out.println(c+"***************************************************************************");
pflag=0;
pathOnType=(Path)c.getAnnotation(Path.class);
//
getOnType=(GET)c.getAnnotation(GET.class);
postOnType=(POST)c.getAnnotation(POST.class);
ias=(injectApplicationScope)c.getAnnotation(injectApplicationScope.class);
iss=(injectSessionScope)c.getAnnotation(injectSessionScope.class);
irs=(injectRequestScope)c.getAnnotation(injectRequestScope.class);
iad=(injectApplicationDirectory)c.getAnnotation(injectApplicationDirectory.class);
startupOnType=(Startup)c.getAnnotation(Startup.class);
securedAccessOnType=(SecuredAccess)c.getAnnotation(SecuredAccess.class);
System.out.println(getOnType+" get");
System.out.println(postOnType+" post");
System.out.println(ias+"ias");
System.out.println(iss+"iss");
System.out.println(irs+"irs");
System.out.println(iad+"iad");
System.out.println(startupOnType+"startup");
System.out.println(securedAccessOnType+" securedAccess");
//
System.out.println(pathOnType.value());
if(pathOnType==null) continue;
methods=c.getDeclaredMethods();


for(Method method:methods)
{
System.out.println(method.getName()+"-----------------------------------------------------------");
pathOnMethod=(Path)method.getAnnotation(Path.class);
//
getOnMethod=(GET)method.getAnnotation(GET.class);
postOnMethod=(POST)method.getAnnotation(POST.class);
forwardOnMethod=(Forward)method.getAnnotation(Forward.class);
if(securedAccessOnType!=null) securedAccessOnMethod=securedAccessOnType;
else securedAccessOnMethod=(SecuredAccess)method.getAnnotation(SecuredAccess.class);
jom=(JSON)method.getAnnotation(JSON.class);
parameters=method.getParameters();
scopeFlag=0;
for(Parameter p:parameters)
{
rpOnMethod=(RequestParameter)p.getAnnotation(RequestParameter.class);
System.out.println(rpOnMethod);
if(p.getType()==SessionScope.class) scopeFlag=1;
if(p.getType()==RequestScope.class) scopeFlag=1;
if(p.getType()==ApplicationScope.class) scopeFlag=1;
System.out.println("????????????????????"+p+"?????????????????????????");
}


System.out.println(getOnMethod+" get");
System.out.println(postOnMethod+" post");
System.out.println(forwardOnMethod+"  forward"  );
if(pathOnMethod==null) continue;
fPath=pathOnType.value()+pathOnMethod.value();

//setting up all the services in pojo

service=new Service();
service.setServiceClass(c);
service.setPath(pathOnMethod.value());
service.setService(method);
if(ias==null)service.setInjectApplicationScope(false);
else service.setInjectApplicationScope(true);
if(iss==null)service.setInjectSessionScope(false);
else service.setInjectSessionScope(true);
if(irs==null)service.setInjectRequestScope(false);
else service.setInjectRequestScope(true);
if(iad==null)service.setInjectApplicationDirectory(false);
else service.setInjectApplicationDirectory(true);
if(jom==null) service.setJsonRequest(false);
else service.setJsonRequest(true);
if(securedAccessOnMethod!=null) service.setSecuredAccess(true);
else service.setSecuredAccess(false);
System.out.println(securedAccessOnMethod);
if(rpOnMethod!=null)
{
service.setRequestParameter(true);
System.out.println("**************RequestPa*******************");
}
else
{
service.setRequestParameter(false);
System.out.println("**************RequestPa*******************");
}
if(scopeFlag==1) service.setRequestParameter(true);



if(startupOnType==null)
{
service.setRunOnStartup(false);
service.setPriority(-1);
}
else 
{
service.setRunOnStartup(true);
service.setPriority(startupOnType.priority());
if(pflag==0)
{
priorityQueue.add(startupOnType.priority());
pClass=new PClass();
pClass.priority=startupOnType.priority();
pClass.clas=c;
priorityClass.add(pClass);
}
pflag=1;
}
if(forwardOnMethod!=null)service.setForward(forwardOnMethod.value());
else service.setForward("null");
modelObject.model.put(fPath,service);
}//for methods
}//for classes

System.out.println(modelObject.model.size());
for(Map.Entry<String,Service> entry: modelObject.model.entrySet())
{
System.out.print(entry.getKey()+"-----------------------> :  ");
System.out.println(entry.getValue().getPath()+" -1------- "+entry.getValue().getService().getName()+"  -2---------  "+entry.getValue().getServiceClass().getName()+"  -3---------  "+entry.getValue().getForward());
}
System.out.println("*2");
context.setAttribute("model",modelObject.model);
context.setAttribute("classList",classList);
System.out.println(modelObject.model.size()+"    2");

Collections.sort(priorityQueue);
System.out.println(priorityQueue);
for(int pq:priorityQueue)
{
System.out.println(pq+"   1");
for(PClass pclss:priorityClass)
{
System.out.println(pclss.priority+"    2");
if(pq==pclss.priority) 
{
System.out.print("true");
System.out.println(pclss.clas);
Method method=pclss.clas.getDeclaredMethod("main",String[].class);
Object o[]=new Object[1];
method.invoke(null,o);

}
}
}




}catch(Exception e)
{
System.out.println(e);
}


System.out.println("--------------------------------hello world------------------------------");

}

public void destroy()
{
//
}


class PClass
{
public Class clas;
public int priority;
}





}