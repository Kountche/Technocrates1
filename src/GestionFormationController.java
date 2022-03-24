import entities.Formation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.FormationDao;

/**
 * FXML Controller class
 *
 * @author damos
 */
public class GestionFormationController implements Initializable {

    @FXML
    private TextField tfNomEvent;
    @FXML
    private DatePicker dpDateEvent;
    @FXML
    private TextField tfLieuEvent;
    @FXML
    private Spinner<Integer> spPrix;
    @FXML
    private TextArea tfDescEvent;
    @FXML
    private Button btnAddEvent;
    @FXML
    private ComboBox<String> comboType;
    @FXML
    private Tab listEvent;
    @FXML
    private TableView<Formation> tvEvent;
    @FXML
    private TableColumn<Formation, String> colNomEvent;
    @FXML
    private TableColumn<Formation, Date> colDateEvent;
    @FXML
    private TableColumn<Formation, String> colLieuEvent;
    @FXML
    private TableColumn<Formation, Integer> colPrixEvent;
    @FXML
    private TableColumn<Formation, String> colDescEvent;
    @FXML
    private TextField tfNomEventM;
    @FXML
    private DatePicker dpDateEventM;
    @FXML
    private TextField tfLieuEventM;
    @FXML
    private Spinner<Integer> spPrixEventM;
    @FXML
    private TextArea tfDescEventM;
    @FXML
    private Label idEvent;
    @FXML
    private Button btnUpdateEvent;
    @FXML
    private Button btnDeleteEvent;
    @FXML
    private TextField tfSearchEvent;
    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnProjet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Gratuit") ;
        options.add("Payant") ;
        comboType.setItems(options);
        idEvent.setVisible(false);
    }    

    @FXML
    private void onclickAjouterEvenement(ActionEvent event) {
        if(tfNomEvent.getText().isEmpty() || tfLieuEvent.getText().isEmpty() || tfDescEvent.getText().isEmpty()
                || dpDateEvent.getValue() == null || comboType.getValue() == null)
        {
            showAlertMessageError("Ajouter Evenement", "veuillez remplir tous les champs");
        }
        else{
           String nom = tfNomEvent.getText() ;
           String lieu = tfLieuEvent.getText() ;
           String description = tfDescEvent.getText() ;
           java.sql.Date date =java.sql.Date.valueOf(dpDateEvent.getValue());
           int prix = spPrix.getValue() ;
           Formation e = new Formation(nom, date, description, lieu, prix) ;
           FormationDao edao = FormationDao.getInstance() ;
           edao.insertFormation(e);
           showAlertMessageInfo("Ajouter Evenement", "evenement ajoutée avec succèss");
           
           tfDescEvent.setText("");
           tfLieuEvent.setText("");
           tfNomEvent.setText("");
           dpDateEvent.setValue(null);
        }
    }

    @FXML
    private void selectedComboType(ActionEvent event) {
        String options = comboType.getValue() ;
        if(options.toLowerCase().equals("gratuit")){
            SpinnerValueFactory<Integer> values =  new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0) ;
            spPrix.setValueFactory(values);
          //  spPrix.setEditable(false);
            spPrix.setDisable(true);
        }
        else if(options.toLowerCase().equals("payant")){
            SpinnerValueFactory<Integer> values =  new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1) ;
            spPrix.setValueFactory(values);
            spPrix.setEditable(true);
            spPrix.setDisable(false);
        }
    }
    
    private void showAlertMessageError(String title,String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.show();
    }
    
    private void showAlertMessageInfo(String title,String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.show();
    }

    @FXML
    private void showEventList(Event event) {
        afficherListeEvenement("");
    }
    
    private void afficherListeEvenement(String critere){
        EvenementDao edao = EvenementDao.getInstance() ;
        ObservableList<Evenement> list = edao.getAllEvent(critere);
        tvEvent.setItems(list);
        colNomEvent.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDateEvent.setCellValueFactory(new PropertyValueFactory<>("date"));
        colLieuEvent.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        colDescEvent.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrixEvent.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }

    @FXML
    private Evenement getSelectedEvent(MouseEvent event) {
        Evenement e = tvEvent.getSelectionModel().getSelectedItem() ;
        if(e != null){
            tfNomEventM.setText(e.getNom());
            tfLieuEventM.setText(e.getLieu());
            tfDescEventM.setText(e.getDescription());
            dpDateEventM.setValue(e.getDate().toLocalDate());
            
            SpinnerValueFactory<Integer> values =  new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, e.getPrix()) ;
            spPrixEventM.setValueFactory(values);
            idEvent.setText(e.getId()+"");
        }
      return e ;
    }

    @FXML
    private void onclickModifierEvenement(ActionEvent event) {
        if(tfNomEventM.getText().isEmpty() || tfLieuEventM.getText().isEmpty() || tfDescEventM.getText().isEmpty()
                || dpDateEventM.getValue() == null )
        {
            showAlertMessageError("Modifier Evenement", "veuillez remplir tous les champs");
        }
        else{
           String nom = tfNomEventM.getText() ;
           String lieu = tfLieuEventM.getText() ;
           String description = tfDescEventM.getText() ;
           java.sql.Date date =java.sql.Date.valueOf(dpDateEventM.getValue());
           int prix = spPrixEventM.getValue() ;
           int id = Integer.parseInt(idEvent.getText()) ;
           Evenement e = new Evenement(id,nom, date, description, lieu, prix) ;
           EvenementDao edao = EvenementDao.getInstance() ;
           edao.updateEvent(e);
            afficherListeEvenement("");
           showAlertMessageInfo("Modifier Evenement", "evenement modifié avec succèss");
           
           tfDescEventM.setText("");
           tfLieuEventM.setText("");
           tfNomEventM.setText("");
           dpDateEventM.setValue(null);
           idEvent.setText("");
        }
    }

    @FXML
    private void onclickSupprimerEvenement(ActionEvent event) {
        if(tfNomEventM.getText().isEmpty() || tfLieuEventM.getText().isEmpty() || tfDescEventM.getText().isEmpty()
                || dpDateEventM.getValue() == null )
        {
            showAlertMessageError("Supprimer Evenement", "veuillez selectionner l'evenement à supprimer");
        }
        else{
           String nom = tfNomEventM.getText() ;
           String lieu = tfLieuEventM.getText() ;
           String description = tfDescEventM.getText() ;
           java.sql.Date date =java.sql.Date.valueOf(dpDateEventM.getValue());
           int prix = spPrixEventM.getValue() ;
           int id = Integer.parseInt(idEvent.getText()) ;
           Evenement e = new Evenement(id,nom, date, description, lieu, prix) ;
           EvenementDao edao = EvenementDao.getInstance() ;
           edao.deleteEvenement(e);
            afficherListeEvenement("");
           showAlertMessageInfo("Supprimer Evenement", "evenement supprimé avec succèss");
           
           tfDescEventM.setText("");
           tfLieuEventM.setText("");
           tfNomEventM.setText("");
           dpDateEventM.setValue(null);
           idEvent.setText("");
        }
    }

    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AccueilProjet.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);

         stage.setScene(
                 new Scene(loader.load())
         );
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();
        stage.show();
    }

    @FXML
    private void goToProjectMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GestionProjet.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);

         stage.setScene(
                 new Scene(loader.load())
         );
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();
        stage.show();
    }

    @FXML
    private void rechercherEvent(KeyEvent event) {
         afficherListeEvenement(tfSearchEvent.getText());
    }
    
}

