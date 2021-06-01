package simulation.cells;

import simulation.CType;

import java.awt.*;

public class Ground extends Cell
{
    public Ground(int x, int y)
    {
        super(x, y);
        type = CType.GROUND;
        color = Color.GRAY;
    }
}