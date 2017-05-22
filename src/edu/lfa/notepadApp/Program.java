package edu.lfa.notepadApp;

import edu.lfa.notepadApp.helper.Filehelper;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Program extends JFrame {

    private JButton btnSave, btnOpen;
    private JTextField txtPath;
    private JTextArea txtPad;
    private JFileChooser fileChooser;
    private JScrollPane txtSp;

    public Program() {
        setTitle("Notes");
        initComponents();
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(600, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enabled(false);
    }

    public void enabled(boolean status) {
        btnSave.setEnabled(status);
        txtPath.setEnabled(true);

    }

    public void initComponents() {
        txtPath = new JTextField(20);

        txtPad = new JTextArea(20, 40);
        txtPad.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                enabled(!txtPad.getText().equals(""));

            }
        });

        btnSave = new JButton("Save");
        btnSave.addActionListener(new SaveFileListener());
        txtSp = new JScrollPane(txtPad);
        btnOpen = new JButton("Open File");
        btnOpen.addActionListener(new OpenFileListener());
        Container container = getContentPane();
        container.add(txtPath);
        container.add(btnSave);
        container.add(btnOpen);
        container.add(txtSp);
    }

    private class SaveFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                Filehelper.saveFile(txtPath.getText(), txtPad.getText());

                txtPath.setText("");
                txtPad.setText("");

                JOptionPane.showMessageDialog(null, "Noted!");
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null, ioe.getMessage());
            }
        }

    }
    
    private class OpenFileListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(btnOpen);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        txtPad.setText(Filehelper.openFile(fileChooser.getSelectedFile().getPath()));
                    } catch (IOException ioe) {
                        JOptionPane.showMessageDialog(null, ioe.getMessage());
                    }
                }
        }
    }
    
    
    
    public static void main(String[] args) {
        Program p = new Program();
    }

}
