package teste.teste;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DynaGUI extends Applet implements ActionListener,
  ItemListener {
    public static final String ADD = "Add a button";
    public static final String REMOVE = "Remove a button";
    public static final String BUTTONGRID = "Button Grid";
    public static final String CONTROLPANEL = "Control Panel";

    Panel  panelTopLevel;   // contains all the other UI components

    Panel  panelSelect;     // contains the Choices component
    Choice choices;         // choices are "Button Grid" and "Control Panel"

    // UI for "Button Grid"
    Panel  panelGridMain;   // contains panelDynamic and panelStatic
    Panel  panelDynamic;    // contains the button grid (vecButtons)
    Panel  panelStatic;	    // contains the Add and Remove buttons
    Vector vecButtons;      // the grid of Buttons
    Button buttonAdd;
    Button buttonRemove;

    // UI for "Control Panel"
    Panel  panelControlsMain;  // contains the miscellaneous controls

    int    activePanel;

    public void init() {

	// Create top-level panel
	panelTopLevel = new Panel();
	panelTopLevel.setLayout(new BorderLayout(5,5));

	// Create a subpanel
	panelSelect = new Panel();
	panelTopLevel.add("North", panelSelect);

	// Put a Choice component in the subpanel
	// Permits user to choose between two UI configs
	choices = new Choice();
	choices.add(BUTTONGRID);
	choices.add(CONTROLPANEL);
	choices.addItemListener(this);
	panelSelect.add(choices);

	// Invoke first UI config: a grid of buttons
	makeButtonGrid();

	add(panelTopLevel);
    }

    //
    // First UI configuration: "Button Grid"
    //
    public void makeButtonGrid() {

	// Create a Vector to keep track of buttons within the grid
	vecButtons = new Vector();

	// Set up the main grid panel
	panelGridMain = new Panel();
	panelGridMain.setLayout(new BorderLayout(5,5));

	// Create panel to hold dynamically created buttons in a grid
	panelDynamic = new Panel();
	panelDynamic.setLayout(new GridLayout(3,2));
	panelGridMain.add("North", panelDynamic);

	// Create an initial button in the dynamic panel
	Button button = new Button("Button 1");
	panelDynamic.add(button);
	vecButtons.addElement(button);

	// create a "static" panel to hold just two control buttons
	panelStatic = new Panel();
	panelStatic.setLayout(new BorderLayout(5,5));
	add("South", panelStatic);
	panelGridMain.add("South", panelStatic);

	// create the two buttons which enable adding/deleting dynamic buttons
	buttonAdd = new Button(ADD);
	panelStatic.add("West", buttonAdd);
	buttonAdd.addActionListener(this);

	buttonRemove = new Button(REMOVE);
	panelStatic.add("East", buttonRemove);
	buttonRemove.addActionListener(this);

	panelTopLevel.add("South", panelGridMain);

	activePanel = 1;	    // Used by paint() method
	setVisible(true);
	panelTopLevel.validate();
	repaint();
    }


    //
    // Second UI configuration: a panel containing some UI components
    //
    public void makeMiscControls() {

	// Create the main to hold the UI components
	panelControlsMain = new Panel();
	panelControlsMain.setLayout(new BorderLayout(5,5));
	panelTopLevel.add(panelControlsMain);

	// Add three components: a button, a text field, and a checkbox
	Button button = new Button("A Button");
	panelControlsMain.add("East", button);

	Checkbox chkbox = new Checkbox("A Checkbox", true);
	panelControlsMain.add("West", chkbox);

	TextField tf = new TextField("A TextField");
	panelControlsMain.add("North", tf);

	activePanel = 2;	    // Used by paint() method
	setVisible(true);
	panelTopLevel.validate();
	repaint();
    }

    public void paint(Graphics g) {
	// Draw rectangle around applet's display area
	g.drawRect(0, 0, getSize().width - 1, getSize().height - 1);

	if (activePanel == 1) {
	    panelTopLevel.paint(g);
	    panelTopLevel.paintComponents(g);
	}
	else if (activePanel == 2) {
	    panelTopLevel.paint(g);
	    panelTopLevel.paintComponents(g);
	}
    }

    //
    // Callback method invoked when "Add Button" or "Remove Button" is
    // clicked
    //
    public void actionPerformed(ActionEvent event) {
	String command = event.getActionCommand();

	if (command.compareTo(ADD) == 0) {
	    // Create new button and add to panelDynamic -- see makeButtonGrid()
	    int i = vecButtons.size() + 1;
	    String strButton = new String();
	    strButton = "Button " + String.valueOf(i);

	    Button button = new Button(strButton);
	    panelDynamic.add(button);
	    vecButtons.addElement(button);

	    panelDynamic.validate();
	}
	else if (command.compareTo(REMOVE) == 0) {
	    // Remove the last button that was created -- see makeButtonGrid()
	    if (vecButtons.size() > 0) {
		Button button = (Button) vecButtons.lastElement();
		panelDynamic.remove(button);
		vecButtons.removeElementAt(vecButtons.size() - 1);
	    }
	}
	setButtonStates();
    }


    //
    // If appropriate, disable "Add Button" or "Remove Button" for
    // makeButtonGrid().
    //
    public void setButtonStates() {
	int numButtons = vecButtons.size();

	if (numButtons >= 9) {
	    buttonAdd.setEnabled(false);
	    buttonRemove.setEnabled(true);
	}
	else if (numButtons > 0 && numButtons < 9) {
	    buttonAdd.setEnabled(true);
	    buttonRemove.setEnabled(true);
	}
	else if (numButtons <= 0) {
	    buttonAdd.setEnabled(true);
	    buttonRemove.setEnabled(false);
	}
	panelStatic.validate();
    }


    //
    // Callback for the Choice component
    //
    public void itemStateChanged(ItemEvent event) {
	String item = (String) event.getItem();

	if (item.compareTo(CONTROLPANEL) == 0) {
	    panelGridMain.removeAll();
	    panelTopLevel.remove(panelGridMain);
	    makeMiscControls();
	    panelTopLevel.validate();
	}
	else if (item.compareTo(BUTTONGRID) == 0) {
	    if (panelControlsMain != null) {
		panelControlsMain.removeAll();
		panelTopLevel.remove(panelControlsMain);
	    }
	    makeButtonGrid();
	    panelTopLevel.validate();
	}
    }

    public static void main (String argv[])
    {
	    Frame f = new Frame("DynaFrame");
	    DynaGUI applet = new DynaGUI();
	    f.add("Center", applet);
	    applet.init();
	    applet.start();
	    f.pack();
	    f.show();
    }
}
