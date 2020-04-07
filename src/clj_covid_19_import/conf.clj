(ns clj-covid-19-import.conf
  (:require
    [cprop.core :refer [load-config]]
    [cprop.source :as source]))

(def configuration (load-config))
