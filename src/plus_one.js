/**
 * You are given a large integer represented as an integer array digits,
 * where each digits[i] is the ith digit of the integer.
 * The digits are ordered from most significant to least significant
 * in left-to-right order. The large integer does not contain any leading 0's.
 * Increment the large integer by one and return the resulting array of digits.
 *
 * Constraints:
 *  1 <= digits.length <= 100
 *  0 <= digits[i] <= 9
 *
 * Example 1:
 *  Input: digits = [1, 2, 3]
 *  Output: [1, 2, 4]
 *
 * Example 2:
 *  Input: digits = [4, 3, 2, 1]
 *  Output: [4, 3, 2, 2]
 *
 * Example 3:
 *  Input: digits = [9, 9]
 *  Output: [1, 0, 0]
 */

/**
 * @param {number[]} digits
 * @returns {number[]}
 */
var plus_one = function (digits) {
  if (digits.length === 0) return [1];
  var last = digits[digits.length - 1];
  var sliced = digits.slice(0, -1);
  return last < 9 ? [...sliced, last + 1] : [...plus_one(sliced), 0];
};

console.log("[1, 2, 3]:", plus_one([1, 2, 3])); // [1, 2, 4]
console.log("[4, 3, 2, 1]:", plus_one([4, 3, 2, 1])); // [4, 3, 2, 2]
console.log("[9]:", plus_one([9])); // [1, 0]
console.log("[9, 9]:", plus_one([9, 9])); // [1, 0, 0]
console.log("[9, 9, 9, 9]:", plus_one([9, 9, 9, 9])); // [1, 0, 0, 0, 0]
console.log("[3, 5, 8]:", plus_one([3, 5, 8])); // [3, 5, 9]
