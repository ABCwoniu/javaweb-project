package example.admin.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import example.admin.db.Admin;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter({ "*.sudo", "*.jhtml" })
public class LoginCheckFilter implements Filter
{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		Admin admin = (Admin) req.getSession().getAttribute("admin");
		if (admin == null)
			resp.sendRedirect(req.getContextPath() + "/login.html");

		chain.doFilter(request, response);
	}

	@Override
	public void destroy()
	{

	}

	@Override
	public void init(FilterConfig config) throws ServletException
	{
		ServletContext application = config.getServletContext();
		List<HttpSession> sessions = new ArrayList<HttpSession>();
		application.setAttribute("sessions", sessions);
		//启动定时清理线程
		ClearThread ct = new ClearThread(sessions);
		ct.start();
	}

	private class ClearThread extends Thread
	{
		private List<HttpSession> sessions;

		public ClearThread(List<HttpSession> sessions)
		{
			this.sessions = sessions;
		}

		@Override
		public void run()
		{
			while (true)
			{
				SingleLogin.login(null, sessions);
				try
				{
					Thread.sleep(1000 * 60);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}

	}

}
