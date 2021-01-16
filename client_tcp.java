package quan_ly_GV;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class client_tcp {
	public static String res_view_table(Socket sk) throws IOException {
		// nhan ket qua tu server
		while(true) {
			DataInputStream inputStream = new DataInputStream(sk.getInputStream());
			String res =inputStream.readUTF();
			if (res!=null) {
				return res;
			}
		}
	}
	
	public static void view_table(String data, Socket sk) throws IOException {
		// gui du lieu di
		DataOutputStream outputStream = new DataOutputStream (sk.getOutputStream());
		outputStream.writeUTF(data);
	}
}
