(ns repetitive.core
  (:require [repetitive.calendar :as c]))

(defn schedule
  [rule]
  "Defines a schedule"
  nil)

(defn rule-occurrences
  "Determins a given rule ocurrences.
   Can take as an optional arguement a :start-date to consider as the start data.
   If a :start-date is not given, the current data will be considered"
  [rule & {:keys [start-date] :or [start-date (c/calendar)]}]
  (filter #(apply (:predicate rule) [%]) (c/daily-stream start-date)))


