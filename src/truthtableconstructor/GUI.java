/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package truthtableconstructor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author simran anand
 */
public class GUI {
    private TruthTableConstructor constructor;
    private ArrayList<String> expressionStrings = new ArrayList<String>();
	private javax.swing.JButton generate;
	private javax.swing.JButton delete;
	private javax.swing.JButton clear;
    private javax.swing.JLabel expression;
	private javax.swing.JPanel btnContainer;
	private javax.swing.JPanel set1Container;
	private javax.swing.JPanel set2Container;
	private javax.swing.JPanel deleteContainer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textArea;
    private javax.swing.JFrame mainWindow;
    
	public GUI(TruthTableConstructor constructor) {
		this.constructor = constructor;
		initCompenents();
		initGenerate();
		initbtnCont();
		initset1Cont();
		initset2Cont();
		initdeleteCont();
  
	}
	
	private void initdeleteCont() {
		deleteContainer.add(new CustomButton("(", this));
		delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!expressionStrings.isEmpty()) {
					expressionStrings.remove(expressionStrings.size()-1);
					update();
				}
			}
		});
		clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				expressionStrings = new ArrayList<>();
				update();
			}
		});
		deleteContainer.add(clear);
		deleteContainer.add(delete);
		deleteContainer.add(new CustomButton(")", this));
		deleteContainer.revalidate();
	}

	private void initset1Cont() {
		char a = 'A';
		for(int i = 0 ; i < 13 ; i++) {
			
			set1Container.add(new CustomButton(new StringBuilder().append(a).toString(),this));
			a +=1;
		}
		set1Container.revalidate();
	}
	
	private void initset2Cont() {
		char a = 'A'+13;
		for(int i = 0 ; i < 13 ; i++) {
			set2Container.add(new CustomButton(new StringBuilder().append(a).toString(),this));
			a +=1;
		}
		set2Container.revalidate();
	}
	
	private void initGenerate() {
		generate.setText("Generate");
        generate .addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder st = new StringBuilder();
				String exp = expression.getText();
				if(!exp.trim().equalsIgnoreCase("")) {
					try {
						Map<String , Boolean> table  = constructor.construct(exp);
						List<Map.Entry<String, Boolean>> list = new LinkedList<Map.Entry<String, Boolean>>(table.entrySet());
						Collections.sort(list , new Comparator<Map.Entry<String, Boolean>>() {
							public int compare(Map.Entry<String, Boolean> o1, Map.Entry<String, Boolean> o2) {
				                return (o1.getKey()).compareTo( o2.getKey() );
				            }
						});
						ArrayList <String> operands = constructor.getOperands(exp);
						for(String x : operands)
							st.append(x);
						st.append("\n");
						for(Entry<String , Boolean> z : list) {
							st.append(z.getKey()+" ");
							if(z.getValue() == false)
								st.append("F\n");
							else
								st.append("T\n");
						}
						textArea.setText(st.toString());
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(mainWindow, "Wrong Formate", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
	}

	private void initbtnCont() {
		for(Operator x : constructor.getSupportedOperators()) {
			btnContainer.add(new CustomButton(x.getRepresentation(), this));
		}
		btnContainer.revalidate();
	}

	private void initCompenents() {
		mainWindow = new javax.swing.JFrame();
		jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        expression = new javax.swing.JLabel();
        btnContainer = new javax.swing.JPanel();
        generate = new javax.swing.JButton();
        set1Container = new javax.swing.JPanel();
        deleteContainer = new javax.swing.JPanel();
        set2Container = new javax.swing.JPanel();

        mainWindow.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setFont(new Font("Arial", Font.PLAIN, 24));
        jScrollPane1.setViewportView(textArea);

        expression.setBackground(new java.awt.Color(255, 255, 255));
        expression.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        expression.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        expression.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(mainWindow.getContentPane());
        mainWindow.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(expression, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(generate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                    .addComponent(deleteContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(set1Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(set2Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(set1Container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(set2Container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(expression, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(deleteContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(generate, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 199, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        mainWindow.pack();
        mainWindow.setVisible(true);
	}
	
	public javax.swing.JLabel getExpression() {
		return expression;
	}
	
	public ArrayList<String> getExpressionStrings() {
		return expressionStrings;
	}
	
	public void update() {
		StringBuilder st = new StringBuilder();
		for(String x : expressionStrings) {
			st.append(x +" ");
		}
		expression.setText(st.toString());
	}
}


    

