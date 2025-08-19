(ns ib-sma-portfolios.ranks
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [ib-sma-portfolios.utils :as utils]))

(defn parse-ranks-data
  "Reads a ranks TSV file and returns an array of maps with the data."
  [file-path]
  (with-open [reader (io/reader file-path)]
    (let [lines (line-seq reader)
          headers ["SYMBOL" "RANK" "CRITERIA"]]
      (->> lines
           (filter #(not (str/blank? %)))
           (map #(str/split % #"\t"))
           (map (fn [values]
                  (zipmap (map keyword headers) values)))
           (into [])))))

(defn load-data
  "Load all plain_ranks*.tsv files from the specified directory and return a map
   of filename (without extension) to parsed data with file info."
  [data-dir]
  (let [ranks-files (->> (io/file data-dir)
                         (.listFiles)
                         (filter #(and (.isFile %)
                                     (re-find #"plain_ranks.*\.tsv$" (.getName %)))))]
    (reduce (fn [acc file]
              (let [filename (.getName file)
                    file-key (keyword (subs filename 0 (.lastIndexOf filename ".")))
                    file-path (.getAbsolutePath file)
                    data (parse-ranks-data file-path)
                    file-info (utils/get-file-info file-path)]
                (assoc acc file-key
                       {:data data
                        :hours-since-modified (:hours-since-modified file-info)
                        :size (:size file-info)})))
            {}
            ranks-files)))