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

(defn kth
  "find the k'th element of a list"
  [l k]
  (if (or
       (>  k (count l))
       ((comp not pos?) k))
    nil
    (loop [acc 1
           xs l]
      (if (= acc k)
        (first xs)
        (recur (inc acc) (rest xs))))))

(defn length
  "find the number of elements of a list"
  [l]
  (reduce (fn [acc _] (inc acc)) 0 l))

(defn my-reverse
  "reverse a list"
  [l]
  (reduce #(conj %1 %2) '() l))
