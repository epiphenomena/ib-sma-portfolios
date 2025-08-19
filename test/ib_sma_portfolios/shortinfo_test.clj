(ns ib-sma-portfolios.shortinfo-test
  (:require [clojure.test :refer :all]
            [ib-sma-portfolios.shortinfo :as shortinfo]))

(deftest test-load-data-function
  (let [data-dir "/home/unveiled/work/P123/script/ib_data"
        shortinfo-data (:data (shortinfo/load-data data-dir))]
    (is (pos? (count shortinfo-data)))
    (is (map? (first shortinfo-data)))
    (is (contains? (first shortinfo-data) :SYM))
    ))

(deftest test-load-data-with-filename
  (let [data-dir "/home/unveiled/work/P123/script/ib_data"
        filename "shorting.tsv"
        shortinfo-data (:data (shortinfo/load-data data-dir filename))]
    (is (pos? (count shortinfo-data)))
    (is (map? (first shortinfo-data)))
    (is (contains? (first shortinfo-data) :SYM))
    ))

(def shortinfo-data (:data (shortinfo/load-data "/home/unveiled/work/P123/script/ib_data")))