;; UNFINISHED

(comment "
You are given an array routes representing bus routes where routes[i]
is a bus route that the ith bus repeats forever.
For example, if routes[0] = [1, 5, 7], this means
that the 0th bus travels in the sequence 
1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not 
on any bus initially), and you want to go to the bus stop target.
You can travel between bus stops by buses only.
Return the least number of buses you must take to travel
from source to target. Return -1 if it is not possible.
          
Example:

Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus
stop 7, then take the second bus to the bus stop 6.")

(defn bus-routes [routes source target]

  (defn find-route-index [place]
    (loop [index 0]
      (if (= index (count routes))
        -1
        (if (contains? (set (get routes index)) place)
          index
          (recur (inc index))))))

  (defn intersects? [route_one route_two]
    (boolean (some (set route_one) route_two)))

  (defn get-intersections [route_index]
    (let [result (atom [])]
      (loop [index 0]
        (if (= index (count routes))
          @result
          (do
            (when (and
                   (not= index route_index)
                   (intersects?
                    (get routes route_index)
                    (get routes index)))
              (swap! result conj index))
            (recur (inc index)))))))
  
  (defn get-each-route-intersections []
    (let [result (atom [])]
      (loop [index 0]
        (if (= index (count routes))
          @result
          (do
            (swap! result conj (get-intersections index))
            (recur (inc index)))))))

  (let [source_index (find-route-index source)
        target_index (find-route-index target)
        source_intersections (get-intersections source_index)]
    (cond 
      (or (= source_index -1) (= target_index -1)) -1
      (= source_index target_index) 1
      (contains? (set source_intersections) target_index) 2
      :else (let [target_itersections (get-intersections target_index)]
              (if (intersects? source_intersections target_itersections)
                (+
                 (count source_intersections)
                 (count target_itersections))
                -1)))))

(bus-routes [[1 2 7] [3 6 7]] 1 6)

(bus-routes [[1 2 7 12] [3 6 7 15]] 15 12)

(bus-routes [[1 2 7] [3 6 7]] 1 2)

(bus-routes [[1 2 7] [3 6 7]] 10 20)

(bus-routes [[1 2 7] [3 6 7] [3 2 39] [8 45 39]] 2 45)

(bus-routes [[1 2 7]
             [3 6 7]
             [100 1002 39]
             [65 23 11]
             [87 41 59]
             [8 45 39]] 2 45)

(bus-routes [[1002 98 97]
             [1 2 7]
             [1002 6 7 100]
             [100 1002 38]
             [65 23 11]
             [4023 38 87]
             [87 45 59]
             [8 45 39]
             [101 102 103]] 2 8) ;; should be 3. Need to check for intersection with subintersections

(comment "
Each route intersections (last function call):
[
 [2]
 [2]
 [0 1 3]
 [2 6]
 []
 [6]
 [3 5]
 []
]
")

(defn get-int [route_index routes]
  (let [result (atom [])]
    (loop [index 0]
      (if (= index (count routes))
        @result
        (do
          (when (and
                 (not= index route_index)
                 (intersects?
                  (get routes route_index)
                  (get routes index)))
            (swap! result conj index))
          (recur (inc index)))))))

(defn get-each [routes]
  (let [result (atom [])]
    (loop [index 0]
      (if (= index (count routes))
        @result
        (do
          (swap! result conj (get-int index routes))
          (recur (inc index)))))))

(get-each [[1002 98 97]
           [1 2 7]
           [1002 6 7 100]
           [100 1002 38]
           [65 23 11]
           [4023 38 87]
           [87 45 59]
           [8 45 39]
           [101 102 103]])


[7 [6 [5 [3 [0 2 5] 6 [5 7]]] [7]]]
;; loop over each route intersection and follow indexes until find target. Save each followed index to storage
;; and dont follow it again. 