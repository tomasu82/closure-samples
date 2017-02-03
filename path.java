// start 11:11 PM
// 11:41 PM something working
// 11:48 PM no backtracking


/*
    Rules:
    You do not need to consume all fuel to possible end locations. You can use 0 fuel
    


*/
import java.util.*;

public class path {
    public static void main(String[] args) {

        /*
        5 x 6
        1   1   1   1   1
        1   1   1   1   1
        1   1   1   1   1
        1   1   1   1   1
        1   1   1   1   1
        1   3   1   1   1

        */
        int[][] grid = new int[5][6];

        for (int i = 0 ; i<5 ; i++) {
            for (int j = 0 ; j<6 ; j++) {
                grid[i][j] = 1;

                if (i == 1 && j == 0)
                    grid[i][j] = 7;
            }
        }

        Set<String> coords = getEndLocations(new Coord(0,0), grid, 4);

        for (String coord:coords) {
            System.out.println(coord);
        }
    }


    public static Set<String> getEndLocations(Coord start, int[][] grid, int fuel) {
        Queue<Move> nextTraversal = new LinkedList<Move>();
        Set<String> endLocations = new HashSet<String>();
        Set<String> visited = new HashSet<String>();

        nextTraversal.add(new Move(start, fuel));

        int maxx = grid.length;
        if (maxx == 0)
            return new HashSet<String>();

        int maxy = grid[0].length;

        while (nextTraversal.peek() != null) {
            Move currMove = nextTraversal.remove();
            Coord currCoord = currMove.coordinate;
            int currFuel = currMove.fuel;

            if (currMove.fuel >= 0) {
                endLocations.add("X: " + currMove.coordinate.x + " Y: " + currMove.coordinate.y);

                if (currMove.fuel == 0)
                    continue;
            }

            if (visited.contains("X: " + currMove.coordinate.x + " Y: " + currMove.coordinate.y))
                continue;
            else
                visited.add("X: " + currMove.coordinate.x + " Y: " + currMove.coordinate.y);

                
            // add north. x,y+1
            if (currCoord.y+1 < maxy && grid[currCoord.x][currCoord.y + 1] <= currFuel)
                nextTraversal.add(
                    new Move(new Coord(currCoord.x, currCoord.y+1), 
                        currFuel - grid[currCoord.x][currCoord.y+1]));
            
            // add south x,y-1
            if (currCoord.y-1 >= 0 && grid[currCoord.x][currCoord.y - 1] <= currFuel)
                nextTraversal.add(
                    new Move(new Coord(currCoord.x, currCoord.y-1), currFuel - grid[currCoord.x][currCoord.y-1]));

            // add east, x+1,y
            if (currCoord.x+1 < maxx && grid[currCoord.x+1][currCoord.y] <= currFuel)
                nextTraversal.add(
                    new Move(new Coord(currCoord.x+1, currCoord.y), currFuel - grid[currCoord.x+1][currCoord.y]));

            // add west, x-1,y
            if (currCoord.x-1 >= 0 && grid[currCoord.x-1][currCoord.y] <= currFuel)
                nextTraversal.add(
                    new Move(new Coord(currCoord.x-1, currCoord.y), currFuel - grid[currCoord.x-1][currCoord.y]));
        }

        return endLocations;
    }
}

class Move {
    public Coord coordinate;
    public int fuel;

    public Move(Coord coord, int fuel) {
        this.coordinate = coord;
        this.fuel = fuel;
    }
}

class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
}