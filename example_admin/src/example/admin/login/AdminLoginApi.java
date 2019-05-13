/**
 * 
 */
package example.admin.login;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import example.admin.MyC3P0Factory;
import example.admin.db.Admin;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月4日
 */
public class AdminLoginApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		String username = jreq.getString("username");
		String password = jreq.getString("password");

		String sql = String.format("select * from admin where username='%s'", username);
		Admin admin = (Admin) MyC3P0Factory.get(sql, Admin.class);

		if (admin == null)
			throw new Exception("用户名不存在！");
		if (!password.equals(admin.password))
			throw new Exception("密码错误");
		//单一登陆
		login(admin);

		JSONObject jAdmin = new JSONObject(admin);
		jAdmin.remove("password");
		return jAdmin;
	}

	private void login(Admin admin)
	{
		HttpSession session = httpReq.getSession();
		session.setAttribute("admin", admin);

		ServletContext application = httpReq.getServletContext();
		List<HttpSession> sessions = (List<HttpSession>) application.getAttribute("sessions");
		// 单一登陆检查
		SingleLogin.login(admin.username, sessions);
		sessions.add(session);

	}

}
