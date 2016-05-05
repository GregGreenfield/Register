package register;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
	private int TID;
	private String name;
	private List<Classes> classes;
	
	public Teacher(int TID, String name)
	{
		System.out.println("Teacher made!!!!");
		this.TID = TID;
		this.name = name;
		classes = new ArrayList<Classes>();
	}
	
	public String getName(){
		return name;
	}
	
	public int getTID(){
		return TID;
	}
	
	public void addClass(int classID, String className){
		Classes cl = new Classes(classID, className);
		
		classes.add(cl);		
	}
	
	public List getClasses(){
		List<String> ls = new ArrayList<String>();
		
		for(int i = 0; i < classes.size(); i++){
			System.out.println(classes.get(i).getName());
			ls.add(classes.get(i).getClassID() + " " + classes.get(i).getName() );
		}
		
		return ls;
	}
}
