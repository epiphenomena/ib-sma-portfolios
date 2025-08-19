(ns ib-sma-portfolios.test-runner
  (:require [ib-sma-portfolios.prices :as prices]
            [ib-sma-portfolios.ranks :as ranks]
            [ib-sma-portfolios.shortinfo :as shortinfo]
            [ib-sma-portfolios.settings :as settings]))

(defn -main []
  (println "Testing prices data loading...")
  (println "Total prices records:" (count prices/prices-data))
  (println "First prices record:" (first prices/prices-data))
  
  (println "\nTesting ranks data loading...")
  (println "Ranks data keys:" (keys ranks/ranks-data))
  (println "Longs records count:" (count (:plain_ranks_longs ranks/ranks-data)))
  
  (println "\nTesting shortinfo data loading...")
  (println "Total shortinfo records:" (count shortinfo/shortinfo-data))
  (println "First shortinfo record:" (first shortinfo/shortinfo-data))
  
  (println "\nTesting settings data loading...")
  (println "Account settings count:" (count settings/account-settings))
  (println "Allocator settings:" (keys settings/allocator-settings))
  (println "Portfolio settings count:" (count settings/portfolio-settings))
  
  (System/exit 0))