import java.awt.*;

import javax.swing.*;

class SecondPanel extends JPanel{
	//insert â
	//update â
	//Ŭ �� �� �� ��
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();//panel�� �ι�° ���� �ι�° ���� �� panel2�� ����
	JPanel panel3 = new JPanel();//button �ΰ��� ���� ���ο� �������� panel3 ����
	
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
	//default�� ���õǾ� �ְ� �ϱ� ���� �ι�° argument�� true�� ����

	JButton save = new JButton("insert");
	JButton cancel = new JButton("cancel");

	
	SecondPanel(){
		
		setLayout(new BorderLayout());
		panel.setLayout(new GridLayout(0,2,200,1));
		
		//NAME ĭ �� ��
		panel.add(name);
		panel.add(t1);
		
		panel.add(age);
		panel.add(t2);
		
		//GENDER ĭ �� ��
		panel2.setLayout(new GridLayout(0,2));
		//panel2���� �ΰ��� ���� ������ layout�� ����
		panel2.add(R1);
		panel2.add(R2);
		panel.add(gender);//������ ������� panel�� label�� gender�� 2���� 1 ���� �Է�
		panel.add(panel2);//2������Ҹ� ����panel2�� panel�� 2���� 2���� �Է�
		
		ButtonGroup b = new ButtonGroup();//radiobutton �ΰ��� groupȭ
		b.add(R1);
		b.add(R2);
		
		//ID ĭ �� ��
		panel.add(id);
		panel.add(t3);//�ΰ��� ��Ҹ� panel�� 3�࿡ �־��ش�.
		//DEPARTMENT ĭ �� ��
		panel.add(department);
		panel.add(t4);//�ΰ��� ��Ҹ� panel�� 4�࿡ �־��ش�.
		//�� ư �� ��
		panel3.add(save);
		panel3.add(cancel);//panel3�� button �ΰ��� ����
		
		add(panel,BorderLayout.CENTER);
		add(panel3,BorderLayout.SOUTH);
		
		}
}