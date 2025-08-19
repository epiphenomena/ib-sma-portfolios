(ns ib-sma-portfolios.ranks-test
  (:require [clojure.test :refer :all]
            [ib-sma-portfolios.ranks :as ranks]))

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