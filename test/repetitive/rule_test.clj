(ns repetitive.rule-test
  (:use clojure.test
        midje.sweet
        repetitive.rules
        repetitive.calendar))

(def t (set-calendar 2013 1 1))

(deftest monthly-rule-test
  (fact "recurs on a monthly base"
    (take 2 (rule-occurrences (monthly-rule :day 15) :start-date t)) => [(set-calendar 2013 1 15)
                                                                         (set-calendar 2013 2 15)]))
