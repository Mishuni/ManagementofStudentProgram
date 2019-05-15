import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
	    //클 래 수 변 수
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
		//insert창에 들어갈 panel
		SecondPanel panel = new SecondPanel();
		//update창에 들어갈 panel2
		SecondPanel panel2 = new SecondPanel();
		ClikedActionListener a = new ClikedActionListener();
		
		
		MainFrame(){
			setTitle("Student System");
			setSize(500,500);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//메 뉴 생 성
			menubar=new MenuBar();
			setJMenuBar(menubar);
			
			//초 기 화
			panel_n = new JPanel();
			panel_s = new JPanel(layout);
			button[0]=new JButton("Search");
			button[1]=new JButton("Insert");
			button[2]=new JButton("Delete");
			button[3]=new JButton("Update");
			
			//상단 panel
			setNorth();
			//하단 panel
			setSouth();

			
			setVisible(true);
		}
		
		public void setNorth(){
			//상단 panel에 버튼을 추가하고, frame에 추가
			for(int i=0 ; i<button.length; i++){
				panel_n.add(button[i]);
				button[i].addActionListener(a);
			}
			add(panel_n,BorderLayout.PAGE_START);
		}
	
		public void setSouth(){
			//초 기 화
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
				//카드 레이아웃 4개 생성
				card[i]= new JPanel();
				panel_s.add(card[i]);
			}
			// 각 레 이 아 웃 G U I 설 정
			search();
			insert();
			delete();
			update();
			
			add(panel_s,BorderLayout.CENTER);
		}
		
		public void search(){
			//event 추가
			go.addActionListener(a);
			
			//첫번째 카드 레이아웃
			area.setEditable(false);
			card[0].add(text);
			card[0].add(go);
			card[0].add(scroll[0]);
			readFile(area);
			//처음에는 file에서 읽어와야 students가 값을 받을 수 있다.
			//다음부터는 students로 부터 정보를 얻는다.
			//대신 file로의 업로드는 수정이 있을 때마다 해준다.

		}
		public void insert(){
			//event 추가
			panel.save.addActionListener(a);
			panel.cancel.addActionListener(a);
			
			//두번째 카드 레이아웃
			area2.setEditable(false);
			card[1].add(panel);
			card[1].add(scroll[1]);
			reloadFile(area2);
		}
		
		public void delete(){
			//event 추가
			del.addActionListener(a);
			
			//세번째 카드 레이아웃
			area3.setEditable(false);
			card[2].add(text2);
			card[2].add(del);
			card[2].add(scroll[2]);
			reloadFile(area3);
		}
		
		public void update(){
			
			//버튼 text 바꾸기
			panel2.save.setText("update");
			
			//event 추가
			panel2.save.addActionListener(a);
			panel2.cancel.addActionListener(a);
			go2.addActionListener(a);
			
			//네번째 카드 레이아웃
			area4.setEditable(false);
			card[3].add(text3);
			card[3].add(go2);
			card[3].add(panel2);
			card[3].add(scroll[3]);
			reloadFile(area4);
		}
		
		
		class MenuBar extends JMenuBar{
			//frame상단의 menubar를 설정하는 클래스
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
				
				//menu에 item 넣고, item들 event 처리 추가
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
					//두번째 카드 레이아웃으로 간다.
				}
				else if(o==button[2]|o==menubar.item[2]){
					// D E L E T E
					layout.last(panel_s);
					layout.previous(panel_s);
					//세번째 카드 레이아웃으로 간다.
				}
				else if(o==button[3]|o==menubar.item[3]){
					// U P D A T E
					layout.last(panel_s);
					//마지막 카드 레이아웃으로 간다.
				}
				
				
				else if(o==go){
					// 검 색 결 과 출 력
					int count=0;
					//3조건 모두 해당하지 않는 수를 세는 변수.
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
							//입력한 값의 이름이 있으면 출력
							area.append(s.toString()+"\n");
						}
						else if(s.id.equalsIgnoreCase(user_input)){
							//입력한 값의 id가 있으면 출력
							area.append(s.toString()+"\n");
						}
						else if(s.department.equalsIgnoreCase(user_input)){
							//입력한 값의 전공이 있으면 출력
							area.append(s.toString()+"\n");;
						}else{
							count++;
						}
					}
					if(count==info.size()){
						//아무것도 포함하지 않으면 no result 출력
						area.setText("No result");
					}
				}
				
				else if(o==panel.cancel){
					//insert에서 cancel 한 경우
					panel.t1.setText("");
					panel.t2.setText("");
					panel.t3.setText("");
					panel.t4.setText("");
					panel.R2.setSelected(true);
				}
				
				else if(o==panel.save){
					// 입 력 값 추 가
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
						//추가된 정보를 file과,HashMap에 업로드
						
						//텍 스 트 칸 초 기 화
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
						//excepion이 발생하는 경우를 말한다.
					}
					
				}
				
				else if(o==del){
					//해 당 정 보 삭 제
					String user_input=text2.getText();
					students.remove(user_input);
					//user_input을 id로 가진 정보를 제거한다
					writeFile();
					//추가된 정보를 file에 업로드 및 area3 초기화
					text2.setText("");
				}

				else if(o==go2){
					//검 색 결 과 불 러 오 기
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
							//만약 검색한 키워드와 같은 이름이나 id,혹은 부서를 가진 학생이 있다면 실행
							
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
					// 정 보 업 데 이 트
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
						//추가된 정보를 file에 업로드
						
						//텍 스 트 칸 초 기 화
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
						//excepion이 발생하는 경우를 말한다.
					}
				}
				
				else if(o==panel2.cancel){
					//업 데 이 트 취 소
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
		
		/* File I/0  함 수 들 */
		
		private void readFile(JTextArea ta){
			// 파일에서 읽은 HASHMAP을 저장하고
			// 이를 AREA에 출력
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
			// file에 다시 씀
			try {
				String fileName = "StudentData.dat"; 
				ObjectOutputStream outputStream = 
				new ObjectOutputStream(new FileOutputStream(fileName));
				outputStream.writeObject(students);
				outputStream.close();
				
				// 수 정 된 파 일 내 용 재 업 로 드
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
			//파일에 수정 된 내용을 text area에 업로드
			ta.setText("");
			Iterator<String> it = students.keySet().iterator();
			while(it.hasNext()){
			ta.append(students.get(it.next()).toString()+"\n");
			//text area을 다시 초기화한다.
			}
		}
}



