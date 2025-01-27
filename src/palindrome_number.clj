(comment "
Given an integer x, return true if x is a 
palindrome, and false otherwise.

Example:
Input: x = 121
Output: true
Explanation: 121 reads as 121 from left
          to right and from right to left.")

(ns palindrome-number
  (:require [clojure.string :as string]))

(defn palindrome? [x]
  (let [s (str x)]
    (= s (string/join (reverse s)))))

(assert (= (palindrome? 121) true))
(assert (= (palindrome? 5) true))
(assert (= (palindrome? 42) false))
(assert (= (palindrome? -121) false))
