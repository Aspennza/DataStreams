import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Allows the creation of objects for reading and filtering files using Streams.
 * Contains a GUI, methods for reading and filtering files, and a method for
 * resetting the program.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class DataStreams
{
    //A JPanel containing all other panels in the GUI
    JPanel mainPnl;

    //A JPanel containing a title for the JFrame
    JPanel titlePnl;

    //A JLabel acting as the visible title for the JFrame
    JLabel titleLbl;

    //The Font applied to the titleLbl
    Font titleFont;

    //A JPanel containing the name of the file being read and the string users want to search on
    JPanel fileSearchPnl;

    //A JLabel for the fileNameTF field
    JLabel fileNameLbl;

    //A JTextField displaying the name of the file being read
    JTextField fileNameTF;

    //A JLabel for the searchStringTF
    JLabel searchStringLbl;

    //A JTextField where users can enter their desired search string
    JTextField searchStringTF;

    //A JPanel where the original text of the file and the filtered text of the file will be displayed
    JPanel fileDisplayPnl;

    //A JLabel for the originalFileTA
    JLabel originalFileLbl;

    //A JLabel for the filteredFileTA
    JLabel filteredFileLbl;

    //A JTextArea for displaying the original text of the file being read
    JTextArea originalFileTA;

    //A JTextArea for displaying the filtered text of the file being read
    JTextArea filteredFileTA;

    //A JScrollPane for the originalFileTA
    JScrollPane scroller1;

    //A JScrollPane for the filteredFileTA
    JScrollPane scroller2;

    //A JPanel containing buttons for selecting a file, searching the file, and quitting the program
    JPanel controlPnl;

    //A JButton for selecting the file to read
    JButton fileSelectBtn;

    //A JButton for searching the file based on the user's search string
    JButton searchFileBtn;

    //A JButton for quitting the program
    JButton quitBtn;

    //A JFileChooser for selecting a file
    JFileChooser chooser;

    //A File containing the file selected by the user
    File selectedFile;

    //A List<String> for storing the streamed data from the file
    List<String> originalStreamResult;

    //A boolean representing whether the user has already loaded a file
    boolean hasSelectedFile = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //This DataStreams object creates an instance of the DataStreams class
        DataStreams data = new DataStreams();
        data.generateFrame();
    }

    /**
     * This constructor initializes the JFileChooser
     */
    public DataStreams()
    {
        chooser = new JFileChooser();
    }

    /**
     * This method prompts the user to select a file, displays its name, reads the file data using a Stream, and displays the file text in the originalFileTA
     */
    public void selectFile()
    {
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);

        //This algorithm prompts the user to select a file
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = chooser.getSelectedFile();
            Path file = selectedFile.toPath();
            String pathString = file.toString();

            fileNameTF.setText(selectedFile.getName());
            hasSelectedFile = true;
            searchFileBtn.setEnabled(true);
            originalFileTA.setText("");
            filteredFileTA.setText("");

            //This algorithm reads the lines of the file using a Stream, appends them to the originalFileTA, and checks for exceptions
            try (Stream<String> lines = Files.lines(Paths.get(pathString)))
            {
                originalStreamResult = lines
                        .collect(Collectors.toList());
                for(String line : originalStreamResult) {
                    originalFileTA.append(line + "\n");
                }

                JOptionPane.showMessageDialog(null, "Now, you can filter your file for specific words using the Search String textbox and Search File button.");
            }
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(null, "An error occurred.");
                e.printStackTrace();
            }
        } else
        {
            JOptionPane.showMessageDialog(null, "No file was selected.");
        }
    }

    /**
     * This method filters the selected file based on the user's normalized search string, then appends the result to the filteredFileTA
     */
    private void filterFile()
    {
        String searchString = searchStringTF.getText().toLowerCase();
        List<String> filteredStreamResult = new LinkedList<>();

        filteredStreamResult = filterLines(originalStreamResult, searchString);

        int lineNum = 1;
        filteredFileTA.setText("");
            for (String line : filteredStreamResult)
            {
                filteredFileTA.append(lineNum + ")       " + line + "\n");
                lineNum++;
            }
    }

    /**
     * This method accepts an existing List<String>, streams it, and normalizes and filters it based on an input search String
     * @param lines the List of Strings to be streamed and filtered
     * @param searchString the String by which to filter the data
     * @return a List of Strings filtered by the chosen search string
     */
    public List<String> filterLines(List<String> lines, String searchString)
    {
        return lines.stream()
                .map(String::trim)
                .filter(s -> s.toLowerCase().contains(searchString))
                .collect(Collectors.toList());
    }

    /**
     * This method initializes all the panels in the GUI and adds them to a JFrame to be displayed; also sets the size, visibility, etc. of the JFrame
     */
    private void generateFrame()
    {
        //A JFrame for displaying the GUI
        JFrame frame = new JFrame();
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridBagLayout());
        frame.add(mainPnl);

        //GridBagConstraints for the titlePnl
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.gridwidth = 1;
        gbc1.gridheight = 1;
        gbc1.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the fileSearchPnl
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        gbc2.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the controlPnl
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        gbc3.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the FileDisplayPnl
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
        JOptionPane.showMessageDialog(null, "Welcome to the Data Stream Filter! First, select a file using the Load File button.");
    }

    /**
     * This method clears the selected file, all relevant textboxes, and program state information
     */
    public void clearProgram()
    {
        selectedFile = null;

        if(originalStreamResult != null) {
            originalStreamResult.clear();
        }
        fileNameTF.setText("");
        searchStringTF.setText("");
        originalFileTA.setText("");
        filteredFileTA.setText("");
        searchFileBtn.setEnabled(false);
    }

    /**
     * This method establishes the titlePnl, titleLbl, and titleFont
     */
    private void createTitlePnl()
    {
        titlePnl = new JPanel();
        titlePnl.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        titleLbl = new JLabel("Data Stream Filter");
        titleFont = new Font("Serif", Font.BOLD, 36);
        titleLbl.setFont(titleFont);
        titlePnl.add(titleLbl);
    }

    /**
     * This method establishes the fileSearchPnl, its layout, and its text fields and JLabels
     */
    private void createFileSearchPnl()
    {
        fileSearchPnl = new JPanel();
        fileSearchPnl.setLayout(new GridBagLayout());
        fileSearchPnl.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));

        //GridBagConstraints for the fileNameLbl
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.gridwidth = 1;
        gbc1.gridheight = 1;
        gbc1.fill = GridBagConstraints.NONE;
        gbc1.anchor = GridBagConstraints.EAST;
        gbc1.insets = new Insets(15, 15, 15, 15);

        //GridBagConstraints for the fileNameTF
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        gbc2.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the searchStringLbl
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 1;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        gbc3.fill = GridBagConstraints.NONE;
        gbc3.anchor = GridBagConstraints.EAST;
        gbc3.insets = new Insets(15, 15, 15, 15);

        //GridBagConstraints for the searchStringTF
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 1;
        gbc4.gridy = 1;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 1;
        gbc4.fill = GridBagConstraints.BOTH;

        fileNameLbl = new JLabel("File Name:");
        fileNameTF = new JTextField(30);
        fileNameTF.setEditable(false);
        searchStringLbl = new JLabel("Search String:");
        searchStringTF = new JTextField(30);

        fileSearchPnl.add(fileNameLbl, gbc1);
        fileSearchPnl.add(fileNameTF, gbc2);
        fileSearchPnl.add(searchStringLbl, gbc3);
        fileSearchPnl.add(searchStringTF, gbc4);
    }

    /**
     * This method establishes the fileDisplayPnl, its layout, and its JLabels and JTextAreas
     */
    private void createFileDisplayPnl()
    {
        fileDisplayPnl = new JPanel();
        fileDisplayPnl.setLayout(new GridBagLayout());
        fileDisplayPnl.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));

        //GridBagConstraints for the originalFileLbl
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.gridwidth = 1;
        gbc1.gridheight = 1;
        gbc1.fill = GridBagConstraints.NONE;
        gbc1.anchor = GridBagConstraints.SOUTH;
        gbc1.insets = new Insets(15, 15, 15, 15);

        //GridBagConstraints for scroller1
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 3;
        gbc2.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the filteredFileLbl
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 1;
        gbc3.gridy = 0;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        gbc3.fill = GridBagConstraints.NONE;
        gbc3.anchor = GridBagConstraints.SOUTH;
        gbc3.insets = new Insets(15, 15, 15, 15);

        //GridBagConstraints for scroller2
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 1;
        gbc4.gridy = 1;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 3;
        gbc4.fill = GridBagConstraints.BOTH;

        originalFileLbl = new JLabel("Original File:");
        filteredFileLbl = new JLabel("Filtered File:");
        originalFileTA = new JTextArea(15, 50);
        filteredFileTA = new JTextArea(15, 50);
        originalFileTA.setEditable(false);
        filteredFileTA.setEditable(false);
        scroller1 = new JScrollPane(originalFileTA);
        scroller2 = new JScrollPane(filteredFileTA);

        fileDisplayPnl.add(originalFileLbl, gbc1);
        fileDisplayPnl.add(scroller1, gbc2);
        fileDisplayPnl.add(filteredFileLbl, gbc3);
        fileDisplayPnl.add(scroller2, gbc4);
    }

    /**
     * This method establishes the controlPnl, its layout, and the JButtons inside it
     */
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
            //This algorithm checks if the user has loaded a file before
            if(hasSelectedFile) {
                //This int tracks whether the user confirmed or denied they want to load a new file
                int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to load a new file?", "Load a File", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                //This algorithm determines whether to load a new file based on the user's input
                if(selection == JOptionPane.YES_OPTION) {
                    clearProgram();
                    selectFile();
                } else
                {
                    JOptionPane.showMessageDialog(null, "Your existing file will remain open.");
                }
            } else {
                selectFile();
            }
        });

        controlPnl.add(searchFileBtn);
        searchFileBtn.setEnabled(false);

        searchFileBtn.addActionListener((ActionEvent ae) ->
        {
            //This algorithm checks that the searchStringTF is not empty before filtering the file
            if(!searchStringTF.getText().trim().isEmpty())
            {
                filterFile();
            } else
            {
                JOptionPane.showMessageDialog(null, "You must enter a search string in the Search String text box before filtering.");
            }

        });

        controlPnl.add(quitBtn);

        quitBtn.addActionListener((ActionEvent ae) -> {
            //This int tracks whether the user confirmed or denied they wanted to quit the program
            int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

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

    public JPanel getMainPnl() {
        return mainPnl;
    }

    public JPanel getTitlePnl() {
        return titlePnl;
    }

    public JLabel getTitleLbl() {
        return titleLbl;
    }

    public Font getTitleFont() {
        return titleFont;
    }

    public JPanel getFileSearchPnl() {
        return fileSearchPnl;
    }

    public JLabel getFileNameLbl() {
        return fileNameLbl;
    }

    public JTextField getFileNameTF() {
        return fileNameTF;
    }

    public JLabel getSearchStringLbl() {
        return searchStringLbl;
    }

    public JTextField getSearchStringTF() {
        return searchStringTF;
    }

    public JPanel getFileDisplayPnl() {
        return fileDisplayPnl;
    }

    public JLabel getOriginalFileLbl() {
        return originalFileLbl;
    }

    public JLabel getFilteredFileLbl() {
        return filteredFileLbl;
    }

    public JTextArea getOriginalFileTA() {
        return originalFileTA;
    }

    public JTextArea getFilteredFileTA() {
        return filteredFileTA;
    }

    public JScrollPane getScroller1() {
        return scroller1;
    }

    public JScrollPane getScroller2() {
        return scroller2;
    }

    public JPanel getControlPnl() {
        return controlPnl;
    }

    public JButton getFileSelectBtn() {
        return fileSelectBtn;
    }

    public JButton getSearchFileBtn() {
        return searchFileBtn;
    }

    public JButton getQuitBtn() {
        return quitBtn;
    }

    public JFileChooser getChooser() {
        return chooser;
    }

    public boolean hasSelectedFile() {
        return hasSelectedFile;
    }

    public List<String> getOriginalStreamResult() {
        return originalStreamResult;
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public void setSearchStringTF(JTextField searchStringTF) {
        this.searchStringTF = searchStringTF;
    }

    public void setOriginalStreamResult(List<String> originalStreamResult) {
        this.originalStreamResult = originalStreamResult;
    }

    public void setFilteredFileTA(JTextArea filteredFileTA) {
        this.filteredFileTA = filteredFileTA;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    public void setFileNameTF(JTextField fileNameTF) {
        this.fileNameTF = fileNameTF;
    }

    public void setOriginalFileTA(JTextArea originalFileTA) {
        this.originalFileTA = originalFileTA;
    }

    public void setSearchFileBtn(JButton searchFileBtn) {
        this.searchFileBtn = searchFileBtn;
    }
}