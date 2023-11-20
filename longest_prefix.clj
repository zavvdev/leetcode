(comment "
Write a function to find the longest common prefix string
amongst an array of strings. If there is no common prefix,
return an empty string.
          
Example:

Input: strs = ['flower','flow','flight']
Output: 'fl'")

(defn get-shortest [words]
  (get (into [] (sort #(compare (count %1) (count %2)) words)) 0))

(defn has-prefix? [words prefix]
  (= (count (filter #(clojure.string/starts-with? %1 prefix) words))
     (count words)))

(defn get-longest-prefix [words]
  (if (= (count words) 0)
    ""
    (let [shortest (get-shortest words)]
      (loop [i (count shortest)]
        (if (= i 0)
          ""
          (let [prefix (reduce str (take i shortest))]
            (if (has-prefix? words prefix)
              prefix
              (recur (dec i)))))))))

(get-longest-prefix ["flower", "flow", "flight"])
(get-longest-prefix ["dog", "racecar", "car"])
