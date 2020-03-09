public class BubbleSort implements SortingAlgorithm {

    private boolean done = false;

    private long frames = 0;

    @Override
    public void sort(RectangleList rl){

        if (done == false){

            if (rl.checkInOrder() == true){
                done = true;
                return;
            }else{
                done = false;
            }

            frames++;


            int positionIndex = (int)(frames % rl.getNumRectangles());

            if (positionIndex == rl.getNumRectangles() - 1){
                return;
            }else if (rl.get(positionIndex).getHeight() > rl.get(positionIndex+1).getHeight()){
                rl.swap(positionIndex, positionIndex+1);
            }

        }

    }
}
