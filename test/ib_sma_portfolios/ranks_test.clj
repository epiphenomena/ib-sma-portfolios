(ns ib_sma_portfolios.ranks_test
  (:require [clojure.test :refer :all]
            [ib_sma_portfolios.ranks :as ranks]))

(deftest test-ranks-data-loaded
  (is (map? ranks/ranks-data))
  (is (pos? (count ranks/ranks-data))))

(deftest test-ranks-data-content
  (is (contains? ranks/ranks-data :plain_ranks_longs))
  (is (contains? ranks/ranks-data :plain_ranks_shorts))
  (is (pos? (count (:plain_ranks_longs ranks/ranks-data))))
  (is (pos? (count (:plain_ranks_shorts ranks/ranks-data))))
  (is (map? (first (:plain_ranks_longs ranks/ranks-data))))
  (is (contains? (first (:plain_ranks_longs ranks/ranks-data)) :SYMBOL)))

(deftest test-load-data-function
  (let [data-dir "/home/unveiled/work/P123/script/ib_data"
        ranks-data (ranks/load-data data-dir)]
    (is (map? ranks-data))
    (is (pos? (count ranks-data)))
    (is (contains? ranks-data :plain_ranks_longs))
    (is (contains? ranks-data :plain_ranks_shorts))
    ;; Should have same keys as the default loaded data
    (is (= (keys ranks-data) (keys ranks/ranks-data)))))