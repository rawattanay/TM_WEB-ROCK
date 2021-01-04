package com.thinking.machines.webrock.services;
import com.thinking.machines.webrock.pojo.*;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.model.*;
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
public class ApplicationScope
{
private ServletContext servletContext;
public ApplicationScope(ServletContext session)
{
this.servletContext=session;
}


public void setAttribute(String key,Object value)
{
this.servletContext.setAttribute(key,value);
}
public Object getAttribute(String key)
{
return this.servletContext.getAttribute(key);
}

}