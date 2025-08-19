(ns ib-sma-portfolios.core
  (:require [clojure.data.json :as json]
            [clj-http.client :as http]
            [cheshire.core :as cheshire]))

(defn -main [& args]
  (let [data-dir (or (first args) "/home/unveiled/work/P123/script/ib_data")]
    (println "IB SMA Portfolios running with data directory:" data-dir)
    (System/setProperty "ib.sma.portfolios.data.dir" data-dir)
    (flush)))