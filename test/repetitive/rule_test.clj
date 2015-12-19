(ns repetitive.rule-test
  (:require [clojure.test :refer [deftest]]
            [midje.sweet :refer :all]
            [clj-time.core :as t]
            [repetitive.rules :refer :all]
            [repetitive.core :refer [rule-occurrences]]))

(def t (t/date-time 2013 1 1))

(deftest daily-tule-test
  (fact "recurrs on a daily basis"
    (take 3 (rule-occurrences (daily-rule) :start-date t)) => [(t/date-time 2013 1 1)
                                                               (t/date-time 2013 1 2)
                                                               (t/date-time 2013 1 3)]))

(deftest monthly-rule-test
  (fact "recurs on a monthly basis"
    (take 3 (rule-occurrences (monthly-rule :days 15) :start-date t)) => [(t/date-time 2013 1 15)
                                                                          (t/date-time 2013 2 15)
                                                                          (t/date-time 2013 3 15)])

  (fact "recurs per day of week"
    (take 4 (rule-occurrences (monthly-rule :days-of-week [:monday :friday]) :start-date t)) => [(t/date-time 2013 1 4)
                                                                                                 (t/date-time 2013 1 7)
                                                                                                 (t/date-time 2013 1 11)
                                                                                                 (t/date-time 2013 1 14)])

  (fact "supports multiple days"
    (take 4 (rule-occurrences (monthly-rule :days [10 20]) :start-date t)) => [(t/date-time 2013 1 10)
                                                                               (t/date-time 2013 1 20)
                                                                               (t/date-time 2013 2 10)
                                                                               (t/date-time 2013 2 20)])

  (fact "defaults to 1st day of month"
    (take 3 (rule-occurrences (monthly-rule) :start-date t)) =>[(t/date-time 2013 1 1)
                                                                (t/date-time 2013 2 1)
                                                                (t/date-time 2013 3 1)]))
