package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import Utils.ObjectJson;

public class JsonController {
	public void getDateByName(String name) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("C://TEMP/hol.json")); //https://date.nager.at/api/v2/publicholidays/2020/US
		
		String text = "";
		
		String entry;
		while((entry = in.readLine()) != null) {			
			text += entry;
		}										
		
		List<ObjectJson> objs = new LinkedList<ObjectJson>();
		
		String ans = "";
		boolean isNewObject=false, isQuoting=false, doneKey=false;
		
		String buffer = "";
		for(Character it: text.toCharArray()) {
			if(it == '{' && !isNewObject) {
				isNewObject = true;
				objs.add(new ObjectJson());
				buffer = "";
			} else if(isNewObject) {
				if(buffer.equals("fixed:")) isNewObject = isQuoting = doneKey = false;
				
				if(it == '\"' && !isQuoting && !doneKey) {				
					buffer = "";		
					isQuoting = true;
				} else if(it == '\"' && isQuoting && !doneKey) {
					objs.getLast().add(buffer);
					isQuoting = false;
					doneKey = true;
				} else if(it == '\"' && !isQuoting && doneKey) {
					buffer = "";
					isQuoting = true;
				} else if(it == '\"' && isQuoting && doneKey) {
					isQuoting = false;
					doneKey = false;
					objs.getLast().setValueLast(buffer);
				} else if(it == '}') {				
					isNewObject = false;
					buffer = "";
				}
				else {
					buffer += Character.toString(it);
				}
			}
		}
					
		for(ObjectJson it: objs) {
			if(it.atts.get(2).s.equals(name)) {
				ans = (String)it.atts.get(0).s; 
			}
		}
		
		if(ans.equals("")) throw new Exception("Feriado não encontrado");
		
		JOptionPane.showMessageDialog(null, "A data do feriado " + name + " é: " + ans);
	}
}
