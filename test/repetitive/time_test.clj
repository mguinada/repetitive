(ns repetitive.time-test
  (:require [clojure.test :refer :all]
            [repetitive.time :as t]
            [clj-time.core :refer [date-time hours months]]))

(deftest day-test
  (is (= 31 (t/day (date-time 2016 1 31)))))

(deftest day-of-week-test
  (is (= :sunday (t/day-of-week (date-time 2016 1 3))))
  (is (= :monday (t/day-of-week (date-time 2016 1 4))))
  (is (= :tuesday (t/day-of-week (date-time 2016 1 5))))
  (is (= :wednesday (t/day-of-week (date-time 2016 1 6))))
  (is (= :thursday (t/day-of-week (date-time 2016 1 7))))
  (is (= :friday (t/day-of-week (date-time 2016 1 8))))
  (is (= :saturday (t/day-of-week (date-time 2016 1 9)))))

(deftest dialy-stream-test
  (is (= (take 3 (t/daily-stream (date-time 2016 1 1)))
         [(date-time 2016 1 1) (date-time 2016 1 2) (date-time 2016 1 3)])))

(deftest monthly-stream-test
  (is (= (take 3 (t/monthly-stream (date-time 2016 1 31)))
         [(date-time 2016 1 31) (date-time 2016 2 29) (date-time 2016 3 31)])))
