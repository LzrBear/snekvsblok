package snek;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.Scene;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {

    private PlayGame game;


    final String IDLE_BUTTON_STYLE = "-fx-padding: 8 15 15 15;"+
        "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;"+
        "-fx-background-radius: 8;"+
        "-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%), #9d4024, #d86e3a, radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);"+
        "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );"+
        "-fx-font-weight: bold;"+
        "-fx-font-size: 1.1em;" ;

    final String HOVEROVER_BUTTON_STYLE ="-fx-padding: 8 15 15 15;"+
            "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;"+
            "-fx-background-radius: 8;"+
            " -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%), #9d4024, #d86e3a,radial-gradient(center 50% 50%, radius 100%, #ea7f4b, #c54e2c);"+
            "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );"+
            "-fx-font-weight: bold;"+
            "-fx-font-size: 1.1em;" ;
    final String PRESSED_BUTTON_STYLE = "-fx-padding: 10 15 13 15;"+
            "-fx-background-insets: 2 0 0 0,2 0 3 0, 2 0 4 0, 2 0 5 0;"+
            "-fx-background-radius: 8;"+
            " -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%), #9d4024, #d86e3a,radial-gradient(center 50% 50%, radius 100%, #ea7f4b, #c54e2c);"+
            "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );"+
            "-fx-font-weight: bold;"+
            "-fx-font-size: 1.1em;" ;

    final String HOVEROVER_BUTTON_STYLE_QUIT = "-fx-padding: 8 15 15 15;"+
            "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;"+
            "-fx-background-radius: 8;"+
            "-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%), #9d4024, #a22312, radial-gradient(center 50% 50%, radius 100%, #a22312, #a21212);"+
            "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );"+
            "-fx-font-weight: bold;"+
            "-fx-font-size: 1.1em;" ;

    final String IDLE_BUTTON_STYLE_QUIT ="-fx-padding: 8 15 15 15;"+
            "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;"+
            "-fx-background-radius: 8;"+
            " -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%), #9d4024, #d86e3a,radial-gradient(center 50% 50%, radius 100%, #ea7f4b, #c54e2c);"+
            "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );"+
            "-fx-font-weight: bold;"+
            "-fx-font-size: 1.1em;" ;
    final String PRESSED_BUTTON_STYLE_QUIT = "-fx-padding: 10 15 13 15;"+
            "-fx-background-insets: 2 0 0 0,2 0 3 0, 2 0 4 0, 2 0 5 0;"+
            "-fx-background-radius: 8;"+
            "-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%), #9d4024, #a22312, radial-gradient(center 50% 50%, radius 100%, #a22312, #a21212);"+
            "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );"+
            "-fx-font-weight: bold;"+
            "-fx-font-size: 1.1em;" ;
    Stage window;
    Scene mainScreen , leaderBoards , changeSkin , pause , mainGame ;
    Button toMainScreen,toLeaderPage,toSkinChangePage, Quit, toStartGame, confirmButton, resumeButton;
    ChoiceBox<String> Choices ;

    private StackPane playButton;

    public void renderMainpage(){
        VBox mainlayout = new VBox();

        Image top = new Image(getClass().getResourceAsStream("top.png"));
        ImageView topp = new ImageView();

        topp.setImage(top);

        mainlayout.setAlignment(Pos.CENTER);
        mainlayout.getChildren().add(topp);
        mainlayout.getChildren().add(playButton);
        mainlayout.getChildren().add(toLeaderPage);
        mainlayout.getChildren().add(toSkinChangePage);
        mainlayout.getChildren().add(resumeButton);
        mainlayout.getChildren().add(Quit);
        mainlayout.setSpacing(30);

        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResource("background.png").toExternalForm(),1281,720,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        mainlayout.setBackground(new Background(myBI));

        mainScreen = new Scene(mainlayout);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        window.setX(bounds.getMinX());
        window.setY(bounds.getMinY());
        window.setWidth(bounds.getWidth());
        window.setHeight(bounds.getHeight());

        window.setTitle("SnekvsBlok");
        window.setScene(mainScreen);
//        window.setMaximized(true);
//        window.setResizable(false);
        window.show();
    }

    public void exiting(){
        Platform.exit();
        window.close();
    }

    public void renderLeaderboard(){
        Pane leaderboardlayout = new Pane();
        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResource("background.png").toExternalForm(),1281,720,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        leaderboardlayout.setBackground(new Background(myBI));
        leaderboardlayout.setTranslateX(0);
        leaderboardlayout.setTranslateY(0);
        toMainScreen.setLayoutX(8);
        toMainScreen.setLayoutY(8);
        ListView<String> board = new ListView<String>();

        String[] str = new String[11];
        str[0] = " Name      Score       Date";
        str[1] = "aniket       69      03/11/2018";
        str[2] = "amoghe      35      03/11/2018";
        str[3] = "pranaay     420     03/11/2018";
        str[4] = "aniket       69      03/11/2018";
        str[5] = "amoghe      35      03/11/2018";
        str[6] = "pranaay     420     03/11/2018";
        str[7] = "aniket       69      03/11/2018";
        str[8] = "amoghe      35      03/11/2018";
        str[9] = "pranaay     420     03/11/2018";
        str[10]= "aniket       69      03/11/2018";

        leaderboardlayout.getStylesheets().add(getClass().getResource("lmao.css").toExternalForm());
        board.setLayoutX(510);
        board.setLayoutY(120);
        board.setEditable(false);
        board.setPrefWidth(335);
        board.setPrefHeight(500);
       // board.set
        board.getItems().addAll(str);
        //board.



        leaderboardlayout.getChildren().add(toMainScreen);
        leaderboardlayout.getChildren().add(board);
        leaderBoards = new Scene(leaderboardlayout,1280,720);
       // System.out.println(toMainScreen.getTranslateX()+" "+toMainScreen.getTranslateY());
        window.setTitle("Leaderboards");
        window.setScene(leaderBoards);
        window.show();
    }

    private ImageView createImageView(final File imageFile)throws Exception {
        // DEFAULT_THUMBNAIL_WIDTH is a constant you need to define
        // The last two arguments are: preserveRatio, and use smooth (slower)
        // resizing

        ImageView imageView = null;
        try {
            final Image image = new Image(new FileInputStream(imageFile), 150, 0, true, true);
            imageView = new ImageView(image);
            imageView.setStyle("-fx-background-color: transparent");
            imageView.setFitWidth(150);
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {

                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){

                        if(mouseEvent.getClickCount() == 2){
                            try {
                                game.setSnekmage(new Image(new FileInputStream(imageFile)));
                                if(imageFile.getName().equals("A.png")){
                                    game.setSnekmage(null);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            });
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return imageView;
    }
    public void renderChangeSkin()throws Exception{

        //stage = window;

        ScrollPane root = new ScrollPane();
        TilePane tile = new TilePane();
        toMainScreen.setLayoutX(8.0);
        toMainScreen.setLayoutY(8.0);

        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResourceAsStream("background.png"),1281,720,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        tile.getChildren().add(toMainScreen);

        tile.setPadding(new Insets(15, 15, 15, 15));
        tile.setHgap(15);

        String path = "CreepyPictures";

        File folder = new File(path);
        System.out.println(folder.getAbsolutePath());
        File[] listOfFiles = folder.listFiles();

        for (final File file : listOfFiles) {
            ImageView imageView;
            imageView = createImageView(file);
            tile.getChildren().addAll(imageView);
        }

        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
        root.setFitToWidth(true);
        root.setContent(tile);

        Scene scene = new Scene(root,1280,720);
        window.setScene(scene);
        window.show();
    }






    @Override
    public void start(Stage primaryStage) throws Exception{
        game = new PlayGame();
        window = primaryStage ;

        ///////////////////////////////////
        toMainScreen = new Button();
        toMainScreen.setText("Back");

        ///////////////////////////////////
        toLeaderPage = new Button();
        toLeaderPage.setText("Leaderboards");
        toLeaderPage.setStyle(IDLE_BUTTON_STYLE);

        ///////////////////////////////////
        resumeButton = new Button();
        resumeButton.setText("Resume");
        resumeButton.setStyle(IDLE_BUTTON_STYLE);

        ///////////////////////////////////
        toSkinChangePage = new Button();
        toSkinChangePage.setText("Change Skin");
        toSkinChangePage.setStyle(IDLE_BUTTON_STYLE);

        //////////////////////////////////
        toStartGame = new Button();
        toStartGame.setStyle(IDLE_BUTTON_STYLE);

        playButton = new StackPane();

        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(0.0, 0.0, 20.0, 10.0, 0.0, 20.0);

        playButton.getChildren().addAll(toStartGame, polygon);

        double radius = 80;

        toStartGame.setShape(new Circle(radius));
        toStartGame.setMinSize(2*radius, 2*radius);
        toStartGame.setMaxSize(2*radius, 2*radius);

        ////////////////////////////
        Quit = new Button();
        Quit.setText("Quit");
        Quit.setStyle(IDLE_BUTTON_STYLE_QUIT);
        toMainScreen.setStyle(IDLE_BUTTON_STYLE);



/////////////////////////////////////////////////////////////////////BACK TO MAIN SCREEN BUTTON
        toMainScreen.setOnAction(e -> {
            this.renderMainpage();
        });

        toMainScreen.setOnMousePressed(e -> {
            toMainScreen.setStyle(PRESSED_BUTTON_STYLE);
        });

        toMainScreen.setOnMouseEntered(e -> toMainScreen.setStyle(HOVEROVER_BUTTON_STYLE));
        toMainScreen.setOnMouseExited(e -> toMainScreen.setStyle(IDLE_BUTTON_STYLE));


///////////////////////////////////////////////////////////////////start game button

        toStartGame.setOnMousePressed(e -> {
            toStartGame.setStyle(PRESSED_BUTTON_STYLE);
        });

        toStartGame.setOnAction((e->{
            try {
//                ((Node)(e.getSource())).getScene().getWindow().hide();
                game.start(window);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }));

        toStartGame.setOnMouseEntered(e -> toStartGame.setStyle(HOVEROVER_BUTTON_STYLE));
        toStartGame.setOnMouseExited(e -> toStartGame.setStyle(IDLE_BUTTON_STYLE));

        //////////////////////////////////////////////////////////////////////////////////////////////////
        Choices = new ChoiceBox<String>();
        Choices.getItems().add("RESTART");
        Choices.getItems().add("GO Back");

        //////////////////////////////////////////////////////////////////////leaderpage button
        toLeaderPage.setOnAction(e -> {
            this.renderLeaderboard();
        });
        toLeaderPage.setOnMousePressed(e -> {
            toLeaderPage.setStyle(PRESSED_BUTTON_STYLE);
        });

        toLeaderPage.setOnMouseEntered(e -> toLeaderPage.setStyle(HOVEROVER_BUTTON_STYLE));
        toLeaderPage.setOnMouseExited(e -> toLeaderPage.setStyle(IDLE_BUTTON_STYLE));

        //////////////////////////////////////////////////////////////////////
        resumeButton.setOnAction(e -> {
            //TODO
        });
        resumeButton.setOnMousePressed(e -> {
            resumeButton.setStyle(PRESSED_BUTTON_STYLE);
        });

        resumeButton.setOnMouseEntered(e -> resumeButton.setStyle(HOVEROVER_BUTTON_STYLE));
        resumeButton.setOnMouseExited(e -> resumeButton.setStyle(IDLE_BUTTON_STYLE));

        /////////////////////////////////////////////////////////////////////////////quit button
        Quit.setOnAction(e -> {
            this.exiting();
        });

        Quit.setOnMousePressed(e -> {
            Quit.setStyle(PRESSED_BUTTON_STYLE_QUIT);
        });

        Quit.setOnMouseEntered(e -> Quit.setStyle(HOVEROVER_BUTTON_STYLE_QUIT));
        Quit.setOnMouseExited(e -> Quit.setStyle(IDLE_BUTTON_STYLE_QUIT));

        //////////////////////////////////////////////////////////////////////change skin button
        toSkinChangePage.setOnAction(event -> {
            try {
                this.renderChangeSkin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        toSkinChangePage.setOnMousePressed(event -> {
            toSkinChangePage.setStyle(PRESSED_BUTTON_STYLE);
        });

        toSkinChangePage.setOnMouseEntered(e -> toSkinChangePage.setStyle(HOVEROVER_BUTTON_STYLE));
        toSkinChangePage.setOnMouseExited(e -> toSkinChangePage.setStyle(IDLE_BUTTON_STYLE));

        this.renderMainpage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
