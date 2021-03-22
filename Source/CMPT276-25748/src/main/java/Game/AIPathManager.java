package Game;
/*
Tony Ho
AIPathManager class
Calculates the next position an enemy should move to.
Requires: Row size, the board, current player position
Pass current enemy position to GetNextPos, returns next position to move to
Assumes can move in any direction
*/

import java.util.List;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.ArrayList;

public class AIPathManager 
{
    public int playerPos;
    public char[] board;
    public int rowSize;

    public List<AbstractMap.SimpleEntry<Integer, Integer>> directions;

    public Movable playerRef;

    public AIPathManager()
    {
        directions = Arrays.asList(
        new AbstractMap.SimpleEntry<Integer, Integer>(1, 0),
        new AbstractMap.SimpleEntry<Integer, Integer>(1, -1), 
        new AbstractMap.SimpleEntry<Integer, Integer>(0, -1), 
        new AbstractMap.SimpleEntry<Integer, Integer>(-1, -1), 
        new AbstractMap.SimpleEntry<Integer, Integer>(-1, 0), 
        new AbstractMap.SimpleEntry<Integer, Integer>(-1, 1), 
        new AbstractMap.SimpleEntry<Integer, Integer>(0, 1), 
        new AbstractMap.SimpleEntry<Integer, Integer>(1, 1)
        );

        //TODO: hook up playerRef (request from GameManager?)
        //playerRef = GameManager.GetPlayer();
    }

    //Uses A* search algorithm
    //https://medium.com/@nicholas.w.swift/easy-a-star-pathfinding-7e6689c7f7b2

    public int getNextPos(int currentPos)
    {
        var startNode = new GridCell(null, currentPos);
        var endNode = new GridCell(null, playerRef.getPosition());

        List<GridCell> openList = new ArrayList<GridCell>();
        List<GridCell> closedList = new ArrayList<GridCell>();

        openList.add(startNode);

        while(openList.size() > 0)
        {
            var currentNode = openList.get(0);
            var currentIndex = 0;

            for(int i = 0; i < openList.size(); ++i)
            {
                var node = openList.get(i);

                if(node.f < currentNode.f)
                {
                    currentNode = node;
                    currentIndex = i;
                }
            }

            openList.remove(currentIndex);
            closedList.add(currentNode);

            if(currentNode.pos == endNode.pos)
            {
                var current = currentNode;
                var path = new ArrayList<GridCell>();

                while(current != null)
                {
                    path.add(current);
                    current = current.parent;
                }

                return path.get(path.size()-1).pos;
            }

            var children = new ArrayList<GridCell>();

            for(var dir : directions)
            {
                int rowPos = currentNode.pos / rowSize;
                int colPos = currentNode.pos % rowSize;
                
                rowPos += dir.getKey();
                colPos += dir.getValue();

                //TODO: adapt this to movable/nonmovable
                //O means open/moveable
                var newPos = rowPos * rowSize + colPos;

                if(newPos >= board.length || board[newPos] != 'O')
                {
                    continue;
                }

                var newNode = new GridCell(currentNode, newPos);
                children.add(newNode);
            }

            for(var child : children)
            {
                for(var closedChild : closedList)
                {
                    if(child.pos == closedChild.pos)
                    {
                        continue;
                    }
                }

                int childXPos = child.pos / rowSize;
                int childYPos = child.pos % rowSize;

                int endXPos = endNode.pos / rowSize;
                int endYPos = endNode.pos % rowSize;

                //distance formula between two 2D points before the root
                child.h = (float)(Math.pow((float)(childXPos - endXPos), 2) + Math.pow((float)(childYPos - endYPos), 2));

                child.g = currentNode.g + 1;
                child.f = child.g + child.h;

                for(var openNode : openList)
                {
                    if(child.pos == openNode.pos && child.g > openNode.g)
                    {
                        continue;
                    }
                }

                openList.add(child);
            }
        }

        //if here, no valid moves
        return -1;
    }

    public void setNextPosition(Movable enemy)
    {
        int nextPos = getNextPos(enemy.getPosition());
        
        if(nextPos > 0)
        {
            enemy.setNextPosition(nextPos);
        }
        else
        {
            enemy.setNextPosition(enemy.getPosition());
        }

        //let GameManager call enemy.updatePosition
    }

    private class GridCell
    {
        public float f = 0.0f;
        public float g = 0.0f;
        public float h = 0.0f;

        public GridCell parent = null;
        public int pos = 0;

        public GridCell(GridCell _parent, int _pos)
        {
            parent = _parent;
            pos = _pos;
        }
    }
}
