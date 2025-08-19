(ns ib-sma-portfolios.ranks-test
  (:require [clojure.test :refer :all]
            [ib-sma-portfolios.ranks :as ranks]))



(deftest test-load-data-function
  (let [data-dir "/home/unveiled/work/P123/script/ib_data"
        ranks-data (ranks/load-data data-dir)]
    (is (map? ranks-data))
    (is (contains? ranks-data :plain_ranks_longs))
    (is (contains? ranks-data :plain_ranks_shorts))
    ))