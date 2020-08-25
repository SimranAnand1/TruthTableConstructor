/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package truthtableconstructor;

/**
 *
 * @author simran anand
 */
public interface Operator {
    public int precedenceLevel();
    public boolean isOperator(String element);
    public int numberOfOperands();
    public boolean apply(boolean operand);
    public boolean apply(boolean operand_1 , boolean operand_2);
    public Operator getInstance();
    public String getRepresentation();
}   

