(ns ib-sma-portfolios.core
  (:require [clojure.data.json :as json]
            [clj-http.client :as http]
            [cheshire.core :as cheshire]
            [ib-sma-portfolios.prices :as prices]
            [ib-sma-portfolios.ranks :as ranks]
            [ib-sma-portfolios.shortinfo :as shortinfo]
            [ib-sma-portfolios.settings :as settings])
  (:import [java.io File]
           [java.util Date]))

(defn -main [& args]
  (let [data-dir (first (first args))]
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

    ;; Load settings data
    (let [account-settings (settings/load-account-settings data-dir)
          allocator-settings (settings/load-allocator-settings data-dir)
          portfolio-settings (settings/load-portfolio-settings data-dir)]
      (println "Settings loaded:"
               (count account-settings) "accounts,"
               (count portfolio-settings) "portfolios"))))