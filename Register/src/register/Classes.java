package register;

public class Classes {
	private Integer classID;
	private String name;
	
	public Classes(int classID, String name){
		this.classID = classID;
		this.name = name;
		
		System.out.println("Class Made!");
	}
	
	public String getClassID(){
		return classID.toString();
	}
	public String getName(){
		return name;
	}
}
