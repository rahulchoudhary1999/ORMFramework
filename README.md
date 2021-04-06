# ORMFramework

*A java based framework for Object Relational Mapping. ORMFramework is specifically designed for MySQL Database Management System.*

## Getting Started

With this framework, go SQL-free. This framework manages all operations related to insertion, updation, retrieval and deletion.

## Prerequisites

The user needs to download the jar file and put it into the working directory or the lib path. That's it! The framework will take care of the rest.

## Usage and Examples

This framework comes with a tool (POJOGenerator) that eases all your efforts.

Just create a conf.json file like we show you in below example :-
```
{
"jdbc-driver":"com.mysql.cj.jdbc.Driver",
"connection-url":"jdbc:mysql://localhost:3306/",
"database":"tmschool",
"username":"admin",
"password":"admin",
"package-name":"com.thinking.machines.orm.pojo",
"jar-file-name":"orm.jar"
}
```

and then create a class and in that class write following code :-
```
import com.thinking.machines.orm.utils.*;
class psp
{
public static void main(String gg[])
{

try
{
POJOGenerator.generatePOJOEquivalentToTable();
}catch(Exception e)
{
System.out.println(e);
}
}

}
```
when you run that code our tool POJOGenerator generates POJO's by creating folder structure that you specified in conf.json against package-name and also create its equivalent jar file with name that you specified in conf.json against jar-file-name in dist folder.

We have tmschool database and there are two tables course and student:-

![image](https://user-images.githubusercontent.com/70053621/113697614-0e87d600-96f1-11eb-910c-0ac793d9a293.png)

![image](https://user-images.githubusercontent.com/70053621/113697670-252e2d00-96f1-11eb-8a52-0f04eaf9c239.png)

![image](https://user-images.githubusercontent.com/70053621/113697727-37a86680-96f1-11eb-86fb-79a05126d807.png)

Our tool will create file like this:-

Examples:-
```
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
```

```
package com.thinking.machines.orm.pojo;
import com.thinking.machines.orm.annotations.*;
import java.util.*;
import java.math.*;
@Table(name="student")
public class Student{
@ForeignKey(parent="course",column="code")
@Column(name="course_code")
public int courseCode;

@Column(name="gender")
public String gender;

@Column(name="date_of_birth")
public Date dateOfBirth;

@Column(name="last_name")
public String lastName;

@Column(name="addhar_card_number")
public String addharCardNumber;

@PrimaryKey
@Column(name="roll_number")
public int rollNumber;

@Column(name="first_name")
public String firstName;

}
```
### Annotations

If the user solely depends upon the tools he/she may never need to use or worry about the annotations. But if he/she manually creates the class as per framework standard following annotations will be useful:

|Annotation | Description |
| --- | ---|
| Table | This annotation is class level and must be used with the class. The value of this annotation is the table_name the user wants to map to the object. |
| Column |  The annotation is only to be used with the fields of the class. The value of this annotation is the column name of the table user wants to map with the field.|
| PrimaryKey |  Annotation used with the field that is mapped to the primary key of the table.|
| AutoIncrement | This annotation is used with the field mapped to a column which is autoincremented. |
| ForeignKey | Annotation used with the field that is mapped to the foreign key of the table.|

### DataManager

Following DataManager methods of our framework will be useful for insertion, updation, retrieval and deletion of the data:

| Return Type | Method | Description |
| --- | --- | --- |
| DataManager | **getDataManager()** |   A static method that gives you the instance of the framework. The Framework uses **Singleton** design technique. |
| void    |   **begin()** | Each and every transaction user want to do must start from begin() |
| void    |   **save(Object object)**   |   Does relational mapping of the received object and saves the information extracted into the database. |
| void   |   **delete(Object object)**  |   delete the object from the database by looking for the field mapped to the primary key in the object. |
| void   |   **update(Object object)**   |   Updates all the details of the given object into the database. |
| Query |  **query(Class<?> tableClass)**  |   Lets you data retrival from the database. |
| void   | **end()**                |   Commits the transaction. |

### Query

Following are the methods of Query class:

| Return Type | Method | Description |
| --- | --- | --- |
| Query  |    **where(String value)**   | Takes the value and adds 'WHERE' keyword followed by the value to the so far generated query string.|
| Query  |      **eq(Object value)**    | Takes the value and adds '=' followed by the value to the so far generated query string.|
| Query   |     **le(Object value)**     | Takes the value and adds '<=' followed by the value to the so far generated query string.|
| Query   |     **lt(Object value)**     | Takes the value and adds '<' followed by the value to the so far generated query string.  |             
| Query   |     **gt(Object value)**     | Takes the value and adds '>' followed by the value to the so far generated query string.|
| Query   |    **ge(Object value)**    | Takes the value and adds '>=' followed by the value to the so far generated query string.|
| Query   |    **ne(Object value)**          | Takes the value and adds '!=' followed by the value to the so far generated query string.|
| Query   |     **orderBy(String value)**     | Takes the value and adds 'ORDER BY' followed by the value to the so far generated query string.|
| Query   |     **ascending()**             | Takes the value and adds 'ASC' to the so far generated query string.|
| Query   |     **descending()**            | Takes the value and adds 'DESC' to the so far generated query string.|
| Query   |     **and(String value)**         | Takes the value and adds 'AND' to the so far generated query string.|
| Query   |     **or(String value)**          | Takes the value and adds 'OR' to the so far generated query string.|
| List     |     **fire()**                 | Return the ```java.util.List``` type of object.|

         
Implementation(how to perform operations) :
-------------------------------------------
1)First Example will show you how to do save operation.

```
import java.text.*;
import java.util.*;
import com.thinking.machines.orm.exceptions.*;
import com.thinking.machines.orm.annotations.*;
import com.thinking.machines.orm.framework.*;
import com.thinking.machines.orm.pojo.*;

class psp
{
public static void main(String gg[])
{
try
{
DataManager dm=DataManager.getDataManager();

Course c=new Course();
c.title="Machine Learning";
dm.begin();
dm.save(c);
dm.end();

SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
Student s=new Student();
s.firstName="Rahul";
s.lastName="Choudhary";
s.rollNumber=161;
s.dateOfBirth=format. parse("1999-07-16");
s.addharCardNumber="aaaa";
s.gender="M";
s.courseCode=1;
dm.begin();
dm.save(s);
dm.end();

}catch(Exception e)
{
System.out.println(e);
}
}
}
```
2)Second Example will show you how to do update operation.

```
import java.text.*;
import java.util.*;
import com.thinking.machines.orm.exceptions.*;
import com.thinking.machines.orm.annotations.*;
import com.thinking.machines.orm.framework.*;
import com.thinking.machines.orm.pojo.*;

class psp
{
public static void main(String gg[])
{
try
{
DataManager dm=DataManager.getDataManager();

SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
Student s=new Student();
s.firstName="Twinkle";
s.lastName="Choudhary";
s.rollNumber=161;
s.dateOfBirth=format. parse("1996-02-07");
s.addharCardNumber="aaaa";
s.gender="F";
s.courseCode=1;
dm.begin();
dm.update(s);
dm.end();

}catch(Exception e)
{
System.out.println(e);
}
}
}
```
3)Third Example will show you how to do delete operation.

```
import java.text.*;
import java.util.*;
import com.thinking.machines.orm.exceptions.*;
import com.thinking.machines.orm.annotations.*;
import com.thinking.machines.orm.framework.*;
import com.thinking.machines.orm.pojo.*;

class psp
{
public static void main(String gg[])
{
try
{
DataManager dm=DataManager.getDataManager();

Course c=new Course();
c.code=4;
dm.begin();
dm.delete(c);
dm.end();

}catch(Exception e)
{
System.out.println(e);
}
}
}
```
4)Fourth Example will show you how to do retrieval operation.

```
import java.text.*;
import java.util.*;
import com.thinking.machines.orm.exceptions.*;
import com.thinking.machines.orm.annotations.*;
import com.thinking.machines.orm.framework.*;
import com.thinking.machines.orm.pojo.*;

class psp
{
public static void main(String gg[])
{
try
{
DataManager dm=DataManager.getDataManager();
dm.begin();
List<Student> list=dm.query(Student.class).where("rollNumber").eq(1).and().where("name").eq("Rahul Choudhary").fire();
dm.end();
dm.begin();
List<Student> list2=dm.query(Student.class).orderBy("firstName").fire();
dm.end();
dm.begin();
List<Student> list5=dm.query(Student.class).where("rollNumber").gt(1).orderBy("lastName").fire();
dm.end();
dm.begin();
List<Student> list3=dm.query(Student.class).where("rollNumber").gt(1).orderBy("firstName").descending().orderBy("rollNumber").fire();
dm.end();
for(Student ss:list3)
{
	System.out.println("roll_no::"+ss.rollNumber+"   ,Name::"+ss.firstName+"   ,gender::"+ss.gender);
}
}catch(Exception e)
{
System.out.println(e);
}
}
}
```
