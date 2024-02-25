package io.codeforall.ironMaven;

import org.academiadecodigo.simplegraphics.mouse.MouseEvent;

public class PaintCells implements Runnable{
    private GameGrid gameGrid;
    private MouseEvent mouseEvent;

    public PaintCells(MouseEvent mouseEvent, GameGrid gameGrid){
        this.mouseEvent = mouseEvent;
        this.gameGrid = gameGrid;

    }
    @Override
    public void run() {
        paintCellsUnderMouse(this.mouseEvent,this.gameGrid);
    }
    private void paintCellsUnderMouse(MouseEvent mouveEvent, GameGrid gameGrid){

        for (int i = 0; i < gameGrid.getGridCells().size(); i++) {
            if (mouveEvent.getX() >= gameGrid.getGridCells().get(i).getRectangle().getX() && mouveEvent.getX() <= gameGrid.getGridCells().get(i).getRectangle().getX()+gameGrid.getCellDimentions() && mouveEvent.getY()-30 >= gameGrid.getGridCells().get(i).getRectangle().getY() && mouveEvent.getY()-30 <= gameGrid.getGridCells().get(i).getRectangle().getY()+gameGrid.getCellDimentions()){
                gameGrid.getGridCells().get(i).fill();
                gameGrid.getGridCells().get(i).setPainted(true);

            }
        }
    }
}
