package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Admin extends User implements Initializable, Serializable {
    @FXML
    private TextField AdminTextField;
    @FXML
    private Label AdminCity;
    @FXML
    private Label AdminCaption;
    @FXML
    private Label AdminName;
    @FXML
    private Label AdminCost;
    @FXML
    private RadioButton AdminRadioButton;
    @FXML
    private TableView AdminPlans;
    @FXML
    private TableView AdminTableInvitations;
    @FXML
    private TableColumn AdminInvitations;
    @FXML
    private TableColumn AdminPlansColumn;

    private String location;
    Place newPlace = new Place();
    public Admin(String location) {this.location = location; }
    public String getLocation() {return location; }
    public void setLocation(String location) {this.location = location;}
    Admin admin= LogIn.admin;


    public void printInvitations() {
        ObservableList<Invitation> invitations = FXCollections.observableArrayList();
        if (admin.getInvitedTo().length != 0) {
            for (int i = 0; i < admin.getInvitedTo().length; i++) {
                Invitation invitation = new Invitation(admin.getInvitedTo()[i]);
                invitations.add(invitation);
            }
        }
        AdminInvitations.setCellValueFactory(new PropertyValueFactory<Invitation,String>("name"));
        AdminTableInvitations.setItems(invitations);
    }

    public void locationInformation(ActionEvent event) throws IOException {
        printInvitations();
        String data =AdminTextField.getText();


        for(int i=0; i<admin.getInvitedTo().length; i++)
        {
            if (admin.getInvitedTo()[i].equals(data))
            {
                setLocation(data);
                readLocation();
                putInformation();
                break;
            }
        }

    }

    //prints data about location into console
    public void putInformation() {
        AdminCity.setText(newPlace.getCaption());
        AdminCaption.setText(newPlace.getCity());
        AdminCost.setText(Double.toString(newPlace.getCost()));

        ObservableList<Invitation> invitations = FXCollections.observableArrayList();
        if (newPlace.getActivities().length != 0) {
            for (int i = 0; i < newPlace.getActivities().length; i++) {
                Invitation invitation = new Invitation(newPlace.getActivities()[i]);
                invitations.add(invitation);
            }
        }
        AdminPlansColumn.setCellValueFactory(new PropertyValueFactory<Invitation, String>("name"));
        AdminPlans.setItems(invitations);
    }

    //reads file and inserts data into variables
    public void readLocation() {

        try {
            File file = new File(getLocation()+".txt");
            Scanner myReader = new Scanner(file);

            while(myReader.hasNextLine()) {
                newPlace.setCreatorPlace(myReader.nextLine());
                newPlace.setCaption(myReader.nextLine());
                newPlace.setCity(myReader.nextLine());

                String data = myReader.nextLine();
                newPlace.setActivities(data.split(", "));

                data=myReader.nextLine();
                newPlace.setCost(Double.parseDouble(data));

                data = myReader.nextLine();
                newPlace.setInvited(data.split(", "));

                String[] read =myReader.nextLine().split(", ");
                Integer[] convert = new Integer[read.length];

                for(int i=0; i< read.length; i++)
                {
                    convert[i]= Integer.parseInt(read[i]);
                }
                newPlace.setGoing(convert);
            }

            myReader.close();
        } catch (FileNotFoundException var7) {
            System.out.println("An error occurred.");
            var7.printStackTrace();
        }
    }

    public void decideToGo(ActionEvent event) throws IOException {

        boolean want;
        want = AdminRadioButton.isSelected();
        if (want) {
            int index = 0;
            Integer[] newArray = newPlace.getGoing();
            for (int i = 0; i < newPlace.getInvited().length; i++) {
                if (newPlace.getInvited()[i].equals(getUser())) {
                    index = i;
                    break;
                }
            }
            newArray[index] = 1;
            newPlace.setGoing(newArray);
            SaveData.writeData(newPlace);
        }
        AdminTextField.clear();
        AdminCity.setText("");
        AdminCaption.setText("");
        AdminCost.setText("");
        AdminRadioButton.fire();

        ObservableList<Invitation> invitations = FXCollections.observableArrayList();
        for (int i = 0; i < 1; i++) {
            Invitation invitation = new Invitation("");
            invitations.add(invitation);
        }

        AdminPlansColumn.setCellValueFactory(new PropertyValueFactory<Invitation, String>("name"));
        AdminPlans.setItems(invitations);

    }

    public void LogOutAdmin(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("start.fxml");
    }

    public class Invitation implements Serializable {
        private String name;

        public Invitation(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        printInvitations();
        AdminName.setText(admin.getUser()+"!");
    }

    public Admin() {
        super();
    }
}
