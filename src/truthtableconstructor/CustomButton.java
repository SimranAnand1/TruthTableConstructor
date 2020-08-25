/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package truthtableconstructor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 *
 * @author simran anand
 */
public class CustomButton extends JButton{
	public CustomButton(String text , GUI gui) {
		super(text);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gui.getExpressionStrings().add(text);
				gui.update();
			}
		});
	}
}
