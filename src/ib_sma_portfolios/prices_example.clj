(ns ib-sma-portfolios.prices-example
  (:require [ib-sma-portfolios.prices :as prices]))

;; Example usage of the prices data

;; Get the total number of records
(println "Total prices records:" (count prices/prices-data))

;; Get the first few records
(println "First 3 records:")
(run! println (take 3 prices/prices-data))

;; Get symbols of the first few records
(println "Symbols of first 5 records:" (map :SYMBOL (take 5 prices/prices-data)))

;; Find a specific symbol
(let [symbol "A:USA"]
  (println (str "Record for symbol '" symbol "':") 
           (first (filter #(= symbol (:SYMBOL %)) prices/prices-data))))

;; Find records with high prices
;; Note: PRICE is a string, so we need to parse it for comparison
(println "Records with price > 100:")
(->> prices/prices-data
     (filter #(try 
                (> (Double/parseDouble (:PRICE %)) 100.0)
                (catch Exception _ false)))
     (take 3)
     (run! println))

;; Get all unique sectors
(println "Unique sectors:" (distinct (map :SECTOR prices/prices-data)))

;; Get records for a specific sector
(println "First 3 healthcare stocks:")
(->> prices/prices-data
     (filter #(= "HEALTHCARE" (:SECTOR %)))
     (take 3)
     (run! println))