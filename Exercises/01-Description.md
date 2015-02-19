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
1. Start KNIME (click OK for default workspace)
1. Go to File->New... and Select 'New Knime Workflow'
1. Create a new 'List Files' Node and select the folder where the extracted files are (if you did this correctly this should be /scratch)
1. Create a new 'Cell Splitter' Node and connect it to 'List Files'
1. Right click 'Cell Splitter' and put '-' in the delimeter field
1. Right click 'Cell Splitter' and click 'Execute'
1. Right click 'Cell Splitter' and click 'Filtered Table'

## More information about KNIME
1. KNIME Website: http://knime.org
2. Examples, Tutorials, Webinars on KNIME Image Processing: http://knime.imagej.net
3. Need help? Ask in the KNIME Forum: http://tech.knime.org/forum
