(ns clojure-99-problems.lists)

(defn last-item 
  "return last item of list"
  [l]
  (reduce (fn [_ item] item) nil l))
