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
public class getJsFile extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
PrintWriter pw=response.getWriter();
response.setContentType("text/javascript");
String jsName=request.getParameter("name");
ServletContext servletContext=getServletContext();
File file=new File(servletContext.getRealPath("")+File.separator+"WEB-INF"+File.separator+"js"+File.separator+jsName);
RandomAccessFile raf=new RandomAccessFile(file,"r");
while(raf.getFilePointer()<raf.length())
{
pw.println(raf.readLine());


}
raf.close();

}catch(Exception e)
{
System.out.println(e);
}
}
}