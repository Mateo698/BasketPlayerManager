package threads;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.ProgressBar;
import ui.PlayerManagerGUI;
public class Loading extends Thread {
    private PlayerManagerGUI program;
    private ProgressBar bar;
    private double percentage;
    public Loading(PlayerManagerGUI td,ProgressBar bar) {
        this.bar = bar;
        percentage=0;
        program= td;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            percentage+=0.05;
            Platform.runLater(() -> {
                bar.setProgress(percentage);                
            });
            try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        Platform.runLater(() -> { 
            program.mainWindowStart(new ActionEvent());         
        });
    }
}