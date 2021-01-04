package com.thinking.machines.webrock.annotations;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)  
public @interface Startup
{
public int priority() default 0;
}