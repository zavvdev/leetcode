/*
You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

 

Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 

Constraints: 1 <= n <= 45
 */

/**
 * @param {number} n
 * @return {number}
 */
var climbStairs = function (n) {
  var fib = [];

  for (let i = 1; i <= 45; i++) {
    let len = fib.length;
    let idx = i - 1;
    len < 3
      ? (fib[idx] = i)
      : (fib[idx] = fib[len - 1] + fib[len - 2]);
  }

  return fib[n - 1];
};
