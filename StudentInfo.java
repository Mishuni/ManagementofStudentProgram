import java.io.Serializable;

class StudentInfo implements Serializable{
	String name;
	char gender ;
	String id;
	String department;
	int age;
	
	StudentInfo(){
		name ="no name";
		gender='F';
		id="no id";
		department="empty";
		age=0;
	}
	
	StudentInfo(String name, char gender, String id,String department,int age){
		this.name=name;
		this.age=age;
		this.gender=gender;
		this.id=id;
		this.department=department;
	}
	
	public String toString(){
		return name+"("+age+")"+"\t"+gender+"\t"+id+"\t"+department;
	}
	
}