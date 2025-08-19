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

(defn load-data
  "Load shortinfo data from the specified directory with optional filename.
   Defaults to shorting.tsv if no filename is provided."
  ([data-dir]
   (load-data data-dir "shorting.tsv"))
  ([data-dir filename]
   (let [file-path (str data-dir "/" filename)]
     (parse-shortinfo-data file-path))))