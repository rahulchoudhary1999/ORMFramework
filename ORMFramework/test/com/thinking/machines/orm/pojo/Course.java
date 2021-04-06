package com.thinking.machines.orm.pojo;
import com.thinking.machines.orm.annotations.*;
import java.util.*;
import java.math.*;
@Table(name="course")
public class Course{
@PrimaryKey
@AutoIncrement
@Column(name="code")
public int code;

@Column(name="title")
public String title;

}