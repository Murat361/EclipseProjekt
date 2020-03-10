package studiengaenge;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.StudentDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Ergebnisse;
import model.Student;
import model.Studiengaenge_Faecher;

public class StudiengaengeController implements Initializable{
	StudentDAO studentDAO;
	Studiengaenge_Faecher studiengaenge;
	@FXML
    private TableView<Studiengaenge_Faecher> tableStudiengaenge;
	
    @FXML
    private TableColumn<Studiengaenge_Faecher, String> spalteStudiengang;

    @FXML
    private TableColumn<Studiengaenge_Faecher, String> spalteFach;
    
    @FXML
    private Pane paneLeftBar;
    
    @FXML
    private ImageView imageViewStudents;
    @FXML
    private ImageView imageViewNotes;
    @FXML
    private ImageView imageViewCourse;
    @FXML
    private ImageView imageViewTeacher;
    @FXML
    private ImageView imageViewErgebnisse;
    
    @FXML
    private HBox hboxDeleteUpdate;
    
    @FXML
    private ImageView imageViewUpdate;

    @FXML
    private ImageView imageViewDelete;
    
    @FXML
    private Pane paneBottom;
    
    @FXML
    private HBox hboxBottom;
    
    @FXML
    private VBox panes;
    
    @FXML
    void changeColumns(TableColumn.CellEditEvent<Student, String> edit) {
    	studiengaenge = tableStudiengaenge.getSelectionModel().getSelectedItem();
    	System.out.println(studiengaenge.getId());
    	System.out.println(edit.getNewValue());
    }
    
    
    @FXML
    void mouseClickedHandler(MouseEvent event) {
    	if(event.getSource().equals(imageViewStudents)) {
    		openViews("/students/studentsView.fxml");
    	}else if(event.getSource().equals(imageViewNotes)) {
    		openViews("/notes/notesView.fxml");
    	}else if(event.getSource().equals(imageViewCourse)) {
    		openViews("/studiengaenge/studiengaengeView.fxml");
    	}else if(event.getSource().equals(imageViewTeacher)) {
    		openViews("/dozenten/dozentenView.fxml");
    	}else if(event.getSource().equals(imageViewErgebnisse)) {
    		openViews("/ergebnisse/ergebnisseView.fxml");
    	}else if(event.getSource().equals(imageViewDelete)) {
    		//TODO: DELETE
    	}else if(event.getSource().equals(imageViewUpdate)) {
    		//TODO: UPDATE
    	}
    }
    
    public void openViews(String fxml) {
    	try {
			AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(fxml));
			main.Main.getStage().setScene(new Scene(anchorPane));
			main.Main.getStage().getScene().getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
			main.Main.getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		studentDAO = new StudentDAO();
		
		paneLeftBar.prefWidthProperty().bind(main.Main.getStage().widthProperty().divide(0.25));
		paneBottom.prefWidthProperty().bind(main.Main.getStage().widthProperty().divide(1));
		paneBottom.prefHeightProperty().bind(main.Main.getStage().heightProperty().divide(0.1166666));
		hboxBottom.prefWidthProperty().bind(paneBottom.widthProperty().divide(1));
		hboxBottom.prefHeightProperty().bind(paneBottom.heightProperty().divide(1));
		spalteStudiengang.prefWidthProperty().bind(tableStudiengaenge.widthProperty().divide(2));
		spalteFach.prefWidthProperty().bind(tableStudiengaenge.widthProperty().divide(2));
		
		spalteStudiengang.setCellValueFactory(new PropertyValueFactory<Studiengaenge_Faecher, String>("studiengang"));
		spalteFach.setCellValueFactory(new PropertyValueFactory<Studiengaenge_Faecher, String>("fach"));
		
		
		try {
			ObservableList<Studiengaenge_Faecher> list = studentDAO.getStudiengaenge_Faecher();
			tableStudiengaenge.setItems(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tableStudiengaenge.setEditable(true);
		spalteStudiengang.setCellFactory(TextFieldTableCell.forTableColumn());//zeichnet ein TextField in die Zeile, so dass man es editieren kann
		spalteFach.setCellFactory(TextFieldTableCell.forTableColumn());//zeichnet ein TextField in die Zeile, so dass man es editieren kann
		
		tableStudiengaenge.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Studiengaenge_Faecher>() {

			@Override
			public void changed(ObservableValue<? extends Studiengaenge_Faecher> arg0, Studiengaenge_Faecher arg1, Studiengaenge_Faecher arg2) {
				studiengaenge = arg2;
				System.out.println(studiengaenge.getId());
			}
		});
		
				
	}

}