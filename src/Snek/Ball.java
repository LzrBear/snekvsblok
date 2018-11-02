package Snek;

import javafx.scene.shape.Circle;

import java.util.Random;

public class Ball extends Circle {

    private int Value;

    public Ball() {
        super();
        Random rand = new Random();
        this.Value = 1 + rand.nextInt(10);
    }

    public Ball(double radius) {
        super(radius);
        Random rand = new Random();
        this.Value = 1 + rand.nextInt(10);
    }

    public Ball(double X, double Y, double Radius) {
        super(X, Y, Radius);
        Random rand = new Random();
        this.Value = 1 + rand.nextInt(10);
    }
}