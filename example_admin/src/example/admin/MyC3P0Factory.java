package example.admin;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import af.sql.AfSqlConnection;
import af.sql.c3p0.AfC3P0Pool;

/* 如果应用程序里只有一个数据源，可以仿照这个类，自己建一个C3P0Factory
 * 
 */

public class MyC3P0Factory
{
	private static AfC3P0Pool pool = null;
	
	/* 全局对象初始化 */
	static {
		pool = new AfC3P0Pool();
	}
	
	
	/* 获取连接 */
	public static AfSqlConnection getConnection() throws Exception
	{
		return pool.getConnection();
	}
		


	
	// 获取唯一一行记录
	public static Object get(String sql, Class clazz)throws Exception
	{
		AfSqlConnection connection = getConnection();
		try{
			List rows = executeQuery(sql, clazz);
			if(rows == null || rows.size()== 0)
			{
				return null;
			}
			else 
			{
				return rows.get(0);
			}
		}finally{
			connection.close();
		}	
	}
	// 取得一行，并转成JSON
	public static JSONObject getAsJSON(String sql)throws Exception
	{
		AfSqlConnection connection = getConnection();
		try{
			JSONArray rows = executeQuery2JSON(sql);
			if(rows == null || rows.length()== 0)
			{
				return null;
			}
			else 
			{
				return rows.getJSONObject(0);
			}
		}finally{
			connection.close();
		}	
	}
	// 取得一行，并转成 Map
	public static Map getAsMap(String sql)throws Exception
	{
		AfSqlConnection connection = getConnection();
		try{
			List rows = executeQuery2Map(sql);
			if(rows == null || rows.size()== 0)
			{
				return null;
			}
			else 
			{
				return (Map)rows.get(0);
			}
		}finally{
			connection.close();
		}	
	}
	
	
	// 插入 insert
	public static void insert(Object pojo)throws Exception
	{
		AfSqlConnection connection = getConnection();
		try{
			connection.insert(pojo);
		}finally{
			connection.close();
		}	
	}
	
	// 执行 insert update delete 等SQL
	public static void execute(String sql) throws Exception
	{
		AfSqlConnection connection = getConnection();
		try{
			connection.execute(sql);
		}finally{
			connection.close();
		}	
	}
	
	// 查询，并自动映射到pojo，返回pojo列表
	public static List executeQuery(String sql, Class clazz) throws Exception
	{
		AfSqlConnection connection = getConnection();
		try{
			return connection.executeQuery(sql, clazz);
		}finally{
			connection.close();
		}	
	}
	
	// 查询，并返回 JSONArray
	public static JSONArray executeQuery2JSON(String sql) throws Exception
	{
		JSONArray result = new JSONArray();
		
		AfSqlConnection connection = getConnection();
		try{
			ResultSet rs = connection.executeQuery(sql); 
			
			// 取得每一列的名称和类型
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount(); // 一共几列
			int[] columnTypes = new int[numColumns];  // 每列的类型
			String[] columnLabels = new String[numColumns]; // 每列的标题			
			for(int i=0; i<numColumns; i++) 
			{			
				int columnIndex = i + 1; // 列序号
				columnLabels[i] = rsmd.getColumnLabel(columnIndex); // 列标题	
				columnTypes[i] = rsmd.getColumnType(columnIndex); // 类型, 参考 java.sql.Types定义	
			}
					
			while(rs.next())
		    {		
				// 每一行转成一个JSONObject
				JSONObject jrow = new JSONObject();
				result.put(jrow);
				
		    	for(int i=0; i<numColumns; i++)
		    	{
		    		String columnValue = rs.getString( i + 1); // 每列的值
		    		if(columnValue == null) continue;
		    		
					int type = columnTypes[i];
					if(type == Types.TINYINT || type == Types.SMALLINT
						|| type == Types.INTEGER || type == Types.BIGINT)
					{
						jrow.put(columnLabels[i], Long.valueOf(columnValue));
					}
					else if(type == Types.DOUBLE || type == Types.FLOAT)
					{
						jrow.put(columnLabels[i], Double.valueOf(columnValue));
					}
					else
					{
						jrow.put(columnLabels[i], columnValue);
					}
		    	}	    
		    }
	    	
			return result;
		}finally{
			connection.close();
		}	
	}
	
	// 查询，并返回 List<Map<String,Object>>
	public static List executeQuery2Map(String sql) throws Exception
	{
		List result = new ArrayList();
		
		AfSqlConnection connection = getConnection();
		try{
			ResultSet rs = connection.executeQuery(sql); 
			
			// 取得每一列的名称和类型
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount(); // 一共几列
			int[] columnTypes = new int[numColumns];  // 每列的类型
			String[] columnLabels = new String[numColumns]; // 每列的标题			
			for(int i=0; i<numColumns; i++) 
			{			
				int columnIndex = i + 1; // 列序号
				columnLabels[i] = rsmd.getColumnLabel(columnIndex); // 列标题	
				columnTypes[i] = rsmd.getColumnType(columnIndex); // 类型, 参考 java.sql.Types定义	
			}
					
			while(rs.next())
		    {		
				// 每一行转成一个JSONObject
				HashMap<String,Object> row = new HashMap<String,Object>();
				result.add(row);
				
		    	for(int i=0; i<numColumns; i++)
		    	{
		    		String columnValue = rs.getString( i + 1); // 每列的值
		    		if(columnValue == null)
		    		{
		    			row.put(columnLabels[i] , null);
		    			continue;
		    		}
		    		
					int type = columnTypes[i];
					Object value = columnValue;
					if(type == Types.TINYINT )
						value = Byte.valueOf(columnValue);
					else if(type == Types.SMALLINT)
						value = Short.valueOf(columnValue);
					else if(type == Types.INTEGER)
						value = Integer.valueOf(columnValue);
					else if(type == Types.BIGINT)
						value = Long.valueOf(columnValue);	
					else if(type == Types.DOUBLE)
						value = Double.valueOf(columnValue);	
					else if(type == Types.FLOAT)
						value = Float.valueOf(columnValue);
					
					row.put(columnLabels[i] , value);					
		    	}	    
		    }
	    	
			return result;
		}finally{
			connection.close();
		}	
	}
}
