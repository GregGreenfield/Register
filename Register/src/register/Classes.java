package register;

import java.util.ArrayList;
import java.util.List;

public class Classes {
	private Integer classID;
	private String name;
	private List<Register> registers;
	
	public Classes(int classID, String name){
		this.classID = classID;
		this.name = name;
		registers = new ArrayList<Register>();
		
		System.out.println("Class Made!");
	}
	
	public String getClassID(){
		return classID.toString();
	}
	public String getName(){
		return name;
	}
	
	public void addRegister(int registerID, String date, String time){
		Register rs = new Register(registerID, date, time);
		
		registers.add(rs);
	}
	
	public List getRegisters(){
		List<String> ls = new ArrayList<String>();
		
		for(int i = 0; i < registers.size(); i++){
			System.out.println(registers.get(i).getDate() + " " + registers.get(i).getTime() );
			ls.add(registers.get(i).getRegisterID() + " " + registers.get(i).getDate() + " " + registers.get(i).getTime() );
		}
		
		return ls;
	}
	public Register getRegister(String classID){
		for(int i = 0; i < registers.size(); i++){
			if(registers.get(i).getRegisterID().equals(classID))
				return registers.get(i);
		}
	
		return new Register(0, "false", "false");
		
	}

}
