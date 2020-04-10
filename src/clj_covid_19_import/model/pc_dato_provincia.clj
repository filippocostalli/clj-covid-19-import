(ns clj-covid-19-import.model.pc-dato-provincia
  (:require
    [next.jdbc.date-time]
    [honeysql.helpers :as hh]
    [clj-covid-19-import.services.database :as db]))

(defn delete!
  [datasource]
  (let [query (-> (hh/delete-from :protciv_dati_provincia))]
    (db/query! datasource query)))


(defn insert!
  [datasource map-dato-provincia]
  (let [{:keys [data stato codice-regione denominazione-regione
                codice-provincia denominazione-provincia sigla-provincia
                lat long totale_casi]}
        map-dato-provincia
        q (-> (hh/insert-into :protciv_dati_provincia)
              (hh/columns :data
                          :stato
                          :codice_regione
                          :denominazione_regione
                          :codice_provincia
                          :denominazione_provincia
                          :sigla_provincia
                          :lat
                          :long
                          :totale_casi)
              (hh/values [[data
                           stato
                           codice-regione
                           denominazione-regione
                           codice-provincia
                           denominazione-provincia
                           sigla-provincia
                           lat
                           long
                           totale_casi]]))]
      (db/execute! datasource q)))
