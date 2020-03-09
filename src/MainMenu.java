import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainMenu extends Application {

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 800;

    private Canvas algCanvas = new Canvas(WIDTH, HEIGHT);
    private GraphicsContext gc = algCanvas.getGraphicsContext2D();

    private int numRectangle = 128;

    private RectangleList rl1 = new RectangleList(numRectangle , gc, WIDTH, HEIGHT, (((WIDTH/2) / numRectangle)));
    private RectangleList rl2;

    private boolean running = true;

    private StackPane root = new StackPane();

    private Pane canvasPane = new Pane();

    private Scene scene = new Scene(root, WIDTH, HEIGHT);

    private VBox menu = new VBox(10);

    private double sortingSpeed = 1;

    private SortingAlgorithm sortingAlgorithm1;
    private SortingAlgorithm sortingAlgorithm2;

    private ComboBox<String> alg1ComboBox = new ComboBox<>();
    private ComboBox<String> alg2ComboBox = new ComboBox<>();

    @Override
    public void start(Stage stage) throws Exception {

        canvasPane.getChildren().add(algCanvas);

        root.getChildren().addAll(canvasPane, menu);

        menu.toFront();

        Button button = new Button("Start Algorithm");

        VBox comboVBox1 = new VBox();

        Label alg1Label = new Label("Algorithm 1:");

        VBox comboVBox2 = new VBox();

        Label alg2Label = new Label("Algorithm 2:");

        alg1ComboBox.getItems().addAll(
                "Bubble Sort",
                "Insertion Sort",
                "Merge Sort"
        );

        alg2ComboBox.getItems().addAll(
                "Bubble Sort",
                "Insertion Sort",
                "Merge Sort"
        );

        alg1ComboBox.setValue("Bubble Sort");
        alg2ComboBox.setValue("Insertion Sort");

        comboVBox1.getChildren().addAll(alg1Label, alg1ComboBox);
        comboVBox1.setAlignment(Pos.CENTER);
        comboVBox2.getChildren().addAll(alg2Label, alg2ComboBox);
        comboVBox2.setAlignment(Pos.CENTER);

        menu.getChildren().addAll(comboVBox1, comboVBox2, button);
        menu.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        menu.setAlignment(Pos.CENTER);
        button.setOnAction(e ->{

            switch(alg1ComboBox.getValue().toString()){
                case "Bubble Sort":
                    System.out.println("Hello");
                    sortingAlgorithm1 = new BubbleSort();
                    break;
                case "Insertion Sort":
                    sortingAlgorithm1 = new InsertionSort();
                    break;
                case "Merge Sort":
                    sortingAlgorithm1 = new MergeSort();
                    break;
            }

            switch (alg2ComboBox.getValue().toString()){
                case "Bubble Sort":
                    sortingAlgorithm2 = new BubbleSort();
                    break;
                case "Insertion Sort":
                    sortingAlgorithm2 = new InsertionSort();
                    break;
                case "Merge Sort":
                    sortingAlgorithm2 = new MergeSort();
                    break;
            }


            this.mainLoop(gc);
            menu.toBack();
            menu.setVisible(false);
        });



        rl1.genList();

        rl2 = rl1.mirrorToRight();

        draw();

        stage.setScene(scene);
        stage.setTitle("Algorithm Comparison");
        stage.setResizable(false);

        stage.show();
    }

    public void mainLoop(GraphicsContext gc){

        AnimationTimer animTimer = new AnimationTimer() {

            private static final int MILI_TO_NANO = 1000000;

            private long sleepInNs = (long) (sortingSpeed * MILI_TO_NANO);

            private long previousTime = 0;

            @Override
            public void handle(long timeInNano) {

                if ((timeInNano - previousTime) < sleepInNs)
                    return;

                draw();

                sortingAlgorithm1.sort(rl1);
                sortingAlgorithm2.sort(rl2);



                previousTime = timeInNano;

            }
        };
        animTimer.start();
    }

    public void draw(){
        gc.clearRect(0,0, WIDTH, HEIGHT);

        //Dividing the window into two regions
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.WHITE);
        gc.strokeRect(0, 0, WIDTH, HEIGHT);

        gc.strokeLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);

        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Arial", 30));
        gc.fillText("Algorithm 1:\n"+alg1ComboBox.getValue().toString(), 50, 50);
        gc.fillText("Algorithm 2:\n"+alg2ComboBox.getValue().toString(), (WIDTH / 2) + 50, 50);

        rl1.drawList();
        rl2.drawList();
    }
}
