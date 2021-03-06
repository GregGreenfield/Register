package register;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
	private int TID;
	private String name;
	private List<Classes> classes;
	
	public Teacher(int TID, String name)
	{
		this.TID = TID;
		this.name = name;
		classes = new ArrayList<Classes>();
	}
	
	public Teacher() {
		TID = 0;
		name = "";
	}

	public String getName(){
		return name;
	}
	
	public int getTID(){
		return TID;
	}
	
	public void addClass(int classID, String className, String available){
		Classes cl = new Classes(classID, className, available);
		
		classes.add(cl);		
	}
	
	public List getClasses(){
		List<String> ls = new ArrayList<String>();
		
		for(int i = 0; i < classes.size(); i++){
			ls.add(classes.get(i).getClassID() + " " + classes.get(i).getName() );
		}
		
		return ls;
	}

	public Classes getClass(String classID){
		for(int i = 0; i < classes.size(); i++){
			if(classes.get(i).getClassID().equals(classID))
				return classes.get(i);
		}
	
		return new Classes(0, "false", "f");
	}
	
	public Classes getLastClass(){
		return classes.get(classes.size()-1);
	}
}
