// For a given n list all prime numbers $\leq n$ in increasing order

#include <iostream>

const int MAX_SIZE = 1000;

// Get Primes returns the length of the primes array, stored in the buffer
/**
 * n: the number below which all primes will be returned
 * buffer: An array in which to save the primes
 * bufferSize: the maximum length of the buffer
 * return: The length of the data saved in the buffer
 */
int getPrimes(int n, int* buffer, int bufferSize) {
    if (n < 2) return 0;// If n < 2 then there can be no primes below n

    int primesLen = 0; // The length of the data saved in the buffer
    for (int i = 2; i <= n; i ++) {
        bool isPrime = true; // Sets prime to false if there are any factors in primes
        for (int j = 0; j < primesLen; j ++) {
            isPrime = isPrime && !(i % buffer[j] == 0);
        }
        if (isPrime && (primesLen < bufferSize)) {
            buffer[primesLen] = i;
            primesLen ++;
        }
    }
    return primesLen;
}

int main () {
    int input;
    std::cin >> input;

    // create output buffer with the maximum possible size
    int buffer[MAX_SIZE];
    int bufferLen = getPrimes(input, buffer, MAX_SIZE);
    // Print each line in the now updated buffer
    for (int i = 0; i < bufferLen; i ++) {
        std::cout << buffer[i] << "\n";
    }
}