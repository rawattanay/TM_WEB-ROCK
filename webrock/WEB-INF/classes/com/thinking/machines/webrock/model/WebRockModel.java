package com.thinking.machines.webrock.model;
import com.thinking.machines.webrock.*;
import com.thinking.machines.webrock.pojo.*;
import com.thinking.machines.webrock.annotations.*;
import java.util.*;
public class WebRockModel
{
public Map<String,Service> model;
public ArrayList<Class> classList;
public WebRockModel()
{
this.model=new HashMap<>();
this.classList=new ArrayList<>();
}
public void setClassList(ArrayList<Class> list)
{
this.classList=list;
}
public List getClassList()
{
return this.classList;
}
}