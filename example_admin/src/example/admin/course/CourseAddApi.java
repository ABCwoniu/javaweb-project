/**
 * 
 */
package example.admin.course;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import example.admin.MyC3P0Factory;
import example.admin.db.Course;
import example.admin.login.LoginCheck;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月2日
 */
public class CourseAddApi extends AfRestfulApi
{
	static Logger logger = Logger.getLogger(CourseAddApi.class);

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		// 权限检查
		new LoginCheck(httpReq).check();

		String title = jreq.getString("title");

		Course course = new Course();
		course.title = title;

		MyC3P0Factory.insert(course);
		logger.debug("添加课程：  " + title);
		return null;
	}

}
