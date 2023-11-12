(comment "
Given an integer x, return true if x is a 
palindrome, and false otherwise.

Example:
Input: x = 121
Output: true
Explanation: 121 reads as 121 from left
          to right and from right to left.")

(defn palindrome? [x]
  (let [s (str x)]
    (= s (clojure.string/join (reverse s)))))

(palindrome? 121)
(palindrome? 5)
(palindrome? 42)
(palindrome? -121)
