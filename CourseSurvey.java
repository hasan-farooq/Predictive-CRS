
package CRS.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Observable;
import java.util.ResourceBundle;
import java.io.*;
import java.util.ArrayList;

public class CourseSurvey {
    @FXML private Button button_1;
    @FXML private Button button_2;
    @FXML TextField textField_1;
    @FXML TextField textField_2;
    @FXML TextField textField_3;
    @FXML TextField textField_4;
    @FXML TextField textField_5;
    @FXML TextField textField_6;
    @FXML TextField textField_7;
    @FXML TextField textField_8;

    public void go_back(){
        Scene scene = button_1.getScene();
        Stage stage = (Stage) button_1.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/Login.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            ((Stage) stage).setTitle("Sign In");
            ((Stage) stage).setScene(new Scene(root, 300, 275));
            ((Stage) stage).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login_button(ActionEvent event){

        ArrayList<String> ratings = new ArrayList<String>();
        ratings.add(textField_1.getText());
        ratings.add(textField_2.getText());
        ratings.add(textField_3.getText());
        ratings.add(textField_4.getText());
        ratings.add(textField_5.getText());
        ratings.add(textField_6.getText());
        ratings.add(textField_7.getText());
        ratings.add(textField_8.getText());

        try {
            FileWriter myWriter = new FileWriter("/home/hasan/python/weights.txt");
            for(int i = 0; i < 8; i++)
            {
                String value = ratings.get(i);
                myWriter.write(value);
                if(i != 7)
                {
                    myWriter.write(",");
                }
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", "/home/hasan/python/env/bin/python3 /home/hasan/python/prac.py");
        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
                System.exit(0);
            } else {
                //abnormal...
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
