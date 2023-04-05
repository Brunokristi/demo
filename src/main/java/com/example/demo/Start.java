package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class Start implements Serializable {
    public Start() {
    }

    @FXML
    private transient Button StartSignIn;
    @FXML
    private transient Button StartSignUp;

    static HashMap<String, Integer> myMap = new HashMap();


    public void Startsignin(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("login.fxml");
    }

    public void Startsignup(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("signup.fxml");
    }


    //driver for running login
    public static void runLogin() {

        try {
            File filePersonal = new File("personal.txt");
            Scanner myReader = new Scanner(filePersonal);

            while(myReader.hasNextLine()) {
                String user_name = myReader.nextLine();
                String password_string = myReader.nextLine();
                Integer password_int = Integer.parseInt(password_string);
                myMap.put(user_name, password_int);
            }

            myReader.close();
        } catch (FileNotFoundException var7) {
            System.out.println("An error occurred.");
            var7.printStackTrace();
        }
    }
}
