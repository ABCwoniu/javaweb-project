/**
 * 
 */
package example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import af.sql.AfSqlWhere;
import af.web.AfHttpReqParams;
import af.web.mvc.AfSimpleMVC;
import example.db.Course;
import example.db.Example;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月4日
 */
@WebServlet("/example_list.do")
public class ExampleListDo extends AfSimpleMVC
{

	@Override
	protected String execute(HttpServletRequest req, HttpServletResponse resp, HashMap<String, Object> model)
			throws Exception
	{
		AfHttpReqParams params = new AfHttpReqParams(req);
		int courseId = params.getInt("course", 0);
		int chapterId = params.getInt("chapter", 0);
		if (courseId <= 0 || chapterId <= 0)
			throw new Exception("必须传入id");
		AfSqlWhere where = null;
		if (true)
		{
			where = new AfSqlWhere().add2("id", courseId);
			Map course = execute("course", where).get(0);
			model.put("course", course);
		}
		if (true)
		{
			where = new AfSqlWhere().add2("id", chapterId);
			Map chapter = execute("chapter", where).get(0);
			model.put("chapter", chapter);
		}
		if (true)
		{
			where = new AfSqlWhere().add2("chapter", chapterId);
			List<Map> examples = execute("example", where);
			for (Map e : examples)
			{
				String contentHtml = text2Html((String) e.get("content"));
				e.put("contentHtml", contentHtml);

				String answerHtml = text2Html((String) e.get("answer"));
				e.put("answerHtml", answerHtml);
			}
			model.put("examples", examples);
		}

		return "/example_list.jhtml";
	}

	private List<Map> execute(String table, AfSqlWhere where) throws Exception
	{
		String sql = "select * from " + table + where;
		List<Map> list = MyC3P0Factory.executeQuery2Map(sql);
		return list;
	}

	private String text2Html(String text)
	{
		text = text.replace("\n", "<br/>").replace(" ", "&nbsp;").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		return text;
	}

}
