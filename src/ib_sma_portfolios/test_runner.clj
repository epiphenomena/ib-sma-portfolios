#!/usr/bin/env clojure

(ns ib-sma-portfolios.test-runner
  (:require [clojure.test :refer [run-tests]]
            [ib_sma_portfolios.prices_test]
            [ib_sma_portfolios.ranks_test]
            [ib_sma_portfolios.shortinfo_test]
            [ib_sma_portfolios.settings_test]))

(defn -main []
  (println "Running all tests...")
  (println "\n=== Prices Tests ===")
  (run-tests 'ib_sma_portfolios.prices_test)
  (println "\n=== Ranks Tests ===")
  (run-tests 'ib_sma_portfolios.ranks_test)
  (println "\n=== Shortinfo Tests ===")
  (run-tests 'ib_sma_portfolios.shortinfo_test)
  (println "\n=== Settings Tests ===")
  (run-tests 'ib_sma_portfolios.settings_test)
  (println "\nAll tests completed.")
  (System/exit 0))