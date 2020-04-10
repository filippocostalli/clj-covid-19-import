(ns clj-covid-19-import.services.csv-service
  (:require
    [ultra-csv.core]
    [clj-covid-19-import.utils :as myutils]
    [clj-covid-19-import.conf :as conf]
    [clojure.string :as str]))

(def input-data-regioni-csv
  (ultra-csv.core/read-csv
    (:input-file-reg conf/configuration) {:header? false :skip 1}))

(def input-data-province-csv
  (ultra-csv.core/read-csv
    (:input-file-pro conf/configuration) {:header? false :skip 1}))


(defn checknil [v]
  (if v v 0))

(defn parse-data-regione [v]
    ;;(println v)
    {:data (myutils/string->date (str (nth v 0)))
     :stato (nth v 1)
     :codice_regione (nth v 2)
     :denominazione_regione (str/upper-case (nth v 3))
     :lat (nth v 4)
     :long (nth v 5)
     :ricoverati_con_sintomi (nth v 6)
     :terapia_intensiva (checknil (nth v 7))
     :totale_ospedalizzati (nth v 8)
     :isolamento_domiciliare (nth v 9)
     :totale_attualmente_positivi (nth v 10)
     :nuovi_attualmente_positivi (nth v 11)
     :dimessi_guariti (nth v 13)
     :deceduti (nth v 14)
     :totale_casi (nth v 15)
     :tamponi (nth v 16)})

(defn parse-data-provincia [v]
      {:data (myutils/string->date (str (nth v 0)))
       :stato (nth v 1)
       :codice-regione (nth v 2)
       :denominazione-regione (str/upper-case (nth v 3))
       :codice-provincia (nth v 4)
       :denominazione-provincia (str/upper-case (nth v 5))
       :sigla-provincia (nth v 6)
       :lat (nth v 7)
       :long (nth v 8)
       :totale_casi (nth v 9)})

(defn parse-alldata-regione
  []
  (map parse-data-regione input-data-regioni-csv))


(defn parse-alldata-provincia
  []
  (map parse-data-provincia input-data-province-csv))
