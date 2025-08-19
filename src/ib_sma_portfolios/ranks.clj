(ns ib-sma-portfolios.ranks
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn get-data-dir []
  "Get the data directory from system property or default path"
  (or (System/getProperty "ib.sma.portfolios.data.dir") 
      "/home/unveiled/work/P123/script/ib_data"))

(defn parse-ranks-data
  "Reads a ranks TSV file and returns an array of maps with the data."
  [file-path]
  (with-open [reader (io/reader file-path)]
    (let [lines (line-seq reader)
          headers ["SYMBOL" "RANK" "CRITERIA"]]
      (->> lines
           (filter #(not (str/blank? %)))
           (map #(str/split % #"	"))
           (map (fn [values]
                  (zipmap (map keyword headers) values)))
           (into [])))))

(defn load
  "Load all plain_ranks*.tsv files from the specified directory and return a map 
   of filename (without extension) to parsed data."
  [data-dir]
  (let [ranks-files (->> (io/file data-dir)
                         (.listFiles)
                         (filter #(and (.isFile %) 
                                     (re-find #"plain_ranks.*\.tsv$" (.getName %)))))]
    (reduce (fn [acc file]
              (let [filename (.getName file)
                    file-key (keyword (subs filename 0 (.lastIndexOf filename ".")))
                    file-path (.getAbsolutePath file)]
                (assoc acc file-key (parse-ranks-data file-path))))
            {}
            ranks-files)))

(defn load-all-ranks-files []
  "Load all plain_ranks*.tsv files and return a map of filename (without extension) to parsed data."
  (let [data-dir (get-data-dir)
        ranks-files (->> (io/file data-dir)
                         (.listFiles)
                         (filter #(and (.isFile %) 
                                     (re-find #"plain_ranks.*\.tsv$" (.getName %)))))]
    (reduce (fn [acc file]
              (let [filename (.getName file)
                    file-key (keyword (subs filename 0 (.lastIndexOf filename ".")))
                    file-path (.getAbsolutePath file)]
                (assoc acc file-key (parse-ranks-data file-path))))
            {}
            ranks-files)))

(def ranks-data
  "A map of filename (as keyword) to parsed ranks data."
  (load-all-ranks-files))

(comment
  ;; Example usage:
  ;; Get all ranks data
  ranks-data
  
  ;; Get specific ranks data
  (:plain_ranks_longs ranks-data)
  
  ;; Find records with specific symbol in longs
  (filter #(= "AABVF" (:SYMBOL %)) (:plain_ranks_longs ranks-data))
  
  ;; Get all file keys
  (keys ranks-data))