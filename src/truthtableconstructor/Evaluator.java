/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package truthtableconstructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author simran anand
 */
public class Evaluator {
private ArrayList<Operator> supportedOperators ;
	private ArrayList<String> postFix ;
	private ArrayList<String> operands ;
	public Evaluator(ArrayList<Operator> supportedOperators) {
		this.supportedOperators = supportedOperators;
	}
	
	public Map<String , Boolean> evalute(ArrayList <String> postFix) throws Exception{
		this.postFix = postFix;
		operands = getOperands();
		int numTests = (int) (Math.pow(2,operands.size())-1);
		Map<String , Boolean> table = new HashMap<>();
		Map<String , Boolean> currentValues = new HashMap<>();
		for(String x : operands) {
			currentValues.put(x, false);
		}
		for(int i = 0 ; i <= numTests ; i ++) {
			Stack<Boolean> expression = new Stack();
			StringBuilder bits = new StringBuilder(Integer.toBinaryString(i)).reverse();
			for(int j = 0 ; j < operands.size() ; j++) {
				if(j < bits.length()) {
				if(bits.charAt(j) == '0')
					currentValues.put(operands.get(j), false);
				else if (bits.charAt(j) == '1')
					currentValues.put(operands.get(j), true);
				}
				else {
					bits.append('0');
					currentValues.put(operands.get(j), false);
				}
			}
			for(String x : postFix) {
				if(isOperator(x)) {
					Operator operator = getInstance(x);
					if(operator.numberOfOperands() == 1) {
						if(expression.size() < 1)
							throw new Exception ("Wrong Format");
						else {
							expression.push(operator.apply(expression.pop()));
						} 
					}
					else if (operator.numberOfOperands() == 2) {
						if(expression.size() < 2)
							throw new Exception ("Wrong Format");
						else {
							boolean operand2 = expression.pop();
							boolean operand1 = expression.pop();
							expression.push(operator.apply(operand1,operand2));
						}
					}
				}
				else {
					expression.push(currentValues.get(x));
				}
			}
			if(expression.size() != 1)
				throw new Exception("Wrong Format !!");
			boolean result = expression.pop();
			table.put(bits.toString(), result);
		}
		return table;
	}
	
	private Operator getInstance(String element) {
		for(Operator x : supportedOperators) {
			if(x.isOperator(element)) {
				return x.getInstance();
			}
		}
		return null;
	}

	private ArrayList<String> getOperands() {
		ArrayList<String> operands = new ArrayList<>();
		for(String x : postFix) {
			if(!isOperator(x)) {
				if(!operands.contains(x))
					operands.add(x);
			}
		}
		return operands;
	}
	
	public ArrayList<String> getOperands(ArrayList<String> postFix) {
		this.postFix = postFix ;
		ArrayList<String> operands = new ArrayList<>();
		for(String x : postFix) {
			if(!isOperator(x)) {
				if(!operands.contains(x))
					operands.add(x);
			}
		}
		return operands;
	}

	private boolean isOperator(String element) {
		for(Operator x : supportedOperators) {
			if(x.isOperator(element))
				return true;
		}
		return false;
	}
}   

