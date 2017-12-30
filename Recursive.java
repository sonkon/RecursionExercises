//imports
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;


public class Recursive {

    public static String getBinary(int n) {
        if (n == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Failed precondition: getBinary. " + "n cannot equal Integer.MIN_VALUE. n: " + n);
        }

        if(n < 0) {
            n = n * (-1);
            return "-" + getBinary(n / 2) + (n % 2);
        }
        if(n < 2) {
            return "" + n;
        }

        return "" + getBinary(n / 2) + (n % 2); 
    }

    /**
     * Problem 2: reverse a String recursively.<br>
     *   pre: stringToRev != null<br>
     *   post: returns a String that is the reverse of stringToRev
     *   @param stringToRev the String to reverse.
     *   @return a String with the characters in stringToRev in reverse order.
     */
    public static String revString(String stringToRev) {
        if (stringToRev == null){
            throw new IllegalArgumentException("Failed precondition: revString. parameter may not be null.");
        }
        if(stringToRev.length() == 1) {
            return stringToRev;
        }

        return revString(stringToRev.substring(1)) + stringToRev.charAt(0); //dummy return, replace as necessary

    }

    /**
     * Problem 3: Returns the number of elements in data
     * that are followed directly by value that is
     * double that element.
     * pre: data != null
     * post: return the number of elements in data that are followed immediately by double the value
     * @param data The array to search.
     * @return The number of elements in data that are followed immediately by a value that
     * is double the element.
     */

    public static int nextIsDouble(int[] data)

    {

        if (data == null)

        {

            throw new IllegalArgumentException("Failed precondition: revString. parameter may not be null.");

        }

        return nextToDouble(data, 0);// must change. Need to write a helper method

    }

    // CS314 students, add your nextIsDouble helper method here
    private static int nextToDouble(int[] data, int index)
    {
        int numDouble = 0;
        if(index != 0 && ((data[index - 1] * 2) == data[index]))
        {
            numDouble++;
        }
        if((index + 1) == data.length)
        {
            return numDouble;
        }

        return numDouble + nextToDouble(data, index + 1);

    }

    /**  Problem 4: Find all combinations of mnemonics for the given number.<br>
     *   pre: number != null, number.length() > 0, all characters in number are digits<br>
     *   post: see tips section of assignment handout
     *   @param number The number to find mnemonics for
     *   @return An ArrayList<String> with all possible mnemonics for the given number
     */
    public static ArrayList<String> listMnemonics(String number) 
    {
        if (number == null ||  number.length() == 0 || !allDigits(number)) 
        {
            throw new IllegalArgumentException("Failed precondition: listMnemonics");
        }
        ArrayList<String> result = new ArrayList<>();
        recursiveMnemonics(result, "", number);
        return result;
    }

    /*
     * Helper method for listMnemonics
     * mnemonics stores completed mnemonics
     * mneominicSoFar is a partial (possibly complete) mnemonic
     * digitsLeft are the digits that have not been used from the original number
     */
    private static void recursiveMnemonics(ArrayList<String> mnemonics,
    String mnemonicSoFar, String digitsLeft)
    {
        // CS314 students, complete this method
        String letters = digitLetters(digitsLeft.charAt(0));
        for(int i = 0; i < letters.length(); i++)
        {
            if(digitsLeft.length() > 1)
            {
                recursiveMnemonics(mnemonics, (mnemonicSoFar + letters.charAt(i)), digitsLeft.substring(1));
            }
            else
            {
                mnemonics.add(mnemonicSoFar + letters.charAt(i));
            }
        }
    }

    // used by method digitLetters
    private static final String[] letters = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    /* helper method for recursiveMnemonics
     * pre: ch is a digit '0' through '9'
     * post: return the characters associated with this digit on a phone keypad
     */
    private static String digitLetters(char ch)
    {
        if (ch < '0' || ch > '9') 
        {
            throw new IllegalArgumentException("parameter ch must be a digit, 0 to 9. Given value = " + ch);
        }
        int index = ch - '0';
        return letters[index];
    }

    /* helper method for listMnemonics
     * pre: s != null
     * post: return true if every character in s is a digit ('0' through '9')
     * */
    private static boolean allDigits(String s)
    {
        if (s == null)
        {
            throw new IllegalArgumentException("Failed precondition: allDigits. String s cannot be null.");
        }
        boolean allDigits = true;
        int i = 0;
        while (i < s.length() && allDigits) 
        {
            allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
            i++;
        }
        return allDigits;
    }

    /**
     * Problem 5: Draw a Sierpinski Carpet.
     * @param size the size in pixels of the window
     * @param limit the smallest size of a square in the carpet.
     */
    public static void drawCarpet(int size, int limit) {
        DrawingPanel p = new DrawingPanel(size, size);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,size,size);
        g.setColor(Color.WHITE);
        drawSquares(g, size, limit, 0, 0);

    }

    /* Helper method for drawCarpet
     * Draw the individual squares of the carpet.
     * @param g The Graphics object to use to fill rectangles
     * @param size the size of the current square
     * @param limit the smallest allowable size of squares
     * @param x the x coordinate of the upper left corner of the current square
     * @param y the y coordinate of the upper left corner of the current square
     */
    private static void drawSquares(Graphics g,int size, int limit, double x, double y) {

        if (size > limit) {
            int temp = size / 3;
            g.fillRect ( (int) x + temp, (int) y + temp, temp, temp );
            for (int i = 0; i < 9; i++)  {
                if (i != 4) {
                    int j = i / 3;
                    int k = i % 3;
                    drawSquares (g, temp, limit, (int) x + j * temp, (int) y + k * temp); 
                }
            }
        }
    }

    /* helper method for canFlowOfrawMaze - CS314 students you should not have to
     * call this method,
     * pre: mat != null,
     */
    private static boolean inbounds(int r, int c, int[][] mat) {
        assert mat != null : "Failed precondition: inbounds";
        return r >= 0 && r < mat.length && mat[r] != null && c >= 0 && c < mat[r].length;
    }

    /*
     * helper method for canFlowOfrawMaze - CS314 stdents you should not have to
     * call this method,
     * pre: mat != null, mat.length > 0
     * post: return true if mat is rectangular
     */
    private static boolean isRectangular(int[][] mat) {
        assert (mat != null) && (mat.length > 0) : "Violation of precondition: isRectangular";

        boolean correct = true;
        final int numCols = mat[0].length;
        int row = 0;
        while( correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == numCols);
            row++;
        }
        return correct;
    }

    /**
     * Problem 7: Find the minimum difference possible between teams
     * based on ability scores. The number of teams may be greater than 2.
     * The goal is to minimize the difference between the team with the
     * maximum total ability and the team with the minimum total ability.
     * <br> pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
     * <br> post: return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability.
     * @param numTeams the number of teams to form.
     * Every team must have at least one member
     * @param abilities the ability scores of the people to distribute
     * @return return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability. The return value will be greater than or equal to 0.
     */
    public static int minDifference(int numTeams, int[] abilities) {
        return -1;
    }

    /**
     * Problem 8: Maze solver. Return 2 if it is possible to escape the maze after
     * collecting all the coins. Return 1 if it is possible to escape the maze 
     * but without collecting all the coins. Return 0 if it is not possible
     * to escape the maze. More details in the assignment handout.
     * <br>pre: board != null
     * <br>pre: board is a rectangular matrix
     * <br>pre: board only contains characters 'S', 'E', '$', 'G', 'Y', and '*'
     * <br>pre: there is a single 'S' character present
     * <br>post: rawMaze is not altered as a result of this method.
     * Return 2 if it is possible to escape the maze after```````````````````
     * collecting all the coins. Return 1 if it is possible to escape the maze 
     * but without collecting all the coins. Return 0 if it is not possible
     * to escape the maze. More details in the assignment handout
     * @param rawMaze represents the maze we want to escape. 
     * rawMaze is not altered as a result of this method.
     * @return per the post condition
     */

    public static int canEscapeMaze(char[][] rawMaze) {
        if (rawMaze == null || isRectangularChar(rawMaze) == false) {
            throw new IllegalArgumentException("Failed precondition.");
        }
        
        int rindex = 0;
        int cindex = 0;
        int numcoins = 0;
        int s = 1;
        Tiles tiles = new Tiles(rawMaze);
        ArrayList<Integer> coins = new ArrayList<Integer>();

        for (int i = 0; i < rawMaze.length; i++){
            for (int j = 0; j < rawMaze[0].length; j++) {  
                if(rawMaze[i][j] == 'S') {
                    if (s == 2) {
                        throw new IllegalArgumentException("Failed precondition.");
                    }
                    rindex = i;
                    cindex = j;
                    s++;
                } else if (rawMaze[i][j] == '$') {
                    numcoins = numcoins + 1;
                } else if (rawMaze[i][j] != 'E' && rawMaze[i][j] != 'G' && rawMaze[i][j] != 'Y' && rawMaze[i][j] != '*') {
                    throw new IllegalArgumentException("Failed precondition.");
                }
            } 
        }

        recursiveShit(tiles, rindex, cindex,coins, 0);

        int max = 0;
        for (int i = 0; i < coins.size(); i++){
            if (coins.size() == 0){
                return 0;
            }
            if (coins.get(i) == numcoins){
                max = 2;
            } else if (coins.get(i) != numcoins && max != 2) {
                max = 1;
            }

        }

        return max;
    }

    public static void recursiveShit(Tiles tiles, int row, int col, ArrayList<Integer> addedNum, int num) {
        if (tiles.get(row, col) == 'E'){
            addedNum.add(num);
        } else {
            if(tiles.get(row, col) == '$') {
                num = num + 1;
            }

            if (tiles.isLegit(row - 1, col)) {
                char temp = tiles.get(row, col);
                tiles.changeTile(row, col);
                recursiveShit(tiles, row - 1, col, addedNum, num);
                tiles.change(row, col, temp);
            }

            if (tiles.isLegit(row, col + 1)) {
                char temp = tiles.get(row, col);
                tiles.changeTile(row, col);
                recursiveShit(tiles, row, col + 1, addedNum, num);
                tiles.change(row, col, temp);
            } 

            if(tiles.isLegit(row + 1, col)) {
                char temp = tiles.get(row, col);
                tiles.changeTile(row, col);
                recursiveShit(tiles, row + 1, col, addedNum, num);
                tiles.change(row, col, temp);
            } 

            if (tiles.isLegit(row, col - 1)) {
                char temp = tiles.get(row, col);
                tiles.changeTile(row, col);
                recursiveShit(tiles, row, col - 1, addedNum, num);
                tiles.change(row, col, temp);
            }

        }
    }
    
    private static boolean isRectangularChar(char[][] mat) {
        assert (mat != null) && (mat.length > 0) : "Violation of precondition: isRectangular";

        boolean correct = true;
        final int numCols = mat[0].length;
        int row = 0;
        while( correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == numCols);
            row++;
        }
        return correct;
    }
    
    public static class Tiles {
        static char[][] maze;
        public Tiles(char[][] rawMaze) {
            maze = rawMaze;
        }

        public static void changeTile(int row, int col) {
            if (maze[row][col] == 'S') {
                maze[row][col] = 'G';
            } else if (maze[row][col] == 'G' || maze[row][col] == '$') {
                maze[row][col] = 'Y';
            } else if (maze[row][col] == 'Y') {
                maze[row][col] = '*';
            }
        }

        public static char get(int row, int col) {
            return maze[row][col];
        }

        public static boolean isLegit(int row, int col) {
            if(row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] != '*') {
                return true;    
            }
            return false;
        }

        public static void change(int row, int col, char change) {
            maze[row][col] = change;
        }
    }
}

