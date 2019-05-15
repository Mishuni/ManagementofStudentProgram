import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
	    //Ŭ �� �� �� ��
		MenuBar menubar; 
		JPanel panel_n,panel_s;
		JPanel[] card = new JPanel[4];
		JButton[] button = new JButton[4];
		JButton go,go2, del ;
		JTextField text, text2, text3;
		JTextArea area, area2, area3, area4;
		JScrollPane [] scroll = new JScrollPane[4];
		HashMap<String, StudentInfo> students 
		= new HashMap<String, StudentInfo>(); 
		
		CardLayout layout = new CardLayout();
		//insertâ�� �� panel
		SecondPanel panel = new SecondPanel();
		//updateâ�� �� panel2
		SecondPanel panel2 = new SecondPanel();
		ClikedActionListener a = new ClikedActionListener();
		
		
		MainFrame(){
			setTitle("Student System");
			setSize(500,500);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//�� �� �� ��
			menubar=new MenuBar();
			setJMenuBar(menubar);
			
			//�� �� ȭ
			panel_n = new JPanel();
			panel_s = new JPanel(layout);
			button[0]=new JButton("Search");
			button[1]=new JButton("Insert");
			button[2]=new JButton("Delete");
			button[3]=new JButton("Update");
			
			//��� panel
			setNorth();
			//�ϴ� panel
			setSouth();

			
			setVisible(true);
		}
		
		public void setNorth(){
			//��� panel�� ��ư�� �߰��ϰ�, frame�� �߰�
			for(int i=0 ; i<button.length; i++){
				panel_n.add(button[i]);
				button[i].addActionListener(a);
			}
			add(panel_n,BorderLayout.PAGE_START);
		}
	
		public void setSouth(){
			//�� �� ȭ
			go = new JButton("go");
			go2 = new JButton("go2");
			del = new JButton("del");
			text=new JTextField(20);
			text2=new JTextField(20);
			text3=new JTextField(20);
			
			area=new JTextArea(20,41);
			area2=new JTextArea(10,41);
			area3=new JTextArea(20,41);
			area4=new JTextArea(10,41);
			
			scroll[0]=new JScrollPane(area);
			scroll[1]=new JScrollPane(area2);
			scroll[2]=new JScrollPane(area3);
			scroll[3]=new JScrollPane(area4);
			
			for(int i=0; i<card.length;i++){
				//ī�� ���̾ƿ� 4�� ����
				card[i]= new JPanel();
				panel_s.add(card[i]);
			}
			// �� �� �� �� �� G U I �� ��
			search();
			insert();
			delete();
			update();
			
			add(panel_s,BorderLayout.CENTER);
		}
		
		public void search(){
			//event �߰�
			go.addActionListener(a);
			
			//ù��° ī�� ���̾ƿ�
			area.setEditable(false);
			card[0].add(text);
			card[0].add(go);
			card[0].add(scroll[0]);
			readFile(area);
			//ó������ file���� �о�;� students�� ���� ���� �� �ִ�.
			//�������ʹ� students�� ���� ������ ��´�.
			//��� file���� ���ε�� ������ ���� ������ ���ش�.

		}
		public void insert(){
			//event �߰�
			panel.save.addActionListener(a);
			panel.cancel.addActionListener(a);
			
			//�ι�° ī�� ���̾ƿ�
			area2.setEditable(false);
			card[1].add(panel);
			card[1].add(scroll[1]);
			reloadFile(area2);
		}
		
		public void delete(){
			//event �߰�
			del.addActionListener(a);
			
			//����° ī�� ���̾ƿ�
			area3.setEditable(false);
			card[2].add(text2);
			card[2].add(del);
			card[2].add(scroll[2]);
			reloadFile(area3);
		}
		
		public void update(){
			
			//��ư text �ٲٱ�
			panel2.save.setText("update");
			
			//event �߰�
			panel2.save.addActionListener(a);
			panel2.cancel.addActionListener(a);
			go2.addActionListener(a);
			
			//�׹�° ī�� ���̾ƿ�
			area4.setEditable(false);
			card[3].add(text3);
			card[3].add(go2);
			card[3].add(panel2);
			card[3].add(scroll[3]);
			reloadFile(area4);
		}
		
		
		class MenuBar extends JMenuBar{
			//frame����� menubar�� �����ϴ� Ŭ����
			JMenu menu_f, menu_e;
			JMenuItem save;
			JMenuItem [] item = new JMenuItem[4];
			
			MenuBar(){
				menu_f=new JMenu("File");
				menu_e=new JMenu("Edit");
				JMenuItem save = new JMenuItem("Save");
				item[0] = new JMenuItem("Search");
				item[1] = new JMenuItem("Insert");
				item[2]= new JMenuItem("Delete");
				item[3] = new JMenuItem("Update");
				
				//menu�� item �ְ�, item�� event ó�� �߰�
				menu_f.add(save);
				save.addActionListener(a);
				for(int i=0; i<item.length;i++){
					menu_e.add(item[i]);
					item[i].addActionListener(a);
				}
				add(menu_f);
				add(menu_e);
			}
		}
		
		class ClikedActionListener implements ActionListener{
			StudentInfo pre_info=null;
			public void actionPerformed(ActionEvent e) {
				
				Object o = e.getSource();
				
				if(o==button[0]|o==menubar.item[0]){
					// S E A R C H
					area.setText("");
					int i =0;
					reloadFile(area);
					layout.first(panel_s);
					text.setText("");
				}
				else if(o==button[1]|o==menubar.item[1]){
					// I N S E R T
					layout.first(panel_s);
					layout.next(panel_s);
					//�ι�° ī�� ���̾ƿ����� ����.
				}
				else if(o==button[2]|o==menubar.item[2]){
					// D E L E T E
					layout.last(panel_s);
					layout.previous(panel_s);
					//����° ī�� ���̾ƿ����� ����.
				}
				else if(o==button[3]|o==menubar.item[3]){
					// U P D A T E
					layout.last(panel_s);
					//������ ī�� ���̾ƿ����� ����.
				}
				
				
				else if(o==go){
					// �� �� �� �� �� ��
					int count=0;
					//3���� ��� �ش����� �ʴ� ���� ���� ����.
					area.setText("");
					
					String user_input=text.getText();
					ArrayList <StudentInfo> info = new ArrayList<StudentInfo>();
					Iterator it = students.keySet().iterator();
					
					while(it.hasNext()){
						info.add(students.get(it.next()));
					}
					for(int i=0; i<info.size();i++){
						StudentInfo s = info.get(i);
						if(s.name.equalsIgnoreCase(user_input)){
							//�Է��� ���� �̸��� ������ ���
							area.append(s.toString()+"\n");
						}
						else if(s.id.equalsIgnoreCase(user_input)){
							//�Է��� ���� id�� ������ ���
							area.append(s.toString()+"\n");
						}
						else if(s.department.equalsIgnoreCase(user_input)){
							//�Է��� ���� ������ ������ ���
							area.append(s.toString()+"\n");;
						}else{
							count++;
						}
					}
					if(count==info.size()){
						//�ƹ��͵� �������� ������ no result ���
						area.setText("No result");
					}
				}
				
				else if(o==panel.cancel){
					//insert���� cancel �� ���
					panel.t1.setText("");
					panel.t2.setText("");
					panel.t3.setText("");
					panel.t4.setText("");
					panel.R2.setSelected(true);
				}
				
				else if(o==panel.save){
					// �� �� �� �� ��
					try{
						String name=panel.t1.getText();
						int age=Integer.parseInt(panel.t2.getText());
						char gender=(panel.R2.isSelected())?'F':'M';
						String id=panel.t3.getText();
						String department = panel.t4.getText();
						
						StudentInfo s
						= new StudentInfo(name,gender,id,department,age);
						students.put(id, s);
						writeFile();
						//�߰��� ������ file��,HashMap�� ���ε�
						
						//�� �� Ʈ ĭ �� �� ȭ
						panel.t1.setText("");
						panel.t2.setText("");
						panel.t3.setText("");
						panel.t4.setText("");
						panel.R2.setSelected(true);
						
					}catch(Exception ex){
						System.out.println("Wrong Input");
						panel.t1.setText("");
						panel.t2.setText("");
						panel.t3.setText("");
						panel.t4.setText("");
						panel.R2.setSelected(true);
						//excepion�� �߻��ϴ� ��츦 ���Ѵ�.
					}
					
				}
				
				else if(o==del){
					//�� �� �� �� �� ��
					String user_input=text2.getText();
					students.remove(user_input);
					//user_input�� id�� ���� ������ �����Ѵ�
					writeFile();
					//�߰��� ������ file�� ���ε� �� area3 �ʱ�ȭ
					text2.setText("");
				}

				else if(o==go2){
					//�� �� �� �� �� �� �� ��
					String user_input=text3.getText();
					ArrayList <StudentInfo> info = new ArrayList<StudentInfo>();
					Iterator it = students.keySet().iterator();
					while(it.hasNext()){
						info.add(students.get(it.next()));
					}
					
					for(int i=0; i<info.size();i++){
						pre_info = info.get(i);
						if(pre_info.name.equalsIgnoreCase(user_input)|pre_info.id.equalsIgnoreCase(user_input)
								|pre_info.department.equalsIgnoreCase(user_input)){
							//���� �˻��� Ű����� ���� �̸��̳� id,Ȥ�� �μ��� ���� �л��� �ִٸ� ����
							
							area4.setText(pre_info.toString());
							panel2.t1.setText(pre_info.name);
							panel2.t2.setText(Integer.toString(pre_info.age));
							panel2.t3.setText(pre_info.id);
							panel2.t4.setText(pre_info.department);
							
							if(pre_info.gender=='F'){
								panel2.R2.setSelected(true);
							}else{
								panel2.R1.setSelected(true);
							}
							break;
						}

					}
				}
				else if(o==panel2.save){
					// �� �� �� �� �� Ʈ
					try{
						String name=panel2.t1.getText();
						int age=Integer.parseInt(panel2.t2.getText());
						char gender=(panel2.R2.isSelected())?'F':'M';
						String id=panel2.t3.getText();
						String department = panel2.t4.getText();
						
						StudentInfo s
						= new StudentInfo(name,gender,id,department,age);
						students.remove(pre_info.id);
						students.put(id, s);
						writeFile();
						//�߰��� ������ file�� ���ε�
						
						//�� �� Ʈ ĭ �� �� ȭ
						panel2.t1.setText("");
						panel2.t2.setText("");
						panel2.t3.setText("");
						panel2.t4.setText("");
						panel2.R2.setSelected(true);
						text3.setText("");
						
					}catch(Exception ex){
						System.out.println("Wrong Input");
						panel2.t1.setText("");
						panel2.t2.setText("");
						panel2.t3.setText("");
						panel2.t4.setText("");
						panel2.R2.setSelected(true);
						text3.setText("");
						//excepion�� �߻��ϴ� ��츦 ���Ѵ�.
					}
				}
				
				else if(o==panel2.cancel){
					//�� �� �� Ʈ �� ��
					area4.setText(pre_info.toString());
					panel2.t1.setText(pre_info.name);
					panel2.t2.setText(Integer.toString(pre_info.age));
					panel2.t3.setText(pre_info.id);
					panel2.t4.setText(pre_info.department);

					if(pre_info.gender=='F'){
						panel2.R2.setSelected(true);
					}else{
						panel2.R1.setSelected(true);
					}
				}
				
				else if(o==menubar.save){
					writeFile();
				}
				
			}
		}
		
		/* File I/0  �� �� �� */
		
		private void readFile(JTextArea ta){
			// ���Ͽ��� ���� HASHMAP�� �����ϰ�
			// �̸� AREA�� ���
			String fileName="StudentData.dat"; 
			try {
				ObjectInputStream input
				= new ObjectInputStream(new FileInputStream(fileName));
				
				try {
					students=(HashMap<String, StudentInfo>) input.readObject();
					Iterator<String> it = students.keySet().iterator();
					ta.setText("");
					while(it.hasNext()){
					ta.append(students.get(it.next()).toString()+"\n");
					}
					input.close();
				} 
				catch (ClassNotFoundException e) {
				e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
			
				e.printStackTrace();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}
		
		
		private void writeFile(){
			// file�� �ٽ� ��
			try {
				String fileName = "StudentData.dat"; 
				ObjectOutputStream outputStream = 
				new ObjectOutputStream(new FileOutputStream(fileName));
				outputStream.writeObject(students);
				outputStream.close();
				
				// �� �� �� �� �� �� �� �� �� �� ��
				reloadFile(area);
				reloadFile(area2);
				reloadFile(area3);
				reloadFile(area4);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private void reloadFile(JTextArea ta){
			//���Ͽ� ���� �� ������ text area�� ���ε�
			ta.setText("");
			Iterator<String> it = students.keySet().iterator();
			while(it.hasNext()){
			ta.append(students.get(it.next()).toString()+"\n");
			//text area�� �ٽ� �ʱ�ȭ�Ѵ�.
			}
		}
}



