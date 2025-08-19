(ns ib-sma-portfolios.shortinfo-test
  (:require [clojure.test :refer :all]
            [ib-sma-portfolios.shortinfo :as shortinfo]))

(deftest test-shortinfo-data-loaded
  (is (pos? (count shortinfo/shortinfo-data)))
  (is (map? (first shortinfo/shortinfo-data)))
  (is (contains? (first shortinfo/shortinfo-data) :SYM)))

(deftest test-shortinfo-data-content
  (let [first-record (first shortinfo/shortinfo-data)]
    (is (= "2391358D" (:SYM first-record)))))