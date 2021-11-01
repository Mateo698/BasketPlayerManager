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
import javafx.scene.control.TextField;
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
		loadData();
	}
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


	public void initProgram() throws IOException{
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
		try {
			manager.importData(f.getAbsolutePath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Import players");
		alert.setHeaderText("Players have been loaded successfully");
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
	}

	@FXML
	void toSearchPlayer(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchPlayer-window.fxml"));
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
	void addPlayer(ActionEvent event) {
		manager.addPlayer(Integer.parseInt(playerId.getText()), playerName.getText(), Integer.parseInt(playerAge.getText()), playerTeam.getText(),Double.parseDouble(playerPoints.getText()), Double.parseDouble(playerRebounds.getText()),Double.parseDouble(playerAssists.getText()), Double.parseDouble(playerSteals.getText()),Double.parseDouble(playerBlocks.getText()));
	}
	@FXML
	void exit(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	void showDeletedList(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("deletedPlayerList-window.fxml"));
		Parent userView;
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
		int selection=0;
		searchComboBox.getItems().addAll("id", "name", "age", "team");
		if(searchComboBox.getValue().equals("id")) {
			selection = 1;
		}else if(searchComboBox.getValue().equals("name")) {
			selection = 2;
		}else if(searchComboBox.getValue().equals("age")) {
			selection = 3;
		}else if(searchComboBox.getValue().equals("team")) {
			selection = 4;
		}


		switch(selection){
		case 1:
			manager.searchId(Integer.parseInt(playerToSearch.getText()));
			break;
		case 2:
			manager.searchName(playerToSearch.getText());
			break;
		case 3:
			manager.searchAge(Integer.parseInt(playerToSearch.getText()));
			break;
		case 4: 
			manager.searchTeam(playerToSearch.getText());
			break;
		default:
			break;
		}
	}

	@FXML
	void quickSearch(ActionEvent event) {
		int selection=0;
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


