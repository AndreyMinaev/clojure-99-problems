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

(deftest kth-test
  (testing "should return nil if k is out of range"
    (is (= (kth '(:a :b :c) 5) nil)))
  (testing "should return nil if k is zero or negative"
    (is (= (kth '(:a :b :c) 0) nil))
    (is (= (kth '(:a :b :c) -1) nil)))
  (testing "should return k'th element with one based indexing"
    (is (= (kth '(:a :b :c) 2) :b))))

(deftest length-test
  (testing "should return the number of elements of a list"
    (is (= (length '()) 0))
    (is (= (length '(:a :b)) 2))))

(deftest my-reverse-test
  (testing "should return reversed list"
    (is (= (my-reverse '()) '()))
    (is (= (my-reverse '(:a :b :c)) '(:c :b :a)))))

(deftest palindrome?-test
  (testing "should return true if list is a palindrome"
    (is (= (palindrome? '(:a :b :c :b :a)) true)))
  (testing "should return false if list is not a palindrome"
    (is (= (palindrome? '(:a :b :c :d :e)) false))))

(deftest my-flatten-test
  (testing "should return flat list"
    (is (= (my-flatten '(:a (:b () (:c :d) :e))) '(:a :b :c :d :e)))))

(deftest compress-test
  (testing "schould return list without consecutive duplicates"
    (is (= (compress '(:a :a :b :a :a :c :c)) '(:a :b :a :c)))))
