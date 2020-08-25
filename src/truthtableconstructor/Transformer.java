/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package truthtableconstructor;
import java.util.ArrayList;
import java.util.Stack;

import truthtableconstructor.operators.*;

/**
 *
 * @author simran anand
 */
public class Transformer {
     private ArrayList<Operator> supportedOperators ;
	
	public Transformer(ArrayList<Operator> supportedOperators) {
		this.supportedOperators = supportedOperators;
	}
	
	public ArrayList<String> infixToPostfix(String [] expression) throws Exception{
        ArrayList<String> postFix = new ArrayList<>();
		Stack<String> operator = new Stack<>();
        for(String x : expression) {
        	int index = indexOf(x);
        	if(index == -1) {
        		if(x.equalsIgnoreCase("("))
        			operator.add(x);
        		else if (x.equalsIgnoreCase(")")){
        			while(!operator.isEmpty() && !operator.peek().equalsIgnoreCase("(")) {
        				postFix.add(operator.pop());
        			}
        			if(operator.isEmpty())
        				throw new Exception("Wrong Format !!!!");
        			operator.pop();
        		}
        		else {
        			postFix.add(x);
        		}
        	}
        	else {
        		int currentPrecedence = supportedOperators.get(index).precedenceLevel();
        		while(!operator.isEmpty() && !operator.peek().equalsIgnoreCase("(") && currentPrecedence >= getPrecedence(operator.peek())){
        			postFix.add(operator.pop());
        		}
        		operator.push(x);
        	}
        }
        while(!operator.isEmpty()) {
        	if(operator.peek().equalsIgnoreCase("("))
        		throw new Exception("Wrong Format !!!");
        	postFix.add(operator.pop());
        }
        return postFix;
    }
	
	private int indexOf(String element) {
		int i = 0 ;
		for(Operator x : supportedOperators) {
			if(x.isOperator(element))
				return i ;
			i++ ;
		}
		return -1;
	}

	private int getPrecedence(String operator) {
		int index = indexOf(operator);
		return supportedOperators.get(index).precedenceLevel();
	}
}

