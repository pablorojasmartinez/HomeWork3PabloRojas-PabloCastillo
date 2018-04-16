package Interface;

import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Window extends Application implements Runnable {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private Pane pane;
    private Scene scene;
    private Canvas canvas;
    private Thread thread;
    int x;
    int y;
    private Object MessageBox;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Random Graphics");
        initComponents(primaryStage);
        primaryStage.show();
    }

    private void initComponents(Stage primaryStage) {
        this.pane = new Pane();
        this.scene = new Scene(this.pane, WIDTH, HEIGHT);
        this.canvas = new Canvas(WIDTH, HEIGHT);

        this.thread = new Thread(this);
        this.thread.start();

        this.pane.getChildren().add(this.canvas);
        primaryStage.setScene(this.scene);
    }

    private void myDraw(GraphicsContext gc) {
        Random rand = new Random();
        int x = 0;
        int y = 0;
        while (true) {
            int rand1 = rand.nextInt(5 - 1) + 1;
            try {
                gc.clearRect(0, 0, WIDTH, HEIGHT);
                gc.setFill(Color.AQUA);

                if (rand1 == 1) {
                    gc.fillRect((x), y, 50, 50);
                    x += 6;
                    if (x > WIDTH) {
                        x -= 100;
                    }
                } else if (rand1 == 2) {
                    gc.fillRect((x), y, 50, 50);
                    x -= 6;
                    if (x < 0) {
                        x += 100;

                    }

                } else if (rand1 == 3) {
                    gc.fillRect((x) + 1, y, 50, 50);
                    y += 6;
                    if (y > this.HEIGHT) {
                        y -= 100;
                    }

                } else {
                    gc.fillRect((x), y, 50, 50);
                    y -= 6;
                    if (y < 0) {
                        y += 100;

                    }
                }

                Thread.sleep(100);

            } catch (InterruptedException ex) {
            }
        }
    }

    @Override
    public void run() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        myDraw(gc);
    }

    public static void main(String[] args) {
        Application.launch(Window.class, args);
    }
}
