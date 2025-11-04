# Matrix Chain Parenthesation

## Reasoning

We can use dynamic programming to solve this problem.

What: $S[L,R]$ the optimal cost to multiply matrixes $L$ and $R$

How: $S[L,R]=\begin{pmatrix}0\text{ if } L=R\\\min_{k\in L \text { to } R-1}S[L,k]+S[k+1,R]+a_{L-1}\cdot a_k\cdot a_R$

Where: $S[1,n]$

In order to get the order they are in, it will also be convenient to save the k value we end up using for every entry in S. We will do this in another 2d array for clairity. Then we can make a recursive backtracking algorithm that gets the right side of the k value and the left side and returns $A_{\text{left}}\times A_{\text{right}}$ where the left and right As are broken down into the elements that make them up.

## Psudocode

```python
def backtrack(array<array<int>> S, array<array<int>> SK, L, R):
    if L=R: return "A" + L
    else:
        k = SK[L, R]
        return "( " backtrack(S, SK, L, k) + " x " + backtrack(S, SK, k+1, R) + " )"

def matrix_chain_paren(dimensions: array<int>):
    for int i = 0 to n:
        S[i,i] = 0
    for d=1 to n:
        for L=1 to n-d:
            R=L+d
            S[L,R] = S[L,L] + S[L+1, R] + dimensions[L-1] * dimensions[L] * dimensions[R]
            for k=L+1 to R-1:
                contender = S[L,K] + S[k+1, R] + dimensions[L-1] * dimensions[k] * dimensions[R]
                if contender < S[L,R]:
                    S[L,R] = contender
                    SK[L,R] = k
    return S[1,n], backtrack(S, SK, 1, n)
```

## Proof

If the chain has a length of one then no multiplication must be done, and the result is zero. Past that point, each element of S is the optimal order of operations for the given L and R parameters. This is done by checking each splitting point k along which the equation could be split, and then doing the calculation for how many computations would be needed if the equation were split there and finding the minimum. This means that the final result will be the absolute minimum number of calulations that need to be completed to do the matrix multiplication.

## Running Time

### Estimate

The running time is $O(n^3)$

### Running Time Reasoning

```python
def backtrack(array<array<int>> S, array<array<int>> SK, L, R):
    if L=R: return "A" + L
    else:
        k = SK[L, R]
        return "( " backtrack(S, SK, L, k) + " x " + backtrack(S, SK, k+1, R) + " )"

def matrix_chain_paren(dimensions: array<int>):
    for int i = 0 to n: # O(n)
        S[i,i] = 0
    for d=1 to n: # runs n times -> O(n^3)
        for L=1 to n-d: # runs n times -> O(n^2)
            R=L+d
            S[L,R] = S[L,L] + S[L+1, R] + dimensions[L-1] * dimensions[L] * dimensions[R]
            for k=L+1 to R-1: # Runs n times -> O(n)
                contender = S[L,K] + S[k+1, R] + dimensions[L-1] * dimensions[k] * dimensions[R]
                if contender < S[L,R]:
                    S[L,R] = contender
                    SK[L,R] = k
    return S[1,n], backtrack(S, SK, 1, n)
```

The backtracking algorithm only takes $O(n)$ time since each recursive call cuts it roughly in half, meaning $T(n)=2\cdot T(\frac n 2)=4\cdot T(\frac n 4) = 8\cdot T(\frac n 8) = 2^n T(\frac n {2^n}) = 2^n \cdot d \cdot (\frac n {2^n}) = d \cdot n$ or $O(n)$. So the algorithm as a whole takes $O(n^3)$ time.
