public class InsertionSort implements SortingAlgorithm {

    private boolean done = false;

    private long frames = 0;
    private long iterations = 1;

    private Rectangle key;
    private int jPos;

    private boolean setKey = true;

    @Override
    public void sort(RectangleList rl){

        if (!done){
            int keyIndex = (int)(iterations % rl.getNumRectangles());

            System.out.println(keyIndex);

            if (setKey){
                key = rl.get(keyIndex);
                jPos = keyIndex - 1;
                setKey = false;
            }

            if (jPos >= 0 && rl.get(jPos).getHeight() > key.getHeight()){
                rl.cloneRect(jPos, jPos+1);
                jPos--;
            }else{
                rl.set(jPos + 1, key);
                setKey = true;
                iterations++;

            }


            if (rl.checkInOrder() && (iterations > rl.getNumRectangles())){
                done = true;
            }else{
                done = false;
            }


        }
    }

}
