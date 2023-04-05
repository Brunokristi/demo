package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

import static java.lang.Double.parseDouble;

public class Creator extends User implements Initializable, Serializable {

    @FXML
    private  transient TableColumn locationColumn;
    @FXML
    private  transient TableColumn likesColumn;
    @FXML
    private  transient TableColumn totalRatingsColumn;
    @FXML
    private  transient Label CreatorMessage;
    @FXML
    private  transient Label CreatorName;
    @FXML
    private  transient TableView CreatorTable;
    @FXML
    private TextField CreatorCity;
    @FXML
    private TextField CreatorNeighbouringCities;
    @FXML
    private TextField CreatorCaption;
    @FXML
    private TextField CreatorCost;
    @FXML
    private TextField CreatorPlans;
    @FXML
    private TextField CreatorInvite;

    private transient final Creator creator = LogIn.getCreator();
    private transient final Place newPlace = new Place();
    private transient final String[] arr = Graph.array;
    private transient final Graph graph = new Graph();
    private transient final ArrayList<String> myList = new ArrayList<String>();
    private transient final ArrayList<String> friendList = new ArrayList<String>();

    public Creator() {
        super();
    }

    public void checkCity(ActionEvent event) throws IOException {
        newPlace.setCity(CreatorCity.getText().toString());
        if (Arrays.asList(arr).contains(newPlace.getCity())) {
            CreatorMessage.setText("This city already exists.");
            CreatorCity.clear();

        } else {
            CreatorMessage.setText("OK, City set.");
            File file = new File("graph.txt");
            FileWriter writer = null;
            try {
                writer = new FileWriter(file, true);
                writer.write("\n" + newPlace.getCity());
            } catch (
                    IOException var19) {
                var19.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (IOException var18) {
                    var18.printStackTrace();
                }
            }
        }
    }

    public void AddNeighbour(ActionEvent event) throws IOException {
        File file = new File("graph.txt");
        FileWriter writer = null;
        String Neighbour = CreatorNeighbouringCities.getText().toString();

        graph.addNewEdge(newPlace.getCity(), Neighbour, true);

        try {
            writer = new FileWriter(file, true);
            writer.write(", " + Neighbour);
        } catch (IOException var19) {
            var19.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException var18) {
                var18.printStackTrace();
            }
        }
        CreatorMessage.setText("Neighbour added.");
        CreatorNeighbouringCities.clear();
    }

    public void AddPlans(ActionEvent event) throws IOException {
        String Activity = CreatorPlans.getText().toString();
        myList.add(Activity);
        String[] myArray = new String[myList.size()];
        newPlace.setActivities(myList.toArray(myArray));
        CreatorMessage.setText("Plan added. Keep going!");
        CreatorPlans.clear();
    }


        public void AddFriend(ActionEvent event) throws IOException {
        String Friend = CreatorInvite.getText().toString();
        friendList.add(Friend);

        String[] friendArray = new String[friendList.size()];
        newPlace.setInvited(friendList.toArray(friendArray));
        CreatorMessage.setText("Friend invited!");
        CreatorInvite.clear();
    }




    //prints places we created and add the ranking of the place based on the liking of friends
    public void readCreated() throws IOException {
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        likesColumn.setCellValueFactory(new PropertyValueFactory<>("likes"));
        totalRatingsColumn.setCellValueFactory(new PropertyValueFactory<>("totalRatings"));

        ObservableList<PlaceData> data = FXCollections.observableArrayList();

        if (creator.getCreated().length != 0) {
            for (int i = 0; i < creator.getCreated().length; i++) {
                try {
                    File myObj = new File(creator.getCreated()[i] + ".txt");
                    Scanner myReader = new Scanner(myObj);

                    for (int j = 0; j < 6; j++) {
                        myReader.nextLine();
                    }
                    String dataString = myReader.nextLine();
                    String[] created = dataString.split(", ");
                    int all = created.length;
                    int res = 0;
                    for (int j = 0; j < all; j++) {
                        if (created[j].equals("1")) {
                            res++;
                        }
                    }
                    myReader.close();
                    data.add(new PlaceData(creator.getCreated()[i], all, res));
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
            CreatorTable.setItems(data);
        }
    }

    public void CreatePlace(ActionEvent event) throws IOException {
        newPlace.setCreatorPlace(creator.getUser());

        newPlace.setCaption(CreatorCaption.getText());

        String[] copy = Arrays.copyOf(creator.getCreated(), creator.getCreated().length + 1);
        copy[creator.getCreated().length] = newPlace.getCity();
        creator.setCreated(copy);

        newPlace.setCost( parseDouble(CreatorCost.getText()));

        File file = new File("graph.txt");
        FileWriter writer = null;
        String Neighbour = CreatorNeighbouringCities.getText();

        //add last neighbour
        graph.addNewEdge(newPlace.getCity(), Neighbour, true);

        try {
            writer = new FileWriter(file, true);
            writer.write(", " + Neighbour);
        } catch (IOException var19) {
            var19.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException var18) {
                var18.printStackTrace();
            }
        }

        //add last plan
        String Activity = CreatorPlans.getText();
        myList.add(Activity);
        String[] myArray = new String[myList.size()];
        newPlace.setActivities(myList.toArray(myArray));


        //add last friend
        String Friend = CreatorInvite.getText();
        friendList.add(Friend);
        String[] friendArray = new String[friendList.size()];
        newPlace.setInvited(friendList.toArray(friendArray));

        int count = friendList.size();
        Integer[] goingArray = new Integer[count];

        for (int i = 0; i < count; i++) {
            goingArray[i] = 0;
        }
        newPlace.setGoing(goingArray);

        SaveData.writeData(newPlace);
        updatedInvited();
        SaveData.Put(creator);

        CreatorInvite.clear();
        CreatorPlans.clear();
        CreatorCost.clear();
        CreatorCaption.clear();
        CreatorNeighbouringCities.clear();
        CreatorCity.clear();
        CreatorMessage.setText("");
        readCreated();
    }

    public void updatedInvited() {
        User updateUser = new User();

        for (int i = 0; i < newPlace.getInvited().length; i++) {
            try {
                File File = new File(newPlace.getInvited()[i] + ".txt");
                Scanner myReader = new Scanner(File);

                String data = myReader.nextLine();
                updateUser.setUser(data);

                data = myReader.nextLine();
                updateUser.setPassword(data);

                data = myReader.nextLine();
                updateUser.setInvitedTo(data.split(", "));

                String[] newArray = new String[updateUser.getInvitedTo().length + 1];

                for (i = 0; i < updateUser.getInvitedTo().length; i++) {
                    newArray[i] = updateUser.getInvitedTo()[i];
                }
                newArray[updateUser.getInvitedTo().length] = newPlace.getCity();
                updateUser.setInvitedTo(newArray);

                data = myReader.nextLine();
                updateUser.setCreated(data.split(", "));

                int who;
                data = myReader.nextLine();
                who = Integer.parseInt(data);
                if (who == 1) {
                    updateUser.setCreator(true);
                } else {
                    updateUser.setAdmin(true);
                }
                SaveData.Put(updateUser);

                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public void LogOut(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("start.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialize the table columns
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        likesColumn.setCellValueFactory(new PropertyValueFactory<>("likes"));
        totalRatingsColumn.setCellValueFactory(new PropertyValueFactory<>("totalRatings"));
        CreatorName.setText(creator.getUser()+"!");

        // Load the data
        try {
            readCreated();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}


