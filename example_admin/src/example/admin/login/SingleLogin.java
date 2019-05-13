/**
 * 
 */
package example.admin.login;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import example.admin.db.Admin;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月12日
 */
public class SingleLogin
{
	private SingleLogin()
	{

	}

	public synchronized static void login(String username, List<HttpSession> sessions)
	{
		// 遍历sessions，如果已登陆则挤下线
		Iterator<HttpSession> iter = sessions.iterator();
		while (iter.hasNext())
		{
			HttpSession session = iter.next();
			if(session == null)
			{
				iter.remove();
				continue;
			}

			Admin admin = (Admin) session.getAttribute("admin");
			if (admin.username.equals(username))
			{
				session.setAttribute("admin", null);
				iter.remove();
			}
		}
	}
}
