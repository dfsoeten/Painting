package painting.library;

import javafx.scene.canvas.GraphicsContext;
import painting.model.TreeModel;

/**
 * Created by daan on 11-6-17.
 */
public abstract class TreePainter {
    abstract  protected void paintTree(TreeModel treeModel, GraphicsContext gc, double width, double height);
}
