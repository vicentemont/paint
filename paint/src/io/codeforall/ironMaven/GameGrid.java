package io.codeforall.ironMaven;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.awt.*;
import java.util.LinkedList;

public class GameGrid {

    private int cellDimentions = 10;
    private int padding = 10;
    private int cols;
    private int rows;
    private int gridWidth;
    private int gridHeight;
    private LinkedList<RectagleObject> gridCells = new LinkedList<>();
    private LinkedList<RectagleObject> gridBounds = new LinkedList<>();

    public GameGrid(int cols, int rows) {
        createGrid(cols, rows);
        this.cols = cols;
        this.rows = rows;
        this.gridWidth = padding * 2 + cellDimentions * cols;
        this.gridHeight = padding * 2 + cellDimentions * rows;
        createGridBounds();

    }

    public void createGridBounds() {
        gridBounds.add(new RectagleObject(0, 0, gridWidth, padding));
        gridBounds.add(new RectagleObject(0, 0, padding, gridHeight));
        gridBounds.add(new RectagleObject(gridWidth - padding, 0, padding, gridHeight));
        gridBounds.add(new RectagleObject(0, gridHeight - padding, gridWidth, padding));
    }

    public LinkedList<RectagleObject> getGridBounds() {
        return gridBounds;
    }

    public void clearGrid() {
        for (RectagleObject cell : gridCells) {
            cell.draw();
            cell.setPainted(false);
        }
    }

    public void createGrid(int cols, int rows) {

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {

                gridCells.add(new RectagleObject(i * cellDimentions + padding, j * cellDimentions + padding, cellDimentions, cellDimentions));
                gridCells.getLast().draw();

            }


        }
    }

    public LinkedList<RectagleObject> getGridCells() {
        return gridCells;
    }

    public int getPadding() {
        return padding;
    }

    public int getCellDimentions() {
        return cellDimentions;
    }
}
