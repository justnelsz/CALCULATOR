import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Calculator implements ActionListener{

    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, minButton, mulButton, divButton, negButton;
    JButton delButton, clearButton, equalButton, decButton;
    JLayeredPane panel;

    Font myFont = new Font("Poppins", Font.BOLD, 30);
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator(){
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(420,550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        ImageIcon frameIcon = new ImageIcon("imageicon.png"); 
        frame.setIconImage(frameIcon.getImage());
    
        panel = new JLayeredPane();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4,4,10,10));

        

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);

        addButton = new JButton("+");
        minButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        delButton = new JButton("Del");
        clearButton = new JButton("Clr");
        equalButton = new JButton("=");
        decButton = new JButton(".");
        negButton = new JButton("(-)");

        functionButtons[0] = addButton;
        functionButtons[1] = minButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = delButton;
        functionButtons[5] = clearButton;
        functionButtons[6] = equalButton;
        functionButtons[7] = decButton;
        functionButtons[8] = negButton;

        for(int i = 0; i < 9; i++){ //function buttons 
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for(int i = 0; i < 10; i++){ //number buttons
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        negButton.setBounds(50, 430, 70, 50);
        delButton.setBounds(135, 430, 100, 50);
        clearButton.setBounds(250, 430, 100, 50);

        panel.add(negButton);
        panel.add(delButton);
        panel.add(clearButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(divButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(mulButton);
        
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(minButton);
        
        panel.add(numberButtons[0]);
        panel.add(decButton);
        panel.add(equalButton);
        panel.add(addButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clearButton);
        frame.add(textfield);
        
        ImageIcon backgroundIcon = new ImageIcon("green screen.jpg");
        Image image = backgroundIcon.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        backgroundIcon = new ImageIcon(image);
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0,0, frame.getWidth(), frame.getHeight());

        frame.getContentPane().add(backgroundLabel);
        
        frame.setVisible(true);
    }
    public static void main(String[] args){

        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++){   
            if (e.getSource() == numberButtons[i]){
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton){
            textfield.setText(textfield.getText().concat("."));
        }
        if (e.getSource() == addButton){
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");
        }
        if (e.getSource() == minButton){
            num1 = Double.parseDouble(textfield.getText());
            operator = '-';
            textfield.setText("");
        }
        if (e.getSource() == mulButton){
            num1 = Double.parseDouble(textfield.getText());
            operator = '*';
            textfield.setText("");
        }
        if (e.getSource() == divButton){
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            textfield.setText("");
        }
        if (e.getSource() == equalButton){
            num2 = Double.parseDouble(textfield.getText());

            switch (operator){
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0){
                        result = num1 / num2;
                    } else {
                        textfield.setText("Error");
                        return;
                    }         
                    break;
            }
            textfield.setText(String.valueOf(result));
            num2 = result;
        }
        if (e.getSource() == clearButton){
            textfield.setText("");
        }
        if (e.getSource() == delButton){
            String input = textfield.getText();
            textfield.setText("");
            for(int i = 0; i < input.length() - 1; i++){
                textfield.setText(textfield.getText() + input.charAt(i));
            }
        }if (e.getSource() == negButton){
            try{
                double temp = Double.parseDouble(textfield.getText());
                temp *= -1;
                textfield.setText(String.valueOf(temp));
            } catch (NumberFormatException ex){
                textfield.setText("Error!");
            }
            
        }
    }
}