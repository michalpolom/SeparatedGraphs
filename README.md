# SeparatedGraphs

A simple Java application that return number of separated graphs from listed pairs.
## Task description

The first line of input contains a positive number _n_, next _n_ lines contains pairs of positive integers, where each pair identifies a connection between two vertices in a graph. Please provide a working code that will give us the answer for the following questions: how many separated graphs are in the input.
## How it works?

Run the application in console.

For example:
```
java <path>\SeparatedGraphs.java
```
then paste or write your input
```
3
4 3
1 4
5 6
```
where number in first line is number of pairs and in following lines are pairs.

In return in this example you receive
```
2
```

### Exceptions
When you pass a non-integer argument or non-positive integers in return you receive
```
Arguments should be between 1 and 2147483647
```
When you pass not 2 value in pair you receive
```
Too many values in line
```