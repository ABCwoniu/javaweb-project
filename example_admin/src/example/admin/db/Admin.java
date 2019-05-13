package example.admin.db; 

import af.sql.annotation.AFCOLUMNS; 
import af.sql.annotation.AFTABLE; 
import java.util.Date; 

@AFTABLE(name="admin")  
@AFCOLUMNS(auto=true,generated="id") 
public class Admin 
{ 
 
	public Integer id ; 
	public String username ; 
	public String password ; 
	public String cellphone ; 
	public Date timeCreated ; 


	public void setId(Integer id)
	{
		this.id=id;
	}
	public Integer getId()
	{
		return this.id;
	}
	public void setUsername(String username)
	{
		this.username=username;
	}
	public String getUsername()
	{
		return this.username;
	}
	public void setPassword(String password)
	{
		this.password=password;
	}
	public String getPassword()
	{
		return this.password;
	}
	public void setCellphone(String cellphone)
	{
		this.cellphone=cellphone;
	}
	public String getCellphone()
	{
		return this.cellphone;
	}
	public void setTimeCreated(Date timeCreated)
	{
		this.timeCreated=timeCreated;
	}
	public Date getTimeCreated()
	{
		return this.timeCreated;
	}

} 
 