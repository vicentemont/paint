package io.codeforall.ironMaven;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Game implements KeyboardHandler, MouseHandler {

    private GameGrid gameGrid;
    private PointerObject pointer;
    private PointerObject moveChecker;
    private boolean isPainting;

    public Game() {
        gameGrid = new GameGrid(50, 50);
        pointer = new PointerObject(gameGrid.getPadding(), gameGrid.getPadding(), gameGrid.getCellDimentions(), gameGrid.getCellDimentions());
        moveChecker = new PointerObject(gameGrid.getPadding(), gameGrid.getPadding(), gameGrid.getCellDimentions(), gameGrid.getCellDimentions());
        pointer.setColor(Color.BLACK);
        pointer.fill();
        this.keyboardSetup();

    }

public void mouseSetup(){
    Mouse mouse = new Mouse(this);

  

}


    public void keyboardSetup() {

        Keyboard keyboard = new Keyboard(this);

        // Event configuration

        KeyboardEvent rightArrow = new KeyboardEvent();
        rightArrow.setKey(KeyboardEvent.KEY_RIGHT);
        rightArrow.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent leftArrow = new KeyboardEvent();
        leftArrow.setKey(KeyboardEvent.KEY_LEFT);
        leftArrow.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent upArrow = new KeyboardEvent();
        upArrow.setKey(KeyboardEvent.KEY_UP);
        upArrow.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent downArrow = new KeyboardEvent();
        downArrow.setKey(KeyboardEvent.KEY_DOWN);
        downArrow.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent space = new KeyboardEvent();
        space.setKey(KeyboardEvent.KEY_SPACE);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent spaceR = new KeyboardEvent();
        spaceR.setKey(KeyboardEvent.KEY_SPACE);
        spaceR.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent letterC = new KeyboardEvent();
        letterC.setKey(KeyboardEvent.KEY_C);
        letterC.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent letterL = new KeyboardEvent();
        letterL.setKey(KeyboardEvent.KEY_L);
        letterL.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent letterS = new KeyboardEvent();
        letterS.setKey(KeyboardEvent.KEY_S);
        letterS.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        //Associate event to keyboard

        keyboard.addEventListener(rightArrow);
        keyboard.addEventListener(leftArrow);
        keyboard.addEventListener(upArrow);
        keyboard.addEventListener(downArrow);
        keyboard.addEventListener(space);
        keyboard.addEventListener(spaceR);
        keyboard.addEventListener(letterC);
        keyboard.addEventListener(letterL);
        keyboard.addEventListener(letterS);


    }

    public void movePointer(int direction) {


        int moveCheckerTranslateValueX = 5;
        int moveCheckerTranslateValueY = 5;
        int pointerTranslateValueX = gameGrid.getCellDimentions();
        int pointerTranslateValueY = gameGrid.getCellDimentions();
        switch (direction) {
            case 0:
                moveCheckerTranslateValueX = 5;
                moveCheckerTranslateValueY = 0;
                pointerTranslateValueX = gameGrid.getCellDimentions();
                pointerTranslateValueY = 0;
                break;
            case 1:
                moveCheckerTranslateValueX = -5;
                moveCheckerTranslateValueY = 0;
                pointerTranslateValueX = -gameGrid.getCellDimentions();
                pointerTranslateValueY = 0;
                break;
            case 2:
                moveCheckerTranslateValueX = 0;
                moveCheckerTranslateValueY = -5;
                pointerTranslateValueX = 0;
                pointerTranslateValueY = -gameGrid.getCellDimentions();
                break;
            case 3:
                moveCheckerTranslateValueX = 0;
                moveCheckerTranslateValueY = 5;
                pointerTranslateValueX = 0;
                pointerTranslateValueY = gameGrid.getCellDimentions();
                break;
        }
        moveChecker.translate(moveCheckerTranslateValueX, moveCheckerTranslateValueY);
        boolean isColliding = false;
        for (RectagleObject bound : gameGrid.getGridBounds()) {
            if (CollisionDetector.collisionDetector(moveChecker, bound)) {
                isColliding = true;
                System.out.println(isColliding);
            }
        }

        if (!isColliding) {
            pointer.translate(pointerTranslateValueX, pointerTranslateValueY);
            moveChecker.translate(pointerTranslateValueX - moveCheckerTranslateValueX, pointerTranslateValueY - moveCheckerTranslateValueY);
            System.out.println("moved");
        } else {
            System.out.println("moved Back");
            moveChecker.translate(-moveCheckerTranslateValueX, -moveCheckerTranslateValueY);
        }

    }

    public void paintCell() {
        for (RectagleObject cell : gameGrid.getGridCells()) {
            if (CollisionDetector.collisionDetector(pointer, cell)) {
                if (cell.isPainted()) {
                    cell.setPainted(false);
                    cell.draw();

                } else {
                    cell.setPainted(true);
                    cell.fill();
                }
            }
            ;

        }
    }

    public void save() {
        FileWriter paintSaver;
        try {
            paintSaver = new FileWriter("src/text.txt");

            // Initializing BufferedWriter
            BufferedWriter paintSave = new BufferedWriter(paintSaver);
            System.out.println("Buffered Writer start writing :)");

            for (RectagleObject w : gameGrid.getGridCells()) {
                if (w.isPainted()) {
                    paintSave.write("1");
                    paintSave.write(" ");
                } else {
                    paintSave.write("0");
                    paintSave.write(" ");

                }
            }

            paintSave.close();
            System.out.println("Written successfully");
        } catch (IOException except) {
            except.printStackTrace();
        }

    }



    public void paste() throws IOException {

        class WordReader extends BufferedReader implements Iterable<String> {

            private String[] wordsArray;

            public WordReader(Reader in) throws IOException {
                super(in);
                wordsArray = bReader.readLine().split(" ");

                /*bReader.close();*/
            }

            BufferedReader bReader = new BufferedReader(this);

            @Override
            public Iterator iterator() {

                return new Iterator() {
                    private int current = -1;
                    int init = 0;
                    int end = wordsArray.length;

                    @Override
                    public boolean hasNext() {
                        if (current == end - 1) {
                            try {
                                String nextLine = bReader.readLine();
                                if (nextLine != null) {
                                    wordsArray = nextLine.split(" ");
                                    end = wordsArray.length;
                                } else {
                                    return false;
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            current = -1;
                        }
                        return (current < end);
                    }

                    @Override
                    public String next() {

                        ++current;
                        return wordsArray[current];
                    }
                };
            }
        }
        FileReader reader = new FileReader("src/text.txt");
        WordReader wr = new WordReader(reader);
        Iterator it = wr.iterator();
        int counter = -1;
        while (it.hasNext()) {
            counter++;
            if (it.next().equals("1")) {
                gameGrid.getGridCells().get(counter).fill();
                gameGrid.getGridCells().get(counter).setPainted(true);
            } else {
                gameGrid.getGridCells().get(counter).draw();
                gameGrid.getGridCells().get(counter).setPainted(false);

            }
        }
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {


        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                movePointer(0);
                if (isPainting) {
                    paintCell();
                }
                break;
            case KeyboardEvent.KEY_LEFT:
                movePointer(1);
                if (isPainting) {
                    paintCell();
                }
                break;
            case KeyboardEvent.KEY_UP:
                movePointer(2);
                if (isPainting) {
                    paintCell();
                }
                break;
            case KeyboardEvent.KEY_DOWN:
                movePointer(3);
                if (isPainting) {
                    paintCell();
                }
                break;
            case KeyboardEvent.KEY_SPACE:
                isPainting = true;
                paintCell();
                break;
            case KeyboardEvent.KEY_C:
                gameGrid.clearGrid();
                break;
            case KeyboardEvent.KEY_L:
                try {
                    paste();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case KeyboardEvent.KEY_S:
               save();
                break;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_SPACE:
                isPainting = false;

                break;
        }

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
