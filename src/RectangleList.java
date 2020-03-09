import javafx.scene.canvas.GraphicsContext;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.Arrays;

public class RectangleList {

    private int numRectangles;

    private Rectangle[] rectangles;

    private GraphicsContext gc;

    private int windowWidth;
    private int windowHeight;

    private int rectWidth;
    private int maxRectHeight = 500;


    public RectangleList(int numRectangles, GraphicsContext gc, int windowWidth, int windowHeight, int rectWidth){
        this.numRectangles = numRectangles;

        this.rectangles = new Rectangle[numRectangles];

        this.gc = gc;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        this.rectWidth = rectWidth;

    }

    public void swap(int index1, int index2)
    {
        double tempPosX = rectangles[index1].getPosX();
        rectangles[index1].setPosX(rectangles[index2].getPosX());
        rectangles[index2].setPosX(tempPosX);

        Rectangle temPosRectangle = rectangles[index1];
        rectangles[index1] = rectangles[index2];
        rectangles[index2] = temPosRectangle;
    }

    public void genList(){
        for (int i = 0; i < rectangles.length; i++)
            rectangles[i] = new Rectangle((i * rectWidth), this.windowHeight, this.rectWidth, this.maxRectHeight, this.gc);
    }

    public void drawList(){
        for (Rectangle rectangle : rectangles){
            rectangle.drawRect();
        }
    }

    public RectangleList mirrorToRight() throws CloneNotSupportedException{
        RectangleList tempCp = new RectangleList(this.numRectangles, this.gc, this.windowWidth, this.windowHeight, this.rectWidth);
        tempCp.maxRectHeight = maxRectHeight;

        Rectangle[] tempRectangles = new Rectangle[numRectangles];
        for(int i = 0; i < numRectangles; i++){
            tempRectangles[i] = (Rectangle) this.rectangles[i].clone();
            tempRectangles[i].setPosX((int)(tempRectangles[i].getPosX() + windowWidth/2));
        }
        tempCp.rectangles = tempRectangles;

        return tempCp;
    }

    public int getNumRectangles(){
        return numRectangles;
    }

    public boolean checkInOrder(){
        for (int i = 1; i < numRectangles; i++){
            if ((rectangles[i - 1].getHeight() > rectangles[i].getHeight()))
                return false;
        }
        return true;
    }

    public Rectangle get(int index){ return rectangles[index]; }

    //Sets pos1 to pos2
    public void cloneRect(int pos1, int pos2)
    {
        try{
            Rectangle tempRectangle = (Rectangle) rectangles[pos1].clone();
            tempRectangle.setPosX(rectangles[pos2].getPosX());
            rectangles[pos2] = tempRectangle;
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }

    }

    public void set(int pos, Rectangle rect){

        Rectangle tempRect = null;
        try{
            tempRect = (Rectangle) rect.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }


        double tempPosX = rectangles[pos].getPosX();
        tempRect.setPosX(tempPosX);
        rectangles[pos] = tempRect;
    }

    public void setList(Rectangle[] rectangles){
        this.rectangles = rectangles;
    }

    //Returns a rectangle list with index between the bounds inclusive.
    //Used for merge sort.
    public RectangleList subList(int index1, int index2){
        Rectangle[] tempRect = Arrays.copyOfRange(rectangles, index1, index2);
        RectangleList temp = new RectangleList(tempRect.length, this.gc, this.windowWidth, this.windowHeight, this.rectWidth);
        temp.setList(tempRect);
        return temp;

    }

    public Rectangle[] getRectangles(){
        return rectangles;
    }

    public void setSubList(Rectangle[] subList, int startIndex, int endIndex)
    {
        for (int i = startIndex, j = 0; i < endIndex; i++, j++){
            this.set(i, subList[j]);
        }
    }

    @Override
    public String toString() {

        String temp = "";

        for (Rectangle rect : rectangles){
            temp += " " + rect.getHeight();
        }

        return temp;
    }
}
