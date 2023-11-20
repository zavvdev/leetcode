(comment "
Given two sorted arrays nums1 and nums2 of size m and n respectively,
return the median of the two sorted arrays.
          
Example:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.")

(defn get-middle [vec]
  (if (and (>= (count vec) 0) (<= (count vec) 2))
    vec
    (let [m (- (/ (count vec) 2) 1)]
      (if (not= (rem (count vec) 2) 0)
        [(get vec (+ m 1))]
        [(get vec m) (get vec (+ m 1))]))))

(defn sort-vectors [vec1 vec2]
  (into [] (sort (into [] (concat vec1 vec2)))))

(defn get-median [vec1 vec2]
  (let [middle (get-middle (sort-vectors vec1 vec2))]
    (if (= (count middle) 1)
      (get middle 0)
      (/ (+ (get middle 0) (get middle 1)) 2))))

(get-median [1, 2] [3, 4])
(get-median [1, 3] [2])
(get-median [1, 3] [])
(get-median [] [3])
(get-median [4, 6, 7] [3 8 9])


