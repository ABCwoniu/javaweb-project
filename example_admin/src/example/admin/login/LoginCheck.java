/**
 * 
 */
package example.admin.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import af.web.restful.AfRestfulException;
import example.admin.db.Admin;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月4日
 */
public class LoginCheck
{
	private HttpSession session;
	
	public LoginCheck(HttpServletRequest req)
	{
		this.session = req.getSession();
	}
	public LoginCheck(HttpSession session)
	{
		this.session = session;
	}
	
	public Admin check() throws Exception
	{
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null)
			throw new AfRestfulException(-90, "用户未登录");	
		
		return admin;
	}

}
