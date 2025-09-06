// For a given n list all prime numbers $\leq n$ in increasing order

#include <iostream>

const int MAX_SIZE = 1000;

struct Pair {
    int smallest;
    int second;
};

struct Pair smallestTwo(int* array, int length) {
    int smallest = array[0];
    for (int i = 1; i < length; i ++) {
        if (array[i] < smallest) smallest = array[i];
    }
    int second;
    bool secondInit = true; // Represents whether second still needs to get a value
    for (int i = 0; i < length; i ++) {
        if ((secondInit || (array[i] < second)) && (array[i] != smallest)) {
            secondInit = false;
            second = array[i];
        }
    }
    Pair out = {smallest, second};
    return out;
}

int main () {
    int input[10000];
    int inputLen;
    std::cin >> inputLen;
    for (int i = 0; i < inputLen; i ++) {
        std::cin >> input[i];
    }
    Pair output = smallestTwo(input, inputLen);
    std::cout << output.smallest << "\n" << output.second; 
}