import java.awt.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 1/27/15.
 */
public class FlashCardCreator {
    List<String> fileLines = new ArrayList<String>();
    List<String> terms = new ArrayList<String>();
    List<String> definitions = new ArrayList<String>();
    String file = "/home/aaron/Documents/Spring_2015/Geomorphology/Chapter1.odt";

    private void readFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String fileLine = null;
            int lineCount = 0;
            while((fileLine = reader.readLine()) != null){
                fileLines.add(fileLine);
                lineCount++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void splitTerms(){
       for(int i = 0; i < fileLines.size(); i++){
           if(fileLines.get(i).contains(":")) {
               String[] splitLine = (fileLines.get(i)).split(":");
               terms.add(splitLine[0].trim());
               definitions.add(splitLine[1].trim());
           }
       }
    }

    public void displayTerms(){
        for(int i = 0; i < terms.size(); i++){
            System.out.print(terms.get(i) + "\n");
        }
    }

    public void displayDefinitions(){
        for(int i = 0; i < definitions.size(); i++){
            System.out.print(definitions.get(i) + "\n");
        }
    }

    public void createGUI(){
        JFrame frame = new JFrame("Flash Cards");
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        JTextArea definition = new JTextArea("Some Words");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(definition, constraints);

        /*
        JButton button1 = new JButton();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(button1, constraints);

        JButton button2 = new JButton();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(button2, constraints);

        JButton button3 = new JButton();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 1;
        panel.add(button3, constraints);

        JButton button4 = new JButton();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 3;
        constraints.gridy = 1;
        panel.add(button4, constraints);
        */

        frame.setVisible(true);
//        frame.pack();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JPanel addButtons(){
        JPanel buttonPanel = new JPanel();
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        JButton button4 = new JButton();

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);

        return buttonPanel;
    }

    public static void main(String[] args){
        FlashCardCreator test = new FlashCardCreator();
        test.readFile();
        test.splitTerms();
        test.createGUI();
    }
}
