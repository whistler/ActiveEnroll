package com.mss.tuess.start;

import com.mss.tuess.entity.Term;
import com.mss.tuess.util.DatabaseConnector;
import com.mss.tuess.util.State;
import com.mss.tuess.util.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class TUESS extends Application {
    
    private static TUESS instance;
    public TUESS() {
        instance = this;
    }

    public static TUESS getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        DatabaseConnector.Connect();
        State.setCurrentTerm(Term.getCurrentTerm());
        ViewManager.setStage(stage);
        ViewManager.changeScene("/com/mss/tuess/views/Login.fxml");
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        launch(args);
        
    }

}