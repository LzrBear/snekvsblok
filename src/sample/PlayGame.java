//package Snek;
//
//import javafx.animation.PathTransition;
//import javafx.application.Application;
//import javafx.geometry.Orientation;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.SplitPane;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.*;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//import java.util.Random;
//
//public class PlayGame extends Application{
//
//    private double windowWidth;
//    private double windowHeight;
//    private double boxWidth = 80;
//    private double boxHeight = 50;
//
//    private double gamePaneWidth;
//    private double gamePaneHeight;
//    private double optionsPaneWidth;
//    private double optionsPaneHeight;
//
//    private StackPane[] boxes;
//    private StackPane[] boxesAlternate;
//    private StackPane[] balls;
//
//    private GridPane gridPaneBoard;
//
//    private int numberOfBoxes = 8;
//    private int numberOfBalls;
//
//    private boolean alternateFall = true;
//
//    private DestroyBlock createBlok(){
//        DestroyBlock rect = new DestroyBlock(0, -boxHeight, boxWidth, boxHeight);
//
//        Random rand = new Random();
//        int selectColor = rand.nextInt(10);
//        switch (selectColor){
//            case 0: rect.setFill(Color.BLUEVIOLET);
//                break;
//            case 1: rect.setFill(Color.AQUA);
//                break;
//            case 2: rect.setFill(Color.TOMATO);
//                break;
//            case 3: rect.setFill(Color.PINK);
//                break;
//            case 4: rect.setFill(Color.ORANGE);
//                break;
//            case 5: rect.setFill(Color.RED);
//                break;
//            case 6: rect.setFill(Color.MISTYROSE);
//                break;
//            case 7: rect.setFill(Color.YELLOW);
//                break;
//            case 8: rect.setFill(Color.MAGENTA);
//                break;
//            case 9: rect.setFill(Color.MINTCREAM);
//                break;
//        }
//        rect.setStroke(Color.BLACK);
//        return rect;
//    }
//
//    private void createBoxes(){
//        Random rand = new Random();
//
//        if(this.alternateFall){
//
//            for (int x = 0; x < 2*numberOfBoxes; x++) {
//                DestroyBlock rect = createBlok();
//
//                int inactiveBlock = rand.nextInt(10);
//                if(inactiveBlock == 0){
//                    continue;
//                }
//
//                Text text =  new Text(Integer.toString(rect.getBoxValue()));
//                text.setStyle("-fx-font-size: 18px;style: \"-fx-font-weight: bold\";");
//                text.setY(-boxHeight);
//
//                StackPane stackPane = new StackPane();
//                stackPane.getChildren().addAll(rect, text);
//                if(x < numberOfBoxes){
//                    boxes[x] = stackPane;
//                    boxes[x].setTranslateY(-boxHeight);
////                    boxes[x].setLayoutY(-boxHeight);
//                    gridPaneBoard.add(boxes[x], x, 0);
//                }
//                else{
//                    boxesAlternate[x-numberOfBoxes] = stackPane;
//                    boxesAlternate[x-numberOfBoxes].setTranslateY(-boxHeight);
////                    boxesAlternate[x-numberOfBoxes].setLayoutY(-boxHeight);
//                    gridPaneBoard.add(boxesAlternate[x-numberOfBoxes], x-numberOfBoxes, 0);
//                }
//            }
//        }
//        else {
//            for (int x = 0; x < numberOfBoxes; x++) {
//                DestroyBlock rect = createBlok();
//
//                int inactiveBlock = rand.nextInt(25);
//                if(inactiveBlock == 0){
//                    continue;
//                }
//
//                Text text =  new Text(Integer.toString(rect.getBoxValue()));
//                text.setStyle("-fx-font-size: 18px;style: \"-fx-font-weight: bold\";");
//                text.setY(-boxHeight);
//
//                StackPane stackPane = new StackPane();
//                stackPane.getChildren().addAll(rect, text);
//                boxes[x] = stackPane;
//                boxes[x].setTranslateY(-boxHeight);
////                boxes[x].setLayoutY(-boxHeight);
//                gridPaneBoard.add(boxes[x], x, 0);
//            }
//        }
//
//    }
//
//    private void createAnimation(){
//        Path [] pathBoxes = new Path[boxes.length];
//        for(int i=0; i<pathBoxes.length; i++){
//            if(boxes[i] == null)
//                continue;
//
//            pathBoxes[i] = new Path();
//
//            pathBoxes[i].getElements().add(new MoveTo(boxes[i].getLayoutX() + boxWidth/2, boxes[i].getTranslateY()));
//            pathBoxes[i].getElements().add(new LineTo(boxes[i].getLayoutX() + boxWidth/2, gamePaneHeight + boxHeight/2));
//            PathTransition pathTransition = new PathTransition();
//            pathTransition.setDuration(Duration.millis(3000));
//            pathTransition.setPath(pathBoxes[i]);
//            pathTransition.setNode(boxes[i]);
//            pathTransition.play();
//            if(i == 5)
//                pathTransition.setOnFinished(e -> nextCycle());
//        }
//        if(alternateFall){
//            alternateFall = false;
//            Path [] pathBoxesAlternate = new Path[boxesAlternate.length];
//
//            for(int i=0; i<pathBoxesAlternate.length; i++){
//
//                if(boxesAlternate[i] == null)
//                    continue;
//
//                pathBoxesAlternate[i] = new Path();
//
//                pathBoxesAlternate[i].getElements().add(new MoveTo(boxesAlternate[i].getLayoutX() + boxWidth/2, boxesAlternate[i].getLayoutY() - boxHeight));
//                pathBoxesAlternate[i].getElements().add(new LineTo(boxesAlternate[i].getLayoutX() + boxWidth/2, gamePaneHeight + boxHeight/2));
//                PathTransition pathTransition = new PathTransition();
//                pathTransition.setDuration(Duration.millis(3000));
//                pathTransition.setPath(pathBoxesAlternate[i]);
//                pathTransition.setNode(boxesAlternate[i]);
//                pathTransition.setDelay(new Duration(1500));
//                pathTransition.play();
//            }
//        }
//    }
//
//    private void boxesFall(){
//        createAnimation();
//    }
//
//    private void createBalls(){
//        Random rand = new Random();
//
//        for(int i=0; i<numberOfBalls; i++){
//            int x = rand.nextInt(numberOfBoxes);
//            int y = 0;
//            while(y == 0){
//                y = rand.nextInt(10);
//                y *= -1;
//            }
//
//            StackPane ball = new StackPane();
//            Ball rawBall = new Ball(10);
//            rawBall.setFill(Color.PINK);
//            Text text = new Text(Integer.toString(rand.nextInt(10)));
//            ball.getChildren().addAll(rawBall, text);
//
//            gridPaneBoard.add(ball, x, 0);
//            balls[i] = ball;
//
//            balls[i].setTranslateY(boxHeight * (y - boxHeight));
//        }
//    }
//
//    private void ballsFall(){
//        Path [] pathBalls = new Path[balls.length];
//        Random rand = new Random();
//
//        for(int i=0; i<pathBalls.length; i++){
//
//            if(boxes[i] == null)
//                continue;
//            pathBalls[i] = new Path();
//
//            pathBalls[i].getElements().add(new MoveTo(balls[i].getLayoutX() + boxWidth/2, balls[i].getLayoutY() - boxHeight));
//            pathBalls[i].getElements().add(new LineTo(balls[i].getLayoutX() + boxWidth/2, gamePaneHeight + boxHeight));
//            PathTransition pathTransition = new PathTransition();
//            pathTransition.setDuration(Duration.millis(3000));
//            pathTransition.setPath(pathBalls[i]);
//            pathTransition.setNode(balls[i]);
//            pathTransition.setDelay(new Duration(700 + rand.nextInt(500)));
//            pathTransition.play();
////            System.out.println(balls[i].getTranslateY() + "  " + boxes[i].getTranslateY());
//        }
//    }
//
//    private void nextCycle(){
//        boxes = new StackPane[numberOfBoxes];
//        boxesAlternate = new StackPane[numberOfBoxes];
//
//        Random rand = new Random();
//        int alternate = rand.nextInt(5);
//        if(alternate == 0){
//            this.alternateFall = true;
//        }
//
//        createBoxes();
//        boxesFall();
//
//        numberOfBalls = 1 + rand.nextInt(numberOfBoxes/2);
//        balls = new StackPane[numberOfBalls];
////
//        createBalls();
//        ballsFall();
//    }
//
//    @Override
//    public void start(Stage window) {
//
//        gridPaneBoard = new GridPane();
//        HBox hBox = new HBox();
//        hBox.setTranslateX(20);
//        hBox.setTranslateY(20);
//        hBox.setSpacing(40);
//        window.setResizable(false);
//        Scene scene = new Scene(new Group(hBox));
//        window.show();
//        window.setMinHeight(windowHeight);
//        window.setMinWidth(windowWidth);
//        window.setScene(scene);
//        window.setMaximized(true);
//        scene.setFill(Color.DARKGREY);
//        window.setTitle("Snek");
//
//        windowWidth = window.getWidth();
//        windowHeight = window.getHeight();
//
//        gamePaneWidth = 0.6 * windowWidth - 40;
//        gamePaneHeight = windowHeight - 60;
//        optionsPaneWidth = 0.4 * windowWidth - 40;
//        optionsPaneHeight = windowHeight - 60;
//
////        numberOfBoxes = (int) Math.floor(gamePaneWidth/boxWidth);
//        boxWidth = Math.floor(gamePaneWidth/numberOfBoxes);
//
//        System.out.println(gamePaneHeight + " " + gamePaneWidth);
//
//        SplitPane gamePane = new SplitPane();
//        gamePane.setOrientation(Orientation.VERTICAL);
//        gamePane.setPrefSize(gamePaneWidth, gamePaneHeight);
//        hBox.getChildren().add(gamePane);
//        gamePane.getItems().add(gridPaneBoard);
//
////        gridPaneBoard.setBackground(Background.EMPTY);
//        gamePane.setStyle("-fx-background-color: #000000");
//
//        SplitPane optionsPane = new SplitPane();
//        optionsPane.setOrientation(Orientation.VERTICAL);
//        optionsPane.setPrefSize(optionsPaneWidth, optionsPaneHeight);
//        hBox.getChildren().add(optionsPane);
//
//
//
//        nextCycle();
//    }
//
//
////    public static void main(String[] args) {
////        launch(args);
////    }
//}