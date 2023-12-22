(comment "
          Given an integer numRows, return the first numRows of Pascal's triangle.
          In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

          1
        1  1
      1  2  1
    1  3  3  1
  1  4  6  4  1  
          ")

(ns pascals-triangle)

(defn build-pascals-triangle [depth]
  (let [depth-range (map inc (range depth))
        empty-triangle (map
                        (fn [col-count] (vec (repeat col-count 1)))
                        depth-range)]
    (reduce
     (fn [triangle empty-row]
       (let [last-row (last triangle)]
         (conj
          triangle
          (into
           []
           (map-indexed
            (fn [empty-col-index empty-col-value]
              (let [x (get last-row (dec empty-col-index))
                    y (get last-row empty-col-index)]
                (if (or (nil? x) (nil? y))
                  empty-col-value
                  (+ x y))))
            empty-row)))))
     []
     empty-triangle)))

(assert (=
         (build-pascals-triangle 0)
         []))

(assert (=
         (build-pascals-triangle 1)
         [[1]]))

(assert (=
         (build-pascals-triangle 2)
         [[1], [1 1]]))

(assert (=
         (build-pascals-triangle 5)
         [[1], [1 1], [1 2 1], [1 3 3 1], [1 4 6 4 1]]))
