/**
 * IDSA Short Project 3
 * Team Members:
 * Adarsh Raghupati  NetID: axh190002
 * Keerti Keerti     NetID: kxk190012
 */  
 
####Implementation of different variations of merge sort

###### Steps to run the code in IntelliJ IDE
* Create an empty java project 
* Unzip the source code files and paste it under the location "Java Project Name"/src folder
* Open the SP3MergeSort.java and run the program for different case and input size.
 
###### Performance metrics of different merge sort algorithms:
Time taken is in milli seconds

Input Size MergeSort take 2 MergeSort take 3  MergeSort take 4 MergeSort take 6  

8M          1232				1183			1168			1055
            65MB/252MB          65MB/252MB      65MB/252MB      65MB/252MB
16M			2689				2647			2611			2319
            126MB/252MB         126MB/252MB     126MB/252MB     126MB/252MB    
32M			5574				5765			5248			5205
            248MB/252MB         248MB/252MB     248MB/252MB     248MB/252MB
64M			13563				13288		    12451			11314						
            492MB/497MB         492MB/497MB     492MB/497MB     492MB/497MB        
128M		29415				26851			26356           23740 
            980MB/1230MB        980MB/1230MB    980MB/1230MB    980MB/1230MB

###### Observation:
Take 2(CASE 1) :  Avoiding the creation of an extra array in every Merge Method that we call.  
Take 3(CASE 2): Avoiding the copying of an array to another array.  
Take 4(CASE 3): Using Insertion Sort for smaller Threshold or merge Sort in iterative method  
Take 6(CASE 4): Using Insertion Sort for smaller Threshold and merge Sort in iterative method  

Since in Case 2 , we are avoiding the copying of the original array to another array in Merge calls, the time taken to execute in Case 2 should decrease.    
  
In Case 4, We are trying to use Insertion Sort to sort elements within the threshold  and then use Merge Sort iteratively on the sorted blocks which should reduce the time taken to execute large integers drastically. 

There was no change in the memory consumption of different algorithms for same input size.  