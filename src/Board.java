import java.util.List;
import java.util.ArrayList;
public class Board 
{
    private char[][] board;
    final static char NULLCHAR = '~';
    final static int WIDTH = 3;
    final static int HEIGHT = 3;

    public Board(){
        this.board = new char[WIDTH][HEIGHT];

        for(int i = 0; i < WIDTH;++i)
        {
            for(int j = 0; j < HEIGHT;++j)
            {
                this.board[i][j] = NULLCHAR;
            }
        }
    }
    public boolean MakeMove(int GridReference,boolean PlayerOne)
    {
        if(!IsInBounds(GridReference)) return false;

        final int Y = (GridReference / 10)-1;
        final int X = (GridReference % 10)-1;

        if(this.board[X][Y] != NULLCHAR) return false;

        this.board[X][Y] = PlayerOne ? 'X' : 'O';
        return true;
    }
    private boolean IsInBounds(int GridReference)
    {
        final int Y = (GridReference / 10)-1;
        final int X = (GridReference % 10)-1;
        return (X > -1 && X < 3 && Y > -1 && Y < 3);
    }
    public void PrintBoard()
    {
        String PrintVal = "";
        for(int i = WIDTH -1; i > -1;i--)
        {
            PrintVal += (i+1);
            for(int j = 0; j < HEIGHT;++j)
            {
                PrintVal += "|" + Character.toString(this.board[i][j]);
            }
            PrintVal += "|\n";
        }
        PrintVal += "  1 2 3\n";
        System.out.print(PrintVal);
    }
    public int CountRemainingMoves()
    {
        int MovesRemaining = 0;
        for(int i = 0; i < WIDTH;++i)
        {
            for(int j = 0; j < HEIGHT;++j)
            {
                if(this.board[i][j] == NULLCHAR) MovesRemaining++;
            }
        }
        return MovesRemaining;
    }
    //-1 if player1 wins, 0 if draw, 1 if player 2 wins, null if no one wins yet
    public Integer CheckWin()
    {
        for(int i = 0; i < 3;++i)
        {
            if
            (
                this.board[i][0]    ==  this.board[i][1]
                && this.board[i][1] ==  this.board[i][2]
                && this.board[i][1] !=  NULLCHAR
            )
              return (this.board[i][0] == 'X') ? -1 : 1;
        }
        for(int i = 0; i < 3;++i)
        {
            if
            ( 
                this.board[0][i]    ==  this.board[1][i]
                && this.board[1][i] ==  this.board[2][i]
                && this.board[1][i] !=  NULLCHAR
            )
              return (this.board[0][i] == 'X') ? -1 : 1;
        }
        if
        (
            this.board[0][0] == this.board[1][1] && this.board[0][0] == this.board[2][2] && this.board[0][0]  != NULLCHAR
        )
            return (this.board[0][0] == 'X') ? -1 : 1;
        if
        (
            this.board[0][2] == this.board[1][1] && this.board[0][2] == this.board[2][0] && this.board[0][2]  != NULLCHAR
        )
            return (this.board[0][2] == 'X') ? -1 : 1;

        if(CountRemainingMoves() == 0) return 0;
        return 69;
    }
    public List<Integer> GetPossibleMoves()
    {
        List<Integer> PossibleMoves = new ArrayList<Integer>();
        for(int i = 0; i < WIDTH;++i)
        {
            for(int j = 0; j < HEIGHT;++j)
            {
                if(this.board[i][j] == NULLCHAR) PossibleMoves.add(ToGridRef(i, j));
            }
        }
        return PossibleMoves;
    }
    private int ToGridRef(int x, int y)
    {
        return (y+1)*10 + (x+1);
    }
    public void UndoMove(int GridReference)
    {
        final int Y = (GridReference / 10)-1;
        final int X = (GridReference % 10)-1;
        this.board[X][Y] = NULLCHAR;
    }
}
