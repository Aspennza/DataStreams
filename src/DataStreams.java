import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
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

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();

        JFileChooser chooser = new JFileChooser();
        File selectedFile;

        mainPnl = new JPanel();

        try (Stream lines = Files.lines(user selects file path))
        {

        }
        catch(FileNotFoundException e)
        {
            //joptionpane dialog
            e.printStackTrace();
        }
        catch(IOException e)
        {
            //joptionpane dialog
            e.printStackTrace();
        }
    }

    private void createTitlePnl()
    {
        titlePnl = new JPanel();
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
}