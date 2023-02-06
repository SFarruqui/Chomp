import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;
    ArrayList<Board> winningBoard = new ArrayList<>();
    ArrayList<Board> losingBoard = new ArrayList<>();
    ArrayList<Board> allBoards = new ArrayList<>();
    int a2, b2, c2, d2, e2, f2, g2, h2, i2, j2;

    public void generateBoards() {
        columns = new int[10];
        for (int a = 0; a <= 10; a++) { //loops through all columns going from last to first
            for (int b = a; b <= 10; b++) {
                for (int c = b; c <= 10; c++) {
                    for (int d = c; d <= 10; d++) {
                        for (int e = d; e <= 10; e++) {
                            for (int f = e; f <= 10; f++) {
                                for (int g = f; g <= 10; g++) {
                                    for (int h = g; h <= 10; h++) {
                                        for (int i = h; i <= 10; i++) {
                                            for (int j = i; j <= 10; j++) {// first column
                                                if (j > 0) { //when first column is greater than 0
                                                    columns = new int[]{j, i, h, g, f, e, d, c, b, a};
                                                    // System.out.println(Arrays.toString(columns)); //prints out all possible boards
                                                    Reducer(columns); //Reducer method takes column values from all boards
                                                    // System.out.println(); //creates gap to separate Given Boards
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public MyPlayer() {
        objectDeSerialization();

        allBoards.addAll(winningBoard);
        allBoards.addAll(losingBoard);
        System.out.println("Total number of all boards: " + allBoards.size());
        System.out.println("This code demonstrates a winning algorithm for only the first player in any 10x10 Chomp game board possibility.");
    }

    //when faced with a losing board, make a move that is the furthest away from the (0,0)
    //column = greatest & row = greatest  (use if statements)

    int row, col;

    public String arrToString(int[] nums) {
        String result = "" + nums[0];
        for (int i = 1; i < nums.length; i++) {
            result += "-" + nums[i];
        }
        return result;
    }

    public void Reducer(int[] nums) { //Reducer method reduces all possible boards into smaller boards
        boolean hasLosingBoard = false; //sets boolean whether Reduced Boards has any losing boards

        a2 = nums[0];
        b2 = nums[1];
        c2 = nums[2];
        d2 = nums[3];
        e2 = nums[4];
        f2 = nums[5];
        g2 = nums[6];
        h2 = nums[7];
        i2 = nums[8];
        j2 = nums[9];

        // System.out.println("Given Board: " + a2 + "-" + b2 + "-" + c2 + "-" + d2 + "-" + e2 + "-" + f2 + "-" + g2 + "-" + h2 + "-" + i2 + "-" + j2);

        //reduces column 10
        for (int i = j2 - 1; i >= 0; i--) {
            // System.out.println("Reduced Board: " + a2 + "-" + b2 + "-" + i);
            if (hasLosingBoard == false) {
                for (int x = 0; x < losingBoard.size(); x++) { //checks every losing board
                    if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && i == losingBoard.get(x).col10) { //if the board we're given matches a known losing board
                        hasLosingBoard = true; //set the losingBoard variable to true
                        col = 9; //if modifying the last column gives your opponent a losing board, you select the last column (2)
                        row = i; //you also select which row in that column you want to remove (i)
                        break;
                    }
                }
            }
        }

        //reduces column 9
        for (int j = i2 - 1; j >= 0; j--) { //for every value less than the number of the middle column
            if (j2 > j) {
                // System.out.println("Reduced Board: " + a2 + "-" + j + "-" + j);
                if (hasLosingBoard == false) {
                    for (int x = 0; x < losingBoard.size(); x++) {
                        if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && j == losingBoard.get(x).col9 && j == losingBoard.get(x).col10) {
                            //if this board's possible reduction matches a known losing board, then we can give the opponent a losing board
                            //if we can give the opponent a losing board, this must be a winning board
                            hasLosingBoard = true; //set the hasLosingBoard variable to true
                            col = 8; //this mark the row and column we must select for you to turn this board into a losing board for your opponent (1)
                            row = j; //you also select which row in that column you want to remove (j)
                            break;
                        }
                    }
                }
            } else {
                // System.out.println("Reduced Board: " + a2 + "-" + j + "-" + c2);
                if (hasLosingBoard == false) {
                    for (int x = 0; x < losingBoard.size(); x++) {
                        if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && j == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                            //if this board's possible reduction matches a known losing board, then we can give the opponent a losing board
                            //if we can give the opponent a losing board, this must be a winning board
                            hasLosingBoard = true;
                            col = 8; //this mark the row and column we must select for you to turn this board into a losing board for your opponent
                            row = j; //you also select which row in that column you want to remove (j)
                            break;
                        }
                    }
                }
            }
        }

        //reduces column 8
        for (int k = h2 - 1; k >= 0; k--) {
            if (i2 > k) {
                if (j2 > k) {
                    // System.out.println("Reduced Board: " + k + "-" + k + "-" + k );
                    if (hasLosingBoard == false) {
                        for (int x = 0; x < losingBoard.size(); x++) {
                            if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && k == losingBoard.get(x).col8 && k == losingBoard.get(x).col9 && k == losingBoard.get(x).col10) {
                                hasLosingBoard = true;
                                col = 7;
                                row = k;
                                break;
                            }
                        }
                    }
                } else {
                    // System.out.println("Reduced Board: " + k + "-" + k + "-" + c2);
                    if (hasLosingBoard == false) {
                        for (int x = 0; x < losingBoard.size(); x++) {
                            if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && k == losingBoard.get(x).col8 && k == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                hasLosingBoard = true;
                                col = 7;
                                row = k;
                                break;
                            }
                        }
                    }
                }
            } else {
                // System.out.println("Reduced Board: " + k + "-" + b2 + "-" + c2);
                if (hasLosingBoard == false) {
                    for (int x = 0; x < losingBoard.size(); x++) {
                        if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && k == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                            hasLosingBoard = true;
                            // System.out.println("Reduced Board: " + k + "-" + b2 + "-" + c2);
                            col = 7;
                            row = k;
                            break;
                        }
                    }
                }
            }
        }

        //reduces column 7
        for (int p = g2 - 1; p >= 0; p--) {
            if (h2 > p) {
                if (i2 > p) {
                    if (j2 > p) {
                        // System.out.println("Reduced Board: " + k + "-" + k + "-" + k );
                        if (hasLosingBoard == false) {
                            for (int x = 0; x < losingBoard.size(); x++) {
                                if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && p == losingBoard.get(x).col7 && p == losingBoard.get(x).col8 && p == losingBoard.get(x).col9 && p == losingBoard.get(x).col10) {
                                    hasLosingBoard = true;
                                    col = 6;
                                    row = p;
                                    break;
                                }
                            }
                        }
                    } else {
                        // System.out.println("Reduced Board: " + k + "-" + k + "-" + c2);
                        if (hasLosingBoard == false) {
                            for (int x = 0; x < losingBoard.size(); x++) {
                                if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && p == losingBoard.get(x).col7 && p == losingBoard.get(x).col8 && p == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                    hasLosingBoard = true;
                                    col = 6;
                                    row = p;
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    // System.out.println("Reduced Board: " + k + "-" + b2 + "-" + c2);
                    if (hasLosingBoard == false) {
                        for (int x = 0; x < losingBoard.size(); x++) {
                            if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && p == losingBoard.get(x).col7 && p == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                hasLosingBoard = true;
                                // System.out.println("Reduced Board: " + k + "-" + b2 + "-" + c2);
                                col = 6;
                                row = p;
                                break;
                            }
                        }
                    }
                }
            } else { //new addition
                if (hasLosingBoard == false) {
                    for (int x = 0; x < losingBoard.size(); x++) {
                        if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && p == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                            hasLosingBoard = true;
                            col = 6;
                            row = p;
                            break;
                        }
                    }
                }
            }
        }

        //reduces column 6
        for (int o = f2 - 1; o >= 0; o--) {
            if (g2 > o) {
                if (h2 > o) {
                    if (i2 > o) {
                        if (j2 > o) {
                            // System.out.println("Reduced Board: " + k + "-" + k + "-" + k );
                            if (hasLosingBoard == false) {
                                for (int x = 0; x < losingBoard.size(); x++) {
                                    if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && o == losingBoard.get(x).col6 && o == losingBoard.get(x).col7 && o == losingBoard.get(x).col8 && o == losingBoard.get(x).col9 && o == losingBoard.get(x).col10) {
                                        hasLosingBoard = true;
                                        col = 5;
                                        row = o;
                                        break;
                                    }
                                }
                            }
                        } else {
                            // System.out.println("Reduced Board: " + k + "-" + k + "-" + c2);
                            if (hasLosingBoard == false) {
                                for (int x = 0; x < losingBoard.size(); x++) {
                                    if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && o == losingBoard.get(x).col6 && o == losingBoard.get(x).col7 && o == losingBoard.get(x).col8 && o == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                        hasLosingBoard = true;
                                        col = 5;
                                        row = o;
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        // System.out.println("Reduced Board: " + k + "-" + b2 + "-" + c2);
                        if (hasLosingBoard == false) {
                            for (int x = 0; x < losingBoard.size(); x++) {
                                if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && o == losingBoard.get(x).col6 && o == losingBoard.get(x).col7 && o == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                    hasLosingBoard = true;
                                    // System.out.println("Reduced Board: " + k + "-" + b2 + "-" + c2);
                                    col = 5;
                                    row = o;
                                    break;
                                }
                            }
                        }
                    }
                } else { //new addition
                    if (hasLosingBoard == false) {
                        for (int x = 0; x < losingBoard.size(); x++) {
                            if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && o == losingBoard.get(x).col6 && o == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                hasLosingBoard = true;
                                col = 5;
                                row = o;
                                break;
                            }
                        }
                    }
                }
            } else { //new addition 2
                if (hasLosingBoard == false) {
                    for (int x = 0; x < losingBoard.size(); x++) {
                        if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && o == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                            hasLosingBoard = true;
                            col = 5;
                            row = o;
                            break;
                        }
                    }
                }
            }
        }

        //reduces column 5
        for (int s = e2 - 1; s >= 0; s--) {
            if (f2 > s) {
                if (g2 > s) {
                    if (h2 > s) {
                        if (i2 > s) {
                            if (j2 > s) {
                                // System.out.println("Reduced Board: " + k + "-" + k + "-" + k );
                                if (hasLosingBoard == false) {
                                    for (int x = 0; x < losingBoard.size(); x++) {
                                        if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && s == losingBoard.get(x).col5 && s == losingBoard.get(x).col6 && s == losingBoard.get(x).col7 && s == losingBoard.get(x).col8 && s == losingBoard.get(x).col9 && s == losingBoard.get(x).col10) {
                                            hasLosingBoard = true;
                                            col = 4;
                                            row = s;
                                            break;
                                        }
                                    }
                                }
                            } else {
                                // System.out.println("Reduced Board: " + k + "-" + k + "-" + c2);
                                if (hasLosingBoard == false) {
                                    for (int x = 0; x < losingBoard.size(); x++) {
                                        if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && s == losingBoard.get(x).col5 && s == losingBoard.get(x).col6 && s == losingBoard.get(x).col7 && s == losingBoard.get(x).col8 && s == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                            hasLosingBoard = true;
                                            col = 4;
                                            row = s;
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            // System.out.println("Reduced Board: " + k + "-" + b2 + "-" + c2);
                            if (hasLosingBoard == false) {
                                for (int x = 0; x < losingBoard.size(); x++) {
                                    if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && s == losingBoard.get(x).col5 && s == losingBoard.get(x).col6 && s == losingBoard.get(x).col7 && s == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                        hasLosingBoard = true;
                                        // System.out.println("Reduced Board: " + k + "-" + b2 + "-" + c2);
                                        col = 4;
                                        row = s;
                                        break;
                                    }
                                }
                            }
                        }
                    } else { //new addition
                        if (hasLosingBoard == false) {
                            for (int x = 0; x < losingBoard.size(); x++) {
                                if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && s == losingBoard.get(x).col5 && s == losingBoard.get(x).col6 && s == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                    hasLosingBoard = true;
                                    col = 4;
                                    row = s;
                                    break;
                                }
                            }
                        }
                    }
                } else { //new addition 2
                    if (hasLosingBoard == false) {
                        for (int x = 0; x < losingBoard.size(); x++) {
                            if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && s == losingBoard.get(x).col5 && s == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                hasLosingBoard = true;
                                col = 4;
                                row = s;
                                break;
                            }
                        }
                    }
                }
            } else { //new addition 3
                if (hasLosingBoard == false) {
                    for (int x = 0; x < losingBoard.size(); x++) {
                        if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && s == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                            hasLosingBoard = true;
                            col = 4;
                            row = s;
                            break;
                        }
                    }
                }
            }
        }

        //reduces column 4
        for (int t = d2 - 1; t >= 0; t--) {
            if (e2 > t) {
                if (f2 > t) {
                    if (g2 > t) {
                        if (h2 > t) {
                            if (i2 > t) {
                                if (j2 > t) {
                                    // System.out.println("Reduced Board: " + k + "-" + k + "-" + k );
                                    if (hasLosingBoard == false) {
                                        for (int x = 0; x < losingBoard.size(); x++) {
                                            if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && t == losingBoard.get(x).col4 && t == losingBoard.get(x).col5 && t == losingBoard.get(x).col6 && t == losingBoard.get(x).col7 && t == losingBoard.get(x).col8 && t == losingBoard.get(x).col9 && t == losingBoard.get(x).col10) {
                                                hasLosingBoard = true;
                                                col = 3;
                                                row = t;
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    // System.out.println("Reduced Board: " + k + "-" + k + "-" + c2);
                                    if (hasLosingBoard == false) {
                                        for (int x = 0; x < losingBoard.size(); x++) {
                                            if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && t == losingBoard.get(x).col4 && t == losingBoard.get(x).col5 && t == losingBoard.get(x).col6 && t == losingBoard.get(x).col7 && t == losingBoard.get(x).col8 && t == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                                hasLosingBoard = true;
                                                col = 3;
                                                row = t;
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else {
                                // System.out.println("Reduced Board: " + k + "-" + b2 + "-" + c2);
                                if (hasLosingBoard == false) {
                                    for (int x = 0; x < losingBoard.size(); x++) {
                                        if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && t == losingBoard.get(x).col4 && t == losingBoard.get(x).col5 && t == losingBoard.get(x).col6 && t == losingBoard.get(x).col7 && t == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                            hasLosingBoard = true;
                                            // System.out.println("Reduced Board: " + k + "-" + b2 + "-" + c2);
                                            col = 3;
                                            row = t;
                                            break;
                                        }
                                    }
                                }
                            }
                        } else { //new addition
                            if (hasLosingBoard == false) {
                                for (int x = 0; x < losingBoard.size(); x++) {
                                    if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && t == losingBoard.get(x).col4 && t == losingBoard.get(x).col5 && t == losingBoard.get(x).col6 && t == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                        hasLosingBoard = true;
                                        col = 3;
                                        row = t;
                                        break;
                                    }
                                }
                            }
                        }
                    } else { //new addition 2
                        if (hasLosingBoard == false) {
                            for (int x = 0; x < losingBoard.size(); x++) {
                                if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && t == losingBoard.get(x).col4 && t == losingBoard.get(x).col5 && t == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                    hasLosingBoard = true;
                                    col = 3;
                                    row = t;
                                    break;
                                }
                            }
                        }
                    }
                } else { //new addition 3
                    if (hasLosingBoard == false) {
                        for (int x = 0; x < losingBoard.size(); x++) {
                            if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && t == losingBoard.get(x).col4 && t == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                hasLosingBoard = true;
                                col = 3;
                                row = t;
                                break;
                            }
                        }
                    }
                }
            } else { //new addition 4
                if (hasLosingBoard == false) {
                    for (int x = 0; x < losingBoard.size(); x++) {
                        if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && t == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                            hasLosingBoard = true;
                            col = 3;
                            row = t;
                            break;
                        }
                    }
                }
            }
        }

        //reduces column 3
        for (int z = c2 - 1; z >= 0; z--) {
            if (d2 > z) {
                if (e2 > z) {
                    if (f2 > z) {
                        if (g2 > z) {
                            if (h2 > z) {
                                if (i2 > z) {
                                    if (j2 > z) {
                                        // System.out.println("Reduced Board: " + k + "-" + k + "-" + k );
                                        if (hasLosingBoard == false) {
                                            for (int x = 0; x < losingBoard.size(); x++) {
                                                if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && z == losingBoard.get(x).col3 && z == losingBoard.get(x).col4 && z == losingBoard.get(x).col5 && z == losingBoard.get(x).col6 && z == losingBoard.get(x).col7 && z == losingBoard.get(x).col8 && z == losingBoard.get(x).col9 && z == losingBoard.get(x).col10) {
                                                    hasLosingBoard = true;
                                                    col = 2;
                                                    row = z;
                                                    break;
                                                }
                                            }
                                        }
                                    } else {
                                        // System.out.println("Reduced Board: " + k + "-" + k + "-" + c2);
                                        if (hasLosingBoard == false) {
                                            for (int x = 0; x < losingBoard.size(); x++) {
                                                if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && z == losingBoard.get(x).col3 && z == losingBoard.get(x).col4 && z == losingBoard.get(x).col5 && z == losingBoard.get(x).col6 && z == losingBoard.get(x).col7 && z == losingBoard.get(x).col8 && z == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                                    hasLosingBoard = true;
                                                    col = 2;
                                                    row = z;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    // System.out.println("Reduced Board: " + k + "-" + b2 + "-" + c2);
                                    if (hasLosingBoard == false) {
                                        for (int x = 0; x < losingBoard.size(); x++) {
                                            if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && z == losingBoard.get(x).col3 && z == losingBoard.get(x).col4 && z == losingBoard.get(x).col5 && z == losingBoard.get(x).col6 && z == losingBoard.get(x).col7 && z == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                                hasLosingBoard = true;
                                                // System.out.println("Reduced Board: " + k + "-" + b2 + "-" + c2);
                                                col = 2;
                                                row = z;
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else { //new addition
                                if (hasLosingBoard == false) {
                                    for (int x = 0; x < losingBoard.size(); x++) {
                                        if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && z == losingBoard.get(x).col3 && z == losingBoard.get(x).col4 && z == losingBoard.get(x).col5 && z == losingBoard.get(x).col6 && z == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                            hasLosingBoard = true;
                                            col = 2;
                                            row = z;
                                            break;
                                        }
                                    }
                                }
                            }
                        } else { //new addition 2
                            if (hasLosingBoard == false) {
                                for (int x = 0; x < losingBoard.size(); x++) {
                                    if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && z == losingBoard.get(x).col3 && z == losingBoard.get(x).col4 && z == losingBoard.get(x).col5 && z == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                        hasLosingBoard = true;
                                        col = 2;
                                        row = z;
                                        break;
                                    }
                                }
                            }
                        }
                    } else { //new addition 3
                        if (hasLosingBoard == false) {
                            for (int x = 0; x < losingBoard.size(); x++) {
                                if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && z == losingBoard.get(x).col3 && z == losingBoard.get(x).col4 && z == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                    hasLosingBoard = true;
                                    col = 2;
                                    row = z;
                                    break;
                                }
                            }
                        }
                    }
                } else { //new addition 4
                    if (hasLosingBoard == false) {
                        for (int x = 0; x < losingBoard.size(); x++) {
                            if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && z == losingBoard.get(x).col3 && z == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                hasLosingBoard = true;
                                col = 2;
                                row = z;
                                break;
                            }
                        }
                    }
                }
            } else { //new addition 5
                if (hasLosingBoard == false) {
                    for (int x = 0; x < losingBoard.size(); x++) {
                        if (a2 == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && z == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                            hasLosingBoard = true;
                            col = 2;
                            row = z;
                            break;
                        }
                    }
                }
            }
        }

        //reduces column 2
        for (int w = b2 - 1; w >= 0; w--) {
            if (c2 > w) {
                if (d2 > w) {
                    if (e2 > w) {
                        if (f2 > w) {
                            if (g2 > w) {
                                if (h2 > w) {
                                    if (i2 > w) {
                                        if (j2 > w) {
                                            // System.out.println("Reduced Board: " + k + "-" + k + "-" + k );
                                            if (hasLosingBoard == false) {
                                                for (int x = 0; x < losingBoard.size(); x++) {
                                                    if (a2 == losingBoard.get(x).col1 && w == losingBoard.get(x).col2 && w == losingBoard.get(x).col3 && w == losingBoard.get(x).col4 && w == losingBoard.get(x).col5 && w == losingBoard.get(x).col6 && w == losingBoard.get(x).col7 && w == losingBoard.get(x).col8 && w == losingBoard.get(x).col9 && w == losingBoard.get(x).col10) {
                                                        hasLosingBoard = true;
                                                        col = 1;
                                                        row = w;
                                                        break;
                                                    }
                                                }
                                            }
                                        } else {
                                            // System.out.println("Reduced Board: " + k + "-" + k + "-" + c2);
                                            if (hasLosingBoard == false) {
                                                for (int x = 0; x < losingBoard.size(); x++) {
                                                    if (a2 == losingBoard.get(x).col1 && w == losingBoard.get(x).col2 && w == losingBoard.get(x).col3 && w == losingBoard.get(x).col4 && w == losingBoard.get(x).col5 && w == losingBoard.get(x).col6 && w == losingBoard.get(x).col7 && w == losingBoard.get(x).col8 && w == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                                        hasLosingBoard = true;
                                                        col = 1;
                                                        row = w;
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        // System.out.println("Reduced Board: " + k + "-" + b2 + "-" + c2);
                                        if (hasLosingBoard == false) {
                                            for (int x = 0; x < losingBoard.size(); x++) {
                                                if (a2 == losingBoard.get(x).col1 && w == losingBoard.get(x).col2 && w == losingBoard.get(x).col3 && w == losingBoard.get(x).col4 && w == losingBoard.get(x).col5 && w == losingBoard.get(x).col6 && w == losingBoard.get(x).col7 && w == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                                    hasLosingBoard = true;
                                                    // System.out.println("Reduced Board: " + k + "-" + b2 + "-" + c2);
                                                    col = 1;
                                                    row = w;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                } else { //new addition
                                    if (hasLosingBoard == false) {
                                        for (int x = 0; x < losingBoard.size(); x++) {
                                            if (a2 == losingBoard.get(x).col1 && w == losingBoard.get(x).col2 && w == losingBoard.get(x).col3 && w == losingBoard.get(x).col4 && w == losingBoard.get(x).col5 && w == losingBoard.get(x).col6 && w == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                                hasLosingBoard = true;
                                                col = 1;
                                                row = w;
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else { //new addition 2
                                if (hasLosingBoard == false) {
                                    for (int x = 0; x < losingBoard.size(); x++) {
                                        if (a2 == losingBoard.get(x).col1 && w == losingBoard.get(x).col2 && w == losingBoard.get(x).col3 && w == losingBoard.get(x).col4 && w == losingBoard.get(x).col5 && w == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                            hasLosingBoard = true;
                                            col = 1;
                                            row = w;
                                            break;
                                        }
                                    }
                                }
                            }
                        } else { //new addition 3
                            if (hasLosingBoard == false) {
                                for (int x = 0; x < losingBoard.size(); x++) {
                                    if (a2 == losingBoard.get(x).col1 && w == losingBoard.get(x).col2 && w == losingBoard.get(x).col3 && w == losingBoard.get(x).col4 && w == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                        hasLosingBoard = true;
                                        col = 1;
                                        row = w;
                                        break;
                                    }
                                }
                            }
                        }
                    } else { //new addition 4
                        if (hasLosingBoard == false) {
                            for (int x = 0; x < losingBoard.size(); x++) {
                                if (a2 == losingBoard.get(x).col1 && w == losingBoard.get(x).col2 && w == losingBoard.get(x).col3 && w == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                    hasLosingBoard = true;
                                    col = 1;
                                    row = w;
                                    break;
                                }
                            }
                        }
                    }
                } else { //new addition 5
                    if (hasLosingBoard == false) {
                        for (int x = 0; x < losingBoard.size(); x++) {
                            if (a2 == losingBoard.get(x).col1 && w == losingBoard.get(x).col2 && w == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                hasLosingBoard = true;
                                col = 1;
                                row = w;
                                break;
                            }
                        }
                    }
                }
            } else { //new addition 6
                if (hasLosingBoard == false) {
                    for (int x = 0; x < losingBoard.size(); x++) {
                        if (a2 == losingBoard.get(x).col1 && w == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                            hasLosingBoard = true;
                            col = 1;
                            row = w;
                            break;
                        }
                    }
                }
            }
        }

        //reduces column 1
        for (int c = a2 - 1; c >= 1; c--) {
            if (b2 > c) {
                if (c2 > c) {
                    if (d2 > c) {
                        if (e2 > c) {
                            if (f2 > c) {
                                if (g2 > c) {
                                    if (h2 > c) {
                                        if (i2 > c) {
                                            if (j2 > c) {
                                                // System.out.println("Reduced Board: " + k + "-" + k + "-" + k );
                                                if (hasLosingBoard == false) {
                                                    for (int x = 0; x < losingBoard.size(); x++) {
                                                        if (c == losingBoard.get(x).col1 && c == losingBoard.get(x).col2 && c == losingBoard.get(x).col3 && c == losingBoard.get(x).col4 && c == losingBoard.get(x).col5 && c == losingBoard.get(x).col6 && c == losingBoard.get(x).col7 && c == losingBoard.get(x).col8 && c == losingBoard.get(x).col9 && c == losingBoard.get(x).col10) {
                                                            hasLosingBoard = true;
                                                            col = 0;
                                                            row = c;
                                                            break;
                                                        }
                                                    }
                                                }
                                            } else {
                                                // System.out.println("Reduced Board: " + k + "-" + k + "-" + c2);
                                                if (hasLosingBoard == false) {
                                                    for (int x = 0; x < losingBoard.size(); x++) {
                                                        if (c == losingBoard.get(x).col1 && c == losingBoard.get(x).col2 && c == losingBoard.get(x).col3 && c == losingBoard.get(x).col4 && c == losingBoard.get(x).col5 && c == losingBoard.get(x).col6 && c == losingBoard.get(x).col7 && c == losingBoard.get(x).col8 && c == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                                            hasLosingBoard = true;
                                                            col = 0;
                                                            row = c;
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            // System.out.println("Reduced Board: " + k + "-" + b2 + "-" + c2);
                                            if (hasLosingBoard == false) {
                                                for (int x = 0; x < losingBoard.size(); x++) {
                                                    if (c == losingBoard.get(x).col1 && c == losingBoard.get(x).col2 && c == losingBoard.get(x).col3 && c == losingBoard.get(x).col4 && c == losingBoard.get(x).col5 && c == losingBoard.get(x).col6 && c == losingBoard.get(x).col7 && c == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                                        hasLosingBoard = true;
                                                        // System.out.println("Reduced Board: " + k + "-" + b2 + "-" + c2);
                                                        col = 0;
                                                        row = c;
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    } else { //new addition
                                        if (hasLosingBoard == false) {
                                            for (int x = 0; x < losingBoard.size(); x++) {
                                                if (a2 == losingBoard.get(x).col1 && c == losingBoard.get(x).col2 && c == losingBoard.get(x).col3 && c == losingBoard.get(x).col4 && c == losingBoard.get(x).col5 && c == losingBoard.get(x).col6 && c == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                                    hasLosingBoard = true;
                                                    col = 0;
                                                    row = c;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                } else { //new addition 2
                                    if (hasLosingBoard == false) {
                                        for (int x = 0; x < losingBoard.size(); x++) {
                                            if (c == losingBoard.get(x).col1 && c == losingBoard.get(x).col2 && c == losingBoard.get(x).col3 && c == losingBoard.get(x).col4 && c == losingBoard.get(x).col5 && c == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                                hasLosingBoard = true;
                                                col = 0;
                                                row = c;
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else { //new addition 3
                                if (hasLosingBoard == false) {
                                    for (int x = 0; x < losingBoard.size(); x++) {
                                        if (c == losingBoard.get(x).col1 && c == losingBoard.get(x).col2 && c == losingBoard.get(x).col3 && c == losingBoard.get(x).col4 && c == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                            hasLosingBoard = true;
                                            col = 0;
                                            row = c;
                                            break;
                                        }
                                    }
                                }
                            }
                        } else { //new addition 4
                            if (hasLosingBoard == false) {
                                for (int x = 0; x < losingBoard.size(); x++) {
                                    if (c == losingBoard.get(x).col1 && c == losingBoard.get(x).col2 && c == losingBoard.get(x).col3 && c == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                        hasLosingBoard = true;
                                        col = 0;
                                        row = c;
                                        break;
                                    }
                                }
                            }
                        }
                    } else { //new addition 5
                        if (hasLosingBoard == false) {
                            for (int x = 0; x < losingBoard.size(); x++) {
                                if (c == losingBoard.get(x).col1 && c == losingBoard.get(x).col2 && c == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                    hasLosingBoard = true;
                                    col = 0;
                                    row = c;
                                    break;
                                }
                            }
                        }
                    }
                } else { //new addition 6
                    if (hasLosingBoard == false) {
                        for (int x = 0; x < losingBoard.size(); x++) {
                            if (c == losingBoard.get(x).col1 && c == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                                hasLosingBoard = true;
                                col = 0;
                                row = c;
                                break;
                            }
                        }
                    }
                }
            } else { //new addition 7
                if (hasLosingBoard == false) {
                    for (int x = 0; x < losingBoard.size(); x++) {
                        if (c == losingBoard.get(x).col1 && b2 == losingBoard.get(x).col2 && c2 == losingBoard.get(x).col3 && d2 == losingBoard.get(x).col4 && e2 == losingBoard.get(x).col5 && f2 == losingBoard.get(x).col6 && g2 == losingBoard.get(x).col7 && h2 == losingBoard.get(x).col8 && i2 == losingBoard.get(x).col9 && j2 == losingBoard.get(x).col10) {
                            hasLosingBoard = true;
                            col = 0;
                            row = c;
                            break;
                        }
                    }
                }
            }
        }

        //Boards are categorized into Winning or Losing state. Losing and Winning Boards get added into ArrayLists
        Board boardAddition = new Board(a2, b2, c2, d2, e2, f2, g2, h2, i2, j2); //make the board you've given an object
        if (hasLosingBoard == true) { //if you can make a move that gives your opponent a losing board, this is a winning board for you
            //System.out.println("The board " + a2 + "-" + b2 + "-" + c2 + " is a winning board"+ " so make move ("+row+","+""+col+")");
            boardAddition.row = row; //mark the correct move to make if given this board
            boardAddition.col = col;
            winningBoard.add(boardAddition); //add it to your list of winning boards

        } else {
            //if you have a losing board, end the game by choosing (1,1), the bottom left
            //you could modify this to make moves delaying the inevitable, if you wanted
            boardAddition.row = 0;
            boardAddition.col = 0;
            // System.out.println("The board " + a2 + "-" + b2 + "-" + c2 + " is a losing board");
            losingBoard.add(boardAddition); //if you can't transform it into a losingBoard to give to your opponent, it is a losing board
        }
    }

    //add your code to return the row and the column of the chip you want to take.
    //you'll be returning a data type called Point which consists of two integers.
    public Point move(Chip[][] pBoard) {
        columns = new int[10];
        gameBoard = pBoard;
        for (int r = 0; r < gameBoard.length; r++) { //for loop to find Chip in rows
            for (int c = 0; c < gameBoard[0].length; c++) { //for loop to find Chip in columns
                if (gameBoard[r][c].isAlive == true) { //checking if Chip exists in row and column
                    columns[c]++; //adding 1 to array of columns for value to be stored within each
                }
            }
        }

        // System.out.println(Arrays.toString(columns));

        //look through every known board for a matching board configuration and return the recommended move for that board
        //at the moment, the recommendation for every losing board is to immediately lose on purpose
        // System.out.println("[" + columns[0] + ", " + columns[1] + ", " + columns[2] + ", " + columns[3] + ", " + columns[4] + ", " + columns[5] + ", " + columns[6] + ", " + columns[7] + ", " + columns[8] + ", " + columns[9] + "]");
        for (int i = 0; i < allBoards.size(); i++) {
            Board current = allBoards.get(i);
            if (current.col1 == columns[0] && current.col2 == columns[1] && current.col3 == columns[2] && current.col4 == columns[3] && current.col4 == columns[3] && current.col5 == columns[4] && current.col6 == columns[5] && current.col7 == columns[6] && current.col8 == columns[7] && current.col9 == columns[8] && current.col10 == columns[9]) {
                //System.out.println("The recommended move is: " + current.row + ", " + current.col);
                Point move = new Point(current.row, current.col);
                // System.out.println(current.col1+""+current.col2+""+current.col3+""+current.col4+""+current.col5+""+current.col6+""+current.col7+""+current.col8+""+current.col9+""+current.col10);
                return move;
            }
        }
        // System.out.println("No matching board found (error)");

        //this happens if a matching board isn't found, which should never happen
        int column = 0;
        int row = 0;

        row = 1;
        column = 1;

        columns = new int[10];
        Point myMove = new Point(row, column);
        return myMove;
    }

    //storing boards into a game file
    public void objectSerialization(ArrayList<Board> stu) {
        try {
            //Creating FileOutputStream object.
            FileOutputStream fos =
                    new FileOutputStream("myboards.simon");

            //Creating ObjectOutputStream object.
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //write object.
            oos.writeObject(stu);

            //close streams.
            oos.close();
            fos.close();

            System.out.println("Serialized data is saved in myboards.simon");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void objectDeSerialization() {
        try {
            allBoards = null;
            //Creating FileOutputStream object.
            FileInputStream fis =
                    new FileInputStream("myboards.simon");

            //Creating ObjectOutputStream object.
            ObjectInputStream ois = new ObjectInputStream(fis);

            //write object.
            allBoards = (ArrayList<Board>) ois.readObject();

            //close streams.
            ois.close();
            fis.close();

            // System.out.println("Loaded");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
