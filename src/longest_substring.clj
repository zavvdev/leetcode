(comment "
Longest Substring Without Repeating Characters.
Given a string s, find the length of the longest substring without repeating characters.

Example 1:
  Input: s = 'abcabcbb'
  Output: 3
  Explanation: The answer is 'abc', with the length of 3.

Constraints:
  0 <= s.length <= 5 * 10^4
  s consists of English letters, digits, symbols and spaces.
")

(ns longest-substring)

(defn longest-substring [s]
  (if (empty? s)
    0
    (->> (range (count s))
         (reduce (fn [counts i]
                   (let [chars (atom #{(get s i)})]
                     (loop [j (inc i)]
                       (when (and (< j (count s)) (not (contains? @chars (get s j))))
                         (swap! chars (fn [p] (conj p (get s j))))
                         (recur (inc j))))
                     (conj counts (count @chars)))) '())
         (apply max))))

(assert (= (longest-substring "abcabcbb") 3))
(assert (= (longest-substring "bbbbb") 1))
(assert (= (longest-substring "pwwkew") 3))
(assert (= (longest-substring "") 0))
(assert (= (longest-substring "dvdf") 3))
