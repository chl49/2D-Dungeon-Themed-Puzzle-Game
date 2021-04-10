package Game;

import java.util.List;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.ArrayList;

/**
* AIPathManager class
* Calculates paths for enemies to move towards the player.
* Holds references to the Player and Board.
*/

public class AIPathManager 
{
    public Player playerRef;
    public Board boardRef;

    /**
    * Calculates paths for enemies to move towards the player.
    * Holds references to the Player and Board.
    * Creates and directional array in the constructor,
    * diagonal movement is commented out
    *
    * @param    player reference to the Player object
    * @param    board reference to the Board object
    */
    public AIPathManager(Player player, Board board)
    {
        playerRef = player;
        boardRef = board;
    }

    /**
    * Takes a position for the enemy, does path finding with A*, and returns the next position.
    * This enemy should move one step towards the player.
    * Uses A* search algorithm: 
    * https://medium.com/@nicholas.w.swift/easy-a-star-pathfinding-7e6689c7f7b2
    *
    * @param    currentPos Current enemy position
    * @return   Next position for the enemy to move to
    */

    public int getNextPos(int currentPos)
    {
        var startNode = new GridCell(null, currentPos);

        var targetPlayerPos = playerRef.getNextPosition();
        if(!boardRef.isEmpty(playerRef.getNextPosition()))
        {
            targetPlayerPos = playerRef.getPosition();
        }

        var endNode = new GridCell(null, targetPlayerPos);

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

                int count = 1;
                int nextPos = currentPos;

                while(nextPos == currentPos 
                && (path.size() - count) >= 0)
                {
                    nextPos = path.get(path.size()-count).pos;
                    count++;
                }

                return nextPos;
            }

            var children = new ArrayList<GridCell>();

            for(var dir : Helper.getDirections())
            {
                int xPos = boardRef.calcXPos(currentNode.pos);
                int yPos = boardRef.calcYPos(currentNode.pos);
                
                xPos += dir.getKey();
                yPos += dir.getValue();

                if(xPos < 0 || xPos >= boardRef.rowSize
                || yPos < 0 || yPos >= boardRef.rowCount)
                {
                    continue;
                }

                var newPos = boardRef.calcPosFrom2D(xPos, yPos);

                if(!boardRef.isEmpty(newPos))
                {
                    continue;
                }

                //check if this position already considered
                boolean explored = false;

                for(var closedNode : closedList)
                {
                    if(closedNode.pos == newPos)
                    {
                        explored = true;
                        break;
                    }
                }

                if(explored)
                {
                    continue;
                }

                for(var openNode : openList)
                {
                    if(openNode.pos == newPos)
                    {
                        explored = true;
                        break;
                    }
                }

                if(explored)
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

                int childXPos = boardRef.calcXPos(child.pos);
                int childYPos = boardRef.calcYPos(child.pos);
                int endXPos = boardRef.calcXPos(endNode.pos);
                int endYPos = boardRef.calcYPos(endNode.pos);

                //distance formula between two 2D points before the root
                child.h = (float)(Math.pow((float)(childXPos - endXPos), 2) + Math.pow((float)(childYPos - endYPos), 2));
                child.h = (float)Math.pow(child.h, 0.5f);  //square root to get magnitude

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

    /**
    * Takes a position for the enemy, does path finding with A*, and sets the next position
    * in the nextPos variable. The updateMovables function will handling updating the position.
    *
    * @param    enemy enemy whose position is to be updated
    */
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
    }

    /**
    * GridCell class
    * Nested class inside AIPathManager.
    * Used for A* algorithm only.
    */
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
