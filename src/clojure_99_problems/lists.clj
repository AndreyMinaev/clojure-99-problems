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

(defn palindrome?
  "find out whether a list is a palindrome"
  [l]
  (= l (reverse l)))

(defn my-flatten
  "flatten a nested list structure"
  [l]
  (loop [acc []
         xs l]
    (if (seq xs)
      (if (seq? (first xs))
        (recur acc (concat (first xs) (rest xs)))
        (recur (conj acc (first xs)) (rest xs)))
      (seq acc))))

(defn compress
  "eliminate consecutive duplicates of list elements"
  [l]
  (reverse (reduce #(if (= (first %1) %2) %1 (conj %1 %2))
                   '()
                   l)))

(defn pack
  "pack repeated elements in separate sublists"
  [l]
  (reverse (reduce (fn [acc item]
                     (if (= (-> acc (first) (first)) item)
                       (conj (rest acc) (conj (first acc) item))
                       (conj acc (list item))))
                   '()
                   l)))

(defn encode
  "pack repeated elements in sublists. encode sublists in format (N E)
  where N is the number of duplicates of element E."
  [l]
  (map #(list (count %) (first %)) (pack l)))

(defn encode-modified
  "pack repeated elements in sublists. encode sublists in format (N E)
  where N is the number of duplicates of element E. if N is 0 then E should
  be without list as a wrapper and N not shown"
  [l]
  (map #(if (> (count %) 1)
          (list (count %) (first %))
          (first %))
       (pack l)))

(defn decode
  "decode list encoded with encode-modified"
  [l]
  (reduce #(if (seq? %2)
             (apply conj %1 (repeat (first %2) (second %2)))
             (conj %1 %2))
          '()
          (reverse l)))

(defn encode-direct
  [l]
  (reverse (reduce
             (fn [acc item]
               (let [head (first acc)]
                 (if (list? head)
                   (if (= (second head) item)
                     (conj (rest acc) (list (inc (first head)) item))
                     (conj acc item))
                   (if (= head item)
                     (conj (rest acc) (list 2 item))
                     (conj acc item)))))


             '()
             l)))
