/**
 * 
 */
package example.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import af.sql.AfSqlUpdate;
import af.sql.AfSqlWhere;
import af.web.restful.AfRestfulApi;
import example.admin.db.Example;
import example.admin.login.LoginCheck;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月3日
 */
public class ExampleSaveApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		// 权限检查
		new LoginCheck(httpReq).check();

		JSONObject chapter = jreq.getJSONObject("chapter");
		int rank = jreq.getInt("rank");
		String content = jreq.getString("content");
		String answer = jreq.getString("answer");
		if (jreq.has("id"))
		{
			int id = jreq.getInt("id");
			update(id, rank, content, answer);
		} else
		{
			Example example = new Example();
			example.course = chapter.getInt("course");
			example.chapter = chapter.getInt("id");
			example.rank = (byte) rank;
			example.content = content;
			example.answer = answer;
			example.status = (byte) 0;
			example.timeCreated = new Date();
			example.timeModified = new Date();
			MyC3P0Factory.insert(example);
		}
		return null;
	}

	private void update(int id, int rank, String content, String answer) throws Exception
	{
		AfSqlUpdate update = new AfSqlUpdate("example");
		update.add2("rank", rank);
		update.add("content", content);
		update.add("answer", answer);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		update.add("timeModified", sdf.format(new Date()));
		AfSqlWhere w = new AfSqlWhere().add2("id", id);

		String sql = update.toString() + w;
		MyC3P0Factory.execute(sql);

	}
}
