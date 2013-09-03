(ns repetitive.rules
  (:require [repetitive.calendar :as c]))

(defn- to-set [values]
  "Converts a single value or a vector of values into a set"
  (if (vector? values)
    (set (distinct values))
    (set (vector values))))

(defprotocol Rule
  (predicate [rule] "Rule predicate"))

(defrecord MonthlyRule [days])
(defrecord DailyRule [])

(extend-type MonthlyRule
  Rule
  (predicate [{days :days}]
    (fn [cal]
      (contains? (to-set days) (c/day cal)))))

(extend-type DailyRule
  Rule
  (predicate [_]
    (fn [_] true)))

(defn monthly-rule
  "Defines a monthly ocurrence rule"
  [& {:keys [days] :or {days 1} :as args}]
  (MonthlyRule. days))

(defn daily-rule
  "Defines a daily rule ocurrence"
  []
  (DailyRule.))

