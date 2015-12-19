(ns repetitive.rule-test
  (:require [clojure.test :refer :all]
            [repetitive.time :as t]
            [repetitive.rules :as r]
            [repetitive.core :as core :refer [rule-occurrences]]))

(def t (t/date-time 2013 1 1))

(deftest daily-tule-test
  (is (= (take 3 (rule-occurrences (r/daily-rule) :start-date t))
         [(t/date-time 2013 1 1) (t/date-time 2013 1 2) (t/date-time 2013 1 3)])))

(deftest monthly-rule-test
  (testing "recurrs on a daily basis"
    (is (= (take 3 (rule-occurrences (r/monthly-rule :days 15) :start-date t))
           [(t/date-time 2013 1 15) (t/date-time 2013 2 15) (t/date-time 2013 3 15)])))

  (testing "recurs per day of week"
    (is (= (take 4 (rule-occurrences (r/monthly-rule :days-of-week [:monday :friday]) :start-date t))
           [(t/date-time 2013 1 4) (t/date-time 2013 1 7) (t/date-time 2013 1 11) (t/date-time 2013 1 14)])))

  (testing "supports multiple days"
    (is (= (take 4 (rule-occurrences (r/monthly-rule :days [10 20]) :start-date t))
           [(t/date-time 2013 1 10) (t/date-time 2013 1 20) (t/date-time 2013 2 10) (t/date-time 2013 2 20)])))

  (testing "defaults to 1st day of month"
    (is (= (take 3 (rule-occurrences (r/monthly-rule) :start-date t))
           [(t/date-time 2013 1 1) (t/date-time 2013 2 1) (t/date-time 2013 3 1)]))))
