(ns ib-sma-portfolios.settings-test
  (:require [clojure.test :refer :all]
            [ib-sma-portfolios.settings :as settings]))

(deftest test-settings-loaded
  (is (map? settings/account-settings))
  (is (map? settings/allocator-settings))
  (is (map? settings/portfolio-settings)))

(deftest test-account-settings-content
  (is (contains? settings/account-settings "F1913897"))
  (is (contains? (get settings/account-settings "F1913897") :desc)))

(deftest test-allocator-settings-content
  (is (contains? settings/allocator-settings :ib_port_num))
  (is (contains? settings/allocator-settings :default_acct)))

(deftest test-portfolio-settings-content
  (is (contains? settings/portfolio-settings "Omega"))
  (is (contains? (get settings/portfolio-settings "Omega") :num_recs)))