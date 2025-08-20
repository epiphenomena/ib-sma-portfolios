(ns settings-test
  (:require [clojure.test :refer :all]
            [settings]))



(deftest test-load-account-settings-function
  (let [data-dir "/home/unveiled/work/P123/script/ib_data"
        account-settings (settings/load-account-settings data-dir)]
    (is (map? account-settings))

    ))

(deftest test-load-allocator-settings-function
  (let [data-dir "/home/unveiled/work/P123/script/ib_data"
        allocator-settings (settings/load-allocator-settings data-dir)]
    (is (map? allocator-settings))

    ))

(deftest test-load-portfolio-settings-function
  (let [data-dir "/home/unveiled/work/P123/script/ib_data"
        portfolio-settings (settings/load-portfolio-settings data-dir)]
    (is (map? portfolio-settings))

    ))