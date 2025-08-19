(ns ib-sma-portfolios.shortinfo-example
  (:require [ib-sma-portfolios.shortinfo :as shortinfo]))

;; Example usage of the shortinfo data

;; Get the total number of records
(println "Total shortinfo records:" (count shortinfo/shortinfo-data))

;; Get the first few records
(println "First 3 records:")
(run! println (take 3 shortinfo/shortinfo-data))

;; Get symbols of the first few records
(println "Symbols of first 5 records:" (map :SYM (take 5 shortinfo/shortinfo-data)))

;; Find a specific symbol
(let [symbol "A"]
  (println (str "Record for symbol '" symbol "':") 
           (first (filter #(= symbol (:SYM %)) shortinfo/shortinfo-data))))

;; Find records with high available quantities
;; Note: AVAILABLE is a string, so we need to parse it for comparison
;; Handle special case where it might be ">10000000"
(println "Records with available > 1000000:")
(->> shortinfo/shortinfo-data
     (filter #(let [avail (:AVAILABLE %)]
                (if (string? avail)
                  (if (re-find #"^>" avail)
                    ;; Handle ">10000000" format
                    (let [num-str (subs avail 1)]
                      (try 
                        (> (Long/parseLong num-str) 1000000)
                        (catch Exception _ false)))
                    ;; Handle regular number format
                    (try 
                      (> (Long/parseLong avail) 1000000)
                      (catch Exception _ false)))
                  false)))
     (take 3)
     (run! println))

;; Get all unique currencies
(println "Unique currencies:" (distinct (map :CUR shortinfo/shortinfo-data)))