package project;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GUI extends JPanel {

  public GUI() {
    setLayout(new GridLayout(2, 1));
    JList lista = new JList(new String[] {"Finish the SQL Statements"});
    add(new JScrollPane(lista));
    ArrayList<String> stringList = new ArrayList<String>(){{
        add("insert");
        add("select");
        add("update");
        add("delete");
    }};
    JComboBox combo = new JComboBox();
    for (int i = 0; i < stringList.size(); i++) {
      combo.addItem(stringList.get(i));
      add(combo);
    }
    
  }

  
  
  
  
  
  
  
  
  public static void main(String args[]) {
	 GUI lista = new GUI();
    JFrame ventana = new JFrame();
    ventana.getContentPane().add(lista, BorderLayout.CENTER);
    ventana.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        System.exit(0);
      }
    });
    ventana.setSize(200, 200);
    ventana.setVisible(true);
    
    
    

  }
}