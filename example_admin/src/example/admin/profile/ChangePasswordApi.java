/**
 * 
 */
package example.admin.profile;

import org.json.JSONObject;

import af.sql.AfSqlUpdate;
import af.sql.AfSqlWhere;
import af.web.restful.AfRestfulApi;
import example.admin.MyC3P0Factory;
import example.admin.db.Admin;
import example.admin.login.LoginCheck;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月4日
 */
public class ChangePasswordApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		// 权限检查
		Admin admin = new LoginCheck(httpReq).check();
		
		String newPassword = jreq.getString("newPassword");
		String confPassword = jreq.getString("confPassword");
		if(newPassword.length() == 0)
		{
			throw new Exception("密码不能为空");
		}
		if(! newPassword.equals(confPassword))
		{
			throw new Exception("两次输入密码不一致");
		}
		if(admin.password.equals(newPassword))
		{
			throw new Exception("不能修改为原密码，请重新输出");
		}
		
		AfSqlUpdate update = new AfSqlUpdate("admin");
		update.add("password", newPassword);
		AfSqlWhere where = new AfSqlWhere();
		where.add2("username", admin.username);
		String sql = update.toString() + where;
		MyC3P0Factory.execute(sql);
		
		//注销登录
		httpReq.getSession().setAttribute("admin", null);
		
		return null;
	}

}
