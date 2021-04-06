import java.text.*;
import java.util.*;
//import com.thinking.machines.orm.exceptions.*;
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
System.out.println("Done1");
/*
//Course cc=new Course();
Course c=new Course();
c.title="Machine Learning";
dm.save(c);
List<Course> list=new LinkedList<>();
list=dm.query(Course.class).fire();
for(Course c:list)
System.out.println(c.code+","+c.title);

*/
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
Student s=new Student();
s.firstName="Rakhna";
s.lastName="Jainyim";
s.rollNumber=1111;
s.dateOfBirth=format. parse("2021-02-13");
s.addharCardNumber="ssssdd";
s.gender="F";
s.courseCode=1;
System.out.println("Done2");
dm.begin();
System.out.println("Done3");
dm.save(s);
System.out.println("Done4");
dm.end();
System.out.println("Done5");

/*
dm.begin();
Course c=new Course();
c.title="C++";
System.out.println(c.code);
dm.save(c);
dm.end();
System.out.println(c.code);

Course c=new Course();
c.code=4;
dm.delete(c);
*/
}catch(Exception e)
{
System.out.println(e);
}
}

}