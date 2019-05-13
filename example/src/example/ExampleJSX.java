/**
 * 
 */
package example;

import java.util.List;

import org.json.JSONArray;

import af.web.jsx.AfJsxCreator;
import example.db.Chapter;
import example.db.Course;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月4日
 */
public class ExampleJSX extends AfJsxCreator
{

	@Override
	public Object execute() throws Exception
	{
		String sql = "select * from course order by id asc";
		List<Course> courses = MyC3P0Factory.executeQuery(sql, Course.class);
		
		String s = "select * from chapter order by course asc, number asc";
		List<Chapter> chapters = MyC3P0Factory.executeQuery(s, Chapter.class);
		
		List[] arr = {courses, chapters};
		JSONArray data = new JSONArray(arr);
		
		return data;
	}

}
