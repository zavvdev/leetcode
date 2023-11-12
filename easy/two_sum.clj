(comment "
Given an array of integers nums and an integer target,
return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution,
and you may not use the same element twice. You can return
the answer in any order.

Example:
Input: nums = [2,7,11,15], target = 9, Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1]")

(defn two-sum [nums, target]
  (defn get-pair-index [i]
    (let [r (.indexOf nums (- target (get nums i)))]
      (condp = r
        i -1
        r)))
  (let [x (atom -1) y (atom -1)]
    (loop [i 0]
      (if (and (not= @x -1) (not= @y -1))
        [@x @y]
        (if (= i (count nums))
          nil
          (do
            (swap! x (fn [&] i))
            (swap! y (fn [&] (get-pair-index i)))
            (recur (inc i))))))))

(two-sum [2, 7, 11, 15] 9)
(two-sum [3, 2, 4] 6)
(two-sum [3, 3] 6)
(two-sum [11, 5] 6)
(two-sum [1, 2, 3, 4, 10] 7)
(two-sum [] 42)
