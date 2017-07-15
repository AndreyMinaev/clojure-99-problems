(ns clojure-99-problems.lists-test
  (:require [clojure.test :refer :all]
            [clojure-99-problems.lists :refer :all]))

(deftest last-item-test
  (testing "should return last item of the list"
    (is (= (last-item '(:a :b :c :d)) :d)))
  (testing "should return nil if list is empty"
    (is (= (last-item '()) nil))))

(deftest last-two-items-test
  (testing "should return empty list if initial list is empty"
    (is (= (last-two-items '()) '())))
  (testing "should return the equal list if initial list have one item"
    (is (= (last-two-items '(:a)) '(:a))))
  (testing "should return list of last two items of initial list"
    (is (= (last-two-items '(:a :b :c :d :e)) '(:d :e)))))
