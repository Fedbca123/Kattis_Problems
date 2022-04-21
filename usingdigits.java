import java.util.*;
//Omar Shalaby, COP3503, 4/20/22

public class usingdigits {
    public static Scanner scnr = new Scanner(System.in);

    public static int y = scnr.nextInt();//takes in the dimensions of board
    public static int x = scnr.nextInt();

    public static void main(String[] args){

        int[][] board;
        scnr.nextLine();//reads in garbage line to get in proper input
        String input;
        String codeKey = scnr.nextLine();// reads in amount of jumps and length of each jump
        board = new int[x][y];//creates board
        int[][][] memo =  new int[x + 1][y + 1][codeKey.length() +1];//creates memoization array

        for(int i = 0; i <= x; i++)//fills array with -1
            for(int j = 0; j <= y; j++)
                Arrays.fill(memo[i][j], -1);

        for(int i = 0; i < x; i++) {//takes in each row and parses them into an array of ints
            input = scnr.nextLine();
            for (int j = 0; j < y; j++)
                board[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
        }


        int output = solve(board, codeKey, x -1, 0, memo);//returns the smallest sum to
        //cross the board

        System.out.println(output);//prints answer

    }

    public static int solve(int[][] board, String codeKey, int currX, int currY, int[][][] memo){

        if(currX == 0 && currY  == y -1) return board[currX][currY];//if at exit, begins returning

        if(!checkAround(currX, currY)) return 1000000000;//if out of bounds, makes it so that path
        //is invalid.

        if(memo[currX][currY][codeKey.length()] != -1) return memo[currX][currY][codeKey.length()];
        //if already calculated that path, just returns that array

        int v1;
        int v2;

         if(codeKey.length() > 0) {//if there are still any jumps, sees if it's worth it to jump
             //or not

             int tmp = Integer.parseInt(String.valueOf(codeKey.charAt(0)));//takes the jump form the string

             //checks if it's better to jump up or just go up one space on the board
            v1 = Math.min(solve(board, codeKey.substring(1),
                            currX - tmp - 1, currY, memo),
                    solve(board, codeKey, currX -1, currY,memo));

            //checks if it's better to hop to the right or just go to the right on the board
            v2 = Math.min( solve(board, codeKey.substring(1),
                            currX , currY + 1 + tmp, memo),
                    solve(board, codeKey, currX, currY + 1, memo));

            memo[currX][currY][codeKey.length()] = board[currX][currY] + Math.min(v1, v2);
            //saves the smallest sum between all four options to memo

            return memo[currX][currY][codeKey.length()];

        }

         memo[currX][currY][codeKey.length()] = board[currX][currY] + Math.min(solve(board, codeKey, currX -1, currY, memo),
                solve(board, codeKey, currX, currY + 1, memo));
         //if no jumps left, just checks if it's better to go up or to the right

         return memo[currX][currY][codeKey.length()];//returns value

    }

    public static boolean checkAround(int currX, int currY){
        return((currX >= 0) && (currY < y));//checks if current position is out of bounds.
    }
}
