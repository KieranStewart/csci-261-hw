# Title

## Reasoning

To solve this problem we can use the bfs or dfs algorithms to find the shortest path, then if we use bfs we can keep tracking how many possible paths there are that reach the output in the same ammount of time.

## Psudocode

```python
count_optimal_paths(G=(V,E), s, t):
    make an adjacency list: adj

    for every vertex v:#O(n)
        seen[v] = false
        dist[v] = true
    beg = 1
    end = 2
    Q[1] = s
    seen[s] = true
    while beg<end:#O(m)
        head=Q[beg]
        for every neighbor u of head:#O(deg(u))
            if not seen[u]
                Q[end++] = u
                dist[u] = dist[head]+1
                seen[u] = true
        beg ++
```

## Proof

Proof of the algorithm here.

## Running Time

### Estimate

The running time is $\Theta(?)$ or $O(?)$

### Running Time Reasoning

Reasoning Here
