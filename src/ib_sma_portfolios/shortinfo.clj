(ns ib-sma-portfolios.shortinfo
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:import [java.io File]))

(defn get-file-info
  "Get file modification time and size information"
  [file-path]
  (let [file (File. file-path)]
    {:last-modified (.lastModified file)
     :size (.length file)
     :hours-since-modified (-> (.lastModified file)
                               (- (System/currentTimeMillis))
                               (/ 1000) ; seconds
                               (/ 3600) ; hours
                               (-) ; make positive
                               Math/abs
                               int)}))

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
   Defaults to shorting.tsv if no filename is provided.
   Returns a map with :data, :hours-since-modified, and :size."
  ([data-dir]
   (load-data data-dir "shorting.tsv"))
  ([data-dir filename]
   (let [file-path (str data-dir "/" filename)
         data (parse-shortinfo-data file-path)
         file-info (get-file-info file-path)]
     {:data data
      :hours-since-modified (:hours-since-modified file-info)
      :size (:size file-info)})))