package register;

import java.util.ArrayList;
import java.util.List;

public class Classes {
	private Integer classID;
	private String name;
	private String available;
	private List<Register> registers;
	
	public Classes(int classID, String name, String available){
		this.classID = classID;
		this.name = name;
		this.available = available;
		registers = new ArrayList<Register>();
	}
	
	public String getClassID(){
		return classID.toString();
	}
	public String getName(){
		return name;
	}
	
	public void changeAvailable(String available){
		this.available = available;
	}
	
	public String getAvailable(){
		return available;
	}
	
	public void addRegister(int registerID, String date, String time){
		Register rs = new Register(registerID, date, time);
		
		registers.add(rs);
	}
	
	public List getRegisters(){
		List<String> ls = new ArrayList<String>();
		
		for(int i = 0; i < registers.size(); i++){
			ls.add(registers.get(i).getRegisterID() + " " + registers.get(i).getDate() + " " + registers.get(i).getTime() );
		}
		
		return ls;
	}
	
	public String getDetails(String regID){
		String display = null;
		
		for(int i = 0; i < registers.size(); i++){
			if(registers.get(i).getRegisterID().equals(regID))
				display = registers.get(i).getRegisterID() + " " + registers.get(i).getDate() + " " + registers.get(i).getTime();
		}
		
		return display;
	}
	
	public Register getRegister(String classID){
		for(int i = 0; i < registers.size(); i++){
			if(registers.get(i).getRegisterID().equals(classID))
				return registers.get(i);
		}
	
		return new Register(0, "false", "false");
	}
	
	public int getSizeOfRegister(){
		return registers.size();
	}
	
	public Integer unusedRegID(){
		Integer id = registers.size() + 1;
		
		for(int i = 0; i < registers.size(); i++){
			if(registers.get(i).getRegisterID().equals(i))
				id = i;
		}
		
		return id;
	}

}
