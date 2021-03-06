
(ns clj-covid-19-import.core
  (:require
    [next.jdbc.connection :as connection]
    [cambium.core :as log]
    [clj-covid-19-import.conf :as conf]
    [clj-covid-19-import.services.csv-service :as csvservice]
    [clj-covid-19-import.model.pc-dato-regione :as pc-dato-regione-dao]
    [clj-covid-19-import.model.pc-dato-provincia :as pc-dato-provincia-dao])
 (:import
  (com.zaxxer.hikari HikariDataSource)))

(def no-provincia "IN FASE DI DEFINIZIONE/AGGIORNAMENTO")

(defn clean!
    [datasource]
    (do
      (pc-dato-regione-dao/delete! datasource)
      (pc-dato-provincia-dao/delete! datasource)))

(defn -main
  [& args]
  (let [dbspec-covid19 (:covid19-db conf/configuration)
        ^HikariDataSource ds-covid19 (connection/->pool HikariDataSource dbspec-covid19)
        regione-data (csvservice/parse-alldata-regione)
        provincia-data (filter #(not= (:denominazione-provincia %) no-provincia) (csvservice/parse-alldata-provincia))
        insert-regione (future
                         (doseq [rec regione-data] (pc-dato-regione-dao/insert! ds-covid19 rec))
                         (count regione-data))
        insert-provincia (future
                           (doseq [rec provincia-data] (pc-dato-provincia-dao/insert! ds-covid19 rec))
                           (count provincia-data))]
    (clean! ds-covid19)
    (log/info (str "Region data inserted: " (deref insert-regione)))
    (log/info (str "Province data inserted: " (deref insert-provincia)))))
