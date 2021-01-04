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
public class jsFile extends HttpServlet
{

public void init() throws ServletException
{
try
{
ServletContext servletContext=getServletContext();
String param=(String)servletContext.getInitParameter("JS_FILE");
System.out.println(param);
File file1=new File(servletContext.getRealPath("/")+File.separator+param);
//+"WEB-INF"+File.separator+"js"+File.separator


RandomAccessFile raf1=new RandomAccessFile(file1,"r");
File file2=new File(servletContext.getRealPath("/")+File.separator+"WEB-INF"+File.separator+"js");
if(!file2.exists()) file2.mkdirs();
System.out.println(file2.getPath());

file2=new File(servletContext.getRealPath("/")+File.separator+"WEB-INF"+File.separator+"js"+File.separator+param);
System.out.println(file2.getPath());
RandomAccessFile raf2=new RandomAccessFile(file2,"rw");
while(raf1.getFilePointer()<raf1.length())
{
raf2.writeBytes(raf1.readLine());
raf2.writeBytes("\n\r");

}
raf1.close();
raf2.close();





}catch(Exception e)
{
System.out.println(e);
}

}

public void destroy()
{
//
}

}//jsFile
