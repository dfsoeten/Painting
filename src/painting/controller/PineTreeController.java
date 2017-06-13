package painting.controller;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import painting.library.TreePainter;
import painting.model.TreeModel;



/**
 * Created by daan on 11-6-17.
 */
public class PineTreeController extends TreePainter{

    public PineTreeController() {

    }

    @Override
    protected void paintTree(TreeModel treeModel, GraphicsContext gc, double width, double height) {
        //Calculate Leaves Position
        treeModel.setLeavesPosX((((width / 100) * treeModel.getRelX()) - (treeModel.getTotalWidth() / 2)));
        treeModel.setLeavesPosY(((height / 100) * treeModel.getRelY()) - (treeModel.getTotalHeight() * 1.6));

        //Calculate Bark Position
        treeModel.setBarkPosX(treeModel.getLeavesPosX() + ((treeModel.getLeavesWidth() / 2) - (treeModel.getBarkWidth() / 2 )));
        treeModel.setBarkPosY((treeModel.getLeavesPosY() + treeModel.getLeavesHeight()));

        //Draw the Bark
        gc.setFill(Color.SADDLEBROWN);
        gc.setLineWidth(5.0);
        gc.strokeRect(treeModel.getBarkPosX(), treeModel.getBarkPosY(), treeModel.getBarkWidth(), treeModel.getBarkHeight());
        gc.fillRect(treeModel.getBarkPosX(), treeModel.getBarkPosY(), treeModel.getBarkWidth(), treeModel.getBarkHeight());

        //Draw the Leaves
        gc.setFill(treeModel.getColor());
        gc.setLineWidth(5.0);
        gc.strokeArc(treeModel.getLeavesPosX(), treeModel.getLeavesPosY(), treeModel.getLeavesWidth(), treeModel.getLeavesHeight(), 230.0, 80.0, ArcType.ROUND);
        gc.fillArc(treeModel.getLeavesPosX(), treeModel.getLeavesPosY(), treeModel.getLeavesWidth(), treeModel.getLeavesHeight(), 230.0, 80.0, ArcType.ROUND);
    }
}
