package painting.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import painting.model.TreeModel;
import painting.model.WorldModel;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by daan on 10-6-17.
 */
public class MenuController{
    private WorldModel model;
    final FileChooser fileChooser = new FileChooser();

    private Timer timer;

    //File
    @FXML
    private MenuItem fileLoad;

    @FXML
    private MenuItem fileSave;

    @FXML
    private MenuItem fileExit;

    //Tree
    @FXML
    private MenuItem treeAddLeaf;

    @FXML
    private MenuItem treeAddPine;

    @FXML
    private MenuItem treeAdd100Trees;

    @FXML
    private MenuItem treeClearTrees;

    //Autograph
    @FXML
    private RadioMenuItem autographArial;

    @FXML
    private RadioMenuItem autographHelvetica;

    @FXML
    private RadioMenuItem autographCourier;

    @FXML
    private RadioMenuItem autographTimesNewRoman;

    //Movie
    @FXML
    private CheckMenuItem movieToggle;


    @FXML
    public void initialize() {
        //Event handlers for File
        fileLoad.setOnAction(event -> {
            //Clear our canvas
            model.clearTrees(true);

            //Create a new FileChooser and set it's Title
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load Painting");

            //Filters the saved file to the ".painting" format
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Painting files (*.painting)", "*.painting");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show the load dialog
            File file = fileChooser.showOpenDialog(new Stage());

            if (file != null) {
                //Try to read our file and load back in our trees
                try {
                    //Create a new BufferedReader
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line = null;

                    //Go over all the lines in the savefile
                    while ((line = br.readLine()) != null)
                    {
                        String[] tree = line.split(":");

                        //Add the tree
                        model.addTree(
                                TreeModel.TreeType.valueOf(tree[0].toUpperCase()),
                                TreeModel.TreeSize.valueOf(tree[1].toUpperCase()),
                                Double.parseDouble(tree[2]),
                                Double.parseDouble(tree[3])
                        );
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        fileSave.setOnAction(event -> {
            //Create a new FileChooser and set it's Title
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Painting");

            //Filters the saved file to the ".painting" format
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Painting files (*.painting)", "*.painting");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show the save dialog
            File file = fileChooser.showSaveDialog(new Stage());
            if (file != null) {
                //Try to write all the trees.painting to the file, each on a new line
                try {
                    //Set the filename
                    FileWriter fileWriter = new FileWriter(file);

                    //Write all the trees.painting
                    for(TreeModel tree : model.getTrees()){
                        fileWriter.write(tree.getTreeType() + ":" + tree.getTreeSize() + ":" + (int)tree.getRelX() + ":" + (int)tree.getRelY() + "\n");
                    }

                    //Close the FileWriter
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        fileExit.setOnAction(event -> {
            System.exit(0);
        });

        //Event handlers for Tree
        treeAddLeaf.setOnAction(event -> {
            model.addTree(TreeModel.TreeType.LEAF);
        });

        treeAddPine.setOnAction(event -> {
            model.addTree(TreeModel.TreeType.PINE);
        });

        treeAdd100Trees.setOnAction(event -> {
            for(int i = 0; i < 100; i++) {
                model.addTree();
            }
        });

        treeClearTrees.setOnAction(event -> {
            model.clearTrees(true);
        });

        //Event handlers for Autograph
        autographArial.setOnAction(event -> {
            model.setFont("Arial");
            model.update();
        });

        autographHelvetica.setOnAction(event -> {
            model.setFont("Helvetica");
            model.update();
        });

        autographCourier.setOnAction(event -> {
            model.setFont("Courier New");
            model.update();
        });

        autographTimesNewRoman.setOnAction(event -> {
            model.setFont("Times New Roman");
            model.update();
        });

        //Event handlers for Movie
        movieToggle.setOnAction(event -> {
            if(movieToggle.isSelected()){
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //This is looped backwards to prevent ConcurrentModificationExceptions
                        for(int i = model.getTrees().size(); i > 0; i--){
                            TreeModel tree = model.getTrees().get(i - 1);

                            //Move trees back to the beginning of the screen
                            if(tree.getRelX() > (100.0 + ((tree.getTotalWidth() / 800) * 100)))
                                tree.setRelX((0.0 - ((tree.getTotalWidth() / 800) * 100)));

                            tree.move((tree.getRelY() / 100), 0);
                        }
                        model.update();
                    }
                }, 0, 41); //Run loop with 24FPS
            }
            else{
                timer.cancel();
                timer.purge();
            }
        });
    }

    public void initModel(WorldModel worldModel){
        this.model = worldModel;
    }
}
