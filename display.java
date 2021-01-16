package quan_ly_GV;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;

public class display {
//	client_tcp client_tcp = new client_tcp();
	private JFrame frame;
	private JTable table_1;
	Socket sk = new Socket("127.0.0.1", 1234);
	String[] column;
	String[][] data;
	String type = "";
	int dem_deader = 0;
	int dem_row_table = 0;
	private JPanel panel_truyvannangcao;
	private JTextArea textArea;
	private JComboBox comboBox_tsql;
	private JButton bt_Tsql;
	private JComboBox comboBox;
	private JButton bt_viewtable;
	private JButton bt_them;
	private JButton bt_update;
	private JButton bt_delete;
	private JButton bt_save;
	private JScrollPane js;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					display window = new display();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws InterruptedException
	 */
	public display() throws UnknownHostException, IOException, InterruptedException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws InterruptedException
	 */
	private void initialize() throws UnknownHostException, IOException, InterruptedException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 700);
		JLabel label_quanlyGV = new JLabel("QUẢN LÝ GIẢNG VIÊN");
		label_quanlyGV.setBackground(Color.DARK_GRAY);
		label_quanlyGV.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_quanlyGV.setForeground(Color.RED);
		label_quanlyGV.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 255)));
		JPanel pannel_table = new JPanel();
		pannel_table.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE));
		
		panel_truyvannangcao = new JPanel();
		panel_truyvannangcao.setBackground(Color.LIGHT_GRAY);
		panel_truyvannangcao.setForeground(Color.BLACK);
		panel_truyvannangcao.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Truy v\u1EA5n n\u00E2ng cao", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(label_quanlyGV, GroupLayout.DEFAULT_SIZE, 1184, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1164, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(pannel_table, GroupLayout.DEFAULT_SIZE, 1164, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_truyvannangcao, GroupLayout.DEFAULT_SIZE, 1164, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(label_quanlyGV, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pannel_table, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_truyvannangcao, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setForeground(Color.BLACK);
		textArea.setText("SELECT HOTEN, LUONG FROM GIAOVIEN WHERE PHAI = N'Nữ'");
		comboBox_tsql = new JComboBox();
		comboBox_tsql.setModel(new DefaultComboBoxModel(
				new String[] { "1-Cho biết họ tên và mức lương của các GV Nữ/Nam",
						"2-Cho biết họ tên của các giáo viên và lương của họ sau khi tăng 10%"
						,"3-Với mỗi giáo viên, hãy cho biết thông tin của bộ môn mà họ đang làm việc", 
						"4-Cho biết tên những GV khoa Công nghệ thông tin",
						"5-Cho biết thông tin của bộ môn cùng thông tin giảng viên làm trưởng bộ môn đó",
						"6-Cho biết tên đề tài và giáo viên chủ nhiệm đề tài đó",
						"7-Với mỗi khoa cho biết thông tin trưởng khoa",
						"8-Cho biết họ tên và mức lương của các giáo viên",
						"9-Tìm những giao viên không tham gia bất kì đề tài nào",
						"10-Cho biết tên khoa có đông giáo viên nhất "}));
		ArrayList<String>T_sql = new ArrayList<String>();
		T_sql.add("SELECT HOTEN, LUONG FROM GIAOVIEN WHERE PHAI = N'Nữ'");
		T_sql.add("SELECT HOTEN, LUONG*110/100 AS LUONGTANG FROM GIAOVIEN");
		T_sql.add("SELECT GV.*, BM.* FROM GIAOVIEN GV, BOMON BM WHERE GV.MABM = BM. MABM");
		T_sql.add("SELECT HOTEN AS HOTEN_GV_KHOA_CNTT FROM GIAOVIEN GV, KHOA K WHERE GV.MAGV = K.TRUONGKHOA AND K.TENKHOA = N'Công nghệ thông tin'");
		T_sql.add("SELECT BM.*, GV.* FROM GIAOVIEN GV, BOMON BM WHERE BM.MABM = GV.MABM AND GV.MAGV = BM.TRUONGBM");
		T_sql.add("SELECT DISTINCT  DT.TENDT, GV .HOTEN FROM GIAOVIEN GV, DETAI DT WHERE GV.MAGV = DT.GVCNDT");
		T_sql.add("SELECT GV.* FROM GIAOVIEN GV, KHOA K WHERE GV.MAGV = K.TRUONGKHOA");
		T_sql.add("SELECT HOTEN,LUONG FROM GIAOVIEN");
		T_sql.add("SELECT GV.* FROM GIAOVIEN GV WHERE NOT EXISTS( SELECT * FROM THAMGIADT TGDT WHERE TGDT.MAGV = GV.MAGV)");
		T_sql.add("SELECT k.TENKHOA  FROM KHOA k , BOMON bm WHERE k.MAKHOA = bm.MAKHOA AND bm.MABM = (SELECT MABM FROM GIAOVIEN  GROUP BY MABM  HAVING COUNT(*) >= ALL(SELECT COUNT(*)  FROM GIAOVIEN gv GROUP BY gv.MABM))");
		comboBox_tsql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// lua chon t sql
				String[] tmp = comboBox_tsql.getSelectedItem().toString().trim().split("-");
				textArea.setText(T_sql.get(Integer.parseInt(tmp[0])-1));
				
			}
		});
		DefaultTableModel defaultTableModel = new DefaultTableModel(data, column);
		bt_Tsql = new JButton("Chạy Truy Vân");
		bt_Tsql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// chạy T sql
				bt_them.setVisible(false);
				bt_update.setVisible(false);
				bt_delete.setVisible(false);
				bt_save.setVisible(false);
				String sql ="";
				 sql+= textArea.getText();
				System.out.println(sql);
				if(sql == null || sql == "") {
					JOptionPane.showMessageDialog(frame, "Câu lệnh truy vấn rỗng !");
				}else {
					try {
						quan_ly_GV.client_tcp.view_table("TSQL###" + sql, sk);
						String tmp = quan_ly_GV.client_tcp.res_view_table(sk);
						if(tmp == "faill") {
							JOptionPane.showMessageDialog(frame, "Query Completed with errors");
						}else {
							String tmp_data[] = tmp.split("&");
							String tmp_column = tmp_data[0];
							String tmp_da = tmp_data[1];
							String[] ok_da = tmp_da.split("#");
							column = tmp_column.split("//");
							defaultTableModel.setColumnIdentifiers(column);
							dem_deader = column.length;
							dem_row_table = ok_da.length;
							defaultTableModel.setRowCount(0);
							for (int i = 0; i < ok_da.length; i++) {
								String[] res = ok_da[i].split("//");
								defaultTableModel.addRow(res);
							}
							defaultTableModel.fireTableDataChanged();
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		bt_Tsql.setForeground(Color.BLUE);
		bt_Tsql.setFont(new Font("Tahoma", Font.BOLD, 12));
		bt_Tsql.setBackground(Color.ORANGE);
		
		JLabel lblNewLabel = new JLabel("Viết Câu lệnh Truy Vấn : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		JLabel lblNewLabel_1 = new JLabel("");
		
		JLabel lblNewLabel_2 = new JLabel("Các mẫu câu T-sql Hay dùng");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GroupLayout gl_panel_truyvannangcao = new GroupLayout(panel_truyvannangcao);
		gl_panel_truyvannangcao.setHorizontalGroup(
			gl_panel_truyvannangcao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_truyvannangcao.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_truyvannangcao.createParallelGroup(Alignment.LEADING)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 659, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_truyvannangcao.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(bt_Tsql, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
						.addComponent(comboBox_tsql, 0, 467, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2))
					.addContainerGap())
		);
		gl_panel_truyvannangcao.setVerticalGroup(
			gl_panel_truyvannangcao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_truyvannangcao.createSequentialGroup()
					.addGap(4)
					.addGroup(gl_panel_truyvannangcao.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_truyvannangcao.createParallelGroup(Alignment.LEADING)
						.addComponent(textArea, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
						.addGroup(gl_panel_truyvannangcao.createSequentialGroup()
							.addComponent(comboBox_tsql, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
							.addComponent(bt_Tsql)))
					.addContainerGap())
		);
		panel_truyvannangcao.setLayout(gl_panel_truyvannangcao);
	
		table_1 = new JTable(defaultTableModel);
		table_1.setBorder(new LineBorder(Color.BLACK));
		table_1.setRowHeight(30);
		// không cho sửa
		table_1.setEnabled(false);
		js = new JScrollPane(table_1);
		js.setVisible(true);

		GroupLayout gl_pannel_table = new GroupLayout(pannel_table);
		gl_pannel_table.setHorizontalGroup(gl_pannel_table.createParallelGroup(Alignment.LEADING).addComponent(js));
		gl_pannel_table.setVerticalGroup(gl_pannel_table.createParallelGroup(Alignment.LEADING).addComponent(js));
		pannel_table.setLayout(gl_pannel_table);
		comboBox = new JComboBox();
		bt_them = new JButton("ADD");
		bt_delete = new JButton("DELETE");
		bt_save = new JButton("THỰC HIỆN TRUY VẤN");
		bt_update = new JButton("UPDATE");
		bt_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// bat su kien update
				bt_them.setVisible(false);
				bt_update.setVisible(false);
				bt_delete.setVisible(false);
				bt_save.setVisible(true);
				type = "update";
				defaultTableModel.addColumn("UPDATE");
				JOptionPane.showMessageDialog(frame, "Gõ X vào Row Mà Bạn muốn cập nhật!");
				table_1.setEnabled(true);

			}
		});

		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "BOMON", "CHUDE", "CONGVIEC", "DETAI", "GIAOVIEN", "KHOA", "NGUOITHAN", "THAMGIADT" }));
		comboBox.setSelectedIndex(0);
		bt_viewtable = new JButton("View Table");
		bt_viewtable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// bắt sự kiện cho xem bảng
				bt_them.setVisible(true);
				bt_update.setVisible(true);
				bt_delete.setVisible(true);
				bt_save.setVisible(false);
				try {
					String getcomboBox = comboBox.getSelectedItem().toString().trim();
					System.out.println(getcomboBox);
					quan_ly_GV.client_tcp.view_table("view###" + getcomboBox, sk);
					String tmp = quan_ly_GV.client_tcp.res_view_table(sk);
					String tmp_data[] = tmp.split("&");
					String tmp_column = tmp_data[0];
					String tmp_da = tmp_data[1];
					String[] ok_da = tmp_da.split("#");
					column = tmp_column.split("//");
					defaultTableModel.setColumnIdentifiers(column);
					dem_deader = column.length;
					dem_row_table = ok_da.length;
					defaultTableModel.setRowCount(0);
					for (int i = 0; i < ok_da.length; i++) {
						String[] res = ok_da[i].split("//");
						defaultTableModel.addRow(res);
					}
					defaultTableModel.fireTableDataChanged();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bt_viewtable.setFont(new Font("Tahoma", Font.BOLD, 11));

		bt_them.setVisible(true);
		bt_update.setVisible(true);
		bt_delete.setVisible(true);
		bt_save.setVisible(false);
		bt_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// bat su kien them du lieu
				bt_them.setVisible(false);
				bt_update.setVisible(false);
				bt_delete.setVisible(false);
				bt_save.setVisible(true);
				type = "add";

				String[] add_data = new String[dem_deader];
				defaultTableModel.addRow(add_data);
				table_1.setEnabled(true);
				dem_row_table++;
			}
		});
		bt_them.setForeground(Color.MAGENTA);
		bt_them.setFont(new Font("Tahoma", Font.BOLD, 11));

		bt_update.setForeground(Color.BLUE);
		bt_update.setFont(new Font("Tahoma", Font.BOLD, 11));

		bt_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// thuc hien delete;
				bt_them.setVisible(false);
				bt_update.setVisible(false);
				bt_delete.setVisible(false);
				bt_save.setVisible(true);
				type = "delete";

				defaultTableModel.addColumn("DELETE");
				JOptionPane.showMessageDialog(frame, "Gõ X vào Row Mà Bạn muốn xóa!");
				table_1.setEnabled(true);
			}
		});
		bt_delete.setFont(new Font("Tahoma", Font.BOLD, 11));
		bt_delete.setForeground(Color.RED);

		bt_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// bat su kien cho bt run (save)
				bt_them.setVisible(true);
				bt_update.setVisible(true);
				bt_delete.setVisible(true);
				bt_save.setVisible(false);

				if (type.equals("add")) {
					type = "";
					defaultTableModel.fireTableDataChanged();
					boolean check = true;
					table_1.setEnabled(false);
					String s = "";
					for (int i = 0; i < dem_deader; i++) {
						String s1 = (String) table_1.getValueAt(dem_row_table - 1, i);
						if (i + 1 == dem_deader) {
							s += s1;
						} else {
							s += s1 + "<>";
						}
						if (s1 == null) {
							JOptionPane.showMessageDialog(frame, "Bạn Phải nhập đủ thông tin !");
							check = false;
							break;
						}
					}
					if (check) {
						try {
							String getcomboBox = comboBox.getSelectedItem().toString().trim();
							quan_ly_GV.client_tcp.view_table("insert###" + getcomboBox + "###" + s, sk);
							defaultTableModel.fireTableDataChanged();
							String k = quan_ly_GV.client_tcp.res_view_table(sk);
							if (k.equals("faill")) {

								JOptionPane.showMessageDialog(frame, "Thêm dữ liệu thất bại !");
							} else {
								JOptionPane.showMessageDialog(frame, "Thêm dữ liệu thành công !");
							}

						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
				if (type.equals("delete")) {
					type = "";
					String da_chon = "";
					for (int i = 0; i < dem_row_table; i++) {
						String tmp = "";
						tmp = (String) table_1.getValueAt(i, dem_deader);
						if (tmp != null) {
							if (tmp.equals("x") || tmp.equals("X")) {
								if (i + 1 == dem_row_table) {
									da_chon += table_1.getValueAt(i, 0).toString();
								} else {
									da_chon += table_1.getValueAt(i, 0).toString() + "<>";
								}
							}
						}
					}
					if (da_chon != "") {
						try {
							String getcomboBox = comboBox.getSelectedItem().toString().trim();
							quan_ly_GV.client_tcp.view_table("delete###" + getcomboBox + "###" + da_chon, sk);
							defaultTableModel.fireTableDataChanged();
							String k1 = quan_ly_GV.client_tcp.res_view_table(sk);
							if (k1.equals("faill")) {
								bt_them.setVisible(false);
								bt_update.setVisible(false);
								bt_delete.setVisible(false);
								bt_save.setVisible(false);
								JOptionPane.showMessageDialog(frame, "Xóa dữ liệu thất bại !");
							} else {
								bt_them.setVisible(false);
								bt_update.setVisible(false);
								bt_delete.setVisible(false);
								bt_save.setVisible(false);
								JOptionPane.showMessageDialog(frame,
										"Xóa thành công ! \n Hãy ấn view table để Refresh Data!");

							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
				if (type.equals("update")) {
					System.out.println("update");
					type = "";
					for (int i = 0; i < dem_row_table; i++) {
						String tmp = "";
						String da_chon = "";
						tmp = (String) table_1.getValueAt(i, dem_deader);
						if (tmp != null) {
							if (tmp.equals("x") || tmp.equals("X")) {
								da_chon += table_1.getValueAt(i, 0).toString();
								String s = "";
								for (int j = 0; j < dem_deader; j++) {
									String s1 = (String) table_1.getValueAt(i , j);
									if (j + 1 == dem_deader) {
										s += s1;
									} else {
										s += s1 + "<>";
									}
								}

								try {
									String getcomboBox = comboBox.getSelectedItem().toString().trim();
									quan_ly_GV.client_tcp.view_table("delete###" + getcomboBox + "###" + da_chon, sk);
									defaultTableModel.fireTableDataChanged();
									String k1 = quan_ly_GV.client_tcp.res_view_table(sk);
									if (k1.equals("faill")) {
										bt_them.setVisible(false);
										bt_update.setVisible(false);
										bt_delete.setVisible(false);
										bt_save.setVisible(false);
										JOptionPane.showMessageDialog(frame, "Query Completed with errors 1");
									} else {
										try {
											quan_ly_GV.client_tcp.view_table("insert###" + getcomboBox + "###" + s, sk);
											defaultTableModel.fireTableDataChanged();
											String k = quan_ly_GV.client_tcp.res_view_table(sk);
											if (k.equals("faill")) {
												JOptionPane.showMessageDialog(frame,
														"Update faill, Query Completed with errors !");
											} else {
												JOptionPane.showMessageDialog(frame, "Query for "+table_1.getValueAt(i, 0).toString()+" executed successfully !");
											}
										} catch (Exception e1) {
											e1.printStackTrace();
											JOptionPane.showMessageDialog(frame, "Update faill, " + e1.getMessage());

										}
									}
								} catch (Exception e1) {
									e1.printStackTrace();
									JOptionPane.showMessageDialog(frame, "Update faill, " + e1.getMessage());
								}
								bt_them.setVisible(false);
								bt_update.setVisible(false);
								bt_delete.setVisible(false);
								bt_save.setVisible(false);
								table_1.setEnabled(false);
								JOptionPane.showMessageDialog(frame, "Ấn view table để Refresh dữ liệu !");	
							}
						}
					}

				}
			}
		});
		bt_save.setForeground(Color.BLACK);
		bt_save.setFont(new Font("Tahoma", Font.BOLD, 12));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(bt_viewtable).addGap(38)
						.addComponent(bt_them).addGap(31).addComponent(bt_update).addGap(18).addComponent(bt_delete)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(bt_save)
						.addContainerGap(576, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(bt_viewtable).addComponent(bt_update).addComponent(bt_them)
								.addComponent(bt_delete).addComponent(bt_save))
						.addContainerGap()));
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}
}
