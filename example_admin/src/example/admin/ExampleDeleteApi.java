/**
 * 
 */
package example.admin;

import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import example.admin.login.LoginCheck;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月3日
 */
public class ExampleDeleteApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		// 权限检查
		new LoginCheck(httpReq).check();

		int id = jreq.getInt("id");

		String sql = "delete from example where id=" + id;
		MyC3P0Factory.execute(sql);

		return null;
	}

}
