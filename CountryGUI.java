
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.IOException;
import java.lang.Object;

/*************************************************************
 * GUI for a Country Database class
 * 
 * @Ana Posada
 * @version March 2018
 ************************************************************/
public class CountryGUI extends JFrame implements ActionListener{
    // Define on object of CountryDatabas
    private CountryDatabase world;

    /** JButtons */
    private JButton smallestAreaBtn;
    private JButton highestGdpBtn;
    private JButton topTenGdpBtn;
    private JButton countriesInContinentBtn;
    private JButton populationBtn;
    private JButton capitalBtn;
    private JButton detailsBtn;
    // **FIX ME: Define the rest of buttons

    /** JTextFields */
    private JTextField continentTextField;
    private JTextField populationTextField;
    private JTextField countryNameTextField;
    // **FIX ME: Define text fields for country name and population

    /** Results text area */
    private JTextArea resultsArea;

    /** menu items */
    private JMenuBar menus;
    private JMenu fileMenu;
    private JMenuItem listAllItem;
    private JMenuItem quitItem;
    private JMenuItem openItem;
    private JMenuItem countItem;

    /*****************************************************************
     * Main Method
     ****************************************************************/ 
    public static void main(String args[]){
        CountryGUI gui = new CountryGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Countries of the world");
        gui.pack();
        gui.setVisible(true);

    }

    /*****************************************************************
     * constructor 
     ****************************************************************/    
    public CountryGUI(){
        world = new CountryDatabase();

        // setting up the GUI
        setupGui();

        // hide details of creating menus
        setupMenus();
    }

    /*****************************************************************
     * sets up the layout and the GUI
     ****************************************************************/
    private void setupGui () {
        // set the layout to GridBag       
        setLayout(new GridBagLayout());
        GridBagConstraints loc = new GridBagConstraints();

        // create results area to span one column and 10 rows
        resultsArea = new JTextArea(20,30);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        loc.gridx = 0;
        loc.gridy = 1;
        loc.gridheight = 10;  
        loc.insets.left = 20;
        loc.insets.right = 20;
        loc.insets.bottom = 20;
        add(scrollPane, loc);  

        // create Results label
        loc = new GridBagConstraints();
        loc.gridx = 0;
        loc.gridy = 0;
        loc.insets.bottom = 20;
        loc.insets.top = 20;
        add(new JLabel("Results"), loc);

        // create Searches label
        loc.gridx = 1;
        loc.gridwidth = 2;
        add(new JLabel("Searches"), loc);     

        // instantiate the JButtons 
        // we are providing a few - complete the rest
        smallestAreaBtn = new JButton ("Smallest Area");
        highestGdpBtn = new JButton ("Highest GDP");
        topTenGdpBtn = new JButton ("Top ten GDP");
        countriesInContinentBtn = new JButton ("Countries in Continent");
        populationBtn = new JButton ("Population");
        capitalBtn = new JButton ("Capital");
        detailsBtn = new JButton ("Details");
        // **FIX ME: complete the rest of the 5 JButton

        // instantiate the JTextFields
        continentTextField = new JTextField (20);
        populationTextField = new JTextField (20);
        countryNameTextField = new JTextField (20);
        // **FIX ME: complete the JTextFields for countryName and Population

        // adding labels and buttons
        loc = new GridBagConstraints();

        loc.anchor = GridBagConstraints.LINE_END;
        loc.insets = new Insets(5,5,5,5);
        loc.gridx = 1;
        loc.gridy = 1;
        add(new JLabel ("Continent"), loc);
        loc.gridx = 2;
        loc.anchor = GridBagConstraints.LINE_START;
        add(continentTextField, loc);

        loc.gridy++;
        add(smallestAreaBtn, loc);

        // **FIX ME: complete adding the rest of the JButtons
        // associated with the continent search options
        loc.gridy++;
        add(highestGdpBtn, loc);

        loc.gridy++;
        add(topTenGdpBtn, loc);

        loc.gridy++;
        add(countriesInContinentBtn, loc);

        //adding the JLabel for population 
        loc.gridx = 1;
        loc.gridy++;
        loc.insets = new Insets(30,5,5,5);
        loc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel ("Population"), loc);

        //adding the JTextField for the population
        loc.gridx = 2;
        add(populationTextField, loc);

        //adding the JButton for the population
        loc.gridy++;
        loc.insets = new Insets(5,5,5,5);
        loc.anchor = GridBagConstraints.LINE_START;
        add(populationBtn, loc);

        // **FIX ME: add the JLabel, JTextField and JButtons for the country

        //adding the JLabel for Country 
        loc.gridx = 1;
        loc.gridy++;
        loc.insets = new Insets(30,5,5,5);
        loc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel ("Country"), loc);

        //adding the JTextField for the country
        loc.gridx = 2;
        add(countryNameTextField, loc);

        //adding the JButton for the country
        loc.gridy++;
        loc.insets = new Insets(5,5,5,5);
        loc.anchor = GridBagConstraints.LINE_START;
        add(capitalBtn, loc);

        loc.gridy++;
        add(detailsBtn, loc);

        // registering the listeners for the buttons
        highestGdpBtn.addActionListener(this); 
        smallestAreaBtn.addActionListener(this);
        // **FIX ME: register listeners for the other buttons
        topTenGdpBtn.addActionListener(this);
        countriesInContinentBtn.addActionListener(this);
        populationBtn.addActionListener(this);
        capitalBtn.addActionListener(this);
        detailsBtn.addActionListener(this);

    }

    /*****************************************************************
     * This method is called when any button is clicked.  The proper
     * internal method is called as needed.
     * 
     * @param e the event that was fired
     ****************************************************************/       
    public void actionPerformed(ActionEvent e){

        // extract the button that was clicked
        JComponent buttonPressed = (JComponent) e.getSource();

        // loads the file with the countries of the world    
        if (buttonPressed == openItem){
            openFile();
        }

        // quit item 
        if (buttonPressed == quitItem){ 
            System.exit(1);
        }

        if (world.countAllCountries() == 0){
            JOptionPane.showMessageDialog(this, "Forget to load the file?");
        }else if (buttonPressed == countItem){
            displayCounts();

        }else if (buttonPressed == listAllItem){
            displayCountries (world.getAllCountries ()); 

            // **FIX ME: write an else if for the rest of JButtons pressed

        }else if(buttonPressed == smallestAreaBtn){
            displaySmallestArea();

        }else if(buttonPressed == highestGdpBtn){
            displayHighestGdp();

        }else if(buttonPressed == topTenGdpBtn){
            displayTopTen();

        }else if(buttonPressed == countriesInContinentBtn){
            displayCountriesInContinent();

        }else if(buttonPressed == populationBtn){
            displayPopulation();

        }else if(buttonPressed == capitalBtn){
            displaySearchForCapital();

        }else if(buttonPressed == detailsBtn){
            displaySearchForCountry();

        }
    }

    /*****************************************************************
     * displays the ArrayList passed as input parameter
     * @param - ArrayList <Country>
     ****************************************************************/ 
    private void displayCountries(ArrayList <Country> list) {
        // ?? FIX ME: complete the code for this method. See example
        // on project description on page 8
        resultsArea.setText("");

        for(Country c : list){
            resultsArea.append("\n" + c.toString());
        }
        resultsArea.append("\n" + list.size() + " items listed.");

    }

    /*****************************************************************
     * display country with smallest area
     ****************************************************************/ 
    private void displaySmallestArea (){
        // FIX ME: complete the code for this method.
        if (continentTextField.getText().length() > 0){
            if(checkContinent()){
            resultsArea.setText(world.smallestArea (continentTextField.getText()).toString());
            }else{
            resultsArea.setText("Continent not found");
        }
        }else{
            JOptionPane.showMessageDialog(this, "Enter a continent");
        }

    }

    /*****************************************************************
     * display country with highest GDP
     ****************************************************************/ 
    private void displayHighestGdp ()  {
        // FIX ME: complete the code for this method.
        if (continentTextField.getText().length() > 0){
            if(checkContinent()){
            resultsArea.setText(world.highestGdp(continentTextField.getText()).toString());
            }else{
            resultsArea.setText("Continent not found");
        }
        }else{
            JOptionPane.showMessageDialog(this, "Enter a continent");
        }

    }

    /*****************************************************************
     * display the countries in a particular continent
     ****************************************************************/ 
    private void displayCountriesInContinent() {
        if (continentTextField.getText().length() > 0){
            if(checkContinent()){
            displayCountries (world.searchCountriesInContinent (continentTextField.getText()));
        }else{
            resultsArea.setText("Continent not found");
        }
        }else{
            JOptionPane.showMessageDialog(this, "Enter a continent");
        }

    }

    /*****************************************************************
     * display top ten GDP countries
     ****************************************************************/ 
    private void displayTopTen () {
        // FIX ME: complete the code for this method.
        if (continentTextField.getText().length() > 0){
            
            if(checkContinent()){
                displayCountries(world.topTenGdpCountries(continentTextField.getText().toString()));
            }else{
                resultsArea.setText("Continent not found");
            }
            
        }else{
            JOptionPane.showMessageDialog(this, "Enter a continent");
        }

    }

    /*****************************************************************
     * display the capital for a particular country
     ****************************************************************/ 
    private void displaySearchForCapital ()  {
        // FIX ME: complete the code for this method.
        if (countryNameTextField.getText().length() > 0){
            if(checkCountry()){
                resultsArea.setText("The capital of " + countryNameTextField.getText().toString().toUpperCase() + " is: " +
                    world.searchForCapital(countryNameTextField.getText()).toString());
            }else{
                resultsArea.setText("Country not found");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Enter a country");
        }

    }

    /*****************************************************************
     * display the facts about a country
     ****************************************************************/ 
    private void displaySearchForCountry ()  {
        DecimalFormat fmt = new DecimalFormat ("###,###,###,###");
        if (countryNameTextField.getText().length() > 0){
            Country country = world.findCountryDetails (countryNameTextField.getText());
            if (country != null){
                resultsArea.setText ("\nCountry Name:\t" + country.getCountry() + 
                    "\nContinent:\t\t" + country.getContinent() +
                    "\nCapital:\t\t" + country.getCapital() +
                    "\nArea in sq km:\t\t" + fmt.format(country.getArea()) +
                    "\nPopulation:\t\t" + fmt.format (country.getPopulation() / 1000000) + " million" +
                    "\nGDP:\t\t" + fmt.format (country.getGDP() / 1000000000) + " billion" +
                    "\nPerCapita GDP:\t" + fmt.format (country.getGDP() / country.getPopulation()) );
            }
            else 
                resultsArea.setText ("Country not found");
        }
        else
            JOptionPane.showMessageDialog(this, "Enter a country");
    }

    /*****************************************************************
     * display counts - total number of countries
     ****************************************************************/ 
    private void displayCounts () {
        // FIX ME: complete the code for this method.
        resultsArea.setText("Total Countries: " + world.countAllCountries());

    }

    /*****************************************************************
     * display countries with a population greater than a specific value
     ****************************************************************/ 
    private void displayPopulation () {
        try {
            int people = Integer.parseInt(populationTextField.getText());
            displayCountries (world.searchCountriesWithPopulation(people));
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Enter a valid number for population");
        }

    }

    /*****************************************************************
     * open a data file with the name selected by the user
     ****************************************************************/ 
    private void openFile(){

        // create File Chooser so that it starts at the current directory
        String userDir = System.getProperty("user.dir");
        JFileChooser fc = new JFileChooser(userDir);

        // show File Chooser and wait for user selection
        int returnVal = fc.showOpenDialog(this);

        // did the user select a file?
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            world.readCountriesData(filename);
            resultsArea.setText("File Opened");
        }
    }

    /*******************************************************
     * Creates the menu items
     *******************************************************/    
    private void setupMenus(){
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        countItem = new JMenuItem("Count Countries");
        listAllItem = new JMenuItem("List Countries");
        openItem = new JMenuItem("Open...");

        fileMenu.add(openItem);
        fileMenu.add(countItem);
        fileMenu.add(listAllItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();
        setJMenuBar(menus);
        menus.add(fileMenu);

        // register the menu items with the action listener
        countItem.addActionListener(this); 
        // **FIX ME: complete the other three menu items.
        fileMenu.addActionListener(this); 
        quitItem.addActionListener(this);
        listAllItem.addActionListener(this); 
        openItem.addActionListener(this); 

    }
    
    /**
     * checks if there is an actual continent entered in text field
     * @return true if string entered is an actual continent
     */
    public boolean checkContinent(){
        if(continentTextField.getText().toString().equalsIgnoreCase("africa") ||
            continentTextField.getText().toString().equalsIgnoreCase("asia") ||
            continentTextField.getText().toString().equalsIgnoreCase("north america") ||
            continentTextField.getText().toString().equalsIgnoreCase("south america") ||
            continentTextField.getText().toString().equalsIgnoreCase("europe") ||
            continentTextField.getText().toString().equalsIgnoreCase("oceania")){
            return true;
        }
        return false;
    }
    
    /**
     * checks if there is an actual country entered in text field
     * @return true if string entered is an actual country
     */
    public boolean checkCountry(){
        if(world.searchForCapital(countryNameTextField.getText().toString()) != null){
            return true;
        }
        return false;
    }
}