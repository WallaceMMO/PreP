package View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import Controller.JsonController;
import Utils.Pair;

public class Main {
	
	public static void main(String[] args) throws Exception {		
		String name = JOptionPane.showInputDialog("Digite o nome do feriado");
		
		
		JsonController cont = new JsonController();
		cont.getDateByName(name);
	}

}
