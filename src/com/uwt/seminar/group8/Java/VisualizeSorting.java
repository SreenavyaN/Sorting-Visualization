package com.uwt.seminar.group8.Java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static java.awt.Color.cyan;

@SuppressWarnings("serial")
public class VisualizeSorting extends JFrame {

	private final String[] Sorts = {"Bubble", "Selection", "Insertion", "Merge","Quick"};
	
	private int sizeModifier;

	private JPanel wrapper;
	private JPanel arrayWrapper;
	private JPanel detailWrapper;
	private JPanel buttonWrapper;
	private JPanel[] squarePanels;
	private JButton start;
	private JButton reset;
	private JComboBox<String> selection;
	private JLabel sortName;
	private JLabel dataType;


	private GridBagConstraints c;
	private JCheckBox stepped;
	
	public VisualizeSorting(){
		super("Sorting Visualizer");
		start = new JButton("Start");
		reset = new JButton("Reset");
		buttonWrapper = new JPanel();
		arrayWrapper = new JPanel();
		detailWrapper = new JPanel();
		wrapper = new JPanel();
		selection = new JComboBox<String>();
		sortName = new JLabel("");
		sortName.setFont(new Font("Courier New", Font.BOLD, 20));
		sortName.setForeground(Color.RED);
		dataType = new JLabel("");
		dataType.setFont(new Font("Courier New", Font.BOLD, 20));
		dataType.setForeground(Color.BLUE);


		stepped = new JCheckBox("Stepped-decreasing");
		c = new GridBagConstraints();
		
		for(String s : Sorts) selection.addItem(s);
		
		arrayWrapper.setLayout(new GridBagLayout());
		wrapper.setLayout(new BorderLayout());
		arrayWrapper.setBackground(cyan);
		arrayWrapper.setOpaque(true);
		wrapper.setBackground(cyan);
		wrapper.setOpaque(true);

		c.insets = new Insets(0,1,0,1);
		c.anchor = GridBagConstraints.SOUTH;
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortName.setText((String) selection.getSelectedItem() + " Sort :: ");
				if(stepped.isSelected())
					dataType.setText("Decreasing Stepped Dataset");
				else
					dataType.setText("Random Dataset");
				SortingVisualizerMain.startSort((String) selection.getSelectedItem());
			}
		});

		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SortingVisualizerMain.isSorting = false;
				SortingVisualizerMain.resetArray();
			}
		});
		
		stepped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(stepped.isSelected())
					dataType.setText("Decreasing Stepped Dataset");
				else
					dataType.setText("Random Dataset");
				SortingVisualizerMain.stepped = stepped.isSelected();
			}
		});
		

		buttonWrapper.add(stepped);
		buttonWrapper.add(start);
		buttonWrapper.add(reset);
		buttonWrapper.add(selection);

		detailWrapper.add(sortName);
		detailWrapper.add(dataType);


		wrapper.add(buttonWrapper, BorderLayout.SOUTH);
		wrapper.add(detailWrapper,BorderLayout.NORTH);
		wrapper.add(arrayWrapper);
		
		add(wrapper);

		setExtendedState(JFrame.MAXIMIZED_BOTH );
		
		addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				// Reset the sizeModifier
				// 90% of the windows height, divided by the size of the sorted array.
				sizeModifier = (int) ((getHeight()*0.9)/(squarePanels.length));
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// Do nothing
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// Do nothing
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// Do nothing
			}
			
		});

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}
	
		public void preDrawArray(Integer[] squares){
		squarePanels = new JPanel[SortingVisualizerMain.sortDataCount];
		arrayWrapper.removeAll();
		// 90% of the windows height, divided by the size of the sorted array.
		sizeModifier =  (int) ((getHeight()*0.9)/(squarePanels.length));
		for(int i = 0; i< SortingVisualizerMain.sortDataCount; i++){
			squarePanels[i] = new JPanel();
			squarePanels[i].setPreferredSize(new Dimension(SortingVisualizerMain.blockWidth, squares[i]*sizeModifier));
			squarePanels[i].setBackground(Color.red);
			arrayWrapper.add(squarePanels[i], c);
		}
		repaint();
		validate();
	}
	
	public void reDrawArray(Integer[] x){
		reDrawArray(x, -1);
	}
	
	public void reDrawArray(Integer[] x, int y){
		reDrawArray(x, y, -1);
	}
	
	public void reDrawArray(Integer[] x, int y, int z){
		reDrawArray(x, y, z, -1);
	}
	
	// reDrawArray does similar to preDrawArray except it does not reinitialize the panel array.
	public void reDrawArray(Integer[] squares, int working, int comparing, int reading){
		arrayWrapper.removeAll();
		for(int i = 0; i<squarePanels.length; i++){
			squarePanels[i] = new JPanel();
			squarePanels[i].setPreferredSize(new Dimension(SortingVisualizerMain.blockWidth, squares[i]*sizeModifier));
			if (i == working){
				squarePanels[i].setBackground(Color.yellow);
			}else if(i == comparing){
				squarePanels[i].setBackground(Color.blue);
			}else if(i == reading){
				squarePanels[i].setBackground(Color.green);
			}else{
				squarePanels[i].setBackground(Color.red);
			}
			arrayWrapper.add(squarePanels[i], c);
		}
		repaint();
		validate();
	}
	
}
