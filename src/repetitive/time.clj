(ns repetitive.time
  (:require [clj-time.core :as t]
            [clj-time.local :as l]
            [clj-time.periodic :as p]))

(def date-time t/date-time)

(def now l/local-now)

(def day t/day)

(def n-day-of-week t/day-of-week)

(defn day-of-week
  [^org.joda.time.DateTime dt]
  (let [days-of-the-week [:monday :tuesday :wednesday :thursday :friday :saturday :sunday]]
    (get days-of-the-week (dec (n-day-of-week dt)))))

(defn daily-stream
  [^org.joda.time.DateTime start]
  (p/periodic-seq start (t/days 1)))

(defn monthly-stream
  [^org.joda.time.DateTime start]
  (p/periodic-seq start (t/months 1)))
