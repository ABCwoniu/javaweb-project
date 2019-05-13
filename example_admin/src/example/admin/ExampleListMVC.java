/**
 * 
 */
package example.admin;

import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import af.web.mvc.AfSimpleMVC;
import example.admin.db.Chapter;
import example.admin.db.Course;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月3日
 */
@WebServlet("/example.sudo")
public class ExampleListMVC extends AfSimpleMVC
{

	@Override
	protected String execute(HttpServletRequest jreq, HttpServletResponse jresp, HashMap<String, Object> model)
			throws Exception
	{
		String sql = "select * from course order by id asc";
		List<Course> courses = MyC3P0Factory.executeQuery(sql, Course.class);
		model.put("coursesJ", new JSONArray(courses));
		
		if(true)
		{
			String s = "select * from chapter order by course asc, number asc";
			List<Chapter> chapters = MyC3P0Factory.executeQuery(s, Chapter.class);
			model.put("chaptersJ", new JSONArray(chapters));
		}
		
		return "/example.html";
	}

}
