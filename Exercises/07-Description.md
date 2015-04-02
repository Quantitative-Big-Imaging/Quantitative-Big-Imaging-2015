# Exercise 7: Analyzing Complex Shapes

1. FIJI / ImageJ Macro's
 - Read first about using FIJI and recording macros [here](https://github.com/kmader/Quantitative-Big-Imaging-2015/wiki/FIJI-and-Macros)
1. For this exercise you will need to enhance the set of tools KNIME has access to using the plugins built into FIJI. Follow the instructions [here](https://github.com/kmader/Quantitative-Big-Imaging-2015/wiki/KNIME-Setup#installing-fiji-plugins-imagej1-macro) for installing FIJI and setting the KNIME ImageJ1 Macro to find it's plugin folder

### New Nodes
- ImageJ1 Macro
 - This node allows you to run ImageJ macros on images in KNIME.
 - You specify the macro to run in the panel below. There are pre-built 'Snippets' which can be selected or 'Pure Code' where everything can be copy and pasted from a Macro created in ImageJ
 - ![Macro Panel](07-files/IJMacro.png)
 - The macro is then run on each image in a selected column one row at a time
 - There are two output ports, the first is the image, the second the results table
 - Most plugins do not place information in the results table, but for certain ones like histograms or thickness they will

## Setup

In this exercise you will build together a chain of analyses step by step. You import the first workflows (which have some components preassembled) and then work to build them together

### Basic Workflow

1. Start KNIME.
2. Download this [workflows](07-files/KNIME_ex7.zip).
3. Import the above Workflow from an Archive.

## Part 1 - Object Simulation (3DBlobSimulator)

In this portion you will get comfortable with making 3D objects, using similar tools to what we had before. The objects now have both a position and dimensions in X, Y, and Z.

### Tasks
1. Try using the different input tables to generate different images
1. Create grid of overlapping balls like the one shown
 - ![Grid](07-files/GridSample.png)
1. How would you create a cylinder using the position and radius ellipsoid model we have specified?


## Part 2 - Thickness Analysis

In this part you will calculate the thickness using the ImageJ plugin developed by [Bob Dougherty](http://fiji.sc/Local_Thickness) with the source code available [here](https://github.com/fiji/LocalThickness). It can be found in FIJI under the _Analyze_ -> _Local Thickness_ menu.

The workflow should at the end look something like follows.

![Workflow Overview](https://rawgithub.com/kmader/Quantitative-Big-Imaging-2015/master/Exercises/07-files/Thickness.svg)

### Tasks
1. Select all the nodes from _3DBlobSimulator_ and copy/paste (this works) them into _Thickness Analysis_
1. Connect the components together so it works
1. Verify that the thickness gives the right results by using the Line Chart blocks
1. What happens when the balls overlap?
1. What is the thickness of a cylinder? Does this make sense?
1. What happens when the object is larger than the field of view?


## Part 3 - Watershed Labeling (and 2D)

In this part you will use the watershed labeling and compare the results with the standard connected component labeling KNIME. 

-The goal is to go from an image like this
- ![CCA](07-files/CellCCA.png)
- To an image like this
- ![CCA](07-files/CellWatershed.png)

Since here we assume these are two cells which just happen to be located very close to one another and do not want them labeled as part of the same object

### Tasks
![Workflow 2D](https://rawgithub.com/kmader/Quantitative-Big-Imaging-2015/master/Exercises/07-files/Watershed2D.svg)

1. Start with the 2D model which includes all the nodes already
 - How do you compare the different methods? 
 - Which works best? 
 - Is there a morphological-based method that might work as well?
1. Select all the nodes from _3DBlobSimulator_ and copy/paste (this works) them into _Watershed Segmentation_
1. Connect the components together so it works and use the 'Double Ball Overlap' as an easy test image.
1. The 'Connected Component Analysis' should give you an image like the one shown above
1. Use the 'Watershed' based on the distance map and other blocks to try and get the second image. 
 - Why is the Connected Component Analysis block needed for this approach?
1. Try using a morphological based approach to get a third segmentation


## Part 4 - Curvature Analysis (Advanced)

In this part you will calculate the thickness using the ImageJ plugin developed by [Stephan Preibisch and Mark Longair](http://fiji.sc/Compute_Curvatures) with the source code available [here](https://github.com/fiji/VIB/blob/master/src/main/java/Compute_Curvatures.java). It can be found in FIJI under the _Plugins_ -> _Analyze_ -> _Compute Curvatures_ menu. 

- This requires that the ```VIB-lib-2.0.0-SNAPSHOT.jar``` file is manually copied from the FIJI/jars folder to the FIJI/Plugins folder otherwise it will not run correctly and you will see an error message

The final workflow (parallel pipeline) should at the end look something like follows.

![Workflow Overview](https://rawgithub.com/kmader/Quantitative-Big-Imaging-2015/master/Exercises/07-files/CurvatureComparison.svg)

The premade surface curvature node looks like this

![Workflow Overview](https://rawgithub.com/kmader/Quantitative-Big-Imaging-2015/master/Exercises/07-files/CurvatureSurface.svg)

### Tasks
1. Select all the nodes from _3DBlobSimulator_ and copy/paste (this works) them into _Curvature Analysis_
1. Connect the components together so it works
1. Calculate the curvature for a simple system with one sphere
1. Adjust the parameters in the ImageJ Macro block, specifically 'sigma'
 - What does sigma do?
 - How does it affect the final results?
1. Make a parallel pipeline with another sized sphere and compare them
 - You should get a chart like below when it is running correctly
 - ![Compare Balls](07-files/BigBallSmallBall.png)

