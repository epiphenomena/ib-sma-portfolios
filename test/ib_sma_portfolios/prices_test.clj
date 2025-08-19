(ns ib-sma-portfolios.prices-test
  (:require [clojure.test :refer :all]
            [ib-sma-portfolios.prices :as prices]))

(deftest test-load-data-function
  (let [data-dir "/home/unveiled/work/P123/script/ib_data"
        prices-data (:data (prices/load-data data-dir))]
    (is (pos? (count prices-data)))
    (is (map? (first prices-data)))
    (is (contains? (first prices-data) :SYMBOL))
    ))

(deftest test-load-data-with-filename
  (let [data-dir "/home/unveiled/work/P123/script/ib_data"
        filename "prices.tsv"
        prices-data (:data (prices/load-data data-dir filename))]
    (is (pos? (count prices-data)))
    (is (map? (first prices-data)))
    (is (contains? (first prices-data) :SYMBOL))
    ))