package CatCloud.Util;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class JsonTool {

	static public int FLAG_DEFAULT=1;
	
	

	
	/**
	 * 解析Bean到JsonObejct
	 * @param bean 需要解析的bean
	 * @param flags 一些特殊的标志位
	 * @param ignores 需要忽略的字段名
	 * @return 处理完的JsonObject
	 */
	public static JSONObject parseBeanToJsonObj(Object bean,int[] flags,String[] ignores) {
		
		
		//暂时还没想到什么标志位,这边先占着位置
		boolean defaultFlag=false;
		
		if(flags!=null)
		{
		for(int i=0;i<flags.length;i++)
		{
			if(flags[i]==FLAG_DEFAULT)
			{
				defaultFlag=true;
			}
		}
		}
		
		if(ignores==null)
		{
			ignores=new String[]{};
		}
		
		
		List<String> ignores2=Arrays.asList(ignores);
		JSONObject jsonObject=new JSONObject();
		
				Field[] fields=bean.getClass().getDeclaredFields();
				for(Field field:fields)
				{
					if(!ignores2.contains(field.getName()))
					{
					field.setAccessible(true);					
					try {
						//不过这里有一个很莫名其妙的bug
						//数据库转换到bean 时间格式如果是 2017-1-1 00:00:00可以转换成功
						//但是这样的格式从json转换到bean就不可以
						//所以这边统一下时间的格式 换成30 Dec 1899 09:47:00 GMT这种双方才都能识别
						if(field.get(bean)==null)
						{
							continue;
						}
						
						
						String type=field.getType().getName();
						if(type.equals("java.util.Date"))
						{
							jsonObject.put(field.getName(), ((Date)field.get(bean)).toGMTString());
							continue;
						}
						
						
						jsonObject.put(field.getName(), field.get(bean));
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					
					}
				}
				
		
	
		
		return jsonObject;
	}

	

	
	/**
	 * 解析json到bean
	 * @param <T>
	 * @param class1 bean的类
	 * @param flags 标志位
	 * @param object json对象
	 * @param ignores 需要忽略的字段
	 * @return 解析的bean 或者null
	 */
	public static <T> T parseJsonObjToBean(Class<T> class1,JSONObject object, int[] flags, String[] ignores) {
		
		
		
		//暂时还没想到什么标志位,这边先占着位置
		boolean defaultFlag=false;
		
		if(flags!=null)
		{
		for(int i=0;i<flags.length;i++)
		{
			if(flags[i]==FLAG_DEFAULT)
			{
				defaultFlag=true;
			}
		}
		}
		
		if(ignores==null)
		{
			ignores=new String[]{};
		}
		
		
		List<String> ignores2=Arrays.asList(ignores);
		T bean = null;
		try {
			bean = class1.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			Field[] fields=class1.getDeclaredFields();
				for(Field field:fields)
				{
					if(!ignores2.contains(field.getName()))
					{
					field.setAccessible(true);					
					try {
						//我靠这边居然能把Thu Mar 02 10:27:59 CST 2017这样的类型直接转换成date?
						//所以感觉数据库和json的set本身就做了很多工作,只要双方的数据类型一致都可以直接转换的
						//好像又不行了..
						String type=field.getType().getName();
						if(type.equals("java.util.Date"))
						{
							field.set(bean, new Date(object.getString(field.getName())));
							continue;
						}			
						
						field.set(bean,object.get(field.getName()) );
					} catch (IllegalArgumentException | IllegalAccessException | JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					
					}
				}
				
		
	
		
		return bean;
	
	}
	
	/**
	 * 转换listBean到jsonArray
	 * @param <T> bean的类型
	 * @param beans 需要转换的bean
	 * @param flags 标志位
	 * @param ignores 需要忽略的字段名,对所有bean适用
	 * @return json数组
	 */
	public static <T> JSONArray parseListBeanToJsonArray(List<T> beans, int[] flags,String[] ignores) {
		
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<beans.size();i++)
		{
			jsonArray.put(parseBeanToJsonObj(beans.get(i), flags, ignores));
		}
			
		return jsonArray;
	}




	/**
	 * 转换jsonArray到listBean
	 * @param jsonArray jsonArray
	 * @param class1 要转换的类
	 * @param flags 标志位
 	 * @param ignores 忽略的字段
	 * @return List
	 */
	public static <T> List<T> parseJsonArrayToListBean(JSONArray jsonArray, Class<T> class1,
			int[] flags, String[] ignores) {
		
		List<T> list=new ArrayList<>();
		for(int i=0;i<jsonArray.length();i++)
		{
			try {
				list.add(parseJsonObjToBean(class1, jsonArray.getJSONObject(i), flags, ignores));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return list;
	}
	

	
	
}
