(ns binary-search)

(defn b-search
  ([target sorted-vec]
   (b-search target sorted-vec 0 (dec (count sorted-vec))))
  ([target sorted-vec left right]
   (if (< right left)
     -1
     (let [mid-index (int (Math/floor (/ (+ left right) 2)))
           mid-value (get sorted-vec mid-index)]
       (cond
         (= mid-value target) mid-index
         (< target mid-value) (b-search
                               target
                               sorted-vec
                               left
                               (dec mid-index))
         :else (b-search
                target
                sorted-vec
                (inc mid-index)
                right))))))

(assert (= (b-search 5 [1 2 3 4 5 6 7 8 9 10]) 4))
(assert (= (b-search 9 [1 2 3 4 5 6 7 8 9 10]) 8))
(assert (= (b-search 100 [1 2 3 4 5 6 7 8 9 10]) -1))
(assert (= (b-search 1 [1]) 0))
(assert (= (b-search 1 []) -1))
