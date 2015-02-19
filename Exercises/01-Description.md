# Exercise 1: Basic workflows with KNIME



## on D61.1 Machines
KNIME is already installed so you can start it by typing ```Alt+F2``` (a Run Application dialog appears) and type ```knime``` and click run 

## On your local machine
- To install KNIME follow the instructions on http://tech.knime.org/wiki/install-knime-image-processing
- Click the download link and download the version with Community Extensions

## Downloading the data
1. The data for the example can be downloaded from http://tinyurl.com/knime-ws-eth
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

### Part 1 - Load the images
Video - https://www.youtube.com/watch?v=7HwCgleJMk4

1. Start KNIME (click OK for default workspace)
1. Go to File->New... and Select 'New Knime Workflow'
1. Create a new 'List Files' Node 
 1. _This lists the files in a directory so they can be later read in with KNIME_
 1. Right click and go to 'Configure'
 1. Select the folder where the extracted files are _if you did this correctly this should be /scratch_
 1. Check the 'File extension(s)' option under 'Filter'
 1. Type ```tif``` in the Exensions Field
1. Create a new 'Cell Splitter' Node and connect it to 'List Files'
 1. Right click 'Cell Splitter' and put '-' in the delimeter field
 1. Right click 'Cell Splitter' and click 'Execute'
 1. Right click 'Cell Splitter' and click 'Filtered Table'
1. Create a new 'Column Filter' and right click 'Configure'
 1. Click on Columns 'URL_Arr[0]' and 'URL_Arr[2]' and click exclude
 1. _This removes the results of the split which are not the group which we are interested in (we only want the letter, the group)_
1. Create a new 'Column Rename' and right click 'Configure'
1. Click 'URL_Arr[1]' and check 'Change' and fill 'Group' in the text
 - _We want to have a meaningful column name_
1. Create an 'Image Reader' node and right click 'Configure'
 1. Go to the 'Additional Option' tab 
 1. Select 'File name column in optional table' and select 'URL'
 1. _This reads the file from the path in the table from list files instead of a specific file_

### Part 2 - Calculate the mean nucleus intensity
Video - https://www.youtube.com/watch?v=HR5fqEoAQ5c

1. Create a new 'Image Cropper' Node
 1. _As the image has multiple channels (the different colors seen in the last table), and we only want to keep one of them, in this case the nucleus_
 2. Right click and select 'Configure'
 3. Uncheck 'All' in the Channel Row
 4. Select 0 in Channel
1. Create a new 'Image Features' Node
 1. _For this example we are just trying to calculate the mean/average intensity in the image, which this node can accomplish_
 2. Right click and select 'Configure'
 3. Go to the 'Features' tab
 4. Check the box next to 'First Order Statistics'
 5. Select 'Mean'

### Part 3 - Group by the Image Group and Plot the Mean Value
Video - https://www.youtube.com/watch?v=ZPd7ZXl9dPs
_While this task in of itself isn't very useful, it is easy to imagine a scenario where because of constantly changing illumination, the average intensity of a group of images will need to be checked and plotted_

1. Select the 'Image Reader' node
 1. _The `Image Reader` needs to be reconfigured to save the image name as the row key (in the table) so it can be later combined with the group information we previously identified_
 2. Go to the 'Additional Option' tab
 3. Check the 'Use complete file path as row key' box
1. Create a new 'Joiner' node
 1. _The joiner node combines information from two different tables based on a column value._ http://en.wikipedia.org/wiki/Join_%28SQL%29
 2. Connect the 'Image Features' node to the top input
 3. Connect the 'Column Rename' to the bottom input
 4. Right click on 'Joiner' and select 'Configure'
 5. Select 'Inner Join' for Join Mode
 6. Click the small '+' icon
 7. Select 'Row ID' in the left column
 8. Select 'URL' in the right column
1. Create a new 'GroupBy' Node
 1. Add 'Group' to the list of 'Group Columns'
 2. Change to the 'Manual Aggregation' tab
 3. Select 'Mean' column and click 'Add >>>'
 4. Under 'Aggregation' select 'Mean' to calculate the mean value of the mean value in each images
6. Create a new 'Line Plot' node
 1. _This node will display a plot from the grouped data_
 2. Right click and select 'Execute' to show the results

## More information about KNIME
1. KNIME Website: http://knime.org
2. Examples, Tutorials, Webinars on KNIME Image Processing: http://knime.imagej.net
3. Need help? Ask in the KNIME Forum: http://tech.knime.org/forum
