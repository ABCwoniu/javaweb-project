/**
 * 
 */
package example.admin;

import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import af.web.AfHttpReqParams;
import af.web.mvc.AfSimpleMVC;
import example.admin.db.Chapter;
import example.admin.db.Example;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月3日
 */
@WebServlet("/example_edit.sudo")
public class ExampleEditMVC extends AfSimpleMVC
{

	@Override
	protected String execute(HttpServletRequest req, HttpServletResponse resp, HashMap<String, Object> model)
			throws Exception
	{
		AfHttpReqParams params = new AfHttpReqParams(req);
		int chapterId = params.getInt("id", 0);
		String courseTitle = params.getString("course", "");
		int exampleId = params.getInt("exampleId", 0);
		if (exampleId > 0)
		{
			String sql = "select * from example where id=" + exampleId;
			Example example = (Example) MyC3P0Factory.get(sql, Example.class);
					
			model.put("exampleJ", new JSONObject(example));
		}

		String s = "select * from chapter where id=" + chapterId;
		Chapter chapter = (Chapter) MyC3P0Factory.get(s, Chapter.class);

		model.put("course", courseTitle);
		model.put("chapterJ", new JSONObject(chapter));

		return "/example_edit.html";
	}

}
