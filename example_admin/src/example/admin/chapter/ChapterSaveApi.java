/**
 * 
 */
package example.admin.chapter;

import org.json.JSONArray;
import org.json.JSONObject;

import af.sql.AfSqlConnection;
import af.web.restful.AfRestfulApi;
import example.admin.MyC3P0Factory;
import example.admin.db.Chapter;
import example.admin.login.LoginCheck;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月3日
 */
public class ChapterSaveApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		//权限检查
		new LoginCheck(httpReq).check();
		
		int course = jreq.getInt("course");
		JSONArray chapters = jreq.getJSONArray("chapters");

		AfSqlConnection conn = MyC3P0Factory.getConnection();
		//删除原有章节
		String sql = "delete from chapter where course=" + course;
		conn.execute(sql);
		//重新添加章节
		save(course, chapters, conn);
		
		conn.close();
		return null;
	}

	private void save(int course, JSONArray chapters, AfSqlConnection conn) throws Exception
	{
		int len = chapters.length();
		for(int i = 0; i < len; i ++)
		{
			String title = chapters.getString(i);
			int number = i + 1;
			
			Chapter chapter = new Chapter();
			chapter.course = course;
			chapter.title = title;
			chapter.number = number;
			conn.insert(chapter);
		}
	}
}
