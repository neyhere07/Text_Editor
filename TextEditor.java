import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TextEditor implements ActionListener {
    //create a constructor
    //declaring properties of text editor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;
    //file menu item
    JMenuItem openFile,newFile,saveFile,cut,copy,paste,selectAll,close;
    JTextArea textArea;
    TextEditor(){
        //initialize frame
        frame = new JFrame();

        //initialize text area
        textArea = new JTextArea();

        //initialize a menu bar
        menuBar = new JMenuBar();

        //initialize menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //before add we need to create  initialize file items
        newFile = new JMenuItem("New Window");
        openFile= new JMenuItem("open File");
        saveFile = new JMenuItem("save File");

        //add action Listener here
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // add this menu items
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initialize edit items
        cut = new JMenuItem("cut");
        copy= new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll= new JMenuItem("select All");
        close = new JMenuItem("Close");

        //add action listener here
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //add  to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // adds menus to menubar
        menuBar.add(file);
        menuBar.add(edit);

        // set menu bar to frame;
        frame.setJMenuBar(menuBar);

        // create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout( new BorderLayout(0,0));

        //add text area panel
        panel.add(textArea,BorderLayout.CENTER);

        //CREATE SCROLL PEN
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //add scroll pen to panel
        panel.add(scrollPane,BorderLayout.CENTER);

        //add panel to frame
        frame.add(panel);

        //frame.add(textArea);
        //set dimension of frame
        frame.setBounds(460,150,400,400);
        frame.setVisible(true);
        frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cut) {
            //perform the action
            textArea.cut();
        }
        if (actionEvent.getSource() == copy) {
            textArea.copy();
        }
        if (actionEvent.getSource() == paste) {
            textArea.paste();
        }
        if (actionEvent.getSource() == selectAll) {
            textArea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            System.exit(0);
        }
        if (actionEvent.getSource() == openFile) {
            //open file chooser

            JFileChooser fileChooser = new JFileChooser("c:");
            int ChooseOption = fileChooser.showOpenDialog(null);

            //if we have clicked on open button
            if (ChooseOption == JFileChooser.APPROVE_OPTION) {
                //getting selected file
                File file = fileChooser.getSelectedFile();
                //get the path of selected file
                String filePath = file.getPath();
                try {
                    //initialized file reader
                    FileReader fileReader = new FileReader(filePath);
                    //initialized buffer reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String intermediate = "", output = "";
                    //set the output String to text area

                    while ((intermediate = bufferedReader.readLine()) != null)
                        output = output + (intermediate + "\n");
                    textArea.setText(output);
                    bufferedReader.close(); // Close the BufferedReader

                }
                catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if (actionEvent.getSource() == saveFile) {
            //initialize file picker
            JFileChooser fileChooser1 = new JFileChooser("c:");
            //get choose option from file chooser
            int chooseOption = fileChooser1.showSaveDialog(null);
            // check if we clicked on save button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser1.getSelectedFile().getAbsolutePath() + ".txt");
                try {
                    //initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //initialize buffer writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write content of text area to file
                    textArea.write(bufferedWriter);
                    //closing bufferWriter
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    // handling exceptions
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()== newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }

    public static void main(String[] args) {
        TextEditor texteditor = new TextEditor();
    }
}