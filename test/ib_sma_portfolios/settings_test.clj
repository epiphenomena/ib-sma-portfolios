(ns ib_sma_portfolios.settings_test
  (:require [clojure.test :refer :all]
            [ib_sma_portfolios.settings :as settings]))

(deftest test-settings-loaded
  (is (map? settings/account-settings))
  (is (map? settings/allocator-settings))
  (is (map? settings/portfolio-settings)))

(deftest test-account-settings-content
  (is (contains? settings/account-settings "F1913897"))
  (is (contains? (get settings/account-settings "F1913897") "desc")))

(deftest test-allocator-settings-content
  (is (contains? settings/allocator-settings "ib_port_num"))
  (is (contains? settings/allocator-settings "default_acct")))

(deftest test-portfolio-settings-content
  (is (contains? settings/portfolio-settings "Omega"))
  (is (contains? (get settings/portfolio-settings "Omega") "num_recs")))

(deftest test-load-account-settings-function
  (let [data-dir "/home/unveiled/work/P123/script/ib_data"
        account-settings (settings/load-account-settings data-dir)]
    (is (map? account-settings))
    ;; Should have same keys as the default loaded data
    (is (= (keys account-settings) (keys settings/account-settings)))))

(deftest test-load-allocator-settings-function
  (let [data-dir "/home/unveiled/work/P123/script/ib_data"
        allocator-settings (settings/load-allocator-settings data-dir)]
    (is (map? allocator-settings))
    ;; Should have same keys as the default loaded data
    (is (= (keys allocator-settings) (keys settings/allocator-settings)))))

(deftest test-load-portfolio-settings-function
  (let [data-dir "/home/unveiled/work/P123/script/ib_data"
        portfolio-settings (settings/load-portfolio-settings data-dir)]
    (is (map? portfolio-settings))
    ;; Should have same keys as the default loaded data
    (is (= (keys portfolio-settings) (keys settings/portfolio-settings)))))