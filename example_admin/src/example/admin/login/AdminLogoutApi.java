/**
 * 
 */
package example.admin.login;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import example.admin.db.Admin;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月4日
 */
public class AdminLogoutApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		
		/////// 删除application中保存的session///////
		ServletContext application = httpReq.getServletContext();
		List<HttpSession> sessions = (List<HttpSession>) application.getAttribute("sessions");

		HttpSession session = httpReq.getSession();
		String username = ((Admin) session.getAttribute("admin")).getUsername();
		
		SingleLogin.login(username, sessions);
		
		return null;
	}

}
