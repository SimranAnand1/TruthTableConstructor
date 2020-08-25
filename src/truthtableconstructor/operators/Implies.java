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
public class Implies implements Operator{
	private static final int PRECEDENCE = 3;
	private static final String REPRESENTATION = "=>";
	private static final int OPERANDS = 2;
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
	public boolean apply(boolean operand) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean apply(boolean operand_1, boolean operand_2) {
		return (operand_1&!operand_2) ? false : true;
	}
    
	@Override
    public Operator getInstance() {
    	return new Implies();
    }

	@Override
	public String getRepresentation() {
		return REPRESENTATION;
	}
}