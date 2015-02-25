# Exercise 2: Image Enhancement


## Downloading the data
1. The data for the example can be downloaded from [here](https://github.com/kmader/Quantitative-Big-Imaging-Course/blob/master/Ex2/matlab.zip?raw=true)
2. Open the file in Archive Manager and extract the data to ```/scratch``` (only on D61.1 machines)

## Getting Started
- Steps are shown in normal text, comments are shown in _italics_.

- Knime Basics: [here](https://github.com/kmader/Quantitative-Big-Imaging-2015/wiki/KNIME-Setup)
- Install latest image processing extensions [here](https://github.com/kmader/Quantitative-Big-Imaging-2015/wiki/KNIME-Setup#installing-the-latest-image-processing-extensions)
- Use workflow variables: [here](https://github.com/kmader/Quantitative-Big-Imaging-2015/wiki/KNIME-Setup#workflow-variables)

## Part 1 - Images, Resizing, Noise, and Filters

![Workflow](https://rawgithub.com/kmader/Quantitative-Big-Imaging-2015/master/Exercises/02-files/ImageNoise.svg)

![Output Images](02-files/FilterAndNoiseImages.png?raw=true)

Video - Coming soon

Basic Workflow - [Images Noise and Gaussian](02-files/ImagesNoiseAndGaussian.zip?raw=true)

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
 1. Select 'FLOATTYPE' for the Target Type
 1. _This node converts the image to a double/floating point value so we can add fractions of a value to it, and it won't start clipping or saturating when the value exceeds 255_

### Add Noise to the Image

To add noise to the image we use the various noise adding tools available from 'Community Nodes -> KNIME Image Processing -> ImageJ2 -> Process -> Noise' 

1. Create a 'Salt and Pepper' node
 1. Connect it to the 'Image Reader' node
 1. Right click and select 'Configure'
 1. Uncheck the 'Use data min and max' box.
 1. Set 'Salt Value' to 255
 1. Set 'Pepper Value' to 0
 1. Go to the 'Column Selection' tab
 1. Change 'Column Creation Mode' to 'Append'
 1. _We want to keep the original image for comparison_
 1. Change 'Column Suffix' to '_noisy'
 1. _We want the noisy image to have a meaningful name (default would be Image (#2))_
 
### Filter the Images

To filter the images we can use the large selection of filters available from 'Community Nodes -> KNIME Image Processing -> Image -> Filters' even more are available in 'Community Nodes -> KNIME Image Processing -> ImageJ2 -> Process -> Noise -> Noise Reduction'. We shall start with the first group and selec the 'Gaussian Convolution'

1. Create a 'Gaussian Convolution' node
 1. Connect it to the 'Salt and Pepper' node
 1. Right click and select 'Configure'
 1. Set 'Sigma Value' to 2.0
 1. Go to the 'Column Selection' tab
 1. Change 'Column Creation Mode' to 'Append'
 1. _We want to keep the original image for comparison_
 1. Change 'Column Suffix' to '_filtered'
 1. _We want the noisy image to have a meaningful name (default would be Image (#2))_

### Calculate Difference Image

Here we calculate the SNR using the 'Image Calculator' to create a difference image (between the filtered noisy image and the original) and then the 'Image Features' to calculate the mean value.

1. Create a 'Image Calculator' node
 1. Connect this node with the 'Gaussian Convolution'
 1. Right click and select 'Configure'
 1. Type in the 'Expression' field ```abs($Image$-$Image_noisy_filtered$)``` to calculate the absolute value of the difference between the original image and the noisy filtered image
 1. Select the 'New Table' option and type in a nice name like ```noisy_image```
 1. Select 'FLOATTYPE' for the Result pixel type
 1. Right click and select 'Execute and Open Views'
 1. Click the 'Normalize' checkbox to rescale the colors so the contrast in the image is visible (otherwise it shows from -1e30 to 1e30 which makes the whole image gray)



### Calculate Signal to Noise Ratio

We define the signal to noise ratio as signal^2/noise^2 and therefore do not want the mean of each image rather the sum of squares. We can then divide these two values to determine the signal to noise

1. Create two 'Image Feature' nodes and connect them to the 'Image Calculator' node.
 1. Change the name of the first block to 'Signal'
 1. Have the first block perform on column (under Configure in tab 'Column Selection') 'Image'
 1. Change the name of the second block to 'Noise' (_to keep their function clear_)
 1. Have the second block perform on column 'difference' (_calculate in the last step_)
 1. Have both blocks calculate Features -> First Order Statistics -> Sum of Squares
1. Create a new 'Joiner' node to combine the results of the two 'Image Features' nodes
 1. Connect the top to the second block (labeled 'Noise') and the bottom to 'Signal'
 1. Configure this node to use 'Row ID' in both for the matching criteria (+ and then select 'Row ID' for both)
 1. Go to the tab 'Column Selection'
 1. Under 'Join Column Handling' select 'Filter right joining columns'
 1. Under 'Duplicate Column Handling' select 'Append custom suffix' and type '_signal'
1. Create a 'Math Formula' block to calculate the signal to noise from these two image feature columns
 1. Select 'Append Column' and type SNR
 1. In Expression, paste the following code
```20*log($Squares of Sum_signal$/$Squares of Sum$)```

1. Right click and select 'Execute' and then right click again and select 'Output Data' to see the SNR values

### Tasks

1. Change the 'Salt' and 'Pepper' values in the filter and observe how this changes the results. How do these parameters affect the SNR?
1. Change the Sigma value for the Gaussian Convolution, how does the sigma value affect the resulting images and the SNR?
1. Now change the 'Gaussian Convolution' to a Median filter, how does this change the results? Is the SNR improved or worsened? Why?

## Part 2 - Multiple Filters


![Workflow](https://rawgithub.com/kmader/Quantitative-Big-Imaging-2015/master/Exercises/02-files/MultipleFilters.svg)

The next example is fairly complicated so we recommend using the pre-built workflow to start out with. Feel free to explore the 'Signal To Noise' metanodes and other aspects, if you are interested.


- Knime Workflow - [Multiple Filters](02-files/MultipleFilters.zip?raw=true)

The basic overview is images are read in using 'Image Reader' and downsampled using 'Image Resize', the downsampling is used because filters like the anisotropic diffusion filter are very time consuming and testing or playing around with settings is painful with full sized images. The resizing can later be removed or simply change to 1.0 for X and 1.0 for Y in its configuration. 

### New Nodes
- Add Specified Noise
 - This node adds the noise to the image and has a parameter called 'Standard Deviation' to control the magnitude of this noise
- Anisotropic Diffusion
 - This node performs the anisotropic diffusion filter on the noisy image in the options panel you can adjust 'Kappa', 'Delta t' and 'Iterations' to change the effect of the filter
- Median Filter
 - This node runs a median filter on the image and the size and shape of the neighborhood examined as well as the strategy at the borders can be adjusted in the 'Options' tab.
- Line Chart
 - This node plots the SNR results for the different image filters run, the image can be exported and saved to include with the exercise tasks and for comparing different settings/systems
- ![Line Chart](02-files/LineChart.png?raw=true)
- Table to HTML
 - This node must be configured to save a file in an existing directory (its default value will not work), and it will save a report containing both the noisy images and the calculated SNR values
 - ![Table to HTML](02-files/TableToHTML.png?raw=true)


### Tasks
1. Change the standard deviation of the noise (In 'Add Specific Noise') how does this affect the results? Which filter performs best for low noise (10), which for high noise (100)?
1. Sometimes small regions of interest at full resolution are more accurate than downsampled images. To do this replace the 'Image Resize' with an 'Image Cropper' block and crop the images before adding noise and the other steps. How does this change the final SNR?
1. Try using 'Salt and Pepper' noise instead of 'Add Specific Noise' how does it change the results? Does making the Salt and Pepper extremely intense (>>255) change the SNR significantly? For which filters does this happen?