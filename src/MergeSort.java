import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class MergeSort implements SortingAlgorithm {

    private int iterations = 0;

    private boolean sorting = false;

    private int step = 2;

    private int currentIndex;
    private int endIndex;


    private int leftPointer;
    private int rightPointer;
    private int generalPointer;

    private Rectangle[] subList;


    private boolean stepGreater = false;
    private boolean done = false;

    @Override
    public void sort(RectangleList rl) {




        if (iterations == 0){
            //Later change code so it checks the length of the list.
            currentIndex = 0;
            endIndex = Math.min(currentIndex + step, rl.getNumRectangles());

            leftPointer = currentIndex;
            rightPointer = (currentIndex + endIndex) / 2;
            generalPointer = 0;

            subList = rl.subList(currentIndex, endIndex).getRectangles();
            System.out.println(rl);
        }


        if (!done){
            merge(rl);


            iterations++;
        }

        if (rl.checkInOrder()){
            done = true;
        }




    }

    public void merge(RectangleList rl){
        int mid;
        /*if (!stepGreater){
            mid = (currentIndex + endIndex) / 2;
        }else{
            mid = (2*currentIndex + step) / 2;
        }*/

        mid = (currentIndex + endIndex) / 2;

        /*if (mid >= rl.getNumRectangles()) {
            step /= 2;
            mid = (2 * currentIndex + step) / 2;
        }*/

        System.out.println("Mid:" + mid);


        if (leftPointer < mid && rightPointer < endIndex){
            if (rl.get(leftPointer).getHeight() <= rl.get(rightPointer).getHeight()){
                subList[generalPointer] = rl.get(leftPointer);
                leftPointer++;
                generalPointer++;
            }else{
                subList[generalPointer] = rl.get(rightPointer);
                rightPointer++;
                generalPointer++;
            }
        }else if (leftPointer < mid){
            subList[generalPointer] = rl.get(leftPointer);
            leftPointer++;
            generalPointer++;

        }else if (rightPointer < endIndex){
            subList[generalPointer] = rl.get(rightPointer);
            rightPointer++;
            generalPointer++;


        }else{

            rl.setSubList(subList, currentIndex, endIndex);


            System.out.println("Length: " + subList.length);

            currentIndex += step;

            if (currentIndex >= rl.getNumRectangles()){
                currentIndex = 0;
                step *= 2;
            }

            endIndex = Math.min(currentIndex + step, rl.getNumRectangles());

            /*if (currentIndex + step < rl.getNumRectangles() && endIndex + step > rl.getNumRectangles()){
                endIndex = rl.getNumRectangles();
            }*/

            leftPointer = currentIndex;
            rightPointer = Math.min((currentIndex + endIndex) / 2, rl.getNumRectangles() - 1);

            /*if (step > rl.getNumRectangles()){
                rightPointer = (2*currentIndex + step) / 2;
                stepGreater = true;
            }*/

            generalPointer = 0;



            System.out.println("Current index: " + currentIndex + " End index: " +endIndex);

            subList = rl.subList(currentIndex, endIndex).getRectangles();

        }
    }


    public String sublistString(Rectangle[] rectangles) {

        String temp = "";

        for (Rectangle rect : rectangles){
            temp += " " + rect.getHeight();
        }

        return temp;
    }


}

