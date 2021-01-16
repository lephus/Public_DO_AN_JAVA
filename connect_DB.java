package quan_ly_GV;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class connect_DB {
	private  ArrayList<String> listHeader = new  ArrayList<String>();
	private  ArrayList<String> data = new  ArrayList<String>();
	
	// chức năng view table
	public  List<Object> view_table(String sql) {
		java.sql.Connection connection = null;
		List<Object> res = new ArrayList<Object>();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL = "jdbc:sqlserver://PHU\\SQLEXPRESS:1433;databaseName=DO_AN_JAVA;integratedSecurity=true;";
			connection = DriverManager.getConnection(connectionURL, "sa", "123");	
			System.out.println("Kết nối sql Thành Công :))");
			String docsql = "SELECT * FROM "+sql;
			PreparedStatement dsql =  connection.prepareStatement(docsql);
			ResultSet rs = dsql.executeQuery();
			listHeader = setup_header(sql);
			data.clear();
			Set<String> tmpheader;
			while(rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				for(int i=0; i<listHeader.size(); i++) {
					data.add(rs.getString(listHeader.get(i)));
				}
			}
			res.clear();
			res.add(listHeader);
			res.add(data);
			connection.close();
			return res;
		}catch (Exception e) { 
			System.err.println("Kết nối sql thất bại :((");
			e.printStackTrace();
			return null;
		}
	}
	// chuc nang T sql
	public  List<Object> Tsql_table(String sql) {
		java.sql.Connection connection = null;
		List<Object> res = new ArrayList<Object>();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL = "jdbc:sqlserver://PHU\\SQLEXPRESS:1433;databaseName=DO_AN_JAVA;integratedSecurity=true;";
			connection = DriverManager.getConnection(connectionURL, "sa", "123");	
			System.out.println("Kết nối sql Thành Công :))");
			String docsql =sql;
			PreparedStatement dsql =  connection.prepareStatement(docsql);
			ResultSet rs = dsql.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			listHeader.clear();
			data.clear();
			int columnCount = rsmd.getColumnCount(); 
			for(int i=1;i <= columnCount; i++) {
				listHeader.add(rsmd.getColumnName(i));
			}
			while(rs.next()) {
				for(int i=0; i<listHeader.size(); i++) {
					data.add(rs.getString(listHeader.get(i)));
				}
			}
			res.clear();
			res.add(listHeader);
			res.add(data);
			connection.close();
			return res;
		}catch (Exception e) { 
			System.err.println("Kết nối sql thất bại :((");
			e.printStackTrace();
			return null;
		}
	}
	
	// end T sql
	public ArrayList<String> setup_header(String name){
		ArrayList<String> arraylist = new ArrayList<>();
		if(name.equals("BOMON")) {
			arraylist.add("MABM");
			arraylist.add("TENBM");
			arraylist.add("PHONG");
			arraylist.add("DIENTHOAI");
			arraylist.add("TRUONGBM");
			arraylist.add("MAKHOA");
			arraylist.add("NGAYNHANCHUC");
			return arraylist;
		}
		if(name.equals("CHUDE")) {
			arraylist.add("MACD");
			arraylist.add("TENCD");
			return arraylist;
		}
		if(name.equals("CONGVIEC")) {
			arraylist.add("MADT");
			arraylist.add("SOTT");
			arraylist.add("TENCV");
			arraylist.add("NGAYBD");
			arraylist.add("NGAYKT");
			return arraylist;
		}
		if(name.equals("DETAI")) {
			arraylist.add("MADT");
			arraylist.add("TENDT");
			arraylist.add("CAPQL");
			arraylist.add("KINHPHI");
			arraylist.add("NGAYBD");
			arraylist.add("NGAYKT");
			arraylist.add("MACD");
			arraylist.add("GVCNDT");
			return arraylist;
		}
		if(name.equals("GIAOVIEN")) {
			arraylist.add("MAGV");
			arraylist.add("HOTEN");
			arraylist.add("LUONG");
			arraylist.add("PHAI");
			arraylist.add("NGSINH");
			arraylist.add("DIACHI");
			arraylist.add("GVQLCM");
			arraylist.add("MABM");
			return arraylist;
		}
		if(name.equals("KHOA")) {
			arraylist.add("MAKHOA");
			arraylist.add("TENKHOA");
			arraylist.add("NAMTL");
			arraylist.add("PHONG");
			arraylist.add("GV_DT");
			arraylist.add("TRUONGKHOA");
			arraylist.add("NGAYNHANCHUC");
			return arraylist;
		}
		if(name.equals("NGUOITHAN")) {
			arraylist.add("MAGV");
			arraylist.add("TEN");
			arraylist.add("NGSINH");
			arraylist.add("PHAI");
			return arraylist;
		}
		if(name.equals("THAMGIADT")) {
			arraylist.add("MAGV");
			arraylist.add("MADT");
			arraylist.add("STT");
			arraylist.add("PHUCAP");
			arraylist.add("KETQUA");
			return arraylist;
		}
		return null;
	}
	// het chưc nang view table
	// chưc nang add table
	public String add_table(String sql, String[] s) {
		java.sql.Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL = "jdbc:sqlserver://PHU\\SQLEXPRESS:1433;databaseName=DO_AN_JAVA;integratedSecurity=true;";
			connection = DriverManager.getConnection(connectionURL, "sa", "123");	
			System.out.println("Kết nối sql Thành Công :))");
			String giatri = "";
			for(int i=0; i<s.length; i++) {
				System.out.println(s[i]);
				if(i+1 == s.length) {
					giatri+="N'"+s[i]+"'";
				}else {
					giatri+="N'"+s[i]+"'"+", ";
				}
			}
			String insert_sql = "INSERT INTO DO_AN_JAVA.dbo."+sql+
					" VALUES ( "+giatri+" );";
			PreparedStatement dsql1 =  connection.prepareStatement(insert_sql);
			dsql1.execute();
			return "success";
		}catch (Exception e) { 
			System.err.println("Kết nối sql thất bại :((");
			e.printStackTrace();
		}
		return "faill";
	}
	// end chuc nang add table
	// chưc nang delete table
	public String delete_table(String sql, String[] s) {
		java.sql.Connection connection = null;
		ArrayList<String> oh_no = new ArrayList<String>();
		oh_no.addAll(setup_header(sql));
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL = "jdbc:sqlserver://PHU\\SQLEXPRESS:1433;databaseName=DO_AN_JAVA;integratedSecurity=true;";
			connection = DriverManager.getConnection(connectionURL, "sa", "123");	
			System.out.println("Kết nối sql Thành Công :))");
			for(int i=0; i<s.length; i++) {
				String insert_sql = "DELETE FROM DO_AN_JAVA.dbo."+sql+
						" WHERE "+oh_no.get(0)+" = " +"'" +s[i] +"'"+ ";";
				PreparedStatement dsql1 =  connection.prepareStatement(insert_sql);
				dsql1.execute();
			}
			return "success";
		}catch (Exception e) { 
			System.err.println("Kết nối sql thất bại :((");
			e.printStackTrace();
		}
		return "faill";
	}
	// end chuc nang delete
	
}

