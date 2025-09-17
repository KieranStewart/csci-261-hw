#include <iostream>
using namespace std;

// Merge two sorted halves arr from start to mid and arr from mid + 1 to the end
void merge(int* arr, int* newArr, int start, int mid, int end) {
    int i = start;
    int j = mid + 1;
    int k = start;

    // Merge elements into newArr
    while (i <= mid && j <= end) {
        if (arr[i] <= arr[j]) {
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

void findCommonSum(const int input[], int inputLen, int& maxStreakLen, int& maxStreakHolder) {
    // Create a sums array that can hold all possible sums
    int sumsLen = inputLen * inputLen;
    int* sums = new int[sumsLen];
    // Calculate all of thee sums
    int s = 0;
    for (int i = 0; i < inputLen; i ++) {
        for (int j = i; j < inputLen; j ++) {
            sums[s++] = input[i] + input[j];
        }
    }

    // Sort the sums array
    int *tempArr = new int[sumsLen];
    mergeSort(sums, sumsLen, tempArr);
    delete[] tempArr;

    // Find the most common sum (max streak)
    maxStreakLen = 0;
    maxStreakHolder = sums[0];
    int currentStreakLen = 1;
    for (int i = 1; i < sumsLen; ++i) {
        // Ignore empty sums
        if (sums[i] == 0) {
            continue;
        }

        if (sums[i] != sums[i - 1]) {
            currentStreakLen = 1;
        } else {
            currentStreakLen++;
        }
        if (currentStreakLen > maxStreakLen) {
            maxStreakLen = currentStreakLen;
            maxStreakHolder = sums[i];
        }
    }

    delete[] sums;
}

int main() {
    // Read p as the size of the array
    int p;;
    cin >> p;

    int* input_arr = new int[p];

    for (int i = 0; i < p; ++i) {
        cin >> input_arr[i];
    }

    int maxStreakLen, maxStreakHolder;
    findCommonSum(input_arr, p, maxStreakLen, maxStreakHolder);

    // Output result
    cout << maxStreakLen << " " << maxStreakHolder << endl;

    delete[] input_arr;
    return 0;
}