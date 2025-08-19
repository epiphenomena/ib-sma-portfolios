(ns ib_sma_portfolios.prices
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn get-data-dir []
  "Get the data directory from system property or default path"
  (or (System/getProperty "ib.sma.portfolios.data.dir") 
      "/home/unveiled/work/P123/script/ib_data"))

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

(def prices-file-path
  "Path to the prices.tsv file"
  (str (get-data-dir) "/" prices-filename-default))

(def prices-data
  "The prices data loaded from the TSV file as an array of maps with keyword keys."
  (parse-prices-data prices-file-path))