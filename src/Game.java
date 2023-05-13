import java.util.Scanner;
public class Game 
{
    private Board board;
    private Scanner scanner;
    public Game()
    {
        this.board = new Board();
        this.scanner = new Scanner(System.in);
    }
    public void Run()
    {
        while(true)
        {
            this.board.PrintBoard();
            int UserInput = -11;
            while(!this.board.MakeMove(UserInput, true))
            { 
                UserInput = GetUserInput();
            }
            if(HasGameEnded(board)) return;
            this.board.MakeMove(FindBestMove(board), false);
            if(HasGameEnded(board)) return;
        }
    }
    public boolean HasGameEnded(Board board)
    {
        switch(board.CheckWin())
        {
        case -1:
            System.out.println("You win");
            board.PrintBoard();
            return true;
        case 1:
            System.out.println("Computer wins");
            board.PrintBoard();
            return true;
        case 0:
            System.out.println("It's a draw");
            board.PrintBoard();
            return true;
        default:
            return false;
        }
    }
    public int GetUserInput()
    {
        System.out.println("Please enter the co ordinate of the place you want to move");
        System.out.println("Please use the XY format for input");
        final int UserInput = Integer.parseInt(scanner.nextLine());
        return UserInput;
    }
    static int FindBestMove(Board board)
    {
        int BestVal = -1000;
        int BestMove = -11;
        for(Integer move : board.GetPossibleMoves())
        {
            board.MakeMove(move, false);
            final int MoveVal = MiniMax(board, 0, false);
            board.UndoMove(move);
            if(MoveVal > BestVal)
            {
                BestMove = move;
                BestVal = MoveVal;
            }
        }
        return BestMove;
    }
    static int MiniMax(Board board, int depth, boolean isMaximsing)
    {
        switch(board.CheckWin())
        {
            case -1:
                return -100;
            case 1:
                return 100;
            case 0:
                return 0;
            default:
                break;
        }
        if(isMaximsing)
        {
            int best = -1000;
            for(Integer move : board.GetPossibleMoves())
            {
                board.MakeMove(move,false);
                best = Integer.max(best-depth, MiniMax(board, depth+1, !isMaximsing));
                board.UndoMove(move);
            }
            return best;
        }
        else
        {
            int best = 1000;
            for(Integer move : board.GetPossibleMoves())
            {
                board.MakeMove(move,true);
                best = Integer.min(best+depth, MiniMax(board, depth+1, !isMaximsing));
                board.UndoMove(move);
            }
            return best;
        }   
    }
}
