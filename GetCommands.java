/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pk.edu.nust.seecs.bscs2.advancedprogramming.lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import org.nfunk.jep.JEP;



/**
 *
 * @author Ghulam Ul Hassan Ali
 * This class reads the java commands from a file
 * Format and save them
 * check for any syntax error. If found then Raise Exceptions or continue otherwise
 * returns an array of commands to be executed.
 */
public class GetCommands {

    
    
    int totalCommands, totalDeclarations, currentLineNumber;
    BufferedReader br;
    String currentLine;
    String currentLineCommands[];
    ArrayList<String> variablesInString = new ArrayList<String>();
    
    ArrayList<Variable> variables = new ArrayList<Variable>();
    
    static HashMap <String, Integer> variablesMapped = new HashMap();
    
    static HashMap<String,Variable> keyMappedVariables=new HashMap<String,Variable>();
    
    public GetCommands() {
        totalCommands=0;
        totalDeclarations=0;
        currentLineNumber=0;
    }

    public GetCommands(int totalCommands, int totalDeclarations, BufferedReader br, String currentLine) {
        this.totalCommands = totalCommands;
        this.totalDeclarations = totalDeclarations;
        this.br = br;
        this.currentLine = currentLine;
    }
    
    public void readCommands(){ 
    
        try{   
            
            br = new BufferedReader(new FileReader("C:\\Users\\hchsr_000\\Documents\\NetBeansProjects\\Advanced Programming Lab-2\\src\\pk\\edu\\nust\\seecs\\bscs2\\advancedprogramming\\lab2\\javaCommands.txt"));
            br.readLine();
            while( (currentLine = br.readLine()) != null){
                
                currentLineNumber++;
                currentLine = currentLine.trim();
                
                if (currentLine.length() > 0){  //if the line is not all whitespaces                    
                    currentLineCommands = currentLine.split(";");                    
                    
                    
                    for(int x=0; x<currentLineCommands.length;x++){
                        totalCommands++;
                        System.out.println("\n\nCurrent command being read:" + currentLineCommands[x] +"   "+currentLineNumber+  totalCommands);

                        //errors checking
                        checkSyntax(currentLineCommands[x]);
                        //variable declaration commands
                        if(currentLineCommands[x].toLowerCase().contains("let")){
                            if(!currentLineCommands[x].contains("Let ")){
                                throw new IllegalArgumentException("Syntax Error: at Line # :"+currentLineNumber+". 'Let' is a keyword and should be written as 'Let' followed by a space.\n");
                            }

                            totalDeclarations++;

                            //System.out.println("Is a decalration" + totalDeclarations);                        
                            handleDeclaration(currentLineCommands[x]);
                            
                        }else if(currentLineCommands[x].toLowerCase().contains("print")){//variable print commands
                            if(!currentLineCommands[x].contains("Print ")){
                                throw new IllegalArgumentException("Syntax Error: at Line # :"+currentLineNumber+". 'Print' is a keyword and should be written as 'Print' followed by a space.\n");
                            }

                            totalDeclarations++;

                            //System.out.println("Is a Print statement" + totalDeclarations);                        
                            printValue(currentLineCommands[x]);
                            
                        }else if(currentLineCommands[x].contains("+") || currentLineCommands[x].contains("-") || currentLineCommands[x].contains("*")){//arithmetic commands
                            arithmeticCommands(currentLineCommands[x]);                        
                        }
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();           
        }finally{            
            try{
                if(br!=null)
                    br.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        } 
        System.out.println("total commands found from file:" + totalCommands);        
    }
    
    
    private void handleDeclaration(String declaration){
        System.out.println("In handleDeclaration");  
        String[] parts;
        String regex = "[0-9]+";
        String regex1 = "[+-/\\*]";
        String tempVar="";
        int tempVal=0;
        //extract variables to declare
        parts = declaration.split("Let");
        System.out.println("Splitted command:");
        
        parts = parts[1].split("=");
        System.out.println("-"+parts[0]+"\n-"+parts[1]);
        
        variablesInString.add(parts[0].trim()); //hold all the declared variables here
        String[] rightExpression = parts[1].split("\\s");
        
        for(int x=1; x<rightExpression.length;x++){
             System.out.println(rightExpression[x]);
             if(!rightExpression[x].matches(regex) && !rightExpression[x].matches(regex1) ){
                 System.out.println(rightExpression[x]+" Is not a number");
                 if(!variablesInString.contains(rightExpression[x])){//declared already or not?
                     System.out.println(rightExpression[x]+" Is not declared");
                     throw new IllegalArgumentException("Undeclared variable: at Line # :"+currentLineNumber);
                 }
                 //value
             }
        }
        
        
        
        
       
//        if(! parts[0].contentEquals("Let")){
//            throw new IllegalArgumentException("Syntax Error: at Line # :"+currentLineNumber+". 'Let' must be the written at the start of command when declaring a variable.\n");
//        }
  
        //IF LET -/=/* X is written. handle this Operator should not be before variable
//        if(! parts[1].contentEquals("+")){
//            throw new IllegalArgumentException("Syntax Error: at Line # :"+currentLineNumber+". 'Let' must be the written at the start of command when declaring a variable.\n");
//        }
        
        for(int x=1; x< parts.length; x++){
            parts[x]=parts[x].trim();
            //System.out.println("\n- "+parts[x].trim());
            if(!( parts[x].contentEquals("+") || parts[x].contentEquals("-") || parts[x].contentEquals("*") ||  parts[x].contentEquals("=") ) ){    //it is not + - * =
                variablesInString.add(parts[x]);
            }
            //System.out.println(variablesInString.toString());
            //variables.contains();
        }
        
        
        
        
        
        
        
    }
    
    private void printValue(String print){
        System.out.println("In printValue");  
    }
    
    private void arithmeticCommands(String arithmeticOperation){
        //if arithmetic operation occurs on the left side of the assignment operator
        System.out.println("in arithmetic operation");
        if(arithmeticOperation.contains("=")){
//            if((arithmeticOperation.indexOf("+") < arithmeticOperation.indexOf("=") ) || (arithmeticOperation.indexOf("-") < arithmeticOperation.indexOf("=")) || (arithmeticOperation.indexOf("*") < arithmeticOperation.indexOf("*")) ){
//                throw new IllegalArgumentException("Syntax Error: at Line # :"+currentLineNumber+". Arithmetic operation should be on the right side of assignment operator\n");
//            }
            
        }
        else{
            System.out.println("Arithmetic command doesn't contain assignment operator.");
        }
        
    }
    
    public static boolean evaluateExpression(Variable var,String expression){
		try{
			for (String key :keyMappedVariables.keySet()) {
				if (expression.contains(key)) {
					expression = expression.replace(key, (CharSequence)( keyMappedVariables.get(key).getValue()+""));
				}
			}
			ScriptEngineManager mgr = new ScriptEngineManager();
			ScriptEngine engine = mgr.getEngineByName("JavaScript");
			float value=Float.parseFloat(engine.eval(expression)+"");
			var.setValue((int)value);
			keyMappedVariables.remove(keyMappedVariables.get(var.getName()));
			keyMappedVariables.put(var.getName(), var);
			return true;
		}catch(Exception e){
			return false;
		}

	}
    
    private int addTwoVariables(Variable v1, Variable v2){                
        return v1.getValue() + v2.getValue();
    }    
    private int subtractTwoVariables(Variable v1, Variable v2){
        return v1.getValue() - v2.getValue();
    }
    private int multipleTwoVariables(Variable v1, Variable v2){
        return v1.getValue() * v2.getValue();
    }
    
    private void checkSyntax(String command){
        //if let and print are both used in a command.
        if(command.toLowerCase().contains("let") && command.toLowerCase().contains("print")){
            throw new IllegalArgumentException("Syntax Error: at Line # :"+currentLineNumber+". Command unrecognized. 'Let' and 'Print' can not be used together in the same command.\n");
        }
        
        
    }
    
    
}
