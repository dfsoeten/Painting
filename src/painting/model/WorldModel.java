package painting.model;

import javafx.scene.canvas.GraphicsContext;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by daan on 10-6-17.
 */
public class WorldModel extends Observable{
    //Graphics Context
    private GraphicsContext gc;

    //World height and width
    private final double width = 800.0;
    private final double height = 600.0;

    //Font
    private String font = "Arial";

    //Trees
    private List<TreeModel> trees = new ArrayList<TreeModel>();

    public GraphicsContext getGc() {
        return gc;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public List<TreeModel> getTrees() {
        return trees;
    }

    public void setObserver(Observer o) {
        addObserver(o);
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public void addTree(TreeModel.TreeType treeType, TreeModel.TreeSize treeSize, double relX, double relY) {
        this.trees.add(new TreeModel(treeSize, treeType, relX, relY));
        trees.sort(Comparator.comparing(TreeModel::getRelY));
        setChanged();
        notifyObservers(this.trees);
    }

    public void addTree(TreeModel.TreeType treeType){
        Random r = new Random();

        TreeModel.TreeSize treeSize = null;
        double relX = ThreadLocalRandom.current().nextDouble(0.0, 100.0);
        double relY = ThreadLocalRandom.current().nextDouble(50.0, 100.0);

        switch(r.nextInt(5) + 1){
            case 1:
                treeSize = TreeModel.TreeSize.S;
                break;
            case 2:
                treeSize = TreeModel.TreeSize.M;
                break;
            case 3:
                treeSize = TreeModel.TreeSize.L;
                break;
            case 4:
                treeSize = TreeModel.TreeSize.XL;
                break;
            case 5:
                treeSize = TreeModel.TreeSize.XXL;
                break;
        }

        this.trees.add(new TreeModel(treeSize, treeType, relX, relY));
        trees.sort(Comparator.comparing(TreeModel::getRelY));
        setChanged();
        notifyObservers(this.trees);
    }

    public void addTree(){
        Random r = new Random();

        TreeModel.TreeSize treeSize = null;
        TreeModel.TreeType treeType = null;
        double relX = ThreadLocalRandom.current().nextDouble(0.0, 100.0);
        double relY = ThreadLocalRandom.current().nextDouble(50.0, 100.0);

        switch(r.nextInt(2) + 1) {
            case 1:
                treeType = TreeModel.TreeType.LEAF;
                break;
            case 2:
                treeType = TreeModel.TreeType.PINE;
                break;
        }

        switch(r.nextInt(5) + 1){
            case 1:
                treeSize = TreeModel.TreeSize.S;
                break;
            case 2:
                treeSize = TreeModel.TreeSize.M;
                break;
            case 3:
                treeSize = TreeModel.TreeSize.L;
                break;
            case 4:
                treeSize = TreeModel.TreeSize.XL;
                break;
            case 5:
                treeSize = TreeModel.TreeSize.XXL;
                break;
        }

        this.trees.add(new TreeModel(treeSize, treeType, relX, relY));
        trees.sort(Comparator.comparing(TreeModel::getRelY));
        setChanged();
        notifyObservers(this.trees);
    }

    public void clearTrees(Boolean removeData){
        if(removeData)
            trees.removeAll(trees);

        setChanged();
        notifyObservers(true);
    }

    public void update(){
        setChanged();
        notifyObservers(this.trees);
    }
}
