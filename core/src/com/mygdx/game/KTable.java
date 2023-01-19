package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.interfaces.Constants;

import java.util.ArrayList;

public class KTable extends Rectangle implements Constants {

    private Game game;

    private ArrayList<Rect> allRects = new ArrayList<Rect>();
    private ArrayList<Ability> allAbilities = new ArrayList<>();
    private ArrayList<KLabel> allLabels = new ArrayList<>();
    private General c = new General();

    private ShapeRenderer shape;
    private int rectsPerRow = 4;
    private int rectsPerRowIncremental = 0;

    private int x = 0;
    private int y = 0;
    private int tx;
    private int ty;
    private int ex;
    private int ey;

    private int n = 8;
    private int width = 100;
    private int height = 50;
    private int nIncremental = 0;

    private int tableWidth = width * rectsPerRow;
    private int tableHeight = height * rectsPerRow;
    private String text = "DEFAULT";

    private Color alpha = new Color(0,0,0,0.3f);
    private Color color = Color.WHITE;

    private Color color2 = new Color(0,0.3f,0.8f,0);
    private Color highlight = new Color(1,1,1,1);

    private Color frameColor = Color.BLACK;
    private int selectedN = -1;

    private int savedN = n;

    private int rectNumber = 1;
    private boolean rectsHighlighted = true;
    private boolean tableHighlighted = true;
    private boolean sperre = false;

    private boolean hasLabels = false;

    private boolean isVisible = true;
    private boolean allVisible = true;
    private ArrayList<Integer> invisibleRects = new ArrayList<>();

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    //0. CONSTRUCTORS

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public KTable(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public KTable(Game game) {
        this.game = game;
        this.shape = shape;
    }

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    //1. GETTERS

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public int getAllRectsSize() {
        return allRects.size();
    }
    public int getRectX(int rectIndex) {
        return x + (rectIndex % rectsPerRow) * width;
    }

    public int getRectY(int rectIndex) {
        return y - (rectIndex / rectsPerRow) * height - height;
    }

    public Rect getRect(int index) {
        return allRects.get(index);
    }
    public int getRectNumber() {
        return nIncremental+1;
    }
    public int getnIncremental() {
        return nIncremental;
    }

    public int getRectIndex(int gx, int gy) {
        int tx = getTableX(gx);
        int ty = getTableY(gy);
        int col = tx/width;
        int row = ty/height;
        return col + row * rectsPerRow;
    }

    public int getTableX(int gx) {
        return gx - x;
    }

    public int getTableY(int gy) {
        return y - gy;
    }
    public Ability getAbility(int nx, int ny) {
        int n = getRectIndex(nx,ny);
        if (!(n >= allRects.size()) && !(n<0)) {
            Rect clickedRect = allRects.get(n);
            return allRects.get(n).getAbility();
        }
        return new Ability();
    }

    public int getRowNumber() {
        int rowNumber = n / rectsPerRow;
        if (n % rectsPerRow == 0) {
            return rowNumber;
        } else {
            return rowNumber + 1;
        }
    }

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    //2. SETTERS

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public void setColor(Color color) {
        this.color = color;
    }
    public void setInvisible(int rectIndex) {
        //rectIndex = -1: Set all rects invisible
        if (rectIndex == -1) {
            allVisible = false;
        }
        invisibleRects.add(rectIndex);
       // allRects.get(rectIndex).setVisible(false);
    }
    public void setInvisible(ArrayList<Integer> list) {
        invisibleRects = list;
    }
    public void setVisible(int rectIndex) {
        allVisible = true;
        allRects.get(rectIndex).setVisible(true);
    }
    public void setFrameColor(Color frameColor) {
        this.frameColor = frameColor;
    }
    public void setRectSperre(boolean b) {
        sperre = b;
    }

    public void setHighlightColor(Color color) {
        this.highlight = color;
        rectsHighlighted = true;
    }

    public void setHighlightedTable(boolean b) {
        tableHighlighted = b;
    }
    public void setHighlightedRects(boolean b) {
        rectsHighlighted = b;
    }
    public void setMouseListener(int ex, int ey) {
        this.ex = ex;
        this.ey = ey;
    }

    public void setLabels(boolean b) {
        hasLabels = b;
    }

    public void setBatch(SpriteBatch batch) {
    }

    public void setSecondaryColor(Color color) {
        color2 = color;
    }

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    //3. DRAW

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public void drawTable() {
        checkForMouseMovement();
        // savedN = n;
        for (int i=0; i<allRects.size(); i++) {
            allLabels.add(allRects.get(i).getLabel());
            allRects.get(i).setLabel("");
            if (invisibleRects.contains(i) || (allVisible == false)) {
                allRects.get(i).setVisible(false);
            }
            if (hasLabels == true) {
                // allRects.get(i).setLabel(text, batch);
            }
            if ((i == selectedN) && (isTable() == true)) {
                allRects.get(i).setColor(highlight);
            } else {
                if (i % 2 == 0) {
                    allRects.get(i).setColor(color);
                } else {
                    allRects.get(i).setColor(color2);
                }
            }
            allRects.get(i).setFrameColor(frameColor);
            allRects.get(i).setDimensions(x + width*(i%rectsPerRow),y - height*(i/rectsPerRow),width,height);
            allRects.get(i).draw(shape);
            //allRects.get(i).setLabel(allAbilities.get(i).getName(), getRectX(i), getRectY(i));

        }
        savedN = n;
    }

    public void drawLabels(SpriteBatch batch) {
        Ability ab = new Ability();
        for (int i=0; i<allRects.size(); i++) {
            ab = allRects.get(i).getAbility();
            allRects.get(i).setLabel(ab.getName());
            allRects.get(i).drawLabel(batch);
        }

    }

    public void drawTable(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        allRects = c.fillRects(n+nIncremental, game.batch);
        drawTable();
    }

    public void drawTable(int x, int y, int width, int height, int rectsPerRow) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rectsPerRow = rectsPerRow;
        this.tableHeight = height * rectsPerRow;
        this.tableWidth = width * rectsPerRow;
        allRects = c.fillRects(nIncremental, game.batch);
        drawTable();
    }

    public void drawTable(int x, int y, int rectWidth, int rectHeight, int numberOfRects, int rectsPerRow, ShapeRenderer shape) {
        this.x = x;
        this.y = y;
        this.width = rectWidth;
        this.height = rectHeight;
        this.n = numberOfRects;
        this.rectsPerRow = rectsPerRow;
        this.tableHeight = height * rectsPerRow;
        this.tableWidth = width * rectsPerRow;
        this.shape = shape;
        allRects = c.fillRects(numberOfRects, game.batch);
        allAbilities = c.fillAbilities(nIncremental+1);
        drawTable();
    }

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    //4. GENERAL

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public boolean isTable() {
        boolean XisRightOfBorder = ex > x;
        boolean XisLeftOfBorder = ex < x + tableWidth;
        boolean YisHigherThanBorder = ey > y - tableHeight + height;
        boolean YisLowerThanBorder = ey < y;
        boolean insideTable = getRectIndex(ex,ey) < n;
        if (insideTable && XisRightOfBorder && XisLeftOfBorder && YisHigherThanBorder && YisLowerThanBorder) {
            return true;
        }
        return false;
    }
    private void highlightTable() {
        this.color = Color.YELLOW;
    }
    public void addRect() {
        if (sperre == false) {
            sperre = true;
            // rectNumber++;
            nIncremental++;
        }
    }
    public void addRect(Ability ab) {
        if (sperre == false) {
            sperre = true;
            // rectNumber++;
            nIncremental++;
            allAbilities.add(ab);
        }
    }
    public void addRect(int row) {
        if (sperre == false) {
            sperre = true;
            rectsPerRowIncremental++;
            rectsPerRow++;
            int rowN = getRowNumber();
            n = rowN * rectsPerRow;
            for (int i=0; i<n; i++) {
                if ((i % rectsPerRow == 0) && (i / rectsPerRow != row))  {
                    invisibleRects.add(i);
                }
            }
        }
    }
    private void highlightCurrentRect(int n) {
        selectedN = n;
    }
    public void invertColors(int i) {
        if (i == 0) {
            color = new Color(0,0.3f,0.8f,1);
            color2 = new Color(0,0.1f,0.6f,1);
        } else {
            color = new Color(0.8f,0,0f,1);
            color2 = new Color(0.6f,0,0f,1);
        }
    }
    private void checkForMouseMovement() {
        if (isTable()==true) {
            // if (tableHighlighted==true) { highlightTable(); }
            if  (rectsHighlighted==true) highlightCurrentRect(getRectIndex(ex,ey));
        }
    }
}
