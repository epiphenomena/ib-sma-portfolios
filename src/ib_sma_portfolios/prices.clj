(ns ib-sma-portfolios.prices
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn parse-prices-data
  "Reads the prices.tsv file and returns an array of maps with the data."
  [file-path]
  (with-open [reader (io/reader file-path)]
    (let [lines (line-seq reader)
          headers ["SYMBOL" "PRICE" "DATE" "SECTOR"]]
      (->> lines
           (filter #(not (str/blank? %)))
           (map #(str/split % #"\t"))
           (map (fn [values]
                  (zipmap (map keyword headers) values)))
           (into [])))))

(def prices-data
  "The prices data loaded from the TSV file as an array of maps with keyword keys."
  (parse-prices-data "/home/unveiled/work/P123/script/ib_data/prices.tsv"))

(comment
  ;; Example usage:
  ;; Get the first 5 records
  (take 5 prices-data)
  
  ;; Find records with specific symbol
  (filter #(= "A:USA" (:SYMBOL %)) prices-data)
  
  ;; Count total records
  (count prices-data))