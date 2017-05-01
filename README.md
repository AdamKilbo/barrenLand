# Barren Land

This is a project aimed at solving the Barren Land problem.

# Problem

## Description
You have a farm of 400m by 600m where coordinates of the field are from (0, 0) to (399, 599). A portion of the farm is barren, and all the barren land is in the form of rectangles. Due to these rectangles of barren land, the remaining area of fertile land is in no particular shape. An area of fertile land is defined as the largest area of land that is not covered by any of the rectangles of barren land. 
Read input from STDIN. Print output to STDOUT 
## Input 
You are given a set of rectangles that contain the barren land. These rectangles are defined in a string, which consists of four integers separated by single spaces, with no additional spaces in the string. The first two integers are the coordinates of the bottom left corner in the given rectangle, and the last two integers are the coordinates of the top right corner. 
## Output 
Output all the fertile land area in square meters, sorted from smallest area to greatest, separated by a space. 

## Sample Inputs:
{“0 292 399 307”}

{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”} 

## Sample Outputs:
116800  116800

22816 192608 

# How to run

It is the easiest to run this project by loading the project into eclipse and using the testRunner.java file's main function. This will automatically run the two test cases above and print out their respective answers. Then if you wish to input your own coordinates you will be able to do so after the test cases run. 

# Dependencies and outside material used.

This program uses the JUnit framework for testing purposes. You can find out more about it here: http://junit.org/junit4/

I grabbed a function and a test off of Stackoverflow to sort the HashMap that stores the field key/values into a smallest -> largest order, as well as a JUnit test to ensure that it works. You can find the code here: http://stackoverflow.com/a/2581754. The code is also clearly marked with comments in the program proper within the MapUtil.java and testRunner.java files where it is used.
