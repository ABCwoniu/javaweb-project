/**
 * 
 */
package example.admin;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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
public class ExampleQueryApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		// 权限检查
		new LoginCheck(httpReq).check();

		int id = jreq.getInt("id");

		String sql = "select * from example where chapter=" + id + " order by rank asc, id asc";
		List<Example> examples = MyC3P0Factory.executeQuery(sql, Example.class);

		JSONArray rows = new JSONArray(examples);
		int len = rows.length();
		for (int i = 0; i < len; i++)
		{
			JSONObject row = rows.getJSONObject(i);
			String contentHtml = text2Html(row.getString("content"));
			row.put("contentHtml", contentHtml);
		}
		return rows;
	}

	// 把纯文本转成HTML
	private String text2Html(String text)
	{
		// 换行 <br>
		// 空格 &nbsp;
		// 制表位 &nbsp;&nbsp;&nbsp;&nbsp;
		String strHtml = text.replace("\n", "<br>").replace(" ", "&nbsp;").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		return strHtml;
	}
}
