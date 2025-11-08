import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class DataStreams
{
    static JPanel mainPnl;
    static JPanel titlePnl;
    static JLabel titleLbl;
    static Font titleFont;
    static JPanel fileSearchPnl;
    static JLabel fileNameLbl;
    static JTextField fileNameTF;
    static JLabel regExLbl;
    static JTextField regExTF;
    static JPanel fileDisplayPnl;
    static JLabel originalFileLbl;
    static JLabel filteredFileLbl;
    static JTextArea originalFileTA;
    static JTextArea filteredFileTA;
    static JScrollPane scroller1;
    static JScrollPane scroller2;
    static JPanel controlPnl;
    static JButton fileSelectBtn;
    static JButton searchFileBtn;
    static JButton quitBtn;
    static JFileChooser chooser;
    static File selectedFile;

    //Main can only launch the program, not actually hold the code!!!
    public static void main(String[] args)
    {
        DataStreams data = new DataStreams();
        data.generateFrame();
    }

    private void selectFile()
    {
//        try (Stream lines = Files.lines(user selects file path))
//        {
//
//        }
//        catch(FileNotFoundException e)
//        {
//            //joptionpane dialog
//            e.printStackTrace();
//        }
//        catch(IOException e)
//        {
//            //joptionpane dialog
//            e.printStackTrace();
//        }
    }

    private void generateFrame()
    {
        JFrame frame = new JFrame();
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridBagLayout());
        frame.add(mainPnl);
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.gridwidth = 1;
        gbc1.gridheight = 1;
        gbc1.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        gbc2.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        gbc3.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 0;
        gbc4.gridy = 3;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 3;
        gbc4.fill = GridBagConstraints.BOTH;

        createTitlePnl();
        createFileSearchPnl();
        createControlPnl();
        createFileDisplayPnl();

        mainPnl.add(titlePnl, gbc1);
        mainPnl.add(fileSearchPnl, gbc2);
        mainPnl.add(controlPnl, gbc3);
        mainPnl.add(fileDisplayPnl, gbc4);

        //This Toolkit is used to find the screen size of the computer running the GUI
        Toolkit kit = Toolkit.getDefaultToolkit();

        //This Dimension stores the screen size
        Dimension screenSize = kit.getScreenSize();

        //This int stores the height of the screen
        int screenHeight = screenSize.height;

        //This int stores the width of the screen
        int screenWidth = screenSize.width;

        frame.setSize(screenWidth * 3 / 4, screenHeight * 3 / 4);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Data Streams");
        frame.setVisible(true);
    }

    private void createTitlePnl()
    {
        titlePnl = new JPanel();
        titlePnl.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        titleLbl = new JLabel("Data Stream Filter");
        titleFont = new Font("Serif", Font.BOLD, 36);
        titleLbl.setFont(titleFont);
        titlePnl.add(titleLbl);
    }

    private void createFileSearchPnl()
    {
        fileSearchPnl = new JPanel();
        fileSearchPnl.setLayout(new GridBagLayout());
        fileSearchPnl.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.gridwidth = 1;
        gbc1.gridheight = 1;
        gbc1.fill = GridBagConstraints.NONE;
        gbc1.anchor = GridBagConstraints.EAST;
        gbc1.insets = new Insets(15, 15, 15, 15);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        gbc2.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 1;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        gbc3.fill = GridBagConstraints.NONE;
        gbc3.anchor = GridBagConstraints.EAST;
        gbc3.insets = new Insets(15, 15, 15, 15);
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 1;
        gbc4.gridy = 1;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 1;
        gbc4.fill = GridBagConstraints.BOTH;

        fileNameLbl = new JLabel("File Name:");
        fileNameTF = new JTextField(30);
        fileNameTF.setEditable(false);
        regExLbl = new JLabel("Search String:");
        regExTF = new JTextField(30);

        fileSearchPnl.add(fileNameLbl, gbc1);
        fileSearchPnl.add(fileNameTF, gbc2);
        fileSearchPnl.add(regExLbl, gbc3);
        fileSearchPnl.add(regExTF, gbc4);
    }

    private void createFileDisplayPnl()
    {
        fileDisplayPnl = new JPanel();
        fileDisplayPnl.setLayout(new GridBagLayout());
        fileDisplayPnl.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.gridwidth = 1;
        gbc1.gridheight = 1;
        gbc1.fill = GridBagConstraints.NONE;
        gbc1.anchor = GridBagConstraints.SOUTH;
        gbc1.insets = new Insets(15, 15, 15, 15);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 3;
        gbc2.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 1;
        gbc3.gridy = 0;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        gbc3.fill = GridBagConstraints.NONE;
        gbc3.anchor = GridBagConstraints.SOUTH;
        gbc3.insets = new Insets(15, 15, 15, 15);
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 1;
        gbc4.gridy = 1;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 3;
        gbc4.fill = GridBagConstraints.BOTH;

        originalFileLbl = new JLabel("Original File:");
        filteredFileLbl = new JLabel("Filtered File:");
        originalFileTA = new JTextArea(10, 50);
        filteredFileTA = new JTextArea(10, 50);
        originalFileTA.setEditable(false);
        filteredFileTA.setEditable(false);
        scroller1 = new JScrollPane(originalFileTA);
        scroller2 = new JScrollPane(filteredFileTA);

        fileDisplayPnl.add(originalFileLbl, gbc1);
        fileDisplayPnl.add(scroller1, gbc2);
        fileDisplayPnl.add(filteredFileLbl, gbc3);
        fileDisplayPnl.add(scroller2, gbc4);
    }

    private void createControlPnl()
    {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 3));
        controlPnl.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));

        fileSelectBtn = new JButton("Load File");
        searchFileBtn = new JButton("Search File");
        quitBtn = new JButton("Quit");

        controlPnl.add(fileSelectBtn);

        fileSelectBtn.addActionListener((ActionEvent ae) ->
        {

        });

        controlPnl.add(searchFileBtn);

        searchFileBtn.addActionListener((ActionEvent ae) -> {

        });

        controlPnl.add(quitBtn);

        quitBtn.addActionListener((ActionEvent ae) -> {
            //This int tracks whether the user confirmed or denied they wanted to quit the program
            int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit? You can press Re-run Program to reset the program.", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            //This algorithm determines whether to quit the program based on the user's input
            if(selection == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Quitting the program...");
                System.exit(0);
            } else
            {
                JOptionPane.showMessageDialog(null, "The program will remain open.");
            }
        });
    }
}