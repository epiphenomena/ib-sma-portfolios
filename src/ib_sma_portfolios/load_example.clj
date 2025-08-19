(ns ib-sma-portfolios.load-example
  (:require [ib-sma-portfolios.prices :as prices]
            [ib-sma-portfolios.ranks :as ranks]
            [ib-sma-portfolios.shortinfo :as shortinfo]
            [ib-sma-portfolios.settings :as settings]))

;; Example of how to use the new load functions with a custom data directory

(defn example-with-custom-directory [data-dir]
  (println "Loading data from:" data-dir)
  
  ;; Load prices data
  (let [prices-data (prices/load-data data-dir)]
    (println "Loaded" (count prices-data) "prices records"))
  
  ;; Load ranks data
  (let [ranks-data (ranks/load-data data-dir)]
    (println "Loaded ranks data with" (count ranks-data) "files"))
  
  ;; Load shortinfo data
  (let [shortinfo-data (shortinfo/load-data data-dir)]
    (println "Loaded" (count shortinfo-data) "shortinfo records"))
  
  ;; Load settings data
  (let [account-settings (settings/load-account-settings data-dir)
        allocator-settings (settings/load-allocator-settings data-dir)
        portfolio-settings (settings/load-portfolio-settings data-dir)]
    (println "Loaded settings for" (count account-settings) "accounts")
    (println "Loaded allocator settings:" (keys allocator-settings))
    (println "Loaded settings for" (count portfolio-settings) "portfolios")))

(comment
  ;; Example usage:
  ;; (example-with-custom-directory "/path/to/your/data/directory")
  )