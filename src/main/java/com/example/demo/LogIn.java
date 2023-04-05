package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class LogIn implements Serializable {
    // instance variables
    @FXML
    private transient TextField logInUserName;
    @FXML
    private transient TextField PasswordLogIn;
    @FXML
    private transient Label LogInMessage;

    final User person = new User();
    public static Creator creator = null;
    public static Admin admin = null;

    public LogIn() {
    }

    // event handler for login button

    public void logInAction(ActionEvent event) throws IOException {
        try {
            logIn(Start.myMap);
        } catch (InvalidCredentialsException e) {
            LogInMessage.setText(e.getMessage());
        }
    }


    public static Creator getCreator() {
        return creator;
    }

    // Main login logic
    public void logIn(HashMap<String, Integer> myMap) throws IOException, InvalidCredentialsException {
        Main main = new Main();

        String username = logInUserName.getText();
        String password = PasswordLogIn.getText();

        // If the entered username and password matches the ones in the HashMap
        if (myMap.containsKey(username) && myMap.get(username).equals(Integer.parseInt(password))) {
            LogInMessage.setText("Success!");

            try {
                File txtFile = new File(username + ".txt");
                Scanner myReader = new Scanner(txtFile);

                // Read user details from the text file and set them to the User object
                String data = myReader.nextLine();
                person.setUser(data);

                data = myReader.nextLine();
                person.setPassword(data);

                data = myReader.nextLine();
                person.setInvitedTo(data.split(", "));

                data = myReader.nextLine();
                person.setCreated(data.split(", "));

                int who;
                data = myReader.nextLine();
                who = Integer.parseInt(data);

                // If user is a creator, set the Creator object and navigate to the Creator screen
                if (who == 1) {
                    person.setCreator(true);
                    creator = new Creator();
                    creator.setUser(person.getUser());
                    creator.setPassword(person.getPassword());
                    creator.setInvitedTo(person.getInvitedTo());
                    creator.setCreated(person.getCreated());
                    creator.setCreator(true);

                    main.changeScene("creator.fxml");
                }
                // If user is an admin, set the Admin object and navigate to the Admin screen
                else {
                    person.setAdmin(true);
                    admin = new Admin();
                    admin.setUser(person.getUser());
                    admin.setPassword(person.getPassword());
                    admin.setInvitedTo(person.getInvitedTo());
                    admin.setCreated(person.getCreated());
                    admin.setAdmin(true);

                    main.changeScene("admin.fxml");
                }

                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            throw new InvalidCredentialsException("You have entered the wrong credentials, please try again.");
        }
    }

    public void GoBackLogin(ActionEvent event) throws IOException {
        back();
    }

    public void back() throws IOException {
        Main main = new Main();
        main.changeScene("start.fxml");

    }

}