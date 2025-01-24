/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if: 
 *  Open brackets must be closed by the same type of brackets.
 *  Open brackets must be closed in the correct order.
 *  Every close bracket has a corresponding open bracket of the same type.
 *
 *  Example 1:
 *    Input: s = "({}[])"
 *    Output: true
 *
 *  Example 2:
 *    Input: s = "{[}]"
 *    Output: false
 */

/**
 * @param {string} s
 * @return {boolean}
 */
var is_valid_parens = function (s) {
  var s_len = s.length;

  if (s_len % 2 !== 0) return false;

  var stack = [];

  var pairs = {
    "(": ")",
    "[": "]",
    "{": "}",
  };

  for (let i = 0; i < s_len; i++) {
    let char = s[i];

    if (char in pairs) {
      stack.push(char);
    } else if (stack.length === 0 || pairs[stack.pop()] !== char) {
      return false;
    }
  }

  return stack.length === 0;
};
