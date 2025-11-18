# Title

## Reasoning

~~Since we are given the number of points and then a list of edges. We only need to add one edge per unconnected point, as that is the minimum edges that can be added to connect it to the graph. So all we have to find is the number of unconnected points, and that will give us the number of connections we need to make.~~

This would be a great application of the union find algorithm. We can go through each node, find it's neighbors and add them to it's boss group, then whenever we run out of neighbors to move to, we increment the count of edges to add, then move to a new subtree.

Alternatively we could use 

## Psudocode

```python
def example():
    return solution
```

## Proof

Proof of the algorithm here.

## Running Time

### Estimate

The running time is $\Theta(?)$ or $O(?)$

### Running Time Reasoning

Reasoning Here
