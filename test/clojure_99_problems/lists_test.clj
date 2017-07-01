(ns clojure-99-problems.lists-test
  (:require [clojure.test :refer :all]
            [clojure-99-problems.lists :refer :all]))

(deftest last-item-test
  (testing "should return last item of the list"
    (is (= (last-item '(:a :b :c :d)) :d)))
  (testing "should return nil if list is empty"
    (is (= (last-item '()) nil))))
