(comment "
          Given a string containing digits from 2-9 inclusive, return all possible letter combinations 
          that the number could represent. Return the answer in any order.
          ")

(ns phone-letter-combinations
  (:require [clojure.string :as string]))

(defn combine-each [key values]
  (into [] (map #(str key %) values)))

(defn get-combine-reducer [combine-values]
  (fn [carry key] (into carry (combine-each key combine-values))))

(defn letter-combinations [digits]
  (let [letter-map {"2" ["a" "b" "c"]
                    "3" ["d" "e" "f"]
                    "4" ["g" "h" "i"]
                    "5" ["j" "k" "l"]
                    "6" ["m" "n" "o"]
                    "7" ["p" "q" "r" "s"]
                    "8" ["t" "u" "v"]
                    "9" ["w" "x" "y" "z"]}
        split-digits (string/split digits #"")
        letters (into [] (map (fn [digit] (letter-map digit)) split-digits))
        result (atom [])]
    (if (= (count letters) 1)
      (get letters 0)
      (do
        (swap! result (fn [_] (get letters 0)))
        (loop [i 1]
          (if (<= i (dec (count letters)))
            (do
              (swap!
               result
               (fn [_] (reduce
                        (get-combine-reducer (get letters i))
                        []
                        @result)))
              (recur (inc i)))
            @result))))))

(assert (=
         (letter-combinations "2")
         ["a" "b" "c"]))

(assert (=
         (letter-combinations "23")
         ["ad" "ae" "af" "bd" "be" "bf" "cd" "ce" "cf"]))
