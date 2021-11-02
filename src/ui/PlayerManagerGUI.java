package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Manager;
import model.Player;
import threads.Loading;
import threads.Percentage;


public class PlayerManagerGUI {
	private static ArrayList<Player> players;
	private static final String SAVE_PLAYERS_PATH="data/players.ap2";
	private static Manager manager;
	private static ArrayList<Player> deletedPlayers;
	public PlayerManagerGUI() {
		manager = new Manager();
		loadData();
	}
	private Stage s;
	
	@FXML
    private Label timeLabel;
	
	@FXML
    private TableView<Player> playerTable;

    @FXML
    private TableColumn<Player, Integer> idCol;

    @FXML
    private TableColumn<Player, String> nameCol;

    @FXML
    private TableColumn<Player, Integer> ageCol;

    @FXML
    private TableColumn<Player, String> teamCol;

    @FXML
    private TableColumn<Player, Double> pointCol;

    @FXML
    private TableColumn<Player, Double> rebCol;

    @FXML
    private TableColumn<Player, Double> assCol;

    @FXML
    private TableColumn<Player, Double> stlCol;

    @FXML
    private TableColumn<Player, Double> blockCol;

    @FXML
    private Label noFoundLabel;

	@FXML
	private TextField playerToSearch;
	@FXML
	private ComboBox<String> searchComboBox;
	@FXML
	private ComboBox<String> quicksearchComboBox;
	@FXML
	private ProgressBar bar;
	@FXML
	private ProgressIndicator percentage;
	@FXML
	private Pane mainPane;
	@FXML
	private GridPane mainGrid;
	@FXML
	private TextField playerName;

	@FXML
	private TextField playerId;

	@FXML
	private TextField playerPoints;

	@FXML
	private TextField playerAge;

	@FXML
	private TextField playerTeam;

	@FXML
	private TextField playerRebounds;

	@FXML
	private Label name;

	@FXML
	private Label age;

	@FXML
	private Label id;

	@FXML
	private Label team;

	@FXML
	private Label points;

	@FXML
	private Label rebounds;

	@FXML
	private TextField playerAssists;

	@FXML
	private TextField playerSteals;

	@FXML
	private Label assists;

	@FXML
	private Label steals;

	@FXML
	private TextField playerBlocks;

	@FXML
	private Label blocks;

	@FXML
	private ListView<Player> lvDeletedPlayers;

	@FXML
	private TextField playerToDelete;


	@FXML
	private ListView<Player> lvSearchedPlayers;

	@FXML
	private GridPane loadingGrid;

	@FXML
	private TextField quickSearchData;
	
	@FXML
    void foundBttn(ActionEvent event) {
		s.setHeight(600);
		s.setWidth(600);
		mainWindowStart(event);
    }

	public void initProgram(Stage s) throws IOException{
		this.s = s;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/loading.fxml"));
		fxmlLoader.setController(this);
		Parent userView = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.getChildren().add(userView);
		Loading loading = new Loading(this, bar);
		Percentage pi = new Percentage(percentage);
		loading.start();
		pi.start();


	}
	@SuppressWarnings("unchecked")
	public static void loadData(){
		File playerList = new File(SAVE_PLAYERS_PATH);
		if (playerList.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(playerList));
				players = (ArrayList<Player>)ois.readObject();
				ois.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				players = new ArrayList<Player>();
			}
		}else {
			players = new ArrayList<Player>();
		}
	}
	public static void saveData(){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PLAYERS_PATH));
			oos.writeObject(players);
			oos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void mainWindowStart(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-window.fxml"));
		fxmlLoader.setController(this);
		Parent userView;
		try {
			userView = fxmlLoader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add(userView);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void toAddPlayer(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addPlayer-window.fxml"));
		Parent userView;
		fxmlLoader.setController(this);
		try {
			userView = fxmlLoader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add(userView);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void toDeletePlayers(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("deletePlayer-window.fxml"));
		Parent userView;
		fxmlLoader.setController(this);
		try {
			userView = fxmlLoader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add(userView);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

 	@FXML
	void loadPlayers(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.setTitle("Select the base products list");
		File f = fc.showOpenDialog(mainPane.getScene().getWindow());
		if(f!=null) {
			try {
				manager.importData(f.getAbsolutePath());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Import players");
				alert.setHeaderText("Players have been loaded successfully");
				alert.showAndWait();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void toSearchPlayer(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchPlayer-window.fxml"));
		Parent userView;
		fxmlLoader.setController(this);
		try {
			userView = fxmlLoader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add(userView);
			ObservableList<String> list = FXCollections.observableArrayList();
			list.add("Id");
			list.add("Name");
			list.add("Age");
			list.add("Team");
	        searchComboBox.setItems(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@FXML
	void addPlayer(ActionEvent event) {
		if(checkAddFields()) {
			manager.addPlayer(Integer.parseInt(playerId.getText()), playerName.getText(), Integer.parseInt(playerAge.getText()), playerTeam.getText(),Double.parseDouble(playerPoints.getText()), Double.parseDouble(playerRebounds.getText()),Double.parseDouble(playerAssists.getText()), Double.parseDouble(playerSteals.getText()),Double.parseDouble(playerBlocks.getText()));
			Alert alertWarnings = new Alert(AlertType.INFORMATION);
	    	alertWarnings.setTitle("Player added");
			alertWarnings.setHeaderText("Player added succesfully");
			alertWarnings.show();
			mainWindowStart(event);
		}else {
			Alert alertWarnings = new Alert(AlertType.WARNING);
	    	alertWarnings.setTitle("Error");
			alertWarnings.setHeaderText("Empty fields");
			alertWarnings.setContentText("Please check the info.");
			alertWarnings.show();
		}
		
	}
	
	private boolean checkAddFields() {
		if(playerId.getText() == null || playerId.getText().isEmpty() || playerName.getText() == null || playerName.getText().isEmpty() || playerAge.getText() == null || playerAge.getText().isEmpty() || playerTeam.getText() == null || playerTeam.getText().isEmpty() || playerPoints.getText() == null || playerPoints.getText().isEmpty() || playerRebounds.getText() == null || playerRebounds.getText().isEmpty() || playerAssists.getText() == null || playerAssists.getText().isEmpty() || playerSteals.getText() == null || playerSteals.getText().isEmpty() || playerBlocks.getText() == null || playerBlocks.getText().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	@FXML
	void exit(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	void showDeletedList(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("deletedPlayerList-window.fxml"));
		Parent userView;
		fxmlLoader.setController(this);
		try {
			userView = fxmlLoader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add(userView);
			lvDeletedPlayers.setItems(FXCollections.observableArrayList(deletedPlayers));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}		   

	@FXML
	void restorePlayer(ActionEvent event) {
		Player playerToRestore = lvDeletedPlayers.getSelectionModel().getSelectedItem();
		manager.addPlayer(playerToRestore);

	}

	@FXML
	void deletePlayer(ActionEvent event) {
		Player playerTemp=manager.searchId(Integer.parseInt(playerToDelete.getText()));
		deletedPlayers.add(playerTemp);
		manager.removePlayer(manager.searchId(Integer.parseInt(playerToDelete.getText())));

		if(manager.searchId(Integer.parseInt(playerToDelete.getText()))!=null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Delete players");
			alert.setHeaderText("Player "+manager.searchId(Integer.parseInt(playerToDelete.getText()))+" has been deleted successfully");
			alert.showAndWait();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-window.fxml"));
			fxmlLoader.setController(this);
			Parent userView;
			try {
				userView = fxmlLoader.load();
				mainPane.getChildren().clear();
				mainPane.getChildren().add(userView);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Delete players");
			alert.setHeaderText("Player not found");
			alert.showAndWait();
		}
	}


	@FXML
	void searchPlayer(ActionEvent event) {
		if(searchComboBox.getValue() == null) {
			Alert alertWarnings = new Alert(AlertType.WARNING);
	    	alertWarnings.setTitle("Error");
			alertWarnings.setHeaderText("Empty field");
			alertWarnings.setContentText("Please fill all the info.");
			alertWarnings.show();
		}else {
			int selection=0;
			if(searchComboBox.getValue().equals("Id")) {
				selection = 1;
			}else if(searchComboBox.getValue().equals("Name")) {
				selection = 2;
			}else if(searchComboBox.getValue().equals("Age")) {
				selection = 3;
			}else if(searchComboBox.getValue().equals("Team")) {
				selection = 4;
			}

			if(playerToSearch.getText() == null || playerToSearch.getText().isEmpty()) {
				Alert alertWarnings = new Alert(AlertType.WARNING);
		    	alertWarnings.setTitle("Error");
				alertWarnings.setHeaderText("Empty field");
				alertWarnings.setContentText("Please fill all the info.");
				alertWarnings.show();
			}else {
				double initTime = System.nanoTime();
				double endTiem = 0;
				Player found = new Player(0);
				switch(selection){
				case 1:
					found = manager.searchId(Integer.parseInt(playerToSearch.getText()));
					endTiem = System.nanoTime();
					break;
				case 2:
					found = manager.searchName(playerToSearch.getText());
					endTiem = System.nanoTime();
					break;
				case 3:
					found = manager.searchAge(Integer.parseInt(playerToSearch.getText()));
					endTiem = System.nanoTime();
					break;
				case 4: 
					found = manager.searchTeam(playerToSearch.getText());
					endTiem = System.nanoTime();
					break;
				default:
					break;
				}
				double time = endTiem - initTime;
				if(found == null) {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("info-player.fxml"));
					Parent userView;
					fxmlLoader.setController(this);
					try {
						userView = fxmlLoader.load();
						mainPane.getChildren().clear();
						mainPane.getChildren().add(userView);
						s.setHeight(330);
						s.setWidth(855);
						noFoundLabel.setVisible(true);
						timeLabel.setText(time+"");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					ArrayList<Player> l = new ArrayList<Player>();
					l.add(found);
					ObservableList<Player> list = FXCollections.observableList(l);
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("info-player.fxml"));
					Parent userView;
					fxmlLoader.setController(this);
					try {
						userView = fxmlLoader.load();
						mainPane.getChildren().clear();
						mainPane.getChildren().add(userView);
						playerTable.setVisible(true);
						s.setHeight(330);
						s.setWidth(855);
						InitTable();
						timeLabel.setText(time+"");
						playerTable.setItems(list);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		
	}
	
	public void InitTable() {
		idCol.setCellValueFactory(new PropertyValueFactory<Player,Integer>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
		ageCol.setCellValueFactory(new PropertyValueFactory<Player,Integer>("age"));
		teamCol.setCellValueFactory(new PropertyValueFactory<Player,String>("team"));
		pointCol.setCellValueFactory(new PropertyValueFactory<Player,Double>("pointsPerMatch"));
		rebCol.setCellValueFactory(new PropertyValueFactory<Player,Double>("reboundPerMatch"));
		assCol.setCellValueFactory(new PropertyValueFactory<Player,Double>("assistPerMatch"));
		stlCol.setCellValueFactory(new PropertyValueFactory<Player,Double>("stealPerMatch"));
		blockCol.setCellValueFactory(new PropertyValueFactory<Player,Double>("blockPerMatch"));
	}

	@FXML
	void quickSearch(ActionEvent event) {
		int selection=0;
		quicksearchComboBox.getItems().removeAll(quicksearchComboBox.getItems());
		quicksearchComboBox.getItems().addAll("points", "rebounds", "assists", "steals", "blocks");
		if(quicksearchComboBox.getValue().equals("points")) {
			selection = 1;
		}else if(quicksearchComboBox.getValue().equals("rebounds")) {
			selection = 2;
		}else if(quicksearchComboBox.getValue().equals("assists")) {
			selection = 3;
		}else if(quicksearchComboBox.getValue().equals("steals")) {
			selection = 4;
		}else if(quicksearchComboBox.getValue().equals("blocks")) {
			selection = 5;
		}
		switch(selection){
		case 1:
			manager.quickSearch(selection,Double.parseDouble(quickSearchData.getText()));
			break;
		case 2:
			manager.quickSearch(selection,Double.parseDouble(quickSearchData.getText()));
			break;
		case 3:
			manager.quickSearch(selection,Double.parseDouble(quickSearchData.getText()));
			break;
		case 4: 
			manager.quickSearch(selection,Double.parseDouble(quickSearchData.getText()));
			break;
		case 5:
			manager.quickSearch(selection,Double.parseDouble(quickSearchData.getText()));
			break;
		default:
			break;

		}
	}


} 


