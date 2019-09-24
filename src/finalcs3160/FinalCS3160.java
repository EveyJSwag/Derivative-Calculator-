package finalcs3160;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class FinalCS3160 {

    static int getExponent(String subString) {
        int exponent = 0;
        int finalIndex = 0;
        for (int i = 0; i < subString.length(); i++) {
            if ((int) subString.charAt(i) >= 48 && (int) subString.charAt(i) <= 57) {
                finalIndex++;
            } else {
                break;
            }
        }
        return Integer.parseInt(subString.substring(0, finalIndex));
    }

    public static String getDerivative(String derivative, int degree) {
        
        
        if (((int) derivative.charAt(0) >= 65 && (int) derivative.charAt(0) <= 90)
                || ((int) derivative.charAt(0) >= 97 && (int) derivative.charAt(0) <= 122)) {
            if ((int) derivative.charAt(1) == 94) {
                if (((int) derivative.charAt(2) >= 48 && (int) derivative.charAt(2) <= 57)) {
                    
                    try {
                        int exponent = getExponent(derivative.substring(2));
                    if (exponent == 2){
                    
                        String returnString = (exponent + "" + derivative.charAt(0) + "");
                        if (degree == 1 ){
                        return returnString;
                        }
                        else if (degree > 1){
                            return getDerivative(returnString, degree - 1);
                        }
                    } else {
                        return getDerivative ((exponent + "" + derivative.charAt(0) + "^" + (exponent - 1)), degree - 1);
                    }
                    
                    
                    } catch (StringIndexOutOfBoundsException w){
                        int exponent = getExponent(derivative.substring(2));
                        if (degree == 1){
                            return exponent + "";
                        } else if (degree > 1){
                            return "0";
                        }
                    }
                }
            } 
        }

        if (((int) derivative.charAt(0) >= 48 && (int) derivative.charAt(0) <= 57)) {
            int scalarLength = 1;
            for (int i = 1; i < derivative.length(); i++) {
                if (((int) derivative.charAt(i) >= 48 && (int) derivative.charAt(i) <= 57)) {
                    scalarLength++;
                } else {
                    break;
                }
            }
            if (scalarLength == derivative.length()) {
                return "0";
            }

            try {
                int scalar = Integer.parseInt(derivative.substring(0, (scalarLength)));
                int exponent = getExponent(derivative.substring(scalarLength + 2));
                int newScalar = scalar * exponent;
                String returnString = newScalar + "" + derivative.charAt(scalarLength) + "^" + (exponent - 1);
                if ( (exponent == 1)  && degree == 1) {
                    return newScalar + "" + derivative.charAt(scalarLength);
                }
                if (degree == 1 && exponent == 2){
                    return (scalar * exponent) + "" + derivative.charAt(scalarLength);
                }
                else if (degree ==1 && exponent > 2){
                    return (scalar * exponent) + "" + derivative.charAt(scalarLength) + "^" +(exponent - 1);
                }
                else if (degree > 1 && exponent ==2){
                    return getDerivative((scalar * exponent) + "" + derivative.charAt(scalarLength), degree - 1);
                }
                else if (degree > 1){
                    return getDerivative (returnString, degree - 1);
                }
            } catch (StringIndexOutOfBoundsException s) {
                int scalar = Integer.parseInt(derivative.substring(0, (scalarLength)));
                return scalar + "";
            }
        }
        return null;
    }

    public static void main(String[] args) {

        JFrame appWindow = new JFrame("Derivative Calculator");
        Color myCoolColor = new Color(200, 0, 200);
        appWindow.setLayout(null);
        appWindow.setSize(333, 150);
        //Degree Input Prompt
        TextField getDegree = new TextField("Enter Deriv #");
        getDegree.setForeground(Color.PINK);
        getDegree.setBackground(myCoolColor);
        getDegree.setFont(new Font("Dialog", Font.BOLD, 12));
        getDegree.setSize(111, 20);
        getDegree.setLocation(111, 10);
        getDegree.setEditable(false);
        //Degree Input
        TextField degreeNumInput = new TextField();
        degreeNumInput.setSize(new Dimension(111, 20));
        degreeNumInput.setFont(new Font("Dialog", Font.BOLD, 12));
        degreeNumInput.setForeground(Color.pink);
        degreeNumInput.setLocation(111, 40);
        degreeNumInput.setBackground(myCoolColor);
        //Derivative Input
        TextField onClick = new TextField();
        onClick.setSize(111, 20);
        onClick.setLocation(111, 70);
        onClick.setBackground(myCoolColor);
        onClick.setForeground(Color.PINK);
        onClick.setFont(new Font("Dialog", Font.BOLD, 12));
        //Calculate Button
        JButton calculate = new JButton("Calculate");
        calculate.setLocation(111, 100);
        calculate.setSize(new Dimension(111, 20));
        calculate.setFont(new Font("Dialog", Font.BOLD, 12));
        calculate.setForeground(myCoolColor);
        calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final String inputString = onClick.getText();
                try {
                    int degreeNum = Integer.parseInt(degreeNumInput.getText());
                    String deriv = getDerivative(inputString, degreeNum);
                    onClick.setText(deriv);
                } catch (NumberFormatException w) {
                    String deriv = getDerivative(inputString, 1);
                    onClick.setText(deriv);
                }
            }
        });
        appWindow.add(degreeNumInput);
        appWindow.add(getDegree);
        appWindow.add(onClick);
        appWindow.add(calculate);
        appWindow.setResizable(false);
        appWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        appWindow.setVisible(true);
    }
}
