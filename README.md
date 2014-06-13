RuzzleSolver
============

Here's a YouTube video that shows the working of this project : http://youtu.be/O3X_zRclYB8

What does it do ?
-----------------
* Takes in a Ruzzle grid as input and finds all words that can be formed.
* An automatic swiper that in conjunction with Bluestacks lets you swipe every possible word thereby getting an insanely high score !

How does it work ?
------------------
* Runs a DFS on the Ruzzle grid and at each instance checks if the word formed is a valid one.
* Since the size of a grid is just 4 x 4, optimizations (such as using a suffix tree) aren't that important.
* The total list of possible words is obtained fairly quickly. Less than 10 seconds on an i7 machine (without using any form of parallelization or multithreading). 
* The TWL06 wordlist, same as the one used by Ruzzle, is used as the dictionary.
* The automated swiping is done by controlling the mouse pointer and getting it to move around automatically. Select the right co ordinates of the tiles on the screen and let it rip !