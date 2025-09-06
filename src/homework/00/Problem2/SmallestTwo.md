# CSCI-261-05 Homework 00 SmallestTwo

Kieran Stewart

## Algorithm Description

My algorithm goes through each element and finds the smallest element, it then goes through and finds the smallest element that isn't the previously defined smallest.

## Psudo Code

```
find smallest two(array):
    set smallest number to the first element of the array
    go through each element e of the array 
        replace smallest with e if it is smaller
    now go through each element e the array
        set second smallest to e if:
            (it is smaller then second smallest 
            or second smallest is not initialized)
            and e is not the smallest
    return smallest and second smallest
```

## Proof

We first find the smallest, by passing through each element and comparing it to the current smallest. Then we find the second smallest, by passing through again and finding the smallest element that isn't already saved as the smallest. By this point, the smallest element has already been determined, so the smallest element that isn't excluded must be the second smallest.

## Running Estimate

$O(n)$

This code checks the array over twice, and all operations inside those loops run in constant time. This gives us a the ammount of time that it takes to run to be $T(n) = 2n$ and we can determine that it runs in $O(n)$.