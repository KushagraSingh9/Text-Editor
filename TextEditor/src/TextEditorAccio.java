import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditorAccio implements ActionListener {
    //declaring properties of TextEditor
    JFrame frame; //for frame
    JMenuBar menuBar; //for menubar

    //initialise file nd edit in menubar
    JMenu file,edit;

    //adding menuitems
    //file menu items
    JMenuItem newFile,openFile,saveFile;

    //edit menu items
    JMenuItem cut,copy,paste,selectAll,close;

    //textArea
    JTextArea textArea;

    TextEditorAccio(){
        //initialize a frame
        frame=new JFrame();

        //setting menubar to frame
        menuBar=new JMenuBar();

        //initialise textArea
        textArea=new JTextArea();

        //initialise menus
        file=new JMenu("File");
        edit=new JMenu("Edit");

        //before adding file n edit to menubar first totally initialise them
        //file items
        newFile=new JMenuItem("New Window");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save File");

        //add action listener
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);


        //add menu items to file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initialise edit menu item
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("SelectAll");
        close=new JMenuItem("Close");

        //adding actionListener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //add to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //add to menus to menubar
        menuBar.add(file);
        menuBar.add(edit);


        frame.setJMenuBar(menuBar);
        //create a content panel
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        //add textArea to panel
        panel.add(textArea,BorderLayout.CENTER);

        //create a scroll pane
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //add scrollpane to panel
        panel.add(scrollPane);

        //add panel to frame
        frame.add(panel);



        //add textArea to frame
//        frame.add(textArea);

        //set frame dimensions
        frame.setBounds(0,0,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true); //to make visible
        frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
    if(actionEvent.getSource()==cut){
        //perform cut operation
        textArea.cut();
    }
    if(actionEvent.getSource()==copy){
        //perform copy operation
        textArea.copy();
    }
    if(actionEvent.getSource()==paste){
            //perform paste operation
        textArea.paste();
    }
    if(actionEvent.getSource()==selectAll){
        //perform selectAll operation
        textArea.selectAll();
    }
        if(actionEvent.getSource()==close){
            //perform close operation
            System.exit(0);
        }

        if(actionEvent.getSource()==openFile){
            //perform selectAll operation
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption=fileChooser.showOpenDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file=fileChooser.getSelectedFile();
                String filePath=file.getPath();
                try{
                    FileReader fileReader=new FileReader(filePath);
                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String intermediate="",output="";
                    while((intermediate=bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //set the outpit string to textArea
                    textArea.setText(output);
                }

                catch (FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile){
            //initialise file picker
            JFileChooser jFileChooser=new JFileChooser("C");
            //get chooseoption from filechooser
            int chooseOption=jFileChooser.showSaveDialog(null);
            //show save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //initialise file writer
                  FileWriter fileWriter=new FileWriter(file);
                  //initialise buffered writer
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    //write contents of textArea to a file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch(IOException ioException){
                      ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile){
            TextEditorAccio textEditor=new TextEditorAccio();
        }
    }
    public static void main(String[] args) {
        TextEditorAccio textEditor=new TextEditorAccio();
    }
}