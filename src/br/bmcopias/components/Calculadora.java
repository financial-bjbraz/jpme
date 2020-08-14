package br.bmcopias.components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

///////////////////////////////////////////////////////////////////// class Calc
public class Calculadora extends JPanel {
    //================================================================ constants
    private static final Font BIGGER_FONT = new Font("monspaced", Font.PLAIN, 10);
    
    //=================================================================== fields
    //... Component referenced during execution
    private JTextField _displayField;       // display result / input.
    
    //... Variables representing state of the calculator
    private boolean   _startNumber = true;      // true: num key next
    private String    _previousOp  = "=";       // previous operation
    private CalcLogic _logic = new CalcLogic(); // The internal calculator.
    
    //============================================================== constructor
    public Calculadora() {
        //... Set attributes of the display field
        _displayField = new JTextField("0", 12);
        _displayField.setHorizontalAlignment(JTextField.RIGHT);
        _displayField.setFont(BIGGER_FONT);
        
        //... Create and set attributes of clear button
        JButton clearButton = new JButton("Clear");
        clearButton.setFont(BIGGER_FONT);
        clearButton.addActionListener(new ClearListener());
        
        //... Use one listener for all numeric keys.
        ActionListener numListener = new NumListener();
        
        //... Layout numeric keys in a grid.  Generate the buttons
        //    in a loop from the chars in a string.
        String buttonOrder = "789456123.0 ";
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 3, 2, 2));
        for (int i = 0; i < buttonOrder.length(); i++) {
            String keyTop = buttonOrder.substring(i, i+1);
            JButton b = new JButton(keyTop);
            if (keyTop.equals(" ")) {
                //... Put a dummy button in this position.
                b.setEnabled(false);
            } else {
                //... Put a digit button in the interface.
                b.addActionListener(numListener);
                b.setFont(BIGGER_FONT);
            }
            buttonPanel.add(b);
        }
        
        //... One ActionListener to use for all operator buttons.
        ActionListener opListener = new OpListener();
        
        //... Create panel with gridlayout to hold operator buttons.
        //    Use array of button names to create buttons in a loop.
        JPanel opPanel = new JPanel();
        opPanel.setLayout(new GridLayout(5, 1, 2, 2));
        String[] opOrder = {"+", "-", "*", "/", "="};
        for (int i = 0; i < opOrder.length; i++) {
            JButton b = new JButton(opOrder[i]);
            b.addActionListener(opListener);
            b.setFont(BIGGER_FONT);
            opPanel.add(b);
        }
        
        //... Put Clear button in flow layout to keep from expanding.
        JPanel clearPanel = new JPanel();
        clearPanel.setLayout(new FlowLayout());
        clearPanel.add(clearButton);
        
        //... Layout the top-level content panel.
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout(5, 5));
        content.add(_displayField, BorderLayout.NORTH );
        content.add(buttonPanel   , BorderLayout.CENTER);
        content.add(opPanel       , BorderLayout.EAST  );
        content.add(clearPanel    , BorderLayout.SOUTH );
        
        add(content);
        
        
        //... Finish building the window (JFrame)
//        this.setContentPane(content);
//        this.pack();
//        this.setTitle("Simple Calc");
//        this.setResizable(false);
//        this.setLocationRelativeTo(null);
    }//end constructor
    
    
    //============================================================== actionClear
    /** Called by Clear btn action listener and elsewhere.*/
    private void actionClear() {
        _startNumber = true;         // Expecting number, not op.
        _displayField.setText("0");
        _previousOp  = "=";
        _logic.setTotal("0");
    }
    
    //////////////////////////////////////////// inner listener class OpListener
    /** Listener for all op buttons. */
    class OpListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // The calculator is always in one of two states.
            // 1. A number must be entered -- an operator is wrong.
            // 2. An operator must be entered.
            if (false) { // Error: needed number, not operator
                //... In this state we're expecting a number, but got an operator.
                actionClear();
                _displayField.setText("ERROR - No operator");
            } else {
                //... We're expecting an operator.
                _startNumber = true;  // Next thing must be a number
                try {
                    // Get value from display field, convert, do prev op
                    // If this is the first op, _previousOp will be =.
                    String displayText = _displayField.getText();
                    
                    if (_previousOp.equals("=")) {
                        _logic.setTotal(displayText);
                    } else if (_previousOp.equals("+")) {
                        _logic.add(displayText);
                    } else if (_previousOp.equals("-")) {
                        _logic.subtract(displayText);
                    } else if (_previousOp.equals("*")) {
                        _logic.multiply(displayText);
                    } else if (_previousOp.equals("/")) {
                        _logic.divide(displayText);
                    }
                    
                    _displayField.setText("" + _logic.getTotalString());
                    
                } catch (NumberFormatException ex) {
                    actionClear();
                    _displayField.setText("Error");
                }
                
                //... set _previousOp for the next operator.
                _previousOp = e.getActionCommand();
            }//endif _startNumber
        }//endmethod
    }//end class
    
    
    //////////////////////////////////// inner listener class ClearListener
    /** Action listener for numeric keys */
    class NumListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String digit = e.getActionCommand(); // Get text from button
            if (_startNumber) {
                //... This is the first digit, clear field and set
                _displayField.setText(digit);
                _startNumber = false;
            } else {
                //... Add this digit to the end of the display field
                _displayField.setText(_displayField.getText() + digit);
            }
        }
    }
    
    
    //////////////////////////////////// inner listener class ClearListener
    class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            actionClear();
        }
    }
    class CalcLogic {
        
        //-- Instance variables.
        private double _currentTotal;   // The current total is all we need to remember.
        
        /** Constructor */
        public CalcLogic() {
            _currentTotal = 0;
        }
        
        public String getTotalString() {
            return "" + _currentTotal;
        }
        
        public void setTotal(String n) {
            _currentTotal = convertToNumber(n);
        }
        
        public void add(String n) {
            _currentTotal += convertToNumber(n);
        }
        
        public void subtract(String n) {
            _currentTotal -= convertToNumber(n);
        }
        
        public void multiply(String n) {
            _currentTotal *= convertToNumber(n);
        }
        
        public void divide(String n) {
            _currentTotal /= convertToNumber(n);
        }
        
        private double convertToNumber(String n) {
            return Double.parseDouble(n);
        }
    }
    
    //============================================================== method main
    public static void main(String[] args) {
        //... Set the Look and Feel to that of system we're running on.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            ; // Ignore exception because we can't do anything.  Will use default.
        }
        
        //... Create the window.
        JFrame window = new JFrame();
        window.getContentPane().add(new Calculadora());
        window.pack();
        window.setTitle("Simple Calc");
        window.setResizable(false);
        window.setLocationRelativeTo(null);        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }    
    
}

