import java.io.Serializable;

public class Board implements Serializable {
    int col1, col2, col3, col4, col5, col6, col7, col8, col9, col10; //3 columns used to write out boards
    int[] cols;
    int row,col; //used as variables for the Best Move

    // creating column variables for the board class called in Chomp
    Board(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j){
        col1=a; //first column = a
        col2=b; //second column = b
        col3=c; //third column = c
        col4=d; //fourth column = d
        col5=e; //fifth column = e
        col6=f; //sixth column = f
        col7=g; //seventh column = g
        col8=h; //eighth column = g
        col9=i; //ninth column = i
        col10=j; //tenth column = j

        cols = new int[]{a, b, c, d, e, f, g, h,i, j};
    }
}

