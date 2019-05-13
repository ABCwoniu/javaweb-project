/**
 * 
 */
package example.admin.login;

import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import example.admin.db.Admin;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月13日
 */
public class ConnectionTestPeriodApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		Admin admin = (Admin) httpReq.getSession().getAttribute("admin");
		JSONObject data = new JSONObject();
		data.put("status", 0);
		if(admin == null)
			data.put("status", -1);
				
		return data;
	}

}
