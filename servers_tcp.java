package quan_ly_GV;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
public class servers_tcp {
	private static connect_DB connect_DB = new connect_DB();
		public static void run_server() throws IOException {
			ServerSocket ssk = new ServerSocket(1234);
			Socket sk = null;
			sk = ssk.accept();
			if (sk.isConnected()) {
				System.out.println("CLIENT CONNECTED");
			}
			while (true) {
				DataInputStream input = new DataInputStream(sk.getInputStream());
				String res = input.readUTF();
				System.out.println(res);
				String[] parts = res.split("###");
				// view
				if(parts[0].equals("view")) {
					List<Object> data_ta = (connect_DB.view_table(parts[1]));
					if(data_ta == null) {
						server_feedback(sk, "faill");
					}else {
						ArrayList<String> header =  new ArrayList<String>();
						header.clear();
						header.addAll((ArrayList<String>) data_ta.get(0));
						String  data_table = "";
						for(int i=0 ; i<header.size(); i++) {
							if(i+1==header.size()) {
								data_table+=header.get(i);
							}else {
								data_table+=header.get(i)+"//";
							}
						}
						data_table+="&";
						System.out.println("list :  "+(ArrayList<String>) data_ta.get(data_ta.size()-1));
						ArrayList<String> body =  new ArrayList<String>();
						body.clear();
						body = ((ArrayList<String>) data_ta.get(data_ta.size()-1));
						int dem =0;
						for(int i=0 ; i<body.size(); i++) {
							if(i+1==body.size()) {
								data_table+=body.get(i);
							}else {
								data_table+=body.get(i)+"//";
							}
							dem++;
							if(dem==header.size()) {
								dem=0;
								data_table+="#";
							}
						}
						System.out.println("data_table>>>>  "+data_table);
						server_feedback(sk, data_table);
					}
				}
				// end view
				// insert
				if(parts[0].equals("insert")) {
					String[] phu = parts[2].split("<>");
					String res_insert = connect_DB.add_table(parts[1], phu);
					System.out.println("insert >> "+res_insert);
					server_feedback(sk, res_insert);
				}
				// end insert
				// delete
				if(parts[0].equals("delete")) {
					String[] phu = parts[2].split("<>");
					String res_insert = connect_DB.delete_table(parts[1], phu);
					System.out.println("delete >> "+res_insert);
					server_feedback(sk, res_insert);
				}
				// end delete
				// T sql
				if(parts[0].equals("TSQL") && parts.length == 2 ) {
					List<Object> data_ta = (connect_DB.Tsql_table(parts[1]));
					if(data_ta == null) {
						server_feedback(sk, "faill");
					}else {
						ArrayList<String> header =  new ArrayList<String>();
						header.clear();
						header.addAll((ArrayList<String>) data_ta.get(0));
						String  data_table = "";
						for(int i=0 ; i<header.size(); i++) {
							if(i+1==header.size()) {
								data_table+=header.get(i);
							}else {
								data_table+=header.get(i)+"//";
							}
						}
						data_table+="&";
						System.out.println("list :  "+(ArrayList<String>) data_ta.get(data_ta.size()-1));
						ArrayList<String> body =  new ArrayList<String>();
						body.clear();
						body = ((ArrayList<String>) data_ta.get(data_ta.size()-1));
						int dem =0;
						for(int i=0 ; i<body.size(); i++) {
							if(i+1==body.size()) {
								data_table+=body.get(i);
							}else {
								data_table+=body.get(i)+"//";
							}
							dem++;
							if(dem==header.size()) {
								dem=0;
								data_table+="#";
							}
						}
						System.out.println("data_table>>>>  "+data_table);
						server_feedback(sk, data_table);
					}
				}
				
				// end T sql
				if (res.equals("exit")) {
					System.out.println("STOP SERVER");
					sk.close();
					ssk.close();
					break;
				}
			}
		}
		static void server_feedback(Socket sk, String t) throws IOException {
			DataOutputStream outputStream = new DataOutputStream (sk.getOutputStream());
			outputStream.writeUTF(t);
		}
		public static void main(String[] args) throws IOException {
			run_server();
		}
}
