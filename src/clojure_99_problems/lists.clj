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

(defn dupli
  [l]
  (reduce #(-> %1 (conj %2) (conj %2))
          '()
          (reverse l)))

(defn repli
  [l n]
  (reduce #(reduce conj %1 (repeat n %2))
          '()
          (reverse l)))

(defn drop-every
  [l n]
  (reverse (loop [xs l
                  i 1
                  acc '()]
             (if (seq xs)
               (recur
                (rest xs)
                (inc i)
                (if (zero? (rem i n))
                  acc
                  (conj acc (first xs))))
               acc))))

(defn split
  [l n]
  (loop [xs l
         i 1
         acc1 '()
         acc2 '()]
    (if (seq xs)
      (if (> i n)
        (recur (rest xs) (inc i) acc1 (conj acc2 (first xs)))
        (recur (rest xs) (inc i) (conj acc1 (first xs)) acc2))
      (list (reverse acc1) (reverse acc2)))))

(defn slice
  [l start end]
  (reverse (loop [[head & tail] l
                  i 1
                  acc '()]
             (if (some? head)
               (if (and (>= i start) (<= i end))
                 (recur tail (inc i) (conj acc head))
                 (recur tail (inc i) acc))
               acc))))

(defn rotate
  [l n]
  (let [length (count l)
        offset (rem (+ n length) length)]
    (concat (slice l (inc offset) length) (slice l 1 offset))))

(defn remove-at
  [l n]
  (loop [[head & tail] l
         i 1
         acc '()]
    (if (some? head)
      (if (= i n)
        (concat (reverse acc) tail)
        (recur tail (inc i) (conj acc head)))
      (reverse acc))))

(defn insert-at
  [el l n]
  (loop [[head & tail] l
         i 1
         acc '()]
    (if (some? head)
      (if (= i n)
        (concat (reverse acc) (list el head) tail)
        (recur tail (inc i) (conj acc head)))
      (reverse acc))))
