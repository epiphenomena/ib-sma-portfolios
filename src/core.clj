(ns core
  (:require [clojure.data.json :as json]
            [clj-http.client :as http]
            [cheshire.core :as cheshire]
            [prices]
            [ranks]
            [shortinfo]
            [settings])
  (:import [java.io File]
           [java.util Date]))

(defn combine-ticker-data
  "Combine price, rank, and shortinfo data by symbol.
  Returns a map where keys are symbols and values are maps containing
  all available data for that symbol from the three sources."
  [data-dir]
  (let [prices-data (:data (prices/load-data data-dir))
        shortinfo-data (:data (shortinfo/load-data data-dir))
        ranks-data (ranks/load-data data-dir)
        ;; Convert data to maps keyed by symbol for easier lookup
        prices-by-symbol (reduce (fn [acc record]
                                   (if-let [symbol (:SYMBOL record)]
                                     (assoc acc symbol record)
                                     acc))
                                 {}
                                 prices-data)
        shortinfo-by-symbol (reduce (fn [acc record]
                                      (if-let [symbol (:SYMBOL record)]
                                        (assoc acc symbol record)
                                        acc))
                                    {}
                                    shortinfo-data)
        ;; Ranks data is a map of filename to data, so we need to flatten it
        ranks-by-symbol (reduce (fn [acc [filename file-data]]
                                  (reduce (fn [inner-acc record]
                                            (if-let [symbol (:SYMBOL record)]
                                              (assoc inner-acc symbol (merge record {:RANKS-FILE (name filename)}))
                                              inner-acc))
                                          acc
                                          (:data file-data)))
                                {}
                                ranks-data)]
    ;; Combine all data by symbol
    (reduce (fn [acc symbol]
              (assoc acc symbol
                     (merge
                      (get prices-by-symbol symbol {})
                      (get shortinfo-by-symbol symbol {})
                      (get ranks-by-symbol symbol {}))))
            {}
            (set (concat (keys prices-by-symbol)
                         (keys shortinfo-by-symbol)
                         (keys ranks-by-symbol))))))

(defn -main [& args]
  (let [data-dir (if (map? (first args)) (:dir (first args)) (first args))]
    (println "IB SMA Portfolios running with data directory:" data-dir)

    ;; Load prices data
    (let [prices-result (prices/load-data data-dir)]
      (println "Prices data loaded:" (count (:data prices-result)) "records")
      (println "Prices file last modified:" (:hours-since-modified prices-result) "hours ago")
      (println "Prices file size:" (:size prices-result) "bytes"))

    ;; Load ranks data
    (let [ranks-result (ranks/load-data data-dir)]
      (println "Ranks data loaded:" (count ranks-result) "files"))

    ;; Load shortinfo data
    (let [shortinfo-result (shortinfo/load-data data-dir)]
      (println "Shortinfo data loaded:" (count (:data shortinfo-result)) "records")
      (println "Shortinfo file last modified:" (:hours-since-modified shortinfo-result) "hours ago")
      (println "Shortinfo file size:" (:size shortinfo-result) "bytes"))

    ;; Combine all ticker data
    (let [combined-data (combine-ticker-data data-dir)]
      (println "Combined data for" (count combined-data) "unique symbols"))

    ;; Load settings data
    (let [account-settings (settings/load-account-settings data-dir)
          allocator-settings (settings/load-allocator-settings data-dir)
          portfolio-settings (settings/load-portfolio-settings data-dir)]
      (println "Settings loaded:"
               (count account-settings) "accounts,"
               (count portfolio-settings) "portfolios"))))