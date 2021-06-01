package simulation.cells;

import simulation.CType;

public class Ground extends Cell
{
    public Ground(int x, int y)
    {
        super(x, y);
        type = CType.GROUND;
    }
}