/**
 * Given a non-negative integer x, return the square root 
 * of x rounded down to the nearest integer. The returned 
 * integer should be non-negative as well.
 *
 * You must not use any built-in exponent function or operator.
 * For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
 *
 * Example 1:
 *  Input: x = 4
 *  Output: 2
 *  Explanation: The square root of 4 is 2, so we return 2.
 *
 * Example 2:
 *  Input: x = 8
 *  Output: 2
 *  Explanation: The square root of 8 is 2.82842..., and since 
 *  we round it down to the nearest integer, 2 is returned.
 *
 * Constraints:
 *  0 <= x <= 2^31 - 1
 */

/**
 * @param {number} x
 * @return {number}
 */
var mySqrt = function (x) {
  if (x === 0) return 0;
  if (x === 1) return 1;

  var start = 2;
  var end = (x / 2) | 0;

  while (end > start) {
    var kStart = start * start;
    var kEnd = end * end;

    if (kStart === x) return start;
    if (kEnd === x) return end;

    var mid = ((end - start) / 2 + start) | 0;
    var next = mid + 1;
    var prev = mid - 1;
    var k = mid * mid;

    if (k === x) {
      return mid;
    } else if (k > x) {
      if (prev >= start && prev * prev < x) return prev;
      var nextEnd = (end / 2) | 0;
      end = nextEnd < start ? mid : nextEnd;
    } else {
      if (next <= end && next * next > x) return mid;
      var nextStart = start + ((end / 2) | 0);
      start = nextStart > end ? mid : nextStart;
    }
  }

  return end;
};

console.log("1) 0:", mySqrt(0), mySqrt(0) === 0);
console.log("2) 1:", mySqrt(1), mySqrt(1) === 1);
console.log("3) 2:", mySqrt(2), mySqrt(1) === 1);
console.log("4) 3:", mySqrt(3), mySqrt(1) === 1);
console.log("5) 2:", mySqrt(4), mySqrt(4) === 2);
console.log("6) 2:", mySqrt(5), mySqrt(5) === 2);
console.log("7) 2:", mySqrt(6), mySqrt(6) === 2);
console.log("8) 2:", mySqrt(7), mySqrt(7) === 2);
console.log("9) 2:", mySqrt(8), mySqrt(8) === 2);
console.log("10) 3:", mySqrt(9), mySqrt(9) === 3);
console.log("11) 4:", mySqrt(16), mySqrt(16) === 4);
console.log("12) 5:", mySqrt(32), mySqrt(32) === 5);
console.log("13) 8:", mySqrt(64), mySqrt(64) === 8);
console.log("14) 11:", mySqrt(128), mySqrt(128) === 11);
console.log(
  "15) 44485:",
  mySqrt(1978959248),
  mySqrt(1978959248) === 44485,
);
