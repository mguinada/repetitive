(defproject repetitive "0.1.0-SNAPSHOT"
  :description "Date recurrence library"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[midje "1.5.1"]
                                  [org.clojure/tools.namespace "0.2.4"]]
                   :plugins [[lein-midje "3.1.0"]]}}
  :repl-options {:init-ns repl-tools})
