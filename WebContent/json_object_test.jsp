<%@ page import="java.util.*" %>
<%@ page import="org.json.*"%>
<%@ page import="com.google.gson.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!

class App {
	String name;
	int version;
	public App() {
		
	}
	public App(String name, int version) {
		this.name = name;
		this.version = version;
	}
	public String toString() {
		return "App:[name = " + name + ", version = " + version + "]";
	}
}

class User {
	String name;
	int age;
	List<App> appList;
	public User() {
		
	}
	public String toString() {
		return "User:[name = " + name + ", age = " + age + ", appList: " + appList + "]";
	}
}

%>
<br/>
<%

out.print("JsonToObject:<br/>");
String json = "{'name':'小明', 'age':'25', 'appList':[{'name':'QQ', 'version':'1'}, {'name':'WeChat', 'version':'2'}, {'name':'AliPay', 'version':'3'}]}";
JSONObject jsonUser = new JSONObject(json);
out.print(jsonUser.toString() + "<br/>");
User user = new User();
user.name = jsonUser.getString("name");
user.age = jsonUser.getInt("age");
List<App> appList = new ArrayList<>();
JSONArray jsonAppArray = jsonUser.getJSONArray("appList");
for (int i = 0; i < jsonAppArray.length(); ++i) {
	JSONObject jsonApp = jsonAppArray.getJSONObject(i);
	App app = new App();
	app.name = jsonApp.getString("name");
	app.version = jsonApp.getInt("version");
	appList.add(app);
}
user.appList = appList;
out.println(user.toString() + "<br/>");

out.println("ObjectToJson:<br/>");
User user1 = new User();
user1.name = "小刚";
user1.age = 26;
user1.appList = new ArrayList<>();
user1.appList.add(new App("饿了么", 5));
user1.appList.add(new App("口碑", 6));
JSONObject userJson = new JSONObject();
JSONArray appArrayJson = new JSONArray();
userJson.put("name", user1.name);
userJson.put("age", user1.age);
//JSONObject appJson1 = new JSONObject();
//appJson1.put("name", user1.appList.get(0).name).put("version", user1.appList.get(1).version);
for (int i = 0; i < user1.appList.size(); ++i) {
	App app = user1.appList.get(i);
	appArrayJson.put(i, new JSONObject().put("name", app.name).put("version", app.version));
}
userJson.put("applist", appArrayJson);
out.println(userJson.toString());

%>
<br/>
<%

out.print("GsonToObject:<br/>");
Gson gsonUser = new Gson();
User user2 = gsonUser.fromJson(json, User.class);
out.print(user2.toString() + "<br/>");

out.print("ObjectToGson:<br/>");
Gson userGson = new Gson();
out.print(userGson.toJson(user1) + "<br/>");

%>
</body>
</html>