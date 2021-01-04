package com.thinking.machines.webrock.pojo;
import java.lang.reflect.*;
public class Service
{
private Class serviceClass;
private String path;
private Method service;
private String forward;
private boolean runOnStartup;
private boolean isGetAllowed;
private boolean isPostAllowed;
private boolean injectApplicationDirectory;
private boolean injectSessionScope;
private boolean injectApplicationScope;
private boolean injectRequestScope;
private boolean autoWired;
private int priority;
private boolean requestParameter;
private boolean jsonRequest;
private boolean securedAccess;



public void setSecuredAccess(boolean b)
{
this.securedAccess=b;
}
public boolean getSecuredAccess()
{
return this.securedAccess;
}

public void setJsonRequest(boolean b) 
{
this.jsonRequest=b;
}
public boolean getJsonRequest() 
{
return this.jsonRequest;
}
public void setRequestParameter(boolean b)
{
this.requestParameter=b;
}
public boolean getRequestParameter()
{
return this.requestParameter;
}


public void setAutoWired(boolean b)
{
this.autoWired=b;
}
public boolean getAutoWired()
{
return this.autoWired;
}


public void setRunOnStartup(boolean b)
{
this.runOnStartup=b;
}
public boolean getRunOnStartup()
{
return this.runOnStartup;
}



public void setIsGetAllowed(boolean b)
{
this.isGetAllowed=b;
}
public boolean getIsGetAllowed()
{
return this.isGetAllowed;
}

public void setIsPostAllowed(boolean b)
{
this.isPostAllowed=b;
}
public boolean getIsPostAllowed()
{
return this.isPostAllowed;
}


public void setInjectApplicationDirectory(boolean b)
{
this.injectApplicationDirectory=b;
}
public boolean getInjectApplicationDirectory()
{
return this.injectApplicationDirectory;
}


public void setInjectSessionScope(boolean b)
{
this.injectSessionScope=b;
}
public boolean getInjectSessionScope()
{
return this.injectSessionScope;
}


public void setInjectApplicationScope(boolean b)
{
this.injectApplicationScope=b;
}
public boolean getApplicationScope()
{
return this.injectApplicationScope;
}



public void setInjectRequestScope(boolean b)
{
this.injectRequestScope=b;
}
public boolean getRequestScope()
{
return this.injectRequestScope;
}


public void setPriority(int p)
{
this.priority=priority;
}
public int getPriority()
{
return this.priority;
}

public void setServiceClass(Class serviceClass)
{
this.serviceClass=serviceClass;
}
public Class getServiceClass()
{
return this.serviceClass;
}
public void setPath(String path)
{
this.path=path;
}
public String getPath()
{
return this.path;
}
public void setService(Method service)
{
this.service=service;
}
public Method getService()
{
return this.service;
}

public void setForward(String forward)
{
this.forward=forward;
}
public String getForward()
{
return this.forward;
}


}