package com.test.connectDb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;

public class TestConDataBase {
	
	
	public void getConn(){
		PropKit.use("a_little_config.txt");
		//数据库连接
		Connection connection = null;
		//预编译的Statement，使用预编译的Statement提高数据库性能
		PreparedStatement preparedStatement = null;
		//结果 集
		ResultSet resultSet = null;
		
		try {
			//加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			
			//通过驱动管理类获取数据库链接
			connection =  DriverManager.getConnection(PropKit.get("jdbcUrl2"), PropKit.get("user"), PropKit.get("password").trim());
			//定义sql语句 ?表示占位符
			String sql = "select * from zone_three";
			//获取预处理statement
			preparedStatement = connection.prepareStatement(sql);
			//设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
			//preparedStatement.setString(1, "王五");
			//向数据库发出sql执行查询，查询出结果集
			resultSet =  preparedStatement.executeQuery();
			//遍历查询结果集
			while(resultSet.next()){
				System.out.println(resultSet.getString("id")+"  "+resultSet.getString("zonename"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//释放资源
			if(resultSet!=null){
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(preparedStatement!=null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
	
	public static void initDataBase(){
		PropKit.use("a_little_config.txt");
		DruidPlugin dp = new DruidPlugin(PropKit.get("jdbcUrl2"), PropKit.get("user"), PropKit.get("password").trim());
		dp.start();
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		arp.setShowSql(true);
		arp.addMapping("zone_three", Zone.class);	// 映射
		arp.addMapping("syszone", SysZone.class);	// 映射
		arp.start();
	}
	
	public static void readTxt(String filePath){
		String encoding = "utf-8";
		File file = new File(filePath);
		if(file.isFile() && file.exists()){
			try {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file),encoding);
				BufferedReader bfr = new BufferedReader(read);
				String lineTxt = null;
				while((lineTxt = bfr.readLine())!=null){
					String[] arr = lineTxt.trim().split(",");
					System.out.println(arr[0]);
					System.out.println(arr[1]);
					System.out.println(arr[2]);
					int level = Integer.parseInt(arr[0].trim());
					String code = arr[1].trim();
					String zonename = arr[2].trim();
					String parent = "000000";
					String pre = code.substring(0, 2);
					if(level==3){
						if(pre.equals("11") ||pre.equals("12") ||pre.equals("31") ||pre.equals("50")){
							continue;
						}
						parent = pre+"0000";
					}else if(level==4){
						if(zonename.equals("市辖区")){
							continue;
						}
						if(pre.equals("11") ||pre.equals("12") ||pre.equals("31") ||pre.equals("50")){
							parent = pre+"0000";
							level=3;
						}else{
							parent = code.substring(0, 4)+"00";
						}
					}
					Zone zone = new Zone();
					zone.set("level", level).set("code", code).set("zonename", zonename).set("parent", parent);
					zone.save();
				}
				bfr.close();
				read.close();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		initDataBase();
//		long begin = System.currentTimeMillis();
//		readTxt("F:\\省市县.txt");
//		long end = System.currentTimeMillis();
//		System.out.println(end-begin);
//		System.out.println((end-begin)/1000/60);
		List<Zone> list = Zone.dao.find("select * from zone_three  order by id limit 10");
		System.out.println(list);
		
	}
	
	
	
}
