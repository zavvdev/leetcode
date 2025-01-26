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

  if (s_len === 0 || s_len === 1) return 0;

  var st = [];

  for (let i = 0; i < s_len; i++) {
    if (s[i] === "(") {
      st.push(i);
    } else {
      let st_len = st.length;
      if (st_len === 0 || s[st[st_len - 1]] !== "(") st.push(i);
      else st.pop();
    }
  }

  if (st.length === 0) return s_len;
  st.push(s_len);

  return Math.max(...st.map((x, i) =>
    i === 0 && x !== 0 ? x : x - (st[i - 1] || 0) - 1,
  ));
};

// Test cases

console.log('"(()" Expect 2:', longest_valid_parentheses("(()"));

console.log(
  '")()())" Expect 4:',
  longest_valid_parentheses(")()())"),
);

console.log('"" Expect 0:', longest_valid_parentheses(""));

console.log(
  '")()()())()()(" Expect 6:',
  longest_valid_parentheses(")()()())()()("),
);

console.log(
  '"(()())(" Expect 6:',
  longest_valid_parentheses("(()())("),
);

console.log(
  '"(()()()(" Expect 6:',
  longest_valid_parentheses("(()()()("),
);

console.log('"(((()" Expect 2:', longest_valid_parentheses("(((()"));

console.log(
  '"(((()))))" Expect 8:',
  longest_valid_parentheses("(((()))))"),
);

console.log(
  '")(((((" Expect 0:',
  longest_valid_parentheses(")((((("),
);

console.log(
  '")()((((" Expect 2:',
  longest_valid_parentheses(")()(((("),
);

console.log(
  '"()()))))()()(" Expect 4:',
  longest_valid_parentheses("()()))))()()("),
);

console.log('"()(()" Expect 2:', longest_valid_parentheses("()(()"));

console.log(
  '"))))())()()(()" Expect 4:',
  longest_valid_parentheses("))))())()()(()"),
);
