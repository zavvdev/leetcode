/**
 * @param {string} s
 * @returns {{
 *    valid: boolean;
 *    stack: Array<{
 *      char: string;
 *      pos: number;
 *    }>;
 * }}
 */
var is_valid_parens = function(s) {
  /**
   * @type {Array<{
   *    char: string;
   *    pos: number
   * }>}
   */
  var stack = [];

  var pairs = {
    "(": ")",
    "[": "]",
    "{": "}",
  };

  for (let i = 0; i < s.length; i++) {
    let char = s[i];

    if (char in pairs) {
      stack.push({
        char,
        pos: i,
      });
    } else if (stack.length === 0 || pairs[stack.pop()?.char] !== char) {
      return {
        valid: false,
        stack: [
          {
            char,
            pos: i,
          },
        ],
      };
    }
  }

  return {
    valid: stack.length === 0,
    stack,
  };
};

/**
 * Given a string containing just the characters '(' and ')',
 * return the length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1:
 *  Input: s = "(()"
 *  Output: 2
 *  Explanation: The longest valid parentheses substring is "()".
 *
 * Example 2:
 *  Input: s = ")()())"
 *  Output: 4
 *  Explanation: The longest valid parentheses substring is "()()".
 *
 * Constraints:
 *
 * 0 <= s.length <= 3 * 10^4
 * s[i] is '(', or ')'.
 */

/**
 * @param {string} s
 * @return {number}
 */
var longest_valid_parentheses = function(s) {
  var s_len = s.length;

  if (s_len === 0) return 0;

  var res = is_valid_parens(s);

  if (res.valid) return s_len;

  var mark_invalid = (c, x) => {
    c[x.pos] = "0";
    return c;
  };

  return Math.max(
    ...res.stack
      .reduce(mark_invalid, s.split(""))
      .join("")
      .split("0")
      .map(longest_valid_parentheses),
  );
};

// Test cases

console.log('"(()" Expect 2:', longest_valid_parentheses("(()"));

console.log('")()())" Expect 4:', longest_valid_parentheses(")()())"));

console.log('"" Expect 0:', longest_valid_parentheses(""));

console.log(
  '")()()())()()(" Expect 6:',
  longest_valid_parentheses(")()()())()()("),
);

console.log('"(()())(" Expect 6:', longest_valid_parentheses("(()())("));

console.log('"(()()()(" Expect 6:', longest_valid_parentheses("(()()()("));

console.log('"(((()" Expect 2:', longest_valid_parentheses("(((()"));

console.log('"(((()))))" Expect 8:', longest_valid_parentheses("(((()))))"));

console.log('")(((((" Expect 0:', longest_valid_parentheses(")((((("));

console.log('")()((((" Expect 2:', longest_valid_parentheses(")()(((("));

console.log(
  '"()()))))()()(" Expect 4:',
  longest_valid_parentheses("()()))))()()("),
);

console.log('"()(()" Expect 2:', longest_valid_parentheses("()(()"));
