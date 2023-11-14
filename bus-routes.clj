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

  (defn get-path [start_index prev_path]
    (loop [index 0]
      (if (= index (count routes))
        prev_path
        (do
          (when (and
                 (not= index start_index)
                 (intersects? 
                  (get routes index) 
                  (get routes start_index))))))))

  (let [source_index (find-route-index source)
        target_index (find-route-index target)]
    (cond 
      (or (= source_index -1) (= target_index -1)) -1
      (= source_index target_index) 1
      (and
       (= (count routes) 2)
       (intersects?
        (get routes source_index)
        (get routes target_index))) 2
      :else (let [source_intersection (get-intersections source_index)
                  target_itersection (get-intersections target_index)]
              (if (intersects? source_intersection target_itersection)
                (+
                 (count source_intersection)
                 (count target_itersection))
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

(bus-routes [[1 2 7]
             [1002 6 7]
             [100 1002 39]
             [65 23 11]
             [87 41 59]
             [8 45 39]] 2 45) ;; should be 3. Need to check for intersection with subintersections
