(comment "
Given two binary strings a and b, return their sum as a binary string.

Example:
  Input: a = " 1010 ", b = " 1011 "
  Output: " 10101 "
 
Constraints:
  1 <= a.length, b.length <= 10^4
  a and b consist only of '0' or '1' characters.
  Each string does not contain leading zeros except for the zero itself.
")

(ns add-binary
  (:require [clojure.string :as str]))

(defn binary-to-decimal [x]
  (let [x-len (count x)]
    (reduce (fn [acc i]
              (if (= (get x i) \1)
                (+ acc (apply * (repeat (- x-len i 1) 2N)))
                acc)) 0N (range x-len))))

(defn decimal-to-binary [x]
  (if (zero? x)
    "0"
    (loop [q (bigint x), r ()]
      (if (= q 0N)
        (str/join "" r)
        (recur (quot q 2N) (conj r (rem q 2N)))))))

(defn add-binary [a b]
  (decimal-to-binary (+
                      (binary-to-decimal a)
                      (binary-to-decimal b))))

(assert (= (add-binary "0" "0") "0"))

(assert (= (add-binary "1" "1") "10"))

(assert (= (add-binary "11" "1") "100"))

(assert (= (add-binary "1010" "1011") "10101"))

(assert (= (add-binary
            "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101"
            "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011")
           "110111101100010011000101110110100000011101000101011001000011011000001100011110011010010011000000000"))
