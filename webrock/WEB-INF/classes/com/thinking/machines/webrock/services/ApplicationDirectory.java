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
public class ApplicationDirectory
{
private File file;
public ApplicationDirectory(File File)
{
this.file=file;
}
public File getAttribute()
{
return this.file;
}

}