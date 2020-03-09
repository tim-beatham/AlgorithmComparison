import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Rectangle implements Cloneable {

    //posX and posY of bottom left hand corner
    private double posX;
    private double posY;

    private double width;

    private double height;

    private Color color;

    private GraphicsContext gc;

    private double maxHeight;

    public Rectangle(int posX, int posY, int width, int maxRectHeight, GraphicsContext gc){
        this.posX = posX;
        this.posY = posY;

        this.width = width;


        this.maxHeight = maxRectHeight;


        int tempRandomHeight = (int)(Math.random() * maxRectHeight);

        if (tempRandomHeight < 1)
            tempRandomHeight = 1;

        this.height = tempRandomHeight;

        this.color = genColor();
        this.gc = gc;
    }

    public Color genColor(){
        return Color.rgb((int)((this.height/this.maxHeight)*255),0, 0);
    }

    public void drawRect(){
        gc.setFill(this.color);
        gc.fillRect(this.posX, this.posY - (this.height), this.width, this.height);
    }

    public void setPosX(double posX){
        this.posX = posX;
    }

    public void setPosY(double posY){
        this.posY = posY;
    }

    public Color getColor(){
        return this.color;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return posX == rectangle.posX &&
                posY == rectangle.posY &&
                width == rectangle.width &&
                height == rectangle.height &&
                Objects.equals(color, rectangle.color) &&
                Objects.equals(gc, rectangle.gc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY, width, height, color, gc);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
