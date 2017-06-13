package painting.model;

import javafx.scene.paint.Color;

/**
 * Created by daan on 10-6-17.
 */
public class TreeModel {
    public enum TreeSize{
        S,
        M,
        L,
        XL,
        XXL
    }

    public enum TreeType{
        PINE,
        LEAF
    }

    private Color color;

    private TreeSize treeSize;
    private TreeType treeType;

    private double leavesPosX;
    private double leavesPosY;

    private double barkPosX;
    private double barkPosY;

    private double relX;
    private double relY;

    private double totalWidth;
    private double totalHeight;

    private double leavesWidth;
    private double leavesHeight;

    private double barkWidth;
    private double barkHeight;

    public TreeModel(TreeSize treeSize, TreeType treeType, double relX, double relY) {
        this.treeSize = treeSize;
        this.treeType = treeType;
        this.relX = relX;
        this.relY = relY;

        switch (this.treeSize) {
            case S:
                this.totalWidth = (100.0 * (this.relY / 100));
                this.totalHeight = (100.0 * (this.relY / 100));

                this.color = Color.FORESTGREEN;
                break;
            case M:
                this.totalWidth = (120.0 * (this.relY / 100));
                this.totalHeight = (120.0 * (this.relY / 100));

                this.color = Color.DARKOLIVEGREEN;
                break;
            case L:
                this.totalWidth = (150.0 * (this.relY / 100));
                this.totalHeight = (150.0 * (this.relY / 100));

                this.color = Color.DARKGREEN;
                break;
            case XL:
                this.totalWidth = (170.0 * (this.relY / 100));
                this.totalHeight = (170.0 * (this.relY / 100));

                this.color = Color.DARKSEAGREEN;
                break;
            case XXL:
                this.totalWidth = (200.0 * (this.relY / 100));
                this.totalHeight = (200.0 * (this.relY / 100));

                this.color = Color.GREEN;
                break;
        }

        if(this.treeType == treeType.LEAF){
            this.leavesHeight = (this.totalHeight * 0.7);
            this.leavesWidth = (this.totalWidth * 0.7);

            this.barkHeight = (this.totalHeight * 0.3);
            this.barkWidth = (this.totalWidth * 0.15);
        }
        else if(this.treeType == treeType.PINE){
            this.leavesHeight = (this.totalHeight * 1.4);
            this.leavesWidth = (this.totalWidth * 0.8);

            this.barkHeight = (this.totalHeight * 0.3);
            this.barkWidth = (this.totalWidth * 0.10);
        }

    }

    public void move(double relX, double relY){
        this.relX = this.relX + relX;
        this.relY = this.relY + relY;
    }

    public TreeSize getTreeSize() {
        return treeSize;
    }

    public TreeType getTreeType() {
        return treeType;
    }

    public double getLeavesPosX() {
        return leavesPosX;
    }

    public void setLeavesPosX(double leavesPosX) {
        this.leavesPosX = leavesPosX;
    }

    public double getLeavesPosY() {
        return leavesPosY;
    }

    public void setLeavesPosY(double leavesPosY) {
        this.leavesPosY = leavesPosY;
    }

    public double getBarkPosX() {
        return barkPosX;
    }

    public void setBarkPosX(double barkPosX) {
        this.barkPosX = barkPosX;
    }

    public double getBarkPosY() {
        return barkPosY;
    }

    public void setBarkPosY(double barkPosY) {
        this.barkPosY = barkPosY;
    }

    public double getRelX() {
        return relX;
    }

    public void setRelX(double relX) {
        this.relX = relX;
    }

    public double getRelY() {
        return relY;
    }

    public void setRelY(double relY) {
        this.relY = relY;
    }

    public double getTotalWidth() {
        return totalWidth;
    }

    public double getTotalHeight() {
        return totalHeight;
    }

    public Color getColor() {
        return color;
    }

    public double getLeavesWidth() {
        return leavesWidth;
    }

    public double getLeavesHeight() {
        return leavesHeight;
    }

    public double getBarkWidth() {
        return barkWidth;
    }

    public double getBarkHeight() {
        return barkHeight;
    }
}
