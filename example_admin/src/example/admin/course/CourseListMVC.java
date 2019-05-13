/**
 * 
 */
package example.admin.course;

import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import af.web.mvc.AfSimpleMVC;
import example.admin.MyC3P0Factory;
import example.admin.db.Course;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月2日
 */
@WebServlet("/course.sudo")
public class CourseListMVC extends AfSimpleMVC
{

	@Override
	protected String execute(HttpServletRequest req, HttpServletResponse resp, HashMap<String, Object> model)
			throws Exception
	{
		//查询所有课程
		String sql = "select * from course";
		List<Course> courses = MyC3P0Factory.executeQuery(sql, Course.class);
		model.put("courses", courses);
		//返回view
		return "/course.html";
	}

}
