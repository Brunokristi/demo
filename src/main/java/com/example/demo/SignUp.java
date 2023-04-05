package com.example.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;


public class SignUp implements Serializable {

    public SignUp(){
    }

    @FXML
    private Button SignUpButton;
    @FXML
    private TextField SignUpUsername;
    @FXML
    private PasswordField SignUpPassword;
    @FXML
    private PasswordField SighUpPassword2;
    @FXML
    private RadioButton CreatorButton;
    @FXML
    private Label SignUpLabel;


    public void SignUpAction(ActionEvent event) throws IOException {
        singUp(Start.myMap);
    }

    public void singUp(HashMap<String, Integer> myMap) throws IOException {
        Main m = new Main();

        String UN = SignUpUsername.getText().toString();


        String PW = SignUpPassword.getText().toString();
        int password_int_1 = Integer.parseInt(PW);


        String PW2 = SighUpPassword2.getText().toString();
        int password_int_2 = Integer.parseInt(PW2);


        boolean whoAreYou= false;
        whoAreYou=CreatorButton.isSelected();
        int val = (whoAreYou) ? 1 : 0;


        if (myMap.containsKey(UN) || password_int_1!=password_int_2) {

            if (myMap.containsKey(UN)){
                SignUpLabel.setText("User already exists.");
            }

            else{
                SignUpLabel.setText("Passwords do not match.");
            }

        }

        else {
            myMap.put(UN, password_int_1);
            File file = new File("personal.txt");
            FileWriter fr = null;

            try {
                fr = new FileWriter(file, true);
                fr.write("\n" + UN + "\n" + PW);
            } catch (IOException var19) {
                var19.printStackTrace();
            } finally {
                try {
                    fr.close();
                } catch (IOException var18) {
                    var18.printStackTrace();
                }

            }

            file = new File(UN+".txt");
            fr = null;

            try {
                fr = new FileWriter(file);
                fr.write(UN+ "\n");
                fr.write(PW+ "\n");
                fr.write(", \n");
                fr.write(", \n");
                fr.write(String.valueOf(val));

            } catch (IOException var19) {
                var19.printStackTrace();
            } finally {
                try {
                    fr.close();
                } catch (IOException var18) {
                    var18.printStackTrace();
                }

            }

            SignUpLabel.setText("Successfully signed up, please log in");
            m.changeScene("login.fxml");

        }

    }

    public void GoBack(ActionEvent event) throws IOException {
        back();
    }

    public void back() throws IOException {
        Main m = new Main();
        m.changeScene("start.fxml");
    }


}
