(ns ib-sma-portfolios.ranks-example
  (:require [ib-sma-portfolios.ranks :as ranks]))

;; Example usage of the ranks data

;; Get all ranks data
(println "Ranks data keys:" (keys ranks/ranks-data))

;; Get count of records in each file
(doseq [[file-key data] ranks/ranks-data]
  (println (str "File " file-key " has " (count data) " records")))

;; Get the first few records from longs
(println "First 3 longs records:")
(run! println (take 3 (:plain_ranks_longs ranks/ranks-data)))

;; Get symbols of the first few longs records
(println "Symbols of first 5 longs records:" (map :SYMBOL (take 5 (:plain_ranks_longs ranks/ranks-data))))

;; Find a specific symbol in longs
(let [symbol "AABVF"
      record (first (filter #(= symbol (:SYMBOL %)) (:plain_ranks_longs ranks/ranks-data)))]
  (println (str "Record for symbol '" symbol "' in longs:") record))

;; Find records with high rank in longs
(println "Longs records with rank > 0:")
(->> (:plain_ranks_longs ranks/ranks-data)
     (filter #(> (Double/parseDouble (:RANK %)) 0.0))
     (take 3)
     (run! println))

;; Compare the same symbol in longs vs shorts
(let [symbol "AABVF"
      longs-record (first (filter #(= symbol (:SYMBOL %)) (:plain_ranks_longs ranks/ranks-data)))
      shorts-record (first (filter #(= symbol (:SYMBOL %)) (:plain_ranks_shorts ranks/ranks-data)))]
  (println (str "Symbol " symbol " in longs:") longs-record)
  (println (str "Symbol " symbol " in shorts:") shorts-record))