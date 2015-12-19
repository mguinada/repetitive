(ns repetitive.core
  (:use [repetitive.rules])
  (:require [repetitive.time :as time]
            [repetitive.rules :refer [Rule]])
  (:import [repetitive.rules MonthlyRule DailyRule]))

(defn schedule
  [rule]
  "Defines a schedule"
  nil)

(defn rule-occurrences
  "Determins a given rule ocurrences.
   Can take as an optional arguement a :start-date to consider as the start data.
   If a :start-date is not given, the current data will be considered"
  [rule & {:keys [start-date] :or {start-date (time/now)}}]
  (filter #(apply (predicate rule) [%]) (time/daily-stream start-date)))
