package util;

import java.sql.*;

public class DbDao {
	private Connection conn;
	private String driver;
	private String url;
	private String username;
	private String pass;
	public DbDao() {}
	
	public DbDao(String driver, String url, String username, String pass) {
		super();
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.pass = pass;
	}

	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	//获取数据库连接
	public Connection getConnection() throws Exception {
		if(conn==null) {
			Class.forName(this.driver);
			conn=DriverManager.getConnection(url,username,this.pass);
		}
		return conn;
	}
	//插入数据
	public boolean insert(String sql,Object...args) throws Exception {
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			pstmt.setObject(i+1, args[i]);
		}
		if(pstmt.executeUpdate()!=1) {
			return false;
		}
		return true;
	}
	//查询数据
	public ResultSet query(String sql,Object...args) throws Exception {
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			pstmt.setObject(i+1, args[i]);
		}
		return pstmt.executeQuery();
	}
	//查询数据
	public ResultSet query(String sql) throws Exception {
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		return pstmt.executeQuery();
	}
	//修改数据
	public void modify(String sql,Object...args) throws Exception {
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			pstmt.setObject(i+1, args[i]);
		}
		pstmt.executeUpdate();
		pstmt.close();
	}
	//关闭连接
	public void closeConn() throws SQLException {
		if(conn!=null&&!conn.isClosed()) {
			conn.close();
		}
	}
	
}
