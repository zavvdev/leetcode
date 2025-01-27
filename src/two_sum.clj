(comment "
Given an array of integers nums and an integer target,
return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution,
and you may not use the same element twice. You can return
the answer in any order.

Example:
Input: nums = [2,7,11,15], target = 9, Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1]")

(ns two-sum)

(defn get-pair-index [nums target index]
  (let [r (.indexOf nums (- target (get nums index)))]
    (condp = r
      index -1
      r)))

(defn two-sum [nums, target]
  (let [x (atom -1) y (atom -1)]
    (loop [i 0]
      (if (and (not= @x -1) (not= @y -1))
        [@x @y]
        (if (= i (count nums))
          nil
          (do
            (swap! x (fn [_] i))
            (swap! y (fn [_] (get-pair-index nums target i)))
            (recur (inc i))))))))

(assert (= (two-sum [2, 7, 11, 15] 9) [0, 1]))
(assert (= (two-sum [3, 2, 4] 6) [1, 2]))
(assert (= (two-sum [3, 3] 6) [1, 0]))
(assert (nil? (two-sum [11, 5] 6)))
(assert (= (two-sum [1, 2, 3, 4, 10] 7) [2, 3]))
(assert (nil? (two-sum [] 42)))
