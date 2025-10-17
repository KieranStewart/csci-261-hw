# Master Theorem Practice

## $T(n) = 3T(\frac n 9) + \sqrt n$

$a=3,b=9,f(n)=n$
$n^{\log_b a}=n^{\log_9 3} = n^{\frac 1 2}$
Case: Case 2
Result: $\Theta(n^{\frac 1 2}\log n)$

## $T(n) = 4T(\frac n 2) + n^3$

$a=4,b=2,f(n)=n^3$
$n^{\log_b a}=n^{\log_2 4} = n^{2}$
Case: Case 3
Result: $\Theta (n^3)$

## $5T(\frac n 4) + n \log n$

$a=5,b=4,f(n)=n\log n$
$n^{\log_b a}=n^{\log_4 5}$
Case: Case 1
Result: $\Theta (n^{\log_4 5})$