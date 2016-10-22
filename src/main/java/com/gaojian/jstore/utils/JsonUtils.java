package com.gaojian.jstore.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.util.StringUtils;

/** 
 * 简单封装Jackson，实现JSON String<->Java Object的Mapper. 
 *  
 * 封装不同的输出风格, 使用不同的builder函数创建实例. 
 *  
 */
public class JsonUtils {

    private String dateFormat = "yyyy-MM-dd";

    /**
     * Create json utils Object
     * @return
     */
    public JsonUtils buildJsonMapper() {
        JsonUtils jsonUtils = new JsonUtils();
        jsonUtils.setDateFormat(dateFormat);
        return jsonUtils;
    }

    /**
     * JSON String<->Java Object的Mapper对象获取
     *
     * @return Object的Mapper对象
     */
    public ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Set time format
        mapper.setDateFormat(new SimpleDateFormat(dateFormat));
        return mapper;
    }

    /**
     * JAVA对象转变为JSON字符串
     *
     * @param object JAVA对象
     * @return 转变后的JSON字符串
     *（如果对象为Null, 返回"null".如果集合为空集合, 返回"[]".）
     */  
    public String toJson(Object object) {
        try {
        	ObjectMapper mapper = getObjectMapper();
            return mapper.writeValueAsString(object);  
        } catch (IOException e) {  
            throw NestedException.wrap(e);
        }  
    }

    /** 
     * 如果JSON字符串为Null或"null"字符串, 返回Null. 
     * 如果JSON字符串为"[]", 返回空集合. 
     *  
     * 如需读取集合如List/Map, 且不是List<String>这种简单类型时,先使用函數constructParametricType构造类型. 
     * @see #constructParametricType(Class, Class...) 
     */
    public <T> T fromJson(String jsonString, Class<T> clazz) {  
        if (StringUtils.isEmpty(jsonString)) {  
            return null;  
        }
        try {
            ObjectMapper mapper = getObjectMapper();
            return mapper.readValue(jsonString, clazz);  
        } catch (IOException e) {
            throw NestedException.wrap(e);  
        }
    }

    /** 
     * 如果JSON字符串为Null或"null"字符串, 返回Null. 
     * 如果JSON字符串为"[]", 返回空集合. 
     *  
     * 如需读取集合如List/Map, 且不是List<String>这种简单类型时,先使用函數constructParametricType构造类型. 
     * @see #constructParametricType(Class, Class...) 
     */  
    @SuppressWarnings("unchecked")
    public <T> T fromJson(String jsonString, JavaType javaType) {  
        if (StringUtils.isEmpty(jsonString)) {  
            return null;
        }  
        try {
            ObjectMapper mapper = getObjectMapper();
            return (T) mapper.readValue(jsonString, javaType);  
        } catch (IOException e) {  
            throw NestedException.wrap(e);  
        }  
    }

    /**
     * Change JSON string to object
     * @param jsonString
     * @param parametrized
     * @param parameterClasses
     * @return object
     */
    @SuppressWarnings("unchecked")
    public <T> T fromJson(String jsonString, Class<?> parametrized, Class<?>... parameterClasses) {  
        return (T) fromJson(jsonString, constructParametricType(parametrized, parameterClasses));  
    }

    /**
     * Change JSON string to list object
     * @param jsonString
     * @param parametrized
     * @param parameterClasses
     * @return list object
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> fromJsonToList(String jsonString, Class<T> classMeta){
        return (List<T>) fromJson(jsonString, constructParametricType(List.class, classMeta));
    }

    /**
     * Change JSON string to map object
     * @param jsonString
     * @param parametrized
     * @param parameterClasses
     * @return map object
     */
    @SuppressWarnings("unchecked")
    public <T, K> Map<T, K> fromJsonToMap(String jsonString, Class<T> classMeta1, Class<K> classMeta2){
        return (Map<T, K>) fromJson(jsonString, constructParametricType(Map.class, classMeta1, classMeta2));
    }

    /** 
     * 構造泛型的Type如List<MyBean>, 则调用constructParametricType(ArrayList.class,MyBean.class) 
     *             Map<String,MyBean>则调用(HashMap.class,String.class, MyBean.class) 
     */  
    public JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
        ObjectMapper mapper = getObjectMapper();
        return mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
    }  

    /**
     * get date format
     * @return dateFormat
     *      date format String
     */
	public String getDateFormat() {
		return dateFormat;
	}

	/**
	 * set data format
	 * @param dateFormat
     *      date format String
	 */
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

//    public static void main(String args[]) {
//    	JsonUtils jsonUtils= new JsonUtils();
//        // 测试1：对象 ⇔ JSON互相转化
//        User user = new User();
//        user.setUserName("userName");
//        user.setPassword("password");
//        user.setAge(new Date());
//        String jsonStr = jsonUtils.toJson(user);
//        System.out.println(jsonStr);
//        User userRst = jsonUtils.fromJson(jsonStr, User.class);
//
//        // 测试2：List对象 ⇔ JSON互相转化
//        User user1 = new User();
//        user1.setUserName("username1");
//        user1.setPassword("password1");
//        User user2  = new User();
//        user2.setUserName("username2");
//        user2.setPassword("password2");
//        List<User> usersList = new ArrayList<User>();
//        usersList.add(user1);
//        usersList.add(user2);
//        String jsonListStr = jsonUtils.toJson(usersList);
//        System.out.println(jsonListStr);
//        List<User> usersRstList = jsonUtils.fromJsonToList(jsonListStr, User.class);
//
//        // 测试2：List对象 ⇔ JSON互相转化
//        Map<String, User> usersMap = new HashMap<String, User>();
//        usersMap.put("user01", user1);
//        usersMap.put("user02", user2);
//        String jsonMapStr = jsonUtils.toJson(usersMap);
//        System.out.println(jsonMapStr);
//        Map<String, User> usersRstMap = jsonUtils.fromJsonToMap(jsonMapStr, String.class, User.class);
//    }
}
