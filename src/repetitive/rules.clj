(ns repetitive.rules
  (:require [repetitive.time :as t]))

(defn- to-set [values]
  "Converts a single value or a vector of values into a set"
  (if (vector? values)
    (set (distinct values))
    (set (vector values))))

(defn- check-day
  [cal {:keys [days]}]
  (contains? (to-set days) (t/day cal)))

;;TODO: Validate the input AND also consider week days as numbers numbers
(defn- check-day-of-week
  [cal {days-of-week :days-of-week}]
  (contains? (to-set days-of-week) (t/day-of-week cal)))

(defn- compose-predicate-fn
  "Creates a predicate function that returns true if any of the
   given functions eval to true against a given calendar date and a rule.
   Otherwise nil is returned"
  [rule & fns]
  (fn [cal]
    (some #(= (apply % [cal rule]) true) fns)))

(defprotocol Rule
  (predicate [rule] "Rule predicate"))

(defrecord MonthlyRule [days days-of-week])
(defrecord DailyRule [])

(extend-type MonthlyRule
  Rule
  (predicate [rule]
    (compose-predicate-fn rule
      check-day
      check-day-of-week)))

(extend-type DailyRule
  Rule
  (predicate [_]
    (fn [_] true)))

(defn monthly-rule
  "Defines a monthly ocurrence rule"
  [& {:keys [days days-of-week]}]
  (if (and (nil? days) (nil? days-of-week))
    (MonthlyRule. 1 days-of-week)
    (MonthlyRule. days days-of-week)))

(defn daily-rule
  "Defines a daily rule ocurrence"
  []
  (DailyRule.))
