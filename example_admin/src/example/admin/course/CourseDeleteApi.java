/**
 * 
 */
package example.admin.course;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import example.admin.MyC3P0Factory;
import example.admin.login.LoginCheck;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月2日
 */
public class CourseDeleteApi extends AfRestfulApi
{
	static Logger logger = Logger.getLogger(CourseDeleteApi.class);

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		// 权限检查
		new LoginCheck(httpReq).check();

		int id = jreq.getInt("id");

		String sql = "delete from course where id=" + id;
		MyC3P0Factory.execute(sql);
		logger.debug("删除课程：  " + id);

		return null;
	}

}
