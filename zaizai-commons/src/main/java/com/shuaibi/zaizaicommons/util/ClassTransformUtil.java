package com.shuaibi.zaizaicommons.util;

import java.lang.reflect.*;
import java.util.*;

/**
 * 对象与对象之间的转换
 * 包括类与类的转换，List<类>与List<类>的转换，Map转换成类，  List<Map>转换成List<类>
 * @author dsq
 */
public class ClassTransformUtil
{
	/**
	 * List<Map> 转换成  List<类>
	 *
	 * @param targetClass 目标List<类>中的类型
	 * @param list        源数据List<Map>实例
	 * @return tarList
	 */
	@SuppressWarnings("unchecked")
	public static <T extends List<?>> T transListMapToListClass(Class<?> targetClass, List<Map<String, Object>> list)
	{
		List<Object> tarList = new ArrayList<Object>();

		if(list!=null && list.size()>0)
		{
			Map<String, Method> map = new HashMap<String, Method>(8);
			getSetMethods(map, targetClass);

			for(int i=0; i<list.size(); i++)
			{
				Map<String, ?> src = list.get(i);
				Object item = transMapToClassPrivate(map, src, targetClass);
				tarList.add(item);
			}
		}

		return (T) tarList;
	}

	/**
	 * Map 转换成  类
	 *
	 * @param targetClass 目标类型
	 * @param src src
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T transMapToClass(Class<?> targetClass, Map<String, ?> src)
	{
		Map<String, Method> map = new HashMap<String, Method>(8);
		getSetMethods(map, targetClass);

		return (T)transMapToClassPrivate(map, src, targetClass);
	}

	/**
	 * transMapToClassPrivate
	 * @param methods methods
	 *  @param src src
	 * @param newItemClazz newItemClazz
	 * @param  <T> <T>
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	private static <T> T transMapToClassPrivate(Map<String, Method> methods, Map<String, ?> src, Class<?> newItemClazz)
	{
		Object newItem = newInstance(newItemClazz);

		if(newItem==null)
		{
			return null;
		}

		Iterator<String> keys = methods.keySet().iterator();

		while(keys.hasNext())
		{
			String fName = keys.next();

			if( src.containsKey(fName) )
			{
				Object val = src.get(fName);
				Method method = methods.get(fName);

				try
				{
					invoke(newItem, method, val);
				}
				catch (IllegalArgumentException e)
				{}
			}
		}

		return (T)newItem;
	}


	/**
	 * 转换成 List<类>
	 *
	 * @param clazz 目标List<类>中的类型
	 * @param list  源数据List实例
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Collection<?>> T transFormList(Class<?> clazz, Collection<?> list)
	{
		Map<String, Method> methods = new HashMap<String, Method>(8);
		getSetMethods(methods, clazz);

		Collection<Object> newList = new ArrayList<Object>();

		for(Object obj : list)
		{
			Object newItem = transFromPrivate(methods, obj, clazz);

			if(newItem!=null)
			{
				newList.add(newItem);
			}
		}

		return (T)newList;
	}

	/**
	 * 单个对象转换
	 *
	 * @param clazz 目录类型
	 * @param src   源数据实例
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T transForm(Class<T> clazz, Object src)
	{
		Map<String, Method> methods = new HashMap<String, Method>(8);
		getSetMethods(methods, clazz);
		return (T)transFromPrivate(methods, src, clazz);
	}

	/**
	 * transFromPrivate
	 * @param methods methods
	 * @param src src
	 * @param  newItemClazz newItemClazz
	 * @param <T> <T>
	 * @return <T>
	 */
	@SuppressWarnings("unchecked")
	private static <T> T transFromPrivate(Map<String, Method> methods, Object src, Class<?> newItemClazz)
	{
		Object newItem = newInstance(newItemClazz);

		if(newItem==null)
		{
			return null;
		}

		Iterator<String> keys = methods.keySet().iterator();

		while(keys.hasNext())
		{
			String fName = keys.next();
			Field field = getField(src.getClass(), fName);

			if(field!=null)
			{
				Method method = methods.get(fName);

				try
				{
					invoke(newItem, method, field.get(src));
				}
				catch (IllegalArgumentException e)
				{}
				catch (IllegalAccessException e)
				{}
			}
		}

		return (T)newItem;
	}


	/**
	 * 单个对象属性拷贝
	 *
	 * @param target         目标实例
	 * @param src            源数据实例
	 * @param noCopyProperty 不拷贝的属性
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T copy(Object target, Object src, String... noCopyProperty)
	{
		List<Field> targetFields = new ArrayList<Field>();
		getFields(targetFields, target.getClass());

		if(noCopyProperty!=null)
		{
			for(int i=0; i<noCopyProperty.length; i++)
			{
				String property = noCopyProperty[i];

				for( Field tarField : targetFields )
				{
					if(tarField.getName().equals(property))
					{
						targetFields.remove(tarField);
						break;
					}
				}
			}
		}

		for( Field tarField : targetFields )
		{
			Field srcField = null;
			Class<?> tarClazz = tarField.getType();

			try
			{
				srcField = getField(src.getClass(), tarField.getName());

				if(srcField!=null)
				{
					Class<?> srcClazz = srcField.getType();
					Object srcFieldVal = srcField.get(src);

					if(tarClazz.isAssignableFrom(List.class) && srcClazz.isAssignableFrom(List.class))
					{
						Type fc = tarField.getGenericType();

						if(fc==null)
						{
							continue;
						}

						if(fc instanceof ParameterizedType)
						{
							ParameterizedType pt = (ParameterizedType)fc;
							Class<?> genericClazz = (Class<?>)pt.getActualTypeArguments()[0];

							List<?> list = transFormList(genericClazz, (List<?>)srcFieldVal);
							tarField.set(target, list);
						}
					}
					else if(tarClazz.isAssignableFrom(Map.class) || srcClazz.isAssignableFrom(Map.class))
					{
						continue;
					}
					else
					{
						tarField.set(target, srcFieldVal);
					}
				}
			}
			catch (SecurityException e)
			{}
			catch (IllegalAccessException e)
			{}
			catch (IllegalArgumentException e)
			{
				try
				{
					Object item = transForm(tarField.getType(), srcField.get(src));
					tarField.set(target, item);
				}
				catch (IllegalArgumentException e1)
				{}
				catch (IllegalAccessException e1)
				{}
			}
		}

		return (T)target;
	}


	/**
	 * 获取类所有字段(包括父类)
	 *
	 * @param list list
	 * @param clazz clasz
	 */
	private static void getFields(List<Field> list, Class<?> clazz)
	{
		Field[] fields = clazz.getDeclaredFields();

		for( Field f : fields )
		{
			f.setAccessible(true);
			list.add(f);
		}

		Class<?> superclass = clazz.getSuperclass();

		if( superclass!=null )
		{
			getFields(list, superclass);
		}
	}

	/**
	 * 根据类字段获取相应的Set方法
	 *
	 * @param map map
	 * @param clazz clazz
	 */
	private static void getSetMethods(Map<String, Method> map, Class<?> clazz)
	{
		Field[] fields = clazz.getDeclaredFields();

		for( Field f : fields )
		{
			String mName = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);

			try
			{
				Method method = clazz.getMethod(mName, f.getType());
				map.put(f.getName(), method);
			}
			catch (SecurityException e)
			{}
			catch (NoSuchMethodException e)
			{}
		}

		Class<?> superclass = clazz.getSuperclass();

		if( superclass!=null )
		{
			getSetMethods(map, superclass);
		}
	}

	/**
	 * 根据字段名，获取Field
	 *
	 * @param clazz clazz
	 * @param fieldName fieldName
	 * @return Field
	 */
	private static Field getField(Class<?> clazz, String fieldName)
	{
		Field[] field = clazz.getDeclaredFields();

		for( Field f : field )
		{
			if( f.getName().equals(fieldName) )
			{
				f.setAccessible(true);
				return f;
			}
		}

		Class<?> superclass = clazz.getSuperclass();

		if( superclass!=null )
		{
			return getField(superclass, fieldName);
		}

		return null;
	}

	/**
	 * 调用方法
	 *
	 * @param obj obj
	 * @param method method
	 * @param args args
	 */
	private static void invoke(Object obj, Method method, Object...args)
	{
		try
		{
			method.invoke(obj, args);
		}
		catch (IllegalArgumentException e)
		{}
		catch (IllegalAccessException e)
		{}
		catch (InvocationTargetException e)
		{}
	}

	/**
	 * 根据Class创建一个新实例
	 *
	 * @param clazz clazz
	 * @return T
	 */
	private static <T> T newInstance(Class<T> clazz)
	{
		try
		{
			return clazz.newInstance();
		}
		catch (InstantiationException e1)
		{
			return null;
		}
		catch (IllegalAccessException e1)
		{
			return null;
		}
	}
}