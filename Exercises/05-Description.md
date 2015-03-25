# Exercise 5: Machine Learning in Image Processing

## Install Nightly extension for KNIME

The workflow used for Part 1 requires the node GraphCut 2D; if this node
is not inside the Node Repository, you have to install the Nightly extensions
for Image Processing by following [these instructions](https://github.com/kmader/Quantitative-Big-Imaging-2015/wiki/KNIME-Setup#install-nightly-image-processing-extensions).

## Downloading the data

You can use [this image](05-files/1_29_s.bmp) or re-use data from a previous exercise.

## Part 1 - Graph-cuts


![Workflow Overview](https://rawgithub.com/kmader/Quantitative-Big-Imaging-2015/master/Exercises/05-files/GraphCut.svg)

### New Nodes
- GraphCut 2D
 - This node performs the GraphCut itself. 
 - The input settings provide the node with information on the image organization. 
 - __Dimension Selection__ sets the dimensons that are to be considered as part of the same graph (if you have a 3D image and do not select Z, each slice will be handled independently)
 - __Feature Vector__ by default just the intensity is used as the feature, but if there are multiple channels (or time points) they can be used as weights for the different portions
 - ![Input Settings](05-files/GraphCut.png)
 - The labeling section controls the value of lambda which controls the locality of the graph cut (a low value, close to 0, makes it very global, while a high value, makes it more local)
 - ![Labeling Settings](05-files/GraphCutLabeling.png)

### Instructions

1. Start KNIME.
2. Download this [workflow](05-files/KNIME_GraphCut.zip?raw=true).
3. Import the above Workflow from an Archive.
4. Right click the image reader to make sure it has downloaded, otherwise you can use your own test image and import it using the 'Image Reader' (make sure to remove the old one first).
5. Click 'Execute' on the Interactive Segmentation View, at the end of the workflow.
6. View the output and it (scroll left) should look like the following table.

![Output Images](05-files/GraphCut_output.png)

### Tasks

You can see that the result is not really good yet as GraphCut tend to remove the non-smooth parts (e.g. the legs in this example). Can you think of ways to improve the results?

## Part 2 - Image classification

We here provide KNIME workflow to train a classifier (you can choose one among SVM, GP, Perceptron or Decision tree) to detect objects specified in a set of ground-truth images. Some images and the associated ground-truth images are provided for the segmentation of mitochondria in Electron microscopy images. We use 3 feature vectors (the same as discussed in lecture 4), Intensity, Gaussian, and Sobel and start with a basic decision tree prediction.

The goal of the exercise is to take a given input image (electron microscopy of cells) and identify the mitochondria
![Input Images](05-files/FIBSLICE0160.png)
For this task there are a series of ground truth (labeled) images available which classify the image into the catagories of mitochondria (white) and everything else (black)
![Ground Truth Images](05-files/FIBSLICE0160_GT.png)
To achieve this goal we want to train a technique using some of the ground truth data and then apply it to a series of images to classify it into various categories.


![Workflow Overview](https://rawgithub.com/kmader/Quantitative-Big-Imaging-2015/master/Exercises/05-files/TrainableClusteringWithEMImages.svg)


### Instructions

1. Download and extract the zip file containing the images from: [set of EM images](http://lucchia.free.fr/Mitochondria/EM_images.zip)
1. Import the workflow from [here](05-files/TrainableClusteringWithEMImages.zip?raw=true)
1. Watch the video [here](https://www.youtube.com/watch?v=Y0X204avgp4)

## Part 3 (Advanced)- Image classification in Matlab

We here provide matlab code to train a classifier (you can choose one among SVM, GP, Perceptron or Decision tree) to detect objects specified in a set of ground-truth images. Some images and the associated ground-truth images are provided for the segmentation of mitochondria in Electron microscopy images.

![Output Images](05-files/FIBSLICE0160.png) ![Output Images](05-files/FIBSLICE0160_GT.png)

1. Download the zip file containing the code [here](http://lucchia.free.fr/code/segmentation.zip)
2. Run main.m
3. You should get an image in the "predictions" directory like the one below. 

![Output Images](05-files/classifier_output.png)

Note that the default classifier is a Perceptron algorithm. If you want to use SVM you need to download the libSVM code from [here](http://www.csie.ntu.edu.tw/~cjlin/libsvm/). You will then need to add the path pointing to the libsvm files with the matlab function addpath.

### Tasks (can be done in any order you like)
1. Change the size of the patches (variable opts.patchSize in main.m). What effect does it have on the output of the classifier? 
2. Change the number of positive and negative examples (variables opts.pos_examples and opts.neg_examples) and observe how the confidence of the classifier varies.
3. Change the type of features (see description of the variable opts.featType in main.m)
4. Change the set of training and test images. You can either use your own images or use [this set of EM images](http://lucchia.free.fr/Mitochondria/EM_images.zip)

#### Use a different set of training and test images
For that, you need to change the following variables:

opts.trainImgFolder = './train/images_mitochondria/';

opts.trainAnnFolder = './train/annotations_mitochondria/';

opts.testImgFolder = './test/images_mitochondria/';

opts.testAnnFolder = './test/annotations_mitochondria/';

#### Comments
Note that the code will error if you try to extract more patches than available in the training data, in which case you should increase the number of training images (if possible) or reduce the number of samples.



