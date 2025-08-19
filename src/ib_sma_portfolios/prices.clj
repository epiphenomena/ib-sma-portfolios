(ns ib-sma-portfolios.prices
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def prices-filename-default "prices.tsv")

(defn parse-prices-data
  "Reads the prices.tsv file and returns an array of maps with the data."
  [file-path]
  (with-open [reader (io/reader file-path)]
    (let [lines (line-seq reader)
          headers ["SYMBOL" "PRICE" "DATE" "SECTOR"]]
      (->> lines
           (filter #(not (str/blank? %)))
           (map #(str/split % #"	"))
           (map (fn [values]
                  (zipmap (map keyword headers) values)))
           (into [])))))

(defn load-data
  "Load prices data from the specified directory with optional filename.
   Defaults to prices.tsv if no filename is provided."
  ([data-dir]
   (load-data data-dir prices-filename-default))
  ([data-dir filename]
   (let [file-path (str data-dir "/" filename)]
     (parse-prices-data file-path))))