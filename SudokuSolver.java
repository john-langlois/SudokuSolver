import java.util.Scanner;

public class SudokuSolver {
    private static final int GRID_SIZE =9;

    public static void main(String[] args) {

        int[][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };
        printBoard(board);

        if (SolveBoard(board)){
            System.out.println("Solved Successfully");
        }
        else{
            System.out.println("Unsolvable Board :(");
        }
        printBoard(board);



    }
    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int column = 0; column < GRID_SIZE; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    private static boolean isNumberInRow(int[][] board, int number, int row){
        for(int i=0; i<GRID_SIZE;i++){
            if(board[row][i] == number){
                return true;
            }
        }
        return false;
    }
    //Check if number is in column
    private static boolean isNumberInColumn(int[][] board, int number, int column){
        for(int i=0; i<GRID_SIZE;i++){
            if(board[i][column] == number){
                return true;
            }
        }
        return false;
    }
    //Returns Location of Box i.e. row and column
    private static boolean isNumberInBox(int[][] board, int number, int row, int column){
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for(int i =localBoxRow;i< localBoxRow +3; i++){
            for (int j = 1;j<localBoxColumn+3; j++){
                if(board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }
    //Check for valid placement using recursion
    private static boolean isValidPlacement(int[][]board, int number, int row, int column){
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number,row,column);

    }
    private static boolean SolveBoard(int[][] board){
        //Goes through row by row to check if there are any empty or "0" Spots
        for(int row=0; row<GRID_SIZE;row++){
            //Goes through column by column to check if there are any empty or "0" Spots
            for(int column=0; column<GRID_SIZE; column++){
                //If there are both row and column available then it can go through numbers 1-9 to check
                // if there are no numbers alike in the same row, column or box
                if(board[row][column]==0){
                    for( int numberToTry =1; numberToTry <=GRID_SIZE; numberToTry++){
                        if(isValidPlacement(board,numberToTry,row,column)){
                            board[row][column] = numberToTry;
                            //If there is an available spot, that also can be a valid placement so that other numbers can
                            //fit in as well then the board returns true value
                            //Note: May loop many times to check how each spot would affect another until it comes to
                            // a solution for all.
                            if (SolveBoard(board)){
                                return true;
                            }
                            else{
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
