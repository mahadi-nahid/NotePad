
package nodepad;

/**
 *
 * @author MdMahadiHasan
 */

import com.mahadihasan.frame.NotepadFrame;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

/**
 *
 * @author MAHADI HASAN NAHID
 */
public class Notepad extends JFrame {

    private JButton systemButton = new JButton("SYSTEM");
    private JButton crossButton = new JButton("CROSS");
    private JLabel lookAndFeel = new JLabel("CHOSE LOOK AND FEEL");
    private JButton quitButton = new JButton("QUIT");
    NotepadFrame notepad;
    private Font font;
    
    public Notepad() {


        super("Notepad Launcher");
        setLayout(new FlowLayout());

        font = new Font("Tahoma", Font.BOLD+Font.ITALIC, 15);
        lookAndFeel.setFont(font);
        
        systemButton.setToolTipText("System Look And Feel");
        crossButton.setToolTipText("Croos Look And Feel");
        quitButton.setToolTipText("Quit ??");
        
        systemButton.setBackground(Color.GREEN);
        crossButton.setBackground(Color.CYAN);
        quitButton.setBackground(Color.RED);
        
        add(lookAndFeel);
        add(systemButton);
        add(crossButton);
        add(quitButton);

        notepad = new NotepadFrame("Untitled");

        systemButton.addActionListener(
                
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        notepad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        notepad.setSize(680, 500);

                        notepad.setLocation(350, 100);


                        try {
                            UIManager.setLookAndFeel(
                                    UIManager.getSystemLookAndFeelClassName());
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        notepad.setVisible(true);
                        
                    }
                });

        crossButton.addActionListener(
                
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        notepad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        notepad.setSize(680, 500);

                        notepad.setLocation(350, 100);

                        try {
                            UIManager.setLookAndFeel(
                                    UIManager.getCrossPlatformLookAndFeelClassName());
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        
                         notepad.setVisible(true);
                    }
                });
        quitButton.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        setLocation(140, 100);
        setSize(200, 120);
        setVisible(true);
        setResizable(false);

        
        
        /*
        if (args.length > 0) {
        notepad = new NotepadFrame(args[0]);
        JOptionPane.showMessageDialog(null, args[0]);
        } else {
        notepad = new NotepadFrame("Untitled");
        }
         */

    }

    public static void main(String[] args) {

        Notepad start = new Notepad();
        start.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }
}
