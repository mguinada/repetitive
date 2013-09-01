(ns repetitive.calendar
  (:import [java.util Calendar TimeZone]))

(defn calendar
  "Get an instance of java.util.Calendar"
  [& {:keys [time-zone] :or {time-zone "UTC"}}]
  (doto (Calendar/getInstance)
    (.setTimeZone (TimeZone/getTimeZone time-zone))))

(defn set-calendar
  "Sets calendar to a date"
  ([year month day hour minute secs & {:keys [time-zone] :or {time-zone "UTC"}}]
    (doto (calendar :time-zone time-zone)
       (.set java.util.Calendar/DAY_OF_MONTH day)
       (.set java.util.Calendar/MONTH (dec month))
       (.set java.util.Calendar/YEAR year)
       (.set java.util.Calendar/HOUR_OF_DAY hour)
       (.set java.util.Calendar/MINUTE minute)
       (.set java.util.Calendar/SECOND secs)
       (.set java.util.Calendar/MILLISECOND 0)))
  ([year month day hour minute]
    (set-calendar year month day hour minute 0))
  ([year month day hour]
    (set-calendar year month day hour 0 0))
  ([year month day]
    (set-calendar year month day 0 0 0)))

(defn set-time
  "sets hours, minutes and seconds"
  [cal hours minutes seconds]
  (doto (.clone cal)
    (.set java.util.Calendar/HOUR_OF_DAY hours)
    (.set java.util.Calendar/MINUTE minutes)
    (.set java.util.Calendar/SECOND seconds)))

(defn date
  "Gets the from the calendar"
  ([cal]
    (.getTime cal)))

(defn- read-unit [unit cal]
  (.get cal unit))

(def day
  "Gets the calendar day"
  (partial read-unit Calendar/DAY_OF_MONTH))

(defn month
  [cal]
  "Gets the calendar month"
  (inc (read-unit Calendar/MONTH cal)))

(def year
  "Gets the calendar year"
  (partial read-unit Calendar/YEAR))

(def hours
  "Gets the calendar hour"
  (partial read-unit Calendar/HOUR_OF_DAY))

(def hour hours)

(def minutes
  "Gets the calendar hour"
  (partial read-unit Calendar/MINUTE))

(def minute minutes)

(def seconds
  "Gets the calendar seconds"
  (partial read-unit Calendar/SECOND))

(def secs seconds)

(defn time-zone
  "Get the calendar timezone"
  [cal]
  (.getID (.getTimeZone cal)))

(defn now
  "Gets the current date."
  ([& {:keys [time-zone] :or {time-zone "UTC"}}]
   (date (calendar :time-zone  time-zone))))

(defn set-date
  "Sets a date"
  [year month day & {:keys [time-zone] :or {time-zone "UTC"}}]
  (.getTime (set-calendar year month day :time-zone time-zone)))

(defn- inc-unit [unit n cal]
  (doto (.clone cal)
    (.add unit n)))

(defn- add-unit [unit cal n]
  (inc-unit unit n cal))

(defn- sub-unit [unit cal n]
  (inc-unit unit (* -1 n) cal))

(def add-days
  "Adds n in calendar days"
  (partial add-unit Calendar/DAY_OF_MONTH))

(def add-months
  "Adds n in calendar months"
  (partial add-unit Calendar/MONTH))

(def add-years
  "Adds n in calendar years"
  (partial add-unit Calendar/YEAR))

(def sub-days
  "Subtracts n in calendar days"
  (partial sub-unit Calendar/DAY_OF_MONTH))

(def sub-months
  "Subtracts n in calendar months"
  (partial sub-unit Calendar/MONTH))

(def sub-years
  "Subtracts n in calendar years"
  (partial sub-unit Calendar/YEAR))

(def inc-day
  "Increment a day"
  (partial inc-unit Calendar/DAY_OF_MONTH 1))

(def inc-month
  "Increment a month"
  (partial inc-unit Calendar/MONTH 1))

(def inc-year
  "Increment a year"
  (partial inc-unit Calendar/YEAR 1))

(def dec-day
  "Decrement a day"
  (partial inc-unit Calendar/DAY_OF_MONTH -1))

(def dec-month
  "Decrement a month"
  (partial inc-unit Calendar/MONTH -1))

(def dec-year
  "Decrement a year"
  (partial inc-unit Calendar/YEAR -1))

(defn daily-stream
  "A lazy sequence of days in increments"
  [cal]
  (iterate inc-day cal))

(defn monthly-stream
  "A lazy sequence of months in increments"
  [cal]
  (iterate inc-month cal))