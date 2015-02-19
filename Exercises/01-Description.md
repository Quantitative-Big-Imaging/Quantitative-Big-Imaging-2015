# Exercise 1: Basic workflows with KNIME



## on D61.1 Machines
KNIME is already installed so you can start it by typing ```Alt+F2``` (a Run Application dialog appears) and type ```knime``` and click run 

## On your local machine
- To install KNIME follow the instructions on http://tech.knime.org/wiki/install-knime-image-processing
- Click the download link and download the version with Community Extensions

## Downloading the data
1. The data for the example can be downloaded from http://tinyurl.com/knime-ws-eth
2. Open the file in Archive Manager and extract the data to /scratch 

## Getting Started
### Part 1 
Video - https://www.youtube.com/watch?v=7HwCgleJMk4

1. Start KNIME (click OK for default workspace)
1. Go to File->New... and Select 'New Knime Workflow'
1. Create a new 'List Files' Node and select the folder where the extracted files are (if you did this correctly this should be /scratch)
1. Create a new 'Cell Splitter' Node and connect it to 'List Files'
1. Right click 'Cell Splitter' and put '-' in the delimeter field
1. Right click 'Cell Splitter' and click 'Execute'
1. Right click 'Cell Splitter' and click 'Filtered Table'
1. Create a new 'Column Filter' and right click 'Configure'
1. Click on Columns 'URL_Arr[0]' and 'URL_Arr[2]' and click exclude
 - This removes the results of the split which are not the group which we are interested in (we only want the letter, the group)
1. Create a new 'Column Rename' and right click 'Configure'
1. Click 'URL_Arr[1]' and check 'Change' and fill 'Group' in the text
 - We want to have a meaningful column name
1. Create an 'Image Reader' node and right click 'Configure'
1. Select 'File name column in optional table' and select 'URL'
 - This reads the file from the path in the table from list files instead of a specific file


## More information about KNIME
1. KNIME Website: http://knime.org
2. Examples, Tutorials, Webinars on KNIME Image Processing: http://knime.imagej.net
3. Need help? Ask in the KNIME Forum: http://tech.knime.org/forum
