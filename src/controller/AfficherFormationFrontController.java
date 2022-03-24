/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Formation;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.FormationDao;

/**
 *
 * @author amado
 */
public class AfficherFormationFrontController implements Initializable {

    @FXML
    
    private Button btnAcceuil;
    @FXML
  
    private Button btnProjet;
    @FXML
  
    private GridPane containerEvent;
    @FXML
   
    private TextField searchEvent;
    @FXML
   
    private ComboBox<String> comboTrier;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setDataContainerEvent(getAllEvents(""));
        ObservableList<String> options =  FXCollections.observableArrayList() ;
        options.add("tous" );
        options.add("mes evenements") ;
        comboTrier.setItems(options);
    }    

    @FXML
   
    private void retourAcceuil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AcceuilProjetFront.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);

         stage.setScene(
                 new Scene(loader.load())
         );
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();
        stage.show();
    }

    @FXML
   
    private void goToProjets(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AfficherProjetFront.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);

         stage.setScene(
                 new Scene(loader.load())
         );
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();
        stage.show();
    }
    
    private List<Formation> getAllEvents(String value){
        FormationDao edao = FormationDao.getInstance() ;
        List<Formation> list = edao.getListEvent(value) ;
       return list ;
    }
    
    public void setDataContainerEvent(List<Formation> listEvent){
        int column = 0 ;
        int row = 1 ;
        try {
            for(Formation e : listEvent){
                FXMLLoader fxmlloader = new FXMLLoader() ;
                fxmlloader.setLocation(getClass().getResource("/views/CardFormation.fxml"));
                VBox testBox = fxmlloader.load() ;
                CardFormationController ce =fxmlloader.getController() ;
                ce.setEventData(e);
                if(column == 3){
                    column = 0 ;
                    ++row ;
                 }
                containerEvent.add(testBox, column++, row);
                //GridPane.setMargin(testBox, new Insets(10));

             }
         }catch (IOException ex) {
            Logger.getLogger(AfficherFormationFrontController.class.getName()).log(Level.SEVERE, null, ex);
       }
                
    }

    @FXML
    
    private void rechercherFormation(KeyEvent event) {
        containerEvent.getChildren().clear();
        setDataContainerEvent(getAllEvents(searchEvent.getText()));
    }

    @FXML
   
    private void trierEvenement(ActionEvent event) {
        if(comboTrier.getValue().toLowerCase().equals("tous")){
            containerEvent.getChildren().clear();
            setDataContainerEvent(getAllEvents(searchEvent.getText()));
        }
        else{
            containerEvent.getChildren().clear();
            FormationDao edao = FormationDao.getInstance() ;
            List<Formation> listEvent = edao.getListUserEvent(searchEvent.getText(), 1) ;
            setDataContainerEvent(listEvent); 
        }
    }


//    public void initialize(URL location, ResourceBundle resources) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    

}
