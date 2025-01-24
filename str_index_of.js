/**
 * Given two strings needle and haystack, return the index of the first occurrence
 * of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Constraints:
 *  1 <= haystack.length, needle.length <= 10^4
 *  haystack and needle consist of only lowercase English characters.
 */

/**
 * @param {string} haystack
 * @param {string} needle
 * @returns {number}
 */
var str_index_of = function(haystack, needle) {
  if (haystack.length < needle.length) return -1;
  if (haystack === needle) return 0;

  outer: for (let h = 0; h < haystack.length; h++) {
    if (haystack[h] === needle[0]) {
      for (let n = 0; n < needle.length; n++)
        if (haystack[h + n] !== needle[n]) continue outer;

      return h;
    }
  }

  return -1;
};

str_index_of("leetcode", "leet"); // 0
str_index_of("leetcode", "tco"); // 3
str_index_of("leetcode", "code"); // 4
str_index_of("leetcode", "codee"); // -1
str_index_of("leet", "missing"); // -1
str_index_of("leet", "leet"); // 0
str_index_of("leetcode", "leeto"); // -1
str_index_of("abc", "c"); // 2
str_index_of("mississippi", "issip"); // 4
