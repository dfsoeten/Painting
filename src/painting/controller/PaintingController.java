package painting.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import painting.model.TreeModel;
import painting.model.WorldModel;

import java.util.*;


/**
 * Created by daan on 10-6-17.
 */
public class PaintingController implements Observer{

    //WorldModel
    private WorldModel model;

    //Trees
    private LeafTreeController leafTree;
    private PineTreeController pineTree;

    @FXML
    private Canvas canvas;

    @FXML
    public void initialize() { //First
        canvas.toFront();
    }

    @Override
    public void update(Observable o, Object arg) {

        if(arg instanceof ArrayList<?>){
            this.clearTrees();

            ArrayList<TreeModel> trees = (ArrayList<TreeModel>) arg;

            for(TreeModel tree : trees){
                if(tree.getTreeType() == TreeModel.TreeType.LEAF)
                    leafTree.paintTree(tree, model.getGc(), model.getWidth(), model.getHeight());
                else if(tree.getTreeType() == TreeModel.TreeType.PINE)
                    pineTree.paintTree(tree, model.getGc(), model.getWidth(), model.getHeight());
            }
        }

        if(arg instanceof Boolean){
            this.clearTrees();
        }

        this.paintSignature();
    }

    public void initModel(WorldModel worldModel){ //Second
        //Initialize Worldmodel
        this.model = worldModel;

        //Set the Graphics Context
        model.setObserver(this);
        model.setGc(this.canvas.getGraphicsContext2D());

        //Create tree objects using the model
        this.leafTree = new LeafTreeController();
        this.pineTree = new PineTreeController();

        //Set Signature
        this.paintSignature();
    }

    public void clearTrees(){
        GraphicsContext gc = model.getGc();

        gc.clearRect(0.0, 0.0, canvas.getWidth(), canvas.getHeight());
    }

    public void paintSignature(){
        GraphicsContext gc = model.getGc();

        gc.setFont(Font.font(model.getFont(), FontWeight.NORMAL, 30.0));
        gc.setFill(Color.BLACK);
        gc.fillText("Daan Soeten", 600.0, 570.0);
    }
}
