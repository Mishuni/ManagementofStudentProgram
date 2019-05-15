import java.awt.*;

import javax.swing.*;

class SecondPanel extends JPanel{
	//insert 창
	//update 창
	//클 래 스 변 수
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();//panel의 두번째 행의 두번째 열에 들어갈 panel2를 설정
	JPanel panel3 = new JPanel();//button 두개를 담을 새로운 참조변수 panel3 생성
	
	JLabel name = new JLabel("NAME");
	JLabel age = new JLabel("AGE");
	JLabel gender = new JLabel("GENDER");
	JLabel id = new JLabel("ID");
	JLabel department = new JLabel("DEPARTMENT");
	
	JTextField t1 = new JTextField();
	JTextField t2 = new JTextField();
	JTextField t3 = new JTextField();
	JTextField t4 = new JTextField();

	JRadioButton R1 = new JRadioButton("Male");
	JRadioButton R2 = new JRadioButton("Female",true);
	//default로 선택되어 있게 하기 위해 두번째 argument를 true로 설정

	JButton save = new JButton("insert");
	JButton cancel = new JButton("cancel");

	
	SecondPanel(){
		
		setLayout(new BorderLayout());
		panel.setLayout(new GridLayout(0,2,200,1));
		
		//NAME 칸 추 가
		panel.add(name);
		panel.add(t1);
		
		panel.add(age);
		panel.add(t2);
		
		//GENDER 칸 추 가
		panel2.setLayout(new GridLayout(0,2));
		//panel2에는 두개의 열을 가지는 layout을 설정
		panel2.add(R1);
		panel2.add(R2);
		panel.add(gender);//기존에 만들었던 panel에 label인 gender를 2행의 1 열로 입력
		panel.add(panel2);//2가지요소를 가진panel2를 panel의 2행의 2열로 입력
		
		ButtonGroup b = new ButtonGroup();//radiobutton 두개를 group화
		b.add(R1);
		b.add(R2);
		
		//ID 칸 추 가
		panel.add(id);
		panel.add(t3);//두가지 요소를 panel의 3행에 넣어준다.
		//DEPARTMENT 칸 추 가
		panel.add(department);
		panel.add(t4);//두가지 요소를 panel의 4행에 넣어준다.
		//버 튼 추 가
		panel3.add(save);
		panel3.add(cancel);//panel3에 button 두개를 설정
		
		add(panel,BorderLayout.CENTER);
		add(panel3,BorderLayout.SOUTH);
		
		}
}