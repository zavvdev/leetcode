/**
 * Given two binary strings a and b, return their sum as a binary string.
 *
 * Example:
 *  Input: a = " 1010 ", b = " 1011 "
 *  Output: " 10101 "
 *
 * Constraints:
 *  1 <= a.length, b.length <= 10^4
 *  a and b consist only of '0' or '1' characters.
 *  Each string does not contain leading zeros except for the zero itself.
 */

var binaryToDecimal = (x) => {
  var xLen = x.length;
  var res = BigInt(0);

  for (let i = 0; i < xLen; i++)
    if (x[i] === "1") res += 2n ** BigInt(xLen - i - 1);

  return res;
};

var decimalToBinary = (x) => {
  if (x === 0n) return "0";

  var q = BigInt(x);
  var r = "";

  while (q > 0n) {
    r = (q & 1n) + r;
    q = q / 2n;
  }

  return r;
};

/**
 * @param {string} a
 * @param {string} b
 * @return {string}
 */
var addBinary = function(a, b) {
  return decimalToBinary(binaryToDecimal(a) + binaryToDecimal(b));
};
