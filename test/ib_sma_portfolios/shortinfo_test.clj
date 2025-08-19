(ns ib_sma_portfolios.shortinfo_test
  (:require [clojure.test :refer :all]
            [ib_sma_portfolios.shortinfo :as shortinfo]))

(deftest test-shortinfo-data-loaded
  (is (pos? (count shortinfo/shortinfo-data)))
  (is (map? (first shortinfo/shortinfo-data)))
  (is (contains? (first shortinfo/shortinfo-data) :SYM)))

(deftest test-shortinfo-data-content
  (let [first-record (first shortinfo/shortinfo-data)]
    (is (= "2391358D" (:SYM first-record)))))

(deftest test-load-data-function
  (let [data-dir "/home/unveiled/work/P123/script/ib_data"
        shortinfo-data (shortinfo/load-data data-dir)]
    (is (pos? (count shortinfo-data)))
    (is (map? (first shortinfo-data)))
    (is (contains? (first shortinfo-data) :SYM))
    ;; Should have same count as the default loaded data
    (is (= (count shortinfo-data) (count shortinfo/shortinfo-data)))))

(deftest test-load-data-with-filename
  (let [data-dir "/home/unveiled/work/P123/script/ib_data"
        filename "shorting.tsv"
        shortinfo-data (shortinfo/load-data data-dir filename)]
    (is (pos? (count shortinfo-data)))
    (is (map? (first shortinfo-data)))
    (is (contains? (first shortinfo-data) :SYM))
    ;; Should have same count as the default loaded data
    (is (= (count shortinfo-data) (count shortinfo/shortinfo-data)))))