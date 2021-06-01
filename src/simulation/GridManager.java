package simulation;

import rendering.RenderPanel;
import simulation.cells.*;
import simulation.cells.physicscells.*;

public class GridManager
{
    private static final int mapWidth = 256;
    private static final int mapHeight = 256;

    private static Cell[][] cells;

    private CType curPaintType;


    public GridManager()
    {
        cells = new Cell[mapWidth][mapHeight];

        curPaintType = CType.GROUND;
    }


    // Every tick, do the processing for each particle
    public void update()
    {
        for (Cell[] cellArr : cells) {
            for (Cell c : cellArr) {
                if (c != null) {
                    c.resetUpdated();
                }
            }
        }

        for (Cell[] cellArr : cells) {
            for (Cell c : cellArr) {
                if (c != null && !c.hasUpdated()) {
                    c.update();
                }
            }
        }
    }


    // When the mouse is clicked or dragged, draw in a number of particles based on BrushSize
    public void paint(int mouseX, int mouseY, RenderPanel rp, int brushSize)
    {
        for (int x = mouseX; x < mouseX + brushSize; x++) {
            for (int y = mouseY; y < mouseY + brushSize; y++) {

                // Prevent an unwanted effect that forces water and sand cells to the right when drawn in at a certain angle
                if ((curPaintType == CType.WATER || curPaintType == CType.SAND) && (inBounds(x, y - 1) && cells[x][y - 1] != null && cells[x][y - 1].getType() == curPaintType))
                    break;

                // Draw the cell
                if (inBounds(x, y) && (cells[x][y] == null || cells[x][y].getType() != curPaintType))
                    addCell(x, y, curPaintType);
            }
        }
    }


    // Add a cell of a specified type at a certain position
    public void addCell(int x, int y, CType type)
    {
        Cell cell;
        switch (type) {
            case GROUND:
                cell = new Ground(x, y); break;
            case WATER:
                cell = new Water(x, y); break;
            case SAND:
                cell = new Sand(x, y); break;
            case STONE:
                cell = new Stone(x, y); break;
            case KINETIC_SAND:
                cell = new KineticSand(x, y); break;
            case AIR:
                cell = null; break;
            default:
                throw new IllegalStateException("Type not assigned to a class: " + type);
        }
        cells[x][y] = cell;
    }


    // When one of the number keys is pressed, switch the material being used to paint
    public void keyPressed(char keyChar)
    {
        switch(keyChar) {
            case '1':
                curPaintType = CType.GROUND; break;
            case '2':
                curPaintType = CType.WATER; break;
            case '3':
                curPaintType = CType.SAND; break;
            case '4':
                curPaintType = CType.KINETIC_SAND; break;
            case '5':
                curPaintType = CType.STONE; break;
            case '6':
                curPaintType = CType.AIR; break;
        }
    }


    // Check whether a certain set of coordinates is within the grid
    public static boolean inBounds(int x, int y)
    {
        return x >= 0 && x < mapWidth && y >= 0 && y < mapHeight;
    }


    // Methods to return the instance variables
    public static int getMapWidth() { return mapWidth; }
    public static int getMapHeight() { return mapHeight; }
    public static Cell[][] getCells() { return cells; }
}
