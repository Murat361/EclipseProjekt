package studiengaenge;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.StudentDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Studiengaenge_ID;

public class Studiengaenge_IdsController implements Initializable{
	StudentDAO studentDao;
    @FXML
    private TableView<Studiengaenge_ID> tableViewIds;

    @FXML
    private TableColumn<Studiengaenge_ID, String> spalteID;

    @FXML
    private TableColumn<Studiengaenge_ID, String> spalteStudiengang;

    @FXML
    private HBox hboxAddBottom;

    @FXML
    private ImageView ImageViewCancel;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		studentDao = new StudentDAO();
		spalteID.prefWidthProperty().bind(tableViewIds.widthProperty().divide(2));
		spalteStudiengang.prefWidthProperty().bind(tableViewIds.widthProperty().divide(2));
		
		
		spalteID.setCellValueFactory(new PropertyValueFactory<Studiengaenge_ID, String>("id"));
		spalteStudiengang.setCellValueFactory(new PropertyValueFactory<Studiengaenge_ID, String>("studiengang"));
		
		try {
			ObservableList<Studiengaenge_ID> list = studentDao.getAllStudiengaenge_Ids();
			tableViewIds.setItems(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
