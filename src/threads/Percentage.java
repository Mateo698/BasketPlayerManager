package threads;
import javafx.application.Platform;
import javafx.scene.control.ProgressIndicator;
public class Percentage extends Thread{
    private ProgressIndicator progress;
    private double percentage;
    public Percentage(ProgressIndicator pi) {
        progress=pi;
        percentage = 0;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            percentage+=0.05;
            Platform.runLater(() -> { 
                progress.setProgress(percentage);      
                    
            });
            try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
}