from collections import defaultdict

def most_frequent_pair_sum(arr):
    n = len(arr)
    sum_counts = defaultdict(int)

    # Count all pair sums
    for i in range(n):
        for j in range(i + 1, n):
            pair_sum = arr[i] + arr[j]
            sum_counts[pair_sum] += 1

    # Find the sum with the highest frequency
    max_freq = 0
    best_t = float('inf')

    for t, freq in sum_counts.items():
        if freq > max_freq or (freq == max_freq and t < best_t):
            max_freq = freq
            best_t = t

    return best_t, max_freq

if __name__ == "__main__":
    length = int(input(""))
    inputs = []
    for i in range(length):
        inputs.append(int(input()))
    print(most_frequent_pair_sum(inputs))