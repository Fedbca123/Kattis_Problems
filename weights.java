import java.util.*;
import java.lang.Math;

public class weights {

    public static void main(String[] args){

        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
        int i;
        int objectWeight; //initializes some variables and get the number of test cases


        for(i = 0;  i < numCases; i++){
            List<Integer> rightSide = new ArrayList<>();// the weights being put on the right side
            List<Integer> leftSide = new ArrayList<>();// the weights being put on the left side
            objectWeight = stdin.nextInt();//Object being weighed

            if(objectWeight == 0){//if nothing is being weighed, output this and move ot next case
                System.out.println("left pan: ");
                System.out.println("right pan: ");
                continue;
            }

            int currWeightBal = objectWeight;


            while(true){

                while(currWeightBal > 0){//if the left side is heavier, put more weight on the right and
                    //update the balance
                    int closest = getClosest(currWeightBal);
                    currWeightBal -= closest;
                    rightSide.add(closest);
                }
                if(currWeightBal == 0) break;//if the scale is now balanced, exit
                currWeightBal = Math.abs(currWeightBal);
                while(currWeightBal > 0){/*If the right side is heavier, puts weight on the left side
                and updates the balance*/
                    int closest = getClosest(Math.abs(currWeightBal));
                    currWeightBal -= closest;
                    leftSide.add(closest);
                }
                currWeightBal = Math.abs(currWeightBal);

                if(currWeightBal == 0) break;

            }

            int rightSize =rightSide.size();
            for(int j = 1; j < rightSize; j++){/* sorts the weights on each side and then fixes if
            duplicate weights are put on each side by popping it to the other side and squaring
            the weight value on the side it originally was on*/
                if(rightSide.size() <= 1 || j >= rightSide.size() || j == 0) break;

                if(rightSide.get(j).equals(rightSide.get(j-1))){
                    leftSide.add(rightSide.get(j));
                    rightSide.remove(j);
                    rightSide.set(j-1, rightSide.get((j-1)) *3);
                    if(j - 1 >= 1)
                        j-=2;
                }
            }
            int leftSize = leftSide.size();

            Collections.sort(leftSide);
            Collections.reverse(leftSide);//sorts the weights in correct order to output
            Collections.sort(rightSide);
            Collections.reverse(rightSide);

            for(int j = 1; j < leftSize; j++){
                if(leftSide.size() <= 1 || j >= leftSide.size() || j == 0) break;

                if(leftSide.get(j).equals(leftSide.get(j-1))){
                    rightSide.add(leftSide.get(j));
                    leftSide.remove(j);
                    leftSide.set(j-1, leftSide.get((j-1)) *3);
                    if(j-1 >= 1)
                        j -= 2;
                }
            }

            Collections.sort(leftSide);
            Collections.reverse(leftSide);
            Collections.sort(rightSide);
            Collections.reverse(rightSide);



            System.out.print("left pan: ");//outputs the weights
            for(int j = 0; j < leftSide.size(); j++){
                System.out.print(leftSide.get(j) + " ");
            }
            System.out.println();

            System.out.print("right pan: ");
            for(int j = 0; j < rightSide.size(); j++){
                System.out.print(rightSide.get(j) + " ");
            }

        }

    }

    private static int getClosest(int num){
        int i = 1;

        while(i < num) {
            i *= 3;
        }

        if(Math.abs(num - i) > Math.abs(num - i/3)) return i/3;


        return i;
    }

}
