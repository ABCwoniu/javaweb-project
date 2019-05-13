/**
 * 
 */
package example.admin.chapter;

import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import af.web.AfHttpReqParams;
import af.web.mvc.AfSimpleMVC;
import example.admin.MyC3P0Factory;
import example.admin.db.Chapter;
import example.admin.db.Course;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月3日
 */
@WebServlet("/chapter.sudo")
public class ChapterListMVC extends AfSimpleMVC
{

	@Override
	protected String execute(HttpServletRequest req, HttpServletResponse resp, HashMap<String, Object> model)
			throws Exception
	{
		AfHttpReqParams params = new AfHttpReqParams(req);
		int id = params.getInt("id", 0);
		if (id == 0)
			throw new Exception("未传入id");
		
		String sql = "select * from course where id=" + id;
		Course course = (Course) MyC3P0Factory.get(sql, Course.class);
		model.put("courseJ", new JSONObject(course));

		if (true)
		{
			String s = "select * from chapter where course=" + id + " order by number asc";
			List<Chapter> rows = MyC3P0Factory.executeQuery(s, Chapter.class);
			model.put("chaptersJ", new JSONArray(rows));
		}

		return "/chapter.html";
	}

}
