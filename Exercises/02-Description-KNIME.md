# Exercise 1: Basic workflows with KNIME



## on D61.1 Machines
KNIME is already installed so you can start it by typing ```Alt+F2``` (a Run Application dialog appears) and type ```knime``` and click run 

## On your local machine
- To install KNIME follow the instructions on http://tech.knime.org/wiki/install-knime-image-processing
- Click the download link and download the version with Community Extensions

## Downloading the data
1. The data for the example can be downloaded from [here](https://github.com/kmader/Quantitative-Big-Imaging-Course/blob/master/Ex2/matlab.zip?raw=true)
2. Open the file in Archive Manager and extract the data to ```/scratch``` (only on D61.1 machines)

## Getting Started
- Steps are shown in normal text, comments are shown in _italics_.

### Knime Basics
- Creating a node can be done by going to the 'Node Repository' and finding it inside the tree or typing the name next to the magnifying glass icon. 
- Under each node is a status light
 - red indicates not configured or connected correctly
 - yellow indicatates configured correctly, but not executed
 - green indicates configured and executed
 - A blue progress bar indicates the current status (if it is executing)
- Double clicking a node will show you its 'Configure' dialog
- Right clicking a node will bring up a menu with many options
 - 'Execute' runs the node and all previous nodes which need to be run to complete the given task
 - 'Reset' resets the current node (clears the output) and resets all subsequent nodes
 - One of the last options is usually the '... Table' which contains the results (only after execution)

### Part 1 - Images, Resizing and Displaying
Video - 

Here we want to 

1. Start KNIME (click OK for default workspace)
1. Go to File->New... and Select 'New Knime Workflow'
1. Create an 'Image Reader' node and right click 'Configure'
 1. Go to the 'Options' tab
 1. Select the files from the downloaded folder called 'matlab/data' (```asphalt_bilevel.tif, asphalt_gray.tif, scroll.tif, testpattern.png, wood.tif```)
 1. Select all of the files and click 'Add Selected'
 1. Right click and select 'Execute and Open Views'
 1. _Here you see the names and previews of the images loaded_
1. Create an 'Image Cropper' 
 1. Connect this node with the 'Image Reader'
 1. Right click and select 'Configure'
 1. _Here you select the region of the image that should be kept, the default is the 'All' box checked which keeps all of the data_
 1. Uncheck the 'All' box next to x and y
 1. Type 100-150 in the field next to x to keep the pixels between 100 and 150
 1. Type 0-100 in the field next to y to keep the pixels between 0 and 100
 1. Right click and select 'Execute and Open Views'
1. Create an 'Image Resizer' 
 1. Connect this node with the 'Image Reader'
 1. Right click and select 'Configure'
 1. _Here you can resize the images through scaling, in each direction, the default values (for relative scaling) are 1.0 which means no rescaling_
 1. Change the values for X and Y to 0.5
1. Create an 'Image Converter' node
 1. Connect this node with the 'Image Reader'
 1. Right click and select 'Configure'
 1. Select 'DOUBLETYPE' for the Target Type
 1. _This node converts the image to a double/floating point value so we can add fractions of a value to it, and it won't start clipping or saturating when the value exceeds 255_

#### Generating Noise

To test how well the filters work we want to generate noise. We can simulate noise using the 'Image Generator' node

1. Create an 'Image Generator' node
 1. Right click and select 'Configure'
 1. Next to X type 1000 and 1000
 1. Next to Y type 1000 and 1000
 1. Next to Z type 1 and 1
 1. _The last three steps set the range in size for the images, 1000 1000 means that the minimum size is 1000 and the maximum size is 1000_
 1. Change 'Pixel Type' to ```DOUBLETYPE``` (_a floating point number, number with a decimal_)
 1. Change 'Factory Type' to ```ARRAY_IMG_FACTORY``` (_Dont worry about this, it is just a technical detail_)
 1. Check 'Random Filling within type bounds'
1. Create a 'Multiply' node (from ImageJ2)
 1. Connect this node with the 'Image Generator'
 1. Right click and select 'Configure'
 1. Change the value to 100 to scale the noise by 100
1. Create a 'Image Calculator' node
 1. Connect this node with the 'Image Generator'
 1. Right click and select 'Configure'
 1. Type in the 'Expression' field ```$Img$*100``` to scale the image by 100
 1. Select the 'New Table' option and type in a nice name like ```scale_noise```
 1. Select 'DOUBLETYPE' for the Result pixel type
 
 
 
#### Add the noise images to the real-images

Here we can utilize the 'Cross-Joiner' node to combine the two different nodes into the same table as different columns

1. Create a 'Cross Joiner' node
 1. Connect one input to the output of the 'Image Converter'
 1. Connect the other input to the output of 'Image Calculator'
 1. Right click and select exectute
1. Create a 'Image Calculator' node
 1. Connect this node with the 'Cross Joiner'
 1. Right click and select 'Configure'
 1. Type in the 'Expression' field ```$Image$+$scale_noise$``` to add the image and the noise together
 1. Select the 'New Table' option and type in a nice name like ```noisy_image```
 1. Select 'DOUBLETYPE' for the Result pixel type


## More information about KNIME
1. KNIME Website: http://knime.org
2. Examples, Tutorials, Webinars on KNIME Image Processing: http://knime.imagej.net
3. Need help? Ask in the KNIME Forum: http://tech.knime.org/forum
