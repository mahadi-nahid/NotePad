package com.mahadihasan.frame;

import java.awt.Font;
import java.awt.event.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.*;

/**
 *
 * @author MAHADI HASAN NAHID
 */
public class NotepadFrame extends JFrame {

    static public JTextArea textArea;
    UndoManager undoManager = new UndoManager();
    String textValue = "";
    String directory = "Untitled";
    boolean addextention;

    public NotepadFrame(String filePath) {

        super("Notepad-(Nahid)");

        //----------------------All Menu Items----------------------------

        //-------------File Menu--------------------------

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');


        // New File.....
        JMenuItem newFile = new JMenuItem("New");
        newFile.setMnemonic('n');

        fileMenu.add(newFile);

        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                InputEvent.CTRL_MASK));
        newFile.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        textArea.setText("");
                        directory = "Untitled";

                        NotepadFrame.super.setTitle(directory + "-Notepad");

                    }
                });

        // Open File.....

        JMenuItem openFile = new JMenuItem("Open");
        openFile.setMnemonic('o');

        fileMenu.add(openFile);

        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                InputEvent.CTRL_MASK));
        openFile.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent event) {
                        NotepadFrame.this.openAction(event);
                    }
                });


        // Save File....
        JMenuItem save = new JMenuItem("Save");
        save.setMnemonic('s');
        fileMenu.add(save);

        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_MASK));
        save.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent event) {

                        NotepadFrame.this.saveAction();
                        
                    }
                });


        // Save As File....
        JMenuItem saveAs = new JMenuItem("Save As");
        saveAs.setMnemonic('S');
        fileMenu.add(saveAs);


        saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                InputEvent.ALT_MASK));

        saveAs.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent event) {

                        NotepadFrame.this.saveAsAction(event);
                    }
                });


        // Exit Menu...
        JMenuItem exitMenu = new JMenuItem("Quit");
        exitMenu.setMnemonic('Q');
        fileMenu.add(exitMenu);
        exitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                InputEvent.CTRL_MASK));
        exitMenu.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        System.exit(0);
                    }
                });

        //-----------------End of File Menu-----------------





        //----------------Menu Bar------------------------
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);


        //-------------------Edit Menu------------------


        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic('E');

        // Undo...
        JMenuItem undo = new JMenuItem("Undo");
        undo.setMnemonic('u');
        editMenu.add(undo);
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                InputEvent.CTRL_MASK));

        undo.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        try {

                            if (undoManager.canUndo()) {
                                undoManager.undo();
                            }
                        } catch (CannotUndoException cannotUndoException) {

                            cannotUndoException.printStackTrace();
                        }
                    }
                });


        // Redo.....
        JMenuItem redo = new JMenuItem("Redo");
        redo.setMnemonic('r');
        editMenu.add(redo);
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
                InputEvent.CTRL_MASK));
        redo.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        try {

                            if (undoManager.canRedo()) {
                                undoManager.redo();
                            }

                        } catch (CannotRedoException cannotRedoException) {
                            cannotRedoException.printStackTrace();
                        }
                    }
                });

        //-----------------End Of Edit Menu------------

        menuBar.add(editMenu);


        //-------------------Search Menu---------------

        JMenu searchMenu = new JMenu("Search");
        searchMenu.setMnemonic('S');

        // Find Menu....
        JMenuItem findMenu = new JMenuItem("Find");
        findMenu.setMnemonic('f');
        searchMenu.add(findMenu);

        findMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                InputEvent.CTRL_MASK));

        findMenu.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        // TO Do......
                    }
                });

        //--------------End of Search Menu-------------------

        menuBar.add(searchMenu);

        //---------------------Help Menu------------------

        JMenu helpMenu = new JMenu("Info");
        helpMenu.setMnemonic('I');

        JMenuItem help = new JMenuItem("Help");
        help.setMnemonic('H');
        helpMenu.add(help);
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
                InputEvent.ALT_MASK));
        help.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        JOptionPane.showMessageDialog(NotepadFrame.this,
                                "-----Key Board Short Cut---\n\n" +
                                "New File-------Ctrl-N\n" +
                                "Open----------Ctrl-O\n" +
                                "Save-----------Ctrl-S\n" +
                                "Save As--------Alt-P\n" +
                                "Quit-----------Ctrl-Q\n\n" +
                                "Undo----------Ctrl-Z\n" +
                                "Redo-----------Ctrl-Y\n\n" +
                                "Find------------Ctrl-F\n\n" +
                                "Help-----------Alt-H\n" +
                                "About----------Alt-A\n" );

                        // TO DO......
                    }
                });


        JMenuItem about = new JMenuItem("About");
        about.setMnemonic('a');
        helpMenu.add(about);

        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                InputEvent.ALT_MASK));
        about.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        JOptionPane.showMessageDialog(NotepadFrame.this,
                                "Notpad-Nahid\n v-2.7182"
                                + "MD MAHADI HASAN NAHID\n"
                                + "CSE, 2010331044\n"
                                + "SUST, Bangladesh\n"
                                + "Contact : 01738150127\n\n"
                                + "nahidsust44@gmail.com\n"
                                + "nahid-cse@outlook.com\n");

                    }
                });

        //------------------End of Help Menu-----------------------


        menuBar.add(helpMenu);

        //---------------------------End of All Menus----------




        //-----------------------Adding Text Area----------------------


        Box box = Box.createHorizontalBox();

        textArea = new JTextArea(textValue, 15, 15);
        textArea.setFont(new Font("Tahoma", 0, 20));
        textArea.setTabSize(4);

        box.add(new JScrollPane(textArea));

        add(box);
        
        //-----------------End Text Area-------------------

        super.addWindowListener(
                new WindowListener() {

                    @Override
                    public void windowOpened(WindowEvent we) {
                    }

                    @Override
                    public void windowClosing(WindowEvent we) {

                        if (directory != null && directory.equals("Untitled")) {
                            confirmation();
                        } //else if()
                        else {
                            confirmation();
                        }
                    }

                    private void confirmation() {

                        if (directory.equals("Untitled") && textArea.getText().equals("")) {
                            System.exit(0);
                        }

                        int option = JOptionPane.showConfirmDialog(NotepadFrame.this, "Do You Want to Save Change To \n" + directory + " ?");

                        if (option == JOptionPane.YES_OPTION) {
                            saveAction();
                            System.exit(0);
                        } else if (option == JOptionPane.NO_OPTION) {
                            System.exit(0);
                        }
                    }

                    @Override
                    public void windowClosed(WindowEvent we) {
                    }

                    @Override
                    public void windowIconified(WindowEvent we) {
                    }

                    @Override
                    public void windowDeiconified(WindowEvent we) {
                    }

                    @Override
                    public void windowActivated(WindowEvent we) {
                    }

                    @Override
                    public void windowDeactivated(WindowEvent we) {
                    }
                });



        //----------------------Undo-Redo--------------------------
        
        undoManager  = new UndoManager();
        Document doc = textArea.getDocument();

        doc.addUndoableEditListener(
                new UndoableEditListener() {

                    @Override
                    public void undoableEditHappened(UndoableEditEvent e) {
                        //System.out.println("Add edit");
                        undoManager.addEdit(e.getEdit());
                    }
                });
        
        //-----------------End-Undo-Redo--------------------

    }

    protected void openAction(ActionEvent event) {

        JFileChooser fileChooser = new JFileChooser();

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION
                && fileChooser.getSelectedFile().canRead()) {

            String filePath = fileChooser.getSelectedFile().toString();

            if (filePath != null) {

                String fileText = readTheFile(filePath);

                textArea.setText(fileText);

                super.setTitle(filePath + "-Notepad");

                directory = filePath;

                undoManager.discardAllEdits();

            } else {

                JOptionPane.showMessageDialog(NotepadFrame.this,
                        "File Can Not Read!!", "Warning!!",
                        JOptionPane.ERROR_MESSAGE);
            }

        }


    }

    private String readTheFile(String filePath) {

        String text = "";

        Scanner input = null;

        try {
            input = new Scanner(new File(filePath), "UTF-8");
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        while (input.hasNextLine()) {
            text = text.concat(input.nextLine());
            if (input.hasNextLine()) {
                text = text.concat("\n");
            }
        }

        if (input != null) {
            input.close();
        }

        return text;

    }

    protected void saveAction() {

        if (directory == null || directory.equals("Untitled")) {
            ActionEvent test = null;
            saveAsAction(test);
        } else {
            addextention = false;
            save(directory);
        }

    }

    // Save Method For Saving File.....
    private void save(String filePath) {

        if (filePath != null) {
            if (addextention) {
                filePath = filePath + ".txt";
            }
            directory = filePath;

            Formatter out = null;

            try {
                try {
                    out = new Formatter(filePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            out.format("%s", textArea.getText());

            if (out != null) {
                out.close();
            }
            super.setTitle(filePath + " - NotePad");
        }

    }

    //For Save As Action....
    protected void saveAsAction(ActionEvent event) {


        JFileChooser fileChooser = new JFileChooser();

        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {

            String path = fileChooser.getSelectedFile().toString();

            addextention = true;

            //---------------------

            save(path);

            //---------------------------


        } else {
            JOptionPane.showMessageDialog(NotepadFrame.this,
                    "File Can't Save", "Warning",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
