package com.company;

public class Task3_Demo {
    private char[][] result;
    private Pair startPos , endPos;
    private int[][] maze;
    public static int countX = 0;
    public static int countN = 0;

    public Task3_Demo(int[][] maze , Pair startPos , Pair endPos){
        this.maze = maze;
        this.startPos = startPos;
        this.endPos = endPos;
        result = new char[maze.length][maze[0].length];
    }


    public char solver(){
        result[startPos.x][startPos.y] = 'S';
        result[endPos.x][endPos.y] = 'E';

        for(int i =0; i< maze.length; i++){
            for(int j = 0 ; j < maze[i].length; j++){
                if(maze[i][j] == 1){
                    result[i][j] = '#';
                }


            }
        }
        char succ = shortest_way(maze , startPos);

        for(int i =0 ; i< maze.length; i++){
            for(int j = 0 ; j <maze[i].length; j++){
                if(result[i][j] == 0)
                    result[i][j] = '_';

            }
        }
        result[startPos.x][startPos.y] = 'S';
        result[endPos.x][endPos.y] = 'E';

        return succ;
    }
    public char shortest_way(int[][] maze , Pair pos){ //pos = starting position
        if(result[pos.x][pos.y] == 'E') {
            return 'X';    //Found the end.
        }
        else
            {
            char c = 0;
            result[pos.x][pos.y] = 'V';	//Visited

            int offset = pos.x + 1;
            if(offset >= maze.length){
                offset = 0;
                System.out.println("Exception handled");
            }

            char south = result[offset][pos.y];
            if(south == 0 || south == 'E')
            {
                //Go south
                c = shortest_way(maze, new Pair(offset, pos.y));
                result[offset][pos.y] = c;
                countX++;
                if(c == 'X') return 'X'; //base-case
            }

            offset = pos.y + 1;
            if(offset >= maze[0].length){
                offset = 0;
                System.out.println("Exception handled");
            }

            char east = result[pos.x][offset];
            if(east == 0 || east == 'E')
            {
                //Go east
                c = shortest_way(maze, new Pair(pos.x , offset));
                result[pos.x][offset] = c;
                countX++;
                if(c == 'X') return 'X';
            }

            offset = pos.y - 1;
            if(offset < 0){
                offset = maze[0].length - 1;
                System.out.println("Exception handled");
            }

            char west = result[pos.x][offset];
            if(west == 0 || west == 'E')
            {
                //Go west
                c = shortest_way(maze, new Pair(pos.x , offset));
                result[pos.x][offset] = c;
                countX++;
                if(c == 'X') return 'X';
            }

            offset = pos.x - 1;
            if(offset < 0){
                offset = maze.length - 1;
                System.out.println("Exception handled");
            }

            char north = result[offset][pos.y];
            if(north == 0 || north == 'E')
            {
                //Go north

                c = shortest_way(maze, new Pair(offset, pos.y));
                result[offset][pos.y] = c;
                countX++;
                if(c == 'X')
                    return 'X';
            }

        }
        countN++;
        return 'N';
    }



    public void print_result()
    {
        for(int i=0; i<result.length; i++) {
            for(int j=0; j<result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args){
        int[][] bfs  = {{1,1,1,1,1,1},
                {1,0,0,1,2,1},
                {1,0,1,0,0,1} ,
                {-1,0,0,0,1,1},
                {1,0,1,0,0,1},
                {1,1,1,1,1,1} };
        int sX = 0;
        int sY = 0;
        int eX = 0;
        int eY = 0;


        for(int i = 0; i < bfs.length; i++){
            for(int j = 0 ; j< bfs[i].length ; j++ ){
                if(bfs[i][j] == 2){
                    sX = i;
                    sY = j;
                }
                if(bfs[i][j] == -1){
                    eX = i;
                    eY = j;
                }
            }
        }
        Pair startPos = new Pair(sX,sY);
        Pair endPos = new Pair(eX , eY);
        Task3_Demo ms = new Task3_Demo(bfs , startPos , endPos);
        char succ = ms.solver();
        ms.print_result();


        if(succ != 'X')
            System.out.println("Maze can not be solved.");
        else{
            System.out.println("Count of steps = " + (countX - countN));
        }
    }

}


class Pair{
    int x, y ;

    public Pair(int x , int y){
        this.x = x;
        this.y = y;

    }

    public String toString(){
        return "[x,y]" + " [" + x + "," + y + "]";
    }
}
