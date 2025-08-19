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

(deftest test-load-data-function
  (let [data-dir "/home/unveiled/work/P123/script/ib-data"
        prices-data (prices/load-data data-dir)]
    (is (pos? (count prices-data)))
    (is (map? (first prices-data)))
    (is (contains? (first prices-data) :SYMBOL))
    ;; Should have same count as the default loaded data
    (is (= (count prices-data) (count prices/prices-data)))))

(deftest test-load-data-with-filename
  (let [data-dir "/home/unveiled/work/P123/script/ib-data"
        filename "prices.tsv"
        prices-data (prices/load-data data-dir filename)]
    (is (pos? (count prices-data)))
    (is (map? (first prices-data)))
    (is (contains? (first prices-data) :SYMBOL))
    ;; Should have same count as the default loaded data
    (is (= (count prices-data) (count prices/prices-data)))))