(ns ib-sma-portfolios.prices-test
  (:require [clojure.test :refer :all]
            [ib-sma-portfolios.prices :as prices]))

(deftest test-prices-data-loaded
  (is (pos? (count prices/prices-data)))
  (is (map? (first prices/prices-data)))
  (is (contains? (first prices/prices-data) :SYMBOL)))

(deftest test-prices-data-content
  (let [first-record (first prices/prices-data)]
    (is (= "A:USA" (:SYMBOL first-record)))))