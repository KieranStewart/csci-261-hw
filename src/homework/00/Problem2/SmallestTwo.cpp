// Finds smallest two elements in an array

#include <iostream>

const int MAX_SIZE = 10000; // Maximum size for the input array

// Lets me return two elements without having to pass an array
struct Pair {
    int smallest;
    int second;
};

struct Pair smallestTwo(int* array, int length) {
    int smallest = array[0];
    // Find the smallest value
    for (int i = 1; i < length; i ++) {
        if (array[i] < smallest) smallest = array[i];
    }
    int second;
    bool secondInit = true; // Represents whether second still needs to get a value
    for (int i = 0; i < length; i ++) {
        // if the item at i is not the smallest, and either the second smalles doesn't have a
        //  value yet or this one is smaller then the value it currently has
        if ((secondInit || (array[i] < second)) && (array[i] != smallest)) {
            secondInit = false; // Second now has a value
            second = array[i];
        }
    }
    // Add the two results to a pair and returns them
    Pair out = {smallest, second};
    return out;
}

int main () {
    int input[MAX_SIZE];
    int inputLen; // The size for input
    std::cin >> inputLen; // Assigning the size
    for (int i = 0; i < inputLen; i ++) {
        std::cin >> input[i];
    }
    Pair output = smallestTwo(input, inputLen);
    std::cout << output.smallest << "\n" << output.second; 
}