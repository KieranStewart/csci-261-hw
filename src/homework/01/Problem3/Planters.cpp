#include <iostream>
using namespace std;
// Merge two sorted halves arr from start to mid and arr from mid + 1 to the end
void merge(int* arr, int* newArr, int start, int mid, int end) {
    int i = start;
    int j = mid + 1;
    int k = start;

    // Merge elements into newArr
    while (i <= mid && j <= end) {
        if (arr[i] >= arr[j]) {
            newArr[k++] = arr[i++];
        } else {
            newArr[k++] = arr[j++];
        }
    }

    // Copy remaining elements from left half and right
    while (i <= mid) {
        newArr[k++] = arr[i++];
    }
    while (j <= end) {
        newArr[k++] = arr[j++];
    }
    // Copy merged elements back into original array
    for (int idx = start; idx <= end; ++idx) {
        arr[idx] = newArr[idx];
    }
}

// Merge sort algorithm
void mergeSortR(int* arr, int length, int* newArr, int start, int end) {
    if (start >= end) return; // if there is only one elemant

    int mid = start + (end - start) / 2;

    // Sort the two halves
    mergeSortR(arr, length, newArr, start, mid);
    mergeSortR(arr, length, newArr, mid + 1, end);

    // Merge sorted halves
    merge(arr, newArr, start, mid, end);
}

// A wrapper to make it cleaner to call
void mergeSort(int* arr, int length, int* newArr) {
    mergeSortR(arr, length, newArr, 0, length - 1);
}

// Checks if it is possible to move all the plants in oldPotsI to a new bigger pot.
bool movePlants(int oldPots[], int p, int newPots[], int r) {
    // Makes new arrays for the pots and enters the sorted elements from oldPots and newPots into them
    int *oldPotsTemp = new int[p]; 
    int *newPotsTemp = new int[r];
    mergeSort(oldPots, p, oldPotsTemp);
    mergeSort(newPots, r, newPotsTemp);

    // These pointers keep track of what pots have already been used
    int old_pot_pointer = 0;
    int new_pot_pointer = 0;

    // I realized we don't actually need to store the output arr unless required
    for (int i = 0; i < p; ++i) {
        int pot = oldPots[i];

        if (new_pot_pointer < r && pot < newPots[new_pot_pointer]) {
            new_pot_pointer ++;
        }
        else if (old_pot_pointer < i && pot < oldPots[old_pot_pointer]) {
            old_pot_pointer ++;
        }
        else {
            return false;
        }
    }

    return true;
}

int main() {
    // Read p and r (the sizes of the arrays) in from the command line
    int p, r;
    cin >> p >> r;

    int* old_pots = new int[p];
    int* new_pots = new int[r];

    // Read the old pot sizes in and save them in old_pots
    for (int i = 0; i < p; ++i) {
        cin >> old_pots[i];
    }

    // Read the new pot sizes in, saving them in new_pots
    for (int i = 0; i < r; ++i) {
        cin >> new_pots[i];
    }

    bool result = movePlants(old_pots, p, new_pots, r);

    // Output result
    cout << (result ? "YES" : "NO") << endl;

    delete[] old_pots;
    delete[] new_pots;

    return 0;
}
