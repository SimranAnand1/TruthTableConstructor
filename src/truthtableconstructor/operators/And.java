/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package truthtableconstructor.operators;
import truthtableconstructor.Operator;

/**
 *
 * @author simran anand
 */
public class And implements Operator{
	private static final int PRECEDENCE = 2;
	private static final String REPRESENTATION = "AND";
	private static final int OPERANDS = 2;
	
	@Override
	public boolean apply(boolean operand) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean apply(boolean operand_1, boolean operand_2) {
		return operand_1 & operand_2;
	}

	@Override
	public int precedenceLevel() {
		return PRECEDENCE;
	}

	@Override
	public boolean isOperator(String element) {
		return REPRESENTATION.equalsIgnoreCase(element);
	}

	@Override
	public int numberOfOperands() {
		return OPERANDS;
	}
    
	@Override
    public Operator getInstance() {
    	return new And();
    }

	@Override
	public String getRepresentation() {
		return REPRESENTATION;
	}
	
}
