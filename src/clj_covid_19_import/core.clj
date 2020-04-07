(ns clj-covid-19-import.core
  (:require
    [next.jdbc.connection :as connection]
    [cambium.core :as log]
    [clj-covid-19-import.conf :as conf]
    [clj-covid-19-import.services.csv-service :as csvservice]
    [clj-covid-19-import.model.pc-dato-regione :as pc-dato-regione-dao])
 (:import
  (com.zaxxer.hikari HikariDataSource)))

(defn -main
  [& args]
  (let [dbspec-covid19 (:covid19-db conf/configuration)
        ^HikariDataSource ds-covid19 (connection/->pool HikariDataSource dbspec-covid19)
        regione-data (csvservice/parse-data)]
        ;;data-to-insert (filter #(pc-dato-regione-dao/exists ds-covid19 %) regione-data)]
    (log/info "Application started")
    (pc-dato-regione-dao/delete! ds-covid19)
    (log/info (str "Data to insert: " (count regione-data)))
    (doseq [rec regione-data] (pc-dato-regione-dao/insert! ds-covid19 rec))))
