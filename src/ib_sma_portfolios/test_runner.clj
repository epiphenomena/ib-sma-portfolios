#!/usr/bin/env clojure

(ns ib-sma-portfolios.test-runner
  (:require [clojure.test :refer [run-tests]]
            [ib-sma-portfolios.prices-test]
            [ib-sma-portfolios.ranks-test]
            [ib-sma-portfolios.shortinfo-test]
            [ib-sma-portfolios.settings-test]))

(defn -main []
  (println "Running all tests...")
  (println "\n=== Prices Tests ===")
  (run-tests 'ib-sma-portfolios.prices-test)
  (println "\n=== Ranks Tests ===")
  (run-tests 'ib-sma-portfolios.ranks-test)
  (println "\n=== Shortinfo Tests ===")
  (run-tests 'ib-sma-portfolios.shortinfo-test)
  (println "\n=== Settings Tests ===")
  (run-tests 'ib-sma-portfolios.settings-test)
  (println "\nAll tests completed.")
  (System/exit 0))