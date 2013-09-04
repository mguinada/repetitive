(ns repetitive.rule-test
  (:use clojure.test
        midje.sweet
        repetitive.rules
        repetitive.core
        repetitive.calendar))

(def t (set-calendar 2013 1 1))

(deftest daily-tule-test
  (fact "recurrs on a daily basis"
    (take 3 (rule-occurrences (daily-rule) :start-date t)) => [(set-calendar 2013 1 1)
                                                               (set-calendar 2013 1 2)
                                                               (set-calendar 2013 1 3)]))

(deftest monthly-rule-test
  (fact "recurs on a monthly basis"
    (take 3 (rule-occurrences (monthly-rule :days 15) :start-date t)) => [(set-calendar 2013 1 15)
                                                                          (set-calendar 2013 2 15)
                                                                          (set-calendar 2013 3 15)])

  (fact "recurs per day of week"
    (take 4 (rule-occurrences (monthly-rule :days-of-week [:monday :friday]) :start-date t)) => [(set-calendar 2013 1 4)
                                                                                                 (set-calendar 2013 1 7)
                                                                                                 (set-calendar 2013 1 11)
                                                                                                 (set-calendar 2013 1 14)])

  (fact "supports multiple days"
    (take 4 (rule-occurrences (monthly-rule :days [10 20]) :start-date t)) => [(set-calendar 2013 1 10)
                                                                               (set-calendar 2013 1 20)
                                                                               (set-calendar 2013 2 10)
                                                                               (set-calendar 2013 2 20)])

  (fact "defaults to 1st day of month"
    (take 3 (rule-occurrences (monthly-rule) :start-date t)) =>[(set-calendar 2013 1 1)
                                                                (set-calendar 2013 2 1)
                                                                (set-calendar 2013 3 1)]))