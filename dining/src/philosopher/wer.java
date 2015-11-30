package philosopher;

import javax.swing.JApplet;
import javax.swing.JFrame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

//import philosopher.Launch.Phil;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Panel;
import javax.swing.JEditorPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class wer extends JPanel 
{
	String [] str = new String[5];
	JTextArea textArea;
	JTextArea textArea1;
	private JTextArea textArea_1;
	private JTextArea textField;
	private JTextArea textField_1;
	private JLabel lblNewLabel;
	private JRadioButton rdbtnNewRadioButton_4;
	private JRadioButton rdbtnNewRadioButton_5;
	private JRadioButton rdbtnNewRadioButton_3;
	private JRadioButton rdbtnNewRadioButton_2;
	private JRadioButton rdbtnNewRadioButton_1;
	public static void main(String[] args)
	{
		wer d =new wer();
		JFrame j = new JFrame();
		j.setTitle("Dining Philosopher");
		j.setSize(750, 550);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.getContentPane().add(d);
	}
	class Launch
	{
		 class semaphore
		{
			private int val;
			public semaphore(int i)
			{
				val=i;
			}
			public semaphore()
			{
				val=0;
			}
			public synchronized void Wait_for_turn()
			{
				val--;
				if (val<0)
				{	
					try
					{
						wait();
					}
					catch(InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
			public synchronized void pick_the_chopsticks()
			{
				val++;
				if(val<=0)
				{
					notify();
				}
			}
		}
		int num,i;
		//public static final int u=0;
		semaphore [] chopstick ;
		Phil [] phil;
		semaphore mutex ;
		Graphics f;
		/* Array of philosophers */
	//String ws = "";
		Launch()
		{
			num=5;						                      /* Total philosophers */
			chopstick = new semaphore[num];               /* Total Chopsticks */
		    mutex = new semaphore(1);                     /* Chopstick not occupied- initialised as 1 */
		    phil = new Phil[num];
		    for(i=0;i<num;i++)									
		    {
		    	chopstick[i]=new semaphore(1);						          /* Giving All chopsticks value as 1 - not occupied */
		    	phil[i]=new Phil(i);								          /* Initialising philosophers array */
		    	phil[i].start();									          /* Starting the simulation */
		    }
		//return ws;
		}
		class Phil extends Thread
		{
			int id;
			//public int u=0;
			String Status="";
			int quantum=6500;
			Phil (int x)
			{
				id=x;
				Status="Hungry";
			}
			public void run()
			{
				int i=0,j=0;
				while(true)
				{
					mutex.Wait_for_turn();						           /* Giving indication that chopstick is occupied */
					chopstick[id].Wait_for_turn();				           /* Left chopstick is occupied */
					chopstick[(id+1)%(num)].Wait_for_turn();         /* Right Chopstick is occupied */
					Status="Eating";
					
				
					
					PrintStatus();
					
					
					
					int timequantum=(int)(Math.random()*quantum);			       /* Eating time */
					try
					{
						Thread.sleep(timequantum);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					chopstick[id].pick_the_chopsticks();				       /* Left chopstick is free */
					chopstick[(id+1)%(num)].pick_the_chopsticks();   		   /* Right Chopstick is free */
					Status="Thinking";
					
					
					PrintStatus();
					
					
					mutex.pick_the_chopsticks();					           /* Indication that chopstick is free */
					timequantum=(int)(Math.random()*quantum);			       /* Thinking Time */
					try
					{
						Thread.sleep(timequantum);
					}	
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					Status="Hungry";
					
					
					PrintStatus();
				}
			}
			
			public void PrintStatus()
			{
				int u=0;
				int y=0;
				int r=0;
				String sw = "";
				for(int i=0;i<num;i++)
				{
					sw+=phil[i].Status+" ";
					if(phil[i].Status=="Eating")
						u=i;
				}
				String [] ew = sw.split(" ");
				System.out.println(sw);
			
				textArea.setText(phil[0].Status);
				textArea1.setText(phil[1].Status);
				textField.setText(phil[2].Status);
				textField_1.setText(phil[3].Status);
				textArea_1.setText(phil[4].Status);
		//		textField_1.setText("Eating");
				
				if(phil[0].Status=="Eating")
				{
					
					rdbtnNewRadioButton_5.setSelected(true);
					rdbtnNewRadioButton_1.setSelected(true);
					rdbtnNewRadioButton_4.setSelected(false);
					rdbtnNewRadioButton_2.setSelected(false);
					rdbtnNewRadioButton_3.setSelected(false);
					}
				else if(phil[1].Status=="Eating")
				{
					
					rdbtnNewRadioButton_2.setSelected(true);
					rdbtnNewRadioButton_1.setSelected(true);
					rdbtnNewRadioButton_5.setSelected(false);
					rdbtnNewRadioButton_4.setSelected(false);
					rdbtnNewRadioButton_3.setSelected(false);
						
				}
				else if(phil[2].Status=="Eating")
				{
					
					rdbtnNewRadioButton_2.setSelected(true);
					rdbtnNewRadioButton_3.setSelected(true);
					rdbtnNewRadioButton_5.setSelected(false);
					rdbtnNewRadioButton_1.setSelected(false);
					rdbtnNewRadioButton_4.setSelected(false);
					
				}
				else if(phil[3].Status=="Eating")
				{
					
					rdbtnNewRadioButton_5.setSelected(false);
					rdbtnNewRadioButton_2.setSelected(false);
					rdbtnNewRadioButton_1.setSelected(false);
					rdbtnNewRadioButton_4.setSelected(true);
					rdbtnNewRadioButton_3.setSelected(true);
						
				}
				else if(phil[4].Status=="Eating")
				{
					
					rdbtnNewRadioButton_4.setSelected(true);
					rdbtnNewRadioButton_5.setSelected(true);
					rdbtnNewRadioButton_1.setSelected(false);
					rdbtnNewRadioButton_2.setSelected(false);
					rdbtnNewRadioButton_3.setSelected(false);
					
				}
			//	System.out.println(ew[0]+" "+ew[1]+" "+ew[2]+" "+ew[3]+" "+ew[4]);
				
				
			//	paintComponent(f,u);
		    //		ws+=sw;
			//			return ws;
			//System.out.println(ws);
			}
			
		}
	}
	public wer() 
	{
		setBackground(Color.LIGHT_GRAY);
		JButton btnRun = new JButton("Run");
		btnRun.setBounds(31, 434, 97, 25);
		btnRun.addActionListener(new ActionListener() 
		{	
			public void actionPerformed(ActionEvent e) 
			{
				Launch t=new Launch();
			}
		});
		setLayout(null);
		add(btnRun);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(586, 434, 97, 25);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnStop);		
		textArea = new JTextArea();
		textArea.setForeground(Color.BLACK);
		textArea.setBounds(326, 20, 100, 22);
		add(textArea);
		textArea1 = new JTextArea();
		textArea1.setForeground(Color.BLACK);
		textArea1.setBounds(480, 220, 100, 22);
		add(textArea1);
		
		textArea_1 = new JTextArea();
		textArea_1.setForeground(Color.BLACK);
		textArea_1.setBounds(27, 220, 100, 22);
		add(textArea_1);
		
		textField = new JTextArea();
		textField.setForeground(Color.BLACK);
		textField.setBounds(370, 350, 100, 22);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextArea();
		textField_1.setForeground(Color.BLACK);
		textField_1.setBounds(180, 350, 100, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(213, 107, 228, 162);
		lblNewLabel.setIcon(new ImageIcon("C:\\Eclipse\\dining\\dp.png"));
		add(lblNewLabel);
		
		 rdbtnNewRadioButton_5 = new JRadioButton("");
		 rdbtnNewRadioButton_5.setBounds(172, 73, 27, 25);
		add(rdbtnNewRadioButton_5);
		
		 rdbtnNewRadioButton_1 = new JRadioButton("");
		 rdbtnNewRadioButton_1.setBounds(429, 73, 27, 25);
		add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton("");
		rdbtnNewRadioButton_2.setBounds(429, 278, 27, 25);
		add(rdbtnNewRadioButton_2);
		
		 rdbtnNewRadioButton_3 = new JRadioButton("");
		 rdbtnNewRadioButton_3.setBounds(295, 294, 27, 25);
		add(rdbtnNewRadioButton_3);
		
		rdbtnNewRadioButton_4 = new JRadioButton("");
		rdbtnNewRadioButton_4.setBounds(141, 278, 27, 25);
		add(rdbtnNewRadioButton_4);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	//	g.setColor(Color.RED);
	//	g.fillRect(150,110, 300, 150);
//		g.fillRect(10, 10, 20,10);
		g.setColor(Color.BLUE);
		g.fillRect(270,30, 50, 50);
		g.fillRect(80, 140, 50, 50);
		g.fillRect(200, 280, 50, 50);
		g.fillRect(350, 280, 50, 50);
		g.fillRect(475, 140, 50, 50);
	//	g.setColor(Color.YELLOW);
		//g.fillOval(270, 150, 60, 60);
		//color=BLACK;
	}
}

