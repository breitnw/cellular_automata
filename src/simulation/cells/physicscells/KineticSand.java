package simulation.cells.physicscells;

import simulation.CType;

import java.awt.*;

public class KineticSand extends PhysicsCell
{
    public KineticSand(int x, int y)
    {
        super(x, y);
        type = CType.KINETIC_SAND;
        color = Color.PINK;

        possibleMovements = new int[][]{
                {0, 2, 4}, {0, 1, 4}, {1, 2, 4}, {-1, 2, 4}
        };
    }
}