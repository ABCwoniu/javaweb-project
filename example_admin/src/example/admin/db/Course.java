package example.admin.db; 

import af.sql.annotation.AFCOLUMNS; 
import af.sql.annotation.AFTABLE; 
import java.util.Date; 

@AFTABLE(name="course")  
@AFCOLUMNS(auto=true,generated="id") 
public class Course 
{ 
 
	public Integer id ; 
	public String title ; 


	public void setId(Integer id)
	{
		this.id=id;
	}
	public Integer getId()
	{
		return this.id;
	}
	public void setTitle(String title)
	{
		this.title=title;
	}
	public String getTitle()
	{
		return this.title;
	}

} 
 