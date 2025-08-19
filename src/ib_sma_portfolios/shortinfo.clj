(ns ib-sma-portfolios.shortinfo
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn parse-shortinfo-data
  "Reads the shorting.tsv file and returns an array of maps with the data."
  [file-path]
  (with-open [reader (io/reader file-path)]
    (let [lines (line-seq reader)
          ;; Find the header line (starts with #SYM)
          header-line (first (filter #(str/starts-with? % "#SYM") lines))
          ;; Remove the # from the header
          clean-header (subs header-line 1)
          headers (str/split clean-header #"\t")
          ;; Get the actual data lines (skip comment lines)
          data-lines (filter #(and (not (str/starts-with? % "#"))
                                   (not (str/blank? %))) lines)]
      (->> data-lines
           (map #(str/split % #"\t"))
           (map (fn [values]
                  (zipmap (map keyword headers) values)))  ; Convert headers to keywords
           (into [])))))

(def shortinfo-data
  "The shortinfo data loaded from the TSV file as an array of maps with keyword keys."
  (parse-shortinfo-data "/home/unveiled/work/P123/script/ib_data/shorting.tsv"))

(comment
  ;; Example usage:
  ;; Get the first 5 records
  (take 5 shortinfo-data)
  
  ;; Find records with specific symbol
  (filter #(= "A" (:SYM %)) shortinfo-data)
  
  ;; Count total records
  (count shortinfo-data))