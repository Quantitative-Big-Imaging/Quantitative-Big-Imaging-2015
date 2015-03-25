# Exercise 6: Analyzing Single Shapes

## Part 1 - Basic Labeling

While we have done similar exercises before, we will now properly explain some of the nodes used and try to run the analysis on an image where we know exactly where each object is and compare our results with the input data.

![Output Images](https://rawgithub.com/kmader/Quantitative-Big-Imaging-2015/master/Exercises/06-files/CellImage.svg)


### New Nodes
- Connected Component Analysis
 - This performs the component labeling of a segmented/thresheld image using a neighborhood. 
 - The panel allows the selection of ```FOUR_CONNECTED``` or ```EIGHT_CONNECTED``` neighborhood (which maps to 8 and 27 connected in 3D)
 - The dimensions allows for the grouping to be selected. For example for a 2D image selecting _X_ and _Y_ labels the components in the image. For a 3D image selecting _X_ and _Y_ means the components are found slice-by-slice and selecting _X_, _Y_, and _Z_ labels the components in 3D
 - ![Component Window](06-files/ConnectedComponents.png)
- Label Features
 - This performs the shape analysis of the components (after labeling). It requires the specification of the metrics to be calculated for each component.
 - The features tab allows for the features to be selected which are calculated. The basic features we are interested in are Shape Geometry 
 - __Shape Geometry__ where we can pick _Centroid_ (for the relavant dimensions), and _Dimension_ which in this case means the bounding box size in the give dimension.
 - __Shape Descriptors__ contains more detailed shape information based on the distances from the centroid to the surface of the object. It can be useful for comparing complex shapes and will be covered in more detail in the next lecture.
 - ![Features](06-files/LabelFeatures2.png)
 - The segment settings is not a feature we need immediately, but it allows for handling overlapping segments (connected components can per definition not overlap, but other labeling methods which we will cover in future lectures can). It also allows filtering (only large components or small, or based on other criteria)
 - ![Segment Settings](06-files/LabelFeatures1.png)

### Basic Workflow

1. Start KNIME.
2. Download this [workflow](06-files/KNIME_CellImage.zip).
3. Import the above Workflow from an Archive.
4. Right click 'Bubble Chart' and select 'Execute and Open Views' and ensure you get a similar plot to the one below

![Output Images](06-files/CellImage_Plot.png)

This chart shows the overlap of the original positions (red dots) from the cell positions table and the calculate objects using component labeling and shape analysis (blue dots)

### Tasks
1. The crappy camera can be configured, try changing the noise and illumination levels, how 'crappy' can it be and still work correctly
1. Try adding some __Image Enhancement__ steps before the _Global Threshold_ step to improve the segmentation and overlap
 - which filters work best?
 - if we know that the cells are larger which other steps could be performed?
1. Change the input cell information 
 - add more cells
 - add some very large
 - some very small
 - some overlapping
 - some on the edges
 - which of the following is the most difficult?
 - Are some of these possible to reconstruct well, what sort of constraints can we place on the analysis?

### Concept Questions
1. Currently we generate a graph for the output but we would like to have a metric to evaluate how well our system works, which metrics might make sense (think about the first topics covered in the lecture)
 - How might these be implemented?
