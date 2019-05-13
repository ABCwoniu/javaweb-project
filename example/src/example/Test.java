package example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import af.web.restful.AfRestfulApi;

/**
 * Servlet implementation class Test
 */
public class Test extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		int id = jreq.getInt("id");
		String name = jreq.getString("name");
		return null;
	}

}
