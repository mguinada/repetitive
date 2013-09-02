(ns repetitive.calendar-test
  (:import [java.util Calendar TimeZone])
  (:use clojure.test
        midje.sweet
        repetitive.calendar))

(def cal-instance (Calendar/getInstance))
(def test-calendar cal-instance)

(deftest date-utils-test
  (fact "plays nice with java.util.Calendar"
    (day test-calendar) => (.get cal-instance Calendar/DAY_OF_MONTH)
    (month test-calendar) => (inc (.get cal-instance Calendar/MONTH))
    (year test-calendar) => (.get cal-instance Calendar/YEAR))

  (fact "allows to set a date"
    (day (set-calendar 2013 2 1)) => 1
    (month (set-calendar 2013 2 1)) => 2
    (year (set-calendar 2013 2 1)) => 2013
    (hour (set-calendar 2013 1 1 23 59 0)) => 23
    (minute (set-calendar 2013 1 1 23 59 0)) => 59
    (seconds (set-calendar 2013 1 1 23 00 59 :time-zone "GMT")) => 59))

  (fact "allows to set time"
    (hours (set-time (calendar) 23 59 30)) => 23
    (minutes (set-time (calendar) 23 59 30)) => 59
    (seconds (set-time (calendar) 23 59 30)) => 30)

  (fact "works with time zones"
    (time-zone (calendar :time-zone "EST")) => "EST"
    (fact "defaults to UTC"
      (time-zone (calendar)) => "UTC"))

  (fact "suports arithmetics"
    (fact "increments"
      (day (inc-day (set-calendar  2013 12 31))) => 1
      (month (inc-month (set-calendar 2013 12 1))) => 1
      (year (inc-year (set-calendar 2013 1 1))) => 2014))

    (fact "decrements"
      (day (dec-day (set-calendar 2013 1 1))) => 31
      (month (dec-month (set-calendar 2013 1 1))) => 12
      (year (dec-year (set-calendar 2013 1 1))) => 2012)

    (fact "adds"
      (day (add-days (set-calendar 2013 12 30) 2)) => 1
      (month (add-months (set-calendar 2013 11 1) 2)) => 1
      (year (add-years (set-calendar 2013 12 30) 2)) => 2015)

    (fact "subtracts"
      (day (sub-days (set-calendar 2013 1 2) 2)) => 31
      (month (sub-months (set-calendar 2013 2 1) 2)) => 12
      (year (sub-years (set-calendar 2015 12 30) 2)) => 2013)

    (fact "produces lazy sequences of days"
      (take 3 (daily-stream (set-calendar 2013 1 1))) => [(set-calendar 2013 1 1)
                                                          (set-calendar 2013 1 2)
                                                          (set-calendar 2013 1 3)])

    (fact "produces lazy sequences of months"
      (take 3 (monthly-stream (set-calendar 2013 1 1))) => [(set-calendar 2013 1 1)
                                                            (set-calendar 2013 2 1)
                                                            (set-calendar 2013 3 1)])

