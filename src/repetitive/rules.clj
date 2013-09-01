(ns repetitive.rules
  (:use repetitive.calendar))

(defn monthly-rule
  "Defines a monthly ocurrence rule"
  [& {:keys [day] :or {day 1} :as args}]
  {:type :monthly :day day})

(defn rule-occurrences
  "Determins a given rule ocurrences.
  Can take as an optional arguement a :start-date to consider as the start data.
  If a :start-date is not given, the current data will be considered"
  [rule & {:keys [start-date] :or [start-date (calendar)]}]
  (filter #(= (day %) (:day rule)) (daily-stream start-date)))