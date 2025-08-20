(ns prices
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [utils]))

(defn process-prices-data
  "Process prices data to remove ':USA' suffix from tickers"
  [prices-data]
  (map (fn [record]
         (if-let [symbol (:SYMBOL record)]
           (assoc record :SYMBOL (clojure.string/replace symbol #":USA$" ""))
           record))
       prices-data))

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

(defn load-data
  "Load prices data from the specified directory with optional filename.
   Defaults to prices.tsv if no filename is provided.
   Returns a map with :data, :hours-since-modified, and :size."
  ([data-dir]
   (load-data data-dir "prices.tsv"))
  ([data-dir filename]
   (let [file-path (str data-dir "/" filename)
         raw-data (parse-prices-data file-path)
         processed-data (process-prices-data raw-data)
         file-info (utils/get-file-info file-path)]
     {:data processed-data
      :hours-since-modified (:hours-since-modified file-info)
      :size (:size file-info)})))