(ns repl-tools
  (:use repetitive.calendar)
  (:use repetitive.rules)
  (:use repetitive.core)
  (:require [clojure.tools.namespace.repl :refer [refresh refresh-all set-refresh-dirs] :as repl]))

(repl/set-refresh-dirs "src/")

(defn init []
  (prn "Init..."))

(defn reload []
  "Reloads code that has been changed"
  (repl/refresh :after 'repl-tools/init))

(defn reload-all []
  "Reloads all the code base"
  (repl/refresh-all :after 'repl-tools/init))

