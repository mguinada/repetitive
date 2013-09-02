(ns repetitive.rules
  (:require [repetitive.calendar :as c]))

(defn- to-set [values]
  "Converts a single value or a vector of values into a set"
  (if (vector? values)
    (set (distinct values))
    (set (vector values))))

(defn build-rule
  [type days]
  {:type :monthly
   :days days
   :predicate (fn [cal]
                (contains? (to-set days) (c/day cal)))})

(defn monthly-rule
  "Defines a monthly ocurrence rule"
  [& {:keys [days] :or {days 1} :as args}]
  (build-rule :monthly days))

