(ns ib-sma-portfolios.test-runner
  (:require [ib-sma-portfolios.shorting :as shorting]))

(defn -main []
  (println "Testing shorting data loading...")
  (println "Total records:" (count shorting/shorting-data))
  (println "First record:" (first shorting/shorting-data))
  (println "Sample symbols:" (take 5 (map :SYM shorting/shorting-data)))
  (System/exit 0))