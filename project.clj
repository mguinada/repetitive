(defproject repetitive "0.1.0-SNAPSHOT"
  :description "Date recurrence library"
  :url "http://example.com/FIXME"
  :license {:name "TODO: Choose a license"
            :url "http://choosealicense.com/"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.4"]
                                  [midje "1.6.0"]
                                  [lein-midje "3.1.1"]]
                   :source-paths ["dev"]
                   :plugins [[lein-midje "3.1.1"]]}})
