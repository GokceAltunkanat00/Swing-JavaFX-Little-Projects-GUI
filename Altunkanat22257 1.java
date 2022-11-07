import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("SnowmanFx");
        Pane p = new Pane();
        Scene scene = new Scene(p,400,400,Color.BLUE);

        double width = p.getWidth(), height = p.getHeight();
        double r1 = height/12 , r2 = height /6, r3 = height /4;
        Circle top = new Circle(width/2,r1,r1, Color.WHITE);
        Circle mid = new Circle(width/2,r1*2+r2,r2,Color.WHITE);
        Circle bot = new Circle(width/2,r1*2+r2*2+r3,r3,Color.WHITE);

        top.centerXProperty().bind(p.widthProperty().divide(2));
        mid.centerXProperty().bind(p.widthProperty().divide(2));
        bot.centerXProperty().bind(p.widthProperty().divide(2));

        top.centerYProperty().bind(p.heightProperty().divide(12));
        mid.centerYProperty().bind(p.heightProperty().divide(3));
        bot.centerYProperty().bind(p.heightProperty().divide(1.333));

        top.radiusProperty().bind(Bindings.min(p.widthProperty(), p.heightProperty().divide(12)));
        mid.radiusProperty().bind(Bindings.min(p.widthProperty(), p.heightProperty().divide(6)));
        bot.radiusProperty().bind(Bindings.min(p.widthProperty(), p.heightProperty().divide(4)));

        p.getChildren().addAll(top, mid, bot);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}