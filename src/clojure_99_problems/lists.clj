(ns clojure-99-problems.lists)

(defn last-item 
  "return last item of list"
  [l]
  (reduce (fn [_ item] item) nil l))

(defn last-two-items
  "return last two items of list"
  [l]
  (if (seq l)
    (seq (reduce #(conj (subvec %1 1) %2)
                 (into [] (take 2 l))
                 (drop 2 l)))
    '()))
