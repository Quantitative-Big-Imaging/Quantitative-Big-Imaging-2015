# Exercise 2: Image Enhancement


## Downloading the data
1. The data for the example can be downloaded from [here](https://github.com/kmader/Quantitative-Big-Imaging-Course/blob/master/Ex2/matlab.zip?raw=true)
2. Open the file in Archive Manager and extract the data to ```/scratch``` (only on D61.1 machines)

## Loading workflows
Many of these workflows are fairly complicated and would be time consuming to reproduce, follow the instructions [here](https://github.com/kmader/Quantitative-Big-Imaging-2015/wiki/KNIME-Setup#loading-workflows) for how to import a workflow from the zip files on this site

## Problems!
If you load a workflow and get an error message, click on the details button. If it says 'Node ... not available' it means you need to update your 'Image Processing Extensions' follow the instructions below to perform this update [here](https://github.com/kmader/Quantitative-Big-Imaging-2015/wiki/KNIME-Setup#installing-the-latest-image-processing-extensions)
- If you cannot find the 'Salt and Pepper' node, this also means you are not using the latest Image Processing Extensions so update it as described above

## Getting Started
- Steps are shown in normal text, comments are shown in _italics_.

- Knime Basics: [here](https://github.com/kmader/Quantitative-Big-Imaging-2015/wiki/KNIME-Setup)

- Use workflow variables: [here](https://github.com/kmader/Quantitative-Big-Imaging-2015/wiki/KNIME-Setup#workflow-variables)

## Part 1 - Automatic Thresholding

The example takes on image of cells and simulates having a poor microscope, so we can test methods for performing thresholds even on very variable data.

![Workflow](https://rawgithub.com/kmader/Quantitative-Big-Imaging-2015/master/Exercises/04-files/AutomaticThreshold.svg)

Basic Workflow - [Automatic Thresholding](04-files/AutomaticThresholds.zip?raw=true)

1. Start KNIME (click OK for default workspace)
1. Import the above Workflow from an Archive
1. Right click the image reader to make sure it has downloaded, otherwise you can redownload the test image from here [Test Image](04-files/Cell_Colony.jpg?raw=true) and import it using the 'Image Reader' (make sure to remove the old one first)
1. Right click the 'Loop End' node and click 'Execute'
1. View the output and it (scroll left) should look like the following table

![Output Images](04-files/BadAutomatic.png?raw=true)

### Tasks

1. Try using different automatic threshold methods available in 'Global Threshold' to improve the results.
 - How will you quantify _improvement_
1. Would adding a filter improve the results? Where should it be added and why?
1. What other approaches could you use for segmenting images to get around the issue?
1. Try to adjust the parameters to get the final result
![Output Images](04-files/BetterAutomatic.png?raw=true)


## Part 2 - Performing K-Means Analysis
In this example we use K-Means to automatically segment the images used in the second lecture


![Workflow](https://rawgithub.com/kmader/Quantitative-Big-Imaging-2015/master/Exercises/04-files/KMeans-Demo.svg)


- Knime Workflow - [KMeans](04-files/KMeans-Demo.zip?raw=true)


1. Right click the image reader to make sure it has downloaded, otherwise you can redownload the test images from [here](https://github.com/kmader/Quantitative-Big-Imaging-Course/blob/master/Ex2/matlab.zip?raw=true)

### New Nodes
- K-Means
 - A node performing the K-Means analysis on a table (not an image!)
 - You can specify the number of clusters to find
 - You cannot specify a distance metric and the default is the Euclidean metric discussed in class.
 - You select the columns to use for the feature-vectors in analysis in the 'Include' area of the 'K-Means Properties' window. 
- Image to Labeled Table
 - This is a meta-node (feel free to open it) that turns an image into a feature vector representation so we can use it with various classification algorithms since they are only implemented on tables.
 
 
### Tasks
1. Use the 'Interactive Annotator' to specify regions in the image as different phases for the training and then classify the rest of the image

![Output Images](04-files/KMeans-Simple.png?raw=true)


## Part 3 - Trainable Segmentation
In this example we use a basic training method (Decision Tree) to learn how to segment data from a small test example. We then apply it to an entire image.


![Workflow](https://rawgithub.com/kmader/Quantitative-Big-Imaging-2015/master/Exercises/04-files/Simple-Trainable-Clustering.svg)

- Knime Workflow - [KMeans](04-files/Simple-Trainable-Clustering.zip?raw=true)

1. Right click the image reader to make sure it has downloaded, otherwise you can redownload the test images from [here](https://github.com/kmader/Quantitative-Big-Imaging-Course/blob/master/Ex2/matlab.zip?raw=true)

When it is running correctly the result will look something like the image below
![Output Images](04-files/Simple-Trainable-Clustering.png?raw=true)

### Tasks
