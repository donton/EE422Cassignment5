package assignment5;

import java.util.List;

public abstract class Implement extends Critter
{
    /**
     * adjusts the Critter's x and y coordinates according to their direction
     * basically a long series of switch statements
     */
    boolean hasMoved = getMoved();
    boolean isFighting = getFighting();
    int dir = getDir();
    int x_coord = getX();
    int y_coord = getY();

    protected static void moveWalk() {
        if (!hasMoved) {
            int x_coordNew = 0;
            int y_coordNew = 0;
            boolean xSafe = false;
            boolean ySafe = false;

            switch (dir) {
                case 0:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == Params.world_width - 1)
                    {
                        x_coordNew = 0;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            x_coord = x_coordNew;
                        }
                    }
                    else
                    {
                        x_coordNew = x_coord + 1;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            x_coord = x_coordNew;
                        }
                    }
                    break;
                case 1:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == Params.world_width - 1)
                    {
                        x_coordNew = 0;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            xSafe = true;
                        }
                        if (y_coord == 0)
                        {
                            y_coordNew = Params.world_height - 1;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                        else
                        {
                            y_coordNew = y_coord - 1;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                y_coord = y_coordNew;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    }
                    else if (y_coord == 0)
                    {
                        y_coordNew = Params.world_height - 1;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            ySafe = true;
                        }
                        if (x_coord == Params.world_width - 1)
                        {
                            x_coordNew = 0;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                        else
                        {
                            x_coordNew = x_coordNew + 1;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    }
                    else
                    {
                        x_coordNew = x_coord + 1;
                        y_coordNew = y_coord - 1;
                        if (isAdjacentSafe(x_coordNew, y_coordNew))
                        {
                            x_coord = x_coordNew;
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 2:
                    if (isFighting && hasMoved)
                        return;
                    if (y_coord == 0)
                    {
                        y_coordNew = Params.world_height - 1;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            y_coord = y_coordNew;
                        }
                    }
                    else
                    {
                        y_coordNew =  y_coord - 1;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 3:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == 0)
                    {
                        x_coordNew = Params.world_width - 1;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            xSafe = true;
                        }
                        if (y_coord == 0)
                        {
                            y_coordNew = Params.world_height - 1;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                        else
                        {
                            y_coordNew = y_coord - 1;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }

                    }
                    else if (y_coord == 0)
                    {
                        y_coordNew = Params.world_height - 1;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            ySafe = true;
                        }
                        if (x_coord == 0)
                        {
                            x_coordNew = Params.world_width - 1;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                        else
                        {
                            x_coordNew = x_coord - 1;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    }
                    else
                    {
                        x_coordNew = x_coord - 1;
                        y_coordNew = y_coord - 1;
                        if (isAdjacentSafe(x_coordNew, y_coordNew))
                        {
                            x_coord = x_coordNew;
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 4:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == 0)
                    {
                        x_coordNew = Params.world_width - 1;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            x_coord = x_coordNew;
                        }
                    }
                    else
                    {
                        x_coordNew = x_coord - 1;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            x_coord = x_coordNew;
                        }
                    }
                    break;
                case 5:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == 0)
                    {
                        x_coordNew = Params.world_width - 1;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            xSafe = true;
                        }
                        if (y_coord == Params.world_height - 1)
                        {
                            y_coordNew = 0;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                        else
                        {
                            y_coordNew = y_coord + 1;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }

                    }
                    else if (y_coord == Params.world_height - 1)
                    {
                        y_coordNew = 0;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            ySafe = true;
                        }
                        if (x_coord == 0)
                        {
                            x_coordNew = Params.world_width - 1;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                        else
                        {
                            x_coordNew = x_coord - 1;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    }
                    else
                    {
                        x_coordNew = x_coord - 1;
                        y_coordNew = y_coord + 1;
                        if (isAdjacentSafe(x_coordNew, y_coordNew))
                        {
                            x_coord = x_coordNew;
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 6:
                    if (isFighting && hasMoved)
                        return;
                    if (y_coord == Params.world_height - 1)
                    {
                        y_coordNew = 0;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            y_coord = y_coordNew;
                        }

                    }
                    else
                    {
                        y_coordNew = y_coord + 1;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 7:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == Params.world_width - 1) {
                        x_coordNew = 0;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            xSafe = true;
                        }
                        if (y_coord == Params.world_height - 1)
                        {
                            y_coordNew = 0;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                        else
                        {
                            y_coordNew = y_coord + 1;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }

                    }
                    else if (y_coord == Params.world_height - 1)
                    {
                        y_coordNew = 0;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            ySafe = true;
                        }
                        if (x_coord == Params.world_width - 1)
                        {
                            x_coordNew = 0;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                        else
                        {
                            x_coordNew = x_coord + 1;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    }
                    else
                    {
                        x_coordNew = x_coord + 1;
                        y_coordNew = y_coord + 1;
                        if (isAdjacentSafe(x_coordNew, y_coordNew))
                        {
                            x_coord = x_coordNew;
                            y_coord = y_coordNew;
                        }
                    }
                    break;
            }
        }
        hasMoved = true;
    }

    /**
     * determines if a space on the board is occupied
     * by any member of the population
     * @param x x_coord to check
     * @param y y_coord to check
     * @return true or false
     */
    private boolean isAdjacentSafe(int x, int y)
    {
        List<Critter> population = getPop();
        for (Critter c : population)
        {
            if (c.getX() == x && c.getY() == y)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * adjusts the Critter's x and y coordinates according to their direction
     * basically a long series of switch statements
     * difference from walk - move farther, larger energy deduction
     */
    protected static void moveRun() {
        if (!hasMoved) {
            int x_coordNew = 0;
            int y_coordNew = 0;
            boolean xSafe = false;
            boolean ySafe = false;
            int newLeftEdgeX = -1;
            int newTopEdgeY = -1;
            int newRightEdgeX = -1;
            int newBottomEdgeY = -1;

            if (Params.world_width - 1 - x_coord == 0) {
                newLeftEdgeX = 1;
            } else if (Params.world_width - 1 - x_coord == 1) {
                newLeftEdgeX = 0;
            }
            if (Params.world_height - 1 - y_coord == 0) {
                newTopEdgeY = 1;
            } else if (Params.world_height - 1 - y_coord == 1) {
                newTopEdgeY = 0;
            }

            if (x_coord == 0) {
                newRightEdgeX = Params.world_width - 2;
            } else if (x_coord == 1) {
                newRightEdgeX = Params.world_width - 1;
            }
            if (y_coord == 0) {
                newBottomEdgeY = Params.world_height - 2;
            } else if (y_coord == 1) {
                newBottomEdgeY = Params.world_height - 1;
            }

            switch (dir) {
                case 0:
                    if (isFighting && hasMoved)
                        return;
                    if (newLeftEdgeX == 0 || newLeftEdgeX == 1)
                    {
                        x_coordNew = newLeftEdgeX;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            x_coord = x_coordNew;
                        }
                    }
                    else
                    {
                        x_coordNew = x_coord + 2;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            x_coord = x_coordNew;
                        }
                    }
                    break;
                case 1:
                    if (isFighting && hasMoved)
                        return;
                    if (newLeftEdgeX == 0 || newLeftEdgeX == 1) {
                        x_coordNew = newLeftEdgeX;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            xSafe = true;
                        }
                        if (y_coord == 0 || y_coord == 1) {
                            y_coordNew = newBottomEdgeY;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        } else {
                            y_coordNew = y_coord - 2;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }

                    } else if (y_coord == 0 || y_coord == 1) {
                        y_coordNew = newBottomEdgeY;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            ySafe = true;
                        }
                        if (newLeftEdgeX == 0 || newLeftEdgeX == 1) {
                            x_coordNew = newLeftEdgeX;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        } else {
                            x_coordNew = x_coord + 2;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    } else {
                        x_coordNew = x_coord + 2;
                        y_coordNew = y_coord - 2;
                        if (isAdjacentSafe(x_coordNew, y_coordNew))
                        {
                            x_coord = x_coordNew;
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 2:
                    if (isFighting && hasMoved)
                        return;
                    if (y_coord == 0 || y_coord == 1) {
                        y_coordNew = newBottomEdgeY;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            y_coord = y_coordNew;
                        }
                    } else {
                        y_coordNew = y_coord - 2;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 3:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == 0 || y_coord == 1) {
                        x_coordNew = newRightEdgeX;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            xSafe = true;
                        }
                        if (y_coord == 0 || y_coord == 1) {
                            y_coordNew = newBottomEdgeY;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        } else {
                            y_coordNew = y_coord - 2;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }

                    } else if (y_coord == 0 || y_coord == 1) {
                        y_coordNew = newBottomEdgeY;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            ySafe = true;
                        }
                        if (x_coord == 0 || x_coord == 1) {
                            x_coordNew = newRightEdgeX;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        } else {
                            x_coordNew = x_coord - 2;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    } else {
                        x_coordNew = x_coord - 2;
                        y_coordNew = y_coord - 2;
                        if (isAdjacentSafe(x_coordNew, y_coordNew))
                        {
                            x_coord = x_coordNew;
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 4:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == 0 || x_coord == 1)
                    {
                        x_coordNew = newRightEdgeX;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            x_coord = x_coordNew;
                        }
                    }
                    else
                    {
                        x_coordNew = x_coord - 2;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            x_coord = x_coordNew;
                        }
                    }
                    break;
                case 5:
                    if (isFighting && hasMoved)
                        return;
                    if (x_coord == 0 || x_coord == 1) {
                        x_coordNew = newRightEdgeX;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            xSafe = true;
                        }
                        if (newTopEdgeY == 0 || newTopEdgeY == 1) {
                            y_coordNew = newBottomEdgeY;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        } else {
                            y_coordNew = y_coord + 2;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }

                    } else if (newTopEdgeY == 0 || newTopEdgeY == 1) {
                        y_coordNew = newTopEdgeY;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            ySafe = true;
                        }
                        if (x_coord == 0 || x_coord == 1) {
                            x_coordNew = newRightEdgeX;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        } else {
                            x_coordNew = x_coord - 2;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    } else {
                        x_coordNew = x_coord - 2;
                        y_coordNew = y_coord + 2;
                        if (isAdjacentSafe(x_coordNew, y_coordNew))
                        {
                            x_coord = x_coordNew;
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 6:
                    if (isFighting && hasMoved)
                        return;
                    if (newTopEdgeY == 0 || newTopEdgeY == 1)
                    {
                        y_coordNew = newTopEdgeY;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            y_coord = y_coordNew;
                        }
                    }
                    else
                    {
                        y_coordNew = y_coord + 2;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            y_coord = y_coordNew;
                        }
                    }
                    break;
                case 7:
                    if (isFighting && hasMoved)
                        return;
                    if (newLeftEdgeX == 0 || newLeftEdgeX == 1) {
                        x_coordNew = newLeftEdgeX;
                        if (isAdjacentSafe(x_coordNew, y_coord))
                        {
                            xSafe = true;
                        }
                        if (newTopEdgeY == 0 || newTopEdgeY == 1) {
                            y_coordNew = newTopEdgeY;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        } else {
                            y_coordNew = y_coord + 2;
                            if (isAdjacentSafe(x_coord, y_coordNew))
                            {
                                ySafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }

                    } else if (newTopEdgeY == 0 || newTopEdgeY == 1) {
                        y_coordNew = newTopEdgeY;
                        if (isAdjacentSafe(x_coord, y_coordNew))
                        {
                            ySafe = true;
                        }
                        if (newLeftEdgeX == 0 || newLeftEdgeX == 1) {
                            x_coordNew = newLeftEdgeX;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        } else {
                            x_coordNew = x_coord + 2;
                            if (isAdjacentSafe(x_coordNew, y_coord))
                            {
                                xSafe = true;
                            }
                            if (xSafe && ySafe) {
                                x_coord = x_coordNew;
                                y_coord = y_coordNew;
                            }
                        }
                    } else {
                        x_coordNew = x_coord + 2;
                        y_coordNew = y_coord + 2;
                        if (isAdjacentSafe(x_coordNew, y_coordNew))
                        {
                            x_coord = x_coordNew;
                            y_coord = y_coordNew;
                        }
                    }
                    break;
            }
        }
        hasMoved = true;
    }

    /**
     * actually implements the process of conflict resolution for two critters
     * @param a the first critter involved in the conflict
     * @param b the second critter involved in the conflict
     */
    protected static void fighting(Critter a, Critter b)
    {
        int ax_coord = a.getX();
        int ay_coord = a.getY();
        int a_energy = a.getEnergy();
        int bx_coord = b.getX();
        int by_coord = b.getY();
        int b_energy = b.getEnergy();
        boolean aFight = a.fight(b.toString());
        boolean bFight = b.fight(a.toString());

        if (!aFight && !bFight)
        {
            if (!a.toString().equals("@"))
            {
                a.run(a.getDir());
            }
            if (!b.toString().equals("@"))
            {
                b.run(b.getDir());
            }
        }
        if (a_energy > 0 && b_energy > 0 &&
                ax_coord == bx_coord &&
                ay_coord == by_coord)
        {
            int diceA = -1;
            int diceB = -1;
            if (!aFight)
            {
                diceA = 0;
            }
            else
            {
                diceA = a.getRandomInt(a_energy);
            }
            if (!bFight)
            {
                diceB = 0;
            }
            else
            {
                diceB = b.getRandomInt(b_energy);
            }

            if (diceA > diceB)
            {
                a.setEnergy(a_energy + Math.round(b_energy / 2));
                b.setEnergy(0);
            }
            else if (diceB > diceA || diceA == diceB)
            {
                b.setEnergy(b_energy + Math.round(a_energy / 2));
                a.setEnergy(0);
            }
        }
        return;
    }
}
