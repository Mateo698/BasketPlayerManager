package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Game;
import model.Manager;
import model.Player;
import threads.Loading;
import threads.Percentage;


public class PlayerManagerGUI {
	private static ArrayList<Player> players;
	private static final String SAVE_PLAYERS_PATH="data/players.ap2";
	private static Manager manager;
	public PlayerManagerGUI() {
		loadData();
	}

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

		    }

		    @FXML
		    void showDeletedList(ActionEvent event) {

		    }		   

		    @FXML
		    void restorePlayer(ActionEvent event) {

		    }
		    
		    @FXML
		    void deletePlayer(ActionEvent event) {
		    	manager.removePlayer();
		    }

		    @FXML
		    void playerToSearch(ActionEvent event) {

		    }

		    @FXML
		    void searchPlayer(ActionEvent event) {

		    }

		
	} 
	

