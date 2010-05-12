/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class placeOrderPanel extends JPanel {

    private specificationPanel specPanel;
    private JButton orderButton, searchButton;
    private JComboBox shipsCombo;
    private JScrollPane orderPane, startDatePane, endDatePane;
    private JTextArea orderText, startDateText, endDateText;
    private JRadioButton startRadio, endRadio;
    private JTextField dateChoiceField;
    private String[] info;
    private String startDate, endDate, startDest, endDest, conNum, content;

    placeOrderPanel(specificationPanel specPanel) {

        //Tilføj obj referencen til Specifkations obj
        this.specPanel = specPanel;


        //Layout for PlaceOrder Panel
        setBackground(Color.white);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //De mulige skibe og datoer
        String[] ships = {"Emma Mærsk", "Titanic", "Queen Mary"};



        //indsætning af dropdown menu, med skibens navne
        shipsCombo = new JComboBox();

        searchButton = new JButton("Søg efter skib ledige skibe");
        searchButton.addActionListener(new searchListener());

        JPanel shipChoosingPanel = new JPanel();
        shipChoosingPanel.setBackground(Color.white);
        shipChoosingPanel.setLayout(new BoxLayout(shipChoosingPanel, BoxLayout.Y_AXIS));
        
        shipChoosingPanel.add(new JLabel("Vælg et skib"));
        shipChoosingPanel.add(shipsCombo);
        shipChoosingPanel.add(searchButton);
        



        //rulbart vindue med startdatoer
        startDateText = new JTextArea();
        startDateText.setEditable(false);

//Temp:
        startDateText.setText("18-12-2010");

        startDatePane = new JScrollPane(startDateText);

        //Rulbart vindue med ankomstdatoer
        endDateText = new JTextArea();
        endDateText.setEditable(false);

//Temp
        endDateText.setText("20-12-2010");

        endDatePane = new JScrollPane(endDateText);



        JPanel datePanePanel = new JPanel();
        datePanePanel.setBackground(Color.white);
        datePanePanel.setLayout(new GridLayout(2, 2));
        datePanePanel.add(new JLabel("Afgangs dato: "));
        datePanePanel.add(new JLabel("Ankomst dato: "));
        datePanePanel.add(startDatePane);
        datePanePanel.add(endDatePane);


        dateChoiceField = new JTextField();
        JPanel choosingDatePanel = new JPanel();
        choosingDatePanel.setBackground(Color.white);
        choosingDatePanel.setLayout(new GridLayout(2, 1));

        choosingDatePanel.add(new JLabel("Indtast den ønskede dato: "));
        choosingDatePanel.add(dateChoiceField);


        //opret Listener til RadioButtons
        chooseOrder listener = new chooseOrder();

        //oprettelse af de to radiobuttons, og tilføjelse af deres actionlistener
        startRadio = new JRadioButton("Brug afgangs dato");
        startRadio.addActionListener(listener);
        startRadio.setBackground(Color.white);

        endRadio = new JRadioButton("Brug ankomst dato");
        endRadio.addActionListener(listener);
        endRadio.setBackground(Color.white);

        //indsæt radiobuttons i en gruppe
        ButtonGroup datoGroup = new ButtonGroup();
        datoGroup.add(startRadio);
        datoGroup.add(endRadio);

        JPanel radioPanel = new JPanel();
        radioPanel.setBackground(Color.white);
        radioPanel.add(startRadio);
        radioPanel.add(endRadio);


        orderText = new JTextArea();    //Et felt hvor man kan skrive
        orderText.setEditable(false);   //Gør at man ikke kan ændre i teksten

        //oprettelse af rul-bar vindue
        orderPane = new JScrollPane(orderText);
        orderPane.setSize(new Dimension(100, 100));


        //De to knapper: søg og opret
        orderButton = new JButton("Opret Ordre");
        orderButton.addActionListener(new makeOrderListener());

        

        

//*********************************************************
//          Tilføjning af de forskellige komponenter
//***************************************************

        add(shipChoosingPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(datePanePanel);
        add(choosingDatePanel);
        add(radioPanel);
        add(orderPane);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(orderButton);

    }

    private class chooseOrder implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            String ship = (String) shipsCombo.getSelectedItem();
            //Ship er skibet som bliver valgt i dropdown vinduet

            Object source = event.getSource();

            if (source == startRadio) {
                orderText.setText("Afgangs dato: " + dateChoiceField.getText()
                        + "\n\nStart destination: " + startDest
                        + "\nSlut Destination: " + endDest
                        + "\nAntal container: " + conNum
                        + "\n\nOrdre indhold: " + content
                        + "\nSkibs ID: " + ship);
            } else {
                orderText.setText("Ankomst dato: " + dateChoiceField.getText()
                        + "\n\nStart destination: " + startDest
                        + "\nSlut Destination: " + endDest
                        + "\nAntal container: " + conNum
                        + "\n\nOrdre indhold: " + content
                        + "\nSkibs ID: " + ship);
            }


        }
    }

    private class makeOrderListener implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            JOptionPane.showMessageDialog(null, "Ordren er oprettet og registeret");
        }
    }

    private class searchListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            /* getInfo sender et String array:
             * {startdato, slutdato, startdestination, slutdestination, antal container
             * , indhold af ordre}
             */
            info = specPanel.getInfo(); //Strign array
            startDate = info[0];
            endDate = info[1];
            startDest = info[2];
            endDest = info[3];
            conNum = info[4];
            content = info[5];
            System.out.println("søgning er udført");
        }
    }



    private class shipChoiceListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            String shipName = (String) shipsCombo.getSelectedItem();
            //ShipName kan nu bruge stil at hente de forskellige daoter


        }
    }
}
