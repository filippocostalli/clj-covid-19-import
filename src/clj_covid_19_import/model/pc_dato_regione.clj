(ns clj-covid-19-import.model.pc-dato-regione
  (:require
    [next.jdbc.date-time]
    [honeysql.helpers :as hh]
    [clj-covid-19-import.services.database :as db]))

(defn select-dato-regione-id
  [datasource codice-regione data]
  (let [query (-> (hh/select :id)
                  (hh/from :protciv_dati_regione)
                  (hh/where [:= :data data] [:= :codice_regione codice-regione]))]
    (db/query! datasource query)))

(defn exists
  [datasource m]
  (let [{:keys [codice_regione data]} m]
    (if (select-dato-regione-id datasource codice_regione data)
      true
      false)))

(defn delete!
  [datasource]
  (let [query (-> (hh/delete-from :protciv_dati_regione))]
    (db/query! datasource query)))


(defn insert!
  [datasource map-dato-regione]
  (let [{:keys [data stato codice_regione denominazione_regione lat long
                ricoverati_con_sintomi terapia_intensiva totale_ospedalizzati
                isolamento_domiciliare totale_attualmente_positivi nuovi_attualmente_positivi
                dimessi_guariti deceduti totale_casi tamponi]}
        map-dato-regione
        q (-> (hh/insert-into :protciv_dati_regione)
              (hh/columns :data
                        :stato
                        :codice_regione
                        :denominazione_regione
                        :lat
                        :long
                        :ricoverati_con_sintomi
                        :terapia_intensiva
                        :totale_ospedalizzati
                        :isolamento_domiciliare
                        :totale_attualmente_positivi
                        :nuovi_attualmente_positivi
                        :dimessi_guariti
                        :deceduti
                        :totale_casi
                        :tamponi)
              (hh/values [[data
                           stato
                           codice_regione
                           denominazione_regione
                           lat
                           long
                           ricoverati_con_sintomi
                           terapia_intensiva
                           totale_ospedalizzati
                           isolamento_domiciliare
                           totale_attualmente_positivi
                           nuovi_attualmente_positivi
                           dimessi_guariti
                           deceduti
                           totale_casi
                           tamponi]]))]
      (db/execute! datasource q)))
